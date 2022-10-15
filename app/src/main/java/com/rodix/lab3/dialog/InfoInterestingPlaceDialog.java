package com.rodix.lab3.dialog;


import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;

import com.rodix.lab3.R;
import com.rodix.lab3.model.ModelInterestingPlace;
import com.squareup.picasso.Picasso;

public class InfoInterestingPlaceDialog extends DialogFragment {
    public static final String TAG = "InfoInterestingPlaceDialog";

    private Toolbar toolbar;
    private TextView nameView, descriptionView;
    private ImageView imagePlace;
    private Button wikipediaButton;

    private final ModelInterestingPlace modelInterestingPlace;

    public void display(FragmentManager fragmentManager) {
        this.show(fragmentManager, TAG);
    }

    public InfoInterestingPlaceDialog(ModelInterestingPlace modelInterestingPlace) {
        this.modelInterestingPlace = modelInterestingPlace;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, R.style.AppTheme_FullScreenDialog);
    }

    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null) {
            int width = ViewGroup.LayoutParams.MATCH_PARENT;
            int height = ViewGroup.LayoutParams.MATCH_PARENT;
            dialog.getWindow().setLayout(width, height);
            dialog.getWindow().setWindowAnimations(R.style.AppTheme_Slide);
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.info_place_dialog, container, false);
        toolbar = view.findViewById(R.id.toolbar);
        nameView = view.findViewById(R.id.name_place_dialog);
        descriptionView = view.findViewById(R.id.text_description_dialog);
        imagePlace = view.findViewById(R.id.image_place);
        wikipediaButton = view.findViewById(R.id.button_wikipedia);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        nameView.setText(modelInterestingPlace.getName());
        descriptionView.setText(modelInterestingPlace.getDescription().getDescr());
        if (modelInterestingPlace.getDescription().getPhoto().getSource() != null) {
            ModelInterestingPlace.Description.Photo photo = modelInterestingPlace.getDescription().getPhoto();
            Picasso.with(getContext()).load(photo.getSource()).into(imagePlace);
        }
        wikipediaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(modelInterestingPlace.getDescription().getWikipedia()));
                startActivity(browserIntent);
            }
        });
        toolbar.setNavigationOnClickListener(v -> dismiss());
        toolbar.setTitle("Описание");
        toolbar.setOnMenuItemClickListener(item -> {
            dismiss();
            return true;
        });
    }
}
