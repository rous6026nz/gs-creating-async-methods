/**
 * APPRUNNER CLASS:
 * Class to injects and calls the service
 * multiple time to demonstrate the
 * method is executing asynchronously. 
 */

package hello;

// Class dependencies.
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

/**
 * @Component Annotation:
 * Indicates that an annotated class is a "component". 
 * Such classes are considered as candidates for 
 * auto-detection when using annotation-based configuration
 * and classpath scanning.
 */
@Component
public class AppRunner implements CommandLineRunner {

    // Class bindings.
    private static final Logger logger = LoggerFactory.getLogger(AppRunner.class);
    private final GitHubLookupService gitHubLookupService;

    public AppRunner(GitHubLookupService gitHubLookupService) {
        this.gitHubLookupService = gitHubLookupService;
    }

    /**
     * @Override Annotation:
     * Indicates that a method declaration is 
     * intended to override a method declaration 
     * in a supertype.
     */
    @Override
    public void run(String... args) throws Exception {

        // Start the clock.
        long start = System.currentTimeMillis();

        // Kick off multiple, asynchronous lookups.
        CompletableFuture<User> page1 = gitHubLookupService.findUser("PivotalSoftware");
        CompletableFuture<User> page2 = gitHubLookupService.findUser("CloudFoundry");
        CompletableFuture<User> page3 = gitHubLookupService.findUser("Spring-Projects");

        // Wait until they are all done.
        CompletableFuture.allOf(page1, page2, page3).join();

        // Print results, including elapsed time.
        logger.info("Elapsed time: " + (System.currentTimeMillis() - start));
        logger.info("--> " + page1.get());
        logger.info("--> " + page2.get());
        logger.info("--> " + page3.get());

    }
}