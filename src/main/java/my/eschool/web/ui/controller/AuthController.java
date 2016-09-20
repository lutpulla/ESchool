package my.eschool.web.ui.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpSession;
import my.eschool.bom.entity.User;
import my.eschool.bom.spring.service.MyEntityService;
import my.eschool.web.ui.util.StrUtil;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 *
 * @author l.avakriyev
 */
@Controller(value = "authController")
@Scope("session")
public class AuthController extends AbstractController {

    private static final Logger LOGGER = Logger.getLogger(AuthController.class.getName());
    private String login;
    private String password;
    private boolean authenticate = false;

    public AuthController() {
    }

    public boolean isAuthenticate() {
        return authenticate;
    }

    public void setAuthenticate(boolean authenticate) {
        this.authenticate = authenticate;
    }

    public String checkAuthenticate() {
        try {
            MyEntityService service = getSessionController().getService();
            Map<String, Object> map = new HashMap<>();
            map.put("name", login);
            User user = service.find(User.class, map);
            if (user != null) {
                String md5 = StrUtil.toMD5(password);
                if (login.equals(user.getName()) && md5 != null && md5.equals(user.getPassword())) {
                    this.authenticate = true;
                    this.sessionController.onAuthentication(user);
                    return "/view/welcome.htm?faces-redirect=true";
                }
            }
            addWarningBundleMessage(null, "AuthenticationFailed");
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "user=" + this.login, ex);
            addErrorBundleMessage(null, "SystemError");
        }
        return "/login.htm";
    }

    public void exit(HttpSession session) {
        if(session == null) {
            session = (HttpSession) getFacesContext().getExternalContext().getSession(false);
        }
        this.authenticate = false;
        this.sessionController.onLogout();
        session.invalidate();
    }

    public String getLogin() {
        return this.login;
    }

    public void setLogin(String login) {
        this.login = (login != null ? login.trim() : "");
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = (password != null ? password : "");
    }

}
