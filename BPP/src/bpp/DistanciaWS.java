package bpp;

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
 
            Document document = getDocumento(url);
 
            return analisaXml(document);
        } catch (MalformedURLException | DocumentException e) {
            e.printStackTrace();
        }
        return "";
    }
 
    @SuppressWarnings("rawtypes")
    public static String analisaXml(Document document) {
        
        //System.out.println(document.supportsParent());
        List list = document.selectNodes("//DirectionsResponse/route/leg/distance/text");
        
        System.out.println(list.size());
        Element element = (Element) list.get(list.size() - 1);
 
        return element.getText();
    }
 
    public static Document getDocumento(URL url) throws DocumentException {
        SAXReader reader = new SAXReader();
        Document document = reader.read(url);
        return document;
    } 
}
