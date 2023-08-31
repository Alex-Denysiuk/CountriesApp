package com.alexd.countriesapp.model;

import com.alexd.countriesapp.di.DaggerApiComponent;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;

public class CountriesService {

    private static CountriesService instance;

    @Inject
    CountriesApi countriesApi;

    private CountriesService() {
        DaggerApiComponent.create().inject(this);
    }

    public static CountriesService getInstance() {
        if(instance == null) {
            instance = new CountriesService();
        }
        return instance;
    }

    public Single<List<CountryModel>> getCountries() {
        return countriesApi.getCountries();
    }
}
