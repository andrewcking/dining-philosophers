/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package philosopher;

/**
 *
 * @author Andrew
 */
public class Philosopher extends Thread {

    private Object leftFork, rightFork;
    private int myNumber;

    public Philosopher(Object left, Object right, int number) {
        leftFork = left;
        rightFork = right;
        myNumber = number;
    }

    @Override
    public void run() {
        int timesDined = 0;
        while (true) {
            synchronized (leftFork) {
                synchronized (rightFork) {
                    timesDined++;
                }
            }
            if (timesDined % 100000 == 0) {
                System.err.println("Thread " + myNumber + " is running.");
            }
        }
    }

    public static void main(String[] args) {
        final int PHILOSOPHERS = 5;
        Object[] forks = new Object[PHILOSOPHERS];
        for (int i = 0; i < PHILOSOPHERS; i++) {
            forks[i] = new Object();
        }
        //added 2 lines to specify fork order
        Philosopher p = new Philosopher(forks[1], forks[0], 0);
        p.start();
        for (int i = 1; i < PHILOSOPHERS; i++) {
            int next = (i + 1) % PHILOSOPHERS;
            p = new Philosopher(forks[i], forks[next], i);
            p.start();
        }
    }
}
