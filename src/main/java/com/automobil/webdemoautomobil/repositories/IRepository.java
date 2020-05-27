package com.automobil.webdemoautomobil.repositories;

public interface IRepository<T>{
    public T getById(int id);
    public T[] getAll();
    public T[] getByParameter(String parameter, String... columns);
    public T create(T model);
    public boolean delete(T model);
}
