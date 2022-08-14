package com.example.mockproject.view.main.fragmentelement.song.element.genres;

public class GenresModel {
    private String nameGenres;
    private String numSongs;

    public GenresModel(String nameGenres, String numSongs) {
        this.nameGenres = nameGenres;
        this.numSongs = numSongs;
    }

    public String getNameGenres() {
        return nameGenres;
    }

    public void setNameGenres(String nameGenres) {
        this.nameGenres = nameGenres;
    }

    public String getNumSongs() {
        return numSongs;
    }

    public void setNumSongs(String numSongs) {
        this.numSongs = numSongs;
    }
}
