/**
 * APPLICATION CLASS:
 * Main class to execute
 * the application.
 */

package hello;

// Class dependencies
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

/**
 * @SpringBootApplication Annotation:
 * Indicates a configuration class that declares 
 * one or more @Bean methods and also triggers 
 * auto-configuration and component scanning.
 */
@SpringBootApplication

/**
 * @EnableAsync Annotation:
 * Enables Spring's asynchronous method execution 
 * capability, similar to functionality found 
 * in Spring's <task:*> XML namespace.
 */
@EnableAsync
public class Application {
    public static void main(String[] args) {
        // Close application context to shut down the custom ExecutorService.
        SpringBootApplication.run(Application.class, args).close();
    }

    /**
     * @Bean Annotation:
     * Indicates that a method produces a 
     * bean to be managed by the Spring container.
     */
    @Bean
    public Executor taksExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(2);
        executor.setMaxPoolSize(2);
        executor.setQueueCapacity(500);
        executor.setThreadNamePrefix("GithubLookup-");
        executor.initialize();
        return executor;
    }
}