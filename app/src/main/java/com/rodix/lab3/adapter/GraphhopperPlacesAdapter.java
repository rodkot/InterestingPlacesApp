package com.rodix.lab3.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;


import com.rodix.lab3.R;
import com.rodix.lab3.model.ModelGeoPlace;

import java.util.List;


import rx.Observable;
import rx.subjects.PublishSubject;


public class GraphhopperPlacesAdapter extends RecyclerView.Adapter<GraphhopperPlacesAdapter.RecyclerViewAdapter> {
    private List<ModelGeoPlace> places;
    private final PublishSubject<ModelGeoPlace> mViewClickSubject = PublishSubject.create();

    public GraphhopperPlacesAdapter() {
    }

    public List<ModelGeoPlace> getPlaces() {
        return places;
    }

    public void setPlaces(List<ModelGeoPlace> places) {
        this.places = places;
    }


    @NonNull
    @Override
    public RecyclerViewAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.place_item, parent, false);
        return new RecyclerViewAdapter(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter holder, int position) {
        ModelGeoPlace place = places.get(position);
        holder.nameView.setText(place.getName());
        holder.countryView.setText(place.getCountry());
        holder.cityView.setText(place.getCity());
        holder.stateView.setText(place.getState());
        holder.postCodeView.setText(place.getPostCode());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mViewClickSubject.onNext(place);
            }
        });
    }

    @Override
    public int getItemCount() {
        return places.size();
    }

    public Observable<ModelGeoPlace> getPositionClicks(){
        return mViewClickSubject.asObservable();
    }

    public static class RecyclerViewAdapter extends RecyclerView.ViewHolder {
        TextView nameView, countryView, stateView, cityView, postCodeView;
        CardView cardView;
        RelativeLayout relativeLayout;

        public RecyclerViewAdapter(@NonNull View itemView) {
            super(itemView);
            relativeLayout = itemView.findViewById(R.id.view);
            nameView = itemView.findViewById(R.id.name_place);
            countryView = itemView.findViewById(R.id.country);
            cityView = itemView.findViewById(R.id.city);
            stateView = itemView.findViewById(R.id.state);
            postCodeView = itemView.findViewById(R.id.post_code);
            cardView = itemView.findViewById(R.id.card_place);
        }
    }
}
