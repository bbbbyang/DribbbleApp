package com.example.bing.dribbbleapp.view.shot_list;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.os.AsyncTaskCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bing.dribbbleapp.R;
import com.example.bing.dribbbleapp.dribbble.Dribbble;
import com.example.bing.dribbbleapp.model.Shot;
import com.example.bing.dribbbleapp.model.User;
import com.example.bing.dribbbleapp.view.base.SpaceItemDecoration;
import com.google.gson.JsonSyntaxException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Bing on 2016/9/16.
 */
public class ShotListFragment extends Fragment {

	private static final int COUNT_PRE_PAGE = 12;

	@BindView(R.id.recycler_view) RecyclerView recyclerView;


	private ShotListAdapter shotListAdapter;

	public static ShotListFragment newInstance() {
		return new ShotListFragment();
	}

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_recycler_view, container, false);
		ButterKnife.bind(this, view);
		return view;
	}

	@Override
	public void onViewCreated(final View view, Bundle savedInstanceState) {
		recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
		recyclerView.addItemDecoration(new SpaceItemDecoration(getResources().getDimensionPixelOffset(R.dimen.spacing_medium)));

		shotListAdapter = new ShotListAdapter(new ArrayList<Shot>(), new ShotListAdapter.LoadMoreListener() {
			@Override
			public void onLoadMore() {

				int page = shotListAdapter.getDataCount() / COUNT_PRE_PAGE + 1;
				AsyncTaskCompat.executeParallel(new LoadShotTask(page));

			}
		});
		recyclerView.setAdapter(shotListAdapter);
	}

	private class LoadShotTask extends AsyncTask<Void, Void, List<Shot>> {

		private int page;

		LoadShotTask(int page) {
			this.page = page;
		}

		@Override
		protected List<Shot> doInBackground(Void... params) {
			try {
				return Dribbble.getShots(page);
			} catch (IOException | JsonSyntaxException e) {
				e.printStackTrace();
				return null;
			}
		}

		@Override
		protected void onPostExecute(List<Shot> shots) {
			if(shots != null) {
				shotListAdapter.append(shots);
			} else {
				Snackbar.make(getView(), "Error", Snackbar.LENGTH_LONG).show();
			}
		}
	}

	private List<Shot> fakeData(int page) {
		List<Shot> shotList = new ArrayList<>();
		Random random = new Random();

		int count = page < 2 ? COUNT_PRE_PAGE : 10;

		for(int i = 0; i < count; i++) {
			Shot shot = new Shot();
			shot.views_count = random.nextInt(1000);
			shot.buckets_count = random.nextInt(50);
			shot.likes_count = random.nextInt(200);

			shot.user = new User();
			shot.user.name = shot.title + " author";
			shotList.add(shot);
		}
		return shotList;
	}
}