package Logica;

import java.util.ArrayList;
import java.util.Objects;

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
    }

    public void comprobarUsuario(String nombreUsuario){
        if (listaUsuarios.size() == 0){
            listaUsuarios.add(new Usuario(nombreUsuario,0));
        }
        else{
        for(int i = 0; i < listaUsuarios.size(); i++){
            if(!Objects.equals(nombreUsuario, listaUsuarios.get(i).getNombreUsuario())){
                System.out.println("AYUDA");
                listaUsuarios.add(new Usuario(nombreUsuario,0));
            }
            else{
                System.out.println("Usuario Encontrado");
                System.out.println(listaUsuarios.get(i).getPuntuacion());
                this.nombreUsuario = listaUsuarios.get(i).getNombreUsuario();
                this.puntuacion = listaUsuarios.get(i).getPuntuacion();
                }
            }
        }
    }

    public void actualizarPuntuacion(String nombreUsuario){
        for (int i=0; i< listaUsuarios.size(); i++){
            if(nombreUsuario == listaUsuarios.get(i).getNombreUsuario()){
                listaUsuarios.get(i).setPuntuacion(this.puntuacion);
                System.out.println(listaUsuarios.get(i).getPuntuacion());
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
