package com.nazoma.notiExtendTest;

import android.*;
import android.app.*;
import android.content.*;
import android.graphics.*;
import android.os.*;
import android.support.v4.app.*;
import android.text.*;
import android.text.style.*;
import android.view.*;
import android.support.v4.view.*;
import android.support.v4.app.RemoteInput;

public class MainActivity extends Activity {

		static final int BIG_TEXT_NOTIFICATION_ID = 1;
		static final int INBOX_NOIFICATION = 2;
		static final int GROUP_NOTI1 = 3;
		static final int GROUP_NOTI2 = 4;
		static final int GROUP_NOTI3 = 5;
		static final int SUMMARY_NOTI = 6;
		static final int ACTION_NOTI = 7;
		static final int PAGE_NOTIFICATION_ID = 10;
		static final int BACKKGROUND_NOTIFICATION_ID = 11;
		static final int CONTENT_ACTION_NOTIFICATION_ID = 12;
		static final int BASIC_RECEIVE_NOTIFICATION_ID = 15;
		static final int VOICE_RECEIVE_NOTIFICATION_ID = 16;
		static final int CHOICE_RECEIVE_NOTIFICATION_ID = 17;
		static final int BROADCAST_RECEIVER_NOTIFICATION_ID = 18;
		
		static final int BASIC_RECEIVE_ACTION1_ID = 0;
		static final int BASIC_RECEIVE_ACTION2_ID = 1;
		static final int BASIC_RECEIVE_ACTION3_ID = 2;
		
