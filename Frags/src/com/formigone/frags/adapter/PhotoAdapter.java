package com.formigone.frags.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.formigone.frags.R;
import com.formigone.frags.view.Card;

public class PhotoAdapter extends BaseAdapter {

	protected List<Card> items;
	protected int layoutRes;
	protected ImageLoader imageLoader;

	public PhotoAdapter(Context context, int view, List<Card> items) {
		this.items = items;
		this.layoutRes = view;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Card item = items.get(position);

		if (convertView == null) {
			LayoutInflater inflater = (LayoutInflater) parent.getContext()
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = inflater.inflate(layoutRes, null);
		}

		// item.fill(convertView);

		NetworkImageView img = (NetworkImageView)convertView.findViewById(R.id.img);
		if (img != null) {
			// TODO: Init imageLoader
			img.setImageUrl(item.getTitle(), imageLoader);
		}

		return convertView;
	}

	@Override
	public int getCount() {
		return items.size();
	}

	@Override
	public Object getItem(int position) {
		return items.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}
}
