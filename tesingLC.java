import org.junit.Test;
import static org.junit.Assert.assertEquals;

import java.util.HashMap;

public class tesingLC {

    @Test
    public void testConvert(){
        Leetcode lcTest = new Leetcode();

        String s = "PAYPALISHIRING";

        assertEquals("Test 1 row", "PAYPALISHIRING", lcTest.convert2(s, 1));

        assertEquals("Test 3 rows", "PAHNAPLSIIGYIR", lcTest.convert2(s, 3));

        assertEquals("Test 4 rows", "PINALSIGYAHRPI", lcTest.convert2(s, 4));

    }

    // Test cases not in use
    public static void main(String args[]) {
        //Testing setZeroes method
        /*int[][] a1 = {{1, -100, 1}, {2, 1, 2}};

        for(int i = 0; i < a1.length; i++) {
            System.out.println(Arrays.toString(a1[i]));
        }
        System.out.println();
        lcTest.setZeroes(a1);
        for(int i = 0; i < a1.length; i++) {
            System.out.println(Arrays.toString(a1[i]));
        } */
        
        // Testing reverse method
        /*System.out.println(lcTest.reverse(-1234));
        System.out.println(lcTest.reverse(1));
        System.out.println(lcTest.reverse(1534236469));
        System.out.println(lcTest.reverse(2147483647)); */

        // Testing atoi method
        /*System.out.println(lcTest.myAtoi("42"));
        System.out.println(lcTest.myAtoi("  -42"));
        System.out.println(lcTest.myAtoi(" 4193 with words"));
        System.out.println(lcTest.myAtoi("-91283472332"));
        System.out.println(lcTest.myAtoi("91283472332")); */

        // Testing median method
        /*int[] a1 = {1, 2}, a2 = {};
        System.out.println(lcTest.findMedianSortedArrays(a1, a2)); */

        // Testing addTwoNumbers method
        /*Leetcode.ListNode l1 = lcTest.new ListNode(2, lcTest.new ListNode(4, lcTest.new ListNode(3, null)));
        Leetcode.ListNode l2 = lcTest.new ListNode(5, lcTest.new ListNode(6, lcTest.new ListNode(4, null)));
        System.out.println(lcTest.addTwoNumbers(l1, l2).next.next.val); */

        // Testing longestSubstring method
        /*String s1 = "abcdefg";
        String s2 = "abcabcbb";
        String s3 = "bbb";
        System.out.println(lcTest.lengthOfLongestSubstring(s1));
        System.out.println(lcTest.lengthOfLongestSubstring(s2));
        System.out.println(lcTest.lengthOfLongestSubstring(s3)); */

        // Testing maxArea
        /*int[] height = {1,8,6,2,5,4,8,3,7};
        System.out.println(lcTest.maxArea(height)); */

        // Testing validSudoku method
        /* char[][] board = {{'5', '3', '.', '.', '7', '.', '.', '.', '.'},
                        {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
                        {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
                        {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
                        {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
                        {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
                        {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
                        {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
                        {'.', '.', '.', '.', '8', '.', '.', '7', '9'}};
        System.out.println(lcTest.isValidSudoku(board)); */

        // Testing plusOne method
        /*int[] a1 = {1, 2, 3};
        int[] a2 = {0};
        int[] a3 = {9, 9, 9};
        int[] a4 = {1, 2, 9};
        System.out.println(Arrays.toString(lcTest.plusOne(a1)));
        System.out.println(Arrays.toString(lcTest.plusOne(a2)));
        System.out.println(Arrays.toString(lcTest.plusOne(a3)));
        System.out.println(Arrays.toString(lcTest.plusOne(a4))); */
    }

    // Works but VERY slow
    public int lengthOfLongestSubstring(String s) {
        
        HashMap<Character, Integer> map = new HashMap<Character, Integer>(s.length());
        // Store char and key in hashmap, if dupe found, start checking again at iDup + 1
        int result = 0;
        int length = 0;


        int i = 0;
        while(i < s.length()) {
            if(!map.containsKey(s.charAt(i))) {
                map.put(s.charAt(i), i);
                length++;
                i++;
            }
            else{   
                result = Math.max(result, length);
                length = 0;
                i = map.get(s.charAt(i)) + 1;
                map.clear();
            }
        }
        result = Math.max(result, length);

        return result;
    }

}
