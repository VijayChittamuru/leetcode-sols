import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.ArrayList;

public class Leetcode {

    public static void main(String args[]) {
        Leetcode lcTest = new Leetcode();

        System.out.println(lcTest.countAndSay(10));
    }    

    


    public String countAndSay(int n) {
        if(n == 1) {
            return "1";
        }
        else {
            return say(countAndSay(n - 1));
        }
    }

    private String say(String sayValue) {
        int sum = 0;
        int n = sayValue.length() - 1;
        StringBuilder result = new StringBuilder();

        while(n >= 0) {
            sum = 0;
            char current = sayValue.charAt(n);
            while(n >= 0  && sayValue.charAt(n) == current) {
                sum++;
                n -= 1;
            }
            result.insert(0, current).insert(0, sum);
        }
        return result.toString();
    }

    public int searchInsert(int[] nums, int target) {
        int[] searchResult = binarySearch(nums, 0, nums.length - 1, target);
        if(searchResult[0] == target) {
            return searchResult[1];
        }
        else if (searchResult[0] > target) {
            return searchResult[1] - 1;
        }
        else {
            return searchResult[1] + 1;
        }
    }

    private int[] binarySearch(int[] nums, int m, int n, int target) {
        int mid = (m+n)/2;
        if(m == n) {
            return new int[]{nums[m], m};
        }
        else if(nums[mid] == target) {
            return new int[]{nums[mid], mid};
        }
        else if(nums[mid] > target) {
            n = mid - 1;
            return binarySearch(nums, m, n, target);
        }
        else {
            m = mid + 1;
            return binarySearch(nums, m, n, target);
        }
    }

    // Happy Number
    public boolean isHappy(int n) {
        HashSet<Integer> reached = new HashSet<Integer>();
        int sum = 0, m = n;
        while(sum != 1) {
            sum = 0;
            while(m != 0) {
                sum += Math.pow(m % 10, 2);
                m /= 10;
            }
            if(!reached.add(sum)) {
                return false;
            }
            m = sum;
        }
        return true;
    }
    
    // Divides sorted array into list of all inclusive ranges
    // O(N) time. Noticeable difference when not using StringBuilder
    public List<String> summaryRanges(int[] nums) {
        ArrayList<String> result = new ArrayList<>();
        if(nums.length == 0)
            return result;
        
        int first = nums[0], last = nums[0];
        // <= to add last element
        for(int i = 1; i <= nums.length; i++) {
            // If in range, change last
            if(i != nums.length && nums[i] - 1 == last) {
                last = nums[i];
            }
            // If encountered value not in range, add range to list
            else {
                StringBuilder b = new StringBuilder();
                // If range and not single value
                if(first != last) {
                    b.append(first);
                    b.append("->");
                    b.append(last);
                }
                else {
                    b.append(last);
                }
                result.add(b.toString());
                // Current element is next range starting point
                if(i != nums.length) {
                    first = nums[i];
                    last = nums[i];
                }
            }
        }
        return result;
    }

    // Reverses Linked List
    public ListNode reverseList(ListNode head) {
        ListNode previous = null, current = head, next = null;
        while(current != null) {
            next = current.next;
            current.next = previous;
            previous = current;
            current = next;
        }
        head = previous;
        return head;
    }

    // Not done, but close (I think), need pen and paper
    /* public boolean exist(char[][] board, String word) {
        boolean[][] boardCheck = new boolean[board.length][board[0].length];
        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[0].length; j++) {
                if(board[i][j] == word.charAt(i) && !boardCheck[i][j]) {
                    boardCheck[i][j] = true;
                    for(int k = 1; k <= word.length(); k++) {
                        int left = i - 1, right = i + 1, up = j - 1, down = j + 1;
                        // Full word found
                        if(k == word.length())
                            return true;
                        else if(left >= 0 && board[left][j] == word.charAt(k))
                            i = left;
                        else if(up >= 0 && board[i][up] == word.charAt(k))
                            j = up;
                        else if(down < board.length && board[i][down] == word.charAt(k))
                            j = down;
                        else if(right < board[0].length && board[right][j] == word.charAt(k))
                            i = right;
                        else
                            break;
                    }
                }
            }
        }
        return false;
    }
 */   

