import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class No1 {
    private final static int CMD_INIT = 1;
    private final static int CMD_BUY = 2;
    private final static int CMD_SELL = 3;
    private final static int CMD_CANCEL = 4;
    private final static int CMD_BEST_PROFIT = 5;

    private final static UserSolution usersolution = new UserSolution();

    private static boolean run(BufferedReader br) throws Exception {
        StringTokenizer st;

        int numQuery;

        int mNumber, mStock, mQuantity, mPrice;

        int userAns, ans;

        boolean isCorrect = false;

        numQuery = Integer.parseInt(br.readLine());

        for (int q = 0; q < numQuery; ++q) {
            st = new StringTokenizer(br.readLine(), " ");

            int cmd;
            cmd = Integer.parseInt(st.nextToken());

            switch (cmd) {
                case CMD_INIT:
                    usersolution.init();
                    isCorrect = true;
                    break;
                case CMD_BUY:
                    mNumber = Integer.parseInt(st.nextToken());
                    mStock = Integer.parseInt(st.nextToken());
                    mQuantity = Integer.parseInt(st.nextToken());
                    mPrice = Integer.parseInt(st.nextToken());
                    userAns = usersolution.buy(mNumber, mStock, mQuantity, mPrice);
                    ans = Integer.parseInt(st.nextToken());
                    System.out.println("BUY - userAns: " + userAns + " ans: " + ans);
                    if (userAns != ans) {
                        isCorrect = false;
                    }
                    break;
                case CMD_SELL:
                    mNumber = Integer.parseInt(st.nextToken());
                    mStock = Integer.parseInt(st.nextToken());
                    mQuantity = Integer.parseInt(st.nextToken());
                    mPrice = Integer.parseInt(st.nextToken());
                    userAns = usersolution.sell(mNumber, mStock, mQuantity, mPrice);
                    ans = Integer.parseInt(st.nextToken());
                    System.out.println("SELL - userAns: " + userAns + " ans: " + ans);
                    if (userAns != ans) {
                        isCorrect = false;
                    }
                    break;
                case CMD_CANCEL:
                    mNumber = Integer.parseInt(st.nextToken());
                    usersolution.cancel(mNumber);
                    break;
                case CMD_BEST_PROFIT:
                    mStock = Integer.parseInt(st.nextToken());
                    userAns = usersolution.bestProfit(mStock);
                    ans = Integer.parseInt(st.nextToken());
                    System.out.println("BEST - userAns: " + userAns + " ans: " + ans);
                    if (userAns != ans) {
                        isCorrect = false;
                    }
                    break;
                default:
                    isCorrect = false;
                    break;
            }
        }

        return isCorrect;
    }

    public static void main(String[] args) throws Exception {
        int TC, MARK;

        //System.setIn(new java.io.FileInputStream("res/sample_input.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        TC = Integer.parseInt(st.nextToken());
        MARK = Integer.parseInt(st.nextToken());

        for (int testcase = 1; testcase <= TC; ++testcase) {
            int score = run(br) ? MARK : 0;
            System.out.println("#" + testcase + " " + score);
        }

        br.close();
    }


    static class UserSolution {

        public UserSolution() {
        }

        static class stock {
            int number;
            int stockNum;
            int quantity;
            int price;

            public stock(int number, int stockNum, int quantity, int price) {
                this.number = number;
                this.stockNum = stockNum;
                this.quantity = quantity;
                this.price = price;
            }
        }

        class SortForBuy implements Comparator<stock> {
            @Override
            public int compare(stock o1, stock o2) {
                int result = o1.price - o2.price;
                result = result == 0 ? o1.number - o2.number : result;

                return result;
            }
        }

        class SortForSell implements Comparator<stock> {
            @Override
            public int compare(stock o1, stock o2) {
                int result = o2.price - o1.price;
                result = result == 0 ? o1.number - o2.number : result;

                return result;
            }
        }

        Map<Integer, stock> stocksForBuy[];
        Map<Integer, stock> stocksForSell[];
        List<Integer> profitList[];

        public void init() {
            stocksForBuy = new HashMap[6];
            stocksForSell = new HashMap[6];
            profitList = new ArrayList[6];
            for (int i = 0; i < 6; i++) {
                stocksForBuy[i] = new HashMap<>();
                stocksForSell[i] = new HashMap<>();
                profitList[i] = new ArrayList<>();
            }
        }

        public int buy(int mNumber, int mStock, int mQuantity, int mPrice) {
            stock tmp = new stock(mNumber, mStock, mQuantity, mPrice);
            if (stocksForSell[mStock].isEmpty()) {
                stocksForBuy[mStock].put(mNumber, tmp);
                return stocksForBuy[mStock].get(mNumber).quantity;
            } else {
                List<stock> list = new ArrayList<>(stocksForSell[mStock].values());
                Collections.sort(list, new SortForBuy());

                for (stock s : list) {
                    if (tmp.price < s.price) {
                        break;
                    }
                    if (tmp.quantity == 0) {
                        return 0;
                    }
                    if (tmp.quantity < s.quantity) {
                        s.quantity -= tmp.quantity;
                        profitList[mStock].add(s.price);
                        return 0;
                    } else if (tmp.quantity == s.quantity) {
                        profitList[mStock].add(s.price);
                        stocksForSell[mStock].remove(s.number);
                        return 0;
                    } else {
                        profitList[mStock].add(s.price);
                        tmp.quantity -= s.quantity;
                        stocksForSell[mStock].remove(s.number);
                    }
                }
                stocksForBuy[mStock].put(mNumber, tmp);
                return tmp.quantity;
            }
        }

        public int sell(int mNumber, int mStock, int mQuantity, int mPrice) {
            stock tmp = new stock(mNumber, mStock, mQuantity, mPrice);
            if (stocksForBuy[mStock].isEmpty()) {
                stocksForSell[mStock].put(mNumber, tmp);
                return mQuantity;
            } else {
                List<stock> list = new ArrayList<>(stocksForBuy[mStock].values());
                Collections.sort(list, new SortForSell());

                for (stock s : list) {
                    if (s.price < tmp.price) {
                        break;
                    }
                    if (tmp.quantity == 0) {
                        return 0;
                    }
                    if (tmp.quantity < s.quantity) {
                        s.quantity -= tmp.quantity;
                        profitList[mStock].add(s.price);
                        return 0;
                    } else if (tmp.quantity == s.quantity) {
                        profitList[mStock].add(s.price);
                        stocksForBuy[mStock].remove(s.number);
                        return 0;
                    } else {
                        profitList[mStock].add(s.price);
                        tmp.quantity -= s.quantity;
                        stocksForBuy[mStock].remove(s.number);
                    }
                }
                stocksForSell[mStock].put(mNumber, tmp);

                return tmp.quantity;
            }
        }

        public void cancel(int mNumber) {
            for (Map<Integer, stock> map : stocksForBuy) {
                for (int i : map.keySet()) {
                    if (i == mNumber) {
                        map.remove(i);
                        return;
                    }
                }
            }
            for (Map<Integer, stock> map : stocksForSell) {
                for (int i : map.keySet()) {
                    if (i == mNumber) {
                        map.remove(i);
                        return;
                    }
                }
            }
        }

        public int bestProfit(int mStock) {
            int maxProfit = 0;
            for (int i = profitList[mStock].size() - 1; i > 0; i--) {
                for (int j = 0; j < i; j++) {
                    int p = profitList[mStock].get(i) - profitList[mStock].get(j);
                    maxProfit = maxProfit < p ? p : maxProfit;
                }
            }
            return maxProfit;
        }
    }
}