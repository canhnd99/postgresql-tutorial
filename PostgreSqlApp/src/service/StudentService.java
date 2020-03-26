package service;

import java.util.HashMap;
import java.util.List;
import model.Student;

public interface StudentService {
    List<Student> findAll();
    void insert(Student student);
    void add(HashMap mapper, String opt);
    void delete(Integer id);
    void update(Student student);
    Student search(String std_id);
    List<Student> sort(String col, String opt);
}
