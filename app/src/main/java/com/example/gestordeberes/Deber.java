package com.example.gestordeberes;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Deber implements Parcelable {

    private int id;
    private String asignatura;
    private String descripcion;
    private String fechaEntrega;
    private String hora;
    private Boolean estadoTarea;

    public Deber(String asignatura, String descripcion, String fechaEntrega, String hora, Boolean estadoTarea) {

        this.asignatura = asignatura;
        this.descripcion = descripcion;
        this.fechaEntrega = fechaEntrega;
        this.hora = hora;
        this.estadoTarea = estadoTarea;
    }
    public Deber(int id,String asignatura, String descripcion, String fechaEntrega, String hora, Boolean estadoTarea) {
        this.id= id;
        this.asignatura = asignatura;
        this.descripcion = descripcion;
        this.fechaEntrega = fechaEntrega;
        this.hora = hora;
        this.estadoTarea = estadoTarea;
    }

    protected Deber(Parcel in) {
        id = in.readInt();
        asignatura = in.readString();
        descripcion = in.readString();
        fechaEntrega = in.readString();
        hora = in.readString();
        byte tmpEstadoTarea = in.readByte();
        estadoTarea = tmpEstadoTarea == 0 ? null : tmpEstadoTarea == 1;
    }

    public static final Creator<Deber> CREATOR = new Creator<Deber>() {
        @Override
        public Deber createFromParcel(Parcel in) {
            return new Deber(in);
        }

        @Override
        public Deber[] newArray(int size) {
            return new Deber[size];
        }
    };

    public int getId(){
        return id;
    }

    public int setId(){
        return id;
    }
    public String getAsignatura() {
        return asignatura;
    }

    public void setAsignatura(String asignatura) {
        this.asignatura = asignatura;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(String fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public Boolean getEstadoTarea() {
        return estadoTarea;
    }

    public void setEstadoTarea(Boolean estadoTarea) {
        this.estadoTarea = estadoTarea;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(asignatura);
        dest.writeString(descripcion);
        dest.writeString(fechaEntrega);
        dest.writeString(hora);
        dest.writeByte((byte) (estadoTarea == null ? 0 : estadoTarea ? 1 : 2));
    }
}
