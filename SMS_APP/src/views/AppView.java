package views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

public class AppView extends javax.swing.JFrame {

    List<Student> listStudent = null;
    DefaultTableModel studentTable = null;
    public StudentService studentService;

    public AppView() {
        initComponents();
        setLocationRelativeTo(null);
        txt_id.setVisible(false);

        studentService = new StudentServiceImpl();
        studentTable = (DefaultTableModel) table.getModel();

        loadDataToTable();

        btn_add.addActionListener((ActionEvent e) -> {
            Map<String, String> map = new HashMap<>();
            map.put("student_id", txt_std_id.getText());
            map.put("full_name", txt_full_name.getText());
            map.put("address", txt_address.getText());
            map.put("age", txt_age.getText());
            studentService.add(map);
            JOptionPane.showMessageDialog(null, "Added!");
            loadDataToTable();
        });

        btn_delete.addActionListener((ActionEvent e) -> {
            Integer selectedRow = table.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(null, "PLEASE SELECT A RECORD TO DELETE.");
            } else {
                Integer id = (Integer) studentTable.getValueAt(selectedRow, 0);
                studentService.delete(id);
                JOptionPane.showMessageDialog(null, "Deleted!");
                loadDataToTable();
            }
        });

        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                Student student = listStudent.get(table.getSelectedRow());
                txt_id.setText(student.getId().toString());
                txt_std_id.setText(student.getStudentId());
                txt_full_name.setText(student.getFullName());
                txt_address.setText(student.getAddress());
                txt_age.setText(student.getAge().toString());
            }
        });

        btn_update.addActionListener((ActionEvent e) -> {
            if (table.getSelectedRow() == -1) {
                JOptionPane.showMessageDialog(null, "PLEASE SELECT A RECORD TO UPDATE.");
            } else {
                Map<String, String> map = new HashMap<>();
                map.put("id", txt_id.getText());
                map.put("student_id", txt_std_id.getText());
                map.put("full_name", txt_full_name.getText());
                map.put("address", txt_address.getText());
                map.put("age", txt_age.getText());
                studentService.update(map);
                loadDataToTable();
            }
        });
        
        btn_clear.addActionListener((ActionEvent e) -> {
            txt_std_id.setText("");
            txt_full_name.setText("");
            txt_address.setText("");
            txt_age.setText("");
        });
        
        txt_search.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent evt){
                if(evt.getKeyCode() == KeyEvent.VK_ENTER){
                    String value = txt_search.getText();
                    List<Student> list = studentService.search(value);
                    if(!list.isEmpty()){
                        studentTable.setRowCount(0);
                        for (Student student : list) {
                            studentTable.addRow(student.toObject());
                        }
                    }
                }
            }
        });
        
        btn_reload.addActionListener((ActionEvent e) -> {
            loadDataToTable();
        });
        
        btn_sort.addActionListener((ActionEvent e) -> {
            String col = cbox_col.getItemAt(cbox_col.getSelectedIndex());
            String opt = cbox_opt.getItemAt(cbox_opt.getSelectedIndex());
            studentTable.setRowCount(0);
            List<Student> list = studentService.sort("SORT", col, opt);
            for (Student s : list) {
                studentTable.addRow(s.toObject());
            }
        });
    }

    public void loadDataToTable() {
        listStudent = studentService.findAll("ALL");
        studentTable.setRowCount(0);
        for (Student s : listStudent) {
            studentTable.addRow(s.toObject());
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txt_std_id = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txt_full_name = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txt_address = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txt_age = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        btn_clear = new javax.swing.JButton();
        btn_add = new javax.swing.JButton();
        btn_update = new javax.swing.JButton();
        btn_delete = new javax.swing.JButton();
        btn_reload = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        txt_search = new javax.swing.JTextField();
        cbox_col = new javax.swing.JComboBox<>();
        cbox_opt = new javax.swing.JComboBox<>();
        btn_sort = new javax.swing.JButton();
        txt_id = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 22)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Student Manager");

        jLabel2.setText("student_id:");

        jLabel3.setText("full_name:");

        jLabel4.setText("address:");

        jLabel5.setText("age:");

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "id", "student_id", "full_name", "address", "age"
            }
        ));
        jScrollPane1.setViewportView(table);

        btn_clear.setText("clear");

        btn_add.setText("add");

        btn_update.setText("update");

        btn_delete.setText("delete");

        btn_reload.setText("reload");

        jLabel6.setText("search:");

        cbox_col.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "id", "student_id", "full_name" }));

        cbox_opt.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "asc", "desc" }));

        btn_sort.setText("sort");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_search))
                    .addComponent(jLabel5)
                    .addComponent(jLabel4)
                    .addComponent(jLabel3)
                    .addComponent(jLabel2)
                    .addComponent(txt_std_id)
                    .addComponent(txt_full_name)
                    .addComponent(txt_address)
                    .addComponent(txt_age, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
                    .addComponent(txt_id))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(btn_clear, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(61, 61, 61)
                        .addComponent(btn_add, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn_update, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(64, 64, 64)
                        .addComponent(btn_delete, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(60, 60, 60)
                        .addComponent(btn_reload, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(cbox_col, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cbox_opt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_sort))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 704, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(txt_search, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbox_col, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbox_opt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_sort))
                        .addGap(23, 23, 23)
                        .addComponent(txt_id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txt_std_id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txt_full_name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txt_address, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txt_age, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(60, 60, 60)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 328, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_clear)
                    .addComponent(btn_add)
                    .addComponent(btn_update)
                    .addComponent(btn_delete)
                    .addComponent(btn_reload))
                .addGap(0, 41, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public static void main(String args[]) {
        AppView app = new AppView();
        app.setVisible(true);
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
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable table;
    private javax.swing.JTextField txt_address;
    private javax.swing.JTextField txt_age;
    private javax.swing.JTextField txt_full_name;
    private javax.swing.JTextField txt_id;
    private javax.swing.JTextField txt_search;
    private javax.swing.JTextField txt_std_id;
    // End of variables declaration//GEN-END:variables
}
