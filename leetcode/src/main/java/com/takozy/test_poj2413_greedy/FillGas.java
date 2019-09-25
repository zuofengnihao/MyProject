package com.takozy.test_poj2413_greedy;

/**
 * 已知一条公路上,有一个起点和一个终点,这之间有n个加油站;已知从这n个加油站到终点的距离d与
 * 各个加油站可以加的油量l,起点位置至终点的距离L与起始时刻邮箱中的油量P;假设使用1个单位的
 * 汽油即走1个单位的距离,油箱没有上限,最少加几次油,可以从起点开至终点(无法到达返回-1)
 */
public class FillGas {

    /**
     *
     * @param l
     * @param p
     * @param stations
     * @return
     */
    //l为起点到终点的距离,p为起点初始的汽油量,stations.length加油站数量[加油站到终点的距离,加油站的加油量]
    public static int getMinimunStop(int l, int p, int[][] stations) {
        if (l <= p) return 0;
        int lastIndex = 0;
        for (int i = 0; i < stations.length; i++) {
            if (stations[i][0] >= l - p) {
                lastIndex = lastIndex == -1 ? stations[i][1] : Math.max(stations[lastIndex][1], stations[i][1]);
            } else {
                break;
            }
        }
        if (lastIndex == -1) return -1;

        return 0;
    }

    public static void main(String[] args) {
        getMinimunStop(25, 16, new int[][]{{15,2},{11,5},{10,3},{4,4}});
    }
}
