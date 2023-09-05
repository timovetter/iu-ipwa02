import entities.User;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;

import java.io.Serializable;

@Named
@SessionScoped
public class UserContext implements Serializable {
    private User user;
    public UserContext() {}

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
