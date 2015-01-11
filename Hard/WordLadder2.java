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
        System.out.println(findLadders(start, end, dict).toString());
    }

    static Map<String, List<String>> map;
    static List<List<String>> results;

    public static List<List<String>> findLadders(String start, String end, Set<String> dict) {
        results = new ArrayList<List<String>>();
        if (dict.size() == 0)
            return results;
        int min = Integer.MAX_VALUE; // store the min dist
        Queue<String> queue = new LinkedList<String>();
        queue.add(start);
        map = new HashMap<String, List<String>>();
        Map<String,Integer> ladder = new HashMap<String,Integer>(); // dist map
        for (String string : dict) ladder.put(string, Integer.MAX_VALUE);
        ladder.put(start, 0);
        dict.add(end); // add ending word to dict set
        
        //BFS: Dijisktra search
        while (!queue.isEmpty()) {
            String word = queue.poll();
            int step = ladder.get(word) + 1;//'step' indicates how many steps are needed to travel to one word.
            if (step > min) break; // not shortest
            for (int i = 0; i < word.length(); i++){
                StringBuilder builder = new StringBuilder(word);
                for (char ch = 'a';  ch <= 'z'; ch++){ // loop from 'a' to 'z'
                    builder.setCharAt(i, ch); // set char at index
                    String newWord = builder.toString();
                    if (ladder.containsKey(newWord)) { // showed up before
                        if (step > ladder.get(newWord))//Check if it is the shortest path to one word.
                            continue;
                        else if (step < ladder.get(newWord)){
                            queue.add(newWord);
                            ladder.put(newWord, step);
                        } else;// KEY line. Do not insert the same word inside the queue twice. Otherwise it gets TLE.
                        if (map.containsKey(newWord)) // Build adjacent Graph
                            map.get(newWord).add(word); // add to list 
                        else{
                            List<String> list = new LinkedList<String>();
                            list.add(word);
                            map.put(newWord, list);
                        }
                        if (newWord.equals(end))
                            min = step;
                    }
                }
            }
        }

        LinkedList<String> result = new LinkedList<String>();
        backTrace(end, start, result);

        return results;
    }

    /**
     * separate backtracking
     * 
     */
    private static void backTrack(String word, String start, List<String> list) {
        if (word.equals(start)){ // ends when find start word
            list.add(0, start); // insert to front 
            results.add(new ArrayList<String>(list)); // add to results
            list.remove(0); // remove first 
            return;
        }
        list.add(0, word); // insert to front
        if (map.get(word) != null) // in map
            for (String s : map.get(word)) // adjacent word
                backTrack(s, start, list);
        list.remove(0);
    }
}
