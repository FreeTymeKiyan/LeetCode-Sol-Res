import java.util.ArrayList;
import java.util.List;

/**
 * Count the number of prime numbers less than a non-negative number, n.
 *
 * Tags: Hash Table, Math
 * Similar Problems: (E) Ugly Number (M) Ugly Number II (M) Perfect Squares
 */
public class CountPrimes {

    public int countPrimes(int n) {
        if (n <= 2) return 0;

        int res = n >> 1; // remove even numbers
        int m = (int) Math.sqrt(n - 1); // p <= sqrt(n)
        boolean[] notPrime = new boolean[n];

        for (int i = 3; i <= m; i += 2) { // traverse odd numbers only
            if (!notPrime[i]) {
                for (int j = i * i, step = i << 1; j < n; j += step) { // p^2, p^2 + p, p^2 + 2p ...
                    if (!notPrime[j]) {
                        notPrime[j] = true;
                        res--;
                    }
                }
            }
        }

        return res;
    }

    public static void main(String[] args) {
        CountPrimes cp = new CountPrimes();
        for (int i = 0; i < 1001; i++)
            System.out.println(i + " : "+ cp.countPrimes(i));
    }

}
