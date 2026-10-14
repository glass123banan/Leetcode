/*
    STEPS:
    1. Find amount of digits
    2. 
*/
#include <cmath>
#include <numeric>
#include <functional>
#include <iostream>

using namespace std;

class Solution {
public:
    bool isPalindrome(int x) {
        if (x < 0){
            return false;
        }
        if(x == 0){
            return true;
        }
        int digits = int(log10(x)) + 1; // amount of digits in x
        int mid = floor(digits/2);
        // cout << "Mid: " << mid << "\n";

        for(int i = 0; i < mid; i++){
            int first = int(x/pow(10,i)) % 10;
            int last = int(x/pow(10, digits-1-i)) % 10;

            // cout << "First: " << first << "\n";
            // cout << "Last: " << last << "\n";

            if(first == last){
                continue;
            }
            else {
                return false;
            }
        }
        return true;
    }
};

int main() {
    Solution s;

    int input;
    cin >> input;
    // input = 100000001;
    cout << s.isPalindrome(input) << "\n";

    return 0;
}
