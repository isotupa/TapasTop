package com.example.tapastop.ui.ayuda;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AyudaViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public AyudaViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is ayuda fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}