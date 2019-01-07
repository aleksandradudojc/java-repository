package pl.adudojc.vitamins_game.generator;

import lombok.Getter;
import pl.adudojc.vitamins_game.model.Vitamin;

import java.util.ArrayList;


public class RuntimeVitaminsGenerator {

    /*Obiekt klasy RuntimeVitaminsGenerator*/
    public static RuntimeVitaminsGenerator instance = new RuntimeVitaminsGenerator();
    @Getter
    private ArrayList<Vitamin> vitaminList = new ArrayList<>();


    /*Generowanie witamin w grze*/
    public void generateMoreVitamins(int second, int freq) {
        if (second % freq == 0) {
            vitaminList.add(new Vitamin());
            vitaminList.add(new Vitamin());
            vitaminList.add(new Vitamin());
        }
    }

    /*Usuwanie elementów z listy wygenerowanych witamin, wyczyszczenie gry*/
    public void removeAllGeneratedVitamins() {
        vitaminList.clear();
    }
}
