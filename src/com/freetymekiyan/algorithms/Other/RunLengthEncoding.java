/**
 * Run Length Encoding
 *      wwwwaaadexxxxxx     
 * =>   w4a3d1e1x6
 *      
 * Tags: String 
 */
public class encoding {
    
    public static void main(String[] args) {
        String src = "wwwwaaadexxxxxx";
    }
    
    /**
     * Traverse the source string
     * Append current char to result first
     * Get count with a loop, note the edge case
     * Append count of current char to result
     */
    public String encode(String src) {
        StringBuilder dest = new StringBuilder();
        for (int i = 0; i < src.length(); i++) {
            dest.append(src.charAt(i));
            int cnt = 1;
            while (i + 1 < src.length() && src.charAt(i) == src.charAt(i + 1)) {
                i++;
                cnt++;
            }
            dest.append(cnt);
        }
        return dest.toString();
    }
}
