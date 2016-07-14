import java.util.HashMap;
import java.util.Map;

/**
 * Given an array of integers, find out whether there are two distinct indices i and j in the array such that the
 * difference between nums[i] and nums[j] is at most t and the difference between i and j is at most k.
 *
 * Tags: Binary Search Tree
 *
 * Similar Problems: (E) Contains Duplicate, (E) Contains Duplicate II
 */
public class ContainsDuplicate3 {
    private long getBucketId(long i, long w) {
        return i < 0 ? (i + 1) / w - 1 : i / w;
    }

    /**
     * O(n) Time, O(n) Space
     */
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        if (t < 0) return false;
        Map<Long, Long> bucketMap = new HashMap<>();
        long width = (long)t + 1;
        for (int i = 0; i < nums.length; ++i) {
            long id = getBucketId(nums[i], width);
            if (bucketMap.containsKey(id))
                return true;
            if (bucketMap.containsKey(id - 1) && Math.abs(nums[i] - bucketMap.get(id - 1)) < width)
                return true;
            if (bucketMap.containsKey(id + 1) && Math.abs(nums[i] - bucketMap.get(id + 1)) < width)
                return true;
            bucketMap.put(id, (long)nums[i]);
            if (i >= k)
                bucketMap.remove(getBucketId(nums[i - k], width)); // out of window
        }
        return false;
    }
}
