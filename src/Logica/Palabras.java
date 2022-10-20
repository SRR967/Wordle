package Logica;

import java.io.*;
import java.util.*;

public class Palabras {

    private final ArrayList<String> posiblesPalabras = new ArrayList<>();
    private String nombreArchivo;
    private Scanner scanner= new Scanner(System.in);
    private int dificultad;

    public Palabras() throws IOException {
        System.out.println("Escoja la dificultad: \n 1:Fácil \n 2: Intermedio \n 3:Dificil");
        this.dificultad=scanner.nextInt();

        switch (dificultad) {
            case 1:
            nombreArchivo = "cuatroCaracteres.txt";
            scanner = new Scanner(new File("Wordle/ArchivosPalabras/" + nombreArchivo));

            leerPalabrasArchivo();
            break;
            case 2:
                nombreArchivo = "cincoCaracteres.txt";
                scanner = new Scanner(new File("Wordle/ArchivosPalabras/" + nombreArchivo));

                leerPalabrasArchivo();
                break;
            case 3:
                nombreArchivo = "seisCaracteres.txt";
                scanner = new Scanner(new File("Wordle/ArchivosPalabras/" + nombreArchivo));

                leerPalabrasArchivo();
                break;
            default:
                System.out.println("Por favor digite un numero del 1 al 3");
                break;
        }
    }





    public void leerPalabrasArchivo(){
        while(scanner.hasNext()){
            posiblesPalabras.add(scanner.next());
        }
        scanner.close();

        System.out.println(posiblesPalabras);

        posiblesPalabras.sort(String.CASE_INSENSITIVE_ORDER);
        System.out.println(posiblesPalabras);

    }

    public int buscarPalabraEnLista(String palabra, int inicio, int fin){
        int mitad = (inicio + fin) / 2;

        if(inicio > fin){
            return -1;
        }else if (palabra.equalsIgnoreCase(posiblesPalabras.get(mitad))){
            return 1;
        }
        if(palabra.compareToIgnoreCase(posiblesPalabras.get(mitad)) > 0 ){
            //System.out.println(palabra.compareToIgnoreCase(posiblesPalabras.get(mitad)));
            return buscarPalabraEnLista(palabra, mitad + 1, fin);
        }
        else{
            //System.out.println(palabra.compareToIgnoreCase(posiblesPalabras.get(mitad)));
            return buscarPalabraEnLista(palabra, inicio, mitad - 1);
        }


    }

    public void agregarPalabra(String palabra){

        if(buscarPalabraEnLista(palabra, 0 , posiblesPalabras.size()-1) == -1){
            posiblesPalabras.add(palabra);
            posiblesPalabras.sort(String.CASE_INSENSITIVE_ORDER);


                try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("ArchivosPalabras/" + nombreArchivo))) {
                    for (String posiblesPalabra : posiblesPalabras) {
                        String palabraLista;
                        palabraLista = posiblesPalabra;

                        bufferedWriter.write(palabraLista);
                        bufferedWriter.newLine();
                        bufferedWriter.flush();
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
        else{
            System.out.println("La palabra ya existe");
        }
        System.out.println(posiblesPalabras);
    }

    public String escogerPalabraAleatoria(){

        return posiblesPalabras.get((int)(Math.random() * posiblesPalabras.size()));

    }

    public ArrayList<String> verificarPalabra(String palabraDada, String palabraIngresada){

        ArrayList<String> posicion = new ArrayList<String>();
        ciclo:
        for (int i=0; i<palabraDada.length(); i++){
            for(int j=0; j<palabraIngresada.length(); j++){
                if(palabraIngresada.charAt(j) == palabraDada.charAt(i) ){
                    String pos = String.valueOf(palabraIngresada.charAt(j)).concat(" = "+ j);
                    posicion.add(pos);
                    continue ciclo;
                }
            }
        }
        return posicion;
    }
}
