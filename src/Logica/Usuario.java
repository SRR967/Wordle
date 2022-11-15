package Logica;

import java.util.ArrayList;

public class Usuario {
    private String nombreUsuario;
    private ArrayList<Usuario> listaUsuarios = new ArrayList<>();
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
        if (listaUsuarios.size() == 0){
            listaUsuarios.add(new Usuario(nombreUsuario,0));
        }
        for(int i = 0; i < listaUsuarios.size(); i++){
            if(nombreUsuario != listaUsuarios.get(i).getNombreUsuario()){
                listaUsuarios.add(new Usuario(nombreUsuario,0));
            }
        }
    }

    public void actualizarPuntuacion(String nombreUsuario){
        for (int i=0; i< listaUsuarios.size(); i++){
            if(nombreUsuario == listaUsuarios.get(i).getNombreUsuario()){
                listaUsuarios.get(i).setPuntuacion(this.puntuacion);
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

    public ArrayList<Usuario> getListaUsuarios() {
        return listaUsuarios;
    }

    public void setPuntuacion(int puntuacion) {
        this.puntuacion = puntuacion;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }
}
