package com.project.twitterredux.fragments;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.activeandroid.util.Log;
import com.project.twitterredux.R;
import com.project.twitterredux.TweetsAdapter;
import com.project.twitterredux.models.Tweet;

public class TweetsListFragment extends Fragment {
	TweetsAdapter adapter;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup parent,
		      Bundle savedInstanceState) {
		
		return inflater.inflate(R.layout.fragment_tweets_list, parent, false);
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		ArrayList<Tweet> tweets = new ArrayList<Tweet>();
		ListView lvTweets = (ListView) getActivity().findViewById(R.id.lvTweets);
		
		adapter = new TweetsAdapter(getActivity(), tweets);
		lvTweets.setAdapter(adapter);
		Log.d("DEBUG", String.valueOf(tweets.size()));
	}
	
	public TweetsAdapter getAdapter() {
		return adapter;
	}

}
