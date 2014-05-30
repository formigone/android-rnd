package com.formigone.frags.view;

import android.view.View;

public interface Fillable {
	public void fill(View convertView);
	// TODO: Make return type generic + type safe
	public Object getHandler(View convertView);
}
