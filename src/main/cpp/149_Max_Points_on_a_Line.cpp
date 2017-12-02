// 149. Max Points on a Line
/**
 * Given n points on a 2D plane, find the maximum number of points that lie on the same straight line.
 *
 * Tags: Hash Table, Math
 *
 * Author: Kuang Qin
 */

#include "stdafx.h"
#include <vector>
#include <unordered_map>

using namespace std;

/**
 * Definition for a point.
 */
struct Point {
    int x;
    int y;
    Point() : x(0), y(0) {}
    Point(int a, int b) : x(a), y(b) {}
};

// Hash Table
// Time: O(n^2)
class Solution {
public:
    int maxPoints(vector<Point>& points) {
        int n = points.size();
        if (n < 2)
        {
            return n;
        }

        // use a hash table to store slopes of lines containing a specific point
        unordered_map<double, int> slope;
        int count = 0;    // results
        for (int i = 0; i < n - 1; i++)
        {
            int duplicates = 1;    // duplicate points counter
            int vertical = 0;    // vertical slope counter
            for (int j = i + 1; j < n; j++)
            {
                // duplicate points
                if (points[j].x == points[i].x && points[j].y == points[i].y)
                {
                    duplicates++;
                    continue;
                }

                // points on a vertical line
                if (points[j].x == points[i].x)
                {
                    vertical++;
                    continue;
                }

                // points on a certain slope
                float k = (float)(points[j].y - points[i].y) / (points[j].x - points[i].x);
                if (slope.find(k) == slope.end())
                {
                    slope[k] = 1;
                }
                else
                {
                    slope[k]++;
                }
            }

            // scan the hash table for the max value
            count = max(count, vertical + duplicates);
            for (auto it = slope.begin(); it != slope.end(); it++)
            {
                if (it->second + duplicates > count)
                {
                    count = it->second + duplicates;
                }
            }

            slope.clear();
        }

        return count;
    }
};

int _tmain(int argc, _TCHAR* argv[])
{
    return 0;
}

