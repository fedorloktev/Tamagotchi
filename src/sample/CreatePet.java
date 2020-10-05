package sample;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javax.swing.*;

public class CreatePet {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ToggleButton pet3;

    @FXML
    private Button select;

    @FXML
    private ToggleButton pet1;

    @FXML
    private ToggleButton pet2;

    @FXML
    private Button back;

    ToggleGroup tg = new ToggleGroup();
    String type;
    @FXML
    void initialize() throws Exception {
        pet1.setToggleGroup(tg);
        pet2.setToggleGroup(tg);
        pet3.setToggleGroup(tg);

        pet1.setOnMouseClicked(event -> {pet1.setOpacity(1); pet2.setOpacity(0.5); pet3.setOpacity(0.5); type = "CAT";});
        pet2.setOnMouseClicked(event -> {pet2.setOpacity(1); pet1.setOpacity(0.5); pet3.setOpacity(0.5); type = "DOG";});
        pet3.setOnMouseClicked(event -> {pet3.setOpacity(1); pet2.setOpacity(0.5); pet1.setOpacity(0.5); type = "BUNNY";});

        select.setOnAction(e -> {
            if (tg.getSelectedToggle()==null) JOptionPane.showMessageDialog(null, "Please, select type pet!");
            else {
                try {
                    new PetsAccount().createAccount(type);
                    new NewPage().moveOnNewPage("joinPet.fxml", "Pet's House - Room", 750, 470);
                    select.getScene().getWindow().hide();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "The page cannot be open!");
                }
            }
        });

        back.setOnAction(event -> {
            try {
                new NewPage().moveOnNewPage("sample.fxml", "Pet's House - You're welcome", 425, 302);
                back.getScene().getWindow().hide();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "The page cannot be open!");
            }
        });
    }
}


