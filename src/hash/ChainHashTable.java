package hash;

import java.util.TreeMap;

// 拉链法
public class ChainHashTable<K extends Comparable<K>,V> extends HashTable<K,V> {
    private TreeMap<K,V>[] table;

    public ChainHashTable(int initCapacity,float loadFactor){
        if(initCapacity < 0){
            throw new IllegalArgumentException("Illegal Capacity: " + initCapacity);
        }

        if(loadFactor <= 0 || Float.isNaN(loadFactor)){
            throw new IllegalArgumentException("Illegal Load Factor: " + loadFactor);
        }

        if(initCapacity == 0){
            initCapacity = 1;
        }

        this.loadFactor = loadFactor;
        table = new TreeMap[initCapacity];
        threshold = (int) (initCapacity * loadFactor);
        this.capacity = initCapacity;
        this.size = 0;
        for (int i = 0; i < capacity; i++) {
            table[i] = new TreeMap<>();
        }
    }

    public ChainHashTable(){
        this(11,0.75f);
    }


    @Override
    public void insert(K key, V value) {
        int i = hash(key);
        TreeMap<K,V> map = table[i];
        if(!map.containsKey(key)){
            map.put(key,value);
            size++;
            if(size > threshold){
                resize(capacity*2);
            }
        }
    }

    @Override
    public void remove(K key) {
        int i = hash(key);
        TreeMap<K,V> map = table[i];
        if(map.containsKey(key)){
           map.remove(key);
           size--;
        }else{
            throw new IllegalArgumentException("the key doesn't exist");
        }
    }

    @Override
    public V get(K key) {
        int i = hash(key);
        TreeMap<K,V> map = table[i];
        return map.getOrDefault(key,null);
    }

    @Override
    public void set(K key, V value) {
        int i = hash(key);
        TreeMap<K,V> map = table[i];
        if(map.containsKey(key))
            map.put(key,value);
        else
            throw new IllegalArgumentException("the item doesn't exist in the hashtable");
    }

    // 将hashCode映射到[0,M)
    @Override
    public int hash(K key) {
        return (key.hashCode() & 0x7fffffff)%capacity;
    }

    void resize(int newCapacity){

        TreeMap<K,V>[] newTables = new TreeMap[newCapacity];
        for (int i = 0; i < newCapacity; i++) {
            newTables[i] = new TreeMap<>();
        }

        int oldCapacity = capacity;
        capacity = newCapacity; // 因为这个值会影响后续的hash计算，所以需要先更新

        for (int i = 0; i < oldCapacity; i++) {
            for(K key:table[i].keySet()){
                int h = hash(key);
                newTables[h].put(key,table[i].get(key));
            }
        }

        // 更新其他参数
        table = newTables;
        // capacity已经更新了
        threshold = (int) (capacity*loadFactor);

        System.out.println("扩容，当前容量为: "+ capacity);
    }

}
