import java.util.Map;
import java.util.HashMap;

/**
 * Given n points on a 2D plane, find the maximum number of points that lie on
 * the same straight line.
 *
 * Tags: Hashtable, math
 */
class MaxPoints {
    public static void main(String[] args) {

    }

    /**
     * Set a point and go through the rest
     * Use max to record the max points of current loop
     * Use countSame to track the number of points
     * Use result to track the max points
     * Use a map to store lines, key is the feature of the line, value is the
     * number of points
     *
     * @param points points generated, can be same
     * @return number of max points share a same line
     */
    private static int maxPoints(Point[] points) {
        if (points.length < 3) return points.length; // 0/1/2 points
        int res = 1; // at least 1 point
        Map<String, Integer> map = new HashMap<String, Integer>(); // line,count
        for (int i = 0; i < points.length; i++) {
            int max = 0;
            int countSame = 0; // # of same points
            for (int j = i + 1; j < points.length; j++) {
                if (points[i].x == points[j].x && points[i].y == points[j].y ) countSame++; // same point
                else {
                    String key = normalize(points[i], points[j]); // a|b|c
                    if (map.containsKey(key)) { // on the line
                        int count = map.get(key) + 1;
                        map.put(key, count); // update count
                        if (count > max) max = count; // update max
                    } else { // not on any line
                        map.put(key, 1);
                        if (max == 0) max++; // update max
                    }
                }
            }
            res = Math.max(res, max + countSame + 1); // +1 for the start point
            map.clear(); // clear map for next point
        }
        return res;
    }

    /**
     * use ax + by = c to represent a line and a|b|c as a key for that line
     * a, b, c should be normalized, how?
     *
     * special case, vertical, horizontal
     */
    private static String normalize(Point p1, Point p2) {
        int a, b;
        float c;
        if (p1.x == p2.x) { // vertical
            b = 0;
            a = 1;
            c = p1.x;
        } else if (p1.y == p2.y) { // horizontal
            a = 0;
            b = 1;
            c = p2.y;
        } else { // ax + by = c
            int dx = p1.x - p2.x;
            int dy = p1.y - p2.y;
            /*reduce to simplest*/
            int gcd = gcd(Math.abs(dx), Math.abs(dy));
            a = dy / gcd;
            b = dx / gcd;
            if (a * b < 0) { // force a to be negative
                a = -1 * Math.abs(a);
                b = Math.abs(b);
            } else { // force both positive
                a = Math.abs(a);
                b =  Math.abs(b);
            }
            c = a * p1.x + b * p1.y; // c = ax + by
        }
        return a + "|" + b + "|" + c; // key format
    }

    /**
     * recursively calculate the greateset common divisor of two numbers
     */
    private static int gcd(int a, int b) {
        if (b == 0) return a; // stop when b == 0, a is the gcd
        return gcd(b, a % b); // a <- b, b <- a % b
    }

    class Point {
        int x;
        int y;
        Point() { x = 0; y = 0;}
        Point(int a, int b) { x = a; y = b; }
    }
}
