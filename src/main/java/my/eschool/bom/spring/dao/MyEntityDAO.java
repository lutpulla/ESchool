package my.eschool.bom.spring.dao;

import java.util.List;
import java.util.Map;
import my.eschool.bom.entity.MyEntity;

/**
 *
 * @author l.avakriyev
 */
public interface MyEntityDAO {
    
    public List query(String hql, Map<String, Object> params, int[] range);
    
    public <T extends MyEntity> Long create(T object);
    
    public <T extends MyEntity> void edit(T object);
    
    public <T extends MyEntity> void delete(T object);
    
    public <T extends MyEntity> T read(Class<T> cl, Long id);
    
    public <T extends MyEntity> void refresh(T object);
    
}
