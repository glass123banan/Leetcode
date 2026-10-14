#include <vector>
#include <numeric>
using namespace std;

class Solution {
public:
    // function that returns bool to see if subsetsum can be partitioned into equal
    bool canPartition(vector<int>& nums) {
        // sums up all the numbers in input vector
        int sum = accumulate(nums.begin(), nums.end(), 0);

        // if the sum is odd, it can't be partitioned into two equal sums
        if(sum % 2 != 0) return false;

        // create 2d vector of dimensions nums.size() x sum+1 with only -1 vals
        vector<vector<int>> memo(nums.size(), vector<int>(sum+1, -1));

        // call to help function 
        return isSubsetSum(nums.size(), nums, sum/2, memo);

    }

    // bool function to return true/false if an array arr on element nr n can have a partition sum
    bool isSubsetSum(int n, vector<int>& arr, int sum, vector<vector<int>> &memo){
        // if sum = 0, true -> always possible to partition into two sums of 0
        if(sum == 0){
            return true;
        }
        // if length of arr is 0, not possible 
        if(n == 0){
            return false;
        }
        // if alr exist in memoization, return directly instead of recursive call
        if(memo[n-1][sum]!= -1) return memo[n-1][sum];
        // if element is greater than sum, ignore it -> recursive call on lower n
        if(arr[n-1] > sum)
            return isSubsetSum(n-1, arr, sum, memo);
        
        // recursive call -> can sum be obtained by:
        //      a) incl. curr elem
        //      b) excl. curr elem
        return memo[n-1][sum] = 
            isSubsetSum(n-1, arr, sum, memo) || 
            isSubsetSum(n-1, arr, sum-arr[n-1], memo);
    }
};