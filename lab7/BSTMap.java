import java.security.Key;
import java.util.Iterator;
import java.util.Set;
import java.util.Spliterator;
import java.util.function.Consumer;

public class BSTMap<K extends Comparable<K>,V> implements Map61B<K,V>{
    private Node root;

    private class Node{
        private K key;
        private V value;
        private Node left,right;
        private int size;

        public Node(K key,V value,int size){
            this.key=key;
            this.value=value;
            this.size=size;
        }
    }

    @Override
    public void clear() {
        root.size=0;
        root=null;
    }

    @Override
    public boolean containsKey(K key) {
        if (key==null) throw new IllegalArgumentException("argument to contains() is null");
        return get(key)!=null;
    }

    @Override
    public V get(K key) {
        return get(root,key);
    }

    public V get(Node x,K key){
        if (key == null) throw new IllegalArgumentException("calls get() with a null key");
        if (x==null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp<0){return get(x.left,key);}
        else if (cmp>0){return get(x.right,key);}
        else return x.value;
    }

    @Override
    public int size() {
        return size(root);
    }

    public int size(Node x){
        if (x==null){
            return 0;
        }
        else return x.size;
    }

    @Override
    public void put(K key, V value) {
        if (key==null) throw new IllegalArgumentException("calls put() with a null key");
        if (value==null){
            remove(key);
            return;
        }
        root=put(root,key,value);
        //assert check();
    }

    private Node put(Node x,K key,V value){
        if (x == null) return new Node(key, value, 1);
        int cmp= key.compareTo(x.key);
        if (cmp<0){
            x.left=put(x.left,key,value);
        }else if (cmp>0){
            x.right=put(x.right,key,value);
        }else {
            x.value=value;
        }
        x.size = size(x.left)+size(x.right)+1;
        return x;
    }

    public void printInOrder(){
        System.out.println();
    }

    @Override
    public Set keySet() {
        return null;
    }

    @Override
    public V remove(K key) {
        return null;
    }

    @Override
    public V remove(K key, V value) {
        return null;
    }

    @Override
    public void forEach(Consumer action) {

    }

    @Override
    public Spliterator spliterator() {
        return null;
    }

    @Override
    public Iterator iterator() {
        throw new UnsupportedOperationException();
    }
}
