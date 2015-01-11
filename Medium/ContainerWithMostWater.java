/**
 * Given n non-negative integers a1, a2, ..., an, where each represents a point
 * at coordinate (i, ai). n vertical lines are drawn such that the two
 * endpoints of line i is at (i, ai) and (i, 0). Find two lines, which together
 * with x-axis forms a container, such that the container contains the most
 * water.
 * 
 * Note: You may not slant the container.
 * 
 * Tags: Array, Two pointers
 */
class ContainerWithMostWater {
    public static void main(String[] args) {
        
    }
    
    /**
     * 2 pointers, low and high
     * curArea = (high - low) * min(height[high], height[low])
     * maxArea = max(maxArea, curArea)
     * Move lower pointer towards center for the next loop
     * Stop when two pointers meet, cause one line can form a container
     * Different from block
     */
    public int maxArea(int[] height) {
        if (height == null || height.length == 0) return 0;
        int ans = 0;
        int low = 0, high = height.length - 1;
        while (low < high) { // note low < high, not <=
            // update answer
            ans = Math.max(ans, (high - low) * Math.min(height[low], height[high]));
            // move lower pointer towards center
            if (height[low] < height[high]) low++;
            else high--;
        }
        return ans;
    }
}
