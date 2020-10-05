package sample;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ParserXML {
    String parseXML(String key)  {
        BufferedReader bf = null;
        try {
            bf = new BufferedReader(new FileReader("Pet Account.xml"));
        } catch (FileNotFoundException e) {
            System.out.println("Error 0001x1");
        }
        String read_string_from_file;
        String valueKey = null;  // The value obtained as a result of parsing
        ArrayList<String> arrayList = new ArrayList<>();
        while (true) {
            try {
                if (!((read_string_from_file = bf.readLine()) != null)) break;
            arrayList.add(read_string_from_file);
            } catch (IOException e) {
                System.out.println("Error 0001x2");
            }
        }

        for (int i = 0; i < arrayList.size(); i++) {
            String pars_line = arrayList.get(i); // Original string from XML document for parsing
            String value = pars_line;
            if (pars_line.contains("<"+key + ">") && pars_line.contains("</" + key+">")) {
                value = value.replace("<" + key + ">", " ");
                value = value.replace("</" + key + ">", " ");
                value = value.replaceAll("\\s","");
                valueKey = value;

            }
        }
        try {
            bf.close();
        } catch (IOException e) {
            System.out.println("Error 0001x3");
        }
        return valueKey;
    }


}