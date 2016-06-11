package com.rafagarcia.rxjavaplayground.countries;

import android.util.Log;

import com.rafagarcia.rxjavaplayground.model.Country;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;

/**
 * Created by rafagarcia on 29/05/2016.
 */
public class CountriesPresenter {

    CountriesView mView;
    CountriesInteractor mInteractor;

    public CountriesPresenter(CountriesView view, CountriesInteractor interactor) {
        mView = view;
        mInteractor = interactor;
    }

    public void getAllCountries(){
        mInteractor.getAllCountries(new Subscriber<List<Country>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                Log.e("Error", e.getMessage());
            }

            @Override
            public void onNext(List<Country> countries) {
                mView.updateAdapter(countries);
            }
        });
    }

    public void getCountry(String countryName){
        mInteractor.getCountry(countryName, new Subscriber<Country>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Country country) {
                if(country != null){
                    List<Country> list = new ArrayList<>();
                    list.add(country);
                    mView.updateAdapter(list);
                }
            }
        });
    }
}
