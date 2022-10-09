package Logica;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Palabras {

    private final ArrayList<String> posiblesPalabras = new ArrayList<>();
    private String nombreArchivo;
    private final Scanner scanner;


    public Palabras() throws FileNotFoundException {
        nombreArchivo = "cuatroCaracteres.txt";
        scanner = new Scanner(new File("ArchivosPalabras/cuatroCaracteres.txt"));

        leerPalabrasArchivo();
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
        //System.out.println(buscarPalabraEnLista(palabra, 0, posiblesPalabras.size() - 1));

        if(buscarPalabraEnLista(palabra, 0 , posiblesPalabras.size()-1) == -1){
            posiblesPalabras.add(palabra);
            posiblesPalabras.sort(String.CASE_INSENSITIVE_ORDER);
            //@TODO Agregar funcion para grabar la informacion en el txt.
        }
        else{
            System.out.println("La palabra ya existe");
        }
        System.out.println(posiblesPalabras);
    }
}
