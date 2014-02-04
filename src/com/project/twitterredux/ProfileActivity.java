package com.project.twitterredux;

import org.json.JSONObject;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.project.twitterredux.models.User;

public class ProfileActivity extends FragmentActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_profile);
		
		String screen_name = getIntent().getStringExtra("screen_name");
		
		if (screen_name == null) {
			loadProfileInfo();
		} else {
			loadFriendProfileInfo(screen_name);
		}
		
	}

	private void loadProfileInfo() {
		MyTwitterApp.getRestClient().getMyInfo(new JsonHttpResponseHandler() {
			@Override
			public void onSuccess(JSONObject json) {
				User user = User.fromJson(json);
				getActionBar().setTitle("@" + user.getScreenName());
				populateUserProfile(user);
			}

		});

	}
	
	private void loadFriendProfileInfo(final String username) {
		MyTwitterApp.getRestClient().getFriendInfo(username, new JsonHttpResponseHandler() {
			@Override
			public void onSuccess(JSONObject json) {
				User user = User.fromJson(json);
				getActionBar().setTitle("@" + user.getScreenName());
				populateUserProfile(user);
			}

		});
		View view = findViewById(R.id.fragment_user_timeline);
		view.setVisibility(View.GONE);
	}
	
	private void populateUserProfile(User user) {
		TextView tvName = (TextView) findViewById(R.id.tvName);
		TextView tvTagLine = (TextView) findViewById(R.id.tvTagLine);
		TextView tvFollowers = (TextView) findViewById(R.id.tvFollowers);
		TextView tvFollowing = (TextView) findViewById(R.id.tvFollowing);
		ImageView ivProfileImage = (ImageView) findViewById(R.id.ivProfileImage);
		
		tvName.setText(user.getName());
		tvTagLine.setText(user.getTagLine());
		tvFollowers.setText(user.getFollowersCount() + " followers");
		tvFollowing.setText(user.getFriendsCount() + " following");
		ImageLoader.getInstance().displayImage(user.getProfileImageUrl(), ivProfileImage);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.profile, menu);
		return true;
	}

}
