package com.example.listycitylab3;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

public class EditCityFragment extends DialogFragment {
    interface EditCityDialogListener {
        void EditCity(City oldCity, City newCity, int position);
    }
    private EditCityDialogListener listener;
    private int position;



    public static EditCityFragment newInstance(City city,int position) {
        Bundle args = new Bundle();
        args.putSerializable("city", city);
        args.putInt("position", position);
        EditCityFragment fragment = new EditCityFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        listener = (EditCityDialogListener) context;


    }
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_add_city, null); //'loads in' the add city fragment, from the on the current fragment using getContext()
        EditText editCityText = view.findViewById(R.id.edit_text_city_text);
        EditText editProvinceText = view.findViewById(R.id.edit_text_province_text);
        position = getArguments().getInt("position");
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        return builder
                .setView(view)
                .setTitle("Edit city")
                .setNegativeButton("Cancel", null)//null means there is no click listener; no operations have to follow after the button is click (the fragment is closed)
                .setPositiveButton("Make changes", (dialog, which) -> {
                    String editCityName = editCityText.getText().toString();
                    String editProvinceName = editProvinceText.getText().toString();
                    City oldCity = (City) getArguments().getSerializable("city");
                    City updateCity = new City(editCityName, editProvinceName);
                    listener.EditCity(oldCity, updateCity, position);
                })
                .create();
    }
}
