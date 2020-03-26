package service;

import dao.StudentDao;
import dao.StudentDaoImpl;
import java.util.HashMap;
import java.util.List;
import javax.swing.JOptionPane;
import model.Student;

public class StudentServiceImpl implements StudentService {

    private StudentDao studentDao;

    public StudentServiceImpl() {
        studentDao = new StudentDaoImpl();
    }

    @Override
    public List<Student> findAll() {
        String sql = "SELECT * FROM student";
        List<Student> list = studentDao.findAll(sql);
        return list;
    }

    @Override
    public void insert(Student student) {
        StringBuilder sql = new StringBuilder("INSERT INTO student ");
        sql.append("(student_id, first_name, last_name, address, phone_number) ");
        sql.append("VALUES(?, ?, ?, ?, ?)");
        if (student != null) {
            studentDao.insert(student, sql.toString());
        }
    }

    @Override
    public void delete(Integer id) {
        StringBuilder sql = new StringBuilder("DELETE FROM student ");
        sql.append("WHERE id = ?");
        if (id != null) {
            studentDao.delete(id, sql.toString());
        }
    }

    @Override
    public void update(Student student) {
        StringBuilder sql = new StringBuilder("UPDATE student ");
        sql.append("SET student_id = ?, first_name = ?, last_name = ?, ");
        sql.append("address = ?, phone_number = ?");
        sql.append("WHERE id = ?");
        if (student != null) {
            studentDao.update(student, sql.toString());
        }
    }

    @Override
    public Student search(String std_id) {
        String sql = "SELECT * FROM student WHERE student_id = ?";
        if (!std_id.equals("")) {
            return studentDao.search(std_id, sql);
        }
        return null;
    }

    @Override
    public List<Student> sort(String col, String opt) {
        String sql = "SELECT * FROM student ORDER BY " + col + " " + opt.toUpperCase();
        return studentDao.findAll(sql);
    }

    @Override
    public void add(HashMap mapper, String opt) {
        String studentId = mapper.get("student_id").toString();
        String fName = mapper.get("f_name").toString();
        String lName = mapper.get("l_name").toString();
        String address = mapper.get("address").toString();
        String phone = mapper.get("phone").toString();

        if (studentId.equals("")
                || fName.equals("")
                || lName.equals("")
                || address.equals("")
                || phone.equals("")) {
            JOptionPane.showMessageDialog(null, "EMPTYYYYYYYYYYY");
        } else {
            String idFormat = "^B[0-9]{2,3}[A-Z]{0,}[0-9]{3,}";
            if (!studentId.matches(idFormat)) {
                JOptionPane.showMessageDialog(null, "IDDDDDDDDDDD");
                System.out.println(studentId);
            } else {
                Student st = new Student(studentId, fName, lName, address, phone);
                StringBuilder sql = new StringBuilder("INSERT INTO student ");
                sql.append("(student_id, first_name, last_name, address, phone_number) ");
                sql.append("VALUES(?, ?, ?, ?, ?)");
                studentDao.insert(st, sql.toString());
            }
        }
    }
}
