package com.rafagarcia.rxjavaplayground.countries;

import com.rafagarcia.rxjavaplayground.model.Country;
import com.rafagarcia.rxjavaplayground.model.webapi.request.country.CountryWebApi;

import java.util.List;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Scheduler;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.observers.Subscribers;
import rx.schedulers.Schedulers;

/**
 * Created by rafagarcia on 29/05/2016.
 */
public class CountriesInteractor {

    CountryWebApi mCountryApi;

    public CountriesInteractor() {
        mCountryApi = new CountryWebApi();
    }

    void getAllCountries(Subscriber<List<Country>> subscriber){
        Observable<List<Country>> countries = mCountryApi.getCountries();
        countries.observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(subscriber);
    }

    void getAllCountriesOneByOne(Subscriber subscriber){
        Observable<List<Country>> countries = mCountryApi.getCountries();
        countries.flatMap(new Func1<List<Country>, Observable<?>>() {
            @Override
            public Observable<?> call(List<Country> countries) {
               return Observable.from(countries);
            }
        })
                .observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(subscriber);
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
