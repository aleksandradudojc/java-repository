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

/*Klasa główna gry*/

public class Controller implements ActionListener, MouseListener, KeyListener {

    @Getter
    private String disease;

    /*Zmienna opisująca aktualną scene gry*/
    @Getter @Setter
    private GameScene gameScene;

    /*Obiektu klasy Controller*/
    public static Controller controller;

    /*Obiekt klasy View*/
    private View view;
    private int second;
    private double velX = 0, velY = 0;

    /*Zmienna, przechowująca położenie myszki*/
    private MouseVector mouseVector;

    /*Konstruktor klasy Controller*/
    public Controller() {
        createComponents();
        createJFrame();
    }

    /*Stworzenie komponentów gry*/
    private void createComponents() {
        view = new View();
        mouseVector = new MouseVector();
        gameScene = GameScene.VITAMINSSCREEN;
        disease = view.getDiseaseName();
        Timer timer = new Timer(10, this);
        timer.start();
        }

        /*Tworzenie okna aplikacji oraz nadanie mu rozmiaru*/
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

    /*Metoda główna*/
    public static void main(String[] args) {
        controller = new Controller();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        second++;

        if (gameScene == GameScene.GAME) {
            Player.instance.detectCollisions();
            if (Player.instance.getLevel() == 1) {
                RuntimeVitaminsGenerator.instance.generateMoreVitamins(second, 40);
            } else if (Player.instance.getLevel() == 2) {
                RuntimeVitaminsGenerator.instance.generateMoreVitamins(second, 10);
            }
            else if (Player.instance.getLevel() == 3) {
                RuntimeVitaminsGenerator.instance.generateMoreVitamins(second, 5);
            }
            setThePlayerPosition();
        }

        view.repaint();
    }

    /*Ustawienie pozycji gracza*/
    private void setThePlayerPosition() {
        int x = Player.instance.getPosX();
        int y = Player.instance.getPosY();
        x += velX;
        y += velY;
        Player.instance.setPosX(x);
        Player.instance.setPosY(y);
        Player.instance.refreshPlayerGraphics();
    }

    /*Metoda określająca akcje po naciśnięciu myszki w danym miejscu*/
    @Override
    public void mousePressed(MouseEvent e) {
        if (gameScene == GameScene.MENU) {
            if ((mouseVector.getMouseX() > 450 && mouseVector.getMouseX() < 800) && (mouseVector.getMouseY() > 400 && mouseVector.getMouseY() < 450)) {
                view.randomizeDescriptions();
                view.randomDiseaseFromDescription();
                Player.instance.createPlayer();
                RuntimeVitaminsGenerator.instance.removeAllGeneratedVitamins();
                Player.instance.setScore(0);
                Player.instance.setLevel(1);
                gameScene = GameScene.VITAMINSSCREEN;
            }

            if ((mouseVector.getMouseX() > 580 && mouseVector.getMouseX() < 672) && (mouseVector.getMouseY() > 500 && mouseVector.getMouseY() < 544)) {
                System.exit(0);
            }
        } else if (gameScene == GameScene.GAME) {
            if ((mouseVector.getMouseX() > 1124) && (mouseVector.getMouseY() < 60)) {
                gameScene = GameScene.MENU;
            }
        } else if (gameScene == GameScene.VITAMINSSCREEN) {
            if ((mouseVector.getMouseX() > 900 && mouseVector.getMouseX() < 1200) && (mouseVector.getMouseY() < 900)) {
                view.randomDiseaseFromDescription();
                disease = view.getDiseaseName();
                gameScene = GameScene.GAME;
            }
        }
    }


    /*Metody generowane przy okazji korzystania z ActionListenera*/
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

    /*Sterowanie graczem za pomocą strzałek z klawiatury*/
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

    /*Odczytywanie pozycji myszki*/
    private class MouseVector {
        public int getMouseX() {
            return (int) (MouseInfo.getPointerInfo().getLocation().getX() - view.getLocationOnScreen().getX());
        }

        public int getMouseY() {
            return (int) (MouseInfo.getPointerInfo().getLocation().getY() - view.getLocationOnScreen().getY());
        }
    }
}
