package model;

public class Student {
    private Integer id;
    private String studentId;
    private String firstName;
    private String lastName;
    private String address;
    private String phoneNumber;

    public Student() {
    }

    public Student(Integer id, String studentId, String firstName, String lastName, String address, String phoneNumber) {
        this.id = id;
        this.studentId = studentId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }
    
    public Student(String studentId, String firstName, String lastName, String address, String phoneNumber) {
        this.studentId = studentId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    
    public Object[] toObject() {
        return new Object[]{id, studentId, firstName, lastName, address, phoneNumber};
    }

    @Override
    public String toString() {
        return "student[" + studentId + ", " + firstName + ", "
                + lastName + ", " + address + ", " + phoneNumber + "]";
    }
}
