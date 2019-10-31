import edu.princeton.cs.algs4.Queue;

import java.security.Key;
import java.util.*;
import java.util.function.Consumer;

public class MyHashMap<K, V> implements Map61B<K,V>{

    private ArrayList<LinkedList<Entry>> buckets;
    private HashSet<K> keySet;
    private int numOfEntries = 0; //键值对总数
    private int numOfBuckets; //存放链表对象的
    private double maxAvgBucketSize;



    private class Entry {
        K key;
        V value;

        Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }



    public MyHashMap(){
        this(16);
    };

    public MyHashMap(int M){
        this(M,0.75);
    };

    public MyHashMap(int initialSize, double loadFactor){
        keySet=new HashSet<>();
        buckets=new ArrayList<>();
        numOfBuckets=initialSize;
        maxAvgBucketSize=loadFactor;
        for(int i=0;i<initialSize;i++){
            buckets.add(new LinkedList<>());
        }
    };

    private void resize(int capacity){
        ArrayList<LinkedList<Entry>> newBuckets = new ArrayList<>();
        for (int i=0;i<capacity;i++){
            newBuckets.add(new LinkedList<>());
        }
        for (K key:keySet){
            int index=hash(key,capacity);
            newBuckets.get(index).add(new Entry(key,get(key)));
        }
        this.numOfBuckets=capacity;
        this.buckets=newBuckets;
    }

    @Override
    public void clear() {
        keySet = new HashSet<>();
        buckets = new ArrayList<>();
        numOfBuckets = 0;
        numOfEntries = 0;
    }

    @Override
    public boolean containsKey(K key) {
        return keySet.contains(key);
    }

    @Override
    public V get(K key) {
        if (!containsKey(key)){
            return null;
        }
        int index = hash(key,numOfBuckets);
        for (Entry entry:buckets.get(index)){
            if (entry.key.equals(key)){
                return entry.value;
            }
        }
        return null;
    }

    @Override
    public int size() {
        return numOfEntries;
    }

    private void update(K key, V value) {
        int index = hash(key,numOfBuckets);
        for (Entry entry : buckets.get(index)) {
            if (entry.key.equals(key)) {
                entry.value = value;
            }
        }
    }

    @Override
    public void put(K key, V value) {
        if (containsKey(key)){
            update(key,value);
            return;
        }
        if (size()>=maxAvgBucketSize*numOfBuckets){
            resize(2*numOfBuckets);
        }
        int index=hash(key,numOfBuckets);
        buckets.get(index).add(new Entry(key,value));
        keySet.add(key);
        numOfEntries+=1;
    }

    @Override
    public Set<K> keySet() {
        return keySet;
    }

    @Override
    public V remove(K key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public V remove(K key, V value) {
        throw new UnsupportedOperationException();
    }


    private int hash(K key,int capacity){
        return Math.floorMod(key.hashCode(), capacity);
    }

    @Override
    public Iterator<K> iterator() {
        return null;
    }

    @Override
    public void forEach(Consumer<? super K> action) {

    }

    @Override
    public Spliterator<K> spliterator() {
        return null;
    }
}
