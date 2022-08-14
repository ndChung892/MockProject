package com.example.mockproject.view.main.fragmentelement.song.element.artists;

import androidx.lifecycle.ViewModel;

import com.example.mockproject.R;

import java.util.ArrayList;
import java.util.List;

public class ArtistViewModel extends ViewModel {

    List<ArtistsModel> artistsModelList;
    public List<ArtistsModel> initArtists(){
        artistsModelList = new ArrayList<>();
        artistsModelList.add(new ArtistsModel("Beyonce", "4 Albums"," 38 Songs", R.drawable.img_artists1));
        artistsModelList.add(new ArtistsModel("Bebe Rexha", "2 Albums","17 Songs", R.drawable.img_artists2));
        artistsModelList.add(new ArtistsModel("Maroon 5", "5 Albums"," 46 Songs", R.drawable.img_artists3));
        artistsModelList.add(new ArtistsModel("Micheal Jackson", "10 Albums","112 Songs", R.drawable.img_artists4));
        artistsModelList.add(new ArtistsModel("Queens", "3 Albums"," 32 Songs", R.drawable.img_artists5));
        artistsModelList.add(new ArtistsModel("Yani", "1 Albums"," 13 Songs", R.drawable.img_artists6));
        artistsModelList.add(new ArtistsModel("Steve Johns", "4 Albums"," 38 Songs", R.drawable.img_artists7));
        return artistsModelList;
    }
}
