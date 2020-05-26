package Repositories;

import models.Model;

public interface IRepository<Model extends models.Model>{
    public Model getById(int id);
    public Model[] getAll();
    public Model[] filter(String parameter);
    public Model create(Model model);
    public boolean delete(Model model);
}
