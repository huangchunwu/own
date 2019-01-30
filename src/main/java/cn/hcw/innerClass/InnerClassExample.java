package cn.hcw.innerClass;

/**
 * Created by huangchunwu on 2019/1/30.
 */
public class InnerClassExample {


    InnerClassExample(){
        System.out.println("1");
    }

    public void getInstance(){
        new inner();
    }

    static class inner{
        //private final static InnerClassExample e = new InnerClassExample();
            inner(){
                System.out.println("2");
            }

        public InnerClassExample getInstance(){
            return  null;
        }
    }



}
