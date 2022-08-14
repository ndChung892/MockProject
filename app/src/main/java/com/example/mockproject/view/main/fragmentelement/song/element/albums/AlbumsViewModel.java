package com.example.mockproject.view.main.fragmentelement.song.element.albums;

import android.util.Log;

import androidx.lifecycle.ViewModel;

import com.example.mockproject.R;

import java.util.ArrayList;
import java.util.List;

public class AlbumsViewModel extends ViewModel {
    private List<AlbumsModel> albumsModelList = new ArrayList<>();

    private static final String TAG = "AlbumsViewModel";

    public List<AlbumsModel> initAlbums(){
        albumsModelList.add(new AlbumsModel("History", R.drawable.img_albums1, "Micheal Jackson", "10 Songs"));
        albumsModelList.add(new AlbumsModel("Thriller", R.drawable.img_albums2, "Micheal Jackson", "10 Songs"));
        albumsModelList.add(new AlbumsModel("It Won't Be Soon", R.drawable.img_albums3, "Maroon 5", "10 Songs"));
        albumsModelList.add(new AlbumsModel("History", R.drawable.img_albums4, "Beyonce", "10 Songs"));
        albumsModelList.add(new AlbumsModel("History", R.drawable.img_albums5, "Micheal Jackson", "10 Songs"));
        albumsModelList.add(new AlbumsModel("History", R.drawable.img_albums6, "Micheal Jackson", "10 Songs"));
        Log.d(TAG, "initAlbums: "+ albumsModelList);
        return albumsModelList;
    }
}
