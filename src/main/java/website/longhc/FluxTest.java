package website.longhc;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

@Slf4j
public class FluxTest {

    public static void main(String[] args) {
        Flux<Integer> flux = Flux.just(1, 2, 3, 4, 5);
        flux.subscribe(
                item -> log.info("item: {}", item),
                err -> log.warn("error: {}", err.getMessage()),
                () -> log.info("Done"),
                sub -> sub.request(3)
        );

    }
}
