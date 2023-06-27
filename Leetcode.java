import java.util.Arrays;
import java.util.HashMap;
import java.util.Stack;

public class Leetcode {

    public static void main(String args[]) {
        Leetcode lcTest = new Leetcode();
        
        int n = 1500;
        System.out.println(lcTest.intToRoman(n));
        
    }  

    public String intToRoman(int num) {
        StringBuilder result = new StringBuilder();

        int currentNum = 0;
        
        // Start with 1000s
        currentNum = num/1000;
        for (int i = 0; i < currentNum; i++) {
            result.append('M');
        }

        num = num % 1000;

        // Next check for 900, then 500, then 400
        if(num / 900 == 1){
            result.append("CM");
            num = num % 900;
        }
        if(num / 500 == 1) {
            result.append('D');
            num = num % 500;
        }
        if(num / 400 == 1) {
            result.append("CD");
            num = num % 400;
        }

        // Next check 100s
        currentNum = num / 100;
        for(int i = 0; i < currentNum; i++) {
            result.append('C');
        }
        num = num % 100;

        // Next check for 90, then 50, then 40
        if(num / 90 == 1){
            result.append("XC");
            num = num % 90;
        }
        if(num / 50 == 1) {
            result.append('L');
            num = num % 50;
        }
        if(num / 40 == 1) {
            result.append("XL");
            num = num % 40;
        }

        // Next check 10s
        currentNum = num / 10;
        for(int i = 0; i < currentNum; i++) {
            result.append('X');
        }
        num = num % 10;

        // Next check for 90, then 50, then 40
        if(num / 9 == 1){
            result.append("IX");
            num = num % 9;
        }
        if(num / 5 == 1) {
            result.append('V');
            num = num % 5;
        }
        if(num / 4 == 1) {
            result.append("IV");
            num = num % 4;
        }

        // Finally, check 1s
        currentNum = num / 1;
        for(int i = 0; i < currentNum; i++)
            result.append('I');
        num = num % 1;

        return result.toString();
    }

    public int longestValidParentheses(String s) {
        int result = 0, current = 0;
        /*Stack<Character> stack = new Stack<Character>();

        for(int i = 0; i < s.length(); i++) {
            if(s.charAt(i) == '(')
                stack.push(s.charAt(i));
            else if(stack.isEmpty()) {
                current = 0;
                continue;
            }
            else {
                result = Math.max(result, current);
            }
        }*/
        return result; 
    }

    public ListNode removeNthFromEnd(ListNode head, int n) {
        // Always removes at least 1 val, and can't remove more than 1 if len = 1
        if(head.next == null)
            return null;

        ListNode tracker = head;
        int length = 0;
        while(tracker != null) {
            length++;
            tracker = tracker.next;
        }

        // Remove first element
        if(length == n) 
            return head.next;

        int toRemove = length - n;

        tracker = head;

        for(int i = 1; i < toRemove; i++)
            tracker = tracker.next;

        tracker.next = tracker.next.next;

        return head;
    }

    public boolean isValidParentheses(String s) {
        if(s.length() == 1)
            return false;
        Stack<Character> stack = new Stack<Character>();
        Character c = null;
        for(int i = 0; i < s.length(); i++) {
            // Push open chars onto stack
            if(s.charAt(i) == '(' || s.charAt(i) == '{' || s.charAt(i) == '[')
                stack.push(s.charAt(i));
            
            // First encountered char is closing bracket
            else if(stack.isEmpty())
                return false;
            
            else {
                c = stack.pop();
                // Must close in same order as opened
                if((s.charAt(i) == ')' && c != '(') || 
                   (s.charAt(i) == '}' && c != '{') || 
                   (s.charAt(i) == ']' && c != '['))
                    return false;
            }
        }
        // If stack isn't empty, only encountered opening brackets
        return stack.isEmpty();
    }

