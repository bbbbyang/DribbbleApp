package com.example.bing.dribbbleapp.view.bucket_list;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bing.dribbbleapp.R;
import com.example.bing.dribbbleapp.model.Bucket;
import com.example.bing.dribbbleapp.view.base.SpaceItemDecoration;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Bing on 2016/9/18.
 */
public class BucketListFragment extends Fragment {

	@BindView(R.id.recycler_view) RecyclerView recyclerView;
	@BindView(R.id.fab)	FloatingActionButton fab;

	public static BucketListFragment newInstance() {
		return new BucketListFragment();
	}

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_fab_recycler_view, container, false);
		ButterKnife.bind(this, view);
		return view;
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
		recyclerView.addItemDecoration(new SpaceItemDecoration(getResources().getDimensionPixelOffset(R.dimen.spacing_medium)));

		BucketListAdapter bucketListAdapter = new BucketListAdapter(fakeData());
		recyclerView.setAdapter(bucketListAdapter);

		fab.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Snackbar.make(v, "Fab", Snackbar.LENGTH_LONG).show();
			}
		});
	}

	private List<Bucket> fakeData() {
		List<Bucket> bucketList = new ArrayList<>();
		Random random = new Random();
		for(int i = 0; i < 20; i++) {
			Bucket bucket = new Bucket();
			bucket.shots_count = random.nextInt();
			bucket.name = "Bucket" + i;
			bucketList.add(bucket);
		}
		return bucketList;
	}
}
