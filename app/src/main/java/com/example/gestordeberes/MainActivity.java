package com.example.gestordeberes;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Deber> deberes = new ArrayList<>();
    private MiAdaptador adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Configurar el botón de añadir (+)
        FloatingActionButton button = findViewById(R.id.btAdd);
        button.setOnClickListener(v -> {
            // Mostrar el diálogo para añadir deberes
            DialogFragment dialogFragment = new DialogFragment();
            //dialogFragment.setTargetFragment(null, 0);  // No es necesario establecer un targetFragment si no se usa
            dialogFragment.show(getSupportFragmentManager(), "Dialogo");
        });

        // Inicializar el RecyclerView
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Crear un nuevo adaptador si no existe
        if (adapter == null) {
            adapter = new MiAdaptador(deberes);
            recyclerView.setAdapter(adapter);
        }

        // Recibir los deberes desde el diálogo
        getSupportFragmentManager().setFragmentResultListener("deberResult", this, (requestKey, result) -> {
            if (result.containsKey("deberParcelable")) {
                Deber nuevoDeber = result.getParcelable("deberParcelable");
                if (nuevoDeber != null) {
                    adapter.agregarDeber(nuevoDeber);

                    Log.d("MainActivity", "Deber agregado: " + nuevoDeber.getAsignatura());
                } else {
                    Toast.makeText(this, "No se pudo agregar el deber. Datos incompletos.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
