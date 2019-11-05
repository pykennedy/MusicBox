package pyk.musicbox.service;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.media.MediaDescriptionCompat;
import android.support.v4.media.MediaMetadataCompat;
import android.support.v4.media.session.MediaSessionCompat;
import android.support.v4.media.session.PlaybackStateCompat;

import pyk.musicbox.R;
import pyk.musicbox.view.activity.MainActivity;

public class PlaybackNotificationManager extends BroadcastReceiver {
  private static final int NOTIFICATION_ID = 412;
  private static final int REQUEST_CODE = 100;
  
  private static final String CHANNEL_ID = "media_playback_channel";
  private static final String CHANNEL_NAME = "Media playback";
  private static final String CHANNEL_DESCRIPTION = "Media playback controls";
  
  private static final String ACTION_PAUSE = "pyk.musicbox.pause";
  private static final String ACTION_PLAY = "pyk.musicbox.play";
  private static final String ACTION_NEXT = "pyk.musicbox.next";
  private static final String ACTION_PREV = "pyk.musicbox.prev";
  
  private final Context context;
  private final PlaybackService service;
  private final NotificationManager notificationManager;
  
  private final NotificationCompat.Action playAction;
  private final NotificationCompat.Action pauseAction;
  private final NotificationCompat.Action nextAction;
  private final NotificationCompat.Action prevAction;
  
  private boolean started;
  
  public PlaybackNotificationManager(PlaybackService service) {
    this.service = service;
    this.context = service.getBaseContext();
    
    String pkg = service.getPackageName();
    PendingIntent playIntent =
        PendingIntent.getBroadcast(
            service,
            REQUEST_CODE,
            new Intent(ACTION_PLAY).setPackage(pkg),
            PendingIntent.FLAG_CANCEL_CURRENT);
    PendingIntent pauseIntent =
        PendingIntent.getBroadcast(
            service,
            REQUEST_CODE,
            new Intent(ACTION_PAUSE).setPackage(pkg),
            PendingIntent.FLAG_CANCEL_CURRENT);
    PendingIntent nextIntent =
        PendingIntent.getBroadcast(
            service,
            REQUEST_CODE,
            new Intent(ACTION_NEXT).setPackage(pkg),
            PendingIntent.FLAG_CANCEL_CURRENT);
    PendingIntent prevIntent =
        PendingIntent.getBroadcast(
            service,
            REQUEST_CODE,
            new Intent(ACTION_PREV).setPackage(pkg),
            PendingIntent.FLAG_CANCEL_CURRENT);
  
    playAction =
        new NotificationCompat.Action(
            R.drawable.ic_play_arrow_black_24dp,
            "play",
            playIntent);
    pauseAction =
        new NotificationCompat.Action(
            R.drawable.ic_pause_black_24dp,
            "pause",
            pauseIntent);
    nextAction =
        new NotificationCompat.Action(
            R.drawable.ic_fast_forward_black_24dp,
            "next",
            nextIntent);
    prevAction =
        new NotificationCompat.Action(
            R.drawable.ic_fast_rewind_black_24dp,
            "prev",
            prevIntent);
  
    IntentFilter filter = new IntentFilter();
    filter.addAction(ACTION_NEXT);
    filter.addAction(ACTION_PAUSE);
    filter.addAction(ACTION_PLAY);
    filter.addAction(ACTION_PREV);
    
    service.registerReceiver(this, filter);
    
    notificationManager = (NotificationManager) service.getSystemService(Context.NOTIFICATION_SERVICE);
    
    notificationManager.cancelAll();
  
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
      createNotificationChannel();
    }
  }
  
  @Override public void onReceive(Context context, Intent intent) {
    final String action = intent.getAction();
    switch (action) {
      case ACTION_PAUSE:
        service.callback.onPause();
        break;
      case ACTION_PLAY:
        service.callback.onPlay();
        break;
      case ACTION_NEXT:
        service.callback.onSkipToNext();
        break;
      case ACTION_PREV:
        service.callback.onSkipToPrevious();
        break;
    }
  }
  
  @RequiresApi(api = Build.VERSION_CODES.O)
  private void createNotificationChannel() {
    NotificationChannel mChannel = new NotificationChannel(
        CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_LOW);
    mChannel.setDescription(CHANNEL_DESCRIPTION);
    mChannel.setShowBadge(false);
    mChannel.setLockscreenVisibility(Notification.VISIBILITY_PUBLIC);
    notificationManager.createNotificationChannel(mChannel);
  }
  
  public void update(
      MediaMetadataCompat metadata,
      PlaybackStateCompat state,
      MediaSessionCompat.Token token) {
    if (state == null
        || state.getState() == PlaybackStateCompat.STATE_STOPPED
        || state.getState() == PlaybackStateCompat.STATE_NONE) {
      service.stopForeground(true);
      try {
        service.unregisterReceiver(this);
      } catch (IllegalArgumentException ex) {}
      service.stopSelf();
      return;
    }
    if (metadata == null) {
      return;
    }
    boolean isPlaying = state.getState() == PlaybackStateCompat.STATE_PLAYING;
    NotificationCompat.Builder notificationBuilder =
        new NotificationCompat.Builder(service, CHANNEL_ID);
    MediaDescriptionCompat description = metadata.getDescription();

    notificationBuilder
        .setStyle(new android.support.v4.media.app.NotificationCompat.MediaStyle()
                      .setMediaSession(token))
        .setSmallIcon(R.drawable.ic_launcher_foreground)
        .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
        .setContentIntent(createContentIntent())
        .setContentTitle(description.getTitle())
        .setContentText(description.getSubtitle())
        .setOngoing(isPlaying)
        .setWhen(isPlaying ? System.currentTimeMillis() - state.getPosition() : 0)
        .setShowWhen(isPlaying)
        .setUsesChronometer(isPlaying);
    
    if ((state.getActions() & PlaybackStateCompat.ACTION_SKIP_TO_PREVIOUS) != 0) {
      notificationBuilder.addAction(prevAction);
    }
    
    notificationBuilder.addAction(isPlaying ? pauseAction : playAction);
    
    if ((state.getActions() & PlaybackStateCompat.ACTION_SKIP_TO_NEXT) != 0) {
      notificationBuilder.addAction(nextAction);
    }
    
    Notification notification = notificationBuilder.build();
    
    if (isPlaying && !started) {
      Intent intent = new Intent(context, PlaybackService.class);
      ContextCompat.startForegroundService(context, intent);
      service.startForeground(NOTIFICATION_ID, notification);
      started = true;
    } else {
      if (!isPlaying) {
        service.stopForeground(false);
        started = false;
      }
      notificationManager.notify(NOTIFICATION_ID, notification);
    }
  }
  
  private PendingIntent createContentIntent() {
    Intent openUI = new Intent(service, MainActivity.class);
    openUI.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
    return PendingIntent.getActivity(
        service, REQUEST_CODE, openUI, PendingIntent.FLAG_CANCEL_CURRENT);
  }
}
