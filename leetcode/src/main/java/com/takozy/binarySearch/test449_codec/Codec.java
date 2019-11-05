package com.takozy.binarySearch.test449_codec;

import com.takozy.binarySearch.TreeNode;

/**
 * 序列化是将数据结构或对象转换为一系列位的过程，以便它可以存储在文件或内存缓冲区中，或通过网络连接链路传输，以便稍后在同一个或另一个计算机环境中重建。
 *
 * 设计一个算法来序列化和反序列化二叉搜索树。 对序列化/反序列化算法的工作方式没有限制。 您只需确保二叉搜索树可以序列化为字符串，并且可以将该字符串反序列化为最初的二叉搜索树。
 *
 * 编码的字符串应尽可能紧凑。
 *
 * 注意：不要使用类成员/全局/静态变量来存储状态。 你的序列化和反序列化算法应该是无状态的
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/serialize-and-deserialize-bst
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Codec {

    public static void main(String[] args) {
        Codec codec = new Codec();
        TreeNode node = codec.deserialize("8#3#1#6#10#15#");
        String serialize = codec.serialize(node);
        System.out.println(serialize);
    }

    /**
     * 安前序遍历root序列化
     * @param root
     * @return
     */
    public String serialize (TreeNode root) {
        if (root == null) return "";
        StringBuilder builder = new StringBuilder();
        write(root, builder);
        return builder.toString();
    }

    /**
     * 反序列化 依次获取数值创建node并插入
     * @param data
     * @return
     */
    public TreeNode deserialize (String data) {
        if (data == null || data.length() < 1) return null;
        String[] split = data.split("#");
        TreeNode root = new TreeNode(Integer.valueOf(split[0]));
        for (int i = 1; i < split.length; i++) {
            TreeNode.insert(root, new TreeNode(Integer.valueOf(split[i])));
        }
        return root;
    }

    public void write (TreeNode node, StringBuilder builder) {
        if (node == null) return;
        builder.append(node.val + "#");
        write(node.left, builder);
        write(node.right, builder);
    }
}
