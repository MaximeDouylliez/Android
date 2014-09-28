package com.example.wapa.douylliezvlille;

import android.os.AsyncTask;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import java.net.URL;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

/**
 * Created by wapa on 28/09/14.
 */
public class VlilleXMLParser  extends AsyncTask<Integer, Void, ArrayList<Station>> {

    MainActivity activity;

public VlilleXMLParser(MainActivity act){ super();this.activity=act;}
    protected ArrayList<Station> doInBackground(Integer... value) {
        ArrayList<Station> liste = new ArrayList<Station>();
        try {
            String name;
            int id;
            double lng;
            double lat;
            int nbBike;
            int nbAttach;


            try {

                URL url = new URL("http://http://vlille.fr/stations/xml-stations.aspx");
                DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
                DocumentBuilder db = dbf.newDocumentBuilder();
                Document doc = db.parse(new InputSource(url.openStream()));
                doc.getDocumentElement().normalize();

                NodeList nodeList = doc.getElementsByTagName("marker");

                /** Assign textview array lenght by arraylist size */


                for (int i = 0; i < nodeList.getLength(); i++) {

                    Node node = nodeList.item(i);
                    Element MarkerElement = (Element) node;
                    name = MarkerElement.getAttribute("name");
                    int index = Integer.parseInt(MarkerElement.getAttribute("id"));
                    id = index;
                    lng = Double.parseDouble(MarkerElement.getAttribute("lng"));
                    lat = Double.parseDouble(MarkerElement.getAttribute("lat"));


                    try {
                        URL url2 = new URL("http://vlille.fr/stations/xml-station.aspx?borne=<" + index + ">");
                        DocumentBuilderFactory dbf2 = DocumentBuilderFactory.newInstance();
                        DocumentBuilder db2 = dbf2.newDocumentBuilder();
                        Document doc2 = db2.parse(new InputSource(url.openStream()));
                        doc2.getDocumentElement().normalize();

                        NodeList station = doc.getElementsByTagName("station");

                        Element fstElmnt = (Element) station;
                        NodeList bike = fstElmnt.getElementsByTagName("bikes");
                        Element bikeElement = (Element) bike.item(0);
                        bike = bikeElement.getChildNodes();
                        nbBike = Integer.parseInt(((Node) bike.item(0)).getNodeValue());

                        NodeList attach = fstElmnt.getElementsByTagName("bikes");
                        Element attachElement = (Element) attach.item(0);
                        attach = attachElement.getChildNodes();
                        nbAttach = Integer.parseInt(((Node) attach.item(0)).getNodeValue());
                        liste.add(new Station(name, id, lat, lng, nbBike, nbAttach));
                        System.out.println("y'a quelqun ?");
                    } catch (Exception e) {
                        System.out.println("XML Parsing Exception = " + e);
                    }


                }
            } catch (Exception e) {
                System.out.println("XML Parsing Exception = " + e);
            }
        } catch (Exception e) {
            System.out.println("" + e);

        }
   return liste; }

    protected int onProgressUpdate(Integer... progress) {
    return 1;
    }




    protected void onPostExecute(ArrayList<Station> feed) {
activity.setStations(feed);
    }
}





