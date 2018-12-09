package pl.adudojc.vitamins_game.model;

import lombok.Getter;
import lombok.Setter;
import pl.adudojc.vitamins_game.controller.Controller;
import pl.adudojc.vitamins_game.enumerations.VitaminType;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Vitamin {

    @Getter @Setter
    private Rectangle shape;
    @Getter @Setter
    private int posX, posY;
    @Getter
    private int sizeX, sizeY;
    private int generatedHeight, generatedWidth;
    private Random rand;
    @Getter
    private VitaminType vitaminType;
    @Getter
    private int points;
    @Getter
    private String textureName;
    private List<String> goodVitamin = new ArrayList<>();
    private List<String> badVitamin = new ArrayList<>();

    public Vitamin() {
        initialize();
    }


    public void initialize() {
        rand = new Random();
        sizeX = 100;
        sizeY = 90;
        generatedHeight = -400 + rand.nextInt(750);
        generatedWidth = -200 + rand.nextInt(1000);
        vitaminType = vitaminType.randomTypeOfVitamin();
        points = vitaminType.getPointsOfRandomVitaminType(vitaminType);
        recognizeVitaminForDisease();
        randomVitaminName();
        posX = Player.instance.getPosX() + generatedWidth;
        posY = (904 / 2) - generatedHeight;
        shape = new Rectangle(posX, posY, sizeX, sizeY);
    }


    private void randomVitaminName() {
        if (vitaminType == vitaminType.GOOD_VITAMIN) {
            int randomizedNumber = rand.nextInt(goodVitamin.size());
            textureName = goodVitamin.get(randomizedNumber);
        } else if (vitaminType == vitaminType.BAD_VITAMIN) {
            int randomizedNumber = rand.nextInt(badVitamin.size());
            textureName = badVitamin.get(randomizedNumber);
        }
    }

    private void recognizeVitaminForDisease() {
        String currentDisease = Controller.controller.getDisease();

        switch (currentDisease) {
            case "Szkorbut":
                goodVitamin = Arrays.asList("vitaminC");
                badVitamin = Arrays.asList("vitaminD", "vitaminE", "vitaminK", "vitaminA");
                break;
            case "Osteoporoza":
                goodVitamin = Arrays.asList("vitaminD");
                badVitamin = Arrays.asList("vitaminC", "vitaminE", "vitaminK", "vitaminA");
                break;
            case "Kurza slepota":
                goodVitamin = Arrays.asList("vitaminA");
                badVitamin = Arrays.asList("vitaminD", "vitaminE", "vitaminK", "vitaminA");
                break;
        }
    }
}

