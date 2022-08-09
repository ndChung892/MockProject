package com.example.mockproject.view.main.fragmentelement.song;

import java.io.Serializable;

public class Song implements Serializable {
    String id;
    String songs;
    String singer;
//    String
    String duration;
    String albumId;
    String AlbumartUri;

    public Song(String id, String songs, String duration, String albumId, String albumartUri) {
        this.id = id;
        this.songs = songs;
        this.duration = duration;
        this.albumId = albumId;
        AlbumartUri = albumartUri;
    }

    public Song(String songs,String singer, String duration) {

        this.songs = songs;
        this.singer = singer;
        this.duration = duration;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSongs() {
        return songs;
    }

    public void setSongs(String songs) {
        this.songs = songs;
    }

    public String getSinger() {
        return singer;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getAlbumId() {
        return albumId;
    }

    public void setAlbumId(String albumId) {
        this.albumId = albumId;
    }

    public String getAlbumartUri() {
        return AlbumartUri;
    }

    public void setAlbumartUri(String albumartUri) {
        AlbumartUri = albumartUri;
    }

    @Override
    public String toString() {
        return "Song{" +
                "id='" + id + '\'' +
                ", songs='" + songs + '\'' +
                ", singer='" + singer + '\'' +
                ", duration='" + duration + '\'' +
                ", albumId='" + albumId + '\'' +
                ", AlbumartUri='" + AlbumartUri + '\'' +
                '}';
    }
}
