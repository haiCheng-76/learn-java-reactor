package website.longhc;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * Hello world!
 */
@Slf4j
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
        integerFlux.subscribe(item -> log.info("first {}", item),
                err -> log.error("Reactive stream failed", err),
                () -> log.info("Completed")
        );

        // 第二次订阅
//        integerFlux.subscribe(item -> log.info("second {}", item));
    }
}
