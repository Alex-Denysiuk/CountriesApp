package com.alexd.countriesapp;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;

import com.alexd.countriesapp.model.CountriesService;
import com.alexd.countriesapp.model.CountryModel;
import com.alexd.countriesapp.viewModel.ListViewModel;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.internal.schedulers.ExecutorScheduler;
import io.reactivex.plugins.RxJavaPlugins;

public class ListViewModelTest {

    @Rule
    public InstantTaskExecutorRule rule = new InstantTaskExecutorRule();

    @Mock
    CountriesService countriesService;

    @InjectMocks
    ListViewModel viewModel = new ListViewModel();

    private Single<List<CountryModel>>  testSingle;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Before
    public void setUpRxSchedulers() {

        Scheduler immediate = new Scheduler() {
            @Override
            public Worker createWorker() {
                return new ExecutorScheduler.ExecutorWorker(Runnable::run, true);
            }
        };
        RxJavaPlugins.setInitNewThreadSchedulerHandler(schedulerCallable -> immediate);
        RxAndroidPlugins.setInitMainThreadSchedulerHandler(schedulerCallable -> immediate);
    }

    @Test
    public void testLoadCountriesSuccess() {
        CountryModel countryModel = new CountryModel("countryName", "capital", "flag");
        List<CountryModel> countryModels = new ArrayList<>();
        countryModels.add(countryModel);

        this.testSingle = Single.just(countryModels);
        when(this.countriesService.getCountries()).thenReturn(this.testSingle);

        this.viewModel.refresh();

        assertEquals(1, this.viewModel.countries.getValue().size());
        assertEquals(false, this.viewModel.countryLoadError.getValue());
        assertEquals(false, this.viewModel.loading.getValue());
    }
}
