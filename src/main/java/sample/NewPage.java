package sample;

/*
 * The class is intended to call a new application window
 * Implements the FormViewer interface with new FXML form parameters
 */
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import sample.Interfaces.FormViewer;
import java.io.IOException;

public class NewPage implements FormViewer {
    @Override
    public void moveOnNewPage(String urlPage, String pageTitle, int width, int height) throws IOException {
        Stage stage = new Stage();
        Parent root = (Parent)FXMLLoader.load(getClass().getResource("../FXML/"+urlPage));
        stage.setTitle(pageTitle);
        stage.setScene(new Scene(root, width, height));
        stage.setResizable(false);
        stage.setMaximized(false);
        stage.show();

        // Close window with help standard button
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            public void handle(WindowEvent we) {
                try {
                    new PetsAccount().changeValueByKey("date_last_join", System.currentTimeMillis());
                } catch (Exception e) { }
                System.exit(1);
            }
        });
    }
}
