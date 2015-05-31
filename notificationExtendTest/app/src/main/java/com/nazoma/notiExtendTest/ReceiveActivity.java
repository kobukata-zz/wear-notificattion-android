package com.nazoma.notiExtendTest;

import android.app.Activity;
import android.os.*;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;
import android.widget.TextView;
import android.support.v4.app.RemoteInput;

public class ReceiveActivity extends Activity {

		@Override
		protected void onCreate(Bundle savedInstanceState) {
				super.onCreate(savedInstanceState);
				setContentView(R.layout.activity_receive);
				
				// プッシュから呼び出されたintentを取得
				Intent intent = this.getIntent();
				
				String extraMessage = intent.getStringExtra(MainActivity.EXTRA_RESULT_KEY);
				
				// 渡されたテキストを取得
				TextView textView = (TextView) findViewById(R.id.receiveText);
				
				// 渡された音声認識結果を取得
				CharSequence voiceMessage = getVoiceMessageText(intent);
				
				String outputText = "ExtraMessage : " + extraMessage;
				outputText += "\n" + "RemoteMessage : " + voiceMessage;
				
				textView.setText(outputText);
		}
		
		private CharSequence getVoiceMessageText(Intent intent) {
				Bundle remoteInput = RemoteInput.getResultsFromIntent(intent);
				if (remoteInput != null) {
						return remoteInput.getCharSequence(MainActivity.VOICE_RESULT_KEY);
				}
				return null;
		}

		
		
		
		
}
