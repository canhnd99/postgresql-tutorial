package dao;

import database.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Student;

public class StudentDaoImpl implements StudentDao {

    private Connection cnt = null;
    private PreparedStatement stm = null;
    private ResultSet res = null;

    public void close() {
        try {
            if (cnt != null) {
                cnt.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (res != null) {
                res.close();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<Student> findAll(String sql) {
        List<Student> listStudents = new ArrayList<>();
        try {
            cnt = DatabaseConnection.getConnection();
            stm = cnt.prepareStatement(sql);
            res = stm.executeQuery();
            while (res.next()) {
                Integer id = res.getInt("id");
                String studentId = res.getString("student_id");
                String fName = res.getString("first_name");
                String lName = res.getString("last_name");
                String address = res.getString("address");
                String phone = res.getString("phone_number");
                Student student = new Student(id, studentId, fName, lName, address, phone);
                listStudents.add(student);
            }
            return listStudents;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        } finally {
            close();
        }
    }

    @Override
    public void insert(Student student, String sql) {
        try {
            cnt = DatabaseConnection.getConnection();
            cnt.setAutoCommit(false);
            stm = cnt.prepareStatement(sql);
            stm.setString(1, student.getStudentId());
            stm.setString(2, student.getFirstName());
            stm.setString(3, student.getLastName());
            stm.setString(4, student.getAddress());
            stm.setString(5, student.getPhoneNumber());
            stm.executeUpdate();
            cnt.commit();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            try {
                cnt.rollback();
            } catch (SQLException e1) {
                System.out.println(e1.getMessage());
            }
        } finally {
            close();
        }
    }

    @Override
    public void delete(Integer id, String sql) {
        try {
            cnt = DatabaseConnection.getConnection();
            cnt.setAutoCommit(false);
            stm = cnt.prepareStatement(sql);
            stm.setInt(1, id);
            stm.executeUpdate();
            cnt.commit();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            try {
                cnt.rollback();
            } catch (SQLException e1) {
                System.out.println(e1.getMessage());
            }
        } finally {
            close();
        }
    }

    @Override
    public void update(Student student, String sql) {
        try {
            cnt = DatabaseConnection.getConnection();
            cnt.setAutoCommit(false);
            stm = cnt.prepareStatement(sql);
            stm.setString(1, student.getStudentId());
            stm.setString(2, student.getFirstName());
            stm.setString(3, student.getLastName());
            stm.setString(4, student.getAddress());
            stm.setString(5, student.getPhoneNumber());
            stm.setInt(6, student.getId());
            stm.executeUpdate();
            cnt.commit();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            try {
                cnt.rollback();
            } catch (SQLException e1) {
                System.out.println(e1.getMessage());
            }
        } finally {
            close();
        }
    }

    @Override
    public Student search(String st_id, String sql) {
        Student s = null;
        try {
            cnt = DatabaseConnection.getConnection();
            stm = cnt.prepareStatement(sql);
            stm.setString(1, st_id);
            res = stm.executeQuery();
            if(res.next()){
                Integer id = res.getInt("id");
                String studentId = res.getString("student_id");
                String fName = res.getString("first_name");
                String lName = res.getString("last_name");
                String address = res.getString("address");
                String phone = res.getString("phone_number");
                s = new Student(id, studentId, fName, lName, address, phone);
            }
            return s;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        } finally {
            close();
        }
    }
}
