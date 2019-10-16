package com.takozy.recurrence.test51_recurrence;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * n 皇后问题研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
 *
 * 给定一个整数 n，返回所有不同的 n 皇后问题的解决方案。
 *
 * 每一种解法包含一个明确的 n 皇后问题的棋子放置方案，该方案中 'Q' 和 '.' 分别代表了皇后和空位。
 *
 * 皇后攻击 横线 支线 斜线 米子攻击 并无距离限制
 *
 * 示例:
 *
 * 输入: 4
 * 输出: [
 *  [".Q..",  // 解法 1
 *   "...Q",
 *   "Q...",
 *   "..Q."],
 *
 *  ["..Q.",  // 解法 2
 *   "Q...",
 *   "...Q",
 *   ".Q.."]
 * ]
 * 解释: 4 皇后问题存在两个不同的解法。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/n-queens
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class SolveNQueens {

    /**
     * 循环行
     *
     * Q x x x      Q x x x             Q x x x       Q x x x
     * x x . .  =>  x x Q x             x x x Q       x x x Q
     * x . x .      x x x x  back ==>   x . x x  ==>  x Q x x  结束 无解
     * x . . x      x . x x             x x . x       x x x x
     *
     * x Q x x      x Q x x      x Q x x      x Q x x
     * x x x .  =>  x x x Q      x x x Q      x x x Q
     * . x . x      . x x x  =>  Q x x x      Q x x x  解1 row+1 = n
     * . x . .      . x . x      x x . x  =>  x x Q x
     *
     * x x Q x      x x Q x      x x Q x      x x Q x
     * . x x x  =>  Q x x x      Q x x x      Q x x x
     * x . x .      x x x .  =>  x x x Q      x x x Q  解2 row+1 = n
     * . . x .      x . x .      x . x x  =>  x Q x x
     *
     * x x x Q      x x x Q      x x x Q                   x x x Q
     * . . x x  =>  Q x x x      Q x x x                   x Q x x
     * . x . x      x x . x  =>  x x Q x          back =>  x x x x  结束 无解
     * x . . x      x . x x      x x x x  back =>          x x . x
     *
     * 上面图形为回溯思路
     *
     * 先确定怎样的才能不互相攻击到 即 皇后为中心的米子型格子都会被攻击到
     * 首先可以确定 同row 只能有一个皇后 每次添加一个皇后后 递归行号row 即可 backtrack(int row)
     * 与此皇后同列col不能再放皇后 使用 rows[] 数组 当row,col位置放置皇后
     * rows[col]=1 判断是否同列 只需判断 rows[col] 此列是否为1
     * 最后是皇后的两条对角线 我们发现 row + col 与 row - col 是两个常量 只要坐标 row,col 相加或相减等于
     * 这两个常量中的一个 即在此皇后的对角线上 使用两个 HashSet 保存 这两个常量
     *
     *
     * @param args
     */

    public static void main(String[] args) {
        SolveNQueens nQueens = new SolveNQueens();
        List<List<String>> lists = nQueens.solveNQeens(8);
        for (int i = 0; i < lists.size(); i++) {
            System.out.println("方法" + (i+1) + ":");
            for (int j = 0; j < lists.get(i).size(); j++) {
                System.out.println(lists.get(i).get(j));
            }
            System.out.println();
        }
    }

    public List<List<String>> solveNQeens(int n) {
        List<List<String>> result = new ArrayList<>();
        int[] queens = new int[n];
        int[] rows = new int[n];
        HashSet<Integer> dale = new HashSet<>();
        HashSet<Integer> hill = new HashSet<>();
        backtrack(0, n, result, queens, dale, hill, rows);
        return result;
    }

    /**
     * 递归行号 为每行添加queen
     * @param row
     * @param n
     * @param result
     * @param queens
     * @param dale
     * @param hill
     * @param rows
     */
    public void backtrack(int row, int n, List<List<String>> result, int[] queens, HashSet<Integer> dale, HashSet<Integer> hill, int[] rows) {
        //当row==n 说明已经放置好了n个皇后 将queens添加至结果集
        if (row == n) {
            List<String> item = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                String str = "";
                for (int j = 0; j < n; j++) {
                    if (j == queens[i]) str += "Q";
                    else str += ".";
                }
                item.add(str);
            }
            result.add(item);
            return;
        }

        //循环列号
        for (int i = 0; i < n; i ++) {
            //判断当前行列row,i 是否会被攻击 不会被攻击情况下继续逻辑
            if (isNotUnderAttack(row, i, dale, hill, rows)) {
                //放下皇后的方法
                putDownQueen(row, i, dale, hill, queens, rows);
                //递归调用下一行
                backtrack(row + 1, n, result, queens, dale, hill, rows);
                //移除刚才添加的皇后 让循环继续 查看此行row的下一个列i是否符合不被攻击
                removeQueen(row, i, dale, hill, queens, rows);
            }
        }
    }

    /**
     * 判断是否会被攻击
     * @param row
     * @param col
     * @param dale
     * @param hill
     * @param rows
     * @return
     */
    public boolean isNotUnderAttack(int row, int col, HashSet<Integer> dale, HashSet<Integer> hill, int[] rows) {
        if (rows[col] == 0 && !dale.contains(row - col) && !hill.contains(row + col)) return true;
        return false;
    }

    /**
     * 放下皇后
     * @param row
     * @param col
     * @param dale
     * @param hill
     * @param queens
     * @param rows
     */
    public void putDownQueen(int row, int col, HashSet<Integer> dale, HashSet<Integer> hill, int[] queens, int[] rows) {
        queens[row] = col;
        rows[col] = 1;
        dale.add(row - col);
        hill.add(row + col);
    }

    /**
     * 移除皇后
     * @param row
     * @param col
     * @param dale
     * @param hill
     * @param queens
     * @param rows
     */
    public void removeQueen(int row, int col, HashSet<Integer> dale, HashSet<Integer> hill, int[] queens, int[] rows) {
        queens[row] = 0;
        rows[col] = 0;
        dale.remove(row - col);
        hill.remove(row + col);
    }
}
