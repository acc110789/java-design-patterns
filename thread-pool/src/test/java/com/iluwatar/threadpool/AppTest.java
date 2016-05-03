/**
 * The MIT License
 * Copyright (c) 2014 Ilkka Seppälä
 * <p>
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * <p>
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * <p>
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.iluwatar.threadpool;

import org.junit.Test;

import java.util.concurrent.*;

/**
 * Application test
 *
 * @author ilkka
 *
 */
public class AppTest {

    @Test
    public void test() {
        System.out.println("Program started");
        ExecutorService executor = Executors.newFixedThreadPool(2);

        executor.execute(new MyWorker());
        executor.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println("sleep start");
                try {
                    TimeUnit.SECONDS.sleep(10);
                } catch (InterruptedException e) {
                    System.out.println("sleep interrupted");
                }
                System.out.println("sleep over");
            }
        });

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("1 second passed");
        executor.shutdownNow();
        System.out.println("isShutdown: "+executor.isShutdown());
        System.out.println("isTerminated: "+ executor.isTerminated());
        long time1 = System.currentTimeMillis();
        while (!executor.isTerminated()) {
            Thread.yield();
        }
        long time2 = System.currentTimeMillis();
        System.out.format("duration: %d\n",time2-time1);
        System.out.println("Program finished");
    }

    private class MyWorker implements Runnable{

        private boolean print = false;

        @Override
        public void run() {
            System.out.println("fab start");
            fab(42);
            System.out.println("fab over");
        }

        private int fab(int num){
            if(Thread.interrupted() && !print){
                print = true;
                System.out.println("already interrupted");
            }
            if(num == 0 || num == 1){
                return 1;
            } else {
                return fab(num-1)+fab(num-2);
            }
        }

    }

    @Test
    public void testAlive(){
        final Object object = new Object();

        Thread thread = new Thread(){
            @Override
            public void run() {
                synchronized (object) {
                    System.out.println("run run run!!!");
                }
            }
        };
        System.out.println("isAlive1: " + thread.isAlive());
        thread.start();
        synchronized (object) {
            System.out.println(thread.getState() + " isAlive2: " + thread.isAlive() );
        }
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("isAlive3: " + thread.isAlive());
    }

    private int threadId = 0;
    @Test
    public void testOverflow() throws InterruptedException {
        BlockingQueue<Runnable> queue = new ArrayBlockingQueue<>(5,true);
        ThreadPoolExecutor executor = new ThreadPoolExecutor(2, 4, 0, TimeUnit.SECONDS, queue,
                new ThreadFactory() {
                    @Override
                    public Thread newThread(Runnable r) {
                        return new Thread(r,String.valueOf(++threadId));
                    }
                }, new RejectedExecutionHandler() {
            @Override
            public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                System.out.println("reject a runable");
            }
        });
        for(int i=1;i<=9;i++){
            executor.execute(new Sleep(i));
        }
        TimeUnit.SECONDS.sleep(10);
        System.out.println("active count : " + executor.getPoolSize());
        executor.shutdown();
        while(!executor.isTerminated()){
            Thread.yield();
        }
        System.out.println("over");
    }

    private class Sleep implements Runnable{

        private final int id;

        Sleep(int id){
            this.id = id;
        }

        @Override
        public void run() {

            System.out.println("task :"+this.id + " begin sleep" + " Thread: "+Thread.currentThread().getName());
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("task: "+this.id + " end sleep");
        }
    }

}
