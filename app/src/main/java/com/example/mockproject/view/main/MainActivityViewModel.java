package com.example.mockproject.view.main;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.mockproject.R;
import com.example.mockproject.view.main.fragmentelement.song.element.allsong.Song;

import java.util.ArrayList;
import java.util.List;

public class MainActivityViewModel extends ViewModel {
    public static final String TAG ="MainActivityViewModel";

    private MutableLiveData<List<MenuModel>> listMenuModelLiveData ;
    private MutableLiveData<Boolean> visibleBottomControl = new MutableLiveData<>();
    private List<MenuModel> menuModelList;

    private static MutableLiveData<Song> serializableMutableLiveData;

    public MainActivityViewModel() {
        listMenuModelLiveData = new MutableLiveData<>();
        serializableMutableLiveData = new MutableLiveData<>();
        createMenuModelList();

    }
    public MutableLiveData<List<MenuModel>> getListMenuModelLiveData(){
        return listMenuModelLiveData;
    }
    public void createMenuModelList(){
        menuModelList = new ArrayList<>();
        menuModelList.add(new MenuModel(R.drawable.ic_theme, "Theme"));
        menuModelList.add(new MenuModel(R.drawable.ic_cutter,"RingTone Cutter"));
        menuModelList.add(new MenuModel(R.drawable.ic_sleep_timer,"Sleep Timer"));
        menuModelList.add(new MenuModel(R.drawable.ic_quliser,"Equliser"));
        menuModelList.add(new MenuModel(R.drawable.ic_drive_mode,"Drive Mode"));
        menuModelList.add(new MenuModel(R.drawable.ic_hidden_folder,"Hidden Folder"));
        menuModelList.add(new MenuModel(R.drawable.ic_scan_media,"Scan Media"));
        listMenuModelLiveData.setValue(menuModelList);
    }
    public void createRecommendedList(){

    }

    public void sendData(Song song) {
        Log.d(TAG, "After sendData: "+serializableMutableLiveData.getValue());
        serializableMutableLiveData.postValue(song);
        Log.d(TAG, "Before sendData: "+serializableMutableLiveData.getValue());
        Log.i(TAG, "============================================");
    }
    public LiveData<Song> getSong() {
        Log.d(TAG, "getSong: "+ serializableMutableLiveData.getValue());
        return serializableMutableLiveData;
    }




}
