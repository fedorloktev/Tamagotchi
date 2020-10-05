package sample;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import sample.Interfaces.checkPets;
import javax.swing.*;
/*
 * The class of the main menu of the application, one of the buttons must be guaranteed to be inaccessible. according to performance requirements
 * if the pet has already been created and is still "alive", then you cannot create a new one. Accordingly, if there is no pet, then it is impossible to visit him.
 * connect before creating it.
*
 * Also, in accordance with the terms of the task, you cannot immediately create a new pet after the death of the previous one.
 * The class implements the checkPets interface with a default method to check for a live pet.
 */
public class Controller implements checkPets {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button create;

    @FXML
    private Button join;
    @FXML
    private AnchorPane root;


    @FXML
    void initialize() throws Exception {

        // Check for the existence of a "live" pet. The check is performed using the default method of the checkPets interface.
        // The method traverses the standard folder to find the XML configuration. If it exists, it returns true.
        if (new Controller().checkingPet()==false) join.setDisable(true);
        if (new Controller().checkingPet()==true) create.setDisable(true);
        if (checkingPet() == true ) {
            System.out.println(new PetsAccount().getValue("isDead"));
            if (new PetsAccount().getValue("isDead").equals("true")) {
                join.setDisable(true);
                create.setDisable(true);
                new NewPage().moveOnNewPage("PetIsDead.fxml", "Pet's House - Увы!", 600, 154);
            }
        }
        create.setOnMouseClicked(event -> {
            try {
                new NewPage().moveOnNewPage("createPet.fxml","Pet's House - Create pet",600,400);
                create.getScene().getWindow().hide();
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "The page cannot be open!");
            }
        });

        join.setOnMouseClicked(event -> {
            try {
                new NewPage().moveOnNewPage("joinPet.fxml","Pet's House - Room",750,470);
                create.getScene().getWindow().hide();
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "The page cannot be open!");
            }
        });
    }
}
