package com.example.bing.dribbbleapp.dribbble.auth;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.example.bing.dribbbleapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Bing on 2016/9/18.
 */
public class AuthActivity extends AppCompatActivity {

	public static final String KEY_URL = "url";
	public static final String KEY_CODE = "code";

	@BindView(R.id.toolbar)	Toolbar toolbar;
	@BindView(R.id.progress_bar) ProgressBar progressBar;
	@BindView(R.id.webview)	WebView webView;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_webview);

		ButterKnife.bind(this);

		setSupportActionBar(toolbar);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		setTitle(getString(R.string.auth_activity_title));

		progressBar.setMax(100);

		webView.setWebViewClient(new WebViewClient(){
			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				if(url.startsWith(Auth.REDIRECT_URI)) {
					Uri uri = Uri.parse(url);
					Intent intent = new Intent();
					intent.putExtra(KEY_CODE, uri.getQueryParameter(KEY_CODE));
					setResult(Activity.RESULT_OK, intent);
					finish();
				}
				return super.shouldOverrideUrlLoading(view, url);
			}

			@Override
			public void onPageStarted(WebView view, String url, Bitmap favicon) {
				progressBar.setVisibility(View.VISIBLE);
				progressBar.setProgress(0);
			}

			@Override
			public void onPageFinished(WebView view, String url) {
				progressBar.setVisibility(View.GONE);
			}
		});

		webView.setWebChromeClient(new WebChromeClient() {
			@Override
			public void onProgressChanged(WebView view, int newProgress) {
				progressBar.setProgress(newProgress);
			}
		});

		String url = getIntent().getStringExtra(KEY_URL);
		webView.loadUrl(url);

	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if(item.getItemId() == android.R.id.home) {
			finish();
			return true;
		}

		return super.onOptionsItemSelected(item);
	}
}
