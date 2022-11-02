package com.example.tapastop.ui.galardones;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class GalardonesViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public GalardonesViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is galardones fragment");
    }

    public  LiveData<String> getText() {
        return mText;
    }
}