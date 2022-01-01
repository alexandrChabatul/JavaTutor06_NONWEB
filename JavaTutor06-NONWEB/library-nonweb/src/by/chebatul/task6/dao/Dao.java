package by.chebatul.task6.dao;

import by.chebatul.task6.exception.DaoException;

public interface Dao<T> {

	boolean save(T entity) throws DaoException;

	boolean delete(T entity) throws DaoException;

	void update(T entity) throws DaoException;

}
