package com.example.gestordeberes;


import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
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

        //Creo la view y la inflo con el dialogo.
        View view = layoutInflater.inflate(R.layout.dialogo_add_deberes, null);

        descripcion= view.findViewById(R.id.editDescripcion);
        fecha=view.findViewById(R.id.editFecha);
        hora=view.findViewById(R.id.editHora);

        fecha.setOnClickListener(v -> showDatePickerDialog());
        hora.setOnClickListener(v -> showTimePickerDialog());


        builder.setView(layoutInflater.inflate(R.layout.dialogo_add_deberes, null));
        builder.setView(view);

        return builder.create();

       /* // Configurar botones predefinidos del dialogo
        builder.setPositiveButton("Aceptar", (dialog, which) -> {
           //Meter logica de cuando pulsamos aceptar
        });
        builder.setNegativeButton("Cancelar", (dialog, which) -> {

            dialog.dismiss();
        });*/
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

    //Método para crear un reloj para establecer la hora
    public void showTimePickerDialog() {
        TimePickerDialog timePickerDialog = new TimePickerDialog(
                getActivity(),
                (view, hourOfDay, minute) -> {
                    // Formatea la hora seleccionada y la muestra en el campo EditText
                    String selectedTime = String.format("%02d:%02d", hourOfDay, minute);
                    hora.setText(selectedTime);
                },
                12, 0, true // Hora inicial, Minuto inicial, formato 24h
        );
        timePickerDialog.show();
    }

}



