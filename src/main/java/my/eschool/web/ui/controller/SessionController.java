package my.eschool.web.ui.controller;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import my.eschool.bom.entity.User;
import my.eschool.bom.spring.service.MyEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 *
 * @author l.avakriyev
 */
@Controller(value = "sessionController")
@Scope("session")
public class SessionController extends AbstractController {
    
    @Autowired
    private MyEntityService service;
    
    private static final Logger LOGGER = Logger.getLogger(SessionController.class.getName());
    private User user;
    
    @PostConstruct
    public void init() {
        this.user = null;
    }

    public String getUserName() {
        if (this.user == null) {
            return null;
        }
        return this.user.getName();
    }
    
    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    void onAuthentication(User user) throws Exception {
        this.user = user;
        LOGGER.log(Level.INFO, "user={0}; Login.", new Object[] {getUserName()} );
    }
    
    void onLogout() {
        LOGGER.log(Level.INFO, "user={0}; Logout.", new Object[] {getUserName()} );
        this.user = null;
    }

    public MyEntityService getService() {
        return service;
    }

}
