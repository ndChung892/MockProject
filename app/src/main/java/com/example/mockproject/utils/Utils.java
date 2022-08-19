package com.example.mockproject.utils;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;

import androidx.core.content.ContextCompat;

import com.example.mockproject.R;
import com.example.mockproject.view.main.fragmentelement.song.element.albums.AlbumsModel;
import com.example.mockproject.view.main.fragmentelement.song.element.allsong.Song;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Utils {
    List<Song> songList = new ArrayList<>();
    List<AlbumsModel> albumsModelList = new ArrayList<>();
    public static final String TAG= "Utils";

    public List<Song> getAudioList(Activity activity) {
//        ListView audioView = (ListView) findViewById(R.id.songView);

        ArrayList<String> audioList = new ArrayList<>();

        String[] proj = { MediaStore.Audio.Media._ID,
                MediaStore.Audio.Media.TITLE,
                MediaStore.Audio.Media.ARTIST,
                MediaStore.Audio.Media.DURATION,
                MediaStore.Audio.Media.GENRE,
                MediaStore.Audio.Media.IS_MUSIC,
                MediaStore.Audio.Media.DATA,
                MediaStore.Audio.Media.ALBUM
//                MediaStore.Audio.Albums.ALBUM_ID,
//                MediaStore.Audio.Albums.ARTIST,
//                MediaStore.Audio.Albums.NUMBER_OF_SONGS

        };// Can include more data for more details and check it.


        String selection =  MediaStore.Audio.Media.DURATION + ">= 60000";
        String DEFAULT_SELECTION = MediaStore.Audio.Media.IS_MUSIC + " != 0 and " +
                MediaStore.Audio.Media.DATA + " NOT LIKE '%/WhatsApp/%'";
        if(ContextCompat.checkSelfPermission(activity, Manifest.permission.READ_EXTERNAL_STORAGE)
                == PackageManager.PERMISSION_GRANTED) {
            Cursor audioCursor = activity
                    .getContentResolver()
                    .query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, proj, selection, null, null);
            if(audioCursor != null){
                if(audioCursor.moveToFirst()){
                    do{
                        String songs = audioCursor.getString(1);
                        String singer = audioCursor.getString(2);
                        String duration = audioCursor.getString(3);

                        //get cover img
                        String img = audioCursor.getString(6);
                        String album = audioCursor.getString(7);

                        Log.d(TAG, "getAudioList: "+ album);

                        MediaMetadataRetriever mmr = new MediaMetadataRetriever();
                        Bitmap art;
                        BitmapFactory.Options bfo=new BitmapFactory.Options();

                        mmr.setDataSource(img);
                        byte[] rawArt = mmr.getEmbeddedPicture();

                        if (null != rawArt){
                            art = BitmapFactory.decodeByteArray(rawArt, 0, rawArt.length, bfo);
                            Song song = new Song(songs,singer, duration, art, img, album,false);
                            songList.add(song);
                        }
                    }while(audioCursor.moveToNext());
                }
            }
            audioCursor.close();
        }
        Log.i(TAG, "getAudioList: "+ songList);
        return songList;
    }

    public static String formatDuration(Context context, int duration){
        long minutes = TimeUnit.MINUTES.convert(duration, TimeUnit.MILLISECONDS);
        long seconds = TimeUnit.SECONDS.convert(duration, TimeUnit.MILLISECONDS)- minutes * TimeUnit.SECONDS.convert(1, TimeUnit.MINUTES);
        return String.format(context.getString(R.string.time), minutes, seconds);
    }
}
