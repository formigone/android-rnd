package com.formigone.android.rnd.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.formigone.android.rnd.model.Article;

public class KSLRssArticleService implements ArticleService {

	private static final String url = "http://www.ksl.com/xml/148,1070.rss";

	@Override
	public List<Article> getArticles(int max) {
		if (max < 1)
			max = 1;
		
		List<Article> articles = new ArrayList<Article>();

		try {
			String xml = getRss(url);
			Document feed = createXML(xml);
			articles = getArticlesFromFeed(feed, max);
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		}

		return articles;
	}

	private String getRss(final String url) throws MalformedURLException, IOException {
		InputStream in = new URL(url).openConnection().getInputStream();
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		StringBuilder sb = new StringBuilder();
		String line;
		
		while ((line = br.readLine()) != null)
			sb.append(line);
		
		return sb.toString();
	}
	
	private Document createXML(final String xml) throws ParserConfigurationException, SAXException, IOException {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setNamespaceAware(true);
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document doc = builder.parse(new InputSource(new StringReader(xml)));
		doc.normalize();

		return doc;
	}

	private List<Article> getArticlesFromFeed(Document feed, int max) {
		List<Article> articles = new ArrayList<Article>();

		NodeList nodeList = feed.getElementsByTagName("item");

		// item
		//  |
		//  +---- title
		//  +---- dc:creator
		//  +---- link
		//  +---- enclosure
		//  +---- description
		//  +---- pubDate
		
		//
		//
		//
		for (int i = 0; i < nodeList.getLength() && i < max; i++) {
			Node item = nodeList.item(i);
			
			Node node = item.getFirstChild().getNextSibling();
			String title = node.getTextContent();
			System.out.println("Title: " + title);
			
			Article article = new Article(title);
			System.out.println("-----");
			
			articles.add(article);
		}
		//
		//
		//
		
		
		//
		//
		//
/*		for (int i = 0; i < nodeList.getLength() && i < max; i++) {
			Node item = nodeList.item(i);
			
			NodeList itemNodes = item.getChildNodes();
			for (int x = 0; x < itemNodes.getLength(); x++) {

				// TODO: Find a way to query the node!
				Node node = itemNodes.item(x);
				if (node.getNodeName() == "title") {
					String title = node.getTextContent();
					System.out.println("Title: " + title);
					Article article = new Article(title);
					articles.add(article);
					break;
				}
			}
			//
			//
			//
		}*/

		return articles;
	}
}
