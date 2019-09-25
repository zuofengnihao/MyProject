package com.takozy.test_poj2413_greedy;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 已知一条公路上,有一个起点和一个终点,这之间有n个加油站;已知从这n个加油站到终点的距离d与
 * 各个加油站可以加的油量l,起点位置至终点的距离L与起始时刻邮箱中的油量P;假设使用1个单位的
 * 汽油即走1个单位的距离,油箱没有上限,最少加几次油,可以从起点开至终点(无法到达返回-1)
 */
public class FillGas {

    /**
     * 我的思路:使用优先级队列(加油站油量 大->小)
     * 遍历加油站 计算出开到此加油站剩余油量(sur 即 初始油量p-(总距离l-剩余距离station[0]))
     * 如果到此油站无需加油(即剩余油量sur>=0) 将此油站的加油量station[1]入堆 并i++ 查看下个油站
     * 如果到此油站初始油量会不够(即剩余油量sur<0) 则在前面经过的油站中取油站加油量最大值(即 queue.poll)
     * 加入p(即p+=queue.poll) 并count++ 加油次数+1 i不++ 继续停留在此站观察 直到保证过此站有足够的油
     * 如果queue为空了 还不够 说明前面没有油站可以加油 直接返回-1 不可能达到终点
     * 循环如果正常结束 说明现在的油量已经可以经过所有的加油站了 只剩最后一段距离即最后一个油站到终点的距离
     * 如果此时油量p>=总路程l直接返回count 如果不够 循环queue加油并count++ 直到p>=l 或者queue为空
     * 如果queue为空还未达到p>=l 则返回-1表示不可能到达终点
     *
     * @param l 为起点到终点的距离
     * @param p 为起点初始的汽油量
     * @param stations 加油站[加油站到终点的距离,加油站的加油量]
     * @return
     */
    public static int getMinimunStop(int l, int p, int[][] stations) {
        int count = 0;
        PriorityQueue<Integer> queue = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });
        int i = 0;
        while (i < stations.length) {
            int sur = p - (l - stations[i][0]); // 剩余油量
            int plus = stations[i][1];// 当前站可加的油量
            if (sur < 0) {
                if (queue.isEmpty()) return -1;
                p += queue.poll();
                count++;
            } else {
                queue.add(plus);
                i++;
            }
        }
        if (p >= l) return count;
        while (!queue.isEmpty()) {
            p += queue.poll();
            count++;
            if (p >= l) return count;
        }
        return -1;
    }


    public static void main(String[] args) {
        System.out.println(getMinimunStop(25, 16, new int[][]{{15,2},{11,5},{10,3},{4,4}}));
    }
}
