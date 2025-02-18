import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;


public class Ninjamanagement {
    // Metoda de citire a logurilor din XML
    public List<Ninja> readLogsFromXML(String filePath) {
        List<Ninja> logs = new ArrayList<>();
        try {
            File xmlFile = new File(filePath);
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(xmlFile);

            document.getDocumentElement().normalize();
            NodeList nodeList = document.getElementsByTagName("log");

            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    try {
                        int id = Integer.parseInt(element.getElementsByTagName("Id").item(0).getTextContent());
                        String charaktername = element.getElementsByTagName("Charaktername").item(0).getTextContent();
                        stufen stufe = stufen.valueOf(element.getElementsByTagName("Stufe").item(0).getTextContent());
                        String beschreibung = element.getElementsByTagName("Beschreibung").item(0).getTextContent();
                        LocalDate datum = LocalDate.parse(element.getElementsByTagName("Datum").item(0).getTextContent());
                        double kraftpunkte = Double.parseDouble(element.getElementsByTagName("Kraftpunkte").item(0).getTextContent());

                        logs.add(new Ninja(id, charaktername, stufe, beschreibung, datum, kraftpunkte));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return logs;
    }

    public static void displayFiltered(List<Ninja> logs, int numar) {
        // Filtrarea mai mare putere
        List<Ninja> filteredNinja = logs.stream()
                .filter(ninja -> ninja.getKraftpunkte() > numar) // Filtrare
                .collect(Collectors.toList());  // Colectează rezultatele într-o listă

        // Dacă există jocuri filtrate, le afișăm
        if (!filteredNinja.isEmpty()) {
            System.out.println("Kraftpunkte >= " + numar + ":");
            // Iterează prin jocuri și afișează echipele, data și locul jocului
            for (Ninja ninja : filteredNinja) {
                System.out.println(ninja.getCharaktername());
            }
        } else {
            System.out.println("Keine gefunden.");
        }
    }

    public static void displayJonin(List<Ninja> logs) {
        // Filtrarea jocurilor care se află în orașul specificat și după data minimă
        List<Ninja> filteredGames = logs.stream()
                .filter(ninja -> ninja.getStufe().toString() == "Jonin")
                .sorted(Comparator.comparing(Ninja::getDatum).reversed()) // Sortare crescatoare
                .collect(Collectors.toList());

        // Dacă există jocuri filtrate, le afișăm
        if (!filteredGames.isEmpty()) {
            System.out.println("Jonini:");
            for (Ninja ninja : filteredGames) {
                System.out.println( ninja.getDatum() + ":" + ninja.getCharaktername() + " " + ninja.getBeschreibung());
                System.out.println("----------------------------------");
            }
        } else {
            System.out.println("Keine gefunden.");
        }
    }

    public static void main(String[] args) {
        Ninjamanagement manager = new Ninjamanagement();
        Scanner scanner = new Scanner(System.in);

        // Calea fișierului XML
        String filePath = "C:/Users/silic/IdeaProjects/RESSilionAndreiPRELAufgabe1/src/ninja_events.xml";

        // Citește logurile din XML
        List<Ninja> logs = manager.readLogsFromXML(filePath);

        // Afișează toate logurile din listă
        logs.forEach(System.out::println);

        System.out.print("Introduceți număr: ");
        int numar = scanner.nextInt();


        displayFiltered(logs, numar);
        displayJonin(logs);
    }
}