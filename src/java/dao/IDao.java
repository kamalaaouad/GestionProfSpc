package ma.projet.dao;

import java.util.List;

public interface IDao<T> {
  boolean craete(T o);
  boolean update(T o);
  boolean Delete(T o);
  T findById(int i);
  List<T> findAll();
}
