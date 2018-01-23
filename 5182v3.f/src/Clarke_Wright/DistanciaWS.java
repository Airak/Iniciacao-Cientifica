package Clarke_Wright;

/**
 *
 * @author Matheus
 */
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
 
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
 
public class DistanciaWS {
    public String calcular(String origem, String destino) {
        URL url;
        try {
            url = new URL(
                    "http://maps.google.com/maps/api/directions/xml?origin="
                            + origem + "&destination=" + destino
                            + "&sensor=false");
 
            while(true){
                /*Correção do bug da consulta: Enquanto não retornar um xml 
                    válido a consulta é repetida */
                //System.out.println();
                Document document = getDocumento(url);
                String a = analisaXml(document);
                if(a!=null){
                    return a;
                }
            }           
        } catch (MalformedURLException | DocumentException e) {
            e.printStackTrace();
        }
        return "";
    }
 
    @SuppressWarnings("rawtypes")
    public static String analisaXml(Document document) {
        
        List list = document.selectNodes("//DirectionsResponse/route/leg/distance/text");
        
        if(list.isEmpty()) return null;
        Element element = (Element) list.get(list.size() - 1);
 
        return element.getText();
    }
 
    public static Document getDocumento(URL url) throws DocumentException {
        SAXReader reader = new SAXReader();
        Document document = reader.read(url);
        return document;
    } 
}
