package com.rafagarcia.rxjavaplayground.countries;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.rafagarcia.rxjavaplayground.R;
import com.rafagarcia.rxjavaplayground.model.Country;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.List;

/**
 * Created by rafagarcia on 29/05/2016.
 */
public class CountriesAdapter extends RecyclerView.Adapter<CountriesAdapter.CountryViewHolder> {

    private static final String COUNTRY_POSITION = "country_position";
    private List<Country> mCountries;
    private Context mContext;

    public CountriesAdapter(List<Country> countries, Context context) {
        this.mCountries = countries;
        this.mContext = context;
    }

    @Override
    public CountryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.country_card, parent, false);
        CountryViewHolder countryViewHolder = new CountryViewHolder(view);
        return countryViewHolder;
    }

    @Override
    public void onBindViewHolder(final CountryViewHolder holder, final int position) {
        final Country country = mCountries.get(position);
        holder.countryName.setText(country.getName());
        DecimalFormat formatter = new DecimalFormat("#,###");
        double populationDouble = Double.parseDouble(country.getPopulation());
        String populationFormatted = formatter.format(populationDouble);
        holder.countryPopulation.setText(mContext.getResources().getString(R.string.population) + populationFormatted);
        holder.countryRegion.setText(mContext.getResources().getString(R.string.region) + country.getRegion());

        Picasso.with(mContext)
                .load(country.getFlagUrl())
                .into(holder.countryFlag);
    }

    @Override
    public int getItemCount() {
        return mCountries.size();
    }

    public static class CountryViewHolder extends RecyclerView.ViewHolder {
        View rootView;
        ImageView countryFlag;
        TextView countryName;
        TextView countryRegion;
        TextView countryPopulation;

        public CountryViewHolder(View itemView) {
            super(itemView);
            rootView = itemView;
            countryFlag = (ImageView)itemView.findViewById(R.id.flagImageView);
            countryName = (TextView)itemView.findViewById(R.id.nameTextView);
            countryRegion = (TextView)itemView.findViewById(R.id.regionTextView);
            countryPopulation = (TextView)itemView.findViewById(R.id.populationTextView);
        }
    }
}
