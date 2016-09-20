package my.eschool.bom.spring.service;

import java.util.List;
import java.util.Map;
import my.eschool.bom.entity.MyEntity;

/**
 *
 * @author l.avakriyev
 */
public interface MyEntityService {
    
    public <T extends MyEntity> T find(Class<T> cl, String field, Object value) throws Exception;
    
    public <T extends MyEntity> List<T> findAll(Class<T> cl, String field, Object value, String orderBy) throws Exception;
    
    public <T extends MyEntity> T find(Class<T> cl, Map<String, Object> params) throws Exception;
    
    public <T extends MyEntity> List<T> getAll(Class<T> cl, String orderBy) throws Exception;
    
    public <T extends MyEntity> List<T> query(String hql, Map<String, Object> params, int[] range);

    public Long count(String hql, Map<String, Object> params);

    public <T extends MyEntity> T read(Class<T> cl, Long id) throws Exception;
    
    public <T extends MyEntity> Long create(T object) throws Exception;
    
    public <T extends MyEntity> void edit(T object) throws Exception;
    
    public <T extends MyEntity> void delete(T object) throws Exception;

    public <T extends MyEntity> void refresh(T object) throws Exception;

}
