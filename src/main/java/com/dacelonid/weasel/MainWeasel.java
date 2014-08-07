package com.dacelonid.weasel;

import java.util.Random;

public class MainWeasel {
    private static final String TARGET_PHRASE = "Methinks it is like a weasel";
    private static Random rand;

    public static void main(final String[] args) {
        rand = new Random();
        String newPhrase = generateNewPhrase();
        while (!TARGET_PHRASE.equals(newPhrase)) {
            newPhrase = selectNextPhase(newPhrase);
            System.out.println(newPhrase);
        }
        System.out.println("done");
    }

    private static String selectNextPhase(final String newPhrase) {
        final String[] mutations = new String[100];
        for (int x = 0; x < 100; x++) {
            mutations[x] = getMutatedPhrase(newPhrase);
            System.out.println(mutations[x]);
        }
        String selectedPhrase = newPhrase;
        int score = getScore(newPhrase);
        for (int x = 0; x < 100; x++) {
            if (getScore(mutations[x]) > score) {
                score = getScore(mutations[x]);
                selectedPhrase = mutations[x];
            }
        }
        return selectedPhrase;
    }

    private static int getScore(final String string) {
        int score = 0;
        for (int x = 0; x < TARGET_PHRASE.length(); x++) {
            if (TARGET_PHRASE.charAt(x) == string.charAt(x)) {
                score++;
            }
        }
        return score;
    }

    private static String getMutatedPhrase(final String newPhrase) {
        final StringBuilder sb = new StringBuilder(newPhrase);
        for (int x = 0; x < newPhrase.length(); x++) {
            if (rand.nextDouble() < 0.05) {
                sb.replace(x, x + 1, getNextLetter());
            }
        }
        return sb.toString();
    }

    private static String generateNewPhrase() {
        String nextSuggestion = new String();
        while (targetPhraseIsNotLongEnough(nextSuggestion)) {
            nextSuggestion += getNextLetter();
        }
        return nextSuggestion;
    }

    private static boolean targetPhraseIsNotLongEnough(final String nextSuggestion) {
        return nextSuggestion.length() != TARGET_PHRASE.length();
    }

    /**
     * Get a character that is between 32 (space) and 126 (~) in the ASCII table
     */
    private static String getNextLetter() {
        final String letter = "" + (char) (rand.nextInt(94) + ' ');
        return letter;
    }
}
