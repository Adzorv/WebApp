package nl.dagobank.webapp.util;


import nl.dagobank.webapp.domain.Address;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import javax.xml.transform.Result;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TestDataGenerator {

    static final String FIRST_NAME_PATH = "src/main/resources/static/firstname.xml";
    static final String LAST_NAME_PATH = "src/main/resources/static/surname.xml";
    static final String ADRESSES_PATH = "src/main/resources/static/10000Adresses.csv";

    static final String[] MAILADRESS_DOMAINS = {"gmail.nl", "zigo.nl", "bluewin.nl", "online.nl"};
    static final int TELEFOON_NUMBER_SEED = 0600000000;

    public TestDataGenerator() {
    }

    public List<String> readFirstnamesFromFile(int numberOfNames){
        return readListFromXMLforTag(FIRST_NAME_PATH, numberOfNames, "voornaam");
    }

    public List<String> readLastNameFromFile(int numberOfNames){
        return readListFromXMLforTag(LAST_NAME_PATH, numberOfNames, "naam");
    }

    public List<String> readPrefixFromFile(int numberOfNames) {
        return readListFromXMLforTag(LAST_NAME_PATH, numberOfNames, "prefix");
    }


    private List<String> readListFromXMLforTag(String filePath, int numberOfNames, String tag){
        List<String> names = new ArrayList<>();
        try {
            XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
            XMLEventReader reader = xmlInputFactory.createXMLEventReader(new FileInputStream(filePath));
            int count = 0;
            while (reader.hasNext() && count < numberOfNames) {
                XMLEvent nextEvent = reader.nextEvent();
                if (nextEvent.isStartElement()) {
                    StartElement startElement = nextEvent.asStartElement();
                    if (startElement.getName().toString().equals(tag)) {
                        nextEvent = reader.nextEvent();
                        if (nextEvent.isEndElement()) {
                            names.add("");
                        } else {
                            names.add(nextEvent.asCharacters().getData());
                        }
                        count++;
                    }
                }
            }
        } catch (Exception e){
            System.out.println(e.getMessage());
        }

    return  names;
    }

    //format of file:
    // PostcodeID;PostCodePK;PostCode;PostcodeNummers;PostcodeLetters;Straat;MinNummer;MaxNummer;Plaats;Gemeente;Provincie
    public List<Address> readAdressesFromFile(int numberOfLines){
        List<Address> output = new ArrayList<>();
        File input = new File(ADRESSES_PATH);
        int count = 0;
        try {
            Scanner reader = new Scanner(input);
            while(reader.hasNextLine() && (count < numberOfLines + 1)){
                if (count == 0){
                    String line = reader.nextLine();
                } else {
                    String line = reader.nextLine();
                    String[] fields = line.split(";");
                    for (int i = 0; i < fields.length; i++) {
                    }
                    Address address = new Address(fields[5], Integer.parseInt(fields[6]), "", fields[2], fields[8]);
                    output.add(address);
                }
                count++;
            }
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    return output;
    }


    public String generateRandomEmailFromMailPrefix(String mailprefix){
        int randomIndex = (int)(Math.random() * MAILADRESS_DOMAINS.length);
        return mailprefix + "@" + MAILADRESS_DOMAINS[randomIndex];
    }

    public String generateTelephoneNumberFromUniqueId(int id){
        int lengthOfId = String.valueOf(id).length();
        int lengthOfSeed = String.valueOf(TELEFOON_NUMBER_SEED).length();
        String trimmedBaseNumber = String.valueOf(TELEFOON_NUMBER_SEED).substring(0,lengthOfSeed - lengthOfId - 1);
        return trimmedBaseNumber + String.valueOf(id);
    }

    public LocalDate getRandomBirthDate(int minAge, int maxAge){
        final int DAYS_OF_YEAR = 365;
        long randomAge = (minAge + (long)(Math.random() * ((maxAge - minAge) + 1)));
        long randomDays = (long)(Math.random() * (DAYS_OF_YEAR + 1));
        LocalDate birthdate = LocalDate.now().minusDays(randomDays).minusYears(randomAge);
        return birthdate;
    }

}
