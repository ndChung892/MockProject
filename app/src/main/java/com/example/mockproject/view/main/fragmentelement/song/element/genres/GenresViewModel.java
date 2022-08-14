package com.example.mockproject.view.main.fragmentelement.song.element.genres;

import androidx.lifecycle.ViewModel;

import com.example.mockproject.R;

import java.util.ArrayList;
import java.util.List;

public class GenresViewModel extends ViewModel {

    List<GenresModel> genresModelList;
    public List<GenresModel> initGenres(){
        genresModelList = new ArrayList<>();
        genresModelList.add(new GenresModel("Classical", "56 Songs"));
        genresModelList.add(new GenresModel("Pop", "56 Songs"));
        genresModelList.add(new GenresModel("Hip-Hop", "56 Songs"));
        genresModelList.add(new GenresModel("Rock", "56 Songs"));
        genresModelList.add(new GenresModel("Soul and R&B", "56 Songs"));
        genresModelList.add(new GenresModel("Instrumental", "56 Songs"));
        genresModelList.add(new GenresModel("Jazz", "56 Songs"));
        genresModelList.add(new GenresModel("Country Music", "56 Songs"));
        return genresModelList;
    }
}
