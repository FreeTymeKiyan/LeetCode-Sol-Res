/**
 * There are N gas stations along a circular route, where the amount of gas at
 * station i is gas[i].
 * 
 * You have a car with an unlimited gas tank and it costs cost[i] of gas to
 * travel from station i to its next station (i+1). You begin the journey with
 * an empty tank at one of the gas stations.
 * 
 * Return the starting gas station's index if you can travel around the circuit
 * once, otherwise return -1.
 * 
 * Note:
 * The solution is guaranteed to be unique.
 * 
 * Tags: Greedy
 */
class GasStation {
    public static void main(String[] args) {
        
    }
    
    /**
     * Use restGas to store the gas left for current trip
     * Use previous to store the gas needed for previous trips
     * Go through the list and calculate restGas
     * If restGas < 0, update previous, reset restGas, set start index from next
     * If previous + restGas >= 0, which means there is a solution, return start
     * Otherwie can't make the trip, return -1
     */
    public static int canCompleteCircuit(int[] gas, int[] cost) {
        int restGas = 0; // gas remain for current trip
        int previous = 0; // negative gas for previous trips
        int start = 0; // start index of current trip
        for (int i = 0; i < gas.length; i++) {
            restGas += gas[i] - cost[i];
            if (restGas < 0) {
                previous += restGas; // gas needed for previous trips
                restGas = 0; // reset restGas
                start = i + 1; // set start index to next station
            }
        }
        return previous + restGas >= 0 ? start : -1;
    }
}
