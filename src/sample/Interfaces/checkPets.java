package sample.Interfaces;

import java.io.File;
    // Interface with default methor for checking Pet
public interface checkPets {
    default boolean checkingPet() {
        boolean chk = false;
        File file = new File(System.getProperty("user.dir"));
        File[] f = file.listFiles();
        for( File w : f) {
            String s = w.toString();
            if (s.contains("Account.xml")) {chk = true; break;}

        }
        return chk;
    }
}
