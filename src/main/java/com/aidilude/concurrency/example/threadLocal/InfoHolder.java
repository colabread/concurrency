package com.aidilude.concurrency.example.threadLocal;

public class InfoHolder {

    //Long：需要被线程封闭存储的信息
    private final static ThreadLocal<Long> infoHolder = new ThreadLocal<>();

    /**
     * 这里虽然只将info传入了ThreadLocal，但ThreadLocal会主动将当前线程的ID存储起来
     * 此时ThreadLocal内部map的结构就是：key（ThreadID） -- value（info）
     * @param info
     */
    public static void addInfo(Long info){
        infoHolder.set(info);
    }

    /**
     * 这里虽然没有传入ThreadID，但是ThreadLocal会主动根据当前线程的ID来查询对应的info
     * @return
     */
    public static Long getInfo(){
        return infoHolder.get();
    }

    /**
     * 一定要实现一个移除info的方法
     * 因为ThreadLocal的生存周期和application一致，一直伴随着应用的启动和关闭
     * 如果不主动remove，那么知道应用关闭ThreadLocal才会清空其内部的map
     * 同理，没有传入ThreadID，也是根据当前线程的ID来取的
     */
    public static void removeInfo(){
        infoHolder.remove();
    }

}