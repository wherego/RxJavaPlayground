package com.rafagarcia.rxjavaplayground.countries;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rafagarcia.rxjavaplayground.R;

import butterknife.ButterKnife;

/**
 * Created by rafagarcia on 29/05/2016.
 */
public class CountriesFragment extends Fragment implements CountriesView {

    CountriesPresenter mPresenter;
    CountriesInteractor mInteractor;

    public static CountriesFragment newInstance() {
        CountriesFragment fragment = new CountriesFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_countries, container, false);
        initViews(view);
        init();
        return view;
    }

    private void initViews(View view) {
        ButterKnife.bind(this, view);
    }

    private void init() {
        mInteractor = new CountriesInteractor();
        mPresenter = new CountriesPresenter(this, mInteractor);
    }
}