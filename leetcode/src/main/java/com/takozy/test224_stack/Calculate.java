package com.takozy.test224_stack;

import java.util.Stack;

/**
 * 实现一个基本的计算器来计算一个简单的字符串表达式的值。
 *
 * 字符串表达式可以包含左括号 ( ，右括号 )，加号 + ，减号 -，非负整数和空格  。
 *
 * 示例 1:
 *
 * 输入: "1 + 1"
 * 输出: 2
 * 示例 2:
 *
 * 输入: " 2-1 + 2 "
 * 输出: 3
 * 示例 3:
 *
 * 输入: "(1+(4+5+2)-3)+(6+8)"
 * 输出: 23
 * 说明：
 *
 * 你可以假设所给定的表达式都是有效的。
 * 请不要使用内置的库函数 eval。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/basic-calculator
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Calculate {

    public static void main(String[] args) {
        int calculate = calculate3("7658766");
        System.out.println(calculate);
    }

    /**
     * 自己的思路 利用两个栈(数据 计算) 数字 左括号 运算符号 直接入数据栈
     * 碰到 右括号时 连续出栈并将出栈的元素压入计算栈 直到碰到栈内的左括号
     * 再将计算栈的全部弹出(正序过程)进行计算后 将结果压入 数据栈
     * 循环结束后 再将数据栈的依次弹出压出计算栈后再依次弹出 计算出最终结果
     * @param s
     * @return
     */
    public static int calculate(String s) {
        Stack<String> dataStack = new Stack<String>();
        Stack<String> numStack = new Stack<String>();
        char[] chars = s.toCharArray();
        String num = "";
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == ')') {
                if (!"".equals(num)) {
                    dataStack.push(num);
                    num = "";
                }
                while (!"(".equals(dataStack.peek())) {
                    numStack.push(dataStack.pop());
                }
                dataStack.pop();
                int sum = 0;
                while (!numStack.empty()) {
                    String pop = numStack.pop();
                    if ("+".equals(pop)) {
                        sum += Integer.valueOf(numStack.pop());
                    } else if ("-".equals(pop)) {
                        sum -= Integer.valueOf(numStack.pop());
                    } else {
                        sum += Integer.valueOf(pop);
                    }
                }
                dataStack.push(sum + "");
            } else if(chars[i] >= '0' && chars[i] <= '9') {
                num += chars[i];
            } else if (chars[i] != ' ') {
                if (!"".equals(num)) {
                    dataStack.push(num);
                    num = "";
                }
                dataStack.push(chars[i] + "");
            }
        }
        if (!"".equals(num)) {
            dataStack.push(num);
            num = "";
        }
        while (!dataStack.empty()) {
            numStack.push(dataStack.pop());
        }
        int result = 0;
        while (!numStack.empty()) {
            String flag = numStack.pop();
            if ("+".equals(flag)) {
                result += Integer.valueOf(numStack.pop());
            } else if ("-".equals(flag)) {
                result -= Integer.valueOf(numStack.pop());
            } else {
                result += Integer.valueOf(flag);
            }
        }
        return result;
    }

    /**
     *利用两个栈 符号栈 数字栈
     *
     * 12 + ( 7 - 3 ) -> 12入数字栈
     * + ( 7 - 3 ) -> +入符号栈(可以计算)
     * ( 7 - 3 ) -> ( 左括号(无法计算)
     * 7 - 3 ) -> 7压入数字栈
     * - 3 ) -> -压入符号栈(可以计算)
     * 3 ) -> 3压入数字栈 此时可以计算 弹出3 7 - 计算7-3 结果4入数字栈
     * ) -> ) 右括号(可以计算) 弹出 4 12 + 计算12+4 结果16入栈
     * 循环结束 返回 数字栈poll值...
     * @param s
     * @return
     */
    public static int calculate1(String s) {
        Stack<Integer> numStack = new Stack<Integer>();
        Stack<Character> actionStack = new Stack<Character>();
        char[] chars = s.toCharArray();

        String num = "";
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] >= '0' && chars[i] <= '9') {
                num += chars[i];
            } else {
                if (!"".equals(num)) {
                    numStack.push(Integer.valueOf(num));
                    num = "";
                    cal(numStack, actionStack);
                }

                if (chars[i] == '+' || chars[i] == '-' || chars[i] == '(') {
                    actionStack.push(chars[i]);
                } else if (chars[i] == ')' && actionStack.peek() == '(') {
                    actionStack.pop();
                    cal(numStack, actionStack);
                }
            }
        }

        if (!"".equals(num)) {
            numStack.push(Integer.valueOf(num));
            num = "";
            cal(numStack, actionStack);
        }

        return numStack.pop();
    }

    public static void cal(Stack<Integer> numStack, Stack<Character> actionStack) {
        if (numStack.size() > 1 && actionStack.size() > 0) {
            Character action = actionStack.peek();
            if (action != '(') {
                int result, factor = numStack.pop(); actionStack.pop();
                result = action == '+' ? numStack.pop() + factor : numStack.pop() - factor;
                numStack.push(result);
            }
        }
    }

    /**
     * 整体思路同上 字符串处理使用“有限状态自动机”思想处理
     * @param s
     * @return
     */

    public final static int BEGIN = 0; //开始状态
    public final static int NUMBER = 1; //数字状态
    public final static int OPERATION = 2; //操作状态

    public static int calculate2(String s) {
        boolean flag = false;
        Stack<Integer> numberStack = new Stack<>();
        Stack<Character> operationStack = new Stack<>();
        char[] chars = s.toCharArray();
        int number = 0;
        int status = BEGIN;
        for (int i = 0; i < s.length(); i++) {
            if (chars[i] == ' ') continue;
            switch (status) {
                case BEGIN: //开始状态
                    if (chars[i] >= '0' && chars[i] <= '9') {
                        status = NUMBER; //为数字 进入NUMBER状态 i退格
                    } else {
                        status = OPERATION; //不为数字(实际上不为数字只能为'(') 进入OPERATION状态
                    }
                    i--;//i 退格
                    break;
                case NUMBER: //数字状态
                    if (chars[i] >= '0' && chars[i] <= '9') {
                        number = number * 10 + chars[i] - '0'; //为数字时处理数字字符串
                    } else {
                        //不为数字时切换状态到 操作状态并尝试计算
                        numberStack.push(number); // 将数字压入栈
                        number = 0; // 归零number
                        status = OPERATION; //切换状态
                        i--; //i退格

                        //计算
                        if (flag) {
                            compute(numberStack, operationStack);
                        }
                    }
                    break;
                case OPERATION: //符号状态
                    if (chars[i] == '+' || chars[i] == '-') {
                        operationStack.push(chars[i]); //为符号时压入符号栈
                        flag = true; // 并将 可计算flag标为true
                    } else if (chars[i] == '(') {
                        flag = false; // 遇到左括号'('时 可计算flag标为false
                    } else if (chars[i] >= '0' && chars[i] <= '9') {
                        //为数字时切换状态
                        status = NUMBER;
                        i--;
                    } else if (chars[i] == ')') { //如果为')'计算
                        //计算
                        compute(numberStack, operationStack);
                    }
                    break;
            }
        }

        if (number != 0) {
            numberStack.push(number);
            compute(numberStack, operationStack);
        }

        if (number == 0 && numberStack.empty()) return 0;

        return numberStack.pop();
    }

    public static void compute(Stack<Integer> numberStack, Stack<Character> operationStack) {
        if (!operationStack.empty() && numberStack.size() > 1) {
            Character pop = operationStack.pop();
            int num = numberStack.pop();
            num = pop == '+' ? numberStack.pop() + num : numberStack.pop() - num;
            numberStack.push(num);
        }
    }

    /**
     * 碰到（递归的思路
     * @param s
     * @return
     */
    public static int calculate3(String s) {
        return doCul(s, 0)[0];
    }

    public static int[] doCul(String s, int index) {
        char[] chars = s.toCharArray();
        int result = 0;
        char operation= '?';
        int number = 0;
        for (int i = index; i < s.length(); i++) {
            if (chars[i] >= '0' && chars[i] <= '9') {
                number = number * 10 + chars[i] - '0';
            } else if (chars[i] == '+' || chars[i] == '-') {
                if (operation != '?') {
                    result = operation == '-' ? result - number : result + number;
                } else {
                    result += number;
                }
                number = 0;
                operation = chars[i];
            } else if (chars[i] == ')') {
                if (operation != '?') {
                    result = operation == '-' ? result - number : result + number;
                } else {
                    result = number;
                }
                return new int[]{result, i};
            } else if (chars[i] == '(') {
                int[] ints = doCul(s, i + 1);
                if (operation != '?') {
                    result = operation == '-' ? result - ints[0] : result + ints[0];
                } else {
                    result = ints[0];
                }
                i = ints[1];
            }
        }

        if (number != 0) {
            result = operation == '-' ? result - number : result + number;
        }

        if (number == 0 && operation == '?') return new int[]{0,0};

        return new int[]{result, s.length() - 1};
    }

    public int[] calculate(String s, int i){
        int result = 0;
        int symbol = 1;
        int tempResult = 0;
        for(;i<s.length();){
            char c = s.charAt(i);
            i++;
            if(c=='('){
                int[] rst = calculate(s, i);
                result = result + symbol * rst[0];
                i = rst[1];
            } else if(c==')'){
                return new int[]{result + symbol * tempResult, i};
            } else if(c>='0' && c<='9'){
                if(symbol != 0){
                    tempResult = tempResult * 10 + c -'0';
                } else {
                    result = result * 10 + c - '0';
                }
            } else if(c == ' '){
            } else{
                result += symbol * tempResult;
                tempResult = 0;
                symbol = c == '+'? 1 : -1;
            }
        }
        return new int[]{result + symbol * tempResult, i};
    }
}
