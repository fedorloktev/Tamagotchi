package sample;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import sample.Interfaces.checkPets;

public class PetIsDead implements checkPets {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;
    @FXML
    private AnchorPane root;


    @FXML
    private Text second;
    private long dead = 0;
    private long bornTime = 0;
    private long currentTime;
    File file = new File("Pet Account.xml");
    @FXML
    void initialize()  {
        if (new PetsAccount().getValue("isDead").equals("true")) {
            try {
                second.setText(new PetIsDead().borningTime());
            } catch (Exception e) {
                System.out.println("Error 0002x1");
            }
        }
        if (Integer.valueOf(second.getText())<=0){
            new PetsAccount().changeValueByKey("isDead","false");
            file.delete();
            System.out.println("File has delete");
        }
    }

    String borningTime() {
        dead = Long.valueOf(new PetsAccount().getValue("deadTime"));
        currentTime = System.currentTimeMillis();
        bornTime = dead+180000; // 3 minutes
        int start = ((int)bornTime-(int)currentTime)/1000;
        System.out.println("dead" +this.dead);
        return String.valueOf(start);
    }

    Stage getStage() {
        return (Stage)root.getScene().getWindow();
    }
}
