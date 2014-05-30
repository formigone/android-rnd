package com.formigone.frags.activity;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.formigone.frags.R;
import com.formigone.frags.adapter.CardAdapter;
import com.formigone.frags.fragment.RandFragment;
import com.formigone.frags.view.Card;

public class HelloWorldActivity extends Activity {
	private static String TAG = "HelloWorldActivity";

	private List<Card> items;
	private CardAdapter adapter;

	private ListView listView;
	private TextView titleTextView;
	private FrameLayout frameLayout;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		items = new ArrayList<Card>();
		items.add(new Card("KSL"));
		items.add(new Card("Deseret News"));
		items.add(new Card("Deseret Connect"));
		items.add(new Card("OK.com"));
		items.add(new Card("Familia.com"));
		items.add(new Card("DDM"));
		items.add(new Card("outdoors.ksl.com"));
		items.add(new Card("/Recd"));
		items.add(new Card("/SA"));
		items.add(new Card("<rand>", "RandFragment"));

		setContentView(R.layout.main_layout);

		getActionBar().setTitle("Hello, World!");

		frameLayout = (FrameLayout) findViewById(R.id.frame_layout);
		titleTextView = (TextView) findViewById(R.id.sectionTitle);
		listView = (ListView) findViewById(R.id.sectionItems);
		adapter = new CardAdapter(this, R.layout.main_layout_cards, items);
		listView.setAdapter(adapter);

		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Card item = items.get(position);
				String type = item.getFragHref();

				if (type != null) {
					titleTextView.setVisibility(View.GONE);
					listView.setVisibility(View.GONE);
					frameLayout.setVisibility(View.VISIBLE);

					Fragment frag = null;

					if (type == "RandFragment") {
						frag = new RandFragment();
					}

					if (frag != null) {
						FragmentManager manager = getFragmentManager();
						manager.beginTransaction()
							.add(R.id.frame_layout, frag)
							.addToBackStack(type)
							.commit();
					}
				}
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		menu.add(Menu.NONE, Menu.FIRST + 1, Menu.NONE, "Help");
		menu.add(Menu.NONE, Menu.FIRST + 2, Menu.NONE, "About");

		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		Card _item;
		Intent intent;

		switch (item.getItemId()) {
			case Menu.FIRST + 1:
				_item = items.get(0);
				_item.setTitle(_item.getTitle() + "!");
				break;

			case Menu.FIRST + 2:
				intent = new Intent(this, RandomActivity.class);
				startActivity(intent);
				break;
		}

		adapter.notifyDataSetChanged();

		return super.onOptionsItemSelected(item);
	}
}
