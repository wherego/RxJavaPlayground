package com.rafagarcia.rxjavaplayground.countries;

import com.rafagarcia.rxjavaplayground.model.Country;
import com.rafagarcia.rxjavaplayground.model.webapi.request.country.CountryWebApi;

import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by rafagarcia on 29/05/2016.
 */
public class CountriesInteractor {

    CountryWebApi mCountryApi;

    public CountriesInteractor() {
        mCountryApi = new CountryWebApi();
    }

    void getAllCountries(Subscriber subscriber){
        Observable<List<Country>> countries = mCountryApi.getCountries();
        countries.observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(subscriber);
    }

    void getCountry(final String countryName, Subscriber subscriber){
        Observable<List<Country>> countries = mCountryApi.getCountries();
        countries.observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io())
                .map(new Func1<List<Country>, Object>() {
                    @Override
                    public Object call(List<Country> countries) {
                        for (Country country : countries) {
                            if(country.getName().equals(countryName)){
                                return country;
                            }
                        }
                        return null;
                    }
                })
                .subscribe(subscriber);
    }
}
