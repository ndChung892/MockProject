package com.example.mockproject.view.main.fragmentelement.song.element.albums;

public class AlbumsModel {
    private String nameAlbums;
    private int imgAlbums;
    private String singerAlbums;
    private String numSong;

    public AlbumsModel(String nameAlbums, int imgAlbums, String singerAlbums, String numSong) {
        this.nameAlbums = nameAlbums;
        this.imgAlbums = imgAlbums;
        this.singerAlbums = singerAlbums;
        this.numSong = numSong;
    }

    public String getNameAlbums() {
        return nameAlbums;
    }

    public void setNameAlbums(String nameAlbums) {
        this.nameAlbums = nameAlbums;
    }

    public int getImgAlbums() {
        return imgAlbums;
    }

    public void setImgAlbums(int imgAlbums) {
        this.imgAlbums = imgAlbums;
    }

    public String getSingerAlbums() {
        return singerAlbums;
    }

    public void setSingerAlbums(String singerAlbums) {
        this.singerAlbums = singerAlbums;
    }

    public String getNumSong() {
        return numSong;
    }

    public void setNumSong(String numSong) {
        this.numSong = numSong;
    }
}

