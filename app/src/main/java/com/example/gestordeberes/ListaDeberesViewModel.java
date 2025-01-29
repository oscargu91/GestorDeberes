package com.example.gestordeberes;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class ListaDeberesViewModel extends ViewModel {

    private MutableLiveData<List<Deber>> lista = new MutableLiveData<>();

    public ListaDeberesViewModel() {
        lista.setValue(new ArrayList<>()); // Inicializar la lista para evitar NullPointerException
    }

    @Override
    public String toString() {
        return "ListaDeberes{" +
                "lista=" + lista +
                '}';
    }

    public MutableLiveData<List<Deber>> getLista() {
        return lista;
    }

    public void setLista(MutableLiveData<List<Deber>> lista) {
        this.lista = lista;
    }

    public void addDeber(Deber deber) {
        if (deber != null) {
            List<Deber> temporal = lista.getValue();
            if (temporal == null) {
                temporal = new ArrayList<>();
            }
            temporal.add(deber);
            lista.setValue(temporal);
        }
    }

    public void borrarDeber(Deber deber) {
        List<Deber> temporal = lista.getValue();
        if (temporal != null && deber != null) {
            temporal.remove(deber);
            lista.setValue(temporal);
        }
    }
}
