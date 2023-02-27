package class07;

import java.util.Comparator;
import java.util.HashMap;

public class EveryStepShowBoss {

    public static class Customer {
        public int id;
        public int buy;
        public int enterTime;

        public Customer(int v, int b, int o) {
            id = v;
            buy = b;
            enterTime = o;
        }
    }

    public static class CandidateComparator implements Comparator<Customer> {
        @Override
        public int compare(Customer o1, Customer o2) {
            return o1.buy != o2.buy ? o2.buy - o1.buy : o1.enterTime - o2.enterTime;
        }
    }

    public static class DaddyComparator implements Comparator<Customer> {

        @Override
        public int compare(Customer o1, Customer o2) {
            return o1.buy != o2.buy ? (o1.buy - o2.buy) : (o1.enterTime - o2.enterTime);
        }
    }

    public static class WhosYourDaddy {
        private HashMap<Integer, Customer> customers;
        private HeapGreater<Customer> candHeap;
        private HeapGreater<Customer> daddyHeap;
        private final int daddyLimit;

        public WhosYourDaddy(int limit) {
            customers = new HashMap<>();
            candHeap = new HeapGreater<>(new CandidateComparator());
            daddyHeap = new HeapGreater<>(new DaddyComparator());
            daddyLimit = limit;
        }

        public void operate(int time, int id, boolean buyOrRefund) {
            if (!buyOrRefund && !customers.containsKey(id)) {
                return;
            }
            if (!customers.containsKey(id)) {
                customers.put(id, new Customer(id, 0, 0));
            }
            Customer c = customers.get(id);
            if (buyOrRefund) {
                c.buy++;
            } else {
                c.buy--;
            }
            if (c.buy == 0) {
                customers.remove(id);
            }

            if (!candHeap.contains(c) && !daddyHeap.contains(c)){
                if (daddyHeap.size()< daddyLimit){
                    c.enterTime = time;
                    daddyHeap.push(c);
                }else{
                    c.enterTime = time;
                    candHeap.push(c);
                }
            }else if ( candHeap.contains(c)){
                if (c.buy==0){
                    candHeap.remove(c);
                }else{
                    candHeap.resign(c);
                }
            }else{
                if (c.buy ==0){
                    daddyHeap.resign(c);
                }else{
                    daddyHeap.resign(c);
                }
            }

        }
    }

}
