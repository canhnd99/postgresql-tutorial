package dao;

import java.util.List;
import model.Student;

public interface StudentDao {
    List<Student> findAll(String sql);
    void insert(Student student, String sql);
    void delete(Integer id, String sql);
    void update(Student student, String sql);
    Student search(String st_id, String sql);
}
