package com.aidilude.concurrency.example.count;

import com.aidilude.concurrency.annotation.UnThreadSafe;
import com.aidilude.concurrency.dto.Temp;
import com.aidilude.concurrency.thread.GainRedirectTask;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;
import java.util.concurrent.*;

@Slf4j
@UnThreadSafe
public class CountExample2 {

    //总请求数
    public static Integer clientTotal = 20;

    //总并发数
    public static Integer threadTotal = 20;

    public static Integer count = 0;

    public static ConcurrentHashMap result = new ConcurrentHashMap();

    private static void add(){
        count++;
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        long time1 = System.currentTimeMillis();
        /**
         * 创建线程池，并发量最大为5
         * LinkedBlockingDeque，表示执行任务或者放入队列
         */
        ThreadPoolExecutor tpe = new ThreadPoolExecutor(50, 50, 0,
                TimeUnit.SECONDS, new LinkedBlockingDeque<Runnable>(),
                new ThreadPoolExecutor.CallerRunsPolicy());
        String nameParams = "arcange,raphaelle,lemooljiang,justyy,elviento,yehey,shenchensucc,haiyangdeperci,liumei,jadabug,waiyee422,yellowbird,superbing,xiaoshancun,dailystats,martusamak,pataty69,jianan,cnbuddy,nileelily,jacktan,anxin,angelina6688,steemitag,iguazi123,osobiggie,woolfe19861008,youandme,dailychina,dongfengman,micaelacf,shentrading,also.einstein,ethanlee,sweetpee,lilypang22,enlighted,hmayak,sweet-jenny8,kidsreturn,council,sbi5,team-cn,coder-bts,fiveguys,partiko,moonbbs,bonefish,chilis,laiyuehta";
        String[] names = nameParams.split(",");
        //存储线程的返回值
        List<Future<Temp>> results = new ArrayList<>();
//        List<Future<String>> results = new LinkedList<Future<String>>();
        for (String name : names) {
            String url = "https://steemitimages.com/u/" + name + "/avatar/small";
            GainRedirectTask task = new GainRedirectTask(name, url);
            //调用submit可以获得线程的返回值
            Future<Temp> result = tpe.submit(task);
//            temp.put(name, result.get());
            results.add(result);
        }
        //此函数表示不再接收新任务，
        //如果不调用，awaitTermination将一直阻塞
        tpe.shutdown();
        System.out.println("*******************************");
//        temp.forEach((key, value) -> {
//            System.out.println(key + "：" + value);
//        });
        for (Future<Temp> tempFuture : results) {
            System.out.println(JSON.toJSONString(tempFuture.get()));
        }
        System.out.println("*******************************");
//        System.out.println(temp.size());
        System.out.println(results.size());
        long time2 = System.currentTimeMillis();
        System.out.println("执行时间（单位：s）：" + (time2 - time1) / 1000.0);
    }

    public static String getRedirectUrl(String path) throws Exception {
        HttpURLConnection conn = (HttpURLConnection)new URL(path).openConnection();
        conn.setInstanceFollowRedirects(false);
        conn.setConnectTimeout(3000);
        return conn.getHeaderField("Location");
    }

}
