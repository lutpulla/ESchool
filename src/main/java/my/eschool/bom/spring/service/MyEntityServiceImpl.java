package my.eschool.bom.spring.service;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import my.eschool.bom.entity.MyEntity;
import my.eschool.bom.spring.dao.MyEntityDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author l.avakriyev
 */
@Service
public class MyEntityServiceImpl implements MyEntityService, Serializable {

    @Autowired
    private MyEntityDAO objectDAO;
 
    @Override
    @Transactional
    public <T extends MyEntity> T find(Class<T> cl, String field, Object value) throws Exception {
        String hql = "from " + cl.getSimpleName() + " e where e." + field + "=:value";
        Map params = new HashMap<>();
        params.put("value", value);
        List<T> listObject = objectDAO.query(hql, params, null);
        if (listObject == null || listObject.isEmpty()) {
            return null;
        }
        return listObject.get(0);
    }

    @Override
    @Transactional
    public <T extends MyEntity> List<T> findAll(Class<T> cl, String field, Object value, String orderBy) throws Exception {
        String hql = "from " + cl.getSimpleName() + " e where e." + field + "=:value";
        Map params = new HashMap<>();
        params.put("value", value);
        if (orderBy != null && !orderBy.trim().isEmpty()) {
            hql = hql + " order by " + orderBy;
        }
        return objectDAO.query(hql, params, null);
    }

    @Override
    @Transactional
    public <T extends MyEntity> T find(Class<T> cl, Map<String, Object> params) throws Exception {
        String hql = "from " + cl.getSimpleName() + " e";
        if (params != null && !params.isEmpty()) {
            hql = hql + " where ";
            int i = 1;
            Set keySet = params.keySet();
            for (String key : params.keySet()) {
                hql = hql + " e." + key + "=:" + key;
                if (i < keySet.size()) {
                    hql = hql + " and ";
                }
                i++;
            }
        }
        List<T> listObject = objectDAO.query(hql, params, null);
        if (listObject == null || listObject.isEmpty()) {
            return null;
        }
        return listObject.get(0);
    }

    @Override
    @Transactional
    public <T extends MyEntity> List<T> getAll(Class<T> cl, String orderBy) throws Exception {
        String hql = "from " + cl.getSimpleName();
        if (orderBy != null && !orderBy.trim().isEmpty()) {
            hql = hql + " order by " + orderBy;
        }
        return objectDAO.query(hql, null, null);
    }

    @Override
    @Transactional
    public <T extends MyEntity> List<T> query(String hql, Map<String, Object> params, int[] range) {
        List<T> objectList = objectDAO.query(hql, params, range);
        return objectList;
    }

    @Override
    @Transactional
    public Long count(String hql, Map<String, Object> params) {
        return (Long) objectDAO.query("select count(*) " + hql, params, null).get(0);
    }

    @Override
    @Transactional
    public <T extends MyEntity> T read(Class<T> cl, Long id) throws Exception {
        return objectDAO.read(cl, id);
    }

    @Override
    @Transactional
    public <T extends MyEntity> Long create(T object) throws Exception {
        return objectDAO.create(object);
    }

    @Override
    @Transactional
    public <T extends MyEntity> void edit(T object) throws Exception {
        try {
            objectDAO.edit(object);
        } catch (Exception ex) {
            objectDAO.refresh(object);
            throw ex;
        }
    }

    @Override
    @Transactional
    public <T extends MyEntity> void delete(T object) throws Exception {
        objectDAO.delete(object);
    }

    @Override
    @Transactional
    public <T extends MyEntity> void refresh(T object) throws Exception {
        if (object != null) {
            objectDAO.refresh(object);
        }
    }

}
