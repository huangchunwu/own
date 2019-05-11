package cn.hcw.gc;


/**
 * 一直 full  gc
 */
public class GcExample {

    private static final int _1MB=1024*1024;

    public static void main(String[] args) {

        while (true){
            byte[] b = new byte[4*_1MB];
            b=null;
            System.gc();
        }

    }

}
