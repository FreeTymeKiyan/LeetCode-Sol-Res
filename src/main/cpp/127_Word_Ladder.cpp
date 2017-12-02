// 127. Word Ladder
/**
 * Given two words (beginWord and endWord), and a dictionary's word list, find the length of shortest transformation sequence from beginWord to endWord, such that:
 * 
 * 1. Only one letter can be changed at a time
 * 2. Each intermediate word must exist in the word list
 *
 * For example,
 * 
 * Given:
 * beginWord = "hit"
 * endWord = "cog"
 * wordList = ["hot","dot","dog","lot","log"]
 * As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog",
 * return its length 5.
 * 
 * Note:
 * Return 0 if there is no such transformation sequence.
 * All words have the same length.
 * All words contain only lowercase alphabetic characters.
 * 
 * Author: Kuang Qin
 */

#include "stdafx.h"
#include <string>
#include <unordered_set>
#include <queue>

using namespace std;

// one direction bfs
class Solution {
public:
    int ladderLength(string beginWord, string endWord, unordered_set<string>& wordList) {
        if (beginWord == endWord)
        {
            return 1;
        }

        // use a queue store each level possible results
        queue<string> q;
        q.push(beginWord);
        wordList.erase(beginWord);

        // make sure endWord is in wordList
        wordList.insert(endWord);

        int distance = 1;    // start from the beginWord

        while (!q.empty())
        {
            distance++;    // new level
            int count = q.size();
                    
            for (int i = 0; i < count; i++)    // bfs by level
            {
                string word = q.front();
                q.pop();
                
                for (int j = 0; j < word.size(); j++)
                {
                    for (char ch = 'a'; ch <= 'z'; ch++)
                    {
                        if (word[j] != ch)    // skip the same word
                        {
                            string str(word);
                            str[j] = ch;
                            if (wordList.find(str) != wordList.end())    // found next level word
                            {
                                if (str == endWord)
                                {
                                    return distance;
                                }

                                wordList.erase(str);
                                q.push(str);
                            }
                        }
                    }
                }
            }
        }

        return 0;
    }
};

// bidirectional bfs
class Solution_BiBFS {
public:
    // create a queue to track the distance of each word in wordList from beginWord
    int ladderLength(string beginWord, string endWord, unordered_set<string>& wordList) {
        if (beginWord == endWord)
        {
            return 1;
        }

        // two hash tables to build paths from beginWord and endWord relatively
        unordered_set<string> forward, backward;
        forward.insert(beginWord);
        backward.insert(endWord);
        wordList.erase(beginWord);
        wordList.erase(endWord);

        int distance = 1;    // start from the beginWord

        while (!forward.empty() && !backward.empty())
        {
            distance++;    // new level
            if (forward.size() > backward.size())
            {
                swap(forward, backward);
            }
                    
            unordered_set<string> nextlevel;
            for (unordered_set<string>::iterator it = forward.begin(); it != forward.end(); it++)
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
                                return distance;
                            }
                            else if (wordList.find(str) != wordList.end())
                            {
                                nextlevel.insert(str);
                                wordList.erase(str);
                            }
                        }
                    }
                }
            }

            // continue searching with nextlevel and backward
            swap(forward, nextlevel);
        }

        return 0;
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
    int res = mySolution.ladderLength(beginWord, endWord, wordList);

    return 0;
}

