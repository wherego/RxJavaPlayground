package com.rafagarcia.rxjavaplayground.countries;

import com.rafagarcia.rxjavaplayground.model.Country;

import java.util.List;

/**
 * Created by rafagarcia on 29/05/2016.
 */
public interface CountriesView {
    void updateAdapter(List<Country> countries);
}
