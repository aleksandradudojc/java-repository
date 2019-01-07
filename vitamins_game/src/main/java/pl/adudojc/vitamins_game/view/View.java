package pl.adudojc.vitamins_game.view;

import lombok.Getter;
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
import java.util.List;
import java.util.Random;


/**
 *Obszar graficzny gry, klasa dziedzicząca po JPanel
 */
public class View extends JPanel {

    /**
     *Pierwsza wyswietlana informacja o witaminach
     */
    private BufferedImage description1;

    /**
     *Druga wyswietlana informacja o witaminach
     */
    private BufferedImage description2;


    /**
     *Pierwszy losowany indeks listy Descriptions
     */
    private int randIndex1;

    /**
     *Drugi losowany indeks listy Descriptions
     */
    private int randIndex2;

    @Getter
    /**
     *Nazwa choroby, przypisywanej do danego indeksu losowanego (randIndex1-2)
     */
    private String diseaseName;


    /**
     *Lista opisow witamin
     */
    private List<BufferedImage> descriptions;


    /**
     *Konstruktor pola graficznego gry
     * Przypisanie wartosci z funkcji randomizeDescriptions do listy descriptions
     * Wywolanie metody randomDiseaseFromDescriptions
     */
    public View() {
        descriptions = randomizeDescriptions();
        randomDiseaseFromDescription();
    }


    /**
     *Metoda przypisujaca chorobe do danego, wylosowanego indeksu
     */

    public void randomDiseaseFromDescription()
    {
        if(randIndex1 == 0)
        {
            diseaseName = "Kurza slepota";
        }

        if(randIndex1 == 1)
        {
            diseaseName = "Osteoporoza";
        }

        if(randIndex1 == 2)
        {
            diseaseName = "Szkorbut";
        }
    }


