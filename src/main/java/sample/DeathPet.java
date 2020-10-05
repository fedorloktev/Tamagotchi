package sample;

import javafx.scene.Scene;
import javax.swing.*;
import java.io.IOException;

public class DeathPet {
    private double health = 0;
    private String typePet = null;
    private String imgPet = "src/sample/images/";
    private String urlPet = null;

    void deathPet(Scene scene)  {
            JOptionPane.showMessageDialog(null, "It looks like you haven't fed your pet for a long time" +
                    " and he died!", "Sorry",1);

        new PetsAccount().changeValueByKey("isDead","true");
        new PetsAccount().changeValueByKey("deadTime", System.currentTimeMillis());
        try {
            new NewPage().moveOnNewPage("PetIsDead.fxml", "Pet's House - Sorry!", 600, 154);
        } catch (IOException e) {
            System.out.println("Error 0000x1");
        }
        scene.getWindow().hide();
        }
    }