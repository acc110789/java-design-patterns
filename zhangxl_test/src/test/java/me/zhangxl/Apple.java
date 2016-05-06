package me.zhangxl;

/**
 * Created by zhangxiaolong on 16/5/6.
 */
public class Apple extends Fruit {
    @Override
    public void out() {
        System.out.println("apple");
    }

    public Fruit getSuper(){
        return null;
    }
}
