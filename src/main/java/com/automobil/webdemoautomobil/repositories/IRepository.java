package com.automobil.webdemoautomobil.repositories;

import java.util.ArrayList;

public interface IRepository<T>{
    public T getById(int id);
    public ArrayList<T> getAll();
    public ArrayList<T> getByParameter(String parameter, String... columns);
    public T create(T model);
    public boolean delete(T model);
}
