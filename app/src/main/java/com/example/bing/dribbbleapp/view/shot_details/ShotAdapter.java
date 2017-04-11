package com.example.bing.dribbbleapp.view.shot_details;

import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.bing.dribbbleapp.R;
import com.example.bing.dribbbleapp.model.Shot;
import com.squareup.picasso.Picasso;

/**
 * Created by Bing on 2016/9/19.
 */
public class ShotAdapter extends RecyclerView.Adapter {

	private static final int VIEW_TYPE_SHOT_IMAGE = 0;
	private static final int VIEW_TYPE_SHOT_INFO = 1;

	Shot shot;

	public ShotAdapter(Shot shot) {
		this.shot = shot;
	}

	@Override
	public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View view;
		switch (viewType) {
			case VIEW_TYPE_SHOT_IMAGE:
				view = LayoutInflater.from(parent.getContext()).inflate(R.layout.shot_image, parent, false);
				return new ImageViewHolder(view);

			case VIEW_TYPE_SHOT_INFO:
				view = LayoutInflater.from(parent.getContext()).inflate(R.layout.shot_item_info, parent, false);
				return new InfoViewHolder(view);
		}
		return null;
	}

	@Override
	public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
		int viewType = getItemViewType(position);

		switch (viewType) {
			case VIEW_TYPE_SHOT_IMAGE:
				Picasso.with(holder.itemView.getContext())
						.load(shot.getImageUrl())
						.placeholder(R.drawable.shot_placeholder)
						.into(((ImageViewHolder) holder).imageView);
				break;
			case VIEW_TYPE_SHOT_INFO:
				InfoViewHolder infoViewHolder = (InfoViewHolder) holder;
				infoViewHolder.title.setText(shot.title);
				infoViewHolder.authorName.setText(shot.user.name);
				infoViewHolder.description.setText(Html.fromHtml(shot.description == null ? "" : shot.description));
				infoViewHolder.likeCount.setText(String.valueOf(shot.likes_count));
				infoViewHolder.bucketCount.setText(String.valueOf(shot.buckets_count));
				infoViewHolder.viewCount.setText(String.valueOf(shot.views_count));

				Picasso.with(holder.itemView.getContext())
						.load(shot.user.avatar_url)
						.placeholder(R.drawable.user_picture_placeholder)
						.into(infoViewHolder.authorPicture);

				break;
		}
	}

	@Override
	public int getItemCount() {
		return 2;
	}

	@Override
	public int getItemViewType(int position) {
		if(position == 0) {
			return VIEW_TYPE_SHOT_IMAGE;
		} else {
			return VIEW_TYPE_SHOT_INFO;
		}
	}
}
