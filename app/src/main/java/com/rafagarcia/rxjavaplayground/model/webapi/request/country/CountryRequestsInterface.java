package com.rafagarcia.rxjavaplayground.model.webapi.request.country;

import com.rafagarcia.rxjavaplayground.model.Country;

import java.util.List;

import retrofit.http.GET;
import rx.Observable;

/**
 * Created by glouzonf on 12/05/2015.
 */
public interface CountryRequestsInterface {

    @GET("all")
    Observable<List<Country>> getAllCountries();
}
