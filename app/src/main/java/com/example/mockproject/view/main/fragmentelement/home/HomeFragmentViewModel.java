package com.example.mockproject.view.main.fragmentelement.home;

import android.app.Application;
import android.util.Log;


import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.mockproject.R;
import com.example.mockproject.view.main.fragmentelement.song.element.allsong.Song;
import com.example.mockproject.view.main.fragmentelement.song.element.allsong.SongViewModel;

import java.util.ArrayList;
import java.util.List;

public class HomeFragmentViewModel extends ViewModel {

    private static final String TAG ="HomeFragmentViewModel";
    private MutableLiveData<List<Recommended>> recommendedListMutable = new MutableLiveData<>();
    private MutableLiveData<List<Playlist>> playlistListMutable = new MutableLiveData<>();
    private MutableLiveData<List<RecentlyPlayed>> recentlyPlayedListMutable = new MutableLiveData<>();
    private MutableLiveData<List<HomeModel>> homeModelListMutable;
    private List<Recommended> recommendedList;
    private List<Playlist> playlistList;
    private List<RecentlyPlayed> recentlyPlayedList;
    private SongViewModel songViewModel;

//    private List<Song> songList;

    private List<HomeModel> homeModelList;
    public HomeFragmentViewModel() {
        homeModelListMutable = new MutableLiveData<>();
        createHomeItemMenu();
    }

    public MutableLiveData<List<HomeModel>> getHomeModelListMutable() {
        return homeModelListMutable;
    }

    private void createRecommendedList(){
        recommendedList = new ArrayList<>();
        recommendedList.add(new Recommended(R.drawable.img_recommened1,"Sound of Sky", "Dilon Bruce"));
        recommendedList.add(new Recommended(R.drawable.img_recommended2,"Girl on Fire", "Alecia Keys"));
    }
    private void createPlayListList(){
        playlistList = new ArrayList<>();
        playlistList.add(new Playlist(R.drawable.img_playlist1, "Classic Playlist","Piano guys"));
        playlistList.add(new Playlist(R.drawable.img_playlist2, "Summer Playlist","Dilon Bruce"));
        playlistList.add(new Playlist(R.drawable.img_playlist3, "Pop Music","Micheal Jackson"));
    }
    private void createRecentlyPlayedList(){
        recentlyPlayedList = new ArrayList<>();
        recentlyPlayedList.add(new RecentlyPlayed("Billie Jean", "Micheal Jackson"));
        recentlyPlayedList.add(new RecentlyPlayed("Earth Song", "Micheal Jackson"));
        recentlyPlayedList.add(new RecentlyPlayed("Mirror", "Justin Timberlake"));
        recentlyPlayedList.add(new RecentlyPlayed("Remember the Time", "Micheal Jackson"));
        recentlyPlayedList.add(new RecentlyPlayed("Billie Jean", "Micheal Jackson"));
        Log.d(TAG, "createRecentlyPlayedList: "+ recentlyPlayedList);
    }

    private void createHomeItemMenu() {
        homeModelList = new ArrayList<>();
        createRecommendedList();
        createPlayListList();
        createRecentlyPlayedList();
        Log.d(TAG, "createHomeItemMenu: "+ recentlyPlayedList);
        homeModelList.add(new HomeModel(0,"Recommended", "View All",recommendedList, null, null));
        homeModelList.add(new HomeModel(1,"Playlist", "View All",null, playlistList, null));
        homeModelList.add(new HomeModel(2,"Recently Played", "View All",null, null, recentlyPlayedList));

        homeModelListMutable.setValue(homeModelList);
    }
}
