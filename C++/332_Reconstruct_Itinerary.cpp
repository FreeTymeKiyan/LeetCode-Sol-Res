//332. Reconstruct Itinerary
/*
Given a list of airline tickets represented by pairs of departure and arrival airports [from, to], reconstruct the itinerary in order. All of the tickets belong to a man who departs from JFK. Thus, the itinerary must begin with JFK.

Note:
If there are multiple valid itineraries, you should return the itinerary that has the smallest lexical order when read as a single string. For example, the itinerary ["JFK", "LGA"] has a smaller lexical order than ["JFK", "LGB"].
All airports are represented by three capital letters (IATA code).
You may assume all tickets form at least one valid itinerary.

Example 1:
tickets = [["MUC", "LHR"], ["JFK", "MUC"], ["SFO", "SJC"], ["LHR", "SFO"]]
Return ["JFK", "MUC", "LHR", "SFO", "SJC"].

Example 2:
tickets = [["JFK","SFO"],["JFK","ATL"],["SFO","ATL"],["ATL","JFK"],["ATL","SFO"]]
Return ["JFK","ATL","JFK","SFO","ATL","SFO"].
Another possible reconstruction is ["JFK","SFO","ATL","JFK","ATL","SFO"]. But it is larger in lexical order.

Tag: Depth First Search, Graph

Author: Xinyu Liu
*/

#include <utility>      // std::pair
#include <iostream>     // std::cout
#include <vector> 
#include <string>
//#include <algorithm>    // std::lexicographical_compare
#include <cctype>       // std::tolower
#include <unordered_map>
#include <map>
using namespace std;
class Solution {
public:
    vector<string> findItinerary(vector<pair<string, string>> tickets) {
        unordered_map<string, map<string, int>> m;
        for (int i = 0; i < tickets.size(); i++){
            m[tickets.at(i).first][tickets.at(i).second]++;
        }
        string from = "JFK";
        vector <string> itinerary;
        itinerary.push_back(from);
        findpath(from, itinerary, tickets.size()+1 , m);
        return (itinerary);
    }
    bool findpath(string from, vector<string>& itinerary, int sz, unordered_map<string, map<string, int>> &m){
        if (itinerary.size() == sz) 
            return true;
        for (auto index = m[from].begin(); index !=  m[from].end(); index++){
            if (index->second != 0){
                itinerary.push_back(index->first);
                index->second --;
                if (findpath(index->first, itinerary, sz , m)) return true;
                itinerary.pop_back();
                index->second ++;
            }
        }
        return false;
    }
};

void main () {

    pair <string, string> itinerary0,itinerary1,itinerary2,itinerary3,itinerary4;
    vector<string>;

    // [["JFK","KUL"],["JFK","NRT"],["NRT","JFK"]]
    vector<pair<string, string>> tickets;
    itinerary0 = make_pair ("JFK","NRT");
    itinerary1 = make_pair ("JFK","KUL");
    itinerary2 = make_pair ("NRT","JFK");

    tickets.push_back(itinerary0);
    tickets.push_back(itinerary1);
    tickets.push_back(itinerary2);

    Solution sol;
    vector<string> itinerary;
    itinerary = sol.findItinerary(tickets);
}
