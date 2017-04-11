package com.example.bing.dribbbleapp.view.shot_details;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bing.dribbbleapp.R;

import org.w3c.dom.Text;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Bing on 2016/9/19.
 */
public class InfoViewHolder extends RecyclerView.ViewHolder {

	@BindView(R.id.shot_title) TextView title;
	@BindView(R.id.shot_author_name) TextView authorName;
	@BindView(R.id.shot_author_picture) ImageView authorPicture;
	@BindView(R.id.shot_description) TextView description;
	@BindView(R.id.shot_view_count) TextView viewCount;
	@BindView(R.id.shot_like_count) TextView likeCount;
	@BindView(R.id.shot_bucket_count) TextView bucketCount;

	public InfoViewHolder(View itemView) {
		super(itemView);
		ButterKnife.bind(this, itemView);
	}
}
