package com.freetymekiyan.algorithms.level.medium;

/**
 * There are n bulbs that are initially off. You first turn on all the bulbs. Then, you turn off every second bulb. On
 * the third round, you toggle every third bulb (turning on if it's off or turning off if it's on). For the ith round,
 * you toggle every i bulb. For the nth round, you only toggle the last bulb. Find how many bulbs are on after n
 * rounds.
 * <p>
 * Example:
 * <p>
 * Given n = 3.
 * <p>
 * At first, the three bulbs are [off, off, off].
 * After first round, the three bulbs are [on, on, on].
 * After second round, the three bulbs are [on, off, on].
 * After third round, the three bulbs are [on, off, off].
 * <p>
 * So you should return 1, because there is only one bulb is on.
 * <p>
 * Tags: Math, Brainteaser
 */
public class BulbSwitch {

    /**
     * Math.
     * A bulb ends up on if it's switched an odd number of times.
     * Bulb i is switched in round d iff d divides i.
     * So bulb i ends up on iff it has an odd number of divisors.
     * Divisors come in pairs, except when i is a square.
     * So just count the square numbers from 1 to n.
     * Possible square roots range from 1 to x, where x^2 <= n.
     * So x = int(sqrt(n)).
     */
    public int bulbSwitch(int n) {
        return (int) Math.sqrt(n);
    }

}
