package com.lisheng.project.eliminate;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * @ClassName： LRU
 * @description: 最近最久未使用淘汰算法
 * LinkedHashMap 开启顺序访问 即刚被访问过得放在最前面
 * @author: 李胜
 * @create: 2020-06-06 16:21
 **/
public class LRU<K,V> extends LinkedHashMap<K,V>{
    public  final int MAX_SIZE;
    public LRU(int initialCapacity) {

        super((int)Math.ceil(initialCapacity/0.75f),0.75f, true);
        MAX_SIZE=initialCapacity;
    }
    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
       return size()>MAX_SIZE;
    }
}
