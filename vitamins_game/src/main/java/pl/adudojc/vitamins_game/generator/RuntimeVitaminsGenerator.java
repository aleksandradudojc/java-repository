package pl.adudojc.vitamins_game.generator;

import lombok.Getter;
import pl.adudojc.vitamins_game.model.Vitamin;

import java.util.ArrayList;


public class RuntimeVitaminsGenerator {

    public static RuntimeVitaminsGenerator instance = new RuntimeVitaminsGenerator();
    @Getter
    private ArrayList<Vitamin> vitaminList = new ArrayList<>();

    public void generateMoreVitamins(int ticks, int frequency) {
        if (ticks % frequency == 0) {
            vitaminList.add(new Vitamin());
            vitaminList.add(new Vitamin());
        }
    }

    public void removeAllGeneratedVitamins() {
        vitaminList.clear();
    }
}
