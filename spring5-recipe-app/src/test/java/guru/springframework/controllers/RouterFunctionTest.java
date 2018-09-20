//: guru.springframework.controllers.RouterFunctionTest.java


package guru.springframework.controllers;


import guru.springframework.config.WebConfig;
import guru.springframework.domain.Recipe;
import guru.springframework.services.IRecipeReactiveService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.server.RouterFunction;
import reactor.core.publisher.Flux;

import static org.mockito.Mockito.when;


public class RouterFunctionTest {

    WebTestClient webTestClient;

    @Mock
    IRecipeReactiveService recipeReactiveService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        WebConfig webConfig = new WebConfig();
        RouterFunction<?> routerFunction = webConfig.routes(this.recipeReactiveService);
        this.webTestClient = WebTestClient.bindToRouterFunction(routerFunction).build();
    }

    @Test
    public void able_To_Get_A_Flux_Without_Any_Data() {

        // Given
        when(this.recipeReactiveService.getAllRecipes()).thenReturn(Flux.just());

        // When
        this.webTestClient.get().uri("/api/recipes").accept(MediaType.APPLICATION_JSON).exchange() //Perform the exchange without a request body
                .expectStatus().isOk();

    }

    @Test
    public void able_To_Get_A_Flux_With_Data() {

        // Given
        when(this.recipeReactiveService.getAllRecipes()).thenReturn(Flux.just(new Recipe()));

        // When
        this.webTestClient.get().uri("/api/recipes").accept(MediaType.APPLICATION_JSON).exchange() //Invoke the web service to perform the exchange without a request body
                .expectStatus().isOk().expectBodyList(Recipe.class);
    }

}///~