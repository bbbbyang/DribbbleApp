package com.example.bing.dribbbleapp.view.shot_list;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bing.dribbbleapp.R;
import com.example.bing.dribbbleapp.model.Shot;
import com.example.bing.dribbbleapp.utils.ModelUtils;
import com.example.bing.dribbbleapp.view.shot_details.ShotActivity;
import com.example.bing.dribbbleapp.view.shot_details.ShotFragment;
import com.google.gson.reflect.TypeToken;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Bing on 2016/9/16.
 */
public class ShotListAdapter extends RecyclerView.Adapter {

	private static final int VIEW_TYPE_SHOT = 1;
	private static final int VIEW_TYPE_LOADING = 2;

	private List<Shot> data;
	private LoadMoreListener loadMoreListener;
	private boolean showLoading;

	public ShotListAdapter(List<Shot> data, LoadMoreListener loadMoreListener) {
		this.data = data;
		this.loadMoreListener = loadMoreListener;
		showLoading = true;
	}

	@Override
	public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		if(viewType == VIEW_TYPE_SHOT) {
			View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_shot, parent, false);
			return new ShotViewHolder(view);
		} else {
			View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_loading, parent, false);
			return new RecyclerView.ViewHolder(view) {};
		}
	}

	@Override
	public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {

		int viewType = getItemViewType(position);

		if(viewType == VIEW_TYPE_LOADING) {
			loadMoreListener.onLoadMore();
		} else {
			final Shot shot = data.get(position);

			ShotViewHolder shotViewHolder = (ShotViewHolder) holder;
			shotViewHolder.viewCount.setText(String.valueOf(shot.views_count));
			shotViewHolder.likeCount.setText(String.valueOf(shot.likes_count));
			shotViewHolder.bucketCount.setText(String.valueOf(shot.buckets_count));

			Picasso.with(holder.itemView.getContext())
					.load(shot.getImageUrl())
					.placeholder(R.drawable.shot_placeholder)
					.into(shotViewHolder.image);

			shotViewHolder.cover.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					Context context = holder.itemView.getContext();
					Intent intent = new Intent(context, ShotActivity.class);
					intent.putExtra(ShotFragment.KEY_SHOT, ModelUtils.toString(shot, new TypeToken<Shot>() {
					}));
					intent.putExtra(ShotActivity.KEY_SHOT_TITLE, shot.title);
					context.startActivity(intent);
				}
			});
		}
	}

	@Override
	public int getItemCount() {
		return showLoading ? data.size() + 1 : data.size();
	}

	@Override
	public int getItemViewType(int position) {
		return position < data.size() ? VIEW_TYPE_SHOT : VIEW_TYPE_LOADING;
	}

	public int getDataCount() {
		return data.size();
	}

	public void setShowLoading(boolean showLoading) {
		this.showLoading = showLoading;
		notifyDataSetChanged();
	}

	public void append(List<Shot> moreShots) {
		data.addAll(moreShots);
		notifyDataSetChanged();
	}

	public interface LoadMoreListener {
		void onLoadMore();
	}
}
