package pl.adudojc.vitamins_game.model;

import lombok.Getter;
import lombok.Setter;
import pl.adudojc.vitamins_game.generator.RuntimeVitaminsGenerator;

import java.awt.*;

/**
 *Klasa Player, reprezentujaca gracza
 */

public class Player {

     /**
     *instancja klasy Player
     */
    public static Player instance = new Player();

    /**
     *ksztalt gracza
     */
    @Getter
    private Rectangle shape;

    /**
     *pozycja x i y
     */
    @Getter @Setter
    private int posX, posY;

    /**
     *szerokosc i wysokosc
     */
    @Getter
    private int sizeX, sizeY;

    /**
     *zsumowane punty
     */
    @Getter @Setter
    private int score;

    /**
     *poziom gry
     */
    @Getter @Setter
    private int level = 1;

    /**
     *poczatkowe x i y
     */
    private int startX, startY;

    /**
     *zmienna zliczajaca zebranie niewlasciwej witaminy
     */
    @Getter @Setter
    int numberOfBadChoices = 0;

    /**
     *konstruktor klasy Player
     */
    public Player() {
        createPlayer();
    }

    /**
     *Tworzenie gracza
     *
     * @return ksztalt gracza
     */
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


    /**
     *Odswieza pozycje gracza
     */
    public void refreshPlayerGraphics() {
        shape = new Rectangle(posX, posY, sizeX, sizeY);
    }


    /**
     *Wykrycie zderzenia gracza z witaminami
     */
    public void detectCollisions() {
        touchVitamin();
    }


    /**
     *Metoda, dzieki ktorej po dotknieciu gracza z witamina, znika witamina
     * Dodanie puntow za witamine
     */
    private void touchVitamin() {
        RuntimeVitaminsGenerator runtimeObjectsGenerator = RuntimeVitaminsGenerator.instance;
        runtimeObjectsGenerator.getVitaminList().forEach(vitamin -> {
            if (vitamin.getShape().intersects(shape)) {
                vitamin.getShape().setSize(0, 0);
                addPlayerPoints(vitamin);
            }
        });
    }


    /**
     *Dodawanie puntow graczowi
     */
    private void addPlayerPoints(Vitamin vitamin) {
        score += vitamin.getPoints();

        if(vitamin.getPoints()<0)
        {
            numberOfBadChoices++;
        }

        System.out.println(numberOfBadChoices);
        if(score==20 && level<2)
        {
            addPlayerLevel();
        }
        if(score == 40 && level < 3)
        {
            addPlayerLevel();
        }
    }

    /**
     *Dodawanie poziomu graczowi
     */
    private void addPlayerLevel() { level++; }
}
