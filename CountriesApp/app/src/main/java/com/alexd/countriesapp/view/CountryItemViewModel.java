package com.alexd.countriesapp.view;

import static com.alexd.countriesapp.view.CountryItemsViewModel.COUNTRY_ITEM;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

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

    @BindingAdapter({"countryFlagUrl"})
    public static void loadImage(ImageView view, String imageUrl) {
        Util.loadImage(view, imageUrl, Util.getProgressDrawable(view.getContext()));
    }

    public String getCountryFlag() {
        return this.item.getFlag();
    }

    public String getCountryName() {
        return this.item.getCountryName();
    }

    public String getCapital() {
        return this.item.getCapital();
    }

}
