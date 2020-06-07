package com.uzgf.leetcode;

import java.util.Stack;

/**
 * 给定一个只包含三种字符的字符串：（ ，） 和 *，写一个函数来检验这个字符串是否为有效字符串。有效字符串具有如下规则：
 *
 *	1. 任何左括号 ( 必须有相应的右括号 )。
 *	2. 任何右括号 ) 必须有相应的左括号 ( 。
 *	3. 左括号 ( 必须在对应的右括号之前 )。
 *	4. * 可以被视为单个右括号 ) ，或单个左括号 ( ，或一个空字符串。
 *	5. 一个空字符串也被视为有效字符串。
 *	示例 1:
 *	
 *	输入: "()"
 *	输出: True
 *	示例 2:
 *	
 *	输入: "(*)"
 *	输出: True
 *	示例 3:
 *	
 *	输入: "(*))"
 *	输出: True
 *	注意:
 *	
 *	字符串大小将在 [1，100] 范围内。
 *	
 *	来源：力扣（LeetCode）
 *	链接：https://leetcode-cn.com/problems/valid-parenthesis-string
 *	著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 *	
 *	
 *	解题思想：准备一个左括号栈，一个*栈，每次遇到左括号或者*，将其序号入栈
 *			每次遇到右括号，优先弹出左括号的栈，若左括号栈为空则弹出*栈
 *
 *			最后判断左括号和*栈中元素的序号，如果存在*中元素的序号比左括号栈中的序号小，则返回false，否则返回true
 */

public class Q0678_ValidParenthesisString {

    public boolean checkValidString(String s) {
    	Stack<Integer> leftStack = new Stack<>();
    	Stack<Integer> starStack = new Stack<>();
    	
    	for (int i=0; i<s.length(); i++) {
    		char c = s.charAt(i);
    		
    		if (c == '(') {
    			leftStack.push(i);
    		} else if (c == '*') {
    			starStack.push(i);
    		} else {
    			// c == ')'
    			if (leftStack.isEmpty()) {
    				// 看starStack是否为空
    				if (starStack.isEmpty()) {
    					// startStack为空
    					return false;
    				} else {
    					starStack.pop();
    				}
    			} else {
    				leftStack.pop();
    			}
    		}
    	}
    	
    	while (!leftStack.isEmpty() && !starStack.isEmpty()) {
    		if (leftStack.peek() > starStack.peek()) {
    			// *)
    			return false;
    		} else {
    			// (*
    			leftStack.pop();
    			starStack.pop();
    		}
		}
		
		if (leftStack.isEmpty()) {
			return true;
		} else {
			return false;
		}
    }
    
    public static void main(String[] args) {
    	Q0678_ValidParenthesisString s = new Q0678_ValidParenthesisString();
    	System.out.println(s.checkValidString("()"));
    	System.out.println(s.checkValidString("(*)"));
    	System.out.println(s.checkValidString("(*))"));
    	System.out.println(s.checkValidString(")"));
    	System.out.println(s.checkValidString("(*****(((("));
    	System.out.println(s.checkValidString("(**()))"));
    }
}
