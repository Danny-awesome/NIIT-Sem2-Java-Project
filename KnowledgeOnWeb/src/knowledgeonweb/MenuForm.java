 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package knowledgeonweb;

import java.awt.Color;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author Daniel
 */
public class MenuForm extends javax.swing.JFrame {

    int xy;
    int xx;
    //placeholder color code
    Color placeholder = new Color(102, 102, 102);
    
    //variables for email validation
    String ckEmail;
    Pattern pattern;
    Matcher matcher;
    String email_pattern = "^[A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
    
    //variables for date
    String mnthIt[] = {"January","February","March","April","May","June","July","August","September","October",
                    "November","December"};
    int days[] = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    int lpDays[] = {31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    boolean notInit = false;
    
    /**
     * Creates new form MianForm
     */
    public MenuForm() {
        initComponents();
        setLocationRelativeTo(null);
        
        URL url = getClass().getResource("/kw/logoTrans.png");
        ImageIcon imgicon = new ImageIcon(url);
        super.setIconImage(imgicon.getImage());
        
        yearbox.removeAllItems();
        for(int i = 1999; i >= 1960; i--){
            yearbox.addItem(i);
        }
        monthbox.removeAllItems();
        for(int i = 0; i < mnthIt.length; i++){
            monthbox.addItem(mnthIt[i]);
        }
        daybox.removeAllItems();
        for(int i = 1; i <= 31; i++){
            daybox.addItem(i);
        }
        notInit = true;
        
        readDB();

    }

    void readDB() {
        try {
            Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection("jdbc:sqlite:KnowledgeOnWebDB.db");
           String sql = "select distinct programName from GraduateSchoolsData";
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                String programName = rs.getString("programName");
                GETprogramName.addItem(programName);
            }
            conn.close();
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public void validateEntryDetails(){  
        String emailValue = emailField.getText();
        String emailregex = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
        Boolean b = emailValue.matches(emailregex);
            
        if(GETprogramName.getSelectedIndex() == 0){
            JOptionPane.showMessageDialog(null, "Choose Program Name");
        }else if(GETschoolName.getSelectedIndex() == 0){
            JOptionPane.showMessageDialog(null, "Choose School Name");
        }else if(firstnameField.getText().equalsIgnoreCase("First") || firstnameField.getText().length() <= 1){
            JOptionPane.showMessageDialog(null, "inalid entry or name length is not allowed\n"
                    + "on First name field!!!");
        }else if(midnameField.getText().equalsIgnoreCase("Middle") || midnameField.getText().length() <= 1){
            JOptionPane.showMessageDialog(null, "inalid entry or name length is not allowed\n"
                    + "on Middle name field!!!");
        }else if(lastnameField.getText().equalsIgnoreCase("Last") || lastnameField.getText().length() <= 1){
            JOptionPane.showMessageDialog(null, "inalid entry or name length is not allowed\n"
                    + "on Last name field!!!");
        }else if(cityField.getText().equalsIgnoreCase("city") || cityField.getText().length() <= 2){
            JOptionPane.showMessageDialog(null, "city field is invalid");
        }else if(stateField.getText().equalsIgnoreCase("state") || stateField.getText().length() <= 2){
            JOptionPane.showMessageDialog(null, "state field is invalid");
        }else if(countryField.getText().equalsIgnoreCase("country") || countryField.getText().length() < 5){
            JOptionPane.showMessageDialog(null, "country field is invalid");
        }else if(residenceCCField.getText().equalsIgnoreCase("country code")){
            JOptionPane.showMessageDialog(null, "country code field is invalid");
        }else if(residenceACField.getText().equalsIgnoreCase("area code")){
            JOptionPane.showMessageDialog(null, "area code field is invalid");
        }else if(residencenumField.getText().equalsIgnoreCase("phone") || residencenumField.getText().length() <= 9){
            JOptionPane.showMessageDialog(null, "phone field is invalid");
        }else if (b == false) {
            JOptionPane.showMessageDialog(null, "email field is invalid");
        }else if(emailField.getText().equalsIgnoreCase("e-mail")){
            JOptionPane.showMessageDialog(null, "email field is invalid");
        }else if(!malebtn.isSelected() && !femalebtn.isSelected()){
            JOptionPane.showMessageDialog(null, "select a gender");
        }else if(citizenshipstatus.getSelectedIndex() == 0){
            JOptionPane.showMessageDialog(null, "invalid citizenship status");
        } else{
            String mxText = null, fxText = null, gender = null;
                if(malebtn.isSelected()){
                     mxText = "Male";
                }if(femalebtn.isSelected()){
                     fxText = "Female";
                }

                if(!mxText.equals("")){
                    gender = mxText;
                }else if(!fxText.equals("")){
                    gender = fxText;
                }
            String program  = GETprogramName.getSelectedItem().toString();
            String school = GETschoolName.getSelectedItem().toString();
            String name = firstnameField.getText()+" "+midnameField.getText()+" "+lastnameField.getText();
            String city = cityField.getText();
            String state = stateField.getText();
            String zip = zipcodeField.getText();
            String country = countryField.getText();
            String resCC = residenceCCField.getText();
            String resAc = residenceACField.getText();
            String resPhone = residencenumField.getText();
            String offCC = officeCCField.getText();
            String offAC = officeACField.getText();
            String offPhone = officenumField.getText();
            String mail = emailField.getText();
            String sex = gender;
            String dob = monthbox.getSelectedItem().toString() +"-"+daybox.getSelectedItem().toString()+"-"+yearbox.getSelectedItem().toString();
            String citizen = citizenshipstatus.getSelectedItem().toString();
            this.dispose();
            new QualificationDetailsForm(program,school,name,city,state,zip,country,resCC,resAc,resPhone,offCC,offAC,offPhone,mail,sex,dob,citizen).setVisible(true);
            
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

        panel1 = new javax.swing.JPanel();
        headerPanel = new javax.swing.JPanel();
        menuformexit = new javax.swing.JLabel();
        menuformminimise = new javax.swing.JLabel();
        GOTOgsd = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();
        GETschoolName = new javax.swing.JComboBox();
        GETprogramName = new javax.swing.JComboBox();
        jSeparator4 = new javax.swing.JSeparator();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        firstnameField = new javax.swing.JTextField();
        jSeparator5 = new javax.swing.JSeparator();
        midnameField = new javax.swing.JTextField();
        jSeparator6 = new javax.swing.JSeparator();
        lastnameField = new javax.swing.JTextField();
        jSeparator7 = new javax.swing.JSeparator();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        cityField = new javax.swing.JTextField();
        jSeparator8 = new javax.swing.JSeparator();
        stateField = new javax.swing.JTextField();
        jSeparator9 = new javax.swing.JSeparator();
        countryField = new javax.swing.JTextField();
        jSeparator10 = new javax.swing.JSeparator();
        zipcodeField = new javax.swing.JTextField();
        jSeparator11 = new javax.swing.JSeparator();
        jLabel8 = new javax.swing.JLabel();
        residenceCCField = new javax.swing.JTextField();
        jSeparator12 = new javax.swing.JSeparator();
        residenceACField = new javax.swing.JTextField();
        jSeparator13 = new javax.swing.JSeparator();
        residencenumField = new javax.swing.JTextField();
        jSeparator14 = new javax.swing.JSeparator();
        jLabel9 = new javax.swing.JLabel();
        officeCCField = new javax.swing.JTextField();
        jSeparator15 = new javax.swing.JSeparator();
        officeACField = new javax.swing.JTextField();
        jSeparator16 = new javax.swing.JSeparator();
        officenumField = new javax.swing.JTextField();
        jSeparator17 = new javax.swing.JSeparator();
        jLabel10 = new javax.swing.JLabel();
        emailField = new javax.swing.JTextField();
        jSeparator18 = new javax.swing.JSeparator();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        malebtn = new javax.swing.JRadioButton();
        femalebtn = new javax.swing.JRadioButton();
        jLabel13 = new javax.swing.JLabel();
        daybox = new javax.swing.JComboBox();
        yearbox = new javax.swing.JComboBox();
        monthbox = new javax.swing.JComboBox();
        citizenshipstatus = new javax.swing.JComboBox();
        GOTOqfldeets = new javax.swing.JButton();
        GOTOqflldeets = new javax.swing.JLabel();
        GOTOstd = new javax.swing.JLabel();
        jSeparator19 = new javax.swing.JSeparator();
        errVal = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panel1.setBackground(new java.awt.Color(255, 255, 255));
        panel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        headerPanel.setBackground(new java.awt.Color(255, 0, 0));
        headerPanel.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                headerPanelMouseDragged(evt);
            }
        });
        headerPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                headerPanelMousePressed(evt);
            }
        });

        menuformexit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/exit1_32.png"))); // NOI18N
        menuformexit.setAutoscrolls(true);
        menuformexit.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        menuformexit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuformexitMouseClicked(evt);
            }
        });

        menuformminimise.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/minimize.png_32.png"))); // NOI18N
        menuformminimise.setAutoscrolls(true);
        menuformminimise.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        menuformminimise.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuformminimiseMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout headerPanelLayout = new javax.swing.GroupLayout(headerPanel);
        headerPanel.setLayout(headerPanelLayout);
        headerPanelLayout.setHorizontalGroup(
            headerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, headerPanelLayout.createSequentialGroup()
                .addContainerGap(666, Short.MAX_VALUE)
                .addComponent(menuformminimise)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(menuformexit)
                .addContainerGap())
        );
        headerPanelLayout.setVerticalGroup(
            headerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(headerPanelLayout.createSequentialGroup()
                .addGroup(headerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(menuformexit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(menuformminimise, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        panel1.add(headerPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 750, 40));

        GOTOgsd.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        GOTOgsd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/view_32.png"))); // NOI18N
        GOTOgsd.setText("Graduate Schools Data");
        GOTOgsd.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        GOTOgsd.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                GOTOgsdMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                GOTOgsdMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                GOTOgsdMouseExited(evt);
            }
        });
        panel1.add(GOTOgsd, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 60, -1, 40));
        panel1.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 100, 160, 10));

        jSeparator3.setBackground(new java.awt.Color(153, 153, 153));
        panel1.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 150, 750, -1));

        jLabel2.setBackground(new java.awt.Color(102, 102, 102));
        jLabel2.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/form_32.png"))); // NOI18N
        jLabel2.setText("Application Form");
        panel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 120, 230, 30));

        GETschoolName.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "School :" }));
        panel1.add(GETschoolName, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 210, 230, 30));

        GETprogramName.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Graduation Degree Program :" }));
        GETprogramName.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                GETprogramNamePopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });
        panel1.add(GETprogramName, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 180, 230, 30));

        jSeparator4.setBackground(new java.awt.Color(204, 204, 204));
        panel1.add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, 250, 750, 10));

        jLabel4.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel4.setText("    Applying For :");
        panel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 150, 150, 30));

        jLabel5.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel5.setText("Personal Details : ");
        panel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 250, 150, 30));

        firstnameField.setForeground(new java.awt.Color(102, 102, 102));
        firstnameField.setText("First");
        firstnameField.setToolTipText("");
        firstnameField.setBorder(null);
        firstnameField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                firstnameFieldFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                firstnameFieldFocusLost(evt);
            }
        });
        firstnameField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                firstnameFieldKeyTyped(evt);
            }
        });
        panel1.add(firstnameField, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 300, 200, 20));
        panel1.add(jSeparator5, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 320, 200, 10));

        midnameField.setForeground(new java.awt.Color(102, 102, 102));
        midnameField.setText("Middle");
        midnameField.setToolTipText("");
        midnameField.setBorder(null);
        midnameField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                midnameFieldFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                midnameFieldFocusLost(evt);
            }
        });
        midnameField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                midnameFieldKeyTyped(evt);
            }
        });
        panel1.add(midnameField, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 300, 220, 20));
        panel1.add(jSeparator6, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 320, 220, 20));

        lastnameField.setForeground(new java.awt.Color(102, 102, 102));
        lastnameField.setText("Last");
        lastnameField.setToolTipText("");
        lastnameField.setBorder(null);
        lastnameField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                lastnameFieldFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                lastnameFieldFocusLost(evt);
            }
        });
        lastnameField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                lastnameFieldKeyTyped(evt);
            }
        });
        panel1.add(lastnameField, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 300, 230, 20));
        panel1.add(jSeparator7, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 320, 230, 20));

        jLabel6.setText("Name : ");
        panel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 280, 70, 20));

        jLabel7.setText("Name : ");
        panel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 280, 70, 20));

        cityField.setForeground(new java.awt.Color(102, 102, 102));
        cityField.setText("City");
        cityField.setToolTipText("");
        cityField.setBorder(null);
        cityField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                cityFieldFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                cityFieldFocusLost(evt);
            }
        });
        cityField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                cityFieldKeyTyped(evt);
            }
        });
        panel1.add(cityField, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 350, 320, 20));
        panel1.add(jSeparator8, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 370, 320, 10));

        stateField.setForeground(new java.awt.Color(102, 102, 102));
        stateField.setText("State");
        stateField.setToolTipText("");
        stateField.setBorder(null);
        stateField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                stateFieldFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                stateFieldFocusLost(evt);
            }
        });
        stateField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                stateFieldKeyTyped(evt);
            }
        });
        panel1.add(stateField, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 350, 340, 20));
        panel1.add(jSeparator9, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 370, 340, 10));

        countryField.setForeground(new java.awt.Color(102, 102, 102));
        countryField.setText("Country");
        countryField.setToolTipText("");
        countryField.setBorder(null);
        countryField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                countryFieldFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                countryFieldFocusLost(evt);
            }
        });
        countryField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                countryFieldKeyTyped(evt);
            }
        });
        panel1.add(countryField, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 380, 340, 20));
        panel1.add(jSeparator10, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 400, 340, 10));

        zipcodeField.setForeground(new java.awt.Color(102, 102, 102));
        zipcodeField.setText("Zip Code");
        zipcodeField.setToolTipText("");
        zipcodeField.setBorder(null);
        zipcodeField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                zipcodeFieldFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                zipcodeFieldFocusLost(evt);
            }
        });
        zipcodeField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                zipcodeFieldKeyTyped(evt);
            }
        });
        panel1.add(zipcodeField, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 380, 320, 20));
        panel1.add(jSeparator11, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 400, 320, 10));

        jLabel8.setText("Address : ");
        panel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 330, 70, 20));

        residenceCCField.setForeground(new java.awt.Color(102, 102, 102));
        residenceCCField.setText("Country Code");
        residenceCCField.setToolTipText("");
        residenceCCField.setBorder(null);
        residenceCCField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                residenceCCFieldFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                residenceCCFieldFocusLost(evt);
            }
        });
        residenceCCField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                residenceCCFieldKeyTyped(evt);
            }
        });
        panel1.add(residenceCCField, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 430, 200, 20));
        panel1.add(jSeparator12, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 450, 200, 10));

        residenceACField.setForeground(new java.awt.Color(102, 102, 102));
        residenceACField.setText("Area Code");
        residenceACField.setToolTipText("");
        residenceACField.setBorder(null);
        residenceACField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                residenceACFieldFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                residenceACFieldFocusLost(evt);
            }
        });
        residenceACField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                residenceACFieldKeyTyped(evt);
            }
        });
        panel1.add(residenceACField, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 430, 220, 20));
        panel1.add(jSeparator13, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 450, 220, 10));

        residencenumField.setForeground(new java.awt.Color(102, 102, 102));
        residencenumField.setText("Phone");
        residencenumField.setToolTipText("");
        residencenumField.setBorder(null);
        residencenumField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                residencenumFieldFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                residencenumFieldFocusLost(evt);
            }
        });
        residencenumField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                residencenumFieldKeyTyped(evt);
            }
        });
        panel1.add(residencenumField, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 430, 230, 20));
        panel1.add(jSeparator14, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 450, 230, 10));

        jLabel9.setText(" Phone (Residence) : ");
        panel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 410, 130, 20));

        officeCCField.setForeground(new java.awt.Color(102, 102, 102));
        officeCCField.setText("Country Code");
        officeCCField.setToolTipText("");
        officeCCField.setBorder(null);
        officeCCField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                officeCCFieldFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                officeCCFieldFocusLost(evt);
            }
        });
        officeCCField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                officeCCFieldKeyTyped(evt);
            }
        });
        panel1.add(officeCCField, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 480, 200, 20));
        panel1.add(jSeparator15, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 500, 200, 10));

        officeACField.setForeground(new java.awt.Color(102, 102, 102));
        officeACField.setText("Area Code");
        officeACField.setToolTipText("");
        officeACField.setBorder(null);
        officeACField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                officeACFieldFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                officeACFieldFocusLost(evt);
            }
        });
        officeACField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                officeACFieldKeyTyped(evt);
            }
        });
        panel1.add(officeACField, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 480, 220, 20));
        panel1.add(jSeparator16, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 500, 220, 10));

        officenumField.setForeground(new java.awt.Color(102, 102, 102));
        officenumField.setText("Phone");
        officenumField.setToolTipText("");
        officenumField.setBorder(null);
        officenumField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                officenumFieldFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                officenumFieldFocusLost(evt);
            }
        });
        officenumField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                officenumFieldActionPerformed(evt);
            }
        });
        officenumField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                officenumFieldKeyTyped(evt);
            }
        });
        panel1.add(officenumField, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 480, 230, 20));
        panel1.add(jSeparator17, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 500, 230, 10));

        jLabel10.setText("Date Of Birth : ");
        panel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 580, 80, 20));

        emailField.setForeground(new java.awt.Color(102, 102, 102));
        emailField.setText("E-Mail");
        emailField.setToolTipText("");
        emailField.setBorder(null);
        emailField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                emailFieldFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                emailFieldFocusLost(evt);
            }
        });
        emailField.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                emailFieldMouseExited(evt);
            }
        });
        emailField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                emailFieldActionPerformed(evt);
            }
        });
        emailField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                emailFieldKeyTyped(evt);
            }
        });
        panel1.add(emailField, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 520, 440, 20));
        panel1.add(jSeparator18, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 540, 440, 10));

        jLabel11.setText(" Phone (Office) : ");
        panel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 460, 130, 20));

        jLabel12.setText(" Phone (Office) : ");
        panel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 460, 130, 20));

        malebtn.setText("Male");
        malebtn.setBorder(null);
        malebtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        malebtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                malebtnActionPerformed(evt);
            }
        });
        panel1.add(malebtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 550, 70, 20));

        femalebtn.setText("Female");
        femalebtn.setBorder(null);
        femalebtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        femalebtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                femalebtnActionPerformed(evt);
            }
        });
        panel1.add(femalebtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 550, 80, 20));

        jLabel13.setText("Gender : ");
        panel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 550, 80, 20));

        daybox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dayboxActionPerformed(evt);
            }
        });
        panel1.add(daybox, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 580, 60, 30));

        yearbox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        panel1.add(yearbox, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 580, 70, 30));

        monthbox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Month", "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December" }));
        monthbox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                monthboxItemStateChanged(evt);
            }
        });
        panel1.add(monthbox, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 580, 120, 30));

        citizenshipstatus.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Citizenship Status :", "U.S. Citizen", "U.S. Permanent Resident", "Not a U.S. Citizen", "Decline To State" }));
        panel1.add(citizenshipstatus, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 620, 230, 30));

        GOTOqfldeets.setText("   CONTINUE");
        GOTOqfldeets.setBorder(null);
        GOTOqfldeets.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        GOTOqfldeets.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                GOTOqfldeetsMouseClicked(evt);
            }
        });
        GOTOqfldeets.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GOTOqfldeetsActionPerformed(evt);
            }
        });
        panel1.add(GOTOqfldeets, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 660, 100, 30));

        GOTOqflldeets.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/continue_32.png"))); // NOI18N
        GOTOqflldeets.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        panel1.add(GOTOqflldeets, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 670, 30, 20));

        GOTOstd.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        GOTOstd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/view_32.png"))); // NOI18N
        GOTOstd.setText("Students' Details");
        GOTOstd.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        GOTOstd.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                GOTOstdMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                GOTOstdMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                GOTOstdMouseExited(evt);
            }
        });
        panel1.add(GOTOstd, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 60, 170, 40));
        panel1.add(jSeparator19, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 100, 120, 10));

        errVal.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        panel1.add(errVal, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 670, 360, 20));

        getContentPane().add(panel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 750, 700));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void menuformexitMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuformexitMouseClicked
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_menuformexitMouseClicked

    private void headerPanelMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_headerPanelMousePressed
        // TODO add your handling code here:
        xx = evt.getX();
        xy = evt.getY();
    }//GEN-LAST:event_headerPanelMousePressed

    private void headerPanelMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_headerPanelMouseDragged
        // TODO add your handling code here:
        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();

        this.setLocation(x - xx, y - xy);
    }//GEN-LAST:event_headerPanelMouseDragged

    private void GOTOgsdMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_GOTOgsdMouseEntered
        // TODO add your handling code here:
        ImageIcon img3 = new ImageIcon(getClass().getResource("/icons/arrow2_32.png"));
        GOTOgsd.setForeground(Color.blue);
        GOTOgsd.setIcon(img3);
    }//GEN-LAST:event_GOTOgsdMouseEntered

    private void GOTOgsdMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_GOTOgsdMouseExited
        // TODO add your handling code here:
        ImageIcon img33 = new ImageIcon(getClass().getResource("/icons/view_32.png"));
        GOTOgsd.setForeground(Color.black);
        GOTOgsd.setIcon(img33);
    }//GEN-LAST:event_GOTOgsdMouseExited

    private void officenumFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_officenumFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_officenumFieldActionPerformed

    private void emailFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_emailFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_emailFieldActionPerformed

    private void dayboxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dayboxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_dayboxActionPerformed

    private void firstnameFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_firstnameFieldFocusGained
        // TODO add your handling code here:
        if (firstnameField.getText().equals("First")) {
            firstnameField.setText("");
        }
        if (!firstnameField.getText().equals("First")) {
            firstnameField.setForeground(Color.black);
        }
    }//GEN-LAST:event_firstnameFieldFocusGained

    private void firstnameFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_firstnameFieldFocusLost
        // TODO add your handling code here:
        if (!firstnameField.getText().isEmpty()) {

        } else {
            firstnameField.setText("First");
            firstnameField.setForeground(placeholder);
        }
        if (!firstnameField.getText().equals("First")) {
            firstnameField.setForeground(Color.black);
        }
    }//GEN-LAST:event_firstnameFieldFocusLost

    private void midnameFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_midnameFieldFocusGained
        // TODO add your handling code here:
        if (midnameField.getText().equals("Middle")) {
            midnameField.setText("");
        }
        if (!midnameField.getText().equals("Middle")) {
            midnameField.setForeground(Color.black);
        }
    }//GEN-LAST:event_midnameFieldFocusGained

    private void midnameFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_midnameFieldFocusLost
        // TODO add your handling code here:
        if (!midnameField.getText().isEmpty()) {

        } else {
            midnameField.setText("Middle");
            midnameField.setForeground(placeholder);
        }
        if (!midnameField.getText().equals("Middle")) {
            midnameField.setForeground(Color.black);
        }
    }//GEN-LAST:event_midnameFieldFocusLost

    private void lastnameFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_lastnameFieldFocusGained
        // TODO add your handling code here:
        if (lastnameField.getText().equals("Last")) {
            lastnameField.setText("");
        }
        if (!lastnameField.getText().equals("Last")) {
            lastnameField.setForeground(Color.black);
        }
    }//GEN-LAST:event_lastnameFieldFocusGained

    private void lastnameFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_lastnameFieldFocusLost
        // TODO add your handling code here:
        if (!lastnameField.getText().isEmpty()) {

        } else {
            lastnameField.setText("Last");
            lastnameField.setForeground(placeholder);
        }
        if (!lastnameField.getText().equals("Last")) {
            lastnameField.setForeground(Color.black);
        }
    }//GEN-LAST:event_lastnameFieldFocusLost

    private void cityFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cityFieldFocusGained
        // TODO add your handling code here:
        if (cityField.getText().equals("City")) {
            cityField.setText("");
        }
        if (!cityField.getText().equals("City")) {
            cityField.setForeground(Color.black);
        }
    }//GEN-LAST:event_cityFieldFocusGained

    private void cityFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cityFieldFocusLost
        // TODO add your handling code here:
        if (!cityField.getText().isEmpty()) {

        } else {
            cityField.setText("City");
            cityField.setForeground(placeholder);
        }
        if (!cityField.getText().equals("City")) {
            cityField.setForeground(Color.black);
        }
    }//GEN-LAST:event_cityFieldFocusLost

    private void stateFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_stateFieldFocusGained
        // TODO add your handling code here:
        if (stateField.getText().equals("State")) {
            stateField.setText("");
        }
        if (!stateField.getText().equals("State")) {
            stateField.setForeground(Color.black);
        }
    }//GEN-LAST:event_stateFieldFocusGained

    private void stateFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_stateFieldFocusLost
        // TODO add your handling code here:
        if (!stateField.getText().isEmpty()) {

        } else {
            stateField.setText("State");
            stateField.setForeground(placeholder);
        }
        if (!stateField.getText().equals("State")) {
            stateField.setForeground(Color.black);
        }
    }//GEN-LAST:event_stateFieldFocusLost

    private void zipcodeFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_zipcodeFieldFocusGained
        // TODO add your handling code here:
        if (zipcodeField.getText().equals("Zip Code")) {
            zipcodeField.setText("");
        }
        if (!zipcodeField.getText().equals("Zip Code")) {
            zipcodeField.setForeground(Color.black);
        }
    }//GEN-LAST:event_zipcodeFieldFocusGained

    private void zipcodeFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_zipcodeFieldFocusLost
        // TODO add your handling code here:
        if (!zipcodeField.getText().isEmpty()) {

        } else {
            zipcodeField.setText("Zip Code");
            zipcodeField.setForeground(placeholder);
        }
        if (!zipcodeField.getText().equals("Zip Code")) {
            zipcodeField.setForeground(Color.black);
        }
    }//GEN-LAST:event_zipcodeFieldFocusLost

    private void countryFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_countryFieldFocusGained
        // TODO add your handling code here:
        if (countryField.getText().equals("Country")) {
            countryField.setText("");
        }
        if (!countryField.getText().equals("Country")) {
            countryField.setForeground(Color.black);
        }
    }//GEN-LAST:event_countryFieldFocusGained

    private void countryFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_countryFieldFocusLost
        // TODO add your handling code here:
        if (!countryField.getText().isEmpty()) {

        } else {
            countryField.setText("Country");
            countryField.setForeground(placeholder);
        }
        if (!countryField.getText().equals("Country")) {
            countryField.setForeground(Color.black);
        }
    }//GEN-LAST:event_countryFieldFocusLost

    private void residenceCCFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_residenceCCFieldFocusGained
        // TODO add your handling code here:
        if (residenceCCField.getText().equals("Country Code")) {
            residenceCCField.setText("");
        }
        if (!residenceCCField.getText().equals("Country Code")) {
            residenceCCField.setForeground(Color.black);
        }
    }//GEN-LAST:event_residenceCCFieldFocusGained

    private void residenceCCFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_residenceCCFieldFocusLost
        // TODO add your handling code here:
        if (!residenceCCField.getText().isEmpty()) {

        } else {
            residenceCCField.setText("Country Code");
            residenceCCField.setForeground(placeholder);
        }
        if (!residenceCCField.getText().equals("Country Code")) {
            residenceCCField.setForeground(Color.black);
        }
    }//GEN-LAST:event_residenceCCFieldFocusLost

    private void residenceACFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_residenceACFieldFocusGained
        // TODO add your handling code here:
        if (residenceACField.getText().equals("Area Code")) {
            residenceACField.setText("");
        }
        if (!residenceACField.getText().equals("Area Code")) {
            residenceACField.setForeground(Color.black);
        }
    }//GEN-LAST:event_residenceACFieldFocusGained

    private void residenceACFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_residenceACFieldFocusLost
        // TODO add your handling code here:
        if (!residenceACField.getText().isEmpty()) {

        } else {
            residenceACField.setText("Area Code");
            residenceACField.setForeground(placeholder);
        }
        if (!residenceACField.getText().equals("Area Code")) {
            residenceACField.setForeground(Color.black);
        }
    }//GEN-LAST:event_residenceACFieldFocusLost

    private void residencenumFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_residencenumFieldFocusGained
        // TODO add your handling code here:
        if (residencenumField.getText().equals("Phone")) {
            residencenumField.setText("");
        }
        if (!residencenumField.getText().equals("Phone")) {
            residencenumField.setForeground(Color.black);
        }
    }//GEN-LAST:event_residencenumFieldFocusGained

    private void residencenumFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_residencenumFieldFocusLost
        // TODO add your handling code here:
        if (!residencenumField.getText().isEmpty()) {

        } else {
            residencenumField.setText("Phone");
            residencenumField.setForeground(placeholder);
        }
        if (!residencenumField.getText().equals("Phone")) {
            residencenumField.setForeground(Color.black);
        }
    }//GEN-LAST:event_residencenumFieldFocusLost

    private void officeCCFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_officeCCFieldFocusGained
        // TODO add your handling code here:
        if (officeCCField.getText().equals("Country Code")) {
            officeCCField.setText("");
        }
        if (!officeCCField.getText().equals("Country Code")) {
            officeCCField.setForeground(Color.black);
        }
    }//GEN-LAST:event_officeCCFieldFocusGained

    private void officeCCFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_officeCCFieldFocusLost
        // TODO add your handling code here:
        if (!officeCCField.getText().isEmpty()) {

        } else {
            officeCCField.setText("Country Code");
            officeCCField.setForeground(placeholder);
        }
        if (!officeCCField.getText().equals("Country Code")) {
            officeCCField.setForeground(Color.black);
        }
    }//GEN-LAST:event_officeCCFieldFocusLost

    private void officeACFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_officeACFieldFocusGained
        // TODO add your handling code here:
        if (officeACField.getText().equals("Area Code")) {
            officeACField.setText("");
        }
        if (!officeACField.getText().equals("Area Code")) {
            officeACField.setForeground(Color.black);
        }

    }//GEN-LAST:event_officeACFieldFocusGained

    private void officeACFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_officeACFieldFocusLost
        // TODO add your handling code here:
        if (!officeACField.getText().isEmpty()) {

        } else {
            officeACField.setText("Area Code");
            officeACField.setForeground(placeholder);
        }
        if (!officeACField.getText().equals("Area Code")) {
            officeACField.setForeground(Color.black);
        }
    }//GEN-LAST:event_officeACFieldFocusLost

    private void officenumFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_officenumFieldFocusGained
        // TODO add your handling code here:
        if (officenumField.getText().equals("Phone")) {
            officenumField.setText("");
        }
        if (!officenumField.getText().equals("Phone")) {
            officenumField.setForeground(Color.black);
        }
    }//GEN-LAST:event_officenumFieldFocusGained

    private void officenumFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_officenumFieldFocusLost
        // TODO add your handling code here:
        if (!officenumField.getText().isEmpty()) {

        } else {
            officenumField.setText("Phone");
            officenumField.setForeground(placeholder);
        }
        if (!officenumField.getText().equals("Phone")) {
            officenumField.setForeground(Color.black);
        }
    }//GEN-LAST:event_officenumFieldFocusLost

    private void emailFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_emailFieldFocusGained
        // TODO add your handling code here:
        if (emailField.getText().equals("E-Mail")) {
            emailField.setText("");
        }
        if (!emailField.getText().equals("E-Mail")) {
            emailField.setForeground(Color.black);
        }
    }//GEN-LAST:event_emailFieldFocusGained

    private void emailFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_emailFieldFocusLost
        // TODO add your handling code here:
        if (!emailField.getText().isEmpty()) {

        } else {
            emailField.setText("E-Mail");
            emailField.setForeground(placeholder);
        }
        if (!emailField.getText().equals("E-Mail")) {
            emailField.setForeground(Color.black);
        }
    }//GEN-LAST:event_emailFieldFocusLost

    private void malebtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_malebtnActionPerformed
        // TODO add your handling code here:
        if (malebtn.isSelected()) {
            femalebtn.setSelected(false);
            //malebtn.setSelected(true);
        }
    }//GEN-LAST:event_malebtnActionPerformed

    private void femalebtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_femalebtnActionPerformed
        // TODO add your handling code here:
        if (femalebtn.isSelected()) {
            malebtn.setSelected(false);
            //femalebtn.setSelected(true);
        }
    }//GEN-LAST:event_femalebtnActionPerformed

    private void firstnameFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_firstnameFieldKeyTyped
        // TODO add your handling code here:
        if (evt.getKeyChar() >= 'a' && evt.getKeyChar() <= 'z') {

        } else if (evt.getKeyChar() >= 'A' && evt.getKeyChar() <= 'Z') {

        } else {
            evt.consume();
        }
    }//GEN-LAST:event_firstnameFieldKeyTyped

    private void midnameFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_midnameFieldKeyTyped
        // TODO add your handling code here:
        if (evt.getKeyChar() >= 'a' && evt.getKeyChar() <= 'z') {

        } else if (evt.getKeyChar() >= 'A' && evt.getKeyChar() <= 'Z') {

        } else {
            evt.consume();
        }
    }//GEN-LAST:event_midnameFieldKeyTyped

    private void lastnameFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_lastnameFieldKeyTyped
        // TODO add your handling code here:
        if (evt.getKeyChar() >= 'a' && evt.getKeyChar() <= 'z') {

        } else if (evt.getKeyChar() >= 'A' && evt.getKeyChar() <= 'Z') {

        } else {
            evt.consume();
        }
    }//GEN-LAST:event_lastnameFieldKeyTyped

    private void cityFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cityFieldKeyTyped
        // TODO add your handling code here:
        if (evt.getKeyChar() >= 'a' && evt.getKeyChar() <= 'z') {

        } else if (evt.getKeyChar() >= 'A' && evt.getKeyChar() <= 'Z') {

        } else {
            evt.consume();
        }
    }//GEN-LAST:event_cityFieldKeyTyped

    private void stateFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_stateFieldKeyTyped
        // TODO add your handling code here:
        if (evt.getKeyChar() >= 'a' && evt.getKeyChar() <= 'z') {

        } else if (evt.getKeyChar() >= 'A' && evt.getKeyChar() <= 'Z') {

        } else {
            evt.consume();
        }
    }//GEN-LAST:event_stateFieldKeyTyped

    private void countryFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_countryFieldKeyTyped
        // TODO add your handling code here:
        if (evt.getKeyChar() >= 'a' && evt.getKeyChar() <= 'z') {

        } else if (evt.getKeyChar() >= 'A' && evt.getKeyChar() <= 'Z') {

        } else {
            evt.consume();
        }
    }//GEN-LAST:event_countryFieldKeyTyped

    private void emailFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_emailFieldKeyTyped
        // TODO add your handling code here:

    }//GEN-LAST:event_emailFieldKeyTyped

    private void GOTOqfldeetsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_GOTOqfldeetsMouseClicked
        // TODO add your handling code here:


    }//GEN-LAST:event_GOTOqfldeetsMouseClicked

    private void emailFieldMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_emailFieldMouseExited
        // TODO add your handling code here:

    }//GEN-LAST:event_emailFieldMouseExited

    private void zipcodeFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_zipcodeFieldKeyTyped
        // TODO add your handling code here:
        if (evt.getKeyChar() >= '0' && evt.getKeyChar() <= '9') {

        } else if (evt.getKeyChar() == '+') {

        } else {
            evt.consume();
        }
    }//GEN-LAST:event_zipcodeFieldKeyTyped

    private void residenceCCFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_residenceCCFieldKeyTyped
        // TODO add your handling code here:
        if (evt.getKeyChar() >= '0' && evt.getKeyChar() <= '9') {

        } else if (evt.getKeyChar() == '+') {

        } else {
            evt.consume();
        }
    }//GEN-LAST:event_residenceCCFieldKeyTyped

    private void residenceACFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_residenceACFieldKeyTyped
        // TODO add your handling code here:
        if (evt.getKeyChar() >= '0' && evt.getKeyChar() <= '9') {

        } else if (evt.getKeyChar() == '+') {

        } else {
            evt.consume();
        }
    }//GEN-LAST:event_residenceACFieldKeyTyped

    private void officeCCFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_officeCCFieldKeyTyped
        // TODO add your handling code here:
        if (evt.getKeyChar() >= '0' && evt.getKeyChar() <= '9') {

        } else if (evt.getKeyChar() == '+') {

        } else {
            evt.consume();
        }
    }//GEN-LAST:event_officeCCFieldKeyTyped

    private void officeACFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_officeACFieldKeyTyped
        // TODO add your handling code here:
        if (evt.getKeyChar() >= '0' && evt.getKeyChar() <= '9') {

        } else if (evt.getKeyChar() == '+') {

        } else {
            evt.consume();
        }
    }//GEN-LAST:event_officeACFieldKeyTyped

    private void residencenumFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_residencenumFieldKeyTyped
        // TODO add your handling code here:
        if (evt.getKeyChar() >= '0' && evt.getKeyChar() <= '9') {

        } else {
            evt.consume();
        }
    }//GEN-LAST:event_residencenumFieldKeyTyped

    private void officenumFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_officenumFieldKeyTyped
        // TODO add your handling code here:
        if (evt.getKeyChar() >= '0' && evt.getKeyChar() <= '9') {

        } else {
            evt.consume();
        }
    }//GEN-LAST:event_officenumFieldKeyTyped

    private void GOTOstdMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_GOTOstdMouseEntered
        // TODO add your handling code here:
        ImageIcon img1 = new ImageIcon(getClass().getResource("/icons/arrow2_32.png"));
        GOTOstd.setForeground(Color.BLUE);
        GOTOstd.setIcon(img1);
    }//GEN-LAST:event_GOTOstdMouseEntered

    private void GOTOstdMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_GOTOstdMouseExited
        // TODO add your handling code here:
        ImageIcon img11 = new ImageIcon(getClass().getResource("/icons/view_32.png"));
        GOTOstd.setForeground(Color.BLACK);
        GOTOstd.setIcon(img11);
    }//GEN-LAST:event_GOTOstdMouseExited

    private void GOTOqfldeetsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GOTOqfldeetsActionPerformed
        // TODO add your handling code here:
        validateEntryDetails();
    }//GEN-LAST:event_GOTOqfldeetsActionPerformed

    private void menuformminimiseMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuformminimiseMouseClicked
        // TODO add your handling code here:
        this.setState(MenuForm.ICONIFIED);
    }//GEN-LAST:event_menuformminimiseMouseClicked

    private void GOTOgsdMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_GOTOgsdMouseClicked
        // TODO add your handling code here:
        dispose();
        new GraduateSchoolsForm().setVisible(true);
    }//GEN-LAST:event_GOTOgsdMouseClicked

    private void monthboxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_monthboxItemStateChanged
        // TODO add your handling code here:
        if(notInit){
            if(Integer.parseInt(yearbox.getSelectedItem().toString())%4 == 0){
                daybox.removeAllItems();
                int ind = monthbox.getSelectedIndex();
                int stopAt = lpDays[ind];
                for(int i = 1; i <= stopAt; i++){
                    daybox.addItem(i);
                }
            }else{
                daybox.removeAllItems();
                int ind = monthbox.getSelectedIndex();
                int stopAt = days[ind];
                for(int i = 1; i <= stopAt; i++){
                    daybox.addItem(i);
                }
            }
        }
    }//GEN-LAST:event_monthboxItemStateChanged

    private void GETprogramNamePopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_GETprogramNamePopupMenuWillBecomeInvisible
        // TODO add your handling code here:
         try {
            Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection("jdbc:sqlite:KnowledgeOnWebDB.db");
            //Statement stat = conn.createStatement();
            
            
            PreparedStatement pst5 = null;
            String programSelected = (String)GETprogramName.getSelectedItem();
            String sqlselect = "select schoolName from GraduateSchoolsData WHERE programName = ? ";
            pst5 = conn.prepareStatement(sqlselect);
            pst5.setString(1, programSelected);
            GETschoolName.removeAllItems();
            GETschoolName.addItem("School :");
            ResultSet rs0 = pst5.executeQuery();
            while (rs0.next()) {
                String schoolName = rs0.getString("schoolName");
                GETschoolName.addItem(schoolName);
            }
            rs0.close();
            conn.close();
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }//GEN-LAST:event_GETprogramNamePopupMenuWillBecomeInvisible

    private void GOTOstdMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_GOTOstdMouseClicked
        // TODO add your handling code here:
        this.dispose();
        new StudentDetailsForm().setVisible(true);
    }//GEN-LAST:event_GOTOstdMouseClicked

    /**
     * @param args the command line arguments
     */
   public static void main(String args[]) {
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
//            java.util.logging.Logger.getLogger(MenuForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(MenuForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(MenuForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(MenuForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//        //</editor-fold>
//
//        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
         public void run() {
               new MenuForm().setVisible(true);
            }
        });
   }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JComboBox GETprogramName;
    public javax.swing.JComboBox GETschoolName;
    private javax.swing.JLabel GOTOgsd;
    private javax.swing.JButton GOTOqfldeets;
    private javax.swing.JLabel GOTOqflldeets;
    private javax.swing.JLabel GOTOstd;
    public javax.swing.JComboBox citizenshipstatus;
    public javax.swing.JTextField cityField;
    public javax.swing.JTextField countryField;
    public javax.swing.JComboBox daybox;
    public javax.swing.JTextField emailField;
    private javax.swing.JLabel errVal;
    public javax.swing.JRadioButton femalebtn;
    public static javax.swing.JTextField firstnameField;
    private javax.swing.JPanel headerPanel;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator10;
    private javax.swing.JSeparator jSeparator11;
    private javax.swing.JSeparator jSeparator12;
    private javax.swing.JSeparator jSeparator13;
    private javax.swing.JSeparator jSeparator14;
    private javax.swing.JSeparator jSeparator15;
    private javax.swing.JSeparator jSeparator16;
    private javax.swing.JSeparator jSeparator17;
    private javax.swing.JSeparator jSeparator18;
    private javax.swing.JSeparator jSeparator19;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JSeparator jSeparator9;
    public javax.swing.JTextField lastnameField;
    public javax.swing.JRadioButton malebtn;
    private javax.swing.JLabel menuformexit;
    private javax.swing.JLabel menuformminimise;
    public javax.swing.JTextField midnameField;
    public javax.swing.JComboBox monthbox;
    public javax.swing.JTextField officeACField;
    public javax.swing.JTextField officeCCField;
    public javax.swing.JTextField officenumField;
    private javax.swing.JPanel panel1;
    public javax.swing.JTextField residenceACField;
    public javax.swing.JTextField residenceCCField;
    public javax.swing.JTextField residencenumField;
    public javax.swing.JTextField stateField;
    private javax.swing.JComboBox yearbox;
    public javax.swing.JTextField zipcodeField;
    // End of variables declaration//GEN-END:variables
}
