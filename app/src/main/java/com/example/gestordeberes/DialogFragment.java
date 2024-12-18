package com.example.gestordeberes;


import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.lifecycle.ViewTreeViewModelKt;

public class DialogFragment extends androidx.fragment.app.DialogFragment {


    private EditText descripcion;
    private EditText fecha;
    private Spinner spinner;
    private EditText hora;



    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater layoutInflater = requireActivity().getLayoutInflater();
       /* View view = */

        View view = layoutInflater.inflate(R.layout.dialogo_add_deberes, null);
        descripcion= view.findViewById(R.id.editDescripcion);
        fecha=view.findViewById(R.id.editFecha);
        hora=view.findViewById(R.id.editHora);

        fecha.setOnClickListener(v -> showDatePickerDialog());



        builder.setView(layoutInflater.inflate(R.layout.dialogo_add_deberes, null));
        builder.setView(view);

       /* // Configurar botones
        builder.setPositiveButton("Aceptar", (dialog, which) -> {
           //Meter logica de cuando pulsamos aceptar

        });

        builder.setNegativeButton("Cancelar", (dialog, which) -> {

            dialog.dismiss();
        });*/

        // Crear y devolver el diálogo
        return builder.create();



    }

    public void showDatePickerDialog(){
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                getActivity(),
                (view, year, month, dayOfMonth) -> {
                    String selectedDate = dayOfMonth + "/" + (month + 1) + "/" + year;
                    fecha.setText(selectedDate);
                },
                2024, 11, 15 // Año, Mes (0-indexado), Día
        );
        datePickerDialog.show();

    }
}



