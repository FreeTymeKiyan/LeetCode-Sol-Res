/**
 * Compare two version numbers version1 and version1.
 * If version1 > version2 return 1, if version1 < version2 return -1, otherwise
 * return 0.
 * 
 * You may assume that the version strings are non-empty and contain only
 * digits and the . character.
 * The . character does not represent a decimal point and is used to separate
 * number sequences.
 * For instance, 2.5 is not "two and a half" or "half way to version three", it
 * is the fifth second-level revision of the second first-level revision.
 * 
 * Here is an example of version numbers ordering:
 * 
 * 0.1 < 1.1 < 1.2 < 13.37
 * 
 * Tags: String
 */
class CompareVersionNums {
    public static void main(String[] args) {
        String v1 = "001.3.3.7.000";
        String v2 = "1.3.3.7";
        System.out.println(compareVersion(v1, v2));
    }
    
    /**
     * Compare each level and compare the rest
     * Note the input can be complex than the example, more dots, more zeros
     */
    int compareVersion(String version1, String version2) {
        if (version1 == null && version2 == null) return 0; // same
        if (version1 == null || version2 == null) return version1 == null ? -1 : 1;
        String[] list1 = version1.split("\\."); // back slash
        String[] list2 = version2.split("\\.");
        int i = 0;
        while (i < list1.length || i < list2.length) {
            int a = i < list1.length ? Integer.valueOf(list1[i]) : 0;
            int b = i < list2.length ? Integer.valueOf(list2[i]) : 0;
            if (a < b) return -1;
            else if (a > b) return 1;
            i++; // update i
        }
        return 0;
    }
}
