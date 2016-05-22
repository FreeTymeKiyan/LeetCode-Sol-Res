// 122. Best Time to Buy and Sell Stock II
/**
 * Say you have an array for which the ith element is the price of a given stock on day i.
 *
 * Design an algorithm to find the maximum profit. You may complete as many transactions as you like (ie, buy one and sell one share of the stock multiple times). 
 * However, you may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).
 *
 * Tags: Array, Greedy
 *
 * Similar Problems: (M) Best Time to Buy and Sell Stock, (H) Best Time to Buy and Sell Stock III,
 *                   (H) Best Time to Buy and Sell Stock IV, (M) Best Time to Buy and Sell Stock with Cooldown
 *
 * Author: Kuang Qin
 */

#include "stdafx.h"
#include <iostream>
#include <vector>

using namespace std;

class Solution {
public:
    int maxProfit(vector<int>& prices) {
        int n = prices.size();
        if (n < 2)
        {
            return 0;
        }

        int res = 0;
        for (int i = 1; i < n; i++)
        {
            // add to profit if the price is increasing
            if (prices[i] > prices[i - 1])
            {
                res += prices[i] - prices[i - 1];
            }
        }

        return res;
    }
};

int _tmain(int argc, _TCHAR* argv[])
{
    vector<int> profit;
    profit.push_back(4);
    profit.push_back(2);
    profit.push_back(3);
    profit.push_back(1);

    Solution mySolution;
    int result = mySolution.maxProfit(profit);
    cout << result << endl;
    system("pause");

    return 0;
}

