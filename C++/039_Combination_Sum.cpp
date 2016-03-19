/*
* Given a set of candidate numbers (C) and a target number (T), find all unique
* combinations in C where the candidate numbers sums to T.

* The same repeated number may be chosen from C unlimited number of times.

* Note:
*   All numbers (including target) will be positive integers.
*  Elements in a combination (a1, a2, … , ak) must be in non-descending order. (ie, a1 ≤ a2 ≤ … ≤ ak).
*  The solution set must not contain duplicate combinations.
*  For example, given candidate set 2,3,6,7 and target 7, 
*  A solution set is: 
*  [7] 
*  [2, 2, 3] 

 */

#include <vector>
#include <algorithm>
using namespace std;

class Solution {
public:
    vector<vector<int>> combinationSum(vector<int>& candidates, int target) {
        // sort the candidate vector in ascending order;
        sort( candidates.begin(), candidates.end() );
        vector<vector<int> > results;
        vector<int> combination;
        sumIteration( candidates, target, results, combination, 0 );
        return results;
    }
    
private:
    void sumIteration( vector<int>& candidates, int target, vector<vector<int> >& results, vector<int>&combination, int begin )
    {
        if( !target )
        {
            results.push_back( combination );
            return;
        }
        for( int i = begin; i< candidates.size() && target >= candidates[i]; i++ )
        {
            combination.push_back( candidates[i] );
            sumIteration( candidates, target - candidates[i], results, combination, i );
            combination.pop_back();
        }
    }
};

int main( int argc, char ** argv )
{
    return 0;
}
