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
     * Use restGas to store the gas left
     * Use previous to store the gas needed
     * Go through the list and calculate restGas
     * if restGas < 0, add previous gas, reset restGas and start index
     * Return start if previous + restGas >= 0, which means there is a solution
     * Otherwie return -1
     */
    public static int canCompleteCircuit(int[] gas, int[] cost) {
        int restGas = 0;
        int previous = 0;
        int start = 0;
        for (int i = 0; i < gas.length; i++) {
            restGas += gas[i] - cost[i];
            if (restGas < 0) {
                previous += restGas; // gas remain
                restGas = 0; // gas for this trip
                start = i + 1; // reset start indexs
            }
        }
        return previous + restGas >= 0 ? start : -1; // has a solution or not
    }
}