    // Sets entire row/col of a matrix to 0 if they have a 0 in them
    public void setZeroes(int[][] matrix) {
        // Edge cases if 1 dimensional matrix
        if(matrix.length == 1) {
            boolean isZero = false;
            for(int i = 0; i < matrix[0].length; i++){
                if(matrix[0][i] == 0){
                    isZero = true;
                    break;
                }
            }
            if(isZero) {
                for(int i = 0; i < matrix[0].length; i++)
                    matrix[0][i] = 0;
            }
            return;
        }

        if(matrix[0].length == 1) {
            boolean isZero = false;
            for(int i = 0; i < matrix.length; i++){
                if(matrix[i][0] == 0){
                    isZero = true;
                    break;
                }
            }
            if(isZero) {
                for(int i = 0; i < matrix.length; i++)
                    matrix[i][0] = 0;
            }
            return;
        }
        
        boolean firstRowZ = false, firstColZ = false, bothZ = false;
        // Runs through only first row and column to find zeroes
        if(matrix[0][0] == 0)
            bothZ = true;
        else {
            for(int i = 0; i < matrix[0].length; i++) {
                if(matrix[0][i] == 0) {
                    firstColZ = true;
                    break;
                }
            }
            for(int i = 0; i < matrix.length; i++) {
                if(matrix[i][0] == 0) {
                    firstRowZ = true;
                    break;
                }
            }
        }   
        
        // Runs through the whole array and finds zeroes
        for(int i = 1; i < matrix.length; i++){
            for(int j = 1; j < matrix[i].length; j++) {
                if(matrix[i][j] == 0) {
                    matrix[0][j] = 0;
                    matrix[i][0] = 0;
                }
            }
        }

        // Sets the *rows* of matrix to zeroes
        for(int i = 1; i < matrix.length; i++) {
            if(matrix[i][0] == 0) {
                for(int j = 0; j < matrix[i].length; j++) {
                    matrix[i][j] = 0;
                }
            }
        }

        // Sets the *columns* of matrix to zeroes
        for(int j = 1; j < matrix[0].length; j++) {
            if(matrix[0][j] == 0) {
                for(int i = 0; i < matrix.length; i++) {
                    matrix[i][j] = 0;
                }
            }
        }

        // Finally sets the first row/col
        if(bothZ) {
            for(int i = 0; i < matrix[0].length; i++)
                matrix[0][i] = 0;
            for(int i = 0; i < matrix.length; i++)
                matrix[i][0] = 0;
            return;
        }
        if(firstColZ)
            for(int i = 0; i < matrix[0].length; i++)
                matrix[0][i] = 0;
        if(firstRowZ)
            for(int i = 0; i < matrix.length; i++)
                matrix[i][0] = 0;
    }

    // Solves the problem though doesn't technically 'sort'
    // Still fast and constant memory
    public void sortColors(int[] nums) {
        int numZ = 0, numO = 0;

        for(int i = 0; i < nums.length; i++) {
            switch(nums[i]){
                case 0: numZ++; break;
                case 1: numO++; break;
            }
        }
        for(int i = 0; i < numZ; i++)
            nums[i] = 0;
        
        for(int i = numZ; i < (numZ + numO); i++)
            nums[i] = 1;
        
        for(int i = numZ+numO; i < nums.length; i++)
            nums[i] = 2;
    }

    // Alternate solution: return Math.pow(x, n);
    public double myPow(double x, int n) {
        // Recursive sol, doesn't count 0.1/0.001/etc. cases
        /* // No problem without, but greatly speeds up edge cases
        if(x == 1)
            return 1;
        if(x == 0)
            return 0;

        // Base cases
        if(n == 0)
            return 1;
        if(n == 1)
            return x;
        if(n > 0)
            return (x * this.myPow(x, n-1));
        else
            return (1/(this.myPow(x, -n))); */
        if(x == -1) {
            if(n % 2 == 1)
                return -1;
            else
                return 1;
        }
        if(x == 1)
            return 1;
        if(x == 0)
            return 0;
        if(n == 0)
            return 1;
        if (n == 1)
            return x;
        
        double mult = x;

        if(n < 0){
            x = 1/x;
            mult = 1/mult;

            for(int i = -1; i > n; i--) {
                x*= mult;
                if(x == 0){
                    return 0;
                }
            }
        }
        else {
            for(int i = 1; i < n; i++){
                x *= mult;
                if(x == 0)
                    return 0;
            }
        }
        return x;
    }

    // Sort array, then two pointers. O(nlogn)
    public int[] twoSum(int[] nums, int target) {
        int[] ans = new int[2];
        int[] sorted = Arrays.copyOf(nums, nums.length);
        Arrays.sort(sorted);

        int left = 0, right = nums.length - 1, sum = 0, val1 = 0, val2 = 0;

        while(left < right) {
            sum = sorted[left] + sorted[right];
            if(sum == target) {
                val1 = sorted[left];
                val2 = sorted[right];
                break;
            }
            else if(sum < target)
                left++;
            else
                right--;
        }
        //System.out.printf("Left: %s, Right: %s, val1: %s, val2: %s \n", left, right, val1, val2);
        boolean done = false;
        for(int i = 0; i < nums.length; i++) {
            if(nums[i] == val1 && !done){
                done = true;
                ans[0] = i;
            }
            if(nums[i] == val2 && i != ans[0])
                ans[1] = i;
        }
        return ans;
    }

    // Technically O(n), maybe more memory
    public int[] twoSum2(int[] nums, int target){
        int[] ans = new int[2];

        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();

        for(int i = 0; i < nums.length; i++){
            map.put(nums[i], i);
        }

        int diff = 0;
        for(int i = 0; i < nums.length; i++){
            diff = target - nums[i];
            if(map.get(diff) != null && map.get(diff) != i) {
                ans[0] = map.get(diff);
                ans[1] = i;
                return ans;
            } 
        }

        return ans;
    }

