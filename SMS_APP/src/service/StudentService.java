package service;

import java.util.List;
import java.util.Map;
import model.Student;

public interface StudentService {
    void add(Map<String, String> map);
    List<Student> findAll(String opt);
    void delete(Integer id);
    void update(Map<String, String> map);
    List<Student> search(String value);
    List<Student> sort(String opt, String col, String opt2);
}
