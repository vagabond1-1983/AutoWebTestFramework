package com.kong.map;

/**
 * Created with IntelliJ IDEA.
 * User: devin
 * Date: 7/30/13
 * Time: 9:59 PM
 * To change this template use File | Settings | File Templates.
 */
public class HashMapSimulate {
    private int size;
    private int threshold = 1;
    private Entry[] table;

    public HashMapSimulate() {
        table = new Entry[threshold];
    }

    public void put(Integer key, String value) {
        int hash = generateHash(key);

        int i = indexFor(hash, table.length);
        // Check position has object
        for (Entry e = table[i]; e != null; e = e.next) {
            e.value = value;
            return;
        }
        addEntry(hash, key, value, i);
    }

    /**
     * Returns index for hash code h.
     */
    static int indexFor(int h, int length) {
        return h & (length - 1);
    }

    private void addEntry(int hash, Integer key, String value, int bucketIndex) {
        checkSize();
        createEntry(hash, key, value, bucketIndex);
    }

    private void createEntry(int hash, Integer key, String value, int bucketIndex) {
        table[bucketIndex] = new Entry(hash, key, value, null);
        size++;
    }

    private void checkSize() {
        if (size >= threshold) {
            threshold = table.length * 2;
            Entry[] newTable = new Entry[threshold];
            System.arraycopy(table, 0, newTable, 0, table.length);
            table = newTable;
        }
    }

    // Copy from HashMap
    private int generateHash(Integer key) {
        int h = 0;
        h ^= key.hashCode();

        // This function ensures that hashCodes that differ only by
        // constant multiples at each bit position have a bounded
        // number of collisions (approximately 8 at default load factor).
        h ^= (h >>> 20) ^ (h >>> 12);
        return h ^ (h >>> 7) ^ (h >>> 4);
    }

    private class Entry {
        private int hash;
        private Integer key;
        private String value;
        private Entry next;

        Entry(int hash, Integer key, String value, Entry next) {
            this.hash = hash;
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }
}
