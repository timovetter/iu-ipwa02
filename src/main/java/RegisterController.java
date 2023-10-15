import dao.UserDAO;
import entities.User;
import entities.UserType;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.component.UIComponent;
import jakarta.faces.component.UIInput;
import jakarta.faces.context.FacesContext;
import jakarta.faces.event.ComponentSystemEvent;
import jakarta.faces.validator.ValidatorException;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import javax.persistence.NoResultException;
import java.io.Serializable;
import java.util.HashMap;

@Named
@ViewScoped
public class RegisterController implements Serializable  {
    private String username;
    private String phone;
    private String password;
    private String type;
    private final UserDAO userDAO;
    @Inject
    private UserContext userContext;

    public RegisterController() {
        this.userDAO = new UserDAO();
    }

    public String register() {
        UserType ut = UserType.REPORT;
        if (this.type.equals("SALVAGE")) {
            ut = UserType.SALVAGE;
        }

        User newUser = new User(this.username, this.phone, ut, this.password);
        this.userDAO.add(newUser);
        this.userContext.setUser(newUser);
        return "/ghost-net/list.xhtml?faces-redirect=true";
    }

    public void validateUsername(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        HashMap<String, String> filter = new HashMap<>();
        filter.put("username", (String) value);

        try {
            this.userDAO.findOne(filter);
        } catch (NoResultException e) {
            // this exception is good because there are no users with the same username
            return;
        }

        throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Registrierung fehlgeschlagen. User mit gleichem Username existiert bereits", null));
    }

    public void validatePhone(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        if ((value == null || ((String) value).isEmpty()) && this.type.equals("SALVAGE")) {
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Telefonnummer ist bei einer bergenden Person ein Pflichtfeld", null));
        }
    }

    public void postValidateType(ComponentSystemEvent event) throws ValidatorException {
        UIInput name = (UIInput) event.getComponent();
        type = (String) name.getValue();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
