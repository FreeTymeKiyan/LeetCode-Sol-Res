/*
 *Design a data structure that supports the following two operations:

 *void addWord(word)
 *bool search(word)
 *search(word) can search a literal word or a regular expression string containing only letters a-z or .. A . means it can represent any one letter.

 *For example:

 *addWord("bad")
 *addWord("dad")
 *addWord("mad")
 *search("pad") -> false
 *search("bad") -> true
 *search(".ad") -> true
 *search("b..") -> true

 *Note:
 *You may assume that all words are consist of lowercase letters a-z.

 *Hint: You should be familiar with how a Trie works. If not, please work on this problem: Implement Trie (Prefix Tree) first.
 *Reference: https://zh.wikipedia.org/wiki/Trie

 *Tag: Backtracking, Trie, Design

 *Author: Linsen Wu

 */

#include "stdafx.h"
#include <string>
#include <vector>

using namespace std;

class TrieNode {
public:
    TrieNode *child[26];
    bool isWord;
    // Initialize your data structure here.
    TrieNode() {
        for (auto &a : child) a = NULL;
        isWord =  false;
    }
};

class WordDictionary {
public:
    WordDictionary() {
        root = new TrieNode();
    }
    // Adds a word into the data structure.
    void addWord(string word) {
        if (word.size() == 0)
        {
            return;
        }

        TrieNode *next = root;
        for (int i = 0; i < word.size(); i++)
        {
            if (next->child[word[i]-'a'] == NULL)
            {
                TrieNode* node = new TrieNode();
                next->child[word[i]-'a'] = node;
            }
            next = next->child[word[i]-'a'];
        }
        next->isWord = true;        
    }

    // Returns if the word is in the data structure. A word could
    // contain the dot character '.' to represent any one letter.
    bool search(string word) {
        if (word.size() == 0)
        {
            return true;
        }

        vector<TrieNode *> pre; //for all current possible nodes
        vector<TrieNode *> now; //for all next round possible nodes
        pre.push_back(root);
        
        for (int i = 0; i < word.size(); i++)
        {
            while (!pre.empty())
            {
                TrieNode *next = pre.back();
                if (word[i] == '.'){
                    for (int j = 0; j < 26; j++)
                    {
                        if (next->child[j] != NULL)
                        {
                            now.push_back(next->child[j]); //push possible nodes to the next round vector
                            if (i == word.size()-1 && next->child[j]->isWord == true){ //if the search string ends with '.', there must be at least one isWord true to return true.
                                return true;
                            }
                        }
                    }
                    
                }
                else if(next->child[word[i]-'a'] != NULL){
                    next = next->child[word[i]-'a'];
                    now.push_back(next); //push possible nodes to the next round vector
                    if (i == word.size()-1 && next->isWord == true){
                        return true;
                    }
                }
                pre.pop_back(); //pop out current node once finished searching
            }

            pre = now;
            now.clear();
        }
    
        return false;        
    }

    private:
        TrieNode* root;
};

// Your WordDictionary object will be instantiated and called as such:
// WordDictionary wordDictionary;
// wordDictionary.addWord("word");
// wordDictionary.search("pattern");

int _tmain(int argc, _TCHAR* argv[])
{
    WordDictionary _inst;
    _inst.addWord("a");
    _inst.addWord("a");
    _inst.search("aa");
    _inst.search("a");
    _inst.search(".a");
    _inst.search("a.");
    return 0;
}

