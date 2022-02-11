package Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main_BOJ_1991_트리_순회 {
    static class node{
        char data;
        node left;
        node right;

        public node(char data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }
    static class Tree{
        node root;

        public void createNode(char data, char left, char right) {
            if (root == null) {
                root = new node(data);
                root.left = left == '.' ? null : new node(left);
                root.right = right == '.' ? null : new node(right);
            } else {
                searchNode(root, data, left, right);
            }
        }

        private void searchNode(node node, char data, char left, char right) {
            if (node == null) return;
            else if (node.data == data) {
                node.left = left == '.' ? null : new node(left);
                node.right = right == '.' ? null : new node(right);
            } else {
                searchNode(node.left, data, left, right);
                searchNode(node.right, data, left, right);
            }
        }


        public void preOrder(node root) {
            if (root != null) {
                System.out.print(root.data);
                preOrder(root.left);
                preOrder(root.right);
            }
        }

        public void inOrder(node root) {
            if (root != null) {
                inOrder(root.left);
                System.out.print(root.data);
                inOrder(root.right);
            }
        }

        public void postOrder(node root) {
            if (root != null) {
                postOrder(root.left);
                postOrder(root.right);
                System.out.print(root.data);
            }
        }
    }

    static int N;
    static Tree tree;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        tree = new Tree();

        for (int i = 0; i < N; i++) {
            char[] arr = br.readLine().toCharArray();
            tree.createNode(arr[0], arr[2], arr[4]);
        }

        tree.preOrder(tree.root);
        System.out.println();
        tree.inOrder(tree.root);
        System.out.println();
        tree.postOrder(tree.root);
    }
}
