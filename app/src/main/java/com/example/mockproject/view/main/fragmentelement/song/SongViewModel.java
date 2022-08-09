package com.example.mockproject.view.main.fragmentelement.song;

import android.app.Activity;

import androidx.lifecycle.ViewModel;

import com.example.mockproject.utils.Utils;

import java.util.List;

public class SongViewModel extends ViewModel {
    public List<Song> getSongList(Activity activity){
        return new Utils().getAudioList(activity);
    }

}
