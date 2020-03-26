
package dao;

import java.util.List;
import model.Student;

public interface StudentDao {
    void add(Student student);
    List<Student> findAll(String opt, String col, String opt2);
    void delete(Integer id);
    void update(Student student);
    List<Student> search(String value);
}
