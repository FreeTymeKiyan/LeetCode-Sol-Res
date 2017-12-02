// 126. Word Ladder II
/**
 * Given two words (beginWord and endWord), and a dictionary's word list, find all shortest transformation sequence(s) from beginWord to endWord, such that:
 * 
 * 1. Only one letter can be changed at a time
 * 2. Each intermediate word must exist in the word list
 * 
 * For example,
 * Given:
 * beginWord = "hit"
 * endWord = "cog"
 * wordList = ["hot","dot","dog","lot","log"]
 *
 * Return
 *   [
 *     ["hit","hot","dot","dog","cog"],
 *     ["hit","hot","lot","log","cog"]
 *   ]
 * 
 * Note:
 * All words have the same length.
 * All words contain only lowercase alphabetic characters.
 * 
 * Tags: Array, Backtracking, Breadth-first Search, String
 * 
 * Author: Kuang Qin
 */

#include "stdafx.h"
#include <vector>
#include <string>
#include <unordered_set>
#include <unordered_map>
#include <queue>
#include <iostream>

using namespace std;

// BFS + DFS
class Solution {
    // find all possible transformations of word in wordList
    vector<string> transform(string &word, unordered_set<string> &wordList) {
        vector<string> transList;
        for (int i = 0; i < word.size(); i++)
        {
            for (char ch = 'a'; ch <= 'z'; ch++)
            {
                if (word[i] != ch)    // skip the same word
                {
                    string str(word);
                    str[i] = ch;
                    if (wordList.find(str) != wordList.end())
                    {
                        // if the transformed word is in wordList, add to result
                        transList.push_back(str);
                    }
                }
            }
        }

        return transList;
    }

    // create a queue to track the distance of each word in wordList from beginWord
    void bfs(unordered_map<string, vector<string>> &transMap, unordered_map<string, int> &distMap, 
                string &beginWord, string &endWord, unordered_set<string> &wordList) {
        queue<string> q;
        q.push(beginWord);

        // make sure beginWord and endWord is in wordList
        wordList.insert(beginWord);
        wordList.insert(endWord);

        // start from the beginWord
        distMap[beginWord] = 0;

        while (!q.empty())
        {
            int count = q.size();
            bool isEndFound = false;    // flag if found the endWord
            for (int i = 0; i < count; i++)    // bfs by level
            {
                string word = q.front();
                q.pop();

                vector<string> transList = transform(word, wordList);
                vector<string> *curList = &transMap[word];
                for (int j = 0; j < transList.size(); j++)
                {
                    string str(transList[j]);

                    // update possible transformations for each word in transList
                    curList->push_back(str);

                    // update distance for each word in transList if it is not in distMap
                    if (distMap.find(str) == distMap.end())
                    {
                        distMap[str] = distMap[word] + 1;
                        if (str == endWord)
                        {
                            // set flag
                            isEndFound = true;
                        }
                        else
                        {
                            q.push(str);
                        }
                    }
                }

                if (isEndFound)
                {
                    // quit current level, go to next level
                    break;
                }
            }
        }

        return;
    }

    void dfs(vector<vector<string>> &res, vector<string> &path, unordered_map<string, vector<string>> &transMap, 
                unordered_map<string, int> &distMap, string &curWord, string &endWord) {
        if (curWord == endWord)
        {
            // found the endWord, record result
            path.push_back(curWord);
            res.push_back(path);
            path.pop_back();
            return;
        }

        for (int i = 0; i < transMap[curWord].size(); i++)
        {
            string nextWord(transMap[curWord][i]);

            // search in transformations for word with +1 distance
            if (distMap.find(nextWord) != distMap.end() && distMap[nextWord] == distMap[curWord] + 1)
            {
                path.push_back(curWord);
                dfs(res, path, transMap, distMap, nextWord, endWord);
                path.pop_back();
            }
        }

        return;
    }

public:
    vector<vector<string>> findLadders(string beginWord, string endWord, unordered_set<string> &wordList) {
        vector<vector<string>> res;
        vector<string> path;
        if (beginWord == endWord)
        {
            path.push_back(beginWord);
            res.push_back(path);
            return res;
        }

        // a hash map to map a string to all its possible transformations in wordList
        unordered_map<string, vector<string>> transMap;

        // a hash map to map a string to its distance from start word
        unordered_map<string, int> distMap;

        // bfs first to build two hash maps
        bfs(transMap, distMap, beginWord, endWord, wordList);

        // dfs to search in two hash maps and output results
        dfs(res, path, transMap, distMap, beginWord, endWord);

        return res;
    }
};

