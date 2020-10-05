package sample;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;
/*
 * A class that operates with an XML file that is a "storage" of information about a pet.
 * Able to: create and update a configuration, parse a single value from an XML file.
 * Supports getter and setters. Setters are complexly executed in the createAccount () method.
 */
public class PetsAccount {
    private String newLine = System.lineSeparator();
    private String current_date = new SimpleDateFormat("dd.MM.yy HH:mm:ss").format(Calendar.getInstance().getTime());
    private long current_date_Era = System.currentTimeMillis();
    private String type             = null;
    private int curr_age            = 0;
    private String date_age         = null;
    private int curr_health         = 0;
    private String date_health      = null;
    private int curr_mood           = 0;
    private String date_mood_change = null;
    private String date_last_play   = null;
    private String date_eat_change  = null;

    // A block of variables that are assigned the current time in milliseconds since the beginning of the Java Age
    private long date_age_Era = 0;
    private long date_health_Era = 0;
    private long date_mood_change_Era = 0;
    private long date_eat_change_Era = 0;
    private long date_last_play_Era = 0;
    private long last_time_join_Era = 0;
    private String isDead = "false";
    private long deadTime = 0;


    // Setter for the fields that will be operated by the rest of the class methods.
    void createAccount(String type)  {
        this.type = type;
        curr_age = 1;
        date_age = current_date;
        curr_health = 1;
        date_health = current_date;
        curr_mood = 1;
        date_mood_change = current_date;
        date_last_play = current_date;
        date_eat_change = current_date;
        date_age_Era = current_date_Era;
        date_health_Era = current_date_Era;
        date_mood_change_Era = current_date_Era;
        date_eat_change_Era = current_date_Era;
        date_last_play_Era = current_date_Era;
        last_time_join_Era = current_date_Era;

        try {
            createXML();
        } catch (Exception e) {
            System.out.println("Error 0004x1");
        }
    }
    // Method that create account's config in XML file
    void createXML() {
        try {
            FileWriter fileWriter = new FileWriter("Pet Account.xml", false);
            fileWriter.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>" + newLine);
            fileWriter.append("<pet>" + newLine);
            fileWriter.append("     <type>" + type + "</type>" + newLine);
            fileWriter.append("     <age>" + newLine);
            fileWriter.append("         <curr_age>" + curr_age + "</curr_age>" + newLine);
            fileWriter.append("         <last_time_grow>" + date_age + "</last_time_grow>" + newLine);
            fileWriter.append("         <last_time_grow_StartEra>" + date_age_Era + "</last_time_grow_StartEra>" + newLine);
            fileWriter.append("     </age>" + newLine);
            fileWriter.append("     <health>" + newLine);
            fileWriter.append("         <curr_health>" + curr_health + "</curr_health>" + newLine);
            fileWriter.append("         <last_time_health_change>" + date_health + "</last_time_health_change>" + newLine);
            fileWriter.append("         <last_time_health_change_StartEra>" + date_health_Era + "</last_time_health_change_StartEra>" + newLine);
            fileWriter.append("     </health>" + newLine);
            fileWriter.append("     <mood>" + newLine);
            fileWriter.append("         <curr_mood>" + curr_mood + "</curr_mood>" + newLine);
            fileWriter.append("         <last_time_mood_change>" + date_mood_change + "</last_time_mood_change>" + newLine);
            fileWriter.append("         <last_time_mood_change_StartEra>" + date_mood_change_Era + "</last_time_mood_change_StartEra>" + newLine);
            fileWriter.append("     </mood>" + newLine);
            fileWriter.append("     <satiety>" + newLine);
            fileWriter.append("         <last_eat_time>" + date_eat_change + "</last_eat_time>" + newLine);
            fileWriter.append("         <last_eat_time_StartEra>" + date_eat_change_Era + "</last_eat_time_StartEra>" + newLine);
            fileWriter.append("     </satiety>" + newLine);
            fileWriter.append("     <hobby>" + newLine);
            fileWriter.append("         <last_time_play>" + date_last_play + "</last_time_play>" + newLine);
            fileWriter.append("         <last_time_play_StartEra>" + date_last_play_Era + "</last_time_play_StartEra>" + newLine);
            fileWriter.append("     </hobby>" + newLine);
            fileWriter.append("     <date_last_join>" + last_time_join_Era + "</date_last_join>" + newLine);
            fileWriter.append("     <isDead>" + isDead + "</isDead>" + newLine);
            fileWriter.append("     <deadTime>" + deadTime + "</deadTime>" + newLine);
            fileWriter.append("</pet>" + newLine);
            fileWriter.close();
        }catch (IOException e){
            System.out.println("Error 0004x2");}
    }

    // Method that changes the value of any parameter in the XML file configuration.
    // Parses and inserts a new value by overwriting the file with changing the required field
    // As passed parameters: a key whose value needs to be updated and a new value
    // The ArrayList collection is used, because a Map dictionary capable of storing the key on its own would complicate the parsing implementation
    // Inheritance from the ParserXML class is not supported, since inheritance will result in 3 or more duplicate code,
    // as it would have to return a Map to return a key-value pair, but there would be no access to the key.
    void changeValueByKey( String key, Object newValue) {
        try {


            BufferedReader bf = new BufferedReader(new FileReader("Pet Account.xml"));
            String read_string_from_file;
            ArrayList<String> arrayList = new ArrayList<>();
            ArrayList<String> newData = new ArrayList<>();
            while ((read_string_from_file = bf.readLine()) != null) {
                arrayList.add(read_string_from_file);
            }
            bf.close();

            for (int i = 0; i < arrayList.size(); i++) {
                String pars_line = arrayList.get(i); // Original string from XML document for parsing
                String value = pars_line;
                if (pars_line.contains(key + ">") && pars_line.contains("</" + key)) {
                    value = value.replace("<" + key + ">", " ");
                    value = value.replace("</" + key + ">", " ");
                    value = value.trim();
                    pars_line = pars_line.replace(value, newValue.toString());
                } else {
                }
                newData.add(pars_line);             // New collection with new values
            }

            FileWriter newFile = new FileWriter("Pet Account.xml", false); // Override file with new values
            for (String w : newData) {
                newFile.append(w + newLine);
            }
            newFile.close();
        }catch (IOException e){
        System.out.println("Error 0004x3");}
}

    // Method using the functionality of the ParserXML class. Returns the parsed string value with the given key
    // This method is a manual version of parsing XML files. The code is implemented in a third party class,
    // to prevent duplication, as well as to further improve the parsing method without changing
    // of this class.
    String getValue(String key)  {
        String value = null;
        try {
            value = new ParserXML().parseXML(key);
        } catch (Exception e) {
            System.out.println("Error 0004x4");
        }
        return value;
    }
}
