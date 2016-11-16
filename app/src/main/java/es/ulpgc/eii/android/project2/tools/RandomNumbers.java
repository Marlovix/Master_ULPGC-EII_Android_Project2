package es.ulpgc.eii.android.project2.tools;

import java.util.Random;

public class RandomNumbers {

    // It returns a random number between two values //
    public static int showRandomInteger(int lower, int higher) {
        if (lower > higher) throw new IllegalArgumentException("Start cannot exceed End.");
        long range = (long) higher - (long) lower + 1;
        Random random = new Random();
        long fraction = (long) (range * random.nextDouble());
        return (int) (fraction + lower);
    }

    // //
    public static int[] getRandomNumbers(int numberOfRandoms, int lower, int higher){
        int[] randoms = new int[numberOfRandoms];
        for (int i=0; i<numberOfRandoms; i++) {
            int random = showRandomInteger(lower, higher);

            if (i == 0) randoms[0] = random;
            else if (randoms[i - 1] == random) randoms[i] = (random%higher) + 1;
            else randoms[i] = random;
        }

        return randoms;
    }

}
