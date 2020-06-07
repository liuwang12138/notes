package com.uzgf.leetcode;

import java.util.HashMap;

/**
 * 	给定两个字符串 s 和 t，它们只包含小写字母。
 *
 *	字符串 t 由字符串 s 随机重排，然后在随机位置添加一个字母。
 *	
 *	请找出在 t 中被添加的字母。
 *	
 *	示例:
 *	
 *	输入：
 *	s = "abcd"
 *	t = "abcde"
 *	
 *	输出：
 *	e
 *	
 *	解释：
 *	'e' 是那个被添加的字母。
 *	
 *	来源：力扣（LeetCode）
 *	链接：https://leetcode-cn.com/problems/find-the-difference
 *	著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 *	思想：
 *		使用HashMap, key是char, value是出现的次数，先统计t，依次增加，再统计s，依次减，最后看哪个的value>0
 *		<p>
 *			亦或
 *		</p>
 *
 */
public class Q0389_FindTheDifference {
    public char findTheDifference(String s, String t) {
    	HashMap<Character, Integer> map = new HashMap<>();

    	for (char c : t.toCharArray()) {
    		Integer k = map.get(c);
    		if (k == null) {
    			map.put(c, 1);
    		} else {
    			map.put(c, k+1);
    		}
    	}
    	
    	for (char c : s.toCharArray()) {
    		Integer k = map.get(c);
    		map.put(c, k-1);
    	}
    	
    	for (Character key : map.keySet()) {
    		if (map.get(key) > 0) {
    			return key;
    		}
    	}

    	return '.';
    }
    
    
    public char findTheDifference2(String s, String t) {
    	char result = 0;

    	for (char c : t.toCharArray()) {
    		result ^= c;
    	}
    	
    	for (char c : s.toCharArray()) {
    		result ^= c;
    	}

    	return result;
    }
    
    public static void main(String[] args) {
    	Q0389_FindTheDifference s = new Q0389_FindTheDifference();
    	System.out.println(s.findTheDifference2("abcd", "abcde"));
    }
}