package com.example.mockproject.utils;

import android.app.Activity;
import android.database.Cursor;
import android.provider.MediaStore;
import android.util.Log;

import com.example.mockproject.view.main.fragmentelement.song.Song;

import java.util.ArrayList;
import java.util.List;

public class Utils {
    List<Song> songList = new ArrayList<>();
    public static final String TAG= "Utils";

    public List<Song> getAudioList(Activity activity) {
//        ListView audioView = (ListView) findViewById(R.id.songView);

        ArrayList<String> audioList = new ArrayList<>();

        String[] proj = { MediaStore.Audio.Media._ID,
                MediaStore.Audio.Media.TITLE,
                MediaStore.Audio.Media.ARTIST,
                MediaStore.Audio.Media.DURATION,
                MediaStore.Audio.Media.GENRE

        };// Can include more data for more details and check it.

        String selection =  MediaStore.Audio.Media.DURATION + ">= 60000";
        String DEFAULT_SELECTION = MediaStore.Audio.Media.IS_MUSIC + " != 0 and " +
                MediaStore.Audio.Media.DATA + " NOT LIKE '%/WhatsApp/%'";
        Cursor audioCursor = activity.getContentResolver().query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, proj, selection, null, null);
        if(audioCursor != null){
            if(audioCursor.moveToFirst()){
                do{
//                    int audioIndex = audioCursor.getColumnIndexOrThrow(MediaStore.Audio.Media.TITLE);
//
//                    songList.add(new Song(
//                            audioCursor.getString()

                    String songs = audioCursor.getString(1);
                    String singer = audioCursor.getString(2);
                    String duration = audioCursor.getString(3);
                    Song song = new Song(songs,singer, duration);
                    Log.d(TAG, "getAudioList: "+ song +" " + duration);
                    songList.add(song);
                }while(audioCursor.moveToNext());
            }
        }
        audioCursor.close();
        return songList;
    }
}
