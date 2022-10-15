package com.rodix.lab3.acvities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.rodix.lab3.InfoInterestingPlaceDialog;
import com.rodix.lab3.R;
import com.rodix.lab3.adapter.GraphhopperPlacesAdapter;
import com.rodix.lab3.adapter.InterestingPlacesAdapter;
import com.rodix.lab3.model.ModelGeoPlace;
import com.rodix.lab3.model.ModelInterestingPlace;
import com.rodix.lab3.model.ModelWeatherPlace;
import com.rodix.lab3.service.RetrofitGraphhopperService;
import com.rodix.lab3.service.RetrofitOpentripmapService;
import com.rodix.lab3.service.RetrofitOpenweathermapService;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

public class PlaceInfoActivity extends AppCompatActivity {
    TextView nameView, stateView, tempView, windView, visibilityView, humidityView, pressureView, descriptionView;
    RecyclerView interestingPlaceView;
    InterestingPlacesAdapter interestingPlacesAdapter;
    ModelGeoPlace place;
    RetrofitOpenweathermapService openweathermapService;
    RetrofitOpentripmapService opentripmapService;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.placeinfo_activity);

        nameView = findViewById(R.id.name_place_activity);
        stateView = findViewById(R.id.state_view);
        tempView = findViewById(R.id.temp);
        windView = findViewById(R.id.wind);
        visibilityView = findViewById(R.id.visibility);
        humidityView = findViewById(R.id.humidity);
        pressureView = findViewById(R.id.pressure);
        descriptionView = findViewById(R.id.description);

        interestingPlaceView = findViewById(R.id.view_intr_place);

        Intent i = getIntent();
        place = (ModelGeoPlace) i.getSerializableExtra("place");
        viewPlace(place);

        openweathermapService = RetrofitOpenweathermapService.getInstance();
        opentripmapService = RetrofitOpentripmapService.getInstance();

        openweathermapService
                .getWeatherPlace(place.getLat(), place.getLng())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::viewWeatherPlace);

        opentripmapService
                .getListInterestingPlacesAroundPlace(place)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<List<ModelInterestingPlace>>() {
                    @Override
                    public void call(List<ModelInterestingPlace> mainInfos) {
                        interestingPlacesAdapter.setPlaces(mainInfos);
                        interestingPlacesAdapter.notifyDataSetChanged();
                    }
                });

        interestingPlacesAdapter = new InterestingPlacesAdapter();

        interestingPlaceView.setAdapter(interestingPlacesAdapter);
        interestingPlaceView.setLayoutManager(new LinearLayoutManager(this));
        interestingPlacesAdapter.setPlaces(new ArrayList<>());

        interestingPlacesAdapter.getmViewClickSubject().observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<ModelInterestingPlace>() {
            @Override
            public void call(ModelInterestingPlace place) {
              opentripmapService.getDescriptionInterestingPlace(place).subscribe(new Action1<ModelInterestingPlace>() {
                  @Override
                  public void call(ModelInterestingPlace modelInterestingPlace) {
                      InfoInterestingPlaceDialog dialog = new InfoInterestingPlaceDialog(modelInterestingPlace);
                      dialog.display(getSupportFragmentManager());
                  }
              });

            }
        });
    }

    void viewPlace(ModelGeoPlace place) {
        nameView.setText(place.getName());
        stateView.setText(place.getState());
    }

    void viewWeatherPlace(ModelWeatherPlace weatherPlace) {
        NumberFormat nm = NumberFormat.getNumberInstance();
        tempView.setText(nm.format(weatherPlace.getTemperature()));
        windView.setText(nm.format(weatherPlace.getWind().getSpeed()));
        visibilityView.setText(nm.format(weatherPlace.getVisibility()));
        humidityView.setText(nm.format(weatherPlace.getHumidity()));
        pressureView.setText(nm.format(weatherPlace.getPressure()));
        descriptionView.setText(weatherPlace.getDescription());

    }
}