// Bidirectional BFS + DFS
class Solution_BiBFS {
    void connect(string &fwdWord, string &bckWord, bool isForward, 
                    unordered_map<string, vector<string>> &transMap) {
        if (isForward)
        {
            // connect backward to forward
            transMap[fwdWord].push_back(bckWord);
        }
        else
        {
            // connect forward to backward
            transMap[bckWord].push_back(fwdWord);
        }

        return;
    }

    // looking for connection from both ends
    bool bfs(unordered_set<string> &forward, unordered_set<string> &backward, bool isForward, 
                unordered_set<string> &wordList, unordered_map<string, vector<string>> &transMap) {
        if (forward.empty() || backward.empty())
        {
            return false;
        }

        // always do bfs with less nodes
        if (forward.size() > backward.size())
        {
            return bfs(backward, forward, !isForward, wordList, transMap);
        }

        // remove added node in the wordList to avoid duplication
        unordered_set<string>::iterator it;
        for (it = forward.begin(); it != forward.end(); it++)
        {
            wordList.erase(*it);
        }

        for (it = backward.begin(); it != backward.end(); it++)
        {
            wordList.erase(*it);
        }

        unordered_set<string> nextlevel;
        bool isConnected = false;
        for (it = forward.begin(); it != forward.end(); it++)
        {
            string word = *it;
            for (int i = 0; i < word.size(); i++)
            {
                for (char ch = 'a'; ch <= 'z'; ch++)
                {
                    if (word[i] != ch)
                    {
                        // word is in forward list, str is in backward list
                        string str(word);
                        str[i] = ch;
                        if (backward.find(str) != backward.end())
                        {
                            isConnected = true;
                            connect(word, str, isForward, transMap);
                        }
                        else if (!isConnected && wordList.find(str) != wordList.end())
                        {
                            nextlevel.insert(str);
                            connect(word, str, isForward, transMap);
                        }
                    }
                }
            }
        }

        return isConnected || bfs(nextlevel, backward, isForward, wordList, transMap);
    }

    void dfs(vector<vector<string>> &res, vector<string> &path, unordered_map<string, vector<string>> &transMap, 
                string &curWord, string &endWord) {
        if (curWord == endWord)
        {
            // found the endWord, record result
            res.push_back(path);
            return;
        }

        // backtracking
        for (int i = 0; i < transMap[curWord].size(); i++)
        {
            string nextWord(transMap[curWord][i]);
            path.push_back(nextWord);
            dfs(res, path, transMap, nextWord, endWord);
            path.pop_back();
        }

        return;
    }

public:
    vector<vector<string>> findLadders(string beginWord, string endWord, unordered_set<string> &wordList) {
        vector<vector<string>> res;
        vector<string> path;
        if (beginWord == endWord)
        {
            path.push_back(beginWord);
            res.push_back(path);
            return res;
        }

        // a hash map to map a string to all its possible transformations in wordList
        unordered_map<string, vector<string>> transMap;

        // two hash tables to build paths from beginWord and endWord relatively
        unordered_set<string> forward, backward;
        forward.insert(beginWord);
        backward.insert(endWord);

        // bfs first to build transMap
        if (bfs(forward, backward, true, wordList, transMap))
        {
            path.push_back(beginWord);
            // dfs to search in transMap and output results
            dfs(res, path, transMap, beginWord, endWord);
        }

        return res;
    }
};

int _tmain(int argc, _TCHAR* argv[])
{
    string beginWord("hit");
    string endWord("cog");
    unordered_set<string> wordList;
    wordList.insert("hot");
    wordList.insert("dot");
    wordList.insert("dog");
    wordList.insert("lot");
    wordList.insert("log");

    Solution_BiBFS mySolution;
    vector<vector<string>> res = mySolution.findLadders(beginWord, endWord, wordList);

    for (int i = 0; i < res.size(); i++)
    {
        for (int j = 0; j < res[i].size(); j++)
        {
            cout << res[i][j] << " ";
        }
        cout << endl;
    }
    system("pause");

    return 0;
}

