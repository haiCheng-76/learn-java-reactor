package website.longhc;

import cn.hutool.core.text.StrFormatter;
import org.junit.Test;
import reactor.core.publisher.Flux;

public class FluxTest {


    /**
     * error 和 complete 是互斥的，只能有一个存在
     */
    @Test
    public void test1() {
        Flux<Integer> flux = Flux.just(1, 2, 3, 4, 5, 6, 7)
                .map(item -> {
                    if (item < 3) {
                        return item;
                    }
                    throw new RuntimeException("error");
                });
        flux.subscribe(
                System.out::println,
                err -> System.err.println(err.getMessage()),
                () -> System.out.println("OK ")
        );
    }

    @Test
    public void test2() {
        Flux<Integer> ints = Flux.range(1, 4);
        ints.subscribe(System.out::println,
                error -> System.err.println("Error " + error),
                () -> System.out.println("Done"),
                sub -> sub.request(10));
    }

    @Test
    public void test3() {
        // 第一个函数是指定初始值，第二个函数是循环累加
        Flux<Object> flux = Flux.generate(
                () -> 0,
                (current, sink) -> {
                    sink.next(StrFormatter.format("3 * {} = {}", current, 3 * current));
                    if (current == 10) {
                        sink.complete();
                    }
                    return current + 1;
                }
        );

        flux.subscribe(System.out::println);
    }
}
