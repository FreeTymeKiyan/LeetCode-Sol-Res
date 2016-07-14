/**
 * Follow up for H-Index: What if the citations array is sorted in ascending order? Could you optimize your algorithm?
 *
 * Hint:
 *
 * Expected runtime complexity is in O(log n) and the input is sorted.
 *
 * Tags: Binary Search
 *
 * Similar Problems: (M) H-Index
 */
public class HIndex2 {

    public int hIndex(int[] citations) {
        if (citations == null || citations.length == 0) return 0;
        int l = 0;
        int h = citations.length;
        int mid;
        while (l < h) {
            mid = l + (h - l) / 2;
            if (citations[mid] == citations.length - mid) {
                return citations.length - mid;
            } else if (citations[mid] > citations.length - mid) {
                h = mid;
            } else {
                l = mid + 1;
            }
        }
        return citations.length - h;
    }

    public static void main(String[] args) {
        HIndex2 h2 = new HIndex2();
        int ans;

        // [] -> 0
        int[] citations1 = new int[0];
        ans = h2.hIndex(citations1);
        System.out.println(ans);

        // [1] -> 1
        int[] citations2 = new int[]{1};
        ans = h2.hIndex(citations2);
        System.out.println(ans);

        // [1.2.3.4.5] -> 3
        int[] citations3 = new int[]{1,2,3,4,5};
        ans = h2.hIndex(citations3);
        System.out.println(ans);

        // [5,6,7,8,9] -> 5
        int[] citations4 = new int[]{5,6,7,8,9};
        ans = h2.hIndex(citations4);
        System.out.println(ans);
    }
}
