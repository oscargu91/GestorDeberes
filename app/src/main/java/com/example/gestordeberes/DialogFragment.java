package com.example.gestordeberes;


import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;

public class DialogFragment extends androidx.fragment.app.DialogFragment {

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater layoutInflater = requireActivity().getLayoutInflater();
        builder.setView(layoutInflater.inflate(R.layout.dialogo_add_deberes, null));

        // Configurar botones
        builder.setPositiveButton("Aceptar", (dialog, which) -> {
           //Meter logica de cuando pulsamos aceptar

        });

        builder.setNegativeButton("Cancelar", (dialog, which) -> {

            dialog.dismiss();
        });

        // Crear y devolver el diálogo
        return builder.create();



    }
}



