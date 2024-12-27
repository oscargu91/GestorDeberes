package com.example.gestordeberes;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MiAdaptador extends RecyclerView.Adapter<MiAdaptador.ViewHolder> {

    // Lista de objetos Deber que contiene los datos a mostrar
    private final List<Deber> deberList;

    // Constructor que recibe la lista de datos
    public MiAdaptador(List<Deber> deberList) {
        this.deberList = deberList;
    }

    // Clase ViewHolder que contiene las vistas de cada item
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // Referencias a los TextViews del layout del item
        TextView tvAsignatura, tvDescripcion, tvFecha, tvHora;

        public ViewHolder(View itemView) {
            super(itemView);
            // Encuentra las vistas dentro del layout del item
            tvAsignatura = itemView.findViewById(R.id.tvTitulo); // Asignatura
            tvDescripcion = itemView.findViewById(R.id.tvDescripcion);
            tvFecha = itemView.findViewById(R.id.tvFecha);
            tvHora = itemView.findViewById(R.id.tvHora);
        }
    }

    // Este método se llama para crear una nueva vista (ViewHolder) para cada item
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Infla el layout para cada item
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new ViewHolder(view);
    }

    // Este método enlaza los datos del Deber con las vistas correspondientes
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // Obtiene el objeto Deber correspondiente a la posición
        Deber deberActual = deberList.get(position);

        // Asigna los valores de los objetos Deber a los TextViews
        holder.tvAsignatura.setText(deberActual.getAsignatura());
        holder.tvDescripcion.setText(deberActual.getDescripcion());
        holder.tvFecha.setText(deberActual.getFechaEntrega());
        holder.tvHora.setText(deberActual.getHora());
    }

    // Este método devuelve el número total de items en la lista
    @Override
    public int getItemCount() {
        return deberList.size(); // Devuelve el tamaño de la lista de datos
    }
    // Método para agregar un nuevo deber y notificar al adapter
    public void agregarDeber(Deber deber) {
        deberList.add(deber);
        notifyItemInserted(deberList.size() - 1);
    }
}
