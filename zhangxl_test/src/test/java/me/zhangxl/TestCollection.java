package me.zhangxl;

import org.junit.Test;

import java.util.AbstractCollection;
import java.util.AbstractList;
import java.util.AbstractSet;
import java.util.Iterator;

/**
 * Created by zhangxiaolong on 16/5/3.
 */
public class TestCollection {

    @Test
    public void testCollection(){

    }

    public static class MyCollection<E> extends AbstractCollection<E>{

        @Override
        public Iterator<E> iterator() {
            return null;
        }

        @Override
        public int size() {
            int num = 0;
            Iterator<E> iterator = iterator();
            while (iterator.hasNext()){
                num ++;
            }
            return num;
        }
    }

    public static class MyList<E> extends AbstractList<E>{

        @Override
        public E get(int index) {
            return null;
        }

        @Override
        public int size() {
            return 0;
        }
    }

    public static class MySet<E> extends AbstractSet<E> {

        @Override
        public Iterator<E> iterator() {
            return null;
        }

        @Override
        public int size() {
            return 0;
        }
    }
}
