package Logica;

import java.io.IOException;
import java.util.Scanner;

public class Tablero {

    private final String palabraEscogida;
    private String palabraInput;

    private final int turnosPartida;

    private boolean ganador = false;

    private final Scanner scanner = new Scanner(System.in);

    private Palabras palabras = new Palabras();


    public Tablero() throws IOException {
        turnosPartida = 4;
        this.palabraInput = "";
        this.palabraEscogida = palabras.escogerPalabraAleatoria();

        controlJuego();
    }

    public void compararPalabra(){

        StringBuilder resultado = new StringBuilder();

        if(palabraInput.equalsIgnoreCase(palabraEscogida)){
            ganador = true;
            System.out.println("Has ganado");
        }
        else{
            for(int i = 0; i < palabraEscogida.length(); i++){
                if(Character.toUpperCase(palabraEscogida.charAt(i)) == Character.toUpperCase(palabraInput.charAt(i))){
                    resultado.append(palabraInput.charAt(i)).append("✓ ; ");
                }
                else{
                    resultado.append(palabraInput.charAt(i)).append("✖ ; ");
                }
            }

            System.out.println(resultado);
        }
    }

    public void pedirPalabra(){
        while(palabraInput.length() != palabraEscogida.length()){
            System.out.println("Por favor ingrese una palabra");
            palabraInput = scanner.next();
        }
    }

    public void controlJuego(){
        for(int i = 0; i < turnosPartida; i++){
            pedirPalabra();
            compararPalabra();
            palabraInput = "";

            if(ganador){
                break;
            }

            if(i == 3){
                System.out.println("Has fallado");
            }
        }
    }

}


