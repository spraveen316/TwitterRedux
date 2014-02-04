package com.project.twitterredux;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

import com.loopj.android.http.JsonHttpResponseHandler;

public class ComposeActivity extends Activity {
	
	EditText etTweet;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_compose);
	}
	
	public void onClickTweet(View view) {
		etTweet = (EditText)findViewById(R.id.etComposeTweet);
		etTweet.addTextChangedListener(new TextWatcher() {
		    @Override
		    public void onTextChanged(CharSequence s, int start, int before, int count) {
		        // Fires right as the text is being changed (even supplies the range of text)
		    }

		    @Override
		    public void beforeTextChanged(CharSequence s, int start, int count,
		            int after) {
		        // Fires right before text is changing
		    }

		    @Override
		    public void afterTextChanged(Editable s) {
		        // Fires right after the text has changed
		    	etTweet.setText(s.toString());
		    }
		});

		
		if (StringUtils.isEmpty(etTweet.getText().toString())) {
			etTweet.setError(getResources().getString(R.string.error_tweet));
			return;
		}
		
		// POST tweet
		MyTwitterApp.getRestClient().postTweet(etTweet.getText().toString(), new JsonHttpResponseHandler() {
			@Override
			public void onSuccess(JSONArray jsonTweets){
			}
		});
		
		Intent intent = new Intent(this, TimelineActivity.class);
		startActivity(intent);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.compose, menu);
		return true;
	}

}
