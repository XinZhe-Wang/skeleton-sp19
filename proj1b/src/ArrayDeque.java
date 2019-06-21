public class ArrayDeque<T> implements Deque<T>{
    private T[] items;
    private int size;
    private static int FACTOR = 2;
    private int capacity = 8;
    private int nextFirst;
    private int nextLast;



    public ArrayDeque(){
        items = (T[]) new Object[capacity];
        nextFirst = 0;
        nextLast = 1;
        size = 0;
    }

    public void resize(int capacity){
        T[] temp = (T[]) new Object[capacity];
        System.arraycopy(items,0,temp,0,size);
        items = temp;
        nextFirst = capacity-1;
        nextLast = size;
    }

    public void printDeque(){
        for (int i= 0;i<items.length;i++){
            System.out.println(items[i]+" ");
        }
    }
    /*
    * addFirst方法解析：
    * 1.检查这个数组是否已经被填满，填满就扩大数组
    * 2.将新加入的item添加到array头部，也就是nextFirst的位置
    * 3.更新size和nextFirst，nextFirst的位置前移，所以要--
    * 4.如果nextFirst的值小于0，并且数组队尾不为null时，证明数组已满，需要扩大数组
    * 5.扩大数组后，新出现的位置肯定是没有填充的，所以nextFirst位置为整个数组的最后一个位置
    * */

    public void addFirst(T item){
        if (size==items.length){
            resize(FACTOR*size);
        }

        items[nextFirst] = item;
        size++;
        nextFirst--;
        if (nextFirst<0){
            if (items[items.length-1] != null){
                resize(size*FACTOR);
            }
            nextFirst = items.length-1;
        }
    }

    public boolean isEmpty(){
        return size==0;
    }
    /*
    * addLast解析：
    * 1.判断数组是否为空，如果数组为空，那么在0位置插入元素
    * 2.不为空的话，需要判定现在数组是否已满，如果已满就扩大
    * 3.在nextLast处填入，然后更新size和nextLast的值
    * 4.如果更新之后nextLast大于数组长度，并且数组的第一位不为空，证明数组已满，需要扩张数组
    * */
    public void addLast(T item){
        if (isEmpty()){
            items[0] = item;
            size ++;
            nextFirst = items.length-1;
        } else {
            if (size==items.length){
                resize(size*FACTOR);
            }
            items[nextLast] = item;
            nextLast++;
            size++;
            if (nextLast>items.length || items[0] != null){
                resize(size*FACTOR);
            }
            nextLast = size;
        }
    }

    /*removeLast:
    * 1.判断数组是否为空
    * 2.如果不为空，判断nextlast是否为0，即第一位，这时删除last元素将会倒退至数组队尾
    * 3.更新nextLast size
    * 4.检测是否需要收缩数组
    * */

    public T removeLast(){
        if (isEmpty()){
            return null;
        }
        if (nextLast == 0){
            nextLast = items.length;
        }
        nextLast--;
        T temp = items[nextLast];
        items[nextLast] = null;
        size--;
        if (size>16 && size/items.length < 0.25){
            shrink();
        }
        return temp;
    }

    /*如果size> 16 并且items填充度小于0.25，就将收缩数组*/
    private void shrink() {
        int nullStartIndex = 0;
        int nullEndIndex = 0;
        for(int i = 0; i< items.length;i++) {
            if(items[i] == null) {
                nullStartIndex = i;
                break;
            }
        }
        for(int i = items.length-1;i>=0;i--) {
            if(items[i] == null) {
                nullEndIndex = i;
                break;
            }
        }
        int frontSize = nullStartIndex;
        int rearSize = (items.length-1) - nullEndIndex;
        for(int i = 0;i< rearSize;i++) {
            items[nullStartIndex+i] = items[nullEndIndex+i+1];
        }
        resize(frontSize+rearSize);
    }


    public T getLast(){
        return items[nextLast];
    }

    public T get(int i){
        return items[i];
    }


    /*removeFirst：
    * 1.判断是否为空
    * 2.判断是否nextFirst为末尾，如果是赋值为1，nextFirst++后会为0
    * 3.判断是否数组填不满，不满就收缩
    * */
    public T removeFirst(){
        if (isEmpty()){
            return null;
        }
        if (nextFirst == items.length - 1){
            nextFirst = -1;
        }
        nextFirst++;
        T temp = items[nextFirst];
        items[nextFirst] = null;
        size--;
        if (size>16 && size/items.length<0.25){
            shrink();
        }
        return temp;
    }

    public int size(){
        return size;
    }

    public static void main(String[] args) {
        ArrayDeque A = new ArrayDeque();
        A.addLast(5);
        A.addLast(6);
        A.addLast(8);
        A.addLast(10);
        A.addLast(50);
        A.addFirst(100);
        A.removeLast();
        A.addFirst(999);
        A.removeFirst();
        A.printDeque();
    }
}
