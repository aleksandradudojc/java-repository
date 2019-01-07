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

    /*Kształt witaminy*/
    @Getter @Setter
    private Rectangle shape;

    /*Pozycja z i pozycja y witaminy*/
    @Getter @Setter
    private int posX, posY;

    /*Rozmiar witaminy*/
    @Getter
    private int sizeX, sizeY;

    /*Generowane współrzędne, w którzych maja pojawić się współrzedne w grze*/
    private int generatedHeight, generatedWidth;

    private Random rand;


    @Getter
    private VitaminType vitaminType;

    /*Punkty przydzielane w zależności od tego czy witamina jest dobra, czy zła*/
    @Getter
    private int points;
    @Getter

    /*Nazwa wylosowanej witaminy witaminy*/
    private String vitaminName;

    /*Lista dobrych witamin*/
    private List<String> goodVitamin = new ArrayList<>();

    /*Lista złych witamin*/
    private List<String> badVitamin = new ArrayList<>();

    /*Konstruktor klasy Vitamin
    * Wywołuje metodę initialize*/
    public Vitamin() {
        initialize();
    }


    /*Inicjalizacja pojawiającej się w grze witaminy */
    public void initialize() {
        rand = new Random();
        sizeX = 100;
        sizeY = 90;
        generatedHeight = -300 + rand.nextInt(650);
        generatedWidth = 200 + rand.nextInt(900);
        vitaminType = vitaminType.randomTypeOfVitamin();
        points = vitaminType.getPointsOfRandomVitaminType(vitaminType);
        recognizeVitaminForDisease();
        randomVitaminName();
        posX = Player.instance.getPosX() + generatedWidth;
        posY = (904 / 2) - generatedHeight;
        shape = new Rectangle(posX, posY, sizeX, sizeY);
    }


    /*Losowanie witamin sposród list dobrych oraz złych witamin*/
    private void randomVitaminName() {
        if (vitaminType == vitaminType.GOOD_VITAMIN) {
            int randomizedNumber = rand.nextInt(goodVitamin.size());
            vitaminName = goodVitamin.get(randomizedNumber);
        } else if (vitaminType == vitaminType.BAD_VITAMIN) {
            int randomizedNumber = rand.nextInt(badVitamin.size());
            vitaminName = badVitamin.get(randomizedNumber);
        }
    }

    /*Rozpoznanie i zapisanie do list dobrych i złych witamin, w zależności od choroby*/
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

