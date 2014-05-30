package com.formigone.frags.activity;

import java.util.Random;

import com.formigone.frags.R;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class RandomActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.random_num);
		
		TextView label = (TextView)findViewById(R.id.num);
		Random num = new Random();
		
		label.setText(num.nextInt() + "");
	}
}
