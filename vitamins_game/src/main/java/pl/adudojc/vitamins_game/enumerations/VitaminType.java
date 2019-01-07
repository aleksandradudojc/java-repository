package pl.adudojc.vitamins_game.enumerations;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/*Wyliczenie zawierające typy witamin (dobre oraz złe)*/

public enum VitaminType {

    /*Przypisanie wartości punktowej za konkretny typ witaminy*/
    GOOD_VITAMIN {
        @Override
        public int getPoints() {
            return 10;
        }
    },

    BAD_VITAMIN {
        @Override
        public int getPoints() {
            return -10;
        }
    };


    abstract int getPoints();

    /*Niemodyfikowalna lista obiektów*/
    private static final List<VitaminType> VALUES =
            Collections.unmodifiableList(Arrays.asList(values()));

    /*Rozmiar listy VALUES*/
    private static final int SIZE = VALUES.size();

    /*Losowanie liczby*/
    private static final Random RANDOM = new Random();

    /*Losowanie typu witaminy z listy VALUES*/
    public static VitaminType randomTypeOfVitamin() {
        return VALUES.get(RANDOM.nextInt(SIZE));
    }

    /*Zwracanie punktów w zależności od witaminy*/
    public int getPointsOfRandomVitaminType(VitaminType vitaminType) {
        return vitaminType.getPoints();
    }
}

