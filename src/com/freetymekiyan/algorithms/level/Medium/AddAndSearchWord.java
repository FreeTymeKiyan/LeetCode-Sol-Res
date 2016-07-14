import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Design a data structure that supports the following two operations:
 * <p>
 * void addWord(word)
 * bool search(word)
 * search(word) can search a literal word or a regular expression string containing only letters a-z or '.'
 * A '.' means it can represent any one letter.
 * <p>
 * For example:
 * <p>
 * addWord("bad")
 * addWord("dad")
 * addWord("mad")
 * search("pad") -> false
 * search("bad") -> true
 * search(".ad") -> true
 * search("b..") -> true
 * Note:
 * You may assume that all words are consist of lowercase letters a-z.
 * <p>
 * You should be familiar with how a Trie works. If not, please work on this problem: Implement Trie (Prefix Tree)
 * first.
 * <p>
 * Tags: Backtracking, Trie, Design
 * Similar Problems: (M) Implement Trie (Prefix Tree)
 */
public class AddAndSearchWord {

    private WordDictionary d;

    @Before
    public void setUp() {
        d = new WordDictionary();
    }

    @Test
    public void testEdgeCase() {
        d.addWord("a");
        Assert.assertTrue(d.search("."));
        d.addWord("ab");
        Assert.assertTrue(d.search("a"));
        Assert.assertTrue(d.search("a."));
    }

    @After
    public void tearDown() {
        d = null;
    }

    public class WordDictionary {

        TrieNode root = new TrieNode();

        // Adds a word into the data structure.
        public void addWord(String word) {
            if (word == null || word.length() == 0) return;
            root.insert(word);
        }

        // Returns if the word is in the data structure. A word could
        // contain the dot character '.' to represent any one letter.
        public boolean search(String word) {
            if (word == null || word.length() == 0) return false;
            return root.search(word);
        }

        class TrieNode {
            private final int R = 26;
            private TrieNode[] links;
            private boolean isEnd;

            // Initialize your data structure here.
            public TrieNode() {
                links = new TrieNode[26];
            }

            public boolean hasLink(char ch) {
                return links[ch - 'a'] != null;
            }

            public TrieNode getNode(char ch) {
                return links[ch - 'a'];
            }

            public void putNode(char ch, TrieNode node) {
                links[ch - 'a'] = node;
            }

            public void setEnd() {
                isEnd = true;
            }

            public boolean isEnd() {
                return isEnd;
            }

            public void insert(String word) {
                if (word == null) return;
                TrieNode node = root;
                for (int i = 0; i < word.length(); i++) {
                    char currentChar = word.charAt(i);
                    if (!node.hasLink(currentChar)) {
                        node.putNode(currentChar, new TrieNode());
                    }
                    node = node.getNode(currentChar);
                }
                node.setEnd();
            }

            public boolean search(String word) {
                return searchPrefix(word, 0, root);
            }

            private boolean searchPrefix(String word, int k, TrieNode node) {
                if (k == word.length()) {
                    return node.isEnd();
                }

                if (word.charAt(k) == '.') {
                    for (int i = 0; i < node.links.length; i++) {
                        if (node.links[i] != null) {
                            if (searchPrefix(word, k + 1, node.links[i])) {
                                return true;
                            }
                        }
                    }
                } else {
                    return node.links[word.charAt(k) - 'a'] != null
                            && searchPrefix(word, k + 1, node.links[word.charAt(k) - 'a']);
                }
                return false;
            }
        }
    }


// Your WordDictionary object will be instantiated and called as such:
// WordDictionary wordDictionary = new WordDictionary();
// wordDictionary.addWord("word");
// wordDictionary.search("pattern");
}
