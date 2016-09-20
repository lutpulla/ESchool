package my.eschool.web.ui.controller;

import java.io.Serializable;
import java.text.MessageFormat;
import java.util.ResourceBundle;
import javax.faces.application.Application;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author l.avakriyev
 */
public abstract class AbstractController implements Serializable {

    protected SessionController sessionController;
    protected AppController appController;
    
    public AbstractController() {
    }

    public SessionController getSessionController() {
        if (sessionController == null) {
            sessionController = (SessionController) getBean("sessionController");
        }
        return sessionController;
    }

    public AppController getAppController() {
        if (appController == null) {
            appController = (AppController) getBean("appController");
        }
        return appController;
    }

    public FacesContext getFacesContext() {
        return FacesContext.getCurrentInstance();
    }

    protected Object getBean(String name) {
        FacesContext fc = getFacesContext();
        return fc.getApplication().getELResolver().getValue(fc.getELContext(), null, name);
    }

    public HttpServletRequest getRequest() {
        return (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
    }

    public HttpServletResponse getResponse() {
        return (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
    }

    public Application getApplication() {
        return FacesContext.getCurrentInstance().getApplication();
    }

    public void addErrorMessage(String id, String msg) {
        FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
        getFacesContext().addMessage(id, facesMsg);
    }

    public void addSuccessMessage(String id, String msg) {
        FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
        getFacesContext().addMessage(id, facesMsg);
    }

    public void addWarningMessage(String id, String msg) {
        FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_WARN, msg, null);
        getFacesContext().addMessage(id, facesMsg);
    }

    public void addErrorBundleMessage(String id, String key, Object... arguments) {
        FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, mainBundleKeyFormat(key, arguments), null);
        getFacesContext().addMessage(id, facesMsg);
    }

    public void addSuccessBundleMessage(String id, String key, Object... arguments) {
        FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, mainBundleKeyFormat(key, arguments), null);
        getFacesContext().addMessage(id, facesMsg);
    }

    public void addWarningBundleMessage(String id, String key, Object... arguments) {
        FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_WARN, mainBundleKeyFormat(key, arguments), null);
        getFacesContext().addMessage(id, facesMsg);
    }
    
    protected ResourceBundle getMainBundle() {
        return ResourceBundle.getBundle("bundle.messages", getFacesContext().getViewRoot().getLocale());
    }

    protected String mainBundleKeyFormat(String key, Object... arguments) {
        return MessageFormat.format(getMainBundle().getString(key), arguments);
    }
    
}
