package com.example.mockproject.view.main;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.mockproject.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivityViewModel extends ViewModel {
    public static final String TAG ="MainActivityViewModel";

    private MutableLiveData<List<MenuModel>> listMenuModelLiveData ;
    private List<MenuModel> menuModelList;


    public MainActivityViewModel() {
        listMenuModelLiveData = new MutableLiveData<>();
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
}
