package com.project.twitterredux;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.ActionBar.TabListener;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.Menu;
import android.view.MenuItem;

import com.project.twitterredux.fragments.HomeTimelineFragment;
import com.project.twitterredux.fragments.MentionsFragment;
import com.project.twitterredux.fragments.TweetsListFragment;

public class TimelineActivity extends FragmentActivity implements TabListener {
	
	TweetsListFragment fragmentTweets;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_timeline);
		setUpNavigationTabs();
	}
	
	private void setUpNavigationTabs() {
		ActionBar actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		actionBar.setDisplayShowTitleEnabled(true);
		Tab tabHome = actionBar.newTab().setText("Home")
				.setTag("HomeTimeLineFragment").setIcon(R.drawable.ic_home)
				.setTabListener(this);

		Tab mentionsHome = actionBar.newTab().setText("Mentions")
				.setTag("MentionsTimeLineFragment")
				.setIcon(R.drawable.ic_mentions).setTabListener(this);

		actionBar.addTab(tabHome);
		actionBar.addTab(mentionsHome);
		actionBar.selectTab(tabHome);

	}

	public void onClickCompose(MenuItem menu) {
		Intent intent = new Intent(this, ComposeActivity.class);
		startActivity(intent);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.timeline, menu);
		return true;
	}

	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {
		
	}

	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		FragmentManager fragmentManager = getSupportFragmentManager();
		android.support.v4.app.FragmentTransaction supportFT = fragmentManager.beginTransaction();
		if (tab.getTag() == "HomeTimeLineFragment") {
			supportFT.replace(R.id.frame_container, new HomeTimelineFragment());
		} else {
			supportFT.replace(R.id.frame_container, new MentionsFragment());
		}
		supportFT.commit();
	}

	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
		
	}

}
