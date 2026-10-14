package group_anagrams;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        HashMap<String, List<String>> anagramMap = new HashMap<>();
        List<List<String>> result = new ArrayList<>();

        for(int i = 0; i < strs.length; i++){
            String currSorted = sortLetters(strs[i]);
            if(!anagramMap.keySet().contains(currSorted)){
                anagramMap.put(currSorted, new ArrayList<String>());
            }
            List<String> value = anagramMap.get(currSorted);
            value.add(strs[i]);
        }
        for(List<String> lst : anagramMap.values()){
            result.add(lst);
        }
        return result;
    }

    private String sortLetters(String str){
        char[] chars = str.toCharArray();
        Arrays.sort(chars);
        String sorted = new String(chars);
        return sorted;
    }
}
