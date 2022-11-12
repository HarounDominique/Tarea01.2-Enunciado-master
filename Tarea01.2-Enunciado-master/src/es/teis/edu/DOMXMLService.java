package es.teis.edu;

import es.teis.data.exceptions.LecturaException;
import es.teis.dataXML.IXMLService;
import es.teis.model.Partido;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class DOMXMLService implements IXMLService {

    @Override
    public ArrayList<Partido> leerPartidos(String ruta, float umbral) throws LecturaException {

        long id = 0;
        String nombre = "";
        int votos = 0;
        float porcentaje = 0;

        Partido partido = null;

        ArrayList<Partido> partidos = new ArrayList<>();

        try {

            File inputFile = new File(ruta);

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);

            doc.getDocumentElement().normalize();

            NodeList nList = doc.getElementsByTagName(PARTIDO_TAG);

            for (int i = 0; i < nList.getLength(); i++) {

                Node nNode = nList.item(i);

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                    Element eElement = (Element) nNode;

                    id = Long.parseLong(eElement.getAttribute(PARTIDO_ATT_ID));

                    nombre = eElement.getElementsByTagName(PARTIDO_NOMBRE_TAG).item(0).getTextContent();
                    votos = Integer.parseInt(eElement.getElementsByTagName(PARTIDO_VOTOS_NUM_TAG).item(0).getTextContent());
                    porcentaje = Float.parseFloat(eElement.getElementsByTagName(PARTIDO_VOTOS_PORC_TAG).item(0).getTextContent());

                    partido = new Partido(id, nombre, votos, porcentaje);

                    partidos.add(partido);
                }
            }

            partidos.removeIf(p -> p.getPorcentaje() < umbral);


        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();

        }
        return partidos;
    }
}
