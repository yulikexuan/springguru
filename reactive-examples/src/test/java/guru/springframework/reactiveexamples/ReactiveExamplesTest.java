//: guru.springframework.reactiveexamples.ReactiveExamplesTest.java


package guru.springframework.reactiveexamples;


import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.concurrent.CountDownLatch;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;


@Slf4j
public class ReactiveExamplesTest {

    private Person michael = new Person("Michael", "Weston");
    private Person fiona = new Person("Fiona", "Glenanne");
    private Person sam = new Person("Sam", "Axe");
    private Person jesse = new Person("Jesse", "Porter");
    private Person mike = new Person("Mike", "Lee");
    private Person berry = new Person("Berry", "Lin");

    private Flux<Person> peopleFlux;

    @Before
    public void setUp() throws Exception {
        /*
         * A Reactive Streams Publisher with rx operators that emits 0 to N
         * elements, and then completes (successfully or with an error)
         */
        this.peopleFlux = Flux.just(this.mike, this.berry, this.michael,
                this.fiona, this.jesse, this.sam);
    }

    @Test
    public void mono_Block_Test() {

        // Given

        /* Create new person mono
         * A Reactive Streams Publisher with basic rx operators that completes
         * successfully by emitting an element, or with an error.
         */
        Mono<Person> personMono = Mono.just(this.michael);

        // When

        //get person object from mono publisher
        Person person = personMono.block();

        // Then
        assertThat(person, is(this.michael));
        log.debug(person.sayMyName());
    }

    @Test
    public void mono_Transform_Test() {

        // Given
        Mono<Person> personMono = Mono.just(this.fiona);

        // When
        PersonCommand command = personMono
                .map(p -> new PersonCommand(p)).block();

        // Then
        assertThat(command.getFirstName(), is(this.fiona.getFirstName()));
        assertThat(command.getLastName(), is(this.fiona.getLastName()));
    }

    @Test(expected = NullPointerException.class)
    public void mono_Filter_Test() {

        // Given
        Mono<Person> personMono = Mono.just(this.sam);

        // When
        Person jesse = personMono
                .filter(p -> p.getFirstName().equalsIgnoreCase(
                        this.jesse.getFirstName())).block();

        // Then
        log.debug(jesse.sayMyName());
    }

    @Test
    public void flux_Subscribe_Test() {

        // When and Then

        /*
         * Subscribe a Consumer to this Flux that will consume all the elements
         * in the sequence. It will request an unbounded demand (Long.MAX_VALUE)
         */
        this.peopleFlux.subscribe(p -> log.debug(p.sayMyName()));
    }

    @Test
    public void flux_Transform_Test() {

        // When and Then
        this.peopleFlux
                .map(p -> new PersonCommand(p))
                .subscribe(p -> log.debug(p.toString()));
    }

    @Test
    public void flux_Filter_Test() {

        // When and Then
        this.peopleFlux
                .filter(p -> p.getFirstName().equalsIgnoreCase(
                        this.fiona.getFirstName()))
                .subscribe(p -> log.debug(p.sayMyName()));
    }

    @Test
    public void flux_Delay_Elements_No_Output_Test() throws Exception {

        // When and Then
        this.peopleFlux
                .delayElements(Duration.ofSeconds(1))
                .subscribe(p -> log.debug(p.sayMyName()));

        // Because of the non-block behavior of Flux, this test method does not
        // have any output unless Thread.sleep is used like the code below
        // Thread.sleep(10000);
    }

    @Test
    public void flux_Delay_Elements_Test() throws Exception {

        // Given

        /*
         * doOnComplete(countDownLatch::countDown) means countDown method can
         * be executed only once, so init arg of CountDownLatch should be 1
         */
        CountDownLatch countDownLatch = new CountDownLatch(1);

        // When and Then
        this.peopleFlux
                .delayElements(Duration.ofSeconds(2))
                .doOnComplete(countDownLatch::countDown)
                .subscribe(p -> log.debug(p.sayMyName()));

        countDownLatch.await();
    }

    @Test
    public void name() throws Exception {

        // Given

        /*
         * doOnComplete(countDownLatch::countDown) means countDown method can
         * be executed only once, so init arg of CountDownLatch should be 1
         */
        CountDownLatch countDownLatch = new CountDownLatch(1);

        // When and Then
        this.peopleFlux
                .delayElements(Duration.ofSeconds(2))
                .filter(p -> p.getFirstName().contains("i"))
                .doOnComplete(countDownLatch::countDown)
                .subscribe(p -> log.debug(p.sayMyName()));

        countDownLatch.await();
    }

}///~