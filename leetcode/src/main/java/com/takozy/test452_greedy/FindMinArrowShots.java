package com.takozy.test452_greedy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

/**
 * 在二维空间中有许多球形的气球。对于每个气球，提供的输入是水平方向上，气球直径的开始和结束坐标。由于它是水平的，所以y坐标并不重要，因此只要知道开始和结束的x坐标就足够了。开始坐标总是小于结束坐标。平面内最多存在104个气球。
 *
 * 一支弓箭可以沿着x轴从不同点完全垂直地射出。在坐标x处射出一支箭，若有一个气球的直径的开始和结束坐标为 xstart，xend， 且满足  xstart ≤ x ≤ xend，则该气球会被引爆。可以射出的弓箭的数量没有限制。 弓箭一旦被射出之后，可以无限地前进。我们想找到使得所有气球全部被引爆，所需的弓箭的最小数量。
 *
 * Example:
 *
 * 输入:
 * [[10,16], [2,8], [1,6], [7,12]]
 *
 * 输出:
 * 2
 *
 * 解释:
 * 对于该样例，我们可以在x = 6（射爆[2,8],[1,6]两个气球）和 x = 11（射爆另外两个气球）。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/minimum-number-of-arrows-to-burst-balloons
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class FindMinArrowShots {

    public static void main(String[] args) {
        int[][] ints = new int[][]{{3,9},{7,12},{3,8},{6,8},{9,10},{2,9},{0,9},{3,9},{0,6},{2,8}};
        System.out.println(findMinArrowShots2(ints));
    }

    /**
     * 自己的思路: 使用区间集合 domains<ArrayList>
     * 先排序points 只看开始端(即point[0])的大小排序(小 -> 大)
     * 循环points 如果domains集合大小为0 表示还没有一个弓箭设计区间
     * 直接将这个points区间入domains集合
     * 如果domains不为0 表示已经存在弓箭射击区间
     * 循环domains 用当前points区间与集合中每个弓箭射击区间对比
     * 如果points区间与当前domain相交(即point[0] <= domain[1])
     * 如果相交 则domain取他们的交点集 并跳出内层循环
     * (即domain[0] = Math.max(domain[0], point[0]);domain[1] = Math.min(domain[1], point[1]);)
     * 如果不相交继续内层循环与下个弓箭射击区间对比
     * 一直到所以射击区间对比结束 如果都没有出现相交 则将次point区间入弓箭射击区间集合
     * 表示需要一只新的弓箭了
     * 两层循环结束 所有point都对比过后 弓箭射击区间集合的大小就是需要用到的弓箭数量 返回集合size即可
     * @param points
     * @return
     */
    public static int findMinArrowShots(int[][] points) {
        Arrays.sort(points, (int[] o1, int[] o2) -> o1[0] - o2[0]);
        ArrayList<int[]> domains = new ArrayList<>();
        for (int i = 0; i < points.length; i++) {
            int[] point = points[i];
            if (domains.size() == 0) {
                domains.add(points[i]);
            } else {
                boolean flag = false;
                for (int j = 0; j < domains.size(); j++) {
                    int[] domain = domains.get(j);
                    if (domain[0] > point[1] || domain[1] < point[0]) {
                        continue;
                    } else {
                        domain[0] = Math.max(domain[0], point[0]);
                        domain[1] = Math.min(domain[1], point[1]);
                        flag = true;
                        break;
                    }
                }
                if (!flag) {
                    domains.add(point);
                }
            }
        }
        return domains.size();
    }

    /**
     * 官方思路: 思路与我的类似 但不记录射击区间 排序数组
     * point[0]升序排序(Xstart小的在前) 记录第一个point的Xend(flag)
     * 并让箭数=1 从第二个point开始循环 如果该point Xstart大于flag
     * 表示需要另外一支箭count++ 并将记录(flag)修改为此point的Xend
     * 如果小于等于则不需要另外一支箭 但是需要取这两个Xend中的最小值
     * 循环结束返回count
     *
     * 此方法排序保证后续的point只会出现在前面point的前半段或者之后
     * 如下图
     *
     * ===
     * ==
     * ====
     *       =====
     *
     * @param points
     * @return
     */
    public static int findMinArrowShots1(int[][] points) {
        if (points.length == 0) return 0;
        Arrays.sort(points, (int[] o1, int[] o2) -> o1[0] - o2[0]);
        int count = 1;
        int flag = points[0][1];
        for (int i = 1; i < points.length; i++) {
            if (points[i][0] > flag) {
                flag = points[i][1];
                count++;
            } else {
                flag = Math.min(flag, points[i][1]);
            }
        }
        return count;
    }

    /**
     * 官方思路2:
     * 按照Xend升序排序(Xend最小的在前) 同样记录第一个point的flag=Xend count=1
     * 从第二个point循环points 如果当前point的Xstart大于flag 则表示需要另外一支箭
     * 修改flag=当前point的Xend 并让count++ 如果小于等于不操作
     * (类比上个方法省去的部分 此方法效率更高)
     * 循环结束返回count
     *
     * 此方法的排序方式保证了后面的point只会出现在前面point的后半段或者之后
     * 如下图
     *
     *  ====
     *    ==
     * =====
     *  ======
     *        ====
     *
     * @param points
     * @return
     */
    public static int findMinArrowShots2(int[][] points) {
        if (points.length == 0) return 0;
        Arrays.sort(points, (int[] o1, int[] o2) -> o1[1] - o2[1]);
        int count = 1;
        int flag = points[0][1];
        for (int i = 1; i < points.length; i++) {
            if (points[i][0] > flag) {
                flag = points[i][1];
                count++;
            }
        }
        return count;
    }

    /**
     * leetcode最优算法
     * 将上面方法不记录flag的值 只记录对应的point的下标
     * 不使用lambda表达式
     * @param points
     * @return
     */
    public static int findMinArrowShots3(int[][] points) {
        if (points.length == 0) return 0;
        Arrays.sort(points, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1] - o2[1];
            }
        });
        int count = 1;
        int index = 0;
        for (int i = 1; i < points.length; i++) {
            if (points[i][0] > points[index][1]) {
                count++;
                index = i;
            }
        }
        return count;
    }
}
