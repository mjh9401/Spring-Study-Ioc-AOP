package org.example.counter;

public class Counter implements Runnable {
    private int count = 0;

    public void increament(){
        count++;
    }

    public void decreament(){
        count--;
    }

    public int getValue(){
        return count;
    }

    @Override
    public void run() {
        synchronized (this){
            this.increament();
            System.out.println("Value for Thread After increament " + Thread.currentThread().getName() + " " + this.getValue()); //1
            this.decreament();
            System.out.println("Value for Thread at last " + Thread.currentThread().getName() + " " + this.getValue()); //0
        }
    }
}
