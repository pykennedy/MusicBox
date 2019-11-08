package pyk.musicbox.service;

import android.os.Bundle;
import android.support.v4.media.MediaBrowserCompat;
import android.support.v4.media.MediaBrowserServiceCompat;
import android.support.v4.media.MediaMetadataCompat;
import android.support.v4.media.session.MediaSessionCompat;
import android.support.v4.media.session.PlaybackStateCompat;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import pyk.musicbox.model.entity.Track;

public class PlaybackService extends MediaBrowserServiceCompat {
  
  private MediaSessionCompat session;
  private PlaybackManager    playback;
  
  final MediaSessionCompat.Callback callback =
      new MediaSessionCompat.Callback() {
        @Override
        public void onPlayFromMediaId(String mediaId, Bundle extras) {
          session.setActive(true);
          MediaMetadataCompat metadata =
              PlaybackManager.toMetaData(PlaybackService.this, mediaId, PlaylistManager.getCurrent());
          session.setMetadata(metadata);
          playback.play(metadata);
        }
        
        @Override
        public void onPlay() {
          if (playback.getCurrentMediaId() != null) {
            onPlayFromMediaId(playback.getCurrentMediaId(), null);
          }
        }
        
        @Override
        public void onPause() {
          playback.pause();
        }
        
        @Override
        public void onStop() {
          stopSelf();
        }
        
        @Override
        public void onSkipToNext() {
          Log.e("asdf", "next");
          Track track = PlaylistManager.getNext();
          onPlayFromMediaId(Long.toString(track.getId()), null);
        }
        
        @Override
        public void onSkipToPrevious() {
          Log.e("asdf", "previous");
          Track track = PlaylistManager.getPrev();
          onPlayFromMediaId(Long.toString(track.getId()), null);
        }
      };
  
  @Override
  public void onCreate() {
    
    super.onCreate();
    session = new MediaSessionCompat(this, "PlaybackService");
    session.setCallback(callback);
    session.setFlags(
        MediaSessionCompat.FLAG_HANDLES_MEDIA_BUTTONS
        | MediaSessionCompat.FLAG_HANDLES_TRANSPORT_CONTROLS);
    setSessionToken(session.getSessionToken());
    
    final PlaybackNotificationManager mediaNotificationManager = new PlaybackNotificationManager(this);
  
    playback =
        new PlaybackManager(
            this,
            new PlaybackManager.Callback() {
              @Override
              public void onPlaybackStatusChanged(PlaybackStateCompat state) {
                session.setPlaybackState(state);
                
                mediaNotificationManager.update(playback.getCurrentMedia(), state, getSessionToken());
              }
            });
  }
  
  @Override
  public void onDestroy() {
    playback.stop();
    session.release();
  }
  
  @Override
  public BrowserRoot onGetRoot(String clientPackageName, int clientUid, Bundle rootHints) {
    return new BrowserRoot("root", null);
  }
  
  @Override
  public void onLoadChildren(
      final String parentMediaId, final Result<List<MediaBrowserCompat.MediaItem>> result) {
    result.sendResult(new ArrayList<MediaBrowserCompat.MediaItem>());
  }
}
