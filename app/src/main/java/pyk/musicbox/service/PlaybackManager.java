package pyk.musicbox.service;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.PowerManager;
import android.os.SystemClock;
import android.provider.MediaStore;
import android.support.v4.media.MediaMetadataCompat;
import android.support.v4.media.session.PlaybackStateCompat;

import java.io.IOException;

import pyk.musicbox.model.entity.Track;

public class PlaybackManager
    implements AudioManager.OnAudioFocusChangeListener, MediaPlayer.OnCompletionListener {
  
  private final    Context             context;
  private          int                 state;
  private          boolean             playOnFocusGain;
  private volatile MediaMetadataCompat currentMedia;
  
  private MediaPlayer mediaPlayer;
  
  private final Callback     callback;
  private final AudioManager audioManager;
  
  public PlaybackManager(Context context, Callback callback) {
    this.context = context;
    this.audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
    this.callback = callback;
  }
  
  public boolean isPlaying() {
    return playOnFocusGain || (mediaPlayer != null && mediaPlayer.isPlaying());
  }
  
  public MediaMetadataCompat getCurrentMedia() {
    return currentMedia;
  }
  
  public String getCurrentMediaId() {
    return currentMedia == null ? null : currentMedia.getString("id");
  }
  
  public int getCurrentStreamPosition() {
    return mediaPlayer != null ? mediaPlayer.getCurrentPosition() : 0;
  }
  
  public void play(MediaMetadataCompat metadata) {
    String  id           = metadata.getString("id");
    boolean mediaChanged = (currentMedia == null || !getCurrentMediaId().equals(id));
    
    if (mediaPlayer == null) {
      mediaPlayer = new MediaPlayer();
      mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
      mediaPlayer.setWakeMode(
          context.getApplicationContext(), PowerManager.PARTIAL_WAKE_LOCK);
      mediaPlayer.setOnCompletionListener(this);
    } else {
      if (mediaChanged) {
        mediaPlayer.reset();
      }
    }
    
    if (mediaChanged) {
      currentMedia = metadata;
      try {
        mediaPlayer.setDataSource(
            context.getApplicationContext(),
            Uri.parse(getURI(id)));
        mediaPlayer.prepare();
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
    }
    
    if (tryToGetAudioFocus()) {
      playOnFocusGain = false;
      mediaPlayer.start();
      state = PlaybackStateCompat.STATE_PLAYING;
      updatePlaybackState();
    } else {
      playOnFocusGain = true;
    }
  }
  
  public void pause() {
    if (isPlaying()) {
      mediaPlayer.pause();
      audioManager.abandonAudioFocus(this);
    }
    state = PlaybackStateCompat.STATE_PAUSED;
    updatePlaybackState();
  }
  
  public void stop() {
    state = PlaybackStateCompat.STATE_STOPPED;
    updatePlaybackState();
    audioManager.abandonAudioFocus(this);
    releaseMediaPlayer();
  }
  
  private boolean tryToGetAudioFocus() {
    int result =
        audioManager.requestAudioFocus(
            this, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN);
    return result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED;
  }
  
  @Override
  public void onAudioFocusChange(int focusChange) {
    boolean gotFullFocus = false;
    boolean canDuck      = false;
    if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
      gotFullFocus = true;
      
    } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS
               || focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT
               || focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
      canDuck = focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK;
    }
    
    if (gotFullFocus || canDuck) {
      if (mediaPlayer != null) {
        if (playOnFocusGain) {
          playOnFocusGain = false;
          mediaPlayer.start();
          state = PlaybackStateCompat.STATE_PLAYING;
          updatePlaybackState();
        }
        float volume = canDuck ? 0.2f : 1.0f;
        mediaPlayer.setVolume(volume, volume);
      }
    } else if (state == PlaybackStateCompat.STATE_PLAYING) {
      mediaPlayer.pause();
      state = PlaybackStateCompat.STATE_PAUSED;
      updatePlaybackState();
    }
  }
  
  @Override
  public void onCompletion(MediaPlayer player) {
    stop();
  }
  
  private void releaseMediaPlayer() {
    if (mediaPlayer != null) {
      mediaPlayer.reset();
      mediaPlayer.release();
      mediaPlayer = null;
    }
  }
  
  @PlaybackStateCompat.Actions
  private long getAvailableActions() {
    long actions =
        PlaybackStateCompat.ACTION_PLAY
        | PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID
        | PlaybackStateCompat.ACTION_PLAY_FROM_SEARCH
        | PlaybackStateCompat.ACTION_SKIP_TO_NEXT
        | PlaybackStateCompat.ACTION_SKIP_TO_PREVIOUS;
    if (isPlaying()) {
      actions |= PlaybackStateCompat.ACTION_PAUSE;
    }
    return actions;
  }
  
  private void updatePlaybackState() {
    if (callback == null) {
      return;
    }
    PlaybackStateCompat.Builder stateBuilder =
        new PlaybackStateCompat.Builder().setActions(getAvailableActions());
    
    stateBuilder.setState(
        state, getCurrentStreamPosition(), 1.0f, SystemClock.elapsedRealtime());
    callback.onPlaybackStatusChanged(stateBuilder.build());
  }
  
  private String getURI(String id) {
    return MediaStore.Audio.Media.EXTERNAL_CONTENT_URI + "/" + id;
  }
  
  public static MediaMetadataCompat toMetaData(Context context, Track track) {
    return new MediaMetadataCompat.Builder()
        .putString("id", Long.toString(track.getId()))
        .putString("name", track.getName())
        .putString("album", track.getAlbum())
        .putString("artist", track.getArtist())
        .putString("duration", track.getDuration())
        .build();
  }
  
  public static MediaMetadataCompat toMetaData(Context context, String id) {
    return new MediaMetadataCompat.Builder()
        .putString("id", id)
        .putString("name", null)
        .putString("album", null)
        .putString("artist", null)
        .putString("duration", null)
        .build();
  }
  
  public interface Callback {
    void onPlaybackStatusChanged(PlaybackStateCompat state);
  }
}