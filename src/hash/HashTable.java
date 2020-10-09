package hash;

// 哈希表的增删改查
public abstract class HashTable<K,V> {
    protected int size;
    protected float loadFactor; // 负载因子 the average number of elements stored in a chain.
    protected int capacity;
    protected int threshold;

    public abstract void insert(K key,V value);
    public abstract void remove(K key);
    public abstract V get(K key);
    public abstract void set(K key,V value);
    public abstract int hash(K key);
    public int size(){
        return size;
    }
}
