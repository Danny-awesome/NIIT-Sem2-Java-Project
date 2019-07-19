/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package knowledgeonweb;

import java.awt.Color;
import java.awt.HeadlessException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author Daniel
 */
public class QualificationDetailsForm extends javax.swing.JFrame {

    /**
     * Creates new form QualificationDetailsForm
     */
    
    MenuForm appForm = new MenuForm();
    
    Color placeholder = new Color(102, 102, 102);
    
    public QualificationDetailsForm(String programm,String school,String name,String city,String state,String zip,String country,String resCC,String resAc,String resPhone,String offCC,String offAC,String offPhone,String mail,String sex,String dob,String citizen) {
        this.newname = name;
        this.program = programm;
        this.school = school;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.country = country;
        this.rescc = resCC;
        this.resac = resAc;
        this.resnum = resPhone;
        this.offcc = offCC;
        this.offac = offAC;
        this.offnum = offPhone;
        this.email = mail;
        this.gender = sex;
        this.dob = dob;
        this.status = citizen;
        
        initComponents();
        URL url = getClass().getResource("/kw/logoTrans.png");
        ImageIcon imgicon = new ImageIcon(url);
        super.setIconImage(imgicon.getImage());
        setLocationRelativeTo(null);
        graduatedpField.setText(programm);
        schoolField.setText(school);
    }
        String newname, program,school,city,state,zip,country,rescc,resac,resnum,offcc,offac,offnum ,email,gender,dob,status;
    void readDB(){
            try{
                Class.forName("org.sqlite.JDBC");
                Connection conn = DriverManager.getConnection("jdbc:sqlite:KnowledgeOnWebDB.db");
                Statement stat = conn.createStatement();
            }catch(ClassNotFoundException | SQLException e){
                System.out.println(e.getMessage());
            }
        }
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel1 = new javax.swing.JLabel();
        graduatedpField = new javax.swing.JTextField();
        jSeparator5 = new javax.swing.JSeparator();
        gradeField = new javax.swing.JTextField();
        jSeparator6 = new javax.swing.JSeparator();
        schoolField = new javax.swing.JTextField();
        jSeparator7 = new javax.swing.JSeparator();
        uniField = new javax.swing.JTextField();
        jSeparator8 = new javax.swing.JSeparator();
        greScoresField = new javax.swing.JTextField();
        jSeparator9 = new javax.swing.JSeparator();
        testDateField = new javax.swing.JTextField();
        jSeparator10 = new javax.swing.JSeparator();
        testScoreField = new javax.swing.JTextField();
        jSeparator11 = new javax.swing.JSeparator();
        toeflScoreField = new javax.swing.JTextField();
        jSeparator12 = new javax.swing.JSeparator();
        honorsAwardsField = new javax.swing.JTextField();
        jSeparator13 = new javax.swing.JSeparator();
        submitBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jSeparator1.setBackground(new java.awt.Color(102, 102, 102));
        jPanel1.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 610, 10));

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel1.setText("     Qualification Details : ");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 20, 200, 40));

        graduatedpField.setEditable(false);
        graduatedpField.setBackground(new java.awt.Color(255, 255, 255));
        graduatedpField.setForeground(new java.awt.Color(102, 102, 102));
        graduatedpField.setText("Graduation Degree Program");
        graduatedpField.setToolTipText("");
        graduatedpField.setBorder(null);
        jPanel1.add(graduatedpField, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 250, 20));
        jPanel1.add(jSeparator5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, 250, 10));

        gradeField.setForeground(new java.awt.Color(102, 102, 102));
        gradeField.setText("Grade");
        gradeField.setToolTipText("");
        gradeField.setBorder(null);
        gradeField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                gradeFieldFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                gradeFieldFocusLost(evt);
            }
        });
        gradeField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                gradeFieldKeyTyped(evt);
            }
        });
        jPanel1.add(gradeField, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 60, 200, 20));
        jPanel1.add(jSeparator6, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 80, 200, 10));

        schoolField.setEditable(false);
        schoolField.setBackground(new java.awt.Color(255, 255, 255));
        schoolField.setForeground(new java.awt.Color(102, 102, 102));
        schoolField.setText("School");
        schoolField.setToolTipText("");
        schoolField.setBorder(null);
        schoolField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                schoolFieldKeyTyped(evt);
            }
        });
        jPanel1.add(schoolField, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, 280, 20));
        jPanel1.add(jSeparator7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, 280, 10));

        uniField.setForeground(new java.awt.Color(102, 102, 102));
        uniField.setText("University");
        uniField.setToolTipText("");
        uniField.setBorder(null);
        uniField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                uniFieldFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                uniFieldFocusLost(evt);
            }
        });
        uniField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                uniFieldKeyTyped(evt);
            }
        });
        jPanel1.add(uniField, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 100, 280, 20));
        jPanel1.add(jSeparator8, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 120, 280, 10));

        greScoresField.setForeground(new java.awt.Color(102, 102, 102));
        greScoresField.setText("Gradute Record Examination (GRE) Scores");
        greScoresField.setToolTipText("");
        greScoresField.setBorder(null);
        greScoresField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                greScoresFieldFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                greScoresFieldFocusLost(evt);
            }
        });
        greScoresField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                greScoresFieldKeyTyped(evt);
            }
        });
        jPanel1.add(greScoresField, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, 580, 20));
        jPanel1.add(jSeparator9, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 160, 580, 10));

        testDateField.setForeground(new java.awt.Color(102, 102, 102));
        testDateField.setText("Test Date");
        testDateField.setToolTipText("");
        testDateField.setBorder(null);
        testDateField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                testDateFieldFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                testDateFieldFocusLost(evt);
            }
        });
        testDateField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                testDateFieldKeyTyped(evt);
            }
        });
        jPanel1.add(testDateField, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 180, 250, 20));
        jPanel1.add(jSeparator10, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 200, 250, 10));

        testScoreField.setForeground(new java.awt.Color(102, 102, 102));
        testScoreField.setText("Score (%)");
        testScoreField.setToolTipText("");
        testScoreField.setBorder(null);
        testScoreField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                testScoreFieldFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                testScoreFieldFocusLost(evt);
            }
        });
        testScoreField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                testScoreFieldKeyTyped(evt);
            }
        });
        jPanel1.add(testScoreField, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 180, 250, 20));
        jPanel1.add(jSeparator11, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 200, 250, 10));

        toeflScoreField.setForeground(new java.awt.Color(102, 102, 102));
        toeflScoreField.setText("TOEFLScore (If required)");
        toeflScoreField.setToolTipText("");
        toeflScoreField.setBorder(null);
        toeflScoreField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                toeflScoreFieldFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                toeflScoreFieldFocusLost(evt);
            }
        });
        toeflScoreField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                toeflScoreFieldKeyTyped(evt);
            }
        });
        jPanel1.add(toeflScoreField, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 220, 250, 20));
        jPanel1.add(jSeparator12, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 240, 250, 10));

        honorsAwardsField.setForeground(new java.awt.Color(102, 102, 102));
        honorsAwardsField.setText("Details Of Recent Honors and Awards");
        honorsAwardsField.setToolTipText("");
        honorsAwardsField.setBorder(null);
        honorsAwardsField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                honorsAwardsFieldFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                honorsAwardsFieldFocusLost(evt);
            }
        });
        honorsAwardsField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                honorsAwardsFieldKeyTyped(evt);
            }
        });
        jPanel1.add(honorsAwardsField, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 260, 580, 20));
        jPanel1.add(jSeparator13, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 280, 580, 10));

        submitBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/Save1_32.png"))); // NOI18N
        submitBtn.setText("SUBMIT");
        submitBtn.setBorder(null);
        submitBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submitBtnActionPerformed(evt);
            }
        });
        jPanel1.add(submitBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 290, 110, 40));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 610, 340));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void gradeFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_gradeFieldFocusGained
        // TODO add your handling code here:
        if(gradeField.getText().equals("Grade")){
            gradeField.setText("");
        }
        if(!gradeField.getText().equals("Grade")){
            gradeField.setForeground(Color.black);
        }
    }//GEN-LAST:event_gradeFieldFocusGained

    private void gradeFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_gradeFieldFocusLost
        // TODO add your handling code here:
        if(!gradeField.getText().isEmpty()){

        }else{
            gradeField.setText("Grade");
            gradeField.setForeground(placeholder);
        }
        if(!gradeField.getText().equals("Grade")){
            gradeField.setForeground(Color.black);
        }
    }//GEN-LAST:event_gradeFieldFocusLost

    private void gradeFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_gradeFieldKeyTyped
        // TODO add your handling code here:
        
    }//GEN-LAST:event_gradeFieldKeyTyped

    private void uniFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_uniFieldFocusGained
        // TODO add your handling code here:
        if(uniField.getText().equals("University")){
            uniField.setText("");
        }
        if(!uniField.getText().equals("University")){
            uniField.setForeground(Color.black);
        }
    }//GEN-LAST:event_uniFieldFocusGained

    private void uniFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_uniFieldFocusLost
        // TODO add your handling code here:
        if(!uniField.getText().isEmpty()){

        }else{
            uniField.setText("University");
            uniField.setForeground(placeholder);
        }
        if(!uniField.getText().equals("University")){
            uniField.setForeground(Color.black);
        }
    }//GEN-LAST:event_uniFieldFocusLost

    private void uniFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_uniFieldKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_uniFieldKeyTyped

    private void greScoresFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_greScoresFieldFocusGained
        // TODO add your handling code here:
        if(greScoresField.getText().equals("Gradute Record Examination (GRE) Scores")){
            greScoresField.setText("");
        }
        if(!greScoresField.getText().equals("Gradute Record Examination (GRE) Scores")){
            greScoresField.setForeground(Color.black);
        }
    }//GEN-LAST:event_greScoresFieldFocusGained

    private void greScoresFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_greScoresFieldFocusLost
        // TODO add your handling code here:
        if(!greScoresField.getText().isEmpty()){

        }else{
            greScoresField.setText("Gradute Record Examination (GRE) Scores");
            greScoresField.setForeground(placeholder);
        }
        if(!greScoresField.getText().equals("Gradute Record Examination (GRE) Scores")){
            greScoresField.setForeground(Color.black);
        }
    }//GEN-LAST:event_greScoresFieldFocusLost

    private void greScoresFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_greScoresFieldKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_greScoresFieldKeyTyped

    private void testDateFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_testDateFieldFocusGained
        // TODO add your handling code here:
        if(testDateField.getText().equals("Test Date")){
           testDateField.setText("");
        }
        if(!testDateField.getText().equals("Test Date")){
            testDateField.setForeground(Color.black);
        }
    }//GEN-LAST:event_testDateFieldFocusGained

    private void testDateFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_testDateFieldFocusLost
        // TODO add your handling code here:
        if(!testDateField.getText().isEmpty()){

        }else{
            testDateField.setText("Test Date");
            testDateField.setForeground(placeholder);
        }
        if(!testDateField.getText().equals("Test Date")){
            testDateField.setForeground(Color.black);
        }
    }//GEN-LAST:event_testDateFieldFocusLost

    private void testDateFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_testDateFieldKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_testDateFieldKeyTyped

    private void testScoreFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_testScoreFieldFocusGained
        // TODO add your handling code here:
        if(testScoreField.getText().equals("Score (%)")){
           testScoreField.setText("");
        }
        if(!testScoreField.getText().equals("Score (%)")){
            testScoreField.setForeground(Color.black);
        }
    }//GEN-LAST:event_testScoreFieldFocusGained

    private void testScoreFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_testScoreFieldFocusLost
        // TODO add your handling code here:
        if(!testScoreField.getText().isEmpty()){

        }else{
            testScoreField.setText("Score (%)");
            testScoreField.setForeground(placeholder);
        }
        if(!testScoreField.getText().equals("Score (%)")){
            testScoreField.setForeground(Color.black);
        }
    }//GEN-LAST:event_testScoreFieldFocusLost

    private void testScoreFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_testScoreFieldKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_testScoreFieldKeyTyped

    private void toeflScoreFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_toeflScoreFieldFocusGained
        // TODO add your handling code here:
        if(toeflScoreField.getText().equals("TOEFLScore (If required)")){
           toeflScoreField.setText("");
        }
        if(!toeflScoreField.getText().equals("TOEFLScore (If required)")){
            toeflScoreField.setForeground(Color.black);
        }
    }//GEN-LAST:event_toeflScoreFieldFocusGained

    private void toeflScoreFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_toeflScoreFieldFocusLost
        // TODO add your handling code here:
        if(!toeflScoreField.getText().isEmpty()){

        }else{
            toeflScoreField.setText("TOEFLScore (If required)");
            toeflScoreField.setForeground(placeholder);
        }
        if(!toeflScoreField.getText().equals("TOEFLScore (If required)")){
            toeflScoreField.setForeground(Color.black);
        }
    }//GEN-LAST:event_toeflScoreFieldFocusLost

    private void toeflScoreFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_toeflScoreFieldKeyTyped
        // TODO add your handling code here:
        
    }//GEN-LAST:event_toeflScoreFieldKeyTyped

    private void honorsAwardsFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_honorsAwardsFieldFocusGained
        // TODO add your handling code here:
        if(honorsAwardsField.getText().equals("Details Of Recent Honors and Awards")){
           honorsAwardsField.setText("");
        }
        if(!honorsAwardsField.getText().equals("Details Of Recent Honors and Awards")){
            honorsAwardsField.setForeground(Color.black);
        }
    }//GEN-LAST:event_honorsAwardsFieldFocusGained

    private void honorsAwardsFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_honorsAwardsFieldFocusLost
        // TODO add your handling code here:
        if(!honorsAwardsField.getText().isEmpty()){

        }else{
            honorsAwardsField.setText("Details Of Recent Honors and Awards");
            honorsAwardsField.setForeground(placeholder);
        }
        if(!honorsAwardsField.getText().equals("Details Of Recent Honors and Awards")){
            honorsAwardsField.setForeground(Color.black);
        }
    }//GEN-LAST:event_honorsAwardsFieldFocusLost

    private void honorsAwardsFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_honorsAwardsFieldKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_honorsAwardsFieldKeyTyped

    @SuppressWarnings("CallToPrintStackTrace")
    private void submitBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submitBtnActionPerformed
        // TODO add your handling code here:
        try {
            Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection("jdbc:sqlite:KnowledgeOnWebDB.db");
            
            String query3 = "INSERT INTO StudentsData VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?,"
                    + "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
            PreparedStatement pst3=conn.prepareStatement(query3);
            pst3.setString(1, school);
            pst3.setString(2, program);
            pst3.setString(3, newname);
            pst3.setString(4, city);
            pst3.setString(5, state);
            pst3.setString(6, zip);
            pst3.setString(7, country);
            pst3.setString(9, rescc);
            pst3.setString(8, resac);
            pst3.setString(10, resnum);
            pst3.setString(11, offcc);
            pst3.setString(12, offac);
            pst3.setString(13, offnum);
            pst3.setString(14, email);
            pst3.setString(15, gender);
            pst3.setString(16, dob);
            pst3.setString(17, status);
            pst3.setString(18, gradeField.getText());
            pst3.setString(19, uniField.getText());
            pst3.setString(20, greScoresField.getText());
            pst3.setString(21, testDateField.getText());
            pst3.setString(22, testScoreField.getText());
            pst3.setString(23, toeflScoreField.getText());
            pst3.setString(24, honorsAwardsField.getText());
            pst3.execute();
           
            JOptionPane.showMessageDialog(null, "Details Submitted Successfully");
        } catch (HeadlessException | ClassNotFoundException | SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        this.dispose();
        new MenuForm().setVisible(true);
              
    }//GEN-LAST:event_submitBtnActionPerformed

    private void schoolFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_schoolFieldKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_schoolFieldKeyTyped

    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(QualificationDetailsForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(QualificationDetailsForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(QualificationDetailsForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(QualificationDetailsForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new QualificationDetailsForm().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField gradeField;
    private javax.swing.JTextField graduatedpField;
    private javax.swing.JTextField greScoresField;
    private javax.swing.JTextField honorsAwardsField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator10;
    private javax.swing.JSeparator jSeparator11;
    private javax.swing.JSeparator jSeparator12;
    private javax.swing.JSeparator jSeparator13;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JSeparator jSeparator9;
    private javax.swing.JTextField schoolField;
    private javax.swing.JButton submitBtn;
    private javax.swing.JTextField testDateField;
    private javax.swing.JTextField testScoreField;
    private javax.swing.JTextField toeflScoreField;
    private javax.swing.JTextField uniField;
    // End of variables declaration//GEN-END:variables
}
