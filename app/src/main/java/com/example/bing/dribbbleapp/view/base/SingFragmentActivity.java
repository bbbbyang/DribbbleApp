package com.example.bing.dribbbleapp.view.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.example.bing.dribbbleapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Bing on 2016/9/19.
 */
public abstract class SingFragmentActivity extends AppCompatActivity {

	@BindView(R.id.toolbar)	Toolbar toolbar;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_single_fragment);
		ButterKnife.bind(this);

		setSupportActionBar(toolbar);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);

		setTitle(getActivityTitle());

		if(savedInstanceState == null) {
			getSupportFragmentManager()
					.beginTransaction()
					.add(R.id.fragment_container, newFragment())
					.commit();
		}
	}

	protected String getActivityTitle() {
		return "";
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if(item.getItemId() == android.R.id.home) {
			finish();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	protected abstract Fragment newFragment();
}
