package pl.adudojc.vitamins_game.model;

import lombok.Getter;
import lombok.Setter;
import pl.adudojc.vitamins_game.generator.RuntimeVitaminsGenerator;

import java.awt.*;

public class Player {

    public static Player instance = new Player();
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

    public void refreshPlayerGraphics() {
        shape = new Rectangle(posX, posY, sizeX, sizeY);
    }

    public void detectCollisions() {
        touchVitamin();
    }

    private void touchVitamin() {
        RuntimeVitaminsGenerator runtimeObjectsGenerator = RuntimeVitaminsGenerator.instance;
        runtimeObjectsGenerator.getVitaminList().forEach(item -> {
            if (item.getShape().intersects(shape)) {
                item.getShape().setSize(0, 0);
                addPlayerPoints(item);
            }
        });
    }

    private void addPlayerPoints(Vitamin vitamin) {
        score += vitamin.getPoints();
    }

    private void addPlayerLevel() {
        level++;
    }
}
