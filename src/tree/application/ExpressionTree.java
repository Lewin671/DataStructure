package tree.application;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;

// 这里主要实现这几个功能:
// 中缀表达式 => 后缀表达式
// 直接计算后缀表达式的值
// 通过后缀表达式构建一个表达式树
// 计算表达式树的值（通过后根遍历）
public class ExpressionTree {
    static void inOrderTraversal(ExpNode root) {
        if (root == null) return;

        boolean flag = false;
        if (root.type == ExpNodeType.opNode && (root.op == '+' || root.op == '-')) {
            flag = true;
        }
        if (flag) {
            System.out.print("(");
        }
        inOrderTraversal(root.left);
        System.out.print(root);
        inOrderTraversal(root.right);
        if (flag) {
            System.out.print(")");
        }
    }

    public static void main(String[] args) throws Exception {
        String midExp;
        Scanner in = new Scanner(System.in);
        midExp = in.nextLine();

        ExpressionTree expressionTree = new ExpressionTree();

        String exp = expressionTree.middleExpToPostExp(midExp);
        // 中缀转后缀
        System.out.println(exp);

        // 计算后缀表达式测试
        System.out.println("直接计算后缀表达式的值: " + expressionTree.calculate(exp));

        // 构造表达式树
        ExpNode root = expressionTree.constructExpTree(exp);
        ExpressionTree.inOrderTraversal(root);
        System.out.println();

        System.out.println("通过构造的表达式树来求值: " + expressionTree.calculateExpTree(root));
        in.close();
    }

    boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/';
    }

    int priority(String c) {
        if (c.equals("+") || c.equals("-")) {
            return 1;
        } else { // * /
            return 2;
        }
    }

    /*
    遇到数字， 直接输出
    遇到运算符
    a.若为“(” 直接入栈
    b.若为“)” 将符号栈中的元素依次出栈并输出, 直到 “(“, “(“只出栈, 不输出
    c.若为其他符号, 将符号栈中的元素依次出栈并输出, 直到遇到比当前符号优先级更低(相等优先级的符号也要弹出)的符号或者”(“。 将当前符号入栈。
    扫描完后, 将栈中剩余符号依次输出
     */
    // 中缀表达式转后缀表达式
    // 测试数据: 2 * (9 + 6 / 3 - 5) + 4
    String middleExpToPostExp(String midExp) {
        midExp = midExp.replaceAll("\\s", "");
        LinkedList<String> res = new LinkedList<>();
        Deque<String> stack = new ArrayDeque<>();

        for (int i = 0; i < midExp.length(); i++) {
            char c = midExp.charAt(i);

            if (c == '(') {
                stack.push("(");
            } else if (c == ')') {
                while (true) {
                    String s = stack.pop();
                    if (s.equals("(")) {
                        break;
                    }
                    res.add(s);
                }
            } else if (isOperator(c)) {
                while (!(stack.isEmpty() || stack.peek().equals("("))
                        // 栈中的优先级高，那么就要先出来，先运算
                        && priority(stack.peek()) >= priority(c + "")) {
                    res.add(stack.pop());
                }
                stack.push(c + "");
            } else { // number
                int j = i + 1;
                for (; j < midExp.length(); j++) {
                    char tmpC = midExp.charAt(j);
                    if (isOperator(tmpC) || tmpC == '(' || tmpC == ')') {
                        break;
                    }
                }
                res.add(midExp.substring(i, j));
                i = j - 1;
            }
        }

        while (!stack.isEmpty()) {
            res.add(stack.pop());
        }

        return res.toString().replaceAll("[,\\[\\]]", "");
    }

    // 测试数据: 3 4 + 5 * 6 -
    double calculate(String exp) throws Exception {
        double res;
        int i = 0;
        Deque<Double> stack = new ArrayDeque<>();
        while (i < exp.length()) {
            // 获取下一个元素，或者是数字，或者是操作符
            char c = exp.charAt(i);
            if (c == ' ') {
                i = i + 1;
                continue;
            }
            int l = exp.indexOf(' ', i);
            if (l == -1) {
                l = exp.length();
            }
            if (c < '0' || c > '9') {
                double right = stack.pop();
                double left = stack.pop();
                if (c == '+') {
                    stack.push(left + right);
                } else if (c == '-') {
                    stack.push(left - right);
                } else if (c == '*') {
                    stack.push(left * right);
                } else if (c == '/') {
                    if (right == 0) {
                        throw new Exception("divide zero");
                    }
                    stack.push(left / right);
                } else { // 不合法字符
                    throw new Exception("unexpected sign");
                }
            } else {
                double num = Double.parseDouble(exp.substring(i, l));
                stack.push(num);
            }
            i = l + 1;
        }
        res = stack.pop();
        return res;
    }

    // 通过后缀表达式构建表达式树
    ExpNode constructExpTree(String exp) {
        ExpNode root;
        int i = 0;
        Deque<ExpNode> stack = new ArrayDeque<>();
        while (i < exp.length()) {
            char c = exp.charAt(i);
            if (c == ' ') {
                i++;
                continue;
            }
            int l = exp.indexOf(' ', i);
            if (l == -1) {
                l = exp.length();
            }
            // 如果是操作数结点
            if (c == '+' || c == '-'
                    || c == '*' || c == '/') {
                ExpNode opNode = new ExpNode(c);
                opNode.right = stack.pop();
                opNode.left = stack.pop();
                stack.push(opNode);
            } else {
                double num = Double.parseDouble(exp.substring(i, l));
                stack.push(new ExpNode(num));
            }
            i = l + 1;
        }
        root = stack.pop();
        return root;
    }

    // 计算表达式树的值
    double calculateExpTree(ExpNode root) throws Exception {
        class Pair<K, V> {
            final K first;
            final V second;


            Pair(K first, V second) {
                this.first = first;
                this.second = second;
            }
        }

        double res;
        Deque<Pair<ExpNode, Integer>> stack = new ArrayDeque<>();
        Deque<Double> calculator = new ArrayDeque<>();

        // 初始化栈
        stack.push(new Pair<>(root, 0));

        // 迭代
        while (!stack.isEmpty()) {
            Pair<ExpNode, Integer> cur = stack.pop();
            switch (cur.second) {
                case 0:
                    stack.push(new Pair<>(cur.first, 1));
                    if (cur.first.left != null)
                        stack.push(new Pair<>(cur.first.left, 0));
                    break;
                case 1:
                    stack.push(new Pair<>(cur.first, 2));
                    if (cur.first.right != null)
                        stack.push(new Pair<>(cur.first.right, 0));
                    break;
                default:
                    if (cur.first.type == ExpNodeType.dataNode) {
                        calculator.push(cur.first.data);
                    } else {
                        double right = calculator.pop();
                        double left = calculator.pop();
                        switch (cur.first.op) {
                            case '+':
                                calculator.push(left + right);
                                break;
                            case '-':
                                calculator.push(left - right);
                                break;
                            case '*':
                                calculator.push(left * right);
                                break;
                            case '/':
                                if (right == 0) throw new Exception("divide zero");
                                calculator.push(left / right);
                                break;
                            default:
                                throw new Exception("unexpected operation");
                        }
                    }
            }
        }
        res = calculator.pop();
        return res;
    }

    // 树的结点类型，叶子结点的数据域存储数据，分支节点的数据域存储操作符
    enum ExpNodeType {
        opNode, dataNode
    }

    static class ExpNode {
        ExpNodeType type;
        double data;
        char op;
        ExpNode left, right;

        ExpNode(char op) {
            this.op = op;
            this.type = ExpNodeType.opNode;
        }

        ExpNode(double data) {
            this.data = data;
            this.type = ExpNodeType.dataNode;
        }

        @Override
        public String toString() {
            switch (type) {
                case opNode:
                    return String.valueOf(op);
                case dataNode:
                    return Double.toString(data);
            }
            return null;
        }
    }
}
