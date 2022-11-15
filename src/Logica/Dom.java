package Logica;

import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

public class Dom {
    private Document documento;
    private Usuario usuario;

    public Dom () {
        try {

            DocumentBuilderFactory factoria = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = null;
            documentBuilder = factoria.newDocumentBuilder();

            documento = documentBuilder.parse(new File("Usuarios.xml"));


        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void imprimirXML(Node nodo){

        NodeList hijos= nodo.getChildNodes();

        for(int i=0; i<hijos.getLength(); i++) {

            //crear un nodo hijo de la lista

            Node hijo = hijos.item(i);

            String nombre= null;
            String puntuacion = null;
            //verificar tipo de nodos
            //elementos, texto o atributos
            if (hijo.getNodeType() == Node.ELEMENT_NODE) {
                imprimirXML(hijo);
            }
            if (hijo.getNodeType() == Node.TEXT_NODE) {
                if(hijo.getTextContent() != null){
                    System.out.println(hijo.getTextContent());
                }


            }
        }
    }

    public Document getDocumento() {
        return documento;
    }
}
