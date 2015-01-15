import java.util.*;

/**
 * Given two words (start and end), and a dictionary, find all shortest
 * transformation sequence(s) from start to end, such that:
 *
 * Only one letter can be changed at a time
 * Each intermediate word must exist in the dictionary
 * For example,
 *
 * Given:
 * start = "hit"
 * end = "cog"
 * dict = ["hot","dot","dog","lot","log"]
 * Return
 *   [
 *     ["hit","hot","dot","dog","cog"],
 *     ["hit","hot","lot","log","cog"]
 *   ]
 * Note:
 * All words have the same length.
 * All words contain only lowercase alphabetic characters.
 *
 * Tags: Array, Backtracking, BFS, String
 */
class WordLadder2 {
    public static void main(String[] args) {
        String start = "hit";
        String end = "cog";
        String[] arr = {"hot","dot","dog","lot","log"};
        Set<String> dict = new HashSet<String>(Arrays.asList(arr));
        System.out.println(new WordLadder2().findLadders(start, end, dict).toString());
    }

    /**
     * BFS then DFS
     */
    public List<List<String>> findLadders(String start, String end, Set<String> dict) {
        List<List<String>> ladders = new ArrayList<List<String>>();
        Map<String, List<String>> map = new HashMap<String, List<String>>();
        Map<String, Integer> distance = new HashMap<String, Integer>();
        dict.add(start);
        dict.add(end);

        bfs(map, distance, start, end, dict);
        dfs(ladders, new LinkedList<String>(), end, start, distance, map);
        return ladders;
    }
    
    /**
     * Create a queue, add start to it and put start in distance map
     * Initialize map with lists
     */
    void bfs(Map<String, List<String>> map, Map<String, Integer> distance,
                String start, String end, Set<String> dict) {
        Queue<String> q = new LinkedList<String>();
        q.offer(start);
        distance.put(start, 0);
        for (String s : dict) map.put(s, new ArrayList<String>());

        while (!q.isEmpty()) {
            String word = q.poll();
            List<String> nextList = expand(word, dict); // generate all words
            for (String next : nextList) {
                map.get(next).add(word);
                if (!distance.containsKey(next)) { // not in distance map
                    distance.put(next, distance.get(word) + 1);
                    q.offer(next);
                }
            }
        }
    }
    
    /**
     * Generate a list of words the word
     * Skip if it's the same character
     * If word is in dictionary, add to expansion list
     */
    List<String> expand(String word, Set<String> dict) {
        List<String> expansion = new ArrayList<String>();
        for (int i = 0; i < word.length(); i++) {
            for (char ch = 'a'; ch <= 'z'; ch++) {
                if (ch != word.charAt(i)) {
                    String expanded = word.substring(0, i) + ch + word.substring(i + 1);
                    if (dict.contains(expanded)) expansion.add(expanded);
                }
            }
        }
        return expansion;
    }
    
    /**
     * Add current word to first position
     * Add path to result if word is start
     */
    void dfs(List<List<String>> ladders, List<String> path, String word, String start, Map<String, Integer> distance, Map<String, List<String>> map) {
        if (wor d.equals(start)) {
            path.add(0, word);
            ladders.add(new ArrayList<String>(path));
            path.remove(0);
            return;
        }
        for (String next : map.get(word)) {
            if (distance.containsKey(next) && distance.get(word) == distance.get(next) + 1) {
                path.add(0, word);
                dfs(ladders, path, next, start, distance, map);
                path.remove(0);
            }
        }           
    }
}