    // returns array of double size w all elements repeated
    public int[] getConcatenation(int[] nums) {
        int n = nums.length;
        int[] ans = new int[2*n];
        for(int i = 0; i < n; i++) {
            ans[i] = nums[i];
            ans[i+n] = nums[i];
        }
        return ans;
    }

    // Removes duplicate values from a sorted linked list
    public ListNode deleteDuplicates(ListNode head) {
        if(head == null)
            return head;
        
        ListNode tracker = head;
        while(tracker.next != null) {
            if(tracker.val == tracker.next.val){
                tracker.next = tracker.next.next;
            }
            else{
                tracker = tracker.next;
            }
        }

        return head;
    }

    // Adds 1 to integer where each digit is array element
    // First element is most significant
    public int[] plusOne(int[] digits) {
        // carry set to 1 to account for inital plus 1
        int carry = 1, sum;
        for(int i = digits.length - 1; i >= 0; i--){
            sum = digits[i] + carry;
            digits[i] = sum % 10;

            carry = sum/10;
        }

        if(carry != 0) {
            int[] newDigits = new int[digits.length + 1];

            for(int i = 0; i < digits.length + 1; i++){
                if(i == 0)
                    newDigits[i] = carry;
                else
                    newDigits[i] = digits[i-1];
            }
            return newDigits;
        }

        return digits;
    }

    // Is checking rows and columns, not checking sub-boxes yet
    public boolean isValidSudoku(char[][] board){
        // Check rows
        for(int i = 0; i < 9; i++) {
            for(int j = 0; j < 9; j++) {
                if(board[i][j] != '.') {
                    for(int k = j + 1; k < 9; k++) {
                        if(board[i][j] == board[i][k])
                            return false;
                    }
                }
            }
        }
        // Check columns
        for(int i = 0; i < 9; i++) {
            for(int j = 0; j < 9; j++) {
                if(board[j][i] != '.') {
                    for(int k = j + 1; k < 9; k++) {
                        if(board[j][i] == board[k][i])
                            return false;
                    }
                }
            }
        }

        // Check boxes
        
        return true;
    }

    // Technically working but slow for large numbers
    // Kind of brute force addition/subtraction
    public int divide(int dividend, int divisor) {
        int quotient = 0, sum = 0;
        boolean isNeg = false;

        if(dividend == Integer.MIN_VALUE) {
            if(divisor == -1)
                return Integer.MAX_VALUE;
            if(divisor < 0)
                divisor = -divisor;
            else
                isNeg = true;
        }
        else{
            // If both are neg, then quotient should be +ve
            if(dividend < 0) {
                isNeg = !isNeg;
                dividend = -dividend;
            }
            if(divisor < 0){
                isNeg = !isNeg;
                divisor = -divisor;
           }
        }
        // Only if it is MIN_VALUE
        if(dividend < 0) {
            sum = dividend;
            while(sum <= 0) {
                sum += divisor;
                quotient++;
            }
        }

        while(sum <= dividend && sum >= 0) {
            sum += divisor;
            quotient++;
        }

        if(isNeg)
            return -(quotient - 1);
        
        return quotient-1;
    }

    // Finds the length of the longest substring w/o repeating chars
    // Very slow and memory inefficient, uses HashMap
    // Redo maybe w HashSet
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

    // Given an integer array, finds the largest 'area' if each value
    // was a line, and the width was diff between indices
    public int maxArea(int[] height) {
        int maxArea = 0, area = 0;

        int left = 0, right = height.length - 1;

        while(left < right) {
            area = Math.min(height[left], height[right]) * (right - left);
            maxArea = Math.max(maxArea, area);
            if(height[left] < height[right])
                left++;
            else
                right--;
        }

        return maxArea;
    } 

    // Converts given string into ZigZag format based on numRows
    // Returns the string read row by row
    // Works but is slow and uses a lot of memory
    public String convert(String s, int numRows) {

        if(numRows == 1)
            return s;

        StringBuilder result = new StringBuilder();
        
        char[][] ziggy = new char[numRows][s.length()];

        int i = 0, row = 0, col = 0;
        while(i < s.length()) {
            if(row == 0) {
                for(int j = 0; i < s.length() && j < numRows; j++) {
                    ziggy[row++][col] = s.charAt(i++);
                }
                row--;
                col++;
            }
            else{
                ziggy[row][col++] = s.charAt(i++);
            }
            row--;
        }

        for(int j = 0; j < numRows; j++){
            for(int k = 0; k < s.length(); k++) {
                System.out.print(ziggy[j][k] + " ");
                if(Character.isAlphabetic(ziggy[j][k]) || ziggy[j][k] == ',' || ziggy[j][k] == '.')
                    result.append(ziggy[j][k]);
            }
            System.out.println("");
        }

        return result.toString();
    }
    