    public void reverseString(char[] s) {
        int left = 0, right = s.length - 1;
        char temp;

        while(left < right) {
            temp = s[right];
            s[right--] = s[left];
            s[left++] = temp;
        }
    }

    // Misunderstood smallest lexicographical order
    // Removes dupes and returns chars in sorted order
    public String removeDuplicateLetters(String s) {
        StringBuilder result = new StringBuilder();
        boolean[] charAdded = new boolean[26];

        for(int i = 0; i < s.length(); i++) {
            if(!charAdded[s.charAt(i) - 'a'])
                charAdded[s.charAt(i) - 'a'] = true;
        }

        for(int i = 0; i < 26; i++) {
            if(charAdded[i])
                result.append((char)('a'+i));
        }
        return result.toString();
    }

    public String getHint(String secret, String guess) {
        int bulls = 0, cows = 0;
        int[] count = new int[10];
        for(int i = 0; i < secret.length(); i++) {
            char s = secret.charAt(i), g = guess.charAt(i);
            if(s == g)
                bulls++;
            else {
                // If seen in guess previously then < 0
                if (count[s - '0'] < 0)
                    cows++;
                // vice versa
                if (count[g - '0'] > 0)
                    cows++;

                // Character seen in s incremented
                count[s - '0'] ++;
                // Character seen in guess decremented
                count[g - '0'] --;
            }
        }

        return (bulls + "A" + cows + "B");
    }

    public boolean isPowerOfTwo(int n) {
        if(n == 0)
            return false;
        double x = Math.log(n)/Math.log(2);
        return (Math.pow(2, Math.round(x)) == n);
    }

