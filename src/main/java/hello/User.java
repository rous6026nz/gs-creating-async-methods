/**
 * USER CLASS:
 * Resource representation class to
 * model the user data we recieve from
 * the API.
 */

package hello;

// Class dependencies.
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @JsonIgnoreProperties Annotation:
 * Annotation that can be used to either 
 * suppress serialization of properties 
 * (during serialization), or ignore processing 
 * of JSON properties read (during deserialization).
 */
@JsonIgnoreProperties(ignoreUnknown=true)
public class User {

    // Class bindings.
    private String name;
    private String blog;

    /**
     * CLASS ACCESSORS:
     * Getters.
     */
    public String getName() {
        return name;
    }

    public String getBlog() {
        return blog;
    }

    // Setters.
    public void setName(String name) {
        this.name = name;
    }

    public void setBlog(String blog) {
        this.blog = blog;
    }

    /**
     * @Override Annotation:
     * Indicates that a method declaration is 
     * intended to override a method declaration 
     * in a supertype.
     */
    @Override
    public String toString() {
        return "User [name=" + name + ", blog=" + blog + "]";
    }
}