package com.rafagarcia.rxjavaplayground.model.webapi.request.country;

import com.rafagarcia.rxjavaplayground.model.Country;
import com.rafagarcia.rxjavaplayground.model.webapi.BaseWebApi;
import com.rafagarcia.rxjavaplayground.model.webapi.HttpLoggingInterceptor;
import com.squareup.okhttp.OkHttpClient;

import java.util.List;

import retrofit.JacksonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;
import rx.Observable;

/**
 * Created by glouzaille on 06/05/2015.
 */
public class CountryWebApi extends BaseWebApi {

    private static final String BASE_URL = "https://restcountries.eu/rest/v1/";
    private OkHttpClient mOkHttpClient;
    private HttpLoggingInterceptor mLogging;
    private Retrofit mRetrofit;
    private CountryRequestsInterface mRequestsService;

    public CountryWebApi() {
        getRequestsService();
    }

    public Observable<List<Country>> getCountries() {

        return mRequestsService.getAllCountries();
    }

    private void getRequestsService() {
        mOkHttpClient = new OkHttpClient();
        mLogging = new HttpLoggingInterceptor();
        mLogging.setLevel(HttpLoggingInterceptor.Level.BODY);
        mOkHttpClient.interceptors().add(mLogging);

        mRetrofit = new Retrofit.Builder()
                .client(mOkHttpClient)
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(JacksonConverterFactory.create())
                .build();

        mRequestsService =  mRetrofit.create(CountryRequestsInterface.class);
    }
}
