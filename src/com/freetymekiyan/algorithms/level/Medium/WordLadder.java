import java.util.*;
/**
 * Given two words (start and end), and a dictionary, find the length of
 * shortest transformation sequence from start to end, such that:
 * 
 * Only one letter can be changed at a time
 * Each intermediate word must exist in the dictionary
 * For example,
 * 
 * Given:
 * start = "hit"
 * end = "cog"
 * dict = ["hot","dot","dog","lot","log"]
 * 
 * As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog",
 * return its length 5.
 * 
 * Note:
 * Return 0 if there is no such transformation sequence.
 * All words have the same length.
 * All words contain only lowercase alphabetic characters.
 * 
 * Tags: BFS, Hashtable
 */
class WordLadder {
    public static void main(String[] args) {
        String start = "hot";
        String end = "dog";
        Set<String> s = new HashSet<String>();
        s.add("hot");
        s.add("dog");
        s.add("dot");
        System.out.println(ladderLength(start, end, s));
    }
    
    /**
     * BFS
     */
    public static int ladderLength(String start, String end, Set<String> dict) {
        if (dict == null || dict.size() == 0) return 0;

        Queue<String> queue = new LinkedList<String>();
        queue.add(start);
        dict.remove(start);
        int length = 1;

        while (!queue.isEmpty()) {
            int count = queue.size(); // get current level size
            for (int i = 0; i < count; i++){
                String word = queue.poll(); // get word from queue
                if (word.equals(end)) return length; // end when word is found
                for (int j = 0; j < word.length(); j++) {
                    for (char c = 'a'; c <= 'z'; c++) { // build new word
                        char[] ch = word.toCharArray();
                        if (ch[j] == c) continue;
                        ch[j] = c;
                        String newWord = new String(ch);
                        if (dict.contains(newWord)){ // new word in dict
                            queue.add(newWord); // add to queue
                            dict.remove(newWord); // remove from dict
                        }
                    }
                }
            }
            length++;
        }
        return 0;
    }
    
    /**
     * use queue to do BFS
     * use Map to record distance with word as key
     * if found end, return distance
     * else continue build queue
     */
    public static int ladderLengthB(String start, String end, Set<String> dict) {
        if (dict == null || dict.isEmpty()) return 0;
        dict.add(end);
        Map<String, Integer> map = new HashMap<String, Integer>();
        map.put(start, 1);
        Queue<String> q = new LinkedList<String>();
        q.add(start);
        while (!q.isEmpty()) {
            String word = q.poll();
            if (word.equals(end)) break;
            for (int i = 0; i < word.length(); i++) {
                for (char j = 'a'; j <= 'z'; j++) {
                    char[] ch = word.toCharArray();
                    if (ch[i] == j) continue;
                    ch[i] = j;
                    String newWord = new String(ch);
                    if (dict.contains(newWord) && !map.containsKey(newWord)) {
                        map.put(newWord, map.get(word) + 1);
                        q.add(newWord);
                    }
                }
            }
        }
        return map.containsKey(end) ? map.get(end) : 0;
    }
}
