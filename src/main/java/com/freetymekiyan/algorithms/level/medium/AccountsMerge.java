package com.freetymekiyan.algorithms.level.medium;

import java.util.*;

/**
 * 721. Accounts Merge
 * <p>
 * Given a list accounts, each element accounts[i] is a list of strings, where the first element accounts[i][0] is a
 * name, and the rest of the elements are emails representing emails of the account.
 * <p>
 * Now, we would like to merge these accounts. Two accounts definitely belong to the same person if there is some email
 * that is common to both accounts. Note that even if two accounts have the same name, they may belong to different
 * people as people could have the same name. A person can have any number of accounts initially, but all of their
 * accounts definitely have the same name.
 * <p>
 * After merging the accounts, return the accounts in the following format: the first element of each account is the
 * name, and the rest of the elements are emails in sorted order. The accounts themselves can be returned in any order.
 * <p>
 * Example 1:
 * Input:
 * |accounts =
 * |[
 * |  ["John", "johnsmith@mail.com", "john00@mail.com"],
 * |  ["John", "johnnybravo@mail.com"],
 * |  ["John", "johnsmith@mail.com", "john_newyork@mail.com"],
 * |  ["Mary", "mary@mail.com"]
 * |]
 * <p>
 * Output:
 * |[
 * |  ["John", 'john00@mail.com', 'john_newyork@mail.com', 'johnsmith@mail.com'],
 * |  ["John", "johnnybravo@mail.com"],
 * |  ["Mary", "mary@mail.com"]
 * |]
 * <p>
 * Explanation:
 * The first and third John's are the same person as they have the common email "johnsmith@mail.com".
 * The second John and Mary are different people as none of their email addresses are used by other accounts.
 * We could return these lists in any order, for example the answer [['Mary', 'mary@mail.com'], ['John',
 * 'johnnybravo@mail.com'],
 * ['John', 'john00@mail.com', 'john_newyork@mail.com', 'johnsmith@mail.com']] would still be accepted.
 * Note:
 * <p>
 * The length of accounts will be in the range [1, 1000].
 * The length of accounts[i] will be in the range [1, 10].
 * The length of accounts[i][j] will be in the range [1, 30].
 * <p>
 * Related Topics: Depth-first Search, Union Find
 * Similar Questions: (M)Redundant Connection, (E)Sentence Similarity, (M)Sentence Similarity II
 */
public class AccountsMerge {

    /**
     * Union-Find.
     */
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        UnionFind uf = new UnionFind();
        HashMap<String, Integer> emailToId = new HashMap<>(); // Email to id mapping.
        HashMap<String, String> emailToName = new HashMap<>(); // Email to name mapping.
        int id = 0;
        for (List<String> account : accounts) {
            String name = null;
            for (String email : account) {
                // First position is account name.
                if (name == null) {
                    name = email;
                    continue;
                }
                emailToId.putIfAbsent(email, id++);
                emailToName.put(email, name);
                uf.union(emailToId.get(account.get(1)), emailToId.get(email));
            }
        }
        // Group all the emails by connected component id.
        Map<Integer, List<String>> idToEmails = new HashMap<>();
        for (Map.Entry<String, Integer> entry : emailToId.entrySet()) {
            idToEmails.computeIfAbsent(uf.find(entry.getValue()), ArrayList::new).add(entry.getKey());
        }
        // Sort and insert name in the front of each list.
        for (List<String> emails : idToEmails.values()) {
            Collections.sort(emails);
            emails.add(0, emailToName.get(emails.get(0)));
        }
        return new ArrayList<>(idToEmails.values());
    }

    /**
     * DFS.
     */
    public List<List<String>> accountsMerge2(List<List<String>> accounts) {
        Map<String, List<String>> graph = new HashMap<>();
        Map<String, String> emailToName = new HashMap<>();
        for (List<String> account : accounts) {
            String name = null;
            for (String email : account) {
                if (name == null) {
                    name = email;
                    continue;
                }
                emailToName.put(email, name);
                graph.computeIfAbsent(account.get(1), x -> new ArrayList<>()).add(email);
                graph.computeIfAbsent(email, x -> new ArrayList<>()).add(account.get(1));
            }
        }

        Set<String> visited = new HashSet<>();
        List<List<String>> result = new ArrayList<>();
        for (String email : graph.keySet()) {
            if (!visited.contains(email)) {
                List<String> list = new ArrayList<>();
                Stack<String> stack = new Stack<>();
                stack.push(email);
                visited.add(email);
                while (!stack.isEmpty()) {
                    String top = stack.pop();
                    list.add(top);
                    for (String neighbor : graph.get(top)) {
                        if (!visited.contains(neighbor)) {
                            stack.push(neighbor);
                            visited.add(neighbor);
                        }
                    }
                }
                Collections.sort(list);
                list.add(0, emailToName.get(email));
                result.add(list);
            }
        }
        return result;
    }

    class UnionFind {

        // length of accounts * length of account.
        private static final int SIZE = 10000; // Even 9000 should enough.
        private int[] ids;

        public UnionFind() {
            this.ids = new int[SIZE];
            for (int i = 0; i < ids.length; i++) {
                ids[i] = i;
            }
        }

        public void union(int i1, int i2) {
            int id1 = find(i1);
            int id2 = find(i2);
            if (id1 != id2) {
                ids[id1] = id2;
            }
        }

        public int find(int i) {
            while (i != ids[i]) {
                ids[i] = ids[ids[i]]; // Path compression.
                i = ids[i];
            }
            return i;
        }
    }
}
