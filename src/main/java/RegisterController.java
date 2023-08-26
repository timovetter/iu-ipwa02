import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;

import java.io.Serializable;

@Named
@ViewScoped
public class RegisterController implements Serializable  {
    private String username;
    private String phone;
    private String password;
    private String type;
    private final UserDAO userDAO;

    public RegisterController() {
        this.userDAO = new UserDAO();
    }

    public void register() {
        UserType ut = UserType.REPORT;
        if (this.type.equals("SALVAGE")) {
            ut = UserType.SALVAGE;
        }
        User newUser = new User(this.username, this.phone, ut, this.password);
        this.userDAO.add(newUser);
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
