package seqtable.test;

import seqtable.LinkedStack;

import java.util.Scanner;

// 给定一个入栈的序列，输出所有可能的出栈序列
public class StackSequence {

    static int cnt = 0;

    /**
     * @param stack 用来模拟出入栈的栈容器
     * @param s     字符序列
     * @param cur   开始位置
     */
    static void solve(LinkedStack<Character> stack, char[] s, int cur, String prefix) {
        // 如果当前元素是最后一个元素，那么压入栈后，必须弹出来
        if (cur + 1 == s.length) {
            cnt++;
            System.out.print(prefix);
            stack.push(s[cur]);
            while (!stack.isEmpty()) {
                System.out.print(stack.pop());
            }
            System.out.println();
        } else {
            stack.push(s[cur]);
            solve(stack.deepClone(), s, cur + 1, prefix); // 不弹

            // 弹出1，2,...,n次
            StringBuilder stringBuilder = new StringBuilder();
            while (!stack.isEmpty()) {
                stringBuilder.append(stack.pop());
                solve(stack.deepClone(), s, cur + 1, new String(new StringBuilder(prefix).append(stringBuilder)));
            }
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        char[] s = in.next().toCharArray();

        solve(new LinkedStack<Character>(), s, 0, "");
        System.out.println("一共有: " + cnt + "种结果");
        in.close();
    }
}
