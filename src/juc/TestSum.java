package juc;

public class TestSum {
    private  int count=0;
    public void sum(){
        for (int i = 0; i < 1000000; i++) {
            count++;
        }
    }
    public int getCount(){
        return count;
    }
}
