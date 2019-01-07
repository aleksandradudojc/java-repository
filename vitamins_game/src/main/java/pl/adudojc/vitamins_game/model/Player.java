package pl.adudojc.vitamins_game.model;

import lombok.Getter;
import lombok.Setter;
import pl.adudojc.vitamins_game.generator.RuntimeVitaminsGenerator;

import java.awt.*;

public class Player {

    /*Instancja klasy Player*/
    public static Player instance = new Player();

    /*Kształt gracza*/
    @Getter
    private Rectangle shape;
    @Getter @Setter
    private int posX, posY;
    @Getter
    private int sizeX, sizeY;
    @Getter @Setter
    private int score;
    @Getter @Setter
    private int level = 1;
    private int startX, startY;
    @Getter @Setter
    int numberOfBadChoices = 0;

    public Player() {
        createPlayer();
    }

    public Rectangle createPlayer() {
        sizeX = 100;
        sizeY = 100;
        startX = 50;
        startY = 512 - sizeY / 2;
        posX = startX;
        posY = startY;
        shape = new Rectangle(posX, posY, sizeX, sizeY);
        return shape;
    }

    /*Odświeża pozycje gracza*/
    public void refreshPlayerGraphics() {
        shape = new Rectangle(posX, posY, sizeX, sizeY);
    }

    /*Wykrycie zderzenia gracza z witaminami*/
    public void detectCollisions() {
        touchVitamin();
    }

    /*Metoda, dzięki której po dotknięciu gracza z witaminą, znika witamina
    * Dodanie puntów za witaminę*/
    private void touchVitamin() {
        RuntimeVitaminsGenerator runtimeObjectsGenerator = RuntimeVitaminsGenerator.instance;
        runtimeObjectsGenerator.getVitaminList().forEach(vitamin -> {
            if (vitamin.getShape().intersects(shape)) {
                vitamin.getShape().setSize(0, 0);
                addPlayerPoints(vitamin);
            }
        });
    }

    /*Dodawanie puntów graczowi*/
    private void addPlayerPoints(Vitamin vitamin) {
        score += vitamin.getPoints();

        if(vitamin.getPoints()<0)
        {
            numberOfBadChoices++;
        }

        System.out.println(numberOfBadChoices); // wychodzi ok
        if(score==20 && level<2)
        {
            addPlayerLevel();
        }
        if(score == 40 && level < 3)
        {
            addPlayerLevel();
        }
    }

    /*Dodawanie poziomu graczowi*/
    private void addPlayerLevel() { level++; }
}
