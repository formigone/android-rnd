package com.formigone.frags.loader;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.util.Log;

import com.formigone.frags.api.PhotoApi;
import com.formigone.frags.model.Photo;

public class PhotoLoader extends AsyncTaskLoader<List<Photo>> {
	private static final String TAG = "PhotoLoader";

	protected List<Photo> mData;

	public PhotoLoader(Context context) {
		super(context);
	}

	@Override
	public List<Photo> loadInBackground() {
		List<Photo> data = new ArrayList<Photo>();
		PhotoApi api = new PhotoApi();

		PhotoApi.Result res = api.getPhotos(3);

		try {
			JSONObject json = new JSONObject(res.data);
			JSONArray jsonArray = json.getJSONArray("result");

			for (int i = 0; i < jsonArray.length(); i++) {
				String str = jsonArray.getString(i);
				Photo photo = new Photo(str);
				data.add(photo);
			}
		} catch (Exception e) {
		}

		return data;
	}

	@Override
	public void deliverResult(List<Photo> data) {
		super.deliverResult(data);
	}

	@Override
	protected void onStartLoading() {
		super.onStartLoading();

		if (mData != null) {
			deliverResult(mData);
		}

		if (isStarted()) {
			forceLoad();
		}
	}
}
