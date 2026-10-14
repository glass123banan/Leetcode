# 347. Top K Frequent Elements
Given an integer array `nums` and an integer `k`, return the `k` most <i>frequent elements</i>. You may return the answer in <b>any order</b>.

## Example 1:
Input: nums = [1,1,1,2,2,3], k = 2
Output: [1,2]

## Example 2: 
Input: nums = [1], k = 1
Output: [1]

## Example 3:
Input: nums = [1,2,1,2,1,2,3,1,3,2], k = 2
Output: [1,2]

## Constraints:
- `1 <= nums.length <= 10^5`
- `-10^4 <= nums[i] <= 10^4`
- `k` is in the range `[1, the number of unique elements in the array]`.
- It is <b>guaranteed</b> that the answer is <b>unique</b>.

<b>Follow up</b>: Your algorithm's time complexity must be better than `O(n log n)`, where n is the array's size.




