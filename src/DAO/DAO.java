package DAO;

import java.sql.SQLException;
import java.util.List;

public interface DAO <T>{
    List<T> findall() ;
    T finById (int id) ;
    T save(T o) ;
    boolean detete(T o) ;
    T update(T o) ;
}
