package sample;

import javafx.application.Application;
import javafx.stage.Stage;
import sample.Interfaces.checkPets;


public class Main  extends Application implements checkPets {

    @Override
    public void start(Stage primaryStage) throws Exception {
        new NewPage().moveOnNewPage("sample.fxml", "Pet's House - You're welcome!", 425, 302);
    }



    public static void main(String[] args) {
        launch(args);
    }
}
