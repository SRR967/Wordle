import Logica.Palabras;
import Logica.Tablero;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException, ParserConfigurationException, TransformerException {
        Document documento;
        DocumentBuilderFactory factoria = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = factoria.newDocumentBuilder();

        documento = documentBuilder.newDocument();
        Element usuarios = documento.createElement("Usuarios");
        documento.appendChild(usuarios);





        Element puntaje = documento.createElement("Puntaje");
        usuarios.appendChild(puntaje);


        Source raiz = new DOMSource(documento);
        TransformerFactory factori = TransformerFactory.newInstance();
        Transformer transfromador = factori.newTransformer();
        File file= new File("Usuarios.xml");
        FileWriter fw = new FileWriter(file);
        PrintWriter pw = new PrintWriter(fw);
        Result resultado = new StreamResult(pw);

        transfromador.transform(raiz,resultado);
        Tablero tablero = new Tablero();

    }

}
