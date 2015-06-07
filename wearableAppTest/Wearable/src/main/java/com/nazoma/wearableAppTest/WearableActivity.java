package com.nazoma.wearableAppTest;

import android.app.*;
import android.os.*;
import android.widget.*;
import android.content.*;
import android.support.wearable.view.*;
import android.view.View;
import android.content.Intent;
import android.support.wearable.activity.ConfirmationActivity;
import android.speech.*;
import java.util.List;
import android.speech.RecognizerIntent;
import android.support.v4.view.GestureDetectorCompat;
import android.support.wearable.view.DismissOverlayView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.GestureDetector.*;
import android.support.wearable.view.DelayedConfirmationView;
import android.widget.Toast;

public class WearableActivity extends Activity {
		private TextView mTextView;
		private GestureDetectorCompat mGestureDetector;
		private DismissOverlayView mDismissOverlayView;
		private DelayedConfirmationView delayedConfirmationView;
		private static final int SPEECH_REQUEST_CODE = 0;
		
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
				
				// dismiss overlay view
				mDismissOverlayView = (DismissOverlayView) findViewById(R.id.dismiss_overlay);
				mGestureDetector = new GestureDetectorCompat(this, new LongPressListener());
				
				// delayed confirmation view
				delayedConfirmationView = (DelayedConfirmationView) findViewById(R.id.deleyed_confirmation);
				delayedConfirmationView.setTotalTimeMs(1 * 1000); // 1sec
    }
		/*
		 ConfirmationActivity 
		 - ユーザーがアクションを完了した後に、確認のアニメーションを表示するActivity.成功、失敗、phoneで開く.
		*/
		// onSuccessActivity
		public void onSuccessActivity(View view) {
				Intent intent = new Intent(this, ConfirmationActivity.class);
				intent.putExtra(ConfirmationActivity.EXTRA_ANIMATION_TYPE, ConfirmationActivity.SUCCESS_ANIMATION);
				intent.putExtra(ConfirmationActivity.EXTRA_MESSAGE, "Success Animation!");
				startActivity(intent);
		}
		
		// onFailureActivity
		public void onFailureActivity(View view) {
				Intent intent = new Intent(this, ConfirmationActivity.class);
				intent.putExtra(ConfirmationActivity.EXTRA_ANIMATION_TYPE, ConfirmationActivity.FAILURE_ANIMATION);
				intent.putExtra(ConfirmationActivity.EXTRA_MESSAGE, "Failure Animation!");
				startActivity(intent);
		}
		
		// open on phone activity
		public void onOpenOnPhoneActivity (View view) {
				Intent intent = new Intent(this, ConfirmationActivity.class);
				intent.putExtra(ConfirmationActivity.EXTRA_ANIMATION_TYPE, ConfirmationActivity.OPEN_ON_PHONE_ANIMATION);
				intent.putExtra(ConfirmationActivity.EXTRA_MESSAGE, "phone animation!");
				startActivity(intent);
		}
		
		// voice recognize activity
		public void onVoiceRecognize(View view) {
				Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
				intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
				startActivityForResult(intent, SPEECH_REQUEST_CODE);
		}

		@Override
		protected void onActivityResult(int requestCode, int resultCode, Intent data) {
				if (requestCode == SPEECH_REQUEST_CODE && resultCode == RESULT_OK) {
						List<String> results = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
						String spokenText = results.get(0);
						mTextView.setText(spokenText);
				}
				super.onActivityResult(requestCode, resultCode, data);
		}

		// touch event
		@Override
		public boolean dispatchTouchEvent(MotionEvent ev)	{
				return mGestureDetector.onTouchEvent(ev) || super.dispatchTouchEvent(ev);
		}

		private class LongPressListener extends GestureDetector.SimpleOnGestureListener {
				@Override
				public void onLongPress(MotionEvent e) {
						mDismissOverlayView.show();
						//super.onLongPress(e);
				}
		}
		
		public void onStartTimer(View v) {
				delayedConfirmationView.start();
				
				delayedConfirmationView.setListener(new DelayedConfirmationView.DelayedConfirmationListener() {
						@Override
						public void onTimerFinished(View v) {
							Intent intent = new Intent(getApplication(), ConfirmationActivity.class);
							intent.putExtra(ConfirmationActivity.EXTRA_ANIMATION_TYPE, ConfirmationActivity.SUCCESS_ANIMATION);
							intent.putExtra(ConfirmationActivity.EXTRA_MESSAGE, "Succeesed!!");
							startActivity(intent);
						}
						
						@Override
						public void onTimerSelected(View v) {
								Toast.makeText(getApplication(), "Timer selected!!", Toast.LENGTH_SHORT).show();
						}
				});
		}
		
		
		
		
}
