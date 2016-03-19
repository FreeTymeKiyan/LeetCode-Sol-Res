//66. Plus One
/*
 *Given a non-negative number represented as an array of digits, plus one to the number.
 *
 *The digits are stored such that the most significant digit is at the head of the list.
 *
 *Tag: Array, Math
 *
 *Author: Linsen Wu
 */

#include "stdafx.h"
#include "iostream"
#include <vector>
#include <unordered_map>

using namespace std;

class Solution {
public:
    vector<int> plusOne(vector<int>& digits) {
        int n = digits.size();
		vector<int> result (digits.size(),0); 

		int sum = 0;
		int carry = 1;

		for (int i = n - 1; i >=0; i--)
		{
			sum = carry + digits[i];
			carry = sum/10;
			result[i] = sum%10;
		}

		if (carry > 0)
		{
			result.insert(result.begin(), carry);
		}

		return result;
    }
};

int _tmain(int argc, _TCHAR* argv[])
{
	return 0;
}

