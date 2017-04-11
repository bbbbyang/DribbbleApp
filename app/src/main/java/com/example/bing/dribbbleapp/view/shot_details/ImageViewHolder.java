package com.example.bing.dribbbleapp.view.shot_details;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.example.bing.dribbbleapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Bing on 2016/9/19.
 */
public class ImageViewHolder extends RecyclerView.ViewHolder {

	@BindView(R.id.shot_image) ImageView imageView;

	public ImageViewHolder(View itemView) {
		super(itemView);
		ButterKnife.bind(this, itemView);
	}
}
