package com.freetymekiyan.algorithms.other;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a dictionary, a method to do lookup in dictionary and a M x N board where every cell has one character. Find
 * all possible words that can be formed by a sequence of adjacent characters. Note that we can move to any of 8
 * adjacent characters, but a word should not have multiple instances of same cell.
 *
 * Input: words[] = {"GEEKS", "FOR", "QUIZ", "GO"};
 * boggle[][]   = {{'G','I','Z'},
 * {'U','E','K'},
 * {'Q','S','E'}};
 *
 * Output:  Following words are present
 * GEEKS
 * QUIZ
 *
 * http://www.geeksforgeeks.org/boggle-find-possible-words-board-characters/
 */
public class FindWords {

    private static final int M = 3;
    private static final int N = 3;
    private static String[] words = {"GEEKS", "FOR", "QUIZ", "GO"};
    private static char[][] boggle = {
            {'G', 'I', 'Z'},
            {'U', 'E', 'K'},
            {'Q', 'S', 'E'}
    };

    public static void main(String[] args) {
        FindWords fw = new FindWords();
        List<String> words = fw.findWords(boggle, FindWords.words);
        System.out.println(words);
    }

    private boolean isWord(String s) {
        for (String word : words) {
            if (word.equalsIgnoreCase(s)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Do a DFS for each character in the given matrix
     */
    public List<String> findWords(char[][] boggle, String[] words) {
        // build a boolean matrix to track character usage
        boolean[][] visited = new boolean[words.length][words[0].length()];
        String str = "";
        // iterate each char and do DFS
        List<String> ans = new ArrayList<>();
        for (int i = 0; i < boggle.length; i++)
            for (int j = 0; j < boggle[i].length; j++)
                findWordsHelper(boggle, visited, i, j, str, ans);
        return ans;
    }

    private void findWordsHelper(char[][] boggle, boolean[][] visited, int i, int j, String str, List<String> ans) {
        // Mark current cell as visited and append current character to str
        visited[i][j] = true;
        str = str + boggle[i][j];

        // If str is present in dictionary, then print it
        if (isWord(str))
            ans.add(str);

        // Traverse 8 adjacent cells of boggle[i][j]
        for (int row = i - 1; row <= i + 1 && row < M; row++)
            for (int col = j - 1; col <= j + 1 && col < N; col++)
                if (row >= 0 && col >= 0 && !visited[row][col])
                    findWordsHelper(boggle, visited, row, col, str, ans);

        // Erase current character from string and mark visited of current cell as false
        visited[i][j] = false;
        str = str.substring(0, str.length() - 1);
    }
}
