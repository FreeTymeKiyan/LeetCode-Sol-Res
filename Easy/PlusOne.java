import java.util.Arrays;

/**
 * Given a non-negative number represented as an array of digits, 
 * <strong>plus one</strong> to the number.
 * 
 * The digits are stored such that the most significant digit is 
 * at the head of the list.
 * 
 * Tags: Array, Math
 */
class PlusOne {
    public static void main(String[] args) {
        int[] result = plusOne(new int[]{9, 9, 9, 9, 8});
        System.out.print("{ ");
        for (int i = 0; i < result.length; i++) {
            System.out.print(result[i] + " ");
        }
        System.out.println("}");
    }
    
    /**
     * Add 1 for the last digit
     * If carry, add one to next digit
     * If no carry, return
     * If all have carry, create a new int array
     */
    public int[] plusOneOthers(int[] digits) {
        for(int i = digits.length - 1; i >= 0; i--){
            digits[i] = 1 + digits[i];
            if (digits[i] == 10) {
                digits[i] = 0;
            } else return digits; // no carry for current digit
        }
        
        int[] ans = new int[digits.length + 1];
        ans[0] = 1;
        for(int i = 0;i < digits.length; i++) ans[i + 1] = digits[i];
        return ans;
    }
    
    public static int[] plusOne(int[] digits) {
        int count = digits.length;
        while (count > 0) {
            digits[count - 1] = digits[count - 1] + 1;
            if (digits[count - 1] > 9) {
                digits[count - 1] = 0;
            } else {
                return digits;
            }
            count--;
        }
        int[] result = new int[digits.length + 1];
        result[0] = 1;
        for (int i = 1; i < digits.length; i++) {
            result[i] = digits[i - 1];
        }
        
        return result;
    }
}
