import jakarta.faces.application.FacesMessage;
import jakarta.faces.component.UIComponent;
import jakarta.faces.component.UIInput;
import jakarta.faces.context.FacesContext;
import jakarta.faces.event.AbortProcessingException;
import jakarta.faces.event.ComponentSystemEvent;
import jakarta.faces.validator.ValidatorException;
import jakarta.inject.Named;
import jakarta.faces.view.ViewScoped;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.io.Serializable;

@Named
@ViewScoped
public class LoginController implements Serializable {
    private String username;
    private String password;
    private final static EntityManagerFactory emf = Persistence.createEntityManagerFactory("ghost_net");

    public LoginController() {}

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void login(FacesContext ctx, UIComponent component, Object obj) throws ValidatorException {
        EntityManager manager = emf.createEntityManager();
        Query q = manager.createQuery("select u from User u where username = " + this.username);
        User u = (User) q.getSingleResult();
        if (u == null) {
            throw new ValidatorException(new FacesMessage("Login falsch!"));
        }
    }

    public void postValidateName(ComponentSystemEvent event) throws AbortProcessingException {
        UIInput name = (UIInput) event.getComponent();
        this.username = (String) name.getValue();
    }

}
