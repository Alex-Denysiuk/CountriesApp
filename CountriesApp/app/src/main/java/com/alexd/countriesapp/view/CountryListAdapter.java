package com.alexd.countriesapp.view;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.library.baseAdapters.BR;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CountryListAdapter extends RecyclerView.Adapter<CountryListAdapter.CountryViewHolder> {


    @NonNull
    private List<ItemViewModel> list = new ArrayList<>();
    @NonNull
    private final Map<Integer, Integer> viewTypeToLayoutId = new HashMap();
    public CountryListAdapter(List<ItemViewModel> countries) {
        this.list = countries;
    }

    @SuppressLint("NotifyDataSetChanged")
    public void updateCountries(List<ItemViewModel> newCountries) {
        this.list.clear();
        this.list.addAll(newCountries);
        super.notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CountryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        int resource = getResourceByViewType(viewType);
        ViewDataBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                resource, parent, false);
        return new CountryViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull CountryListAdapter.CountryViewHolder holder, int position) {
        holder.bind(this.getItem(position));
    }

    @Override
    public int getItemCount() {
        return this.list.size();
    }

    @Override
    public int getItemViewType(int position) {
        ItemViewModel item = this.getItem(position);
        if (!this.viewTypeToLayoutId.containsKey(item.getViewType().getValue())) {
            this.viewTypeToLayoutId.put(item.getViewType().getValue(), item.getLayoutId());
        }
        return item.getViewType().getValue();
    }

    public ItemViewModel getItem(int position) {
        return this.list.get(position);
    }

    private int getResourceByViewType(int viewType) {
        Integer integer = this.viewTypeToLayoutId.get(viewType);
        if(integer == null) {
            return 0;
        }
        return integer;
    }

    class CountryViewHolder extends RecyclerView.ViewHolder {

        public ViewDataBinding itemRowBinding;

        public CountryViewHolder(ViewDataBinding itemRowBinding) {
            super(itemRowBinding.getRoot());
            this.itemRowBinding = itemRowBinding;
        }
        void bind(ItemViewModel itemViewModel) {
            this.itemRowBinding.setVariable(BR.itemViewModel, itemViewModel);
            this.itemRowBinding.executePendingBindings();

        }
    }
}