    /**
     * Metoda losujaca wyswietlane na poczatku gry opisy witamin
     *
     * @return      lista buforowanych zdjec z opisami witamin
     */
    public List<BufferedImage> randomizeDescriptions(){
        List<BufferedImage> descriptions = new ArrayList<>();

        BufferedImage vitaminA_description = null;
        BufferedImage vitaminD_description = null;
        BufferedImage vitaminC_description = null;

        try {
            vitaminA_description = ImageIO.read(getClass().getResourceAsStream("/textures/vitamins_descriptions/vitaminA_description.png"));
            vitaminD_description = ImageIO.read(getClass().getResourceAsStream("/textures/vitamins_descriptions/vitaminD_description.png"));
            vitaminC_description = ImageIO.read(getClass().getResourceAsStream("/textures/vitamins_descriptions/vitaminC_description.png"));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        descriptions.add(vitaminA_description);
        descriptions.add(vitaminD_description);
        descriptions.add(vitaminC_description);

        randIndex1 = new Random().nextInt(descriptions.size());
        randIndex2 = new Random().nextInt(descriptions.size());

        description1 = descriptions.get((randIndex1));
        description2 = descriptions.get((randIndex2));

        return descriptions;
    }



    /**
     * Rysowanie komponentow gry
     *
     * @param g obiekt odpowiedzialny za grafike
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        repaint(g);
    }


    /**
     * Odświezanie gry
     *
     * @param g obiekt odpowiedzialny za grafike
     */
    public void repaint(Graphics g) {
        if (Controller.controller.getGameScene() == GameScene.MENU) {
            paintMenu(g, 1280, 1024);
        } else if (Controller.controller.getGameScene() == GameScene.GAME) {
            paintBackground(g, 904, 1024);
            paintScoreFont(g);
            paintGameplay(g, Controller.controller.getGameScene());
            paintVitamins(g, RuntimeVitaminsGenerator.instance.getVitaminList());
            paintPlayer(g, Player.instance);
            if(Player.instance.getNumberOfBadChoices() == 3)
            {
                g.drawString("GAME OVER!", 400, 500);
            }

        } else if (Controller.controller.getGameScene() == GameScene.VITAMINSSCREEN) {
            paintVitaminsScreen(g, 1280, 1024);
        }
    }


    /**
     * Porownywanie wylosowanych opisow, aby nie zostaly wyswietlone dwa takie same
     *
     * @param img1 pierwszy obrazek z opisem
     * @param img1 drugi obrazek z opisem
     *
     * @return wynik porownywania obrazow
     * */
    private boolean bufferedImagesEqual(BufferedImage img1, BufferedImage img2) {
        if (img1.getWidth() == img2.getWidth() && img1.getHeight() == img2.getHeight()) {
            for (int x = 0; x < img1.getWidth(); x++) {
                for (int y = 0; y < img1.getHeight(); y++) {
                    if (img1.getRGB(x, y) != img2.getRGB(x, y))
                        return false;
                }
            }
        } else {
            return false;
        }
        return true;
    }


    /**
     * Rysowanie wygladu pierwszego ekranu
     *
     * @param g obiekt odpowiedzialny za grafike
     * @param displayHeight wysokosc ekranu
     * @param displayWidth szerokosc ekranu
     *
     * @return wynik porownywania obrazow
     * */
    private void paintVitaminsScreen(Graphics g, int displayWidth, int displayHeight) {
        g.setColor(Color.white);
        g.fillRect(0, 0, displayWidth, displayHeight);

        BufferedImage nextButtonImage = null;

        try {
            nextButtonImage = ImageIO.read(getClass().getResourceAsStream("/textures/buttons/next_button.png"));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        g.drawImage(nextButtonImage, 1000, 800, 200, 74, null);
        paintDescribes(g, 1240, 1024);
    }



    /**
     * Wyświetlanie wylosowanych opisów
     *
     * @param g obiekt odpowiedzialny za grafike
     * @param displayHeight wysokosc ekranu
     * @param displayWidth szerokosc ekranu
     *
     * */
    private void paintDescribes(Graphics g, int displayWidth, int displayHeight) {
        g.drawImage(description1, 100, 100, 676, 172, null);

        boolean bufferedImagesEqual = bufferedImagesEqual(description1, description2);

        do {
            if (bufferedImagesEqual == false) {
                g.drawImage(description2, 100, 350, 676, 172, null);
            } else {
                description2 = descriptions.get((randIndex2));
            }
        } while (bufferedImagesEqual == true);
    }


    /**
     * Rysowanie Menu gry
     *
     * @param g obiekt odpowiedzialny za grafike
     * @param displayHeight wysokosc ekranu
     * @param displayWidth szerokosc ekranu
     *
     * */
    private void paintMenu(Graphics g, int displayWidth, int displayHeight) {

        g.setColor(Color.white);
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


    /**
     * Rysowanie tla gry
     *
     * @param g obiekt odpowiedzialny za grafike
     * @param displayHeight wysokosc ekranu
     * @param displayWidth szerokosc ekranu
     *
     * */
    private void paintBackground(Graphics g, int displayWidth, int displayHeight) {
        g.setColor(new Color(200, 245, 255));
        g.fillRect(0, 0, displayWidth + 500, displayHeight);
    }


    /**
     * Rysowanie glownego ekranu gry
     *
     * @param g obiekt odpowiedzialny za grafike
     * @param gameScene scena gry
     *
     * */
    private void paintGameplay(Graphics g, GameScene gameScene) {
         if (gameScene == GameScene.GAME) {
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


    /**
     * Rysowanie zdobytych puntow
     *
     * @param g obiekt odpowiedzialny za grafike
     *
     * */
    private void paintScore(Graphics g) {
        g.drawString("Score: " + String.valueOf(Player.instance.getScore()), 800, 895);
    }

    /**
     * Rysowanie, ustalonej czcionki
     *
     * @param g obiekt odpowiedzialny za grafike
     *
     * */
    private void paintScoreFont(Graphics g) {
        g.setColor(Color.white);
        g.setFont(new Font("Arial", 1, 70));
    }


    /**
     * Rysowanie poziomu gry
     *
     * @param g obiekt odpowiedzialny za grafike
     *
     * */
    private void paintLevel(Graphics g) {
        g.drawString("Level: " + String.valueOf(Player.instance.getLevel()), 50, 895);
    }


    /**
     * Rysowanie nazwy choroby
     *
     * @param g obiekt odpowiedzialny za grafike
     *
     * */
    private void paintDiseaseInformation(Graphics g) {
        g.drawString(Controller.controller.getDisease(), 200, 100);
        g.setColor(Color.white);
        g.setFont(new Font("Arial", 1, 80));
    }


    /**
     * Rysowanie  witamin
     *
     * @param g obiekt odpowiedzialny za grafike
     * @param generatedObjects lista generowanych obiektow
     *
     * */
    private void paintVitamins(Graphics g, ArrayList<Vitamin> generatedObjects) {
        generatedObjects.forEach(item -> paintSubstances(g, item.getShape(), item.getVitaminName()));
    }


    /**
     * Rysowanie witamin podczas trwania gry
     *
     * @param g obiekt odpowiedzialny za grafike
     * @param substance obiekt substancji, typu Rectangle
     *
     * */
    private void paintSubstances(Graphics g, Rectangle substance, String vitaminName) {
        BufferedImage image = null;
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/textures/vitamins/" + vitaminName + ".png"));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        g.drawImage(image, substance.x, substance.y, substance.width, substance.height, null);
    }


    /**
     * Rysowanie gracza/ pacjenta
     *
     * @param g obiekt odpowiedzialny za grafike
     * @param player obiekt typu Player
     *
     * */
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