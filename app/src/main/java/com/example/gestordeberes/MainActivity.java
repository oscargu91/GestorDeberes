package com.example.gestordeberes;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
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
                Deber tareaEditada = result.getParcelable("deberParcelable");


                if (tareaEditada != null) {
                    // Verificar si es una tarea nueva o editada
                    if (!deberes.contains(tareaEditada)) {
                        // Si no está en la lista, agregarla como una tarea nueva
                        deberes.add(tareaEditada);
                        adapter.notifyItemInserted(deberes.size() - 1); // Notificar al adaptador que se ha insertado un nuevo item
                    } else {
                        // Si la tarea ya existe, actualizarla
                        actualizarTareaEnLista(tareaEditada);
                    }

                    Log.d("MainActivity", "Tarea agregada o editada: " + tareaEditada.getAsignatura());
                } else {
                    Toast.makeText(this, "No se pudo agregar o editar la tarea. Datos incompletos.", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
    public void actualizarTareaEnLista(Deber tareaEditada) {
        for (int i = 0; i < deberes.size(); i++) {
            Deber deber = deberes.get(i);
            // Verificar si el ID o cualquier atributo único es el mismo (suponiendo que tienes un ID o atributo único en Deber)
            if (deber.getDescripcion().equals(tareaEditada.getDescripcion())) {
                deberes.set(i, tareaEditada); // Reemplazar la tarea antigua por la editada
                adapter.notifyItemChanged(i); // Notificar al adaptador que se ha actualizado el item
                break;
            }
        }
    }
}
