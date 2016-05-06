package me.zhangxl;

import org.junit.Test;

/**
 * Created by zhangxiaolong on 16/5/6.
 */
public class RedApple extends Apple {
    @Override
    public void out() {
        super.out();
        ((Fruit)this).out();
    }

    @Test
    public void testOut(){
        this.out();
    }
}
