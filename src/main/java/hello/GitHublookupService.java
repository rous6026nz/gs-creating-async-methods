/**
 * SERVICE CLASS:
 * GitHub Lookup Service class
 * to find user information.
 */

package hello;

// Class dependencies.
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.CompletableFuture;

/**
 * @Service Annotation:
 * Makes the class a candidate for Spring’s 
 * component scanning to detect it and add 
 * it to the application context.
 */
@Service
public class GitHubLookupService {

    // Class bindings.
    private static Logger logger = LoggerFactory.getLogger(GitHubLookupService.class);
    private final RestTemplate restTemplate;

    public GitHubLookupService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    /**
     * @Async Annotation:
     * Annotation that marks a method as a 
     * candidate for asynchronous execution. 
     * Can also be used at the type level, 
     * in which case all of the type's methods 
     * are considered as asynchronous.
     * Indicates a method will run on a
     * separate thread.
     */
    @Async
    public CompletableFuture<User> findUser(String user) throws InterruptedException {
        logger.info("Looking up " + user); 
        String url = String.format("https://api.github.com/users/%s", user);

        /**
         * Use RestTemplate to invoke a remote REST 
         * point (api.github.com/users/), and then 
         * convert the answer into a User object.
         */
        User results = restTemplate.getForObject(url, User.class);

        // Artificial delay of 1s for demonstration purposes.
        Thread.sleep(1000L);
        /**
         * The method’s return type is CompletableFuture<User> 
         * instead of User, a requirement for any asynchronous service. 
         * This code uses the completedFuture method to return a 
         * CompletableFuture instance which is already completed 
         * with result of the GitHub query.
         */
        return CompletableFuture.completedFuture(results);
    }
}