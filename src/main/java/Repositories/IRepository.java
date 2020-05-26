package Repositories;

public interface IRepository<Model>{
    public Model getById(int id);
    public Model[] getAll();
    public Model[] filter(String parameter);
    public Model create(Model model);
    public boolean delete(Model model);
}
