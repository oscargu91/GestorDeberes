package com.example.gestordeberes;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;

public class DialogFragment extends androidx.fragment.app.DialogFragment {

    private EditText descripcion;
    private EditText fecha;
    private EditText hora;
    private Spinner spinner;
    private Button btnGuardar;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialogo_add_deberes, null);

        // Inicializar componentes
        descripcion = view.findViewById(R.id.editDescripcion);
        fecha = view.findViewById(R.id.editFecha);
        hora = view.findViewById(R.id.editHora);
        spinner = view.findViewById(R.id.idTarea);
        btnGuardar = view.findViewById(R.id.btGuardar);

        // Configurar spinner
        String[] items = {"PMDM", "PSP", "AD", "EIE", "DI"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                requireContext(),
                android.R.layout.simple_spinner_item,
                items
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        // Eventos
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(requireContext(), "Seleccionaste: " + parent.getItemAtPosition(position), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Nada seleccionado
            }
        });

        fecha.setOnClickListener(v -> showDatePickerDialog());
        hora.setOnClickListener(v -> showTimePickerDialog());

        btnGuardar.setOnClickListener(v -> {
            String asignatura = spinner.getSelectedItem().toString();
            String descripcionTarea = descripcion.getText().toString().trim();
            String fechaEntrega = fecha.getText().toString().trim();
            String horaEntrega = hora.getText().toString().trim();

            if (descripcionTarea.isEmpty() || fechaEntrega.isEmpty() || horaEntrega.isEmpty()) {
                Toast.makeText(requireContext(), "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show();
                return;
            }

            // Crear objeto Deber (implementa Parcelable)
            Deber nuevoDeber = new Deber(asignatura, descripcionTarea, fechaEntrega, horaEntrega, false);

            // Enviar los datos al fragmento o actividad llamante usando Bundle
            Bundle result = new Bundle();
            result.putParcelable("deberParcelable", nuevoDeber);
            getParentFragmentManager().setFragmentResult("deberResult", result);

            dismiss();
        });

        builder.setView(view);
        return builder.create();
    }

    private void showDatePickerDialog() {
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                requireContext(),
                (view, year, month, dayOfMonth) -> fecha.setText(dayOfMonth + "/" + (month + 1) + "/" + year),
                2024, 11, 15
        );
        datePickerDialog.show();
    }

    private void showTimePickerDialog() {
        TimePickerDialog timePickerDialog = new TimePickerDialog(
                requireContext(),
                (view, hourOfDay, minute) -> hora.setText(String.format("%02d:%02d", hourOfDay, minute)),
                12, 0, true
        );
        timePickerDialog.show();
    }
}
