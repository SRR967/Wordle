package Logica;

import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
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
    private Dom dom = new Dom();


    public Tablero() throws IOException {
        dom.imprimirXML(dom.getDocumento());
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

    public void menuPrincipal() throws FileNotFoundException {

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
                menuPrincipal();
                break;

            case 4:
                System.out.println("Digite! el nuevo nombre de usuario: ");
                String nombre = scanner.next();
                usuario.comprobarUsuario(nombre);
                System.out.println(usuario.getNombreUsuario() + usuario.getPuntuacion());
                menuPrincipal();
                break;

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

                    int posAux = 0;

                    for (int k=0; k<resultado.length(); k++){
                        if(Character.isLetter(resultado.charAt(k))){
                            if(Character.toUpperCase(palabraEscogida.charAt(posAux)) == Character.toUpperCase(palabraInput.charAt(posAux))) {
                                System.out.println(Character.toUpperCase(resultado.charAt(k)) + " Es igual a " + Character.toUpperCase(palabraInput.charAt(posAux)));
                                resultado.replace(k + 1, k + 3, "✓");
                            }
                            posAux++;
                        }
                        //if(resultado.charAt(k)== Character.toUpperCase(palabraInput.charAt(i))){//por si se rompe, se cambia por k
                            //resultado.replace(k+1, k+3, "✓");//no reemplace todas las vainas
                        //}
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



    public void controlJuego() {

        try {
            File xml = new File("Usuarios.xml");
            DocumentBuilderFactory fabrica = DocumentBuilderFactory.newInstance();
            DocumentBuilder constructor = fabrica.newDocumentBuilder();
            DOMImplementation implementation = constructor.getDOMImplementation();
            Document documento = implementation.createDocument(null, "Usuarios", null);
            documento.setXmlVersion("1.0");

            for (int i=0; i< usuario.getListaUsuarios().size(); i++){
                Element user = documento.createElement("User");
                Element nombreusuario = documento.createElement("usuario");
                Element puntuacion = documento.createElement("puntuacion");

                Text txtUsuario = documento.createTextNode(usuario.getListaUsuarios().get(i).getNombreUsuario());
                Text txtPuntuacion = documento.createTextNode(String.valueOf(usuario.getListaUsuarios().get(i).getPuntuacion()));

                nombreusuario.appendChild(txtUsuario);
                puntuacion.appendChild(txtPuntuacion);

                user.appendChild(nombreusuario);
                user.appendChild(puntuacion);

                documento.getDocumentElement().appendChild(user);
            }

            Source source = new DOMSource(documento);
            Result resultado = new StreamResult(xml);
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.transform(source,resultado);


        }catch (ParserConfigurationException | TransformerException | DOMException ex){
            System.out.println(ex.getMessage());
        }



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
        usuario.actualizarPuntuacion(usuario.getNombreUsuario());

    }

    public Usuario getUsuario() {
        return usuario;
    }
}