    public boolean containsNearbyDuplicate(int[] nums, int k) {
        // Value, Index
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < nums.length; i++) {
            if(map.containsKey(nums[i]) && (i - map.get(nums[i])) <= k)
                    return true;
            else
                map.put(nums[i], i);
        }
        return false;
    }

    public boolean containsDuplicate(int[] nums) {
        HashSet<Integer> set = new HashSet<>();
        for(int i = 0; i < nums.length; i++) {
            if(set.contains(nums[i]))
                return true;
            else
                set.add(nums[i]);
        }

        return false;
    }

    public ListNode removeElements(ListNode head, int val) {
        ListNode tracker = head;
        while(tracker != null && tracker.next != null) {
            if(tracker.next.val == val)
                tracker.next = tracker.next.next;
            else
                tracker = tracker.next;
        }

        if(head != null && head.val == val)
            head = head.next;

        return head;
    }

    // Input array sorted
    public int[] twoSum3(int[] numbers, int target) {
        int[] result = new int[2];
        int left = 0, right = numbers.length - 1;

        while(left < right) {
            if(numbers[left] + numbers[right] > target)
                right--;
            else if (numbers[left] + numbers[right] < target)
                left++;
            else {
                result[0] = left + 1;
                result[1] = right + 1;
                break;
            }
        }

        return result;
    }

    public String reverseWords(String s) {
        StringBuilder result = new StringBuilder();

        for(int i = 0; i < s.length(); i++) {
            if(s.charAt(i) != ' '){
                StringBuilder word = new StringBuilder();
                while(i < s.length() && s.charAt(i) != ' ')
                    word.append(s.charAt(i++));
                word.append(' ');
                result.insert(0, word);
            }
        }
        result.deleteCharAt(result.length() - 1);
        return result.toString();
    }

    public List<Integer> preorderTraversal(TreeNode root) {
        ArrayList<Integer> preorderList = new ArrayList<>();
        preorder(root, preorderList);
        return preorderList;
    }
    // recursive preorder traversal helper
    private void preorder(TreeNode root, List<Integer> list) {
        if(root == null)
            return;
        list.add(root.val);
        preorder(root.left, list);
        preorder(root.right, list);
    }

    public int maxProfit(int[] prices) {
        int minNum = 99999, current = 0, result = 0;

        for(int i = 0; i < prices.length; i++) {
            if(prices[i] < minNum)
                minNum = prices[i];
            current = prices[i] - minNum;

            if(result < current)
                result = current;
        }

        return result;
    }

    public int minDepth(TreeNode root) {
        if(root == null)
            return 0;
        if(root.left == null)
            return 1 + minDepth(root.right);
        if(root.right == null)
            return 1 + minDepth(root.left);

        return 1 + Math.min(minDepth(root.left), minDepth(root.right));
    }

    // List problems. Level order w/ queue, adding to right list pain
    public List<List<Integer>> levelOrder(TreeNode root) {
        ArrayList<List<Integer>> result = new ArrayList<List<Integer>>();
        if(root == null)
            return result;
        LinkedList<TreeNode> queue = new LinkedList<TreeNode>();
        int level = 0;
        ArrayList<Integer> a = new ArrayList<>();
        queue.offer(root);
        a.add(root.val);

        while(!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if(node.left != null)
                queue.offer(node.left);
            if(node.right != null)
                queue.offer(node.right);

            if(level == 0) {
                level = queue.size();
                result.add(a);
                a = new ArrayList<>();
                if(node != root) {
                    a.add(node.val);
                    level--;
                }
            }
            else {
                a.add(node.val);
                level--;
            }
        }
        // result.add(a);

        return result;
    }

    public int maxDepth(TreeNode root) {
        if(root == null)
            return 0;

        return 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
    }

    public boolean isSameTree(TreeNode p, TreeNode q) {
        if(p == null && q == null)
            return true;
        if(p == null && q != null)
            return false;
        if(p != null && q == null)
            return false;
        if(p.val != q.val)
            return false;

        return(isSameTree(p.left, q.left) && isSameTree(p.right, q.right));
    }

    // helper method to find max value of tree
    private int maxValue(TreeNode root) {
        if(root == null)
            return Integer.MIN_VALUE;
        int val = root.val;
        int leftMax = maxValue(root.left);
        int rightMax = maxValue(root.right);

        return Math.max(val, Math.max(leftMax, rightMax));
    }

    // helper method to find min value of tree
    private int minValue(TreeNode root) {
        if(root == null)
            return Integer.MAX_VALUE;
        int val = root.val;
        int leftMin = minValue(root.left);
        int rightMin = minValue(root.right);

        return Math.min(val, Math.min(leftMin, rightMin));
    }

    // Works, but slow-ish and lot of memory
    public boolean isValidBST(TreeNode root) {
        // Base case
        if(root == null)
            return true;
        if(root.left != null && root.val <= maxValue(root.left))
            return false;
        if(root.right != null && root.val >= minValue(root.right))
            return false;

        return isValidBST(root.left) && isValidBST(root.right);
    }

    public String addBinary(String a, String b) {
        // Doesn't work w/ binary > max integer
        /*// parseInt w/ radix 2 converts binary string to decimal
        int sum = Integer.parseInt(a, 2) + Integer.parseInt(b,2);
        // Converts sum back to binary string
        String result = Integer.toBinaryString(sum);
        return result; */

        int i = (a.length() - 1), j = (b.length() - 1), sum = 0, carry = 0;
        StringBuilder result = new StringBuilder();
        while(i >= 0 && j >= 0) {
            sum = carry + (int)(a.charAt(i--) + b.charAt(j--) - '0' - '0');
            carry = sum - 1;
            if(sum % 2 == 1)
                sum = 1;
            else
                sum = 0;
            result.append(sum);
        }

        while(i >= 0) {
            sum = carry + (int)(a.charAt(i--) - '0');
            carry = sum - 1;
            if(sum % 2 == 1)
                sum = 1;
            else
                sum = 0;
            result.append(sum);
        }

        while(j >= 0) {
            sum = carry + (int)(b.charAt(j--) - '0');
            carry = sum - 1;
            if(sum % 2 == 1)
                sum = 1;
            else
                sum = 0;
            result.append(sum);
        }
        result.append(Integer.toBinaryString(carry));
        return result.reverse().toString();
    }

    public boolean buddyStrings(String s, String goal) {
        if(s.length() != goal.length())
            return false;
        if(s.length() == 1)
            return false;

        if(s.equals(goal)){
            int[] a = new int[26];
            for(int i = 0; i < s.length(); i++) {
                // If char was already in string, then it can be swapped with itself if s == goal
                if(a[s.charAt(i) - 'a'] != 0)
                    return true;
                a[s.charAt(i) - 'a']++;
            }
        }

        int count = 0;
        char s1 = ' ', g1 = ' ';

        for(int i = 0; i < s.length(); i++) {
            if(s.charAt(i) != goal.charAt(i)) {
                count++;
                // first time finding unequal chars
                if(s1 == ' ') {
                    s1 = s.charAt(i);
                    g1 = goal.charAt(i);
                }
                else if(goal.charAt(i) != s1 || s.charAt(i) != g1)
                    return false;
            }
        }

        return count == 2;
    }

    // Store chars in map with indices, check at end for validity
    // Not done
    public boolean isNumber(String s) {
        if(s.charAt(0) != '.' || s.charAt(0) != '+' ||
           s.charAt(0) != '-' || !Character.isDigit(s.charAt(0)))
            return false;

        // Stores character and it's index value
        HashMap<Character, Integer> map = new HashMap<Character, Integer>();

        for(int i = 0; i < s.length(); i++) {
            if(Character.isAlphabetic(s.charAt(i))) {
                if(s.charAt(i) == 'e' || s.charAt(i) == 'E'){
                    // NaN because 2+ 'E' exist
                    if(map.containsKey(Character.toLowerCase(s.charAt(i))))
                        return false;
                    else
                        map.put(Character.toLowerCase(s.charAt(i)), i);
                }

            }
        }

        return false;
    }

    // Does not work for strings with same chars same lengths but different
    // frequency of chars, like dgggg and gdddd because regex.
    public List<List<String>> groupAnagrams(String[] strs) {
        ArrayList<List<String>> list = new ArrayList<List<String>>();
        boolean[] checked = new boolean[strs.length];
        Pattern pattern;
        Matcher matcher;
        ArrayList<String> empty = new ArrayList<String>();

        for(int i = 0; i < strs.length; i++) {
            if(strs[i].isEmpty())
                empty.add(strs[i]);
            else if(!checked[i]) {
                StringBuilder regex = new StringBuilder();
                ArrayList<String> toAdd = new ArrayList<String>();
                checked[i] = true;
                regex.append("^[");
                regex.append(strs[i]);
                regex.append("]+$");
                pattern = Pattern.compile(regex.toString());

                for(int j = i; j < strs.length; j++) {
                    if(!checked[j]) {
                        matcher = pattern.matcher(strs[j]);
                        if(matcher.matches() && strs[i].length() == strs[j].length()) {
                            toAdd.add(strs[j]);
                            checked[j] = true;
                        }
                    }
                }
                toAdd.add(strs[i]);
                list.add(toAdd);
            }
        }
        if(!empty.isEmpty())
            list.add(empty);

        return list;
    }

    // not done
    public List<String> letterCombinations(String digits) {
        ArrayList<String> result = new ArrayList<String>();
        /*double numToAdd = Math.pow(3, digits.length() - 1);
        for(int i = 0; i < digits.length(); i++) {
            if(digits.charAt(i) == '9' || digits.charAt(i) == '6')
                numToAdd++;
        }

        ArrayList<StringBuilder> a = new ArrayList<StringBuilder>();
        // Adds all elements from the first digit to the list
        if(digits.length() > 0) {
            String s = Leetcode.phoneDigit(digits.charAt(0));
            for(int i = 0; i < s.length(); i++) {
                for(int j = 0; j < numToAdd; j++) {
                    a.add(new StringBuilder(s.charAt(i)));
                }
            }
        }

        if(digits.length() > 1) {
            for(int i = 0; i < a.size();) {

            }
        }
        */
        return result;
    }
    // helper method
    /* private static String phoneDigits(char c){
        if(c == '2')
            return "abc";
        if(c == '3')
            return "def";
        if(c == '4')
            return "ghi";
        if(c == '5')
            return "jkl";
        if(c == '6')
            return "mno";
        if(c == '7')
            return "pqrs";
        if(c == '8')
            return "tuv";
        if(c == '9')
            return "wxyz";
        return null;
    }
 */

    // Converts an integer to roman numerals
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

    // Removes the Nth node going backwards in a linked list
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

    // Checks if parentheses sequence is valid
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

    // Technically O(n), more memory because HashMap
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
                return (nums1[length/2] + nums1[(length/2) - 1])/2.0;
        }
        else if (nums1.length == 0){
            int length = nums2.length;
            if(length % 2 == 1)
                return nums2[(int)Math.floor(length / 2)];
            else
                return (nums2[length/2] + nums2[(length/2) - 1])/2.0;
        }

        int totalLength = nums1.length + nums2.length;
        double result = 0;

        // length is odd
        if(totalLength % 2 == 1) {
            int index = totalLength/2;
            int count = 0, i = 0, j = 0;
            while(count != index+1 && i < nums1.length && j < nums2.length) {
                if(nums1[i] < nums2[j]) {
                    result = nums1[i++];
                    count++;
                }
                else{
                    result = nums2[j++];
                    count++;
                }
            }
            while(count != index+1 && i < nums1.length) {
                result = nums1[i++];
                count++;
            }
            while(count != index+1 && j < nums2.length) {
                result = nums2[j++];
                count++;
            }
        }
        else {
            int index = totalLength/2;
            double val1 = 0, val2 = 0;
            int count = 0, i = 0, j = 0;
            while(count != index && i < nums1.length && j < nums2.length) {
                if(nums1[i] < nums2[j]) {
                    val1 = nums1[i++];
                    count++;
                }
                else{
                    val1 = nums2[j++];
                    count++;
                }
            }
            while(count != index && i < nums1.length) {
                val1 = nums1[i++];
                count++;
            }
            while(count != index && j < nums2.length) {
                val1 = nums2[j++];
                count++;
            }

            if(i >= nums1.length)
                val2 = nums2[j];

            else if(j >= nums2.length)
                val2 = nums1[i];

            else if(nums1[i] > nums2[j])
                val2 = nums2[j];

            else
                val2 = nums1[i];
            
            result = (val1+val2)/2;
        }
        
        return result;
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

    public ListNode create(int[] values) {
        ListNode tracker = new ListNode();
        ListNode head = tracker;
        for(int i = 0; i < values.length; i++) {
            tracker.next = new ListNode(values[i]);
            tracker = tracker.next;
        }
        return head.next;
    }

    public TreeNode cTree(int val) {
        Leetcode lcTest = new Leetcode();
        return lcTest.new TreeNode(val);
    }

    public TreeNode cTree(int val, TreeNode left, TreeNode right) {
        Leetcode lcTest = new Leetcode();
        return lcTest.new TreeNode(val, left, right);
    }

    public class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
        public String toString() {
            ArrayList<Integer> s = new ArrayList<Integer>();
            ListNode temp = this;
            while(temp != null){
                s.add(temp.val);
                temp = temp.next;
            }

            return s.toString();
        }
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

}
