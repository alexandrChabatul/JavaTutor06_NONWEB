package by.chebatul.task6.service;

import java.util.List;

import by.chebatul.task6.exception.ServiceException;

public interface RoleService<K, V> {

	List<V> findBooksByName(K name) throws ServiceException;

	List<V> findBooksByAuthor(K author) throws ServiceException;

	List<V> findAllBooks() throws ServiceException;

}
