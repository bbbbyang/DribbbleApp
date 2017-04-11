package com.example.bing.dribbbleapp.view.bucket_list;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bing.dribbbleapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Bing on 2016/9/18.
 */
public class BucketViewHolder extends RecyclerView.ViewHolder {

	@BindView(R.id.bucket_layout) View bucketLayout;
	@BindView(R.id.bucket_name)	TextView bucketName;
	@BindView(R.id.bucket_shot_count) TextView bucketShotCount;
	@BindView(R.id.bucket_shot_chosen)	ImageView bucketChosen;

	public BucketViewHolder(View itemView) {
		super(itemView);
		ButterKnife.bind(this, itemView);
	}
}
