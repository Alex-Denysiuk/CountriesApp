package com.alexd.countriesapp.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.view.View;
import com.alexd.countriesapp.databinding.ActivityMainBinding;
import com.alexd.countriesapp.model.CountryModel;
import com.alexd.countriesapp.viewModel.ListViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private ListViewModel viewModel;
    private final CountryListAdapter adapter = new CountryListAdapter(new ArrayList<>());
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = this.binding.getRoot();
        setContentView(view);

        this.viewModel = ViewModelProviders.of(this).get(ListViewModel.class);
        this.viewModel.refresh();

        this.binding.rvCountriesList.setLayoutManager(new LinearLayoutManager(this));
        this.binding.rvCountriesList.setAdapter(adapter);

        this.binding.srlRefreshLayout.setOnRefreshListener(() -> {
            viewModel.refresh();
            this.binding.srlRefreshLayout.setRefreshing(false);
        });
        this.observeViewModel();
    }

    private void observeViewModel() {
        this.viewModel.countries.observe(this, countryModels -> {
            if(countryModels == null) {
                return;
            }
            binding.rvCountriesList.setVisibility(View.VISIBLE);
            List<ItemViewModel> items = new ArrayList<>();
            for (CountryModel model :
                    countryModels) {
                items.add(new CountryItemViewModel(model));
            }
            adapter.updateCountries(items);
            binding.rvCountriesList.post(adapter::notifyDataSetChanged);
        });
        this.viewModel.countryLoadError.observe(this, isError -> {
            if(isError == null) {
                return;
            }
            binding.tvListError.setVisibility(isError ? View.VISIBLE : View.GONE);
        });
        this.viewModel.loading.observe(this, isLoading -> {
            if(isLoading == null) {
                return;
            }
            binding.pbLoaginView.setVisibility(isLoading ? View.VISIBLE : View.GONE);
            if(isLoading) {
                binding.tvListError.setVisibility(View.GONE);
                binding.rvCountriesList.setVisibility(View.GONE);
            }
        });
    }
}