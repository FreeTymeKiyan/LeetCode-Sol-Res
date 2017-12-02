// 134. Gas Station
/**
 * There are N gas stations along a circular route, where the amount of gas at station i is gas[i].
 * 
 * You have a car with an unlimited gas tank and it costs cost[i] of gas to travel from station i to its next station (i+1). You begin the journey with an empty tank at one of the gas stations.
 * 
 * Return the starting gas station's index if you can travel around the circuit once, otherwise return -1.
 * 
 * Note:
 * The solution is guaranteed to be unique.
 * 
 * Tags: Greedy
 * 
 * Author: Kuang Qin
 */

#include "stdafx.h"
#include <vector>

using namespace std;

class Solution {
public:
    int canCompleteCircuit(vector<int>& gas, vector<int>& cost) {
        int n = gas.size();
        int start = 0, sum = 0, total = 0;

        // start: the starting point
        // sum:   remaining gas from start up to current station
        // total: overall remaining gas
        for (int i = 0; i < n; i++)
        {
            int remain = gas[i] - cost[i];    // remaining gas at station i
            sum += remain;
            total += remain;
            if (sum < 0)    // no gas at this point
            {
                sum = 0;    // reset sum
                start = i + 1;    // start from next station
            }
        }

        if (total < 0)    // unable to travel once if the total is negative
        {
            return -1;
        }
        
        // Conclusion:
        // If the total remaining gas is positive, the circuit can be travelled around once from the start position
        // (at any station, the cumulative remaining gas from the start station is positive)
        //
        // Proof:
        // from the for loop we know that from start to n the sum is positive, so
        //
        //     remain[start] + ... + remain[n - 1] >= 0
        //
        // also, it is the last time where sum < 0 at station (start - 1), it means that the cumulative total: 
        //
        //     remain[0] + ... + remain[start - 1] is the minimum
        //
        // so if the total remaining gas is positive, given any station j we have the wrap around total: 
        //
        //     remain[start] + ... + remain[n - 1] + remain[0] + ... + remain[j]
        //  >= remain[start] + ... + remain[n - 1] + remain[0] + ... + remain[start - 1]
        //   = remain[0] + remain[n - 1] = total > 0

        return start;
    }
};

int _tmain(int argc, _TCHAR* argv[])
{
    return 0;
}

