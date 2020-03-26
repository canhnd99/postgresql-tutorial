package dao;

import database.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Student;
import system.SystemConst;

public class StudentDaoImpl implements StudentDao {

    Connection cnt = null;
    PreparedStatement stm = null;
    ResultSet res = null;

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
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    @Override
    public void add(Student student) {
        try {
            StringBuilder sql = new StringBuilder("INSERT INTO student ");
            sql.append("(student_id, full_name, address, age) ");
            sql.append("VALUES (?, ?, ?, ?)");
            cnt = DatabaseConnection.getConnection();
            stm = cnt.prepareStatement(sql.toString());
            stm.setString(1, student.getStudentId());
            stm.setString(2, student.getFullName());
            stm.setString(3, student.getAddress());
            stm.setInt(4, student.getAge());
            stm.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            close();
        }
    }

    @Override
    public List<Student> findAll(String opt, String col, String opt2) {
        List<Student> list = new ArrayList<>();
        try {
            String sql = null;
            if (opt.equals(SystemConst.all)) {
                sql = "SELECT * FROM student";
            } else if (opt.equals(SystemConst.sort)) {
                sql = "SELECT * FROM student ORDER BY " + col + " " + opt2.toUpperCase();
            }
            cnt = DatabaseConnection.getConnection();
            stm = cnt.prepareStatement(sql);
            res = stm.executeQuery();
            while (res.next()) {
                Integer id = res.getInt("id");
                String studentId = res.getString("student_id");
                String fullName = res.getString("full_name");
                String address = res.getString("address");
                Integer age = res.getInt("age");
                Student s = new Student(id, studentId, fullName, address, age);
                list.add(s);
            }
            return list;
        } catch (SQLException e) {
        } finally {
            close();
        }
        return null;
    }

    @Override
    public void delete(Integer id) {
        try {
            String sql = "DELETE FROM student WHERE id = ?";
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
                e1.printStackTrace();
            }
        } finally {
            close();
        }
    }

    @Override
    public void update(Student student) {
        try {
            StringBuilder sql = new StringBuilder("UPDATE student ");
            sql.append("SET student_id = ?, full_name = ?, address = ?, age = ? ");
            sql.append("WHERE id = ?");
            cnt = DatabaseConnection.getConnection();
            cnt.setAutoCommit(false);
            stm = cnt.prepareStatement(sql.toString());
            stm.setString(1, student.getStudentId());
            stm.setString(2, student.getFullName());
            stm.setString(3, student.getAddress());
            stm.setInt(4, student.getAge());
            stm.setInt(5, student.getId());
            stm.executeUpdate();
            cnt.commit();
        } catch (SQLException e) {
            try {
                System.out.println(e.getMessage());
                cnt.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        } finally {
            close();
        }
    }

    @Override
    public List<Student> search(String value) {
        List<Student> list = new ArrayList<>();
        try {
            StringBuilder sql_like = new StringBuilder("SELECT * FROM student ");
            sql_like.append("WHERE student_id LIKE ?");
            cnt = DatabaseConnection.getConnection();
            stm = cnt.prepareStatement(sql_like.toString());
            stm.setString(1, value);
            res = stm.executeQuery();
            while (res.next()) {
                Integer id = res.getInt("id");
                String student_id = res.getString("student_id");
                String fullName = res.getString("full_name");
                String address = res.getString("address");
                Integer age = res.getInt("age");
                Student student = new Student(id, student_id, fullName, address, age);
                list.add(student);
            }
            return list;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        } finally {
            close();
        }
    }
}
