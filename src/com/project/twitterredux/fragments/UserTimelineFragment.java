package com.project.twitterredux.fragments;

import java.util.ArrayList;

import org.json.JSONArray;

import android.os.Bundle;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.project.twitterredux.MyTwitterApp;
import com.project.twitterredux.models.Tweet;

public class UserTimelineFragment extends TweetsListFragment {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		MyTwitterApp.getRestClient().getUserTimeline(
				new JsonHttpResponseHandler() {
					@Override
					public void onSuccess(JSONArray jsonTweets) {
						ArrayList<Tweet> tweets = Tweet.fromJson(jsonTweets);
						getAdapter().addAll(tweets);
					}
				});

	}
}
