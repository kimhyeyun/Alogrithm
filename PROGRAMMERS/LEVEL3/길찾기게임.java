import java.util.ArrayList;
import java.util.Collections;

public class 길찾기게임{
    
    public static class node implements Comparable<node>{
        int x;
        int y;
        int data;
        node left;
        node right;

        node(int x, int y, int data){
            this.x = x;
            this.y = y;
            this.data = data;
        }

        @Override
        public int compareTo(node o) {
            return o.y - this.y;
        }
    }

    static ArrayList<node> nodeList;
    static int[][] answer;
    static int idx;

    public static int[][] solution(int[][] nodeinfo) {
        answer = new int[2][nodeinfo.length];

        nodeList = new ArrayList<>();
        for(int i = 0 ; i < nodeinfo.length; i++){
            nodeList.add(new node(nodeinfo[i][0], nodeinfo[i][1], i+1));
        }
        Collections.sort(nodeList);

        node root = nodeList.get(0);

        for(int i = 1; i < nodeList.size() ; i++){
            makeTree(root, nodeList.get(i));
        }

        idx = 0;
        preOrder(root);
        idx = 0;
        postOrder(root);

        return answer;
    }

    private static void postOrder(node root) {
        if(root != null){
            postOrder(root.left);
            postOrder(root.right);
            answer[1][idx++] = root.data;
        }
    }

    private static void preOrder(node root) {
        if(root != null){
            answer[0][idx++] = root.data;
            preOrder(root.left);
            preOrder(root.right);
        }

    }

    private static void makeTree(node root, node child) {
        if(child.x < root.x){
            if(root.left == null)
                root.left = child;
            else
                makeTree(root.left, child);
        }
        else{
            if(root.right == null)
                root.right = child;
            else
                makeTree(root.right, child);
        }
    }
    public static void main(String[] args) {
        int[][] nodeinfo = {{5,3},{11,5},{13,3},{3,5},{6,1},{1,3},{8,6},{7,2},{2,2}};
        int[][] answer = solution(nodeinfo);

        for(int[] ans : answer){
            for(int a : ans){
                System.out.print(a +" ");
            }
            System.out.println();
        }
    }
}