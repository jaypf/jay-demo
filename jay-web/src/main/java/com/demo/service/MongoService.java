package com.demo.service;

import java.util.List;

/**
 * @ClassName MongoService
 * @Description TODO
 * @Author Jay.Jia
 * @Date 2020/5/18 0:21
 * @Version 1.0
 */
public interface MongoService<E> {

    public String save(E obj);

    public List<E> findAll();

    public List<E> findAll(Class<E> c);

    public E getById(String id);

    public E getByName(String name);

    public String updateE(E e);

    public String deleteE(E e);

    public String deleteById(String id);

    public List<E> findLikes(String reg);
}
