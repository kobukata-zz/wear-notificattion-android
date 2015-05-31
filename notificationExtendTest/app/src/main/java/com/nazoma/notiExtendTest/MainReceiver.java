package com.nazoma.notiExtendTest;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.RemoteInput;
import android.widget.Toast;

public class MainReceiver extends BroadcastReceiver {
		public MainReceiver()
		{}

		@Override
		public void onReceive(Context context, Intent intent) {
				String extraMessage = intent.getStringExtra(MainActivity.EXTRA_RESULT_KEY);
				CharSequence remoteMessage = getMessageText(intent);
				
				String outputText = "ExtraMessage : " + extraMessage + "\n"
														+ "remote message : " + remoteMessage;
				Toast.makeText(context, outputText, Toast.LENGTH_LONG).show();
		}
		
		private CharSequence getMessageText(Intent intent) {
				Bundle remoteInput = RemoteInput.getResultsFromIntent(intent);
				
				if (remoteInput != null) {
						return remoteInput.getCharSequence(MainActivity.VOICE_RESULT_KEY);
				}
				return null;
		}
	
}
