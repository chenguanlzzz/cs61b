public class LinkedListDeque<T> implements Deque<T> {
    private class LinkedNode{
        public T item;
        public LinkedNode next;
        public LinkedNode before;

        public LinkedNode(T i, LinkedNode n, LinkedNode b){
            item = i;
            next = n;
            before = b;
        }
    }

    private LinkedNode sentinel;
    private int size;

    public LinkedListDeque(){
        sentinel = new LinkedNode(null, null, null);
        sentinel.before = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }

    public LinkedListDeque(T x){
        sentinel = new LinkedNode(null, null, null);
        LinkedNode newNode = new LinkedNode(x, sentinel, sentinel);
        sentinel.next = newNode;
        sentinel.before = newNode;
        size = 1;
    }

    @Override
    public void addFirst(T x){
        sentinel.next = new LinkedNode(x, sentinel.next, sentinel);
        sentinel.next.next.before = sentinel.next;
        size += 1;
    }

    @Override
    public void addLast(T x){
        sentinel.before = new LinkedNode(x, sentinel, sentinel.before);
        sentinel.before.before.next = sentinel.before;
        size += 1;
    }


    public boolean isEmpty(){
        if (size == 0){
            return true;
        }
        return false;
    }

    @Override
    public int size(){
        return this.size;
    }


    public void printDeque(){
        LinkedNode p = sentinel.next;
        for (int i = 0; i < size; i++){
            System.out.println(p.item);
            p = p.next;
        }
    }

    @Override
    public T removeFirst(){
        if (size == 0){
            return null;
        }
        size -= 1;
        T x = sentinel.next.item;
        sentinel.next = sentinel.next.next;
        sentinel.next.before = sentinel;
        return x;
    }

    @Override
    public T removeLast(){
        if (size == 0){
            return null;
        }
        size -= 1;
        T x = sentinel.before.item;
        sentinel.before = sentinel.before.before;
        sentinel.before.next = sentinel;
        return x;
    }


    @Override
    public T get(int index){
        if (index < 0 || index > size){
            return null;
        }
        LinkedNode p = sentinel.next;
        for (int i = 0; i < index; i++){
            p = p.next;
        }
        return p.item;
    }

    public T getRecursive(int index) {
        if (index < 0 || index > size){
            return null;
        }
        return getRecursive(index, this.sentinel.next);
    }

    private T getRecursive(int index, LinkedNode node) {
        if (index == 0) {
            return node.item;
        }
        return getRecursive(index - 1, node.next);
    }

    public static void main(String[] args) {
        LinkedListDeque <Integer> a = new LinkedListDeque<>(100);
        System.out.println(a.get(0));
    }

}
