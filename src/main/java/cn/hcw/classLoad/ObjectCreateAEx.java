package cn.hcw.classLoad;

public abstract class ObjectCreateAEx {



    ObjectCreateAEx(){
        int a = getAV();
        System.out.println(a);
    }

    public abstract int getAV();
}
