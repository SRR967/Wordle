package Logica;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Locale;
import java.util.Scanner;

public class Tablero {

    private String palabraEscogida;
    private String palabraInput;

    private final int turnosPartida = 4;

    private boolean ganador = false;

    private final Scanner scanner = new Scanner(System.in);

    private Palabras palabras = new Palabras();
    private Usuario usuario;
    private Dom dom;


    public Tablero() throws IOException, ParserConfigurationException, TransformerException {
        palabras.elegirDificultad();
        crearUsuario();
        menuPrincipal();


    }


    public void crearUsuario(){
        String nombreAux;

        System.out.println("Ingrese su nombre de usuario: ");
        nombreAux = scanner.next();
        usuario = new Usuario(nombreAux);

        usuario.comprobarUsuario(nombreAux);


    }

    public void menuPrincipal() throws IOException, TransformerException, ParserConfigurationException {

        System.out.println("Por favor, escoja una opcion: \n" +
                "1. Agregar una nueva palabra a la base de datos \n" +
                "2. Cambiar Dificultad \n" +
                "3. Iniciar Juego \n" +
                "4. Crear Usuario");

        int opcionEscogida = scanner.nextInt();

        switch (opcionEscogida){
            case 1:
                System.out.println("Por favor ingrese la palabra que desea agregar:");
                String palabra = scanner.next();
                palabras.agregarPalabra(palabra);
                menuPrincipal();
                break;

            case 2:
                palabras.elegirDificultad();
                menuPrincipal();
                break;

            case 3:

                this.palabraInput = "";
                this.palabraEscogida = palabras.escogerPalabraAleatoria();
                controlJuego();



            default:
                menuPrincipal();

        }
    }

    public void compararPalabra(){

        StringBuilder resultado = new StringBuilder();

        if(palabraInput.equalsIgnoreCase(palabraEscogida)){
            ganador = true;
            System.out.println("Has ganado");
        }
        else {
            for (int j = 0; j < palabraInput.length(); j++) {

                if (palabraEscogida.contains(String.valueOf(Character.toUpperCase(palabraInput.charAt(j))))) {
                    resultado.append(palabraInput.toUpperCase().charAt(j)).append(" # ; ");
                    //resultado.replace(palabraInput.charAt(j),palabraInput.charAt(j),"#");

                } else {
                    resultado.append(palabraInput.toUpperCase().charAt(j)).append("✖ ; ");

                }
            }
            for (int i = 0; i < palabraEscogida.length(); i++) {

                //resultado.replace(0,1,"s");
                if (Character.toUpperCase(palabraEscogida.charAt(i)) == Character.toUpperCase(palabraInput.charAt(i))) {
                    for (int k=0; k<resultado.length(); k++){
                        if(resultado.charAt(k)== Character.toUpperCase(palabraInput.charAt(i))){
                            resultado.replace(k+1, k+3, "✓");
                        }
                    }
                    //resultado.append(palabraInput.charAt(i)).append("✓ ; ");

                }
            }

        }

            System.out.println(resultado);
        }


    public void pedirPalabra(){
        while(palabraInput.length() != palabraEscogida.length()){
            System.out.println("Por favor ingrese una palabra");
            palabraInput = scanner.next();
        }
    }



    public void controlJuego() throws IOException, TransformerException, ParserConfigurationException {

        ganador = false;

        for(int i = 0; i < turnosPartida; i++){
            pedirPalabra();
            compararPalabra();

            palabraInput = "";

            if(ganador){

                switch(palabras.getDificultad()){
                    case 1:
                        usuario.sumarPuntuacion(1);
                        System.out.println(usuario.getPuntuacion());
                        break;

                    case 2:
                        usuario.sumarPuntuacion(3);
                        System.out.println(usuario.getPuntuacion());
                        break;

                    case 3:
                        usuario.sumarPuntuacion(5);
                        System.out.println(usuario.getPuntuacion());
                        break;

                    default:
                        break;
                }

                break;
            }

            if(i == 3){
                System.out.println("Has fallado");
            }
        }
        this.dom = new Dom();
        System.out.println("Si se ejecuta");
        dom.generarDocumento();
        System.out.println("Si se ejecuta2");
        dom.generarXML();
        System.out.println("Si se ejecuta3");
    }

    public Usuario getUsuario() {
        return usuario;
    }
}


