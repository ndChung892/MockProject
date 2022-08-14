package com.example.mockproject.view.main.fragmentelement.song.element.artists;

public class ArtistsModel {
    private String nameArtist;
    private String numAlbums;
    private String numSongs;
    private int imgArtists;

    public ArtistsModel(String nameArtist, String numAlbums, String numSongs, int imgArtists) {
        this.nameArtist = nameArtist;
        this.numAlbums = numAlbums;
        this.numSongs = numSongs;
        this.imgArtists = imgArtists;
    }

    public String getNameArtist() {
        return nameArtist;
    }

    public void setNameArtist(String nameArtist) {
        this.nameArtist = nameArtist;
    }

    public String getNumAlbums() {
        return numAlbums;
    }

    public void setNumAlbums(String numAlbums) {
        this.numAlbums = numAlbums;
    }

    public String getNumSongs() {
        return numSongs;
    }

    public void setNumSongs(String numSongs) {
        this.numSongs = numSongs;
    }

    public int getImgArtists() {
        return imgArtists;
    }

    public void setImgArtists(int imgArtists) {
        this.imgArtists = imgArtists;
    }
}
