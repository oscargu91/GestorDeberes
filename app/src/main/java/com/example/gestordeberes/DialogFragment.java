package com.example.gestordeberes;


import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
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
    private Button btnGuardar;


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
        btnGuardar = view.findViewById(R.id.btGuardar);

        fecha.setOnClickListener(v -> showDatePickerDialog());
        hora.setOnClickListener(v -> showTimePickerDialog());

        //Instanciar el spinner
        spinner = view.findViewById(R.id.idTarea);

        //Guardar los items en arrayList
        String[] items = {"PMDM", "PSP", "AD", "EIE","DI"};

        //Crear el adapter
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                getContext(),
                android.R.layout.simple_spinner_item, // Layout por defecto para cada item
                items
        );

        // Layout desplegable
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        //Asignar el Adapter al Spinner
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = parent.getItemAtPosition(position).toString();
                Toast.makeText(requireContext(), "Seleccionaste: " + selectedItem, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // No se seleccionó nada
            }
        });


        //Configurar el botón Guardar
        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Recoger los datos
                String tareaSeleccionada = spinner.getSelectedItem().toString();
                String datosDescripcion = descripcion.getText().toString();
                String datosFecha = fecha.getText().toString();
                String datosHora = hora.getText().toString();

                // Crear un Intent para pasar los datos
                Intent intent = new Intent(requireContext(), MainActivity.class);
                intent.putExtra("tarea", tareaSeleccionada);
                intent.putExtra("descripcion", datosDescripcion);
                intent.putExtra("fecha", datosFecha);
                intent.putExtra("hora", datosHora);

                // Ir a la segunda actividad
                startActivity(intent);
            }
        });



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



