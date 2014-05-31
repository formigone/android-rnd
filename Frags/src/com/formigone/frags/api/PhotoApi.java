package com.formigone.frags.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;

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
		Result out = new Result(Result.STATUS.ERROR, "");

		try {
			URL u = new URL(url);
			HttpURLConnection con = null;

			try {
				con = (HttpURLConnection) u.openConnection();

				InputStreamReader in = new InputStreamReader(con.getInputStream());
				BufferedReader reader = new BufferedReader(in);
				String line;
				while ((line = reader.readLine()) != null) {
					str.append(line);
				}

				out.data = str.toString();
				out.status = Result.STATUS.SUCCESS;
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

		return out;
	}
}
