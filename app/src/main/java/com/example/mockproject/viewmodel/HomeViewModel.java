package com.example.mockproject.viewmodel;

import android.app.Application;
import android.view.Menu;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.mockproject.R;
import com.example.mockproject.model.MenuModel;

import java.util.List;

public class HomeViewModel extends AndroidViewModel {
    public static final String TAG ="HomeViewModel";

    private MutableLiveData<List<MenuModel>> listMenuModelLiveData ;
    List<MenuModel> menuModelList;
    Application mApplication;

    public HomeViewModel(@NonNull Application application) {
        super(application);
        this.mApplication = application;
        listMenuModelLiveData = new MutableLiveData<>();
    }
    public MutableLiveData<List<MenuModel>> getListMenuModelLiveData(){
        return listMenuModelLiveData;
    }
    public LiveData<List<MenuModel>> loadData(){
        menuModelList.add(new MenuModel(R.drawable.ic_theme,"Theme"));
        menuModelList.add(new MenuModel(R.drawable.ic_cutter,"RingTone Cutter"));
        menuModelList.add(new MenuModel(R.drawable.ic_sleep_timer,"Sleep Timer"));
        menuModelList.add(new MenuModel(R.drawable.ic_quliser,"Equliser"));
        menuModelList.add(new MenuModel(R.drawable.ic_drive_mode,"Drive Mode"));
        menuModelList.add(new MenuModel(R.drawable.ic_hidden_folder,"Hidden Folder"));
        menuModelList.add(new MenuModel(R.drawable.ic_scan_media,"Scan Media"));
        return menuModelList;
    }
}
