package b000_algorithms.a11_BInaryTree;

import java.util.LinkedList;
import java.util.List;

/**
 * head - left - right
 *
 * @author Chenyu Liu
 * @since 3/5/25 Wednesday
 **/

public class Solution {

    void preorderTraversal(TreeNode root, List<Integer> list) {
        if (root == null) {
            return;
        }
        list.add(root.val);
        preorderTraversal(root.left, list);
        preorderTraversal(root.right, list);
    }

    public List<Integer> preorderTraversal(TreeNode root) {
        LinkedList<Integer> list = new LinkedList<>();
        preorderTraversal(root, list);
        return list;
    }

    void inorderTraversal(TreeNode root, List<Integer> list) {
        if (root == null) {
            return;
        }
        inorderTraversal(root.left, list);
        list.add(root.val);
        inorderTraversal(root.right, list);
    }

    public List<Integer> inorderTraversal(TreeNode root) {
        LinkedList<Integer> list = new LinkedList<>();
        inorderTraversal(root, list);
        return list;
    }

    void postorderTraversal(TreeNode root, List<Integer> list) {
        if (root == null) {
            return;
        }
        postorderTraversal(root.left, list);
        postorderTraversal(root.right, list);
        list.add(root.val);
    }

    public List<Integer> postorderTraversal(TreeNode root) {
        LinkedList<Integer> list = new LinkedList<>();
        postorderTraversal(root, list);
        return list;
    }


    public void preorderWithLevel(TreeNode root, int level, List<List<Integer>> levelList) {
        if (root == null) {
            return;
        }
        if(levelList.size() == level){
            LinkedList<Integer> list = new LinkedList<>();
            list.add(root.val);
            levelList.add(list);
        }else{
            levelList.get(level).add(root.val);
        }
        preorderWithLevel(root.left, level + 1, levelList);
        preorderWithLevel(root.right, level + 1, levelList);
    }

    public List<List<Integer>> levelOrder(TreeNode root) {
        int level = 0;
        System.out.println(level);
        List<List<Integer>> levelList = new LinkedList<>();
        preorderWithLevel(root, level, levelList);
        return levelList;
    }
}
