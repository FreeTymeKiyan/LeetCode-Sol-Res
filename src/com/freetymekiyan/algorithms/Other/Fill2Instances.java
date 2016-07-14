/**
 * Given a number n, create an array of size 2n such that the array contains 2 
 * instances of every number from 1 to n, and the number of elements between 
 * two instances of a number i is equal to i. If such a configuration is not 
 * possible, then print the same.
 * 
 * Tags: Array, BackTracking
 */
class Fill2Instances {
    public static void main(String[] args) {
        Fill2Instances f = new Fill2Instances();
        f.fill(7);
    }
    
    /**
     * 
     */
    public void fill(int n) {
        int[] res = new int[2 * n];
        if (fill(n, n, res)) {
            for (int x : res) System.out.print(x + ", ");
        } else {
            System.out.println("Not possible");
        }
    }
    
    /**
     * Backtrack from position n to 0
     */
    boolean fill(int pos, int n, int[] res) {
        if (pos == 0) return true; // all set
        for (int i = 0; i < 2 * n - pos - 1; i++) { // i+pos+1 < 2n
            if (res[i] == 0 && res[i + pos + 1] == 0) { // both empty
                res[i] = pos; // set 
                res[i + pos + 1] = pos;
                if (fill(pos - 1, n, res)) return true;
                res[i] = 0; // reset
                res[i + pos + 1] = 0;
            }
        }
        return false;
    }
}
