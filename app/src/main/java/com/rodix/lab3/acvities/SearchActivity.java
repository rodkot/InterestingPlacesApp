package com.rodix.lab3.acvities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.rodix.lab3.adapter.GraphhopperPlacesAdapter;
import com.rodix.lab3.model.ModelGeoPlace;
import com.rodix.lab3.R;
import com.rodix.lab3.service.RetrofitGraphhopperService;

import java.util.ArrayList;
import java.util.List;

import rx.android.schedulers.AndroidSchedulers;

public class SearchActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    GraphhopperPlacesAdapter graphhopperAdapter;
    Button searchButton;
    EditText searchEdit;
    RetrofitGraphhopperService retrofitGraphhopperService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_activity_main);

        recyclerView = findViewById(R.id.view_places);
        searchButton = findViewById(R.id.search);
        searchEdit = findViewById(R.id.name);

        graphhopperAdapter = new GraphhopperPlacesAdapter();
        RetrofitGraphhopperService graphhopperService = RetrofitGraphhopperService.getInstance();
        recyclerView.setAdapter(graphhopperAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        graphhopperAdapter.setPlaces(new ArrayList<>());
        graphhopperAdapter.getPositionClicks().subscribe(this::transitionActivityPlaceInfo);

        searchButton.setOnClickListener(view -> graphhopperService.getListPlace(searchEdit.getText().toString(), searchEdit.getTextLocale().getDisplayLanguage())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(SearchActivity.this::change));


        //    ArrayList<ModelGeoPlace.Place> places = new ArrayList<>();
        //   places.add(new ModelGeoPlace.Place(new ModelGeoPlace.Place.Point(1, 2), "Russia", "Novosibirsk", "NSU", "Good", "630054"));
        //   graphhopperAdapter.setPlaces(places);

        //  graphhopperService.getListPlace("Berlin", "en").observeOn(AndroidSchedulers.mainThread()).subscribe(this::change);
    }

    private void change(List<ModelGeoPlace> places) {
        graphhopperAdapter.setPlaces(places);
        graphhopperAdapter.notifyDataSetChanged();
    }

    private void transitionActivityPlaceInfo(ModelGeoPlace place){
        Intent intent = new Intent(this,PlaceInfoActivity.class);
        intent.putExtra("place",  place);
        startActivity(intent);
    }
}