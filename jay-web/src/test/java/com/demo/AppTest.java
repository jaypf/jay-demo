package com.demo;

import com.alibaba.druid.filter.config.ConfigTools;
import org.junit.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Supplier;

import static org.junit.Assert.assertTrue;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }

    /**
     * 功能相当于：
     *  在druid的jar中执行java -cp druid-1.1.17.jar com.alibaba.druid.filter.config.ConfigTools root(需要加密的数据库密码)
     * @Param [] 
     * @Author Jay.Jia    
     * @Date 2020/4/25 18:30 
     * @return void
     **/
    @Test
    public void getDbPassWord() {
        try {
            // 明文密码
            String password = "root";

            System.out.println("密码[ "+password+" ]的加密信息如下：\n");

            // 获取密码对，参数不能小于512，否则会报错
            String [] keyPair = ConfigTools.genKeyPair(512);
            // 私钥
            String privateKey = keyPair[0];
            // 公钥
            String publicKey = keyPair[1];
            // 用私钥加密后的密文
            password = ConfigTools.encrypt(privateKey, password);

            System.out.println("privateKey:"+privateKey);
            System.out.println("publicKey:"+publicKey);
            System.out.println("password:"+password);
            // 通过公钥和密文密码获取密码明文
            String decryptPassword = ConfigTools.decrypt(publicKey, password);
            System.out.println("decryptPassword："+decryptPassword);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void futureTest() throws Throwable, ExecutionException {
        // 两个线程的线程池
        ExecutorService executor = Executors.newFixedThreadPool(2);
        //jdk1.8之前的实现方式
        CompletableFuture<String> future = CompletableFuture.supplyAsync(new Supplier<String>() {
            @Override
            public String get() {
                System.out.println("task started!");
                try {
                    //模拟耗时操作
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return "task finished!";
            }
        }, executor);

        //采用lambada的实现方式
        future.thenAccept(e -> System.out.println(e + " ok"));

        Thread.sleep(8000);
        System.out.println("futureTest thread is running");
    }
}
