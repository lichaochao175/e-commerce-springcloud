package service;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

/**
 * @author lichaochao
 * @data 2022/4/12 8:27 PM
 **/

@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class PredicateTest {


    public static List<String> MICRO_SERVICE = Arrays.asList(
            "nacos", "authority", "gateway", "ribbon", "feign", "hystrix", "e-commerce"
    );

    /**
     * <h2>test 方法主要用于参数符不符合规则, 返回值是 boolean</h2>
     */
    @Test
    public void testPredicateTest() {

        Predicate<String> letterLengthLimit = s -> s.length() > 5;
        MICRO_SERVICE.stream().filter(letterLengthLimit).forEach(System.out::println);
    }

}
