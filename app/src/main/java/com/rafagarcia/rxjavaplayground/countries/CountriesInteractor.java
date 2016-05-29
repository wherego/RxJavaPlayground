package com.rafagarcia.rxjavaplayground.countries;

import com.rafagarcia.rxjavaplayground.model.Country;

import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by rafagarcia on 29/05/2016.
 */
public class CountriesInteractor {

    void getAllCountries(Subscriber subscriber){
        Observable<List<Country>> countries = null;
        countries.observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(subscriber);
    }
}
