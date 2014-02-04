package com.project.twitterredux;

import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.activeandroid.util.Log;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.project.twitterredux.models.Tweet;

public class TweetsAdapter extends ArrayAdapter<Tweet> {

	public TweetsAdapter (Context context, List<Tweet> tweets) {
		super(context, 0, tweets);
	}
	
	@Override
    public View getView(int position, View view, ViewGroup parent) {
		if (view == null) {
			 LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			 view = inflater.inflate(R.layout.tweet_item, null);
		}
		
		Tweet tweet = getItem(position);
		ImageView imageView = (ImageView) view.findViewById(R.id.ivProfile);
		ImageLoader.getInstance().displayImage(tweet.getUser().getProfileImageUrl(), imageView);
		final String screenName = tweet.getUser().getScreenName();
		imageView.setTag(screenName);
		imageView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View view) {
				Intent i = new Intent(getContext(), ProfileActivity.class);
				i.putExtra("screen_name", screenName);
				Log.d("DEBUG", screenName);
				getContext().startActivity(i);
			}
		});
		
		TextView nameView = (TextView)view.findViewById(R.id.tvName);
		String formatterName = "<b>" + tweet.getUser().getName() + "</b>"
				+ " <small><font color='#777777'>"
				+ tweet.getUser().getScreenName() + "</font></small>";
		
		nameView.setText(Html.fromHtml(formatterName));
		
		TextView bodyView = (TextView)view.findViewById(R.id.tvBody);
		bodyView.setText(Html.fromHtml(tweet.getBody()));
		
		return view;
	}
}
