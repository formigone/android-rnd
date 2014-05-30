package com.formigone.frags.fragment;

import java.util.ArrayList;
import java.util.List;

import android.app.Fragment;
import android.app.LoaderManager;
import android.content.Loader;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;

import com.formigone.frags.R;
import com.formigone.frags.adapter.PhotoAdapter;
import com.formigone.frags.loader.PhotoLoader;
import com.formigone.frags.model.Photo;
import com.formigone.frags.view.Card;

public class RandFragment extends Fragment {
	private static final String TAG = "RandFragment";
	private static final int PHOTO_LOADER_ID = 0;

	private BaseAdapter mAdapter;
	private GridView mGridView;

	// private BroadcastReceiver mReceiver;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		getActivity().getActionBar().setTitle("Rand Fragment!");
		getLoaderManager().initLoader(PHOTO_LOADER_ID, null, photoCallbacks);

		// mReceiver = new Loader();

		// Intent service = new Intent(getActivity(), SimpleService.class);
		// service.putExtra(SimpleService.LIST_SIZE_MAX, 10);
		// getActivity().startService(service);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.rand_frag, container, false);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		mGridView = (GridView) getView().findViewById(R.id.grid);
	}

	@Override
	public void onResume() {
		super.onResume();

		// IntentFilter filter = new
		// IntentFilter(SimpleService.ACTION_DATA_READY);
		// getActivity().registerReceiver(mReceiver, filter);
	}

	@Override
	public void onPause() {
		super.onPause();

		// getActivity().unregisterReceiver(mReceiver);
	}

	private LoaderManager.LoaderCallbacks<List<Photo>> photoCallbacks = new LoaderManager.LoaderCallbacks<List<Photo>>() {
		@Override
		public Loader<List<Photo>> onCreateLoader(int id, Bundle args) {
			return new PhotoLoader(getActivity());
		}

		@Override
		public void onLoaderReset(Loader<List<Photo>> loader) {
		}

		@Override
		public void onLoadFinished(Loader<List<Photo>> loader, List<Photo> data) {
			List<Card> cards = new ArrayList<Card>();
			
			for (Photo _photo:data) {
				cards.add(new Card(_photo.getSrc(), "???"));
			}

			mAdapter = new PhotoAdapter(getActivity(), R.layout.photo_card, cards);
			mGridView.setAdapter(mAdapter);
		}
	};
}
