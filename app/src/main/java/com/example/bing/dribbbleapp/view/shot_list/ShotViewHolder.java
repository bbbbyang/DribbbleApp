package com.example.bing.dribbbleapp.view.shot_list;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bing.dribbbleapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Bing on 2016/9/16.
 */
public class ShotViewHolder extends RecyclerView.ViewHolder {

	@BindView(R.id.shot_clickable_cover) public View cover;
	@BindView(R.id.shot_view_count)	public TextView viewCount;
	@BindView(R.id.shot_like_count) public TextView likeCount;
	@BindView(R.id.shot_bucket_count) public TextView bucketCount;
	@BindView(R.id.shot_image) public ImageView image;

	public ShotViewHolder(View itemView) {
		super(itemView);
		ButterKnife.bind(this, itemView);
	}
}
