package my.eschool.web.ui.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import my.eschool.bom.entity.Student;
import my.eschool.bom.spring.service.MyEntityService;
import my.eschool.web.ui.util.StrUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 *
 * @author l.avakriyev
 */
@Controller(value = "studentsController")
@Scope("session")
public class StudentsController extends AbstractController {
    
    @Autowired
    private MyEntityService service;
    
    private static final Logger LOGGER = Logger.getLogger(StudentsController.class.getName());
    private String userName;
    private List<Student> objectList;
    private Map<Long, Boolean> checked = new HashMap<>();
    private Student newObject;
    private String birthDay;
    
    @PostConstruct
    public void init() {
        newObject = new Student();
        objectList = new ArrayList<>();
        userName = getSessionController().getUserName();
    }
    
    public void refresh() {
        try {
            objectList.clear();
            List<Student> list = service.getAll(Student.class, "id");
            objectList.addAll(list);
        } catch (Exception ex) {
            addErrorMessage(null, "Ошибка: " + ex.toString());
            LOGGER.log(Level.SEVERE, "user=" + userName, ex);
        }
    }
    
    public void add() {
        try {
            if (newObject.getFirstName() == null || newObject.getFirstName().trim().isEmpty()) {
                addErrorMessage(null, "Обязательное поле: Имя");
                return;
            }
            newObject.setBirthDay(StrUtil.strToDate(birthDay));
            Long objectId = service.create(newObject);
            cancelAdd();
            LOGGER.log(Level.INFO, "user={0}, objectId={1}; Object added.", new Object[] {userName, objectId});
        } catch (Exception ex) {
            addErrorMessage(null, "Ошибка: " + ex.toString());
            LOGGER.log(Level.SEVERE, "user=" + userName, ex);
        }
    }
    
    public void cancelAdd() {
        newObject = new Student();
        birthDay = null;
    }
    
    public void delete() {
        try {
            cancelAdd();
            boolean deleted = false;
            for (Long id : checked.keySet()) {
                if (checked.get(id)) {
                    for (Student object : objectList) {
                        if (id.equals(object.getId())) {
                            service.delete(object);
                            LOGGER.log(Level.INFO, "user={0}, objectId={1}; Object deleted.", new Object[] {userName, object.getId()});
                            deleted = true;
                            break;
                        }
                    }
                }
            }
            if (!deleted) {
                addWarningMessage(null, "Необходимо выбрать учащихся!");
            }
        } catch (Exception ex) {
            addErrorMessage(null, "Ошибка: " + ex.toString());
            LOGGER.log(Level.SEVERE, "user=" + userName, ex);
        }
    }
    
    public List<Student> getObjectList() {
        return objectList;
    }

    public Student getNewObject() {
        return newObject;
    }

    public void setNewObject(Student newObject) {
        this.newObject = newObject;
    }

    public String getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(String birthDay) {
        this.birthDay = birthDay;
    }

    public Map<Long, Boolean> getChecked() {
        return checked;
    }

    public void setChecked(Map<Long, Boolean> checked) {
        this.checked = checked;
    }

}
