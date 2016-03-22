//190. Reverse Bits
/*
 *Reverse bits of a given 32 bits unsigned integer.
 *
 *For example, given input 43261596 (represented in binary as 00000010100101000001111010011100), return 964176192 (represented in binary as 00111001011110000010100101000000).
 *
 *Follow up:
 *If this function is called many times, how would you optimize it?
 *
 *Related problem: Reverse Integer
 *
 *Tag: Bit Manipulation
 *
 *Author: Linsen Wu
 */

#include "stdafx.h"
#include <stdint.h>

class Solution {
public:
    uint32_t reverseBits(uint32_t n) {
        uint32_t left = 0x80000000;
		uint32_t right = 0x00000001;

		for (int i = 0; i < 16; i++)
		{
			uint32_t leftbit = n & left;
			uint32_t rightbit = n & right;
			leftbit = leftbit >> 31-i*2;
			rightbit = rightbit << 31-i*2;

			n = n & ~left & ~right;
			n = n | leftbit | rightbit;

			left = left >> 1;
			right = right << 1;

		}

		return n;
    }
};


int _tmain(int argc, _TCHAR* argv[])
{
	uint32_t input = 0x80000000;

	Solution _solution;
	_solution.reverseBits(input);

	return 0;
}

