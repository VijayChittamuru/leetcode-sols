from typing import List
class Solution:
    def __init__(self):
        pass

    def twoSum(self, nums, target):
        left = 0
        right = len(nums) - 1
        dMap = {}
        for i in range(0, len(nums)):
            dMap[nums[i]] = i
        print(dMap)
        for i in range (0, len(nums)):
            check = target - nums[i]
            val = dMap.get(check, -1)
            if val != i and val != -1:
                return[i, val]
    
    def isValid(self, s: str) -> bool:
        stack = []
        for i in s:
            if i == '(' or i == '[' or i == '{':
                stack.append(i)
            else:
                if not stack:
                    return False
                c = stack.pop()
                if(c == '(' and i != ')' or
                   c == '[' and i != ']' or
                   c == '{' and i != '}'):
                    return False

        return not stack


    def generateParenthesis(self, n: int) -> List[str]:
        
        return [""]

sol = Solution()
print(sol.twoSum([3, 2, 4], 6))