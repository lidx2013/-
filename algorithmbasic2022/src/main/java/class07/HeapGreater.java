package class07;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

public class HeapGreater<T> {

    private ArrayList<T> heap;
    private HashMap<T, Integer> indexMap;
    private int heapSize;
    private Comparator<? super T> comp;

    public HeapGreater(Comparator<? super T> c) {
        heap = new ArrayList<>();
        indexMap = new HashMap<>();
        heapSize = 0;
        comp = c;
    }

    public boolean isEmpty() {
        return heapSize == 0;
    }

    public int size() {
        return heapSize;
    }

    public boolean contains(T obj) {
        return indexMap.containsKey(obj);
    }

    public T peek() {
        return heap.get(0);
    }

    public void push(T obj) {
        heap.add(obj);
        indexMap.put(obj, heapSize);
        heapInsert(heapSize++);
    }

    public T pop() {
        T ans = heap.get(0);
        swap(0, heapSize - 1);
        indexMap.remove(ans);
        heap.remove(--heapSize);
        heapify(0);
        return ans;
    }

    private void heapify(int i) {
        int left = i * 2 + 1;

        while (left < heapSize) {
            int largeset = left + 1 < heapSize && comp.compare(heap.get(left), heap.get(left + 1)) < 0 ? left + 1 : left;
            largeset = comp.compare(heap.get(largeset), heap.get((i - 1) / 2)) > 0 ? largeset : (i - 1) / 2;

            if (largeset == i) {
                return;
            }
            swap(i, largeset);
            i = largeset;
            left = i * 2 + 1;
        }
    }

    public void heapInsert(int index) {
        while (comp.compare(heap.get(index), heap.get((index - 1) / 2)) < 0) {
            swap(index, (index - 1) / 2);
            index = (index - 1) / 2;
        }
    }

    private void swap(int i, int j) {
        T o1 = heap.get(i);
        T o2 = heap.get(j);
        heap.set(i, o2);
        heap.set(j, o1);
        indexMap.put(o2, i);
        indexMap.put(o1, j);
    }

    public void remove(T obj) {
        T replace = heap.get(heapSize - 1);
        int index = indexMap.get(obj);
        indexMap.remove(obj);
        heap.remove(--heapSize);
        if (obj!=replace){
            heap.set(index,replace);
            indexMap.put(replace,index);
            resign(replace);
        }
    }

    public void resign(T replace) {
        heapInsert(indexMap.get(replace));
        heapify(indexMap.get(replace));
    }
}
