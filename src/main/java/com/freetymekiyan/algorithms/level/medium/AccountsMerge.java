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
 * |  ["John", "john00@mail.com", "john_newyork@mail.com", "johnsmith@mail.com"],
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
 * Similar Questions: (M) Redundant Connection, (E) Sentence Similarity, (M) Sentence Similarity II
 */
public class AccountsMerge {

    /**
     * Union-Find.
     * Each person is a connected component.
     * Each email is just a graph node.
     * Generate an ID for each email, since email is the key to decide whether two accounts are the same.
     * Create an array of ID's, which has at most 9000.
     * Initialize each of them to be unique.
     * For each account:
     * | Generate one unique id for one unique email.
     * | Record which name an email points to.
     * | Union all emails to one single email so that we can use any one to judge whether 2 accounts are of same person.
     * Then group emails by ID to find out the emails of each person.
     * Then sort the emails and insert name to be the first element.
     */
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        Map<String, Integer> emailToId = new HashMap<>();
        Map<String, String> emailToName = new HashMap<>();
        int[] ids = new int[9000]; // 1000 account * 9 emails at most.
        for (int i = 0; i < ids.length; i++) ids[i] = i;
        int id = 0;
        for (List<String> account : accounts) {
            String name = null;
            for (String email : account) {
                if (name == null) {
                    name = email; // First element is the name of this account.
                } else {
                    emailToId.putIfAbsent(email, id); // Generate an ID for each email.
                    id++;
                    emailToName.put(email, name); // Record the name of each email.
                    union(email, account.get(1), emailToId, ids); // Union all emails to the first email.
                }
            }
        }
        Map<Integer, List<String>> idToEmails = new HashMap<>();
        for (Map.Entry<String, Integer> entry : emailToId.entrySet()) {
            // Same as: idToEmails.computeIfAbsent(find(entry.getValue(), ids), ArrayList::new).add(entry.getKey());
            int root = find(entry.getValue(), ids);
            if (!idToEmails.containsKey(root)) {
                idToEmails.put(root, new ArrayList<>());
            }
            idToEmails.get(root).add(entry.getKey());
        }
        for (List<String> emails : idToEmails.values()) {
            Collections.sort(emails);
            emails.add(0, emailToName.get(emails.get(0)));
        }
        return new ArrayList<>(idToEmails.values());
    }

    private void union(String email1, String email2, Map<String, Integer> emailToId, int[] ids) {
        int r1 = find(emailToId.get(email1), ids);
        int r2 = find(emailToId.get(email2), ids);
        if (r1 != r2) {
            ids[r1] = r2;
        }
    }

    private int find(int i, int[] ids) {
        while (i != ids[i]) {
            ids[i] = ids[ids[i]];
            i = ids[i];
        }
        return i;
    }

    /**
     * DFS.
     * Build a graph to connect adjacent emails together.
     * Traverse the graph.
     * If the email is not visited, dfs to get all emails. Sort and add the name.
     * If the email is visited, skip to the next.
     */
    public List<List<String>> accountsMerge2(List<List<String>> accounts) {
        Map<String, List<String>> graph = new HashMap<>();
        Map<String, String> emailToName = new HashMap<>();
        for (List<String> account : accounts) {
            String name = null;
            for (String s : account) {
                if (name == null) {
                    name = s;
                    continue;
                }
                emailToName.put(s, name);
                graph.computeIfAbsent(account.get(1), x -> new ArrayList<>()).add(s);
                graph.computeIfAbsent(s, x -> new ArrayList<>()).add(account.get(1));
            }
        }

        Set<String> visited = new HashSet<>();
        List<List<String>> merged = new ArrayList<>();
        for (String email : graph.keySet()) {
            if (!visited.contains(email)) {
                List<String> account = new ArrayList<>();
                Stack<String> stack = new Stack<>();
                stack.push(email);
                visited.add(email);
                while (!stack.isEmpty()) {
                    String top = stack.pop();
                    account.add(top);
                    for (String neighbor : graph.get(top)) {
                        if (!visited.contains(neighbor)) {
                            stack.push(neighbor);
                            visited.add(neighbor);
                        }
                    }
                }
                Collections.sort(account);
                account.add(0, emailToName.get(email));
                merged.add(account);
            }
        }
        return merged;
    }

    /**
     * Union-Find with a separate class.
     */
    public List<List<String>> accountsMerge3(List<List<String>> accounts) {
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