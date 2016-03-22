import java.util.*;
/**
 * Pascal's Triangle
 * Given numRows, generate the first numRows of Pascal's triangle.
 * For example, given numRows = 5,
 * Return
 * [
 * [1],
 * [1,1],
 * [1,2,1],
 * [1,3,3,1],
 * [1,4,6,4,1]
 * ]
 * Tags: Array
 * Similar Problems (E) Pascal's Triangle II
 *
 * Analysis : using dp
 * @author chenshuna
 */

public class Pascal_Triangle {
    public static List<List<Integer>> generate(int numRows) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        ArrayList<Integer> member = new ArrayList<Integer>();
        if(numRows <= 0){
            return res;
        }
        member.add(1);
        res.add( member);
        return generateArray(res, numRows-1);
    }
    
    public static List<List<Integer>> generateArray(List<List<Integer>> res, int n){
        if(n == 0){
            return res;
        }
        else{
            List<Integer> resLast= new ArrayList<Integer>(); 
            ArrayList<Integer> newMember = new ArrayList<Integer>(); 
            resLast = res.get(res.size()-1);
            newMember.add(1);
            for(int i = 0; i < resLast.size()-1; i++){
               int temp = resLast.get(i) + resLast.get(i+1);
               newMember.add(temp);
            }
            newMember.add(1);
            res.add(newMember);
            return generateArray(res, n-1);
        }
    }

    public static void main(String[] args) {
        System.out.print(generate(5));
    }
}
