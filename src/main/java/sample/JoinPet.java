package sample;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

import javax.swing.*;
            /*
            * Class for join in room with pet. This class create images, growns up pet, look scales eat and hobbie
             */
public class JoinPet extends Update {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    public AnchorPane background;

    @FXML
    private Button play;

    @FXML
    private ImageView typeEat;

    @FXML
    private Button eat;
    @FXML
    public ProgressBar hobbie = null;
    @FXML
    public ProgressBar health = null;

    @FXML
    private ImageView pet;

    private String typePet = null;
    private String imgPet = "src/sample/images/";
    public static double status = 0;
    public static double statusHobb = 0;
    public static double statusEat = 0;
    private long grown = 0; // Pet grown up
    private static long currTimeEraEat;
    private static String currDateEat;
    private static long currTimeEraHobb;
    private static String currDateHobb;
    static double age = 0;

    @Override
    public void run() { }

    @FXML
    void initialize()  throws  Exception{
        int petAge = Integer.valueOf(new PetsAccount().getValue("curr_age"));
        typePet = new PetsAccount().getValue("type").toLowerCase();
        background.setStyle("-fx-background-color: DeepSkyBlue ;");
        try {
            pet.setImage(new Image(new FileInputStream(imgPet+typePet+".png")));
        } catch (FileNotFoundException e) {
            System.out.println("Error 0000x2");
        }

        try {
            grown = new TimeInterval().getTimeIntervalForGeneral("last_time_grow_StartEra");
        } catch (Exception e) {
            System.out.println("Error 0000x3");
        }
        if (grown>=30 && grown<=60) {petAge=2;}
        if (grown>60) {petAge=3;}
        new PetsAccount().changeValueByKey("curr_age", petAge);

        switch (petAge){ // look pet's age
            case 1: { pet.setScaleY(0.5); pet.setScaleX(0.5); age = 0.5; break; }
            case 2: { pet.setScaleY(0.7); pet.setScaleX(0.7); age = 0.7; break; }
            case 3: { pet.setScaleY(1); pet.setScaleX(1); age = 1; break; }
        }

        try {
            typeEat.setImage(new Image(new FileInputStream(imgPet+typePet+"Eat.png")));
        } catch (FileNotFoundException e) {
            System.out.println("Error 0000x4");
        }
        health.setProgress(Double.valueOf(new PetsAccount().getValue("curr_health")));
        hobbie.setProgress(Double.valueOf(new PetsAccount().getValue("curr_mood")));

        double stat = health.getProgress();
        double statHobb = hobbie.getProgress();
        setHealth(stat);
        setHobbie(statHobb);

        // Override timer's method. Else ProgressBar will be null
        Timer t = new Timer();
        t.schedule(new Update() {
            @Override
            public void run() {
                try {
                    setBarHealth();
                    System.out.println(statusEat);
                        if (statusEat <= 0.05) {
                            Platform.runLater(new Runnable() {
                                @Override
                                public void run() {
                                    t.cancel();
                                    t.purge();

                                    try {
                                        new DeathPet().deathPet(background.getScene());
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }
                            });
                        }
                    } catch(Exception e){
                        e.printStackTrace();
                    }
                }

        }, 150, 30000);

        // Method, that check button "Eat". When button checked, pet's health add 10%.
        // If pet's health more 100% will be exception.
        eat.setOnMouseClicked(event -> {
            if (getHealth()<1.0) { //
                health.setProgress(health.getProgress()+0.1);
                double stats = health.getProgress();
                try { new PetsAccount().changeValueByKey("curr_health",stats);
                    new PetsAccount().changeValueByKey("last_time_health_change", new SimpleDateFormat("dd.MM.yy HH:mm:ss").format(Calendar.getInstance().getTime()));
                    new PetsAccount().changeValueByKey("last_time_health_change_StartEra", System.currentTimeMillis());} catch (Exception e) { }
                setHealth(stats);
            }else { JOptionPane.showMessageDialog(null,"Your pet is not hungry!","Overeat",1); }
        });

        play.setOnMouseClicked(event -> {
            if (getHobbie()<1.0) {
                hobbie.setProgress(hobbie.getProgress()+0.1);
                double stats = hobbie.getProgress();
                try { new PetsAccount().changeValueByKey("curr_mood",stats);
                    new PetsAccount().changeValueByKey("last_time_mood_change", new SimpleDateFormat("dd.MM.yy HH:mm:ss").format(Calendar.getInstance().getTime()));
                    new PetsAccount().changeValueByKey("last_time_mood_change_StartEra", System.currentTimeMillis());} catch (Exception e) { }
                setHobbie(stats);
            } else{
                JOptionPane.showMessageDialog(null,"Your pet is tired","Overplayed!",1);
            }
        });
        // Pet's move methods
        background.setOnKeyPressed(event -> {
            try {
                new MovePet().move(pet, event, typePet, background, age);
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        background.setOnKeyReleased(event -> {
            try {
                new MovePet().moveNoPress(pet, event, typePet, background);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
            try {
                new ScaleEatAndPlay().eat();
                new ScaleEatAndPlay().hobbie();
            }  catch (Exception e) {
                System.out.println("Error 0000x5");
            }
    }

    public double getHealth() {
        return statusEat;
    }
    public void setHealth(double stat) {
        statusEat = stat;
    }
    public double getHobbie() {
        return statusHobb;
    }
    public void setHobbie(double statHobb) {
        statusHobb = statHobb;
    }
    public void setHobbie(double timerStat, String direction) {
        statusHobb = statusHobb-timerStat;
        new PetsAccount().changeValueByKey("curr_mood",statusHobb);
        currDateHobb = new SimpleDateFormat("dd.MM.yy HH:mm:ss").format(Calendar.getInstance().getTime());
        currTimeEraHobb = System.currentTimeMillis();
    }
    public void setEat(double timerStat, String direction) {
        statusEat = statusEat-timerStat;
        new PetsAccount().changeValueByKey("curr_health",statusEat);
        currDateEat = new SimpleDateFormat("dd.MM.yy HH:mm:ss").format(Calendar.getInstance().getTime());
        currTimeEraEat = System.currentTimeMillis();
    }

    // Update scales health and hobbie
    public void setBarHealth() throws Exception {
        try {
            System.out.println("statusEat = "+statusEat+" statusHobb = "+statusHobb);
            System.out.println("currDateEat: "+currDateEat+"  currDateHobb: "+currDateHobb);

            if (Double.valueOf(new PetsAccount().getValue("curr_health"))<0) new PetsAccount().changeValueByKey("curr_health", 0.0);
            if (Double.valueOf(new PetsAccount().getValue("curr_mood"))<0) new PetsAccount().changeValueByKey("curr_mood", 0.0);
            health.setProgress(statusEat);
            hobbie.setProgress(statusHobb);

            if (statusEat<0) health.setProgress(0.0);
            if (statusHobb<0) hobbie.setProgress(0.0);

            new PetsAccount().changeValueByKey("last_time_health_change", currDateEat);
            new PetsAccount().changeValueByKey("last_time_health_change_StartEra", currTimeEraEat);
//
            new PetsAccount().changeValueByKey("last_time_mood_change", currDateHobb);
            new PetsAccount().changeValueByKey("last_time_mood_change_StartEra", currTimeEraHobb);
            System.out.println("CHECKED");
        } catch (Exception e) { }

    }
}

abstract class Update extends TimerTask {
     abstract public void run();
}