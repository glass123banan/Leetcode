package valid_anagram;
import java.util.Arrays;
import java.util.HashMap;

public class Solution {
    public boolean isAnagram(String s, String t) {
        char[] lstS = s.toCharArray();
        char[] lstT = t.toCharArray();

        Arrays.sort(lstS);
        String sortedS = new String(lstS);

        Arrays.sort(lstT);
        String sortedT = new String(lstT);

        return sortedS.equals(sortedT);
    }

    public static void main(String[] args) {
        String s = "anagram";
        String t = "nagaram";
        
        Solution solution = new Solution();
        System.out.println(solution.isAnagram(s,t));
    }
}
