package service;

import dao.StudentDao;
import dao.StudentDaoImpl;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;
import model.Student;
import static utils.CheckNumeric.checkNumeric;

public class StudentServiceImpl implements StudentService{
    
    public StudentDao studentDao;

    public StudentServiceImpl() {
        studentDao = new StudentDaoImpl();
    }

    @Override
    public void add(Map<String, String> map) {
        String studentId = map.get("student_id");
        String fullname = map.get("full_name");
        String address = map.get("address");
        String age = map.get("age");
        if(studentId.equals("")
                ||fullname.equals("")
                ||address.equals("")
                ||age.equals("")){
            JOptionPane.showMessageDialog(null, "ONE OR MORE FIELD ARE EMPTY.");
        }else if(!checkNumeric(age)){
            JOptionPane.showMessageDialog(null, "AGE IS NOT NUMERIC");
        }else{
            Integer a = Integer.parseInt(age);
            Student student = new Student(studentId, fullname, address, a);
            studentDao.add(student);
        }
    }

    @Override
    public List<Student> findAll(String opt) {
        List<Student> list = studentDao.findAll(opt, "", "");
        return list;
    }

    @Override
    public void delete(Integer id) {
        studentDao.delete(id);
    }

    @Override
    public void update(Map<String, String> map) {
        String id = map.get("id");
        String studentId = map.get("student_id");
        String fullname = map.get("full_name");
        String address = map.get("address");
        String age = map.get("age");
        if(studentId.equals("")
                ||fullname.equals("")
                ||address.equals("")
                ||age.equals("")){
            JOptionPane.showMessageDialog(null, "ONE OR MORE FIELD ARE EMPTY.");
        }else if(!checkNumeric(age)){
            JOptionPane.showMessageDialog(null, "AGE IS NOT NUMERIC");
        }else{
            Integer i = Integer.parseInt(id);
            Integer a = Integer.parseInt(age);
            Student student = new Student(i, studentId, fullname, address, a);
            studentDao.update(student);
            JOptionPane.showMessageDialog(null, "Updated!");
        }
    }

    @Override
    public List<Student> search(String value) {
        List<Student> list = new ArrayList<>();
        if(value.equals("")){
            JOptionPane.showMessageDialog(null, "PLEASE ENTER SEARCH VALUE");
        }else{
            list = studentDao.search(value);
        }
        return list;
    }

    @Override
    public List<Student> sort(String opt, String col, String opt2) {
        List<Student> list = studentDao.findAll(opt, col, opt2);
        return list;
    }
}
