package Logica;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Dom {
    private Document documento;
    private Tablero tablero= new Tablero();

    public Dom () throws ParserConfigurationException, IOException, TransformerException {
        DocumentBuilderFactory factoria = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = factoria.newDocumentBuilder();
        documento = documentBuilder.newDocument();
    }

    public void generarDocumento(){
        Element usuario = documento.createElement("Usuario");
        documento.appendChild(usuario);
        usuario.setAttribute("usuario",tablero.getUsuario().getNombreUsuario());

        Element puntaje = documento.createElement("Puntaje");
        usuario.appendChild(puntaje);

    }

    public void generarXML() throws TransformerException, IOException {

        Source raiz = new DOMSource(documento);
        TransformerFactory factoria = TransformerFactory.newInstance();
        Transformer transfromador = factoria.newTransformer();
        File file= new File("Usuarios.xml");
        FileWriter fw = new FileWriter(file);
        PrintWriter pw = new PrintWriter(fw);
        Result resultado = new StreamResult(pw);

        transfromador.transform(raiz,resultado);




    }
}
