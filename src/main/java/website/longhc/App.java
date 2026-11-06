package website.longhc;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        Flux<String> just = Flux.just("1", "2", "3", "4");

        List<String> list = List.of("1", "2", "3");
        Flux<String> stringFlux = Flux.fromIterable(list);

        Mono<String> empty = Mono.empty();

        Mono<String> just1 = Mono.just("123");


        Flux<Integer> integerFlux = Flux.range(1, 5).map(item -> {
            if (item < 3) {
                return item;
            }
            throw new RuntimeException("123");
        });

        // // 第一次订阅
        integerFlux.subscribe(item -> System.out.println("first "+ item),
                err -> System.err.println(err.getMessage()),
                () -> System.out.println("OK")
        );

        System.out.println();

        // 第二次订阅
//        integerFlux.subscribe(item -> System.out.println("second "+ item));
    }
}
