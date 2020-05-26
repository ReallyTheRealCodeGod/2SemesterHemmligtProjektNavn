package Repositories;

import models.Autocamper;
import models.Model;
import utility.JDBCConnection;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class Repository<T extends Model> {
    Connection conn;
    Class<T> cls;
    String table;

    public Repository(String table, Class<T> cls) {
        this.cls = cls;
        this.table = table;
        conn = JDBCConnection.getDatabaseConnection();
    }

    public T getById(int id) {
        PreparedStatement prep;
        T model = create();
        try {
            prep = conn.prepareStatement("SELECT * FROM " + table + " WHERE id = ?");
            prep.setInt(1, id);
            ResultSet rs = prep.executeQuery();
            HashMap<Field, Method> map = model.setters();
            Set<Field> keys = map.keySet();

            while(rs.next()){
                for(Field k: keys) {
                    map.get(k).invoke(model, rs.getObject(k.getName(), k.getType()));
                }
            }
        } catch (SQLException | IllegalAccessException | InvocationTargetException sql) {
            sql.printStackTrace();
        }
        return model;
    }
    public ArrayList<T> getAll(){
        ArrayList<T> list = new ArrayList<>();

        try {
            PreparedStatement prep = conn.prepareStatement("SELECT * FROM " + table);
            ResultSet rs = prep.executeQuery();

            HashMap<Field, Method> map = create().setters();
            Set<Field> keys = map.keySet();

            int i = 0;
            while(rs.next()){
                list.add(create());
                for(Field k: keys) {
                    map.get(k).invoke(list.get(i), rs.getObject(k.getName(), k.getType()));
                }
                i++;
            }
        } catch (SQLException | IllegalAccessException | InvocationTargetException sql) {
            sql.printStackTrace();
        }
        return list;
    }
    public int insert(T model){
        Field[] fields = model.fields();

        StringBuilder values = new StringBuilder();
        for(Field f : fields){
            if (values.length() != 0) {
                values.append(", ");
            }
            values.append(f.getName());
        }

        StringBuilder questionMarks = new StringBuilder();
        for(Field f : fields){
            if (questionMarks.length() != 0) {
                questionMarks.append(", ");
            }
            if(!f.getName().equals("id")){
                questionMarks.append("?");
            }else{
                questionMarks.append("DEFAULT");
            }
        }
        try {
            String sql = "INSERT INTO " + table +"("+values +")"+"VALUES("+questionMarks+")";
            PreparedStatement prep = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            int i = 1;
            for(Field f: fields) {
                if(!f.getName().equals("id")) {
                    prep.setObject(i, model.getters().get(f).invoke(model, null));
                    i++;
                }
            }
            prep.executeUpdate();
            ResultSet rs = prep.getGeneratedKeys();
            rs.next();
            return rs.getInt(1);
        } catch (SQLException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return 0;
    }
    public boolean delete(int id){
        try {
            PreparedStatement prep = conn.prepareStatement("DELETE FROM " +table+ " WHERE id=?");
            prep.setInt(1, id);
            prep.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public boolean update(T model){
        try {
            StringBuilder sql = new StringBuilder();
            HashMap<Field, Method> map = model.getters();

            Field[] fields = model.fields();
            Object id = 0;
            StringBuilder values = new StringBuilder();
            for(Field f : fields) {
                if (values.length() != 0) {
                    values.append(", ");
                }
                values.append("? = ?");
            }

            PreparedStatement prep = conn.prepareStatement("UPDATE " + table + " SET " + values + " WHERE id = ?");
            int a = 1;
            for(Field f: fields){
                prep.setString(a, f.getName());
                a++;
                prep.setObject(a, map.get(f).invoke(model, null));
                a++;
            }
            System.out.println(prep.toString());
            prep.setObject(a, id);
            prep.executeUpdate();
            return true;
        }catch(SQLException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return false;
    }

    private T create(){
        try {
            T elem = cls.newInstance();
            return elem;
        }catch(IllegalAccessException | InstantiationException iae){
            iae.printStackTrace();
        }
        return null;
    }
}
