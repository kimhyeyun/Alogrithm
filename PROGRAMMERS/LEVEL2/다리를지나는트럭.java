import java.util.LinkedList;
import java.util.Queue;

public class 다리를지나는트럭 {

    public static int solution(int bridge_length, int weight, int[] truck_weights) {
        int answer = 0;
        int curWeight = 0;
        Queue<Integer> truckOnBridge = new LinkedList<>();
        Queue<Integer> distTruck = new LinkedList<>();
        Queue<Integer> truckWeight = new LinkedList<>();

        for(int t : truck_weights){
            truckWeight.add(t);
        }

        while(true){
            int size = distTruck.size();

            for(int i = 0; i < size; i++){
                int dist = distTruck.poll();

                if(dist <= 1){
                    curWeight -= truckOnBridge.poll();
                    continue;
                }
                
                else{
                    distTruck.add(dist - 1);
                }
            }

            if(truckWeight.size() > 0 && curWeight + truckWeight.peek() <= weight){
                truckOnBridge.add(truckWeight.peek());
                curWeight += truckWeight.peek();
                distTruck.add(bridge_length);
                truckWeight.poll();
            }

            answer++;

            if(truckWeight.size() == 0 && truckOnBridge.size() == 0){
                break;
            }
        }

        return answer;
    }

    /* 다른사람 코드 */
    class Truck{
        int weight;
        int move;

        public Truck(int weight){
            this.weight = weight;
            this.move = 1;
        }

        public void moving(){
            move++;
        }
    }

    public int otherSolution(int bridgeLength, int weight, int[] truckWeights){
        Queue<Truck> waitQ = new LinkedList<>();
        Queue<Truck> moveQ = new LinkedList<>();

        for(int t : truckWeights){
            waitQ.add(new Truck(t));
        }

        int answer = 0;
        int curWeight = 0;

        while(!waitQ.isEmpty() || !moveQ.isEmpty()){
            answer++;

            if(moveQ.isEmpty()){
                Truck t = waitQ.poll();
                curWeight += t.weight;
                moveQ.offer(t);
                continue;
            }

            for(Truck t : moveQ){
                t.moving();
            }

            if(moveQ.peek().move > bridgeLength){
                Truck t = moveQ.poll();
                curWeight -= t.weight;
            }

            if(!waitQ.isEmpty() && curWeight + waitQ.peek().weight <= weight){
                Truck t = waitQ.poll();
                curWeight += t.weight;
                moveQ.offer(t);
            }
        }
        return answer;
    }

    public static void main(String[] args) {
        int[] t = {10,10,10,10,10,10,10,10,10,10};

        System.out.println(solution(100, 100, t));
    }
}