		final static String EXTRA_RESULT_KEY = "extra_result_key";
		final static String VOICE_RESULT_KEY = "voice_result_key";
		final static String GROUP_KEY = "group_key";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }

		// show custom notification
		public void showCustumTextNoti(View v) {
				SpannableStringBuilder title = new SpannableStringBuilder();
				title.append("Styled title");
				title.setSpan(new RelativeSizeSpan(1.25f),0,title.length(),0);  // font size
				title.setSpan(new StyleSpan(Typeface.BOLD_ITALIC),0,title.length(),0);  // bold and italic

				SpannableStringBuilder text = new SpannableStringBuilder();
				text.append("1234567890");
				text.setSpan(new RelativeSizeSpan(1.25f),0,text.length(),0);
				text.setSpan(new ForegroundColorSpan(Color.RED),0,3,0);
				text.setSpan(new ForegroundColorSpan(Color.GREEN),3,6,0);
				text.setSpan(new ForegroundColorSpan(Color.BLUE),6,9,0);

				Notification.BigTextStyle style = new Notification.BigTextStyle();
				style.setBigContentTitle(title);
				style.bigText(text);

				Notification noti = new Notification.Builder(this)
						.setContentTitle("Title")
						.setContentText("Text")
						.setSmallIcon(R.drawable.ic_launcher)
						.setStyle(style)
						.build();

				NotificationManager manager = (NotificationManager) getSystemService(Service.NOTIFICATION_SERVICE);
				manager.notify(BIG_TEXT_NOTIFICATION_ID, noti);
		}

		// show action notifiaction
		public void showActionNotification(View view) {
				Intent viewIntent = new Intent(this, MainActivity.class);
				PendingIntent viewPendingIntent = 
						PendingIntent.getActivity(this, ACTION_NOTI, viewIntent, 0);

				Notification noti = new NotificationCompat.Builder(this)
						.setContentTitle("action noti title")
						.setContentText("actoin noti text")
						.setSmallIcon(R.drawable.ic_launcher)
						.addAction(R.drawable.ic_launcher, "call", viewPendingIntent)
						.addAction(R.drawable.ic_launcher, "cut", viewPendingIntent)
						.addAction(R.drawable.ic_launcher, "accept", viewPendingIntent)
						.setContentIntent(viewPendingIntent)
						.setAutoCancel(true)
						.build();

				NotificationManagerCompat.from(this).notify(ACTION_NOTI, noti);
		}

		// show inbox notification
		public void showInboxNotification(View v) {
				Notification.InboxStyle style = new Notification.InboxStyle();
				style.addLine("Inbox Style Text Example Line 1");
				style.addLine("Inbox Style Text Example Line 2");
				style.addLine("Inbox Style Text Example Line 3");
				style.setBigContentTitle("Inbox Title");
				style.setSummaryText("Inbox Text");

				Notification noti = new Notification.Builder(this)
						.setContentTitle("Title")
						.setContentText("text")
						.setSmallIcon(R.drawable.ic_launcher)
						.setStyle(style)
						.build();

				NotificationManager manager = (NotificationManager) getSystemService(Service.NOTIFICATION_SERVICE);
				manager.notify(INBOX_NOIFICATION, noti);
		}

		// show noti group1
		public void showGroup1(View v) {
				Notification noti = new Notification.Builder(this)
						.setContentTitle("group title 1")
						.setContentText("group text 1")
						.setSmallIcon(R.drawable.ic_launcher)
						//.setGroup(GROUP_KEY)
						//.setSortKey("1")
						.build();

				NotificationManager manager = (NotificationManager) getSystemService(Service.NOTIFICATION_SERVICE);
				manager.notify(GROUP_NOTI1, noti);
		}

		public void showGroup2(View v) {
				Notification noti = new Notification.Builder(this)
						.setContentTitle("group title 2")
						.setContentText("group text 2")
						.setSmallIcon(R.drawable.ic_launcher)
						.setGroup(GROUP_KEY)
						.setSortKey("2")
						.build();

				NotificationManager manager = (NotificationManager) getSystemService(Service.NOTIFICATION_SERVICE);
				manager.notify(GROUP_NOTI2, noti);
		}

		public void showGroup3(View v) {
				Notification noti = new Notification.Builder(this)
						.setContentTitle("group title 3")
						.setContentText("group text 3")
						.setSmallIcon(R.drawable.ic_launcher)
						.setGroup(GROUP_KEY)
						.setSortKey("3")
						.build();

				NotificationManager manager = (NotificationManager) getSystemService(Service.NOTIFICATION_SERVICE);
				manager.notify(GROUP_NOTI3, noti);				
		}

		public void showSummaryNoti(View v) {
		}
		
		public void showPageNotification(View view) {
				
				// make a option for Second page
				NotificationCompat.WearableExtender secondWearableExtender = new NotificationCompat.WearableExtender()
					.setContentIcon(R.drawable.ic_launcher)
					.setContentIconGravity(Gravity.END);
				
				// make a Second page
				Notification secondPage = new NotificationCompat.Builder(this)
					.setContentTitle("second page title")
					.setContentText("second page text")
					.extend(secondWearableExtender)
					.build();
			
				// make a third page
				Notification thirdPage = new NotificationCompat.Builder(this)
					.setContentTitle("third page title")
					.setContentText("third page text")
					.extend(new NotificationCompat.WearableExtender()
										.setContentIcon(R.drawable.ic_launcher)
										.setContentIconGravity(Gravity.END))
					.build();
					
				// make a option for First page
				NotificationCompat.WearableExtender wearableOptions = new NotificationCompat.WearableExtender()
					.setContentIcon(R.drawable.ic_launcher)
					.setContentIconGravity(Gravity.END)
					.addPage(secondPage)
					.addPage(thirdPage);
					
				Notification noti = new NotificationCompat.Builder(this)				
					.setContentTitle("first page title")
					.setContentText("first page text")
					.setSmallIcon(R.drawable.ic_launcher)
					.extend(wearableOptions)
					.build();
					
				NotificationManagerCompat.from(this).notify(PAGE_NOTIFICATION_ID, noti);
		}

		public void showBackgroundNotification(View view){
				// import backgrond image
				Bitmap bg1 = BitmapFactory.decodeResource(getResources(), R.drawable.image1);
				Bitmap bg2 = BitmapFactory.decodeResource(getResources(), R.drawable.image4);
				Bitmap bg3 = BitmapFactory.decodeResource(getResources(), R.drawable.image5);

				// make a 2nd page
				Notification secondPage = new NotificationCompat.Builder(this)
					.setContentTitle("background2 title")
					.setContentText("setHintShowBackgroundOnly(false)")
					.extend(new NotificationCompat.WearableExtender()
										.setContentIcon(R.drawable.ic_launcher)
										.setContentIconGravity(Gravity.START)
										.setBackground(bg2)
										.setHintShowBackgroundOnly(false)
										.setGravity(Gravity.CENTER_VERTICAL))
										
					.build();
				
				// make a 3rd page
				Notification thirdPage = new NotificationCompat.Builder(this)
					.setContentTitle("background3 title")
					.setContentText("setHintShowBackgroundOnly(true)")
					.extend(new NotificationCompat.WearableExtender()
										.setContentIcon(R.drawable.ic_launcher)
										.setContentIconGravity(Gravity.END)
										.setBackground(bg3)
										.setHintShowBackgroundOnly(true)
										.setGravity(Gravity.BOTTOM))
					.build();
					
				// make a 1st page
				Notification noti = new NotificationCompat.Builder(this)
					.setContentTitle("background1 title")
					.setContentText("background1 text")
					.setSmallIcon(R.drawable.ic_launcher)
					.extend(new NotificationCompat.WearableExtender()
										.setGravity(Gravity.TOP)
										.setBackground(bg1)
										.addPage(secondPage)
										.addPage(thirdPage))
					.build();
					
				NotificationManagerCompat.from(this).notify(BACKKGROUND_NOTIFICATION_ID, noti);
		}
		
		public void showContentActionNotification(View view) {
				PendingIntent viewPendingIntent = PendingIntent.getActivity(this, ACTION_NOTI, new Intent(this, MainActivity.class), 0);
				
				// first action
				NotificationCompat.Action action1 = 
					new NotificationCompat.Action.Builder(R.drawable.ic_launcher, "Action1", viewPendingIntent)
					.build();
				
				// second action
				NotificationCompat.Action action2 = 
					new NotificationCompat.Action.Builder(R.drawable.ic_launcher, "Action2", viewPendingIntent)
					.build();
				
				// make a 2nd page
				Notification secondPage = new NotificationCompat.Builder(this)
					.setContentTitle("action 1 title")
					.setContentText("action 1 text")
					.extend(new NotificationCompat.WearableExtender().setContentAction(0))
					.build();
				
				// make a 3rd page
				Notification thirdPage = new NotificationCompat.Builder(this)
					.setContentTitle("action 2 title")
					.setContentText("action 2 text")
					.extend(new NotificationCompat.WearableExtender().setContentAction(1))
					.build();
				
				// make options
				NotificationCompat.WearableExtender options = new NotificationCompat.WearableExtender()
					.setHintHideIcon(true)
					.addAction(action1)
					.addAction(action2)
					.addAction(action2)
					.addPage(secondPage)
					.addPage(thirdPage)
					.setContentAction(2);
				
				Notification noti = new NotificationCompat.Builder(this)
					.setContentTitle("content action title")
					.setContentText("content action text")
					.setSmallIcon(R.drawable.ic_launcher)
					.extend(options)
					.build();
					
				NotificationManagerCompat.from(this).notify(CONTENT_ACTION_NOTIFICATION_ID, noti);
		}
		
		public void showBasicReceiveNotification (View v) {
				Intent receiveIntent = new Intent(this, ReceiveActivity.class);
				
				// action 1 result text
				receiveIntent.putExtra(EXTRA_RESULT_KEY, "Call Action Click!");
				
				// pending intent for action 1
				PendingIntent viewPendingIntent1 = 
					PendingIntent.getActivity(this, BASIC_RECEIVE_ACTION1_ID, receiveIntent, PendingIntent.FLAG_UPDATE_CURRENT);
				
				// action 2 result text
				receiveIntent.putExtra(EXTRA_RESULT_KEY, "Cut Action Click!");
				
				// pending intent for action 2
				PendingIntent viewPendingIntent2 = 
					PendingIntent.getActivity(this, BASIC_RECEIVE_ACTION2_ID, receiveIntent, PendingIntent.FLAG_UPDATE_CURRENT);
					
				// action 3 result text
				receiveIntent.putExtra(EXTRA_RESULT_KEY, "Accept Action Click!");
				
				// pending intent for action 3
				PendingIntent viewPendingIntent3 = 
						PendingIntent.getActivity(this, BASIC_RECEIVE_ACTION3_ID, receiveIntent, PendingIntent.FLAG_UPDATE_CURRENT);
				
				// make a pushalarm and add actions
				Notification noti = new NotificationCompat.Builder(this)
					.setContentTitle("basic receive title")
					.setContentText("basic receive text")
					.setSmallIcon(R.drawable.ic_launcher)
					.addAction(R.drawable.ic_launcher, "Call", viewPendingIntent1)
					.addAction(R.drawable.ic_launcher, "Cut", viewPendingIntent2)
					.addAction(R.drawable.ic_launcher, "Accept", viewPendingIntent3)
					.setAutoCancel(true)
					.build();
					
				NotificationManagerCompat.from(this).notify(BASIC_RECEIVE_NOTIFICATION_ID, noti);
		}
		
		public void showVoiceReceiveNotification(View v) {
				Intent remoteIntent = new Intent(this, ReceiveActivity.class);
				remoteIntent.putExtra(EXTRA_RESULT_KEY, "Remote input Action!");
				PendingIntent replyPendingIntent = 
					PendingIntent.getActivity(this, 0, remoteIntent, PendingIntent.FLAG_UPDATE_CURRENT);
					
				RemoteInput remoteInput = new RemoteInput.Builder(VOICE_RESULT_KEY)
					.setLabel("reply label")
					.build();
				NotificationCompat.Action action = 
					new NotificationCompat.Action.Builder(R.drawable.ic_launcher, 
						"reply", replyPendingIntent)
						.addRemoteInput(remoteInput)
						.build();
				
				Notification noti = new NotificationCompat.Builder(this)
					.setSmallIcon(R.drawable.ic_launcher)
					.setContentTitle("title")
					.setContentText("text")
					.extend(new NotificationCompat.WearableExtender().addAction(action))
					.build();
					
				NotificationManagerCompat.from(this).notify(VOICE_RECEIVE_NOTIFICATION_ID, noti);
		}
		
		public void showChoiceReceiveNotification(View v) {
				Intent remoteIntent = new Intent(this, ReceiveActivity.class);
				remoteIntent.putExtra(EXTRA_RESULT_KEY, "Choice Remote Action!");
				PendingIntent replyPendigIntent = 
					PendingIntent.getActivity(this, 0, remoteIntent, PendingIntent.FLAG_UPDATE_CURRENT);
				
				RemoteInput remoteInput = new RemoteInput.Builder(VOICE_RESULT_KEY)
					.setLabel("Remote Label")
					.setChoices(getResources().getStringArray(R.array.remote_choices))
					.build();
				
				NotificationCompat.Action action = new NotificationCompat.Action.Builder(
					R.drawable.ic_launcher, 
					"Choice Remote Action!!",
					replyPendigIntent)
					.addRemoteInput(remoteInput)
					.build();
				
				Notification noti = new NotificationCompat.Builder(this)
					.setSmallIcon(R.drawable.ic_launcher)
					.setContentText("text")
					.setContentTitle("title")
					.extend(new NotificationCompat.WearableExtender().addAction(action))
					.build();
					
				NotificationManagerCompat.from(this).notify(CHOICE_RECEIVE_NOTIFICATION_ID, noti);
		}
		
		public void showBroadcastReceiveNotification(View v) {
				Intent intentBroadcast = new Intent(this, MainReceiver.class);
				intentBroadcast.putExtra(EXTRA_RESULT_KEY, "Broadcast Action!!");
				PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, intentBroadcast, PendingIntent.FLAG_UPDATE_CURRENT);
				
				RemoteInput remoteInput = new RemoteInput.Builder(VOICE_RESULT_KEY)
					.setLabel("remote label")
					.setChoices(getResources().getStringArray(R.array.remote_choices))
					.build();
				NotificationCompat.Action action = new NotificationCompat.Action.Builder(
					R.drawable.ic_launcher, "Broadcast Action", pendingIntent)
					.addRemoteInput(remoteInput)
					.build();
					
				Notification noti = new NotificationCompat.Builder(this)
					.setSmallIcon(R.drawable.ic_launcher)
					.setContentText("text")
					.setContentTitle("title")
					.extend(new NotificationCompat.WearableExtender().addAction(action))
					.build();
					
				NotificationManagerCompat.from(this).notify(BROADCAST_RECEIVER_NOTIFICATION_ID, noti);
		}
		
		
		
		
}
