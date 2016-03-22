/*
 *Implement a trie with insert, search, and startsWith methods.

 *Note:
 *You may assume that all inputs are consist of lowercase letters a-z.

 *Reference: https://zh.wikipedia.org/wiki/Trie

 *Tag: Trie, Design

 *Author: Linsen Wu
 */

#include "stdafx.h"
#include <string>

using namespace std;

class TrieNode {
public:
    TrieNode *child[26];
    bool isWord;
    // Initialize your data structure here.
    TrieNode() {
        for (auto &a : child) a = NULL;        //initialize the child point to be NULL
        isWord =  false;                    //initialize the whether is an existing word to be false
    }
};

class Trie {
public:
    Trie() {
        root = new TrieNode();
    }

    // Inserts a word into the trie.
    void insert(string word) {
        if (word.size() == 0)
        {
            return;
        }

        TrieNode *next = root;
        for (int i = 0; i < word.size(); i++)
        {
            if (next->child[word[i]-'a'] == NULL)        //If the char is not in the tree, add it to the tree. 
            {
                TrieNode* node = new TrieNode();
                next->child[word[i]-'a'] = node;
            }
            next = next->child[word[i]-'a'];            //Otherwise go to next node directly.
        }
        next->isWord = true;
    }

    // Returns if the word is in the trie.
    bool search(string word) {
        if (word.size() == 0)
        {
            return true;
        }

        TrieNode *next = root;
        for (int i = 0; i < word.size(); i++)
        {
            if (next->child[word[i]-'a'] == NULL)        //return false if the char is not in the tree
            {
                return false;
            }
            next = next->child[word[i]-'a'];
        }

        if (next->isWord == true)                        //If the last char is then end of a word, return true.
        {
            return true;
        }
        
        return false;
    }

    // Returns if there is any word in the trie
    // that starts with the given prefix.
    bool startsWith(string prefix) {                    //Similar to search but do not need to judge whether it's a word or not.
        if (prefix.size() == 0)
        {
            return true;
        }

        TrieNode *next = root;
        for (int i = 0; i < prefix.size(); i++)
        {
            if (next->child[prefix[i]-'a'] == NULL)
            {
                return false;
            }
            next = next->child[prefix[i]-'a'];
        }
        
        return true;        
    }

private:
    TrieNode* root;
};

// Your Trie object will be instantiated and called as such:
// Trie trie;
// trie.insert("somestring");
// trie.search("key");

int _tmain(int argc, _TCHAR* argv[])
{
    return 0;
}

