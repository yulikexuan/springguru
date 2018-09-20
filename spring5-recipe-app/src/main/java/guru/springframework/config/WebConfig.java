//: guru.springframework.config.WebConfig.java


package guru.springframework.config;


import guru.springframework.domain.Recipe;
import guru.springframework.services.IRecipeReactiveService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;


@Configuration
public class WebConfig {

    /*
     * Add a new config bean that's returing back a router function that gets it
     * into the reactive, the webflux context as a route
     * Whenever we do a hit against api recipes we are going to manage a server
     * response with an ok status, content type of JSON, and for the body we are
     * going to call out the RecipeReactiveService and get a flux of recipes and
     * we also have to tell the body function that we are sending it the class of
     * recipes and then it is going to go ahead and render that.
     */
    @Bean
    public RouterFunction<?> routes(IRecipeReactiveService recipeReactiveService) {
        return RouterFunctions.route(GET("/api/recipes"), serverRequest -> ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(recipeReactiveService.getAllRecipes(), Recipe.class));
    }

}///~