#include <vector>
#include <unordered_set> // this is a hashset
#include <iostream>

using namespace std;

class Solution {
public:
    bool containsDuplicate(vector<int>& nums) {
        unordered_set<int> uniqueNums; // skapa set
        int n = nums.size();

        for(int i = 0; i < n; i++){
            if(uniqueNums.find(nums[i]) != uniqueNums.end()){
                return true;
            }
            else {
                uniqueNums.insert(nums[i]);
            }
        }
        return false;
    }
};

// Testing purposes only
int main(){
    Solution s;
    vector<int> v = {1, 2, 3};

    cout << s.containsDuplicate(v) << "\n";

    return 0;
}