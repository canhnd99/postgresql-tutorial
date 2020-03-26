package views;

import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.Student;
import service.StudentService;
import service.StudentServiceImpl;

public class ListStudent extends javax.swing.JFrame {

    private final String UPDATE = "update";
    private final String INSERT = "insert";
    public static String ERROR = "";

    DefaultTableModel studentTable = null;
    List<Student> listStudents = null;
    StudentService studentService;

    public ListStudent() {
        initComponents();
        setLocationRelativeTo(null);
        txt_id.setVisible(false);

        studentService = new StudentServiceImpl();
        studentTable = (DefaultTableModel) table.getModel();

        loadDataToTable();

        btn_reload.addActionListener((ActionEvent e) -> {
            studentTable.setRowCount(0);
            loadDataToTable();
        });

        btn_clear.addActionListener(((e) -> {
            txt_student_id.setText("");
            txt_fname.setText("");
            txt_lname.setText("");
            txt_address.setText("");
            txt_phone.setText("");
        }));

        //add new student.
        btn_add.addActionListener((ActionEvent e) -> {
            HashMap mapper = getModel();
            studentService.add(mapper, INSERT);
            if(ERROR.equals("ERROR_ID")){
                JOptionPane.showMessageDialog(null, "IDDDDDDDDDDDDD");
            }
            loadDataToTable();
        });

        //delete a student.
        btn_delete.addActionListener((ActionEvent e) -> {
            Integer id = (Integer) studentTable.getValueAt(table.getSelectedRow(), 0);
            studentService.delete(id);
            JOptionPane.showMessageDialog(null, "Deleted!");
            loadDataToTable();
        });

        //update student information.
        btn_update.addActionListener((ActionEvent e) -> {
            Student st = getStudentModel(UPDATE);
            studentService.update(st);
            JOptionPane.showMessageDialog(null, "Updated!");
            loadDataToTable();
        });

        //search by student_id
        txt_search.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent evt) {
                if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                    String value = txt_search.getText().toUpperCase();
                    if (value.equals("")) {
                        JOptionPane.showMessageDialog(null, "Enter searched value.");
                    } else {
                        Student std = studentService.search(value);
                        if (std == null) {
                            JOptionPane.showMessageDialog(null, "Not Found!");
                        } else {
                            studentTable.setRowCount(0);
                            studentTable.addRow(std.toObject());
                        }
                    }
                }
            }
        });

        btn_sort.addActionListener((ActionEvent e) -> {
            String col = cbox_col.getItemAt(cbox_col.getSelectedIndex());
            String opt = cbox_opt.getItemAt(cbox_opt.getSelectedIndex());
            studentTable.setRowCount(0);
            listStudents = studentService.sort(col, opt);
            listStudents.forEach((student) -> {
                studentTable.addRow(student.toObject());
            });
        });

        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent et) {
                Student st = listStudents.get(table.getSelectedRow());
                txt_id.setText(st.getId().toString());
                txt_student_id.setText(st.getStudentId());
                txt_fname.setText(st.getFirstName());
                txt_lname.setText(st.getLastName());
                txt_address.setText(st.getAddress());
                txt_phone.setText(st.getPhoneNumber());
            }
        });

    }

    public void loadDataToTable() {
        studentTable.setRowCount(0);
        listStudents = studentService.findAll();
        listStudents.forEach((student) -> {
            studentTable.addRow(student.toObject());
        });
    }
    
    public HashMap getModel(){
        HashMap map = new HashMap();
        map.put("id", Integer.parseInt(txt_id.getText()));
        map.put("student_id", txt_student_id.getText());
        map.put("f_name", txt_fname.getText());
        map.put("l_name", txt_lname.getText());
        map.put("address", txt_address.getText());
        map.put("phone", txt_phone.getText());
        
        Map mapper = new HashMap();
        return map;
    }

    public Student getStudentModel(String opt) {
        Integer id = Integer.parseInt(txt_id.getText());
        String studentId = txt_student_id.getText();
        String fName = txt_fname.getText();
        String lName = txt_lname.getText();
        String address = txt_address.getText();
        String phone = txt_phone.getText();
        if (studentId.equals("") || fName.equals("")
                || lName.equals("") || address.equals("")
                || phone.equals("")) {
            JOptionPane.showMessageDialog(null, "One or more field are empty.");
        } else {
            Student st = null;
            if (opt.equals("insert")) {
                st = new Student(studentId, fName, lName, address, phone);
            } else if (opt.equals("update")) {
                st = new Student(id, studentId, fName, lName, address, phone);
            }
            return st;
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        btn_add = new javax.swing.JButton();
        btn_update = new javax.swing.JButton();
        btn_delete = new javax.swing.JButton();
        txt_search = new javax.swing.JTextField();
        cbox_col = new javax.swing.JComboBox<>();
        btn_reload = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        txt_student_id = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txt_fname = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txt_lname = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txt_address = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txt_phone = new javax.swing.JTextField();
        btn_clear = new javax.swing.JButton();
        txt_id = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        cbox_opt = new javax.swing.JComboBox<>();
        btn_sort = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "id", "student_id", "first_name", "last_name", "address", "phone_number"
            }
        ));
        jScrollPane1.setViewportView(table);

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 20)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("STUDENT MANAGER");

        btn_add.setText("add");

        btn_update.setText("update");

        btn_delete.setText("delete");

        cbox_col.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "id", "student_id" }));

        btn_reload.setText("reload");

        jLabel4.setText("student_id:");

        jLabel5.setText("first_name:");

        jLabel6.setText("last_name");

        jLabel7.setText("address");

        jLabel8.setText("phone_number:");

        btn_clear.setText("clear");

        jLabel2.setText("search:");

        cbox_opt.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "asc", "desc" }));

        btn_sort.setText("sort");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8)
                    .addComponent(txt_phone, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                    .addComponent(txt_address)
                    .addComponent(txt_lname)
                    .addComponent(txt_fname)
                    .addComponent(txt_student_id)
                    .addComponent(txt_id)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_search)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btn_clear, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btn_add, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(72, 72, 72)
                                .addComponent(btn_update, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(74, 74, 74)
                                .addComponent(btn_delete, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(61, 61, 61)
                                .addComponent(btn_reload, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 730, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(29, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(cbox_col, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(cbox_opt, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_sort)
                        .addGap(25, 25, 25))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_search, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(cbox_opt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbox_col, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_sort))
                .addGap(24, 24, 24)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txt_id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txt_student_id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txt_fname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txt_lname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txt_address, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txt_phone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 371, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_delete)
                    .addComponent(btn_update)
                    .addComponent(btn_add)
                    .addComponent(btn_clear)
                    .addComponent(btn_reload))
                .addGap(26, 26, 26))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public static void main(String args[]) {
        ListStudent listStudent = new ListStudent();
        listStudent.setVisible(true);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_add;
    private javax.swing.JButton btn_clear;
    private javax.swing.JButton btn_delete;
    private javax.swing.JButton btn_reload;
    private javax.swing.JButton btn_sort;
    private javax.swing.JButton btn_update;
    private javax.swing.JComboBox<String> cbox_col;
    private javax.swing.JComboBox<String> cbox_opt;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable table;
    private javax.swing.JTextField txt_address;
    private javax.swing.JTextField txt_fname;
    private javax.swing.JTextField txt_id;
    private javax.swing.JTextField txt_lname;
    private javax.swing.JTextField txt_phone;
    private javax.swing.JTextField txt_search;
    private javax.swing.JTextField txt_student_id;
    // End of variables declaration//GEN-END:variables
}
