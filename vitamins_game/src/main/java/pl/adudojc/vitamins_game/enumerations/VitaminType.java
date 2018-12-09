package pl.adudojc.vitamins_game.enumerations;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public enum VitaminType {

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

    private static final List<VitaminType> VALUES =
            Collections.unmodifiableList(Arrays.asList(values()));

    private static final int SIZE = VALUES.size();

    private static final Random RANDOM = new Random();

    public static VitaminType randomTypeOfVitamin() {
        return VALUES.get(RANDOM.nextInt(SIZE));
    }

    public int getPointsOfRandomVitaminType(VitaminType vitaminType) {
        return vitaminType.getPoints();
    }
}