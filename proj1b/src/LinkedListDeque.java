public class LinkedListDeque<T> implements Deque<T>{
    private class IntNode{
        private IntNode prev;
        private T item;
        private IntNode next;

        private IntNode(IntNode p,T i ,IntNode n){
            prev = p;
            item = i;
            next = n;
        }
    }

    private IntNode sentinel_to_front;
    private IntNode sentinel_to_rear;
    private IntNode I;
    private IntNode now;

    private int size;

    public LinkedListDeque(T item){
        sentinel_to_front = new IntNode(null,null,null);
        sentinel_to_rear = new IntNode(null,null,null);
        I = new IntNode(sentinel_to_front,item, sentinel_to_rear);
        sentinel_to_front.next = I;
        sentinel_to_rear.prev = I;
        size =1;
    }

    public LinkedListDeque(){
        sentinel_to_front = new IntNode(null,null,null);
        sentinel_to_rear = new IntNode(null,null,null);
        sentinel_to_rear.prev = sentinel_to_front;
        sentinel_to_front.next = sentinel_to_rear;
        size = 0;
    }

    public void addFirst(T item){
        IntNode new_first = new IntNode(sentinel_to_front,item,sentinel_to_front.next);
        sentinel_to_front.next.prev = new_first;
        size = size + 1;
        sentinel_to_front.next = new_first;
    }

    public void addLast(T item){
        IntNode new_last = new IntNode(sentinel_to_rear.prev,item, sentinel_to_rear);
        sentinel_to_rear.prev.next = new_last;
        sentinel_to_rear.prev = new_last;
        size = size + 1;
    }

    public int size(){
        return size;
    }

    public boolean isEmpty(){
        return size==0;
    }

    public void printDeque(){
        now = sentinel_to_front.next;
        while (now.item!=null){
            System.out.print(now.item + " ");
            now = now.next;
        }
    }

    public T removeFirst(){
        if (sentinel_to_front.equals(sentinel_to_rear)){
            return null;
        }
        else {
            IntNode preRemove = sentinel_to_front.next;
            preRemove.next.prev = sentinel_to_front;
            sentinel_to_front.next = preRemove.next;
            preRemove.next = null;
            preRemove.prev = null;
            size = size-1;
            return preRemove.item;
        }
    }

    public T removeLast(){
        if (sentinel_to_front.equals(sentinel_to_rear)){
            return null;
        }
        else {
            IntNode preRemove = sentinel_to_rear.prev;
            preRemove.prev.next = sentinel_to_rear;
            sentinel_to_rear.prev = preRemove.prev;
            preRemove.next = null;
            preRemove.prev = null;
            size =size-1;
            return preRemove.item;
        }
    }

    public T get(int index){
        IntNode p = sentinel_to_front.next;
        if (index>=size){
            return null;
        }
        else {
            while (index>0){
                p = p.next;
                index--;
            }
            return p.item;
        }
    }



    public static void main(String[] args) {
        LinkedListDeque L =new LinkedListDeque();
        L.addFirst(20);
        L.addFirst(25);
        L.addFirst(30);
        L.addLast(60);
        //System.out.println(L.get(0));
        L.printDeque();
    }
}
