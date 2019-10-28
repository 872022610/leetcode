package question3;

import java.util.HashMap;

//给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
//
// 示例 1:
//
// 输入: "abcabcbb"
//输出: 3
//解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
//
//
// 示例 2:
//
// 输入: "bbbbb"
//输出: 1
//解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
//
//
// 示例 3:
//
// 输入: "pwwkew"
//输出: 3
//解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
//     请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
//
// Related Topics 哈希表 双指针 字符串 Sliding Window

class Solution {
    public static void main(String[] args){
        lengthOfLongestSubstring("p");
    }

    /**
      这里是利用了HashMap的key不能重复的特点，把不重复的字符作为key放入map，直到
      发现遍历到的字符在map中存在，这时取出map的长度，即为本次循环到的最大长度map.size()
      同时将这个长度与maxLength的大小做比较，如果maxLength的值大于map.size(),则表示
      存在长度大于本次循环的字符串，不做任何赋值操作，继续下一个节点的循环。反之则把map.size()
      赋值给maxLength，继续下一个节点的循环。该题的难点在于怎么找出不重复字符的字符串。
     */
    public static int lengthOfLongestSubstring(String s) {
        //先将字符串转成字符数组
        char[] strArr = s.toCharArray();
        HashMap map = new HashMap(strArr.length);
        //maxLength永远存储当前已经找到的最大不重复字符串长度
        int maxLength = 0;
        /*
        双层循环，外层从index开始遍历字符数组，内层从index+1开始遍历，
        外层循环一次就代表完成了一次以当前index为第一个字符的最大不重复字符串查找
        */
        for( int i = 0 ; i < strArr.length; i++) {
            map.put(String.valueOf(strArr[i]), new Object());
            //从i+1开始遍历，一直找到重复的字符为止
            for( int j = i+1 ; j < strArr.length; j++) {
                if (map.get(String.valueOf(strArr[j])) == null) {
                    map.put(String.valueOf(strArr[j]), new Object());
                } else {
                    break;
                }
            }
            //假如本次找到不重复字符串长度大于之前已经找到的最大不重复字符串长度，则将本次找到的长度赋值给maxLength
            if (map.size() > maxLength) {
                maxLength = map.size();
            }
            //清空map开始下一次循环
            map.clear();
        }
        //返回已经找到的所有不重复字符串的最大长度
        return maxLength;
    }
}