package Services;

import java.util.ArrayList;

public interface IService<T> {
    public ArrayList<T> getAll();
    public T get(int id);
    public T create(T object);
    public T update(T newObject);
    public T delete(int id);
    public void save();
}
