package com.rodix.lab3.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.rodix.lab3.R;
import com.rodix.lab3.model.ModelInterestingPlace;

import java.util.List;

import rx.subjects.PublishSubject;

public class InterestingPlacesAdapter extends RecyclerView.Adapter<InterestingPlacesAdapter.RecyclerViewAdapter> {
    private List<ModelInterestingPlace> places;
    private final PublishSubject<ModelInterestingPlace> mViewClickSubject = PublishSubject.create();

    public InterestingPlacesAdapter() {
    }

    public List<ModelInterestingPlace> getPlaces() {
        return places;
    }

    public PublishSubject<ModelInterestingPlace> getmViewClickSubject() {
        return mViewClickSubject;
    }

    public void setPlaces(List<ModelInterestingPlace> places) {
        this.places = places;
    }

    @NonNull
    @Override
    public InterestingPlacesAdapter.RecyclerViewAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.interesting_place_item, parent, false);
        return new InterestingPlacesAdapter.RecyclerViewAdapter(view);
    }

    @Override
    public void onBindViewHolder(@NonNull InterestingPlacesAdapter.RecyclerViewAdapter holder, int position) {
        ModelInterestingPlace place = places.get(position);
        holder.nameView.setText(place.getName());
        if (place.getName().isEmpty())
            holder.nameView.setText(place.getXid());
        holder.cardView.setOnClickListener(view -> mViewClickSubject.onNext(place));
    }

    @Override
    public int getItemCount() {
        return places.size();
    }

    public static class RecyclerViewAdapter extends RecyclerView.ViewHolder {
        TextView nameView;
        CardView cardView;

        public RecyclerViewAdapter(@NonNull View itemView) {
            super(itemView);
            nameView = itemView.findViewById(R.id.name_interesting_place);
            cardView = itemView.findViewById(R.id.card_interesting_place);
        }
    }
}