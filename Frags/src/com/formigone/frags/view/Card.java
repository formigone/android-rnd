package com.formigone.frags.view;

import com.formigone.frags.R;

import android.view.View;
import android.widget.TextView;

public class Card implements Fillable {
	protected String title;
	protected String fragHref;
	
	// TODO: make handler protected!!!
	public static class ViewHandler
	{
		public TextView title;
	}

	public Card(String title) {
		this.title = title;
	}
	
	public Card(String title, String fragHref) {
		this.title = title;
		this.fragHref = fragHref;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getFragHref() {
		return fragHref;
	}

	public void setFragHref(String fragHref) {
		this.fragHref = fragHref;
	}

	@Override
	public void fill(View convertView) {
		ViewHandler handler = (ViewHandler) getHandler(convertView);
		
		if (handler.title != null) {
			handler.title.setText(title);
		}
	}

	@Override
	public Object getHandler(View convertView) {
		ViewHandler handler = (ViewHandler) convertView.getTag();

		if (handler != null) {
			return handler;
		}
		
		handler = new ViewHandler();
		handler.title = (TextView) convertView.findViewById(R.id.title);
		
		convertView.setTag(handler);

		return handler;
	}
}
