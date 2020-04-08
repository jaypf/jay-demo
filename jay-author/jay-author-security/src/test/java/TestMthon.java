import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import redis.clients.jedis.Jedis;
import org.junit.Test;
/**
 * @ClassName TestMthon
 * @Description TODO
 * @Author Jay.Jia
 * @Date 2020/4/6 19:31
 * @Version 1.0
 */
public class TestMthon {
    public static  void testJedis(){
        //1 创建Jedis对象  需要指定连接的地址和端口
        Jedis jedis = new Jedis("localhost",6379);
        //2 直接操作redis set
        jedis.set("key1", "value1");
        System.out.println(jedis.get("key1"));
        //3 关闭连接
        jedis.close();
    }

    public static void main(String[] args) {
        testJedis();
    }

    @Test
    public void test1(){
        String a = "{bcrypt}" + new BCryptPasswordEncoder().encode("123456");
        System.out.println(a);
    }
}
