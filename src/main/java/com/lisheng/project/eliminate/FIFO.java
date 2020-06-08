package com.lisheng.project.eliminate;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @ClassName： FIFO
 * @description:
 * @author: 李胜
 * @create: 2020-06-06 16:34
 **/
public class FIFO<K,V> {
    private final LinkedHashMap<K,V> linkedHashMap;
    public static final float  loadFactor=0.75f;


    public FIFO(int cacheSize) {
        linkedHashMap = new LinkedHashMap((int)Math.ceil(cacheSize/loadFactor),loadFactor,false){
            @Override
            protected boolean removeEldestEntry(Map.Entry eldest) {
                return size()>=cacheSize;
            }
        };
    }
    public synchronized void put(K key, V value) {
        linkedHashMap.put(key, value);
    }

    public synchronized V get(K key) {
        return linkedHashMap.get(key);
    }

    public synchronized void remove(K key) {
        linkedHashMap.remove(key);
    }

}
