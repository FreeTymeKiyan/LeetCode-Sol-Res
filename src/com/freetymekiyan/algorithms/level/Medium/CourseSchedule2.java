import java.util.ArrayList;
import java.util.List;

/**
 * There are a total of n courses you have to take, labeled from 0 to n - 1.
 * <p>
 * Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is
 * expressed as a pair: [0,1]
 * <p>
 * Given the total number of courses and a list of prerequisite pairs, return the ordering of courses you should take
 * to finish all courses.
 * <p>
 * There may be multiple correct orders, you just need to return one of them. If it is impossible to finish all
 * courses, return an empty array.
 * <p>
 * For example:
 * <p>
 * 2, [[1,0]]
 * There are a total of 2 courses to take. To take course 1 you should have finished course 0. So the correct course
 * order is [0,1]
 * <p>
 * 4, [[1,0],[2,0],[3,1],[3,2]]
 * There are a total of 4 courses to take. To take course 3 you should have finished both courses 1 and 2. Both courses
 * 1 and 2 should be taken after you finished course 0. So one correct course order is [0,1,2,3]. Another correct
 * ordering is[0,2,1,3].
 * <p>
 * Note:
 * The input prerequisites is a graph represented by a list of edges, not adjacency matrices. Read more about how a
 * graph is represented.
 * <p>
 * Hints:
 * 1. This problem is equivalent to finding the topological order in a directed graph. If a cycle exists, no
 * topological ordering exists and therefore it will be impossible to take all courses.
 * 2. Topological Sort via DFS - A great video tutorial (21 minutes) on Coursera explaining the basic concepts of
 * Topological Sort.
 * 3. Topological sort could also be done via BFS.
 * <p>
 * Tags: Depth-first Search, Breadth-first Search, Graph, Topological Sort
 * Similar Problems: (M) Course Schedule, (H) Alien Dictionary, (M) Minimum Height Trees
 */
public class CourseSchedule2 {

    /** Label the topological sort result */
    private int currentLabel;

    public int[] findOrder(int numCourses, int[][] prerequisites) {
        int[] result = new int[numCourses];
        Course[] courses = new Course[numCourses];
        for (int i = 0; i < numCourses; i++) {
            courses[i] = new Course(i);
        }
        for (int[] prerequisite : prerequisites) {
            courses[prerequisite[0]].add(courses[prerequisite[1]]);
        }

        for (int i = 0; i < numCourses; i++) {
            if (isCyclic(courses[i], result)) {
                return new int[0];
            }
        }
        return result;
    }

    /**
     * Check whether a node is cyclic or not
     * If yes, return true and this graph is not a DAG
     * If no, go ahead and do the DFS topological sort
     */
    private boolean isCyclic(Course cur, int[] result) {
        if (cur.tested) return false;
        if (cur.visited) return true;
        cur.visited = true;
        for (Course c : cur.pre) {
            if (isCyclic(c, result)) { // DFS
                return true;
            }
        }
        cur.tested = true; // Mark as tested
        result[currentLabel++] = cur.number; // Add course number to output
        return false;
    }

    /**
     * Course object, acted as a DAG node
     */
    class Course {
        /** Keep track of whether we already visited this node */
        boolean visited = false;
        /** Keep track of whether we already tested it's cyclic */
        boolean tested = false;
        /** Course number */
        int number;
        /** Prerequisite courses */
        List<Course> pre = new ArrayList<>();

        public Course(int i) {
            number = i;
        }

        public void add(Course c) {
            pre.add(c);
        }
    }
}
