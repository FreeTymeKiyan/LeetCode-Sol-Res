package com.freetymekiyan.algorithms.level.medium;

import org.testng.Assert;
import org.testng.annotations.Test;

public class SentenceSimilarity2Test {

    @Test
    public void testAreSentencesSimilarTwo() {
        String[] words1 = {"great", "acting", "skills"};
        String[] words2 = {"fine", "drama", "talent"};
        String[][] pairs = {{"great", "good"}, {"fine", "good"}, {"acting", "drama"}, {"skills", "talent"}};

        SentenceSimilarity2 s = new SentenceSimilarity2();
        Assert.assertTrue(s.areSentencesSimilarTwo(words1, words2, pairs));

        words1 = new String[]{"great"};
        words2 = new String[]{"great"};
        pairs = new String[][]{};
        Assert.assertTrue(s.areSentencesSimilarTwo(words1, words2, pairs));

        words1 = new String[]{"great", "acting", "skills"};
        words2 = new String[]{"fine", "painting", "talent"};
        pairs = new String[][]{{"great", "fine"}, {"drama", "acting"}, {"skills", "talent"}};
        Assert.assertFalse(s.areSentencesSimilarTwo(words1, words2, pairs));

        words1 = new String[]{"this", "summer", "thomas", "get", "really", "very", "rich", "and", "have", "any", "actually", "wonderful", "and", "good", "truck", "every", "morning", "he", "drives", "an", "extraordinary", "truck", "around", "the", "nice", "city", "to", "eat", "some", "extremely", "extraordinary", "food", "as", "his", "meal", "but", "he", "only", "entertain", "an", "few", "well", "fruits", "as", "single", "lunch", "he", "wants", "to", "eat", "single", "single", "and", "really", "healthy", "life"};
        words2 = new String[]{"this", "summer", "thomas", "get", "very", "extremely", "rich", "and", "possess", "the", "actually", "great", "and", "wonderful", "vehicle", "every", "morning", "he", "drives", "unique", "extraordinary", "automobile", "around", "unique", "fine", "city", "to", "drink", "single", "extremely", "nice", "meal", "as", "his", "super", "but", "he", "only", "entertain", "a", "few", "extraordinary", "food", "as", "some", "brunch", "he", "wants", "to", "take", "any", "some", "and", "really", "healthy", "life"};
        pairs = new String[][]{
                {"good", "nice"},
                {"good", "excellent"},
                {"good", "well"},
                {"good", "great"},
                {"fine", "nice"},
                {"fine", "excellent"},
                {"fine", "well"},
                {"fine", "great"},
                {"wonderful", "nice"},
                {"wonderful", "excellent"},
                {"wonderful", "well"},
                {"wonderful", "great"},
                {"extraordinary", "nice"},
                {"extraordinary", "excellent"},
                {"extraordinary", "well"},
                {"extraordinary", "great"},
                {"one", "a"},
                {"one", "an"},
                {"one", "unique"},
                {"one", "any"},
                {"single", "a"},
                {"single", "an"},
                {"single", "unique"},
                {"single", "any"},
                {"the", "a"},
                {"the", "an"},
                {"the", "unique"},
                {"the", "any"},
                {"some", "a"},
                {"some", "an"},
                {"some", "unique"},
                {"some", "any"},
                {"car", "vehicle"},
                {"car", "automobile"},
                {"car", "truck"},
                {"auto", "vehicle"},
                {"auto", "automobile"},
                {"auto", "truck"},
                {"wagon", "vehicle"},
                {"wagon", "automobile"},
                {"wagon", "truck"},
                {"have", "take"},
                {"have", "drink"},
                {"eat", "take"},
                {"eat", "drink"},
                {"entertain", "take"},
                {"entertain", "drink"},
                {"meal", "lunch"},
                {"meal", "dinner"},
                {"meal", "breakfast"},
                {"meal", "fruits"},
                {"super", "lunch"},
                {"super", "dinner"},
                {"super", "breakfast"},
                {"super", "fruits"},
                {"food", "lunch"},
                {"food", "dinner"},
                {"food", "breakfast"},
                {"food", "fruits"},
                {"brunch", "lunch"},
                {"brunch", "dinner"},
                {"brunch", "breakfast"},
                {"brunch", "fruits"},
                {"own", "have"},
                {"own", "possess"},
                {"keep", "have"},
                {"keep", "possess"},
                {"very", "super"},
                {"very", "actually"},
                {"really", "super"},
                {"really", "actually"},
                {"extremely", "super"},
                {"extremely", "actually"}};
        Assert.assertTrue(s.areSentencesSimilarTwo(words1, words2, pairs));
    }
}