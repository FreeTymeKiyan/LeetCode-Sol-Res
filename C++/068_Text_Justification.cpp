// 068. Text Justification
/**
 * Given an array of words and a length L, format the text such that each line has exactly L characters and is fully (left and right) justified.
 *
 * You should pack your words in a greedy approach; that is, pack as many words as you can in each line. Pad extra spaces ' ' when necessary so that each line has exactly L characters.
 *
 * Extra spaces between words should be distributed as evenly as possible. If the number of spaces on a line do not divide evenly between words, the empty slots on the left will be assigned more spaces than the slots on the right.
 *
 * For the last line of text, it should be left justified and no extra space is inserted between words.
 *
 * For example,
 * words: ["This", "is", "an", "example", "of", "text", "justification."]
 * L: 16.
 *
 * Return the formatted lines as:
 * [
 *  "This    is    an",
 *  "example  of text",
 *  "justification.  "
 * ]
 * Note: Each word is guaranteed not to exceed L in length.
 *
 * click to show corner cases.
 *
 * Corner Cases:
 * A line other than the last line might contain only one word. What should you do in this case?
 * In this case, that line should be left-justified.
 *
 * Tags: String
 *
 * Author: Kuang Qin
 *
 */

#include "stdafx.h"
#include <iostream>
#include <string>
#include <vector>

using namespace std;

class Solution {
public:
    vector<string> output;

    // add a normal line - adjust space between words
    void addLine(vector<string>& line, int maxWidth) {
        int wordCount = line.size();
        int textLen = 0;
        for (int i = 0; i < wordCount; i++)
        {
            textLen += line[i].size();
        }

        int spaceLen = maxWidth - textLen;    // space length
        int extraSpaceCount = 0;              // extra space counter

        // if there are more than one word, extra space may be needed
        if (wordCount > 1)
        {
            spaceLen = (maxWidth - textLen) / (wordCount - 1);    // the quotient is the basic space length
            extraSpaceCount = (maxWidth - textLen) % (wordCount - 1);  // the remainer gets an extra space
        }

        string str(line[0]);
        string spaceStr(spaceLen, ' ');
        for (int i = 1; i < wordCount; i++)
        {
            str += spaceStr;
            if (i <= extraSpaceCount)  // add an extra space
            {
                str += " ";
            }
            str += line[i];
        }
        str.resize(maxWidth, ' ');    // resize the string to max width
        output.push_back(str);
        line.clear();
    }

    // add the last line - left indent and no extra space added
    void addLastLine(vector<string>& line, int maxWidth) {
        string str;
        for (int i = 0; i < line.size(); i++)
        {
            str += line[i];
            str += " ";    // no extra space is needed
        }
        str.resize(maxWidth, ' ');    // resize the string to max width
        output.push_back(str);
    }

    vector<string> fullJustify(vector<string>& words, int maxWidth) {
        vector<string> line;
        int currWidth = 0;
        
        for (int i = 0; i < words.size(); i++)
        {
            if (words[i].size() == 0)    // ignore zero length word
            {
                continue;
            }

            // if adding next word exceeds max width, add current line
            if (currWidth + words[i].size() > maxWidth)
            {
                addLine(line, maxWidth);
                currWidth = 0;
            }            
            
            // if still in range, keep adding words
            line.push_back(words[i]);
            currWidth += words[i].size() + 1;    // '+ 1' is the space
        }

        addLastLine(line, maxWidth);    // add the last line
        return output;
    }
};

int _tmain(int argc, _TCHAR* argv[])
{
    vector<string> testStr;
    testStr.push_back("This");
    testStr.push_back("problem");
    testStr.push_back("is");
    testStr.push_back("");
    testStr.push_back("difficult.");
    int maxWidth = 11;

    Solution mySolution;
    vector<string> result = mySolution.fullJustify(testStr, maxWidth);

    for (int i = 0; i < result.size(); i++)
    {
        cout << result[i] << endl;
    }
    system("pause");

    return 0;
}

