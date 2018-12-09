package pl.adudojc.vitamins_game.view;

import pl.adudojc.vitamins_game.controller.Controller;
import pl.adudojc.vitamins_game.enumerations.GameScene;
import pl.adudojc.vitamins_game.generator.RuntimeVitaminsGenerator;
import pl.adudojc.vitamins_game.model.Player;
import pl.adudojc.vitamins_game.model.Vitamin;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

public class View extends JPanel {

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        repaint(g);
    }

    public void repaint(Graphics g) {
        if (Controller.controller.getGameScene() == GameScene.MENU) {
            paintMenu(g, 1280, 1024);
        } else {
            paintBackground(g, 904, 1024);
            paintScoreFont(g);
            paintGameScene(g, Controller.controller.getGameScene());
            paintVitamins(g, RuntimeVitaminsGenerator.instance.getVitaminList());
            paintPlayer(g, Player.instance);
        }
    }

    private void paintMenu(Graphics g, int displayWidth, int displayHeight) {
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, displayWidth, displayHeight);

        BufferedImage gameTitleImage = null;
        BufferedImage playGameButtonImage = null;
        BufferedImage exitImageButton = null;

        try {
            gameTitleImage = ImageIO.read(getClass().getResourceAsStream("/textures/vitamin_title.png"));
            playGameButtonImage = ImageIO.read(getClass().getResourceAsStream("/textures/buttons/play_new_game_button.png"));
            exitImageButton = ImageIO.read(getClass().getResourceAsStream("/textures/buttons/exit_button.png"));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        g.drawImage(gameTitleImage, 325, 100, 700, 200, null);
        g.drawImage(playGameButtonImage, 450, 400, 350, 49, null);
        g.drawImage(exitImageButton, 580, 500, 92, 44, null);
    }


    private void paintBackground(Graphics g, int displayWidth, int displayHeight) {
        g.setColor(new Color(200, 245, 255));
        g.fillRect(0, 0, displayWidth + 500, displayHeight);
    }


    private void paintGameScene(Graphics g, GameScene gameScene) {
        if (gameScene == GameScene.GAMEOVER) {
            g.drawString("GAME OVER!", 100, 250);
        } else if (gameScene == GameScene.GAME) {
            paintScore(g);
            paintLevel(g);
            paintDiseaseInformation(g);
            BufferedImage menuButtonImage = null;
            try {
                menuButtonImage = ImageIO.read(getClass().getResourceAsStream("/textures/buttons/menu_button.png"));
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
            g.drawImage(menuButtonImage, 1124, 0, 140, 60, null);
        }
    }

    private void paintScore(Graphics g) {
        g.drawString("Score: " + String.valueOf(Player.instance.getScore()), 800, 895);
    }

    private void paintScoreFont(Graphics g) {
        g.setColor(Color.white);
        g.setFont(new Font("Arial", 1, 70));
    }

    private void paintLevel(Graphics g) {
        g.drawString("Level: " + String.valueOf(Player.instance.getLevel()), 50, 895);
    }

    private void paintDiseaseInformation(Graphics g) {
        g.drawString(Controller.controller.getDisease(), 200, 100);
        g.setColor(Color.white);
        g.setFont(new Font("Arial", 1, 80));
    }

    private void paintVitamins(Graphics g, ArrayList<Vitamin> generatedObjects) {
        generatedObjects.forEach(item -> paintSubstances(g, item.getShape(), item.getTextureName()));
    }

    private void paintSubstances(Graphics g, Rectangle column, String textureName) {
        BufferedImage image = null;
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/textures/vitamins/" + textureName + ".png"));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        g.drawImage(image, column.x, column.y, column.width, column.height, null);
    }

    private void paintPlayer(Graphics g, Player player) {
        BufferedImage image = null;
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/textures/playertexture.png"));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        g.drawImage(image, player.getPosX(), player.getPosY(), player.getSizeX(), player.getSizeY(), null);
    }
}