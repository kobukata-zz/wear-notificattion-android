package com.nazoma.wearableAppTest;

import android.app.*;
import android.os.*;
import android.widget.*;
import android.content.*;
import android.support.wearable.view.*;

public class WearableActivity extends Activity {
		private TextView mTextView;
		
    @Override
    protected void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
				setContentView(R.layout.activity_wearable);
				
				// make a WatchViewStub Instance
				final WatchViewStub stub = (WatchViewStub) findViewById(R.id.watch_view_stub);
				stub.setOnLayoutInflatedListener(new WatchViewStub.OnLayoutInflatedListener(){
						@Override
						public void onLayoutInflated(WatchViewStub stub) {
								mTextView = (TextView) stub.findViewById(R.id.text);
						}
				});
    }
}
