package valid_palindrome;

public class Solution {
    public boolean isPalindrome(String s) {

        // remove all wrong chars + make to array
        char[] stripped = s.replaceAll("[^a-zA-Z0-9]", "").toLowerCase().toCharArray();

        // two pointers
        for(int i = 0; i < stripped.length; i++){
            int p1 = i;
            int p2 = stripped.length - i - 1;

            // return false if at any point it's different
            if(stripped[p1] != stripped[p2]){
                return false;
            }
        }
        return true;
    }
}
