package com.example.mockproject.view.main.fragmentelement.home;

import com.example.mockproject.view.main.fragmentelement.song.element.allsong.Song;

import java.util.List;

public class HomeModel {
    private int type;
    private String title;
    private String viewAll;
    private List<Recommended> recommendedList;
    private List<Playlist> playlistList;
    private List<RecentlyPlayed> recentlyPlayedList;

    public HomeModel(int type, String title, String viewAll, List<Recommended> recommendedList, List<Playlist> playlistList, List<RecentlyPlayed> recentlyPlayedList) {
        this.type = type;
        this.title = title;
        this.viewAll = viewAll;
        this.recommendedList = recommendedList;
        this.playlistList = playlistList;
        this.recentlyPlayedList = recentlyPlayedList;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getViewAll() {
        return viewAll;
    }

    public void setViewAll(String viewAll) {
        this.viewAll = viewAll;
    }

    public List<Recommended> getRecommendedList() {
        return recommendedList;
    }

    public void setRecommendedList(List<Recommended> recommendedList) {
        this.recommendedList = recommendedList;
    }

    public List<Playlist> getPlaylistList() {
        return playlistList;
    }

    public void setPlaylistList(List<Playlist> playlistList) {
        this.playlistList = playlistList;
    }

    public List<RecentlyPlayed> getRecentlyPlayedList() {
        return recentlyPlayedList;
    }

    public void setRecentlyPlayedList(List<RecentlyPlayed> recentlyPlayedList) {
        this.recentlyPlayedList = recentlyPlayedList;
    }

    @Override
    public String toString() {
        return "HomeModel{" +
                "type=" + type +
                ", title='" + title + '\'' +
                ", viewAll='" + viewAll + '\'' +
                ", recommendedList=" + recommendedList +
                ", playlistList=" + playlistList +
                ", recentlyPlayedList=" + recentlyPlayedList +
                '}';
    }
}
