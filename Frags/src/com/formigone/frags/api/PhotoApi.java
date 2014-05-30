package com.formigone.frags.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

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
//		JsonObjectRequest req = new JsonObjectRequest(Request.Method.GET, url,
//				null,
//				new Listener<JSONObject>() {
//
//					@Override
//					public void onResponse(JSONObject response) {
//						Log.i(TAG, response.toString());
//					}
//				}, new ErrorListener() {
//
//					@Override
//					public void onErrorResponse(VolleyError error) {
//						Log.i(TAG, "API Error");
//					}
//				});
//
//		FragsApplication.getRequestQueue().add(req);
//
//		String data = url;
//
//		// TODO: Do JSON Request with Volley
//		Result res = new Result(Result.STATUS.SUCCESS, data);
//
//		return res;
		
		StringBuilder str = new StringBuilder();
		HttpGet req = new HttpGet(url);
		Result out = new Result(Result.STATUS.SUCCESS, "");
		
		try {
			HttpResponse resp = mClient.execute(req);
			int code = resp.getStatusLine().getStatusCode();

			if (code == 200) {
				HttpEntity entity = resp.getEntity();
				InputStream content = entity.getContent();
				BufferedReader reader = new BufferedReader(new InputStreamReader(content));
				String line;
				
				while ((line = reader.readLine()) != null) {
					str.append(line);
				}

				try {
					JSONObject json = new JSONObject(str.toString());
					out.data = json.getString("result");
					Log.i(TAG, "JSON.result = " + out.data);
				} catch (JSONException e) {
					out.status = Result.STATUS.ERROR;
				}
			} else {
				out.status = Result.STATUS.ERROR;
			}
		} catch (ClientProtocolException e) {
			out.status = Result.STATUS.ERROR;
		} catch (IOException e) {
			out.status = Result.STATUS.ERROR;
		}
		
		return out;
	}
}
