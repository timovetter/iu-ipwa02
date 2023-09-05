import dao.UserDAO;
import entities.User;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.component.UIComponent;
import jakarta.faces.component.UIInput;
import jakarta.faces.context.FacesContext;
import jakarta.faces.event.AbortProcessingException;
import jakarta.faces.event.ComponentSystemEvent;
import jakarta.faces.validator.ValidatorException;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@Named
@ViewScoped
public class LoginController implements Serializable {
    private String username;
    private String password;
    private User user;
    private final UserDAO userDAO;

    @Inject
    private UserContext userContext;

    public LoginController() {
        this.userDAO = new UserDAO();
    }

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

    public User getUser() {
        return user;
    }

    public String submitLogin() {
        if (user == null) {
            return null;
        }
        userContext.setUser(user);
        return "/ghost-net/list.xhtml?faces-redirect=true";
    }

    public void validateLogin(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        Map<String, String> filter = new HashMap<>();
        filter.put("username", username);
        filter.put("password", (String) value);

        try {
            user = this.userDAO.findOne(filter);
        } catch (NoResultException e) {
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Falscher Login!", null));
        }
    }

    public void postValidateName(ComponentSystemEvent event) throws AbortProcessingException {
        UIInput name = (UIInput) event.getComponent();
        username = (String) name.getValue();
    }
}
