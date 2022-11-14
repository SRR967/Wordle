package Logica;

import java.util.ArrayList;

public class Usuario {
    private String nombreUsuario;
    private ArrayList<String> listaUsuarios = new ArrayList<>();
    private int puntuacion;

    public Usuario(String nombreUsuario, int puntuacion) {
        this.nombreUsuario = nombreUsuario;
        this.puntuacion = puntuacion;
    }

    public Usuario(String nombreUsuario){
        this.nombreUsuario= nombreUsuario;
        comprobarUsuario(nombreUsuario);
    }

    public void comprobarUsuario(String nombreUsuario){
        for(int i = 0; i < listaUsuarios.size(); i++){
            if(nombreUsuario != listaUsuarios.get(i)){
                listaUsuarios.add(nombreUsuario);
            }
        }
    }

    public void sumarPuntuacion(int numSuma){
        this.puntuacion += numSuma;
    }

    public int getPuntuacion() {
        return puntuacion;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }
}
