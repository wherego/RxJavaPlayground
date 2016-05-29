package com.rafagarcia.rxjavaplayground.countries;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.rafagarcia.rxjavaplayground.R;

public class CountriesActivity extends AppCompatActivity {

    static final String COUNTRY_FRAGMENT_TAG = "country_fragment";
    CountriesFragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (getSupportFragmentManager().findFragmentByTag(COUNTRY_FRAGMENT_TAG) == null) {
            fragment = CountriesFragment.newInstance();
            getSupportFragmentManager().beginTransaction().add(R.id.countryFragment, fragment, COUNTRY_FRAGMENT_TAG).commit();
        } else {
            getSupportFragmentManager().beginTransaction().replace(R.id.countryFragment, fragment).commit();
        }
    }
}
