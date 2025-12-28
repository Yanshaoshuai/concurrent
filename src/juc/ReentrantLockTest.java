package juc;

import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockTest {
    static class Person {
        private final ReentrantLock lock = new ReentrantLock();
        private int energy;

        private final static int MAX_ENERGY = 100;

        private int sleepTime;

        private final static int MAX_SLEEP_TIME = 24;
        private final static int MIN_SLEEP_TIME = 8;

        private final String name;

        public Person(int energy, int sleepTime, String name) {
            this.energy = energy;
            this.sleepTime = sleepTime;
            this.name = name;
        }

        @Override
        public String toString() {
            return "Person{" +
                    "energy=" + energy +
                    ", sleepTime=" + sleepTime +
                    ", name='" + name + '\'' +
                    '}';
        }

        private final Condition energyFullCondition = lock.newCondition();
        private final Condition energyLackCondition = lock.newCondition();
        private final Condition sleepTimeFullCondition = lock.newCondition();
        private final Condition sleepTimeLackCondition = lock.newCondition();

        public void sport(int wasteEnergy, int hours) throws InterruptedException {
            lock.lock();
            try {
                while (energy - wasteEnergy < 0)
                    energyLackCondition.await();
                while (sleepTime - hours < MIN_SLEEP_TIME)
                    sleepTimeLackCondition.await();
                energy = energy - wasteEnergy;
                sleepTime = sleepTime - hours;
                System.out.printf(this + " has done some sport,waste %d energy, %d hours \n", wasteEnergy, hours);
                energyFullCondition.signalAll();// 通知eat工作
                sleepTimeFullCondition.signalAll();// 通知sleep工作
            }  finally {
                lock.unlock();
            }
        }

        public void eat(int energyCount) throws InterruptedException {
            lock.lock();
            try {
                while (true) {
                    if (energy + energyCount > MAX_ENERGY) {// 能量过剩
                        energyCount = energy + energyCount - MAX_ENERGY;// 剩余能量下次再吃
                        energy = MAX_ENERGY;// 能量赋值为最大能量
                        energyLackCondition.signalAll();// 通知sport工作
                        energyFullCondition.await();// 等待能量消耗
                    } else {
                        energy += energyCount;
                        System.out.printf(this + " has eat  %d energy food \n", energyCount);
                        energyLackCondition.signalAll();// 通知sport工作
                        break;
                    }
                }
            }  finally {
                lock.unlock();
            }
        }

        public void sleep(int hours) throws InterruptedException {
            lock.lock();
            try {
                while (true) {
                    if (sleepTime + hours > MAX_SLEEP_TIME) {
                        hours = sleepTime + hours - MAX_SLEEP_TIME;
                        sleepTime = MAX_SLEEP_TIME;
                        sleepTimeLackCondition.signalAll();
                        sleepTimeFullCondition.await();
                    } else {
                        sleepTime += hours;
                        System.out.printf(this + " has sleep  %d hours \n", sleepTime);
                        sleepTimeLackCondition.signalAll();
                        break;
                    }
                }
            } finally {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Person person = new Person(100, 24, "小明");
        Thread t1 = new Thread(() -> {
            Random random = new Random();
            for (int i = 0; i < 10000; i++) {
                try {
                    person.sleep(random.nextInt(24));
                } catch (InterruptedException e) {
                    break;
                }
            }
        });
        Thread t2 = new Thread(() -> {
            Random random = new Random();
            for (int i = 0; i < 10000; i++) {
                try {
                    person.eat(random.nextInt(100));
                } catch (InterruptedException e) {
                    break;
                }
            }
        });

        Thread t3 = new Thread(() -> {
            Random random = new Random();
            for (int i = 0; i < 1000; i++) {
                try {
                    person.sport(random.nextInt(100), random.nextInt(8));
                } catch (InterruptedException e) {
                    break;
                }
            }
        });
        t1.start();
        t2.start();
        t3.start();

        t3.join();
        t1.interrupt();
        t2.interrupt();
        System.out.println("sport finished");
    }
}
