package com.alexd.countriesapp.view;

import static com.alexd.countriesapp.view.CountryItemsViewModel.COUNTRY_ITEM;

import com.alexd.countriesapp.R;
import com.alexd.countriesapp.model.CountryModel;

public class CountryItemViewModel implements ItemViewModel{

    private final CountryModel item;


    public CountryItemViewModel(CountryModel item) {
        this.item = item;
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_country;
    }

    @Override
    public ItemViewModelType getViewType() {
        return COUNTRY_ITEM;
    }

    public String getCountryName() {
        return this.item.getCountryName();
    }

    public String getCapital() {
        return this.item.getCapital();
    }
}
