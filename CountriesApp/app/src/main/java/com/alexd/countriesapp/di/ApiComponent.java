package com.alexd.countriesapp.di;

import com.alexd.countriesapp.model.CountriesService;

import dagger.Component;

@Component(modules = {ApiModule.class})
public interface ApiComponent {

    void inject(CountriesService service);

}
