package sample;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import java.io.FileInputStream;


public class MovePet {
    private String typeKey = null;
    private ImageView image = null;
    private String imgPet = "src/sample/images/";

    void move(ImageView pet, KeyEvent key, String typePet, AnchorPane root, double age) throws  Exception {
        typeKey = key.getCode().getName();
        image = pet;
        leftOrRingt(pet, typePet, root, age);
    }

    void moveNoPress(ImageView pet, KeyEvent key, String typePet, AnchorPane root) throws  Exception {
        typeKey = key.getCode().getName();
        image = pet;
        leftOrRingtNoPress(pet, typePet, root);
    }

    void leftOrRingt(ImageView pet, String typePet, AnchorPane root, double age) throws Exception {
        double ageReverse = age*(-1);
       switch (typeKey) {
            case "Right": {
                if (pet.getX()<=400) {
                    pet.setImage(new Image(new FileInputStream(imgPet + "move" + typePet + ".png")));
                    pet.setScaleX(ageReverse);
                    pet.setX(pet.getX() + 10);
                }
                break;
            }
            case "Left": {
                if (pet.getX()>=-290) {
                    pet.setImage(new Image(new FileInputStream(imgPet + "move" + typePet + ".png")));
                    pet.setScaleX(age);
                    pet.setX(pet.getX() - 10);
                }
                break;
            }
            case "Up": {
                if (pet.getY()>=-20)
                pet.setY(pet.getY() - 10);
                break;
            }
            case "Down": {
                if (pet.getY()<60)
                pet.setY(pet.getY() + 10);
                break;
            }
        }
    }

    // ==============
    void leftOrRingtNoPress(ImageView pet, String typePet, AnchorPane root) throws Exception {
        switch (typeKey) {
            case "Right": {
                    pet.setImage(new Image(new FileInputStream(imgPet  + typePet + ".png")));
                    break;
            }
            case "Left": {
                pet.setImage(new Image(new FileInputStream(imgPet  + typePet + ".png")));
                break;
            }
            case "Up": {
                pet.setImage(new Image(new FileInputStream(imgPet  + typePet + ".png")));
                break;
            }
            case "Down": {
                pet.setImage(new Image(new FileInputStream(imgPet  + typePet + ".png")));
                break;
            }
        }
    }
}
