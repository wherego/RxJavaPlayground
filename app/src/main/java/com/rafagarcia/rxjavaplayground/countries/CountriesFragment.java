package com.rafagarcia.rxjavaplayground.countries;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rafagarcia.rxjavaplayground.R;
import com.rafagarcia.rxjavaplayground.model.Country;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

/**
 * Created by rafagarcia on 29/05/2016.
 */
public class CountriesFragment extends Fragment implements CountriesView {

    CountriesPresenter mPresenter;
    CountriesInteractor mInteractor;
    private RecyclerView mRecyclerView;
    private CountriesAdapter mAdapter;
    private List<Country> mCountryList;

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
        mRecyclerView = (RecyclerView)view.findViewById(R.id.recyclerView);
        mCountryList = new ArrayList<>();
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(manager);
        mAdapter = new CountriesAdapter(mCountryList, getContext());
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void updateAdapter(List<Country> countries) {
        mCountryList.clear();
        mCountryList.addAll(countries);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void updateAdapter(Country country) {
        mCountryList.add(country);
        mAdapter.notifyItemInserted(mCountryList.size() - 1);
    }

    private void init() {
        mInteractor = new CountriesInteractor();
        mPresenter = new CountriesPresenter(this, mInteractor);
//        mPresenter.getAllCountries();
//        mPresenter.getCountry("Spain");
        mPresenter.getAllCountriesOneByOne();
    }
}