    // WIP, faster but not complete
    public String convert2(String s, int numRows) {
        StringBuilder result = new StringBuilder();

        int cycleLength = 2*numRows - 2;

        for(int i = 0; i < numRows; i++) {
            for(int j = i; j < s.length(); j+= cycleLength) {
                result.append(s.charAt(j));
            }
        }

        return result.toString();
    }

    // Reverses the digits of a signed integer
    public int reverse(int x) {
        int result = 0, num = x;
        boolean isNeg = false;

        if(x < 0)
            isNeg = true;

        int exp = -1;

        // Calculates '10' multiplier for reversal
        while(num != 0){
            exp++;
            num /= 10;
        }

        num = x;

        double multD = Math.pow(10, exp);
        
        int mult = (int)multD;

        // Checks if overflow
        if(mult >= 1000000000 && x%10 > 2)
            return 0;

        while(num != 0) {
            result += (num%10) * mult;
            num /= 10;
            mult /= 10;
        }

        // Checks if overflow
        if((isNeg && result > 0) || (!isNeg && result < 0))
            return 0;
        
        return result;
    }

    // Converts string into integer
    // Ignores whitespace and letters
    public int myAtoi(String s) {
        int result = 0, i = 0;
        boolean isNeg = false, readDigits = false;
        String noSpace = s.trim();
        StringBuilder resString = new StringBuilder();

        if(noSpace.isEmpty())
            return 0;

        if(noSpace.charAt(0) == '-') {
            i++;
            isNeg = true;
        }
        else if(noSpace.charAt(0) == '+')
            i++;
            
        while(i < noSpace.length() && Character.isDigit(noSpace.charAt(i))){
            // Checks if there is at least 1 digit
            readDigits = true;
            resString.append(noSpace.charAt(i++));
        }

        try {
            result = Integer.parseInt(resString.toString());
            if(isNeg)
                return -1*result;
            return result;
        }

        catch(Exception e) {
            // readDigits verifies whether exception is because int is too big or no int read
            if (readDigits){
                if(isNeg)
                    return Integer.MIN_VALUE;
                else
                    return Integer.MAX_VALUE;
            }

            return result;
        }
    }

    // Finds the median value of two sorted arrays
    // Can check only when combined length is odd. Even is WIP
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        
        // Cases when 1 array is empty
        if(nums2.length == 0) {
            int length = nums1.length;
            if(length % 2 == 1)
                return nums1[(int)Math.floor(length / 2)];
            else
                return (nums1[length/2] + nums1[(length/2) - 1])/2;
        }
        else if (nums1.length == 0){
            int length = nums2.length;
            if(length % 2 == 1)
                return nums2[(int)Math.floor(length / 2)];
            else
                return (nums2[length/2] + nums2[(length/2) - 1])/2;
        }


        int totalLength = nums1.length + nums2.length;
        int result = 0;

        // length is odd
        if(totalLength % 2 == 1) {
            int index = totalLength/2;
            int count = 0, i = 0, j = 0;
            while(count != index+1) {
                if(nums1[i] < nums2[j]) {
                    result = nums1[i++];
                    count++;
                }
                else{
                    result = nums2[j++];
                    count++;
                }
            }
        }

        return result;
    }

    public class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
    public ListNode create(int[] values) {
        ListNode tracker = new ListNode();
        ListNode head = tracker;
        for(int i = 0; i < values.length; i++) {
            tracker.next = new ListNode(values[i]);
            tracker = tracker.next;
        }
        return head.next;
    }

    // Adds two numbers where each digit is a node in a linked list
    // Head is least significant digit
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode result = new ListNode();
        ListNode tracker = result;

        int val3, carry = 0;

        while(l1 != null && l2 != null) {
            val3 = (l1.val + l2.val + carry) % 10;
            // Carry used for next add
            carry = (l1.val + l2.val + carry)/10;

            tracker.next = new ListNode(val3);
            tracker = tracker.next;
            l1 = l1.next;
            l2 = l2.next;
        }

        while(l1 != null) {
            val3 = (l1.val + carry) % 10;
            carry = (l1.val + carry) / 10;

            tracker.next = new ListNode(val3);
            tracker = tracker.next;
            l1 = l1.next;
        }

        while(l2 != null) {
            val3 = (l2.val + carry) % 10;
            carry = (l2.val + carry) / 10;

            tracker.next = new ListNode(val3);
            tracker = tracker.next;
            l2 = l2.next;
        }

        if(carry != 0)
            tracker.next = new ListNode(carry);


        return result.next;
    }

}
