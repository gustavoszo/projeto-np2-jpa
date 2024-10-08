package model.dao;

import java.util.List;

public interface EntityDao<T> {
	
	void insert(T obj);
	void update(T obj);
	List<T> findAll();
	T findById(String id);
	void deleteById(String id);

}
