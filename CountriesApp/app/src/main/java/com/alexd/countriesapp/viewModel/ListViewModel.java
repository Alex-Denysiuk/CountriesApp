package com.alexd.countriesapp.viewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.alexd.countriesapp.model.CountryModel;

import java.util.ArrayList;
import java.util.List;

public class ListViewModel extends ViewModel {

    public MutableLiveData<List<CountryModel>> countries = new MutableLiveData<>();
    public MutableLiveData<Boolean> countryLoadError = new MutableLiveData<>();
    public MutableLiveData<Boolean> loading = new MutableLiveData<>();

    public void refresh() {
        this.fetchCountries();
    }

    private void fetchCountries() {
        CountryModel countryModel1 = new CountryModel("Albania", "Tirana", "");
        CountryModel countryModel2 = new CountryModel("Brazil", "Brasilia", "");
        CountryModel countryModel3 = new CountryModel("Czechia", "Praga", "");

        List<CountryModel> countryModels = new ArrayList<>();
        countryModels.add(countryModel1);
        countryModels.add(countryModel2);
        countryModels.add(countryModel3);
        countries.setValue(countryModels);
        countryLoadError.setValue(false);
        loading.setValue(false);
    }
}
