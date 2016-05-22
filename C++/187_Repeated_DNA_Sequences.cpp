// 187. Repeated DNA Sequences
/**
 * All DNA is composed of a series of nucleotides abbreviated as A, C, G, and T, for example: "ACGAATTCCG". When studying DNA, it is sometimes useful to identify repeated sequences within the DNA.
 * 
 * Write a function to find all the 10-letter-long sequences (substrings) that occur more than once in a DNA molecule.
 * 
 * For example,
 * 
 * Given s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT",
 * 
 * Return:
 * ["AAAAACCCCC", "CCCCCAAAAA"].
 * 
 * Tags: Hash Table, Bit Manipulation
 * 
 * Author: Kuang Qin
 */

#include "stdafx.h"
#include <iostream>
#include <vector>
#include <string>
#include <bitset>
#include <unordered_map>
#include <unordered_set>

using namespace std;

// this solution uses hash set
// time:  O(n)
// space: O(n)
class Solution {
public:
    // convert char to bitcode
    int char2val(char c) {
        switch (c)
        {
        case 'A':
            return 0;
        case 'C':
            return 1;
        case 'G':
            return 2;
        case 'T':
            return 3;
        }

        return 0;
    }

    vector<string> findRepeatedDnaSequences(string s) {
        vector<string> result;
        int n = s.size();
        if (n < 10)
        {
            return result;
        }

        unordered_set<int> bitset_once;     // if the string has occured once
        unordered_set<int> bitset_twice;    // if the string has occured twice

        for (int i = 0; i < n - 9; i++)
        {
            // encode the 10-letter string into bitcode
            int bitcode = 0;
            for (int j = i; j < i + 10; j++)
            {
                bitcode <<= 2;    // left shift 2 bit
                bitcode |= char2val(s[j]);    // or operation
            }

            if (bitset_twice.find(bitcode) != bitset_twice.end())
            {
                continue;
            }

            if (bitset_once.find(bitcode) != bitset_once.end())
            {
                result.push_back(s.substr(i, 10));
                bitset_twice.insert(bitcode);
            }
            else
            {
                bitset_once.insert(bitcode);
            }
        }

        return result;
    }
};

// this solution uses bitset class in c++
// time:  O(n)
// space: O(n)
class Solution_BitSet {
public:
    // convert char to bitcode
    int char2val(char c) {
        switch (c)
        {
        case 'A':
            return 0;
        case 'C':
            return 1;
        case 'G':
            return 2;
        case 'T':
            return 3;
        }

        return 0;
    }

    vector<string> findRepeatedDnaSequences(string s) {
        vector<string> result;
        int n = s.size();
        if (n < 10)
        {
            return result;
        }

        // use 2 bitsets, each has the size of 10 letter * 2 bits = 1 << 20
        bitset<1 << 20> set_once;     // if the string has occured once
        bitset<1 << 20> set_twice;    // if the string has occured twice

        int bitcode = 0;
        for (int i = 0; i < 10; i++)
        {
            // encode the first 10-letter string into bitcode
            bitcode <<= 2;    // left shift 2 bit
            bitcode |= char2val(s[i]);    // add current s[i] bitcode
        }
        set_once.set(bitcode);

        for (int i = 10; i < n; i++)
        {
            int mask = (1 << 20) - 1;    // the mask is 20 consecutive bits
            bitcode <<= 2;    // left shift 2 bit
            bitcode &= mask;    // erase the bitcode of s[i - 10] 
            bitcode |= char2val(s[i]);    // add the bitcode of s[i]

            if (set_twice[bitcode])    // continue if already occurs twice
            {
                continue;
            }
            
            if (set_once[bitcode])    // record the result if occurs only once before
            {
                result.push_back(s.substr(i - 10 + 1, 10));
                set_twice.set(bitcode);
            }
            else   // mark the first occurance
            {  
                set_once.set(bitcode);
            }
        }

        return result;
    }
};

int _tmain(int argc, _TCHAR* argv[])
{
    string s("AAAAAAAAAAAA");

    Solution_BitSet mySolution;
    vector<string> result = mySolution.findRepeatedDnaSequences(s);

    for (int i = 0; i < result.size(); i++)
    {
        cout << result[i] << endl;
    }
    system("pause");

    return 0;
}

