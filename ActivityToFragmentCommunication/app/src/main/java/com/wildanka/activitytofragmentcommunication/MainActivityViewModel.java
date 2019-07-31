package com.wildanka.activitytofragmentcommunication;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

public class MainActivityViewModel extends ViewModel {
    private int pagesIndex;

    public int getPagesIndex() {
        return pagesIndex;
    }

    public void setPagesIndex(int pagesIndex) {
        this.pagesIndex = pagesIndex;
    }
}
