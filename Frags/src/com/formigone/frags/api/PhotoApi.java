package com.formigone.frags.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class PhotoApi {
	private static final String TAG = "PhotoApi";

	protected static final String URL = "http://dev.rnd.com/api/?action=";
	protected static final String METHOD_GET_PHOTOS = "getPhotos";
	protected static final String METHOD_GET_PHOTOS_PARAM_MAX = "max=";
	
	protected HttpClient mClient;

	public static class Result {
		public enum STATUS {
			SUCCESS, ERROR
		};

		public STATUS status;
		public String data;

		public Result(STATUS status, String data) {
			this.status = status;
			this.data = data;
		}
	}

	public PhotoApi() {
		mClient = new DefaultHttpClient();
	}

	public Result getPhotos(int max) {
		String url = URL + METHOD_GET_PHOTOS + "&" + METHOD_GET_PHOTOS_PARAM_MAX
				+ max;
		return call(url);
	}

	protected Result call(String url) {
		StringBuilder str = new StringBuilder();
//		HttpGet req = new HttpGet(url);
		Result out = new Result(Result.STATUS.ERROR, "");
		
		try {
			URL u = new URL(url);
			HttpURLConnection con = null;
			
			try {
				con = (HttpURLConnection)u.openConnection();
				con.setDoOutput(true);
				con.setRequestMethod("GET");
				con.connect();
				
				if (con.getResponseCode() == 200) {
					out.data = con.getRequestMethod();
				} else {
					out.data = "Response code " + con.getResponseCode();
				}
			} catch (IOException e) {
				out.data = "IO Exception";
			} finally {
				if (con != null) {
					con.disconnect();
				}
			}
		} catch (MalformedURLException e1) {
			out.data = "Malformed URL";
		}
		
//		try {
//			HttpResponse resp = mClient.execute(req);
//			int code = resp.getStatusLine().getStatusCode();
//
//			if (code == 200) {
//				HttpEntity entity = resp.getEntity();
//				InputStream content = entity.getContent();
//				BufferedReader reader = new BufferedReader(new InputStreamReader(content));
//				String line;
//				
//				while ((line = reader.readLine()) != null) {
//					str.append(line);
//				}
//
//				try {
//					JSONObject json = new JSONObject(str.toString());
//					out.data = json.getString("result");
//					Log.i(TAG, "JSON.result = " + out.data);
//				} catch (JSONException e) {
//					out.status = Result.STATUS.ERROR;
//				}
//			} else {
//				out.status = Result.STATUS.ERROR;
//			}
//		} catch (ClientProtocolException e) {
//			out.status = Result.STATUS.ERROR;
//		} catch (IOException e) {
//			out.status = Result.STATUS.ERROR;
//		}
		
		return out;
	}
}
