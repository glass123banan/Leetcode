package valid_parentheses;

import java.util.Stack;

public class Solution {
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        char[] strArray = s.toCharArray();

        for(char c : strArray){
            if(c == '('||c == '['||c == '{') stack.push(c);
            else {
                if(stack.empty()) return false;

                char top = stack.pop();

                if(c == ')' && top != '(') return false;
                
                if(c == ']' && top != '[') return false;
                
                if(c == '}' && top != '{') return false;
            }
        }
        return stack.empty();
    }
}
