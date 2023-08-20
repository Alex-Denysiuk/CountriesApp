package com.alexd.countriesapp.view;

import androidx.annotation.LayoutRes;

public interface ItemViewModel {

    @LayoutRes
    int getLayoutId();

    ItemViewModelType getViewType();
}