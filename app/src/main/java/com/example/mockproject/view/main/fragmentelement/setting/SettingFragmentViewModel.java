package com.example.mockproject.view.main.fragmentelement.setting;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.mockproject.R;

import java.util.ArrayList;
import java.util.List;

public class SettingFragmentViewModel extends ViewModel {
    MutableLiveData<List<MenuSetting>> mMenuListMutableLiveData;
    List<MenuSetting> menuSettingList;

    public SettingFragmentViewModel() {
        mMenuListMutableLiveData = new MutableLiveData<>();
        initData();
    }

    public MutableLiveData<List<MenuSetting>> getMenuListMutableLiveData() {
        return mMenuListMutableLiveData;
    }

    private void initData() {
        menuSettingList = new ArrayList<>();
        menuSettingList.add(new MenuSetting(R.drawable.ic_display, "Display"));
        menuSettingList.add(new MenuSetting(R.drawable.ic_audio, "Audio"));
        menuSettingList.add(new MenuSetting(R.drawable.ic_headset,"Headset"));
        menuSettingList.add(new MenuSetting(R.drawable.ic_lockscreen, "Lockscreen"));
        menuSettingList.add(new MenuSetting(R.drawable.ic_advance,"Advance"));
        menuSettingList.add(new MenuSetting(R.drawable.ic_other, "Other"));
        mMenuListMutableLiveData.setValue(menuSettingList);
    }
}
