package com.example.gestordeberes;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Deber> deberes;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;

        });
        // Configurar el botón
        FloatingActionButton button = findViewById(R.id.btAdd);
        button.setOnClickListener(v -> {
            // Mostrar el diálogo personalizado
            DialogFragment dialogFragment = new DialogFragment();
            dialogFragment.show(getSupportFragmentManager(), "Dialogo");
        });
    }
}


//    Crear un dialogo
//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        builder.setTitle("Título")
//                .setMessage("Mensaje del diálogo")
//                .setPositiveButton("Aceptar", (dialog, which) -> {
//                    // Acción al aceptar
//                })
//                .setNegativeButton("Cancelar", (dialog, which) -> {
//                    // Acción al cancelar
//                })
//                .show();

//      Crear un calendario para establecer la fecha
//        DatePickerDialog datePickerDialog = new DatePickerDialog(
//                this,
//                (view, year, month, dayOfMonth) -> {
//                    // Acción con la fecha seleccionada
//                },
//                2024, 11, 15 // Año, Mes (0-indexado), Día
//        );
//        datePickerDialog.show();


//      Crear un reloj para establecer la hora
//        TimePickerDialog timePickerDialog = new TimePickerDialog(
//                this,
//                (view, hourOfDay, minute) -> {
//                    // Acción con la hora seleccionada
//                },
//                12, 0, true // Hora inicial, Minuto inicial, formato 24h
//        );
//        timePickerDialog.show();


//        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this);
//        bottomSheetDialog.setContentView(R.layout.dialogo_add_deberes);
//        bottomSheetDialog.show();


