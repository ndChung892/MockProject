package com.example.mockproject.view.main.fragmentelement.home;

public class RecentlyPlayed {
    private String song;
    private String singer;

    public RecentlyPlayed(String song, String singer) {
        this.song = song;
        this.singer = singer;
    }

    public String getSong() {
        return song;
    }

    public void setSong(String song) {
        this.song = song;
    }

    public String getSinger() {
        return singer;
    }
    public void setSinger(String singer) {
        this.singer = singer;
    }

    @Override
    public String toString() {
        return "RecentlyPlayed{" +
                "song='" + song + '\'' +
                ", singer='" + singer + '\'' +
                '}';
    }
}
