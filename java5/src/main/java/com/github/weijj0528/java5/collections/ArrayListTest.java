package com.github.weijj0528.java5.collections;

import java.util.ArrayList;

public class ArrayListTest {

    public static void main(String[] args) {
        // ArrayList 初始化
        // 默认空集，容量暂为默认10
        ArrayList<String> arrayList1 = new ArrayList<String>();
        // 指定初始容量
        ArrayList<String> arrayList2 = new ArrayList<String>(16);
        // 人现有的集合复制，长度为现集合长度，容量暂为默认10
        ArrayList<String> arrayList3 = new ArrayList<String>(arrayList2);
        // 尾部添加新元素，添加元素时需要确保容量，容量不足时将执行扩容操作，每次扩容旧容量的 1/2
        // int newCapacity = oldCapacity + (oldCapacity >> 1);
        arrayList1.add("001");
        // 添加多个元素，确保容量后，插入待添加集合
        // System.arraycopy(a, 0, elementData, size, numNew);
        arrayList1.addAll(arrayList2);
        // 指定位置添加新元素，检查指定位置是否正确，确保容量后，指定位置后的所有元素后移一个位置，插入元素
        // System.arraycopy(elementData, index, elementData, index + 1, size - index);
        arrayList1.add(0, "001");
        // 指定位置添加多个元素，检查指定位置是否正确，确保容量后，指定位置后的所有元素后移待添加集合长度位置，插入待添加集合
        // System.arraycopy(elementData, index, elementData, index + numNew, numMoved);
        arrayList1.addAll(0, arrayList2);
        // 删除指定位置元素，检查指定位置是否正确，暂存元素，指定位置后的所有元素前移一个位置，返回暂存元素
        // System.arraycopy(elementData, index+1, elementData, index, numMoved);
        arrayList1.remove(0);
        // 删除指定元素，空元素检查，若删除空元素，删除第一个空元素，若不为空，删除匹配到的第一个元素
        arrayList1.remove("001");
        // 批量删除，不需要删除的元素前移，后续元素置空
        arrayList1.removeAll(arrayList2);
    }

}
