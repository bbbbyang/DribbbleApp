package com.example.bing.dribbbleapp.view.shot_details;

import android.support.v4.app.Fragment;

import com.example.bing.dribbbleapp.model.Shot;
import com.example.bing.dribbbleapp.view.base.SingFragmentActivity;

/**
 * Created by Bing on 2016/9/19.
 */
public class ShotActivity extends SingFragmentActivity {

	public static final String KEY_SHOT_TITLE = "title";

	@Override
	protected Fragment newFragment() {
		return ShotFragment.newInstance(getIntent().getExtras());
	}

	@Override
	protected String getActivityTitle() {
		return getIntent().getStringExtra(ShotActivity.KEY_SHOT_TITLE);
	}
}
