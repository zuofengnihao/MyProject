package com.takozy.test452_greedy;

import java.util.ArrayList;

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
        int[][] ints = new int[][]{{10,16},{2,8},{1,6},{7,12}};
        System.out.println(findMinArrowShots(ints));
    }

    public static int findMinArrowShots(int[][] points) {
        int count = 0;
        ArrayList<int[]> list = new ArrayList<>();
        for (int i = 0; i < points.length; i++) {
            int[] ball = points[i];
            if (list.size() == 0) {
                list.add(ball);
                count++;
            } else {
                boolean flag = false;
                for(int j = 0; j < list.size(); j++) {
                    int[] domain = list.get(j);
                    if ((ball[0] >= domain[0] && ball[0] <= domain[1]) ||
                            ball[1] >= domain[0] && ball[1] <= domain[1]) {
                        domain[0] = Math.max(domain[0], ball[0]);
                        domain[1] = Math.min(domain[1], ball[1]);
                        flag = true;
                        break;
                    }
                }
                if (!flag) {
                    list.add(ball);
                    count++;
                }
            }
        }
        return count;
    }
}
