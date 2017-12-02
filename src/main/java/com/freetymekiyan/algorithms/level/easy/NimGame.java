package com.freetymekiyan.algorithms.level.easy;

/**
 * You are playing the following Nim Game with your friend: There is a heap of stones on the table, each time one of you
 * take turns to remove 1 to 3 stones. The one who removes the last stone will be the winner. You will take the first
 * turn to remove the stones.
 *
 * Both of you are very clever and have optimal strategies for the game. Write a function to determine whether you can
 * win the game given the number of stones in the heap.
 *
 * For example, if there are 4 stones in the heap, then you will never win the game: no matter 1, 2, or 3 stones you
 * remove, the last stone will always be removed by your friend.
 *
 * Hint:
 *
 * If there are 5 stones in the heap, could you figure out a way to remove the stones such that you will always be the
 * winner?
 *
 * Tags: Brainteaser
 *
 * Similar Problems: (M) Flip Game II
 */
public class NimGame {

    /**
     * when n is 4, no matter you remove 1 or 2 or 3 stones, you always lose
     * suppose n = 4k, always lose
     * when k is k+1, n is 4k+4, which is 4 more stones to remove
     * no matter 1 or 2 or 3 stones you remove, your opponent can still make you 4k stones
     * for n = 4k+1, 4k+2, 4k+3, you can always remove 1, 2, 3 stones, relatively, to win
     */
    public boolean canWinNim(int n) {
        return n % 4 != 0;
    }
}
