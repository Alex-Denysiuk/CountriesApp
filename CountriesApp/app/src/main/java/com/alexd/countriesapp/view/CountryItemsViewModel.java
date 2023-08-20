package com.alexd.countriesapp.view;

import java.util.HashMap;
import java.util.Map;

public enum CountryItemsViewModel implements ItemViewModelType {

    NONE(0),
    COUNTRY_ITEM(1);

    private final int value;
    private static final Map map = new HashMap<>();

    CountryItemsViewModel(int value) {
        this.value = value;
    }

    static {
        for (CountryItemsViewModel folder : CountryItemsViewModel.values()) {
            map.put(folder.value, folder);
        }
    }

    @Override
    public int getValue() {
        return value;
    }

    public static CountryItemsViewModel valueOf(int type) {
        if(!contains(type)) {
            return NONE;
        }
        CountryItemsViewModel returnedType = (CountryItemsViewModel) map.get(type);
        if(returnedType == null) {
            return NONE;
        }
        return returnedType;
    }

    public static boolean contains(int type) {
        return map.containsKey(type);
    }
}
