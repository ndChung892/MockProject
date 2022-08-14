package com.example.mockproject.view.main.fragmentelement.song.element.allsong;

import android.graphics.Bitmap;
import android.net.Uri;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import java.io.Serializable;
import java.util.Objects;

public class Song implements Serializable {
    private String id;
    private String songs;
    private String singer;
    private Bitmap img;
    private String resourceMusic;
    private String album;
    private boolean isPlaying;
//    String
    private String duration;
    private String albumId;
    private String AlbumartUri;



    public Song(String songs, String singer, String duration, Bitmap img, String resourceMusic, String album, boolean isPlaying) {
        this.songs = songs;
        this.singer = singer;
        this.duration = duration;
        this.img = img;
        this.resourceMusic = resourceMusic;
        this.album = album;
        this.isPlaying = isPlaying;
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

    public Bitmap getImg() {
        return img;
    }

    public void setImg(Bitmap img) {
        this.img = img;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public boolean isPlaying() {
        return isPlaying;
    }

    public void setPlaying(boolean playing) {
        isPlaying = playing;
    }

    public String getResourceMusic() {
        return resourceMusic;
    }

    public void setResourceMusic(String resourceMusic) {
        this.resourceMusic = resourceMusic;
    }

    @Override
    public String toString() {
        return "Song{" +
                "songs='" + songs + '\'' +
                ", singer='" + singer + '\'' +
                ", img=" + img +
                ", resourceMusic='" + resourceMusic + '\'' +
                ", duration='" + duration + '\'' +
                ", isPlaying=" + isPlaying +
                '}';
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Song song = (Song) o;
        return isPlaying() == song.isPlaying()
                && Objects.equals(getSongs(), song.getSongs())
                && Objects.equals(getSinger(), song.getSinger());
    }

    public static DiffUtil.ItemCallback<Song> diffCallback
            = new DiffUtil.ItemCallback<Song>() {
        @Override
        public boolean areItemsTheSame(@NonNull Song oldItem,
                                       @NonNull Song newItem) {
            return oldItem.id.equals(newItem.id);
        }

        @Override
        public boolean areContentsTheSame(@NonNull Song oldItem
                , @NonNull Song newItem) {
            return oldItem.equals(newItem);
        }
    };

}
