package my.eschool.bom.spring.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import my.eschool.bom.entity.MyEntity;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author l.avakriyev
 */
@Repository
public class MyEntityDAOImpl implements MyEntityDAO, Serializable {

    @Autowired
    private SessionFactory sessionFactory;

    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }
    
    @Override
    public List query(String hql, Map<String, Object> params, int[] range) {
        Query query = getSession().createQuery(hql);
        if (params != null) {
            for (String key : params.keySet()) {
                query.setParameter(key, params.get(key));
            }
        }
        if (range != null) {
            query.setMaxResults(range[1] - range[0]);
            query.setFirstResult(range[0]);
        }
        return query.list();
    }

    @Override
    public <T extends MyEntity> Long create(T object) {
        return (Long) getSession().save(object);
    }

    @Override
    public <T extends MyEntity> void edit(T object) {
        try {
            getSession().update(object);
        } catch (Exception ex) {
            getSession().merge(object);
        }
    }

    @Override
    public <T extends MyEntity> void delete(T object) {
        getSession().delete(object);
    }

    @Override
    public <T extends MyEntity> T read(Class<T> cl, Long id) {
        return (T) getSession().get(cl, id);
    }

    @Override
    public <T extends MyEntity> void refresh(T object) {
        getSession().refresh(object);
    }

}
