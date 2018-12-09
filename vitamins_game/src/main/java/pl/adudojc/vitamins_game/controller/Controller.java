package pl.adudojc.vitamins_game.controller;

import lombok.Getter;
import lombok.Setter;
import pl.adudojc.vitamins_game.enumerations.GameScene;
import pl.adudojc.vitamins_game.generator.RuntimeVitaminsGenerator;
import pl.adudojc.vitamins_game.model.Player;
import pl.adudojc.vitamins_game.view.View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Controller implements ActionListener, MouseListener, KeyListener {

    @Getter
    private String disease;
    @Getter
    @Setter
    private GameScene gameScene;

    public static Controller controller;
    private View view;
    private int ticks;
    private double velX = 0, velY = 0;
    private MouseVector mouseVector;
    private List<String> diseases = Arrays.asList("Szkorbut", "Osteoporoza", "Kurza slepota");

    public Controller() {
        createComponents();
        createJFrame();
    }

    private void createComponents() {
        view = new View();
        mouseVector = new MouseVector();
        gameScene = GameScene.GAME;
        int randIndex = new Random().nextInt(diseases.size());
        disease = diseases.get((randIndex));
        Timer timer = new Timer(10, this);
        timer.start();
    }

    private void createJFrame() {
        JFrame jframe = new JFrame();
        jframe.add(view);
        jframe.setTitle("Vitamins");
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.setSize(1280, 1024);
        jframe.addMouseListener(this);
        jframe.addKeyListener(this);
        jframe.setResizable(false);
        jframe.setVisible(true);
    }

    public static void main(String[] args) {
        controller = new Controller();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ticks++;

        if (gameScene == GameScene.GAME) {
            Player.instance.detectCollisions();
            RuntimeVitaminsGenerator.instance.generateMoreVitamins(ticks, 40);
            setThePlayerPosition();
        }
        view.repaint();
    }

    private void setThePlayerPosition() {
        int x = Player.instance.getPosX();
        int y = Player.instance.getPosY();
        x += velX;
        y += velY;
        Player.instance.setPosX(x);
        Player.instance.setPosY(y);
        Player.instance.refreshPlayerGraphics();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (gameScene == GameScene.MENU) {
            if ((mouseVector.getMouseX() > 450 && mouseVector.getMouseX() < 800) && (mouseVector.getMouseY() > 400 && mouseVector.getMouseY() < 450)) {
                Player.instance.createPlayer();
                RuntimeVitaminsGenerator.instance.removeAllGeneratedVitamins();
                Player.instance.setScore(0);
                int randIndex = new Random().nextInt(diseases.size());
                disease = diseases.get((randIndex));
                gameScene = GameScene.GAME;
            }

            if ((mouseVector.getMouseX() > 580 && mouseVector.getMouseX() < 672) && (mouseVector.getMouseY() > 500 && mouseVector.getMouseY() < 544)) {
                System.exit(0);
            }
        } else if (gameScene == GameScene.GAME) {
            if ((mouseVector.getMouseX() > 1124) && (mouseVector.getMouseY() < 60)) {
                gameScene = GameScene.MENU;
            }
        }
    }


    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_UP) {
            velY = -12;
            velX = 0;
        }
        if (code == KeyEvent.VK_DOWN) {
            velY = 12;
            velX = 0;
        }
        if (code == KeyEvent.VK_LEFT) {
            velY = 0;
            velX = -12;
        }
        if (code == KeyEvent.VK_RIGHT) {
            velY = 0;
            velX = 12;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_UP) {
            velY = 0;
            velX = 0;
        }
        if (code == KeyEvent.VK_DOWN) {
            velY = 0;
            velX = 0;
        }
        if (code == KeyEvent.VK_LEFT) {
            velY = 0;
            velX = 0;
        }
        if (code == KeyEvent.VK_RIGHT) {
            velY = 0;
            velX = 0;
        }
    }

    private class MouseVector {
        public int getMouseX() {
            return (int) (MouseInfo.getPointerInfo().getLocation().getX() - view.getLocationOnScreen().getX());
        }

        public int getMouseY() {
            return (int) (MouseInfo.getPointerInfo().getLocation().getY() - view.getLocationOnScreen().getY());
        }
    }
}
