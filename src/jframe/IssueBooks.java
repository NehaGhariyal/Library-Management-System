 /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package jframe;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author belie
 */
public class IssueBooks extends javax.swing.JFrame {

    /**
     * Creates new form IssueBooks
     */
    public IssueBooks() {
        initComponents();
    }
    
    //to get book details from database
    // Add ActionListener to the JDateChooser
    public void bookdetails(){
        int bookId= Integer.parseInt(is_studentbook.getText());
        
        try{
          Class.forName("com.mysql.cj.jdbc.Driver");
          Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/library_mgnt","root","");  
          String sql="Select * from book_details where book_id = ?";
          PreparedStatement pst =con.prepareStatement(sql);
          pst.setInt(1,bookId);
          ResultSet rs=pst.executeQuery();
         if(rs.next()){
             is_bookid.setText(rs.getString("book_id"));
             is_bookname.setText(rs.getString("book_name"));
             is_bookauthor.setText(rs.getString("author_name"));
             is_bookqty.setText(rs.getString("quantity"));
         }else{
             bid_invalid.setText("Invalid detail");
         }
         
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void studentdetails(){
        int studentId= Integer.parseInt(is_studentid.getText());
        
        try{
          Class.forName("com.mysql.cj.jdbc.Driver");
          Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/library_mgnt","root","");  
          String sql="Select * from student_details where student_id = ?";
          PreparedStatement pst =con.prepareStatement(sql); 

          pst.setInt(1,studentId);
          ResultSet rs=pst.executeQuery();
         if(rs.next()){
             is_sid.setText(rs.getString("student_id"));
             is_sname.setText(rs.getString("name"));
             is_scourse.setText(rs.getString("course"));
             is_ssection.setText(rs.getString("section"));
         }else{
             sid_invalid.setText("Invalid detail");
         }
         
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    
    //issue details into database
    public boolean issuedetails() throws ParseException{
        boolean isIssue=false;
        int studentId= Integer.parseInt(is_sid.getText());
        int bookId= Integer.parseInt(is_bookid.getText());
        String bookName = is_bookname.getText();  
        String studentName = is_sname.getText(); 
        Date selectedidate = i_issuedate.getDate();
        Date selectedrdate = i_returndate.getDate();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String formattedidate = sdf.format(selectedidate);
        String formattedrdate = sdf.format(selectedrdate);
        
        Date parsedidate = sdf.parse(formattedidate);
        Date parsedrdate = sdf.parse(formattedrdate);
        
        try{
           Connection con=DBconnection.getConnection();
           String sql="Insert into issue_details(book_id,book_name,student_id,student_name,issue_date,due_date,status) values(?,?,?,?,?,?,?)";
           PreparedStatement pst=con.prepareStatement(sql);
           pst.setInt(1, bookId);
           pst.setString(2, bookName);
           pst.setInt(3, studentId);
           pst.setString(4, studentName);
           pst.setDate(5, new java.sql.Date(parsedidate.getTime()));
           pst.setDate(6, new java.sql.Date(parsedrdate.getTime()));
           pst.setString(7, "Pending");
           
           int rowCount = pst.executeUpdate();
           if(rowCount>0){
               isIssue=true;
           }else{
               isIssue=false;
           }
        }catch(Exception e){
           e.printStackTrace();
        }
        
        return isIssue;
    }
    
    //for book count changes 
    public void bookcount(){
        int bookId = Integer.parseInt(is_studentbook.getText());
     try{
           Connection con=DBconnection.getConnection();
           String sql="Update book_details set quantity = quantity -1 where book_id = ?";
           PreparedStatement pst=con.prepareStatement(sql);
           pst.setInt(1, bookId);
          
           int rowCount = pst.executeUpdate();
           if(rowCount>0){
               int count=Integer.parseInt(is_bookqty.getText()); 
               is_bookqty.setText(Integer.toString(count- 1));
           }else{
               JOptionPane.showMessageDialog(this,"Can't updated book count");
           }
           
        }catch(Exception e){
           e.printStackTrace();
        }
        
    }
    
    //to check book already issued
    public boolean alreadyissued(){
        
    boolean alreadyissued= false;
        int bookId = Integer.parseInt(is_studentbook.getText());
        int studentId = Integer.parseInt(is_studentid.getText());
    
        try{
           Connection con=DBconnection.getConnection();
           String sql="Select * from issue_details where book_id = ? and student_id = ? and status = ? ";
           PreparedStatement pst=con.prepareStatement(sql);
           pst.setInt(1, bookId);
           pst.setInt(2, studentId);
           pst.setString(3, "pending");
          
           ResultSet rs= pst.executeQuery();
           if(rs.next()){
               alreadyissued =true;
               }else{
                alreadyissued =false;   
           }}
           catch(Exception e){
             e.printStackTrace();
        }
    return alreadyissued;
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
        jLabel4 = new javax.swing.JLabel();
        b_id = new app.bolivia.swing.JCTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        b_name = new app.bolivia.swing.JCTextField();
        jLabel9 = new javax.swing.JLabel();
        b_aname = new app.bolivia.swing.JCTextField();
        jLabel10 = new javax.swing.JLabel();
        b_qty = new app.bolivia.swing.JCTextField();
        rSMaterialButtonCircle4 = new rojerusan.RSMaterialButtonCircle();
        rSMaterialButtonCircle5 = new rojerusan.RSMaterialButtonCircle();
        rSMaterialButtonCircle6 = new rojerusan.RSMaterialButtonCircle();
        jLabel5 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        b_id1 = new app.bolivia.swing.JCTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        b_name1 = new app.bolivia.swing.JCTextField();
        jLabel13 = new javax.swing.JLabel();
        b_aname1 = new app.bolivia.swing.JCTextField();
        jLabel14 = new javax.swing.JLabel();
        b_qty1 = new app.bolivia.swing.JCTextField();
        rSMaterialButtonCircle7 = new rojerusan.RSMaterialButtonCircle();
        rSMaterialButtonCircle8 = new rojerusan.RSMaterialButtonCircle();
        rSMaterialButtonCircle9 = new rojerusan.RSMaterialButtonCircle();
        jLabel15 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        s_id = new app.bolivia.swing.JCTextField();
        jLabel24 = new javax.swing.JLabel();
        s_name = new app.bolivia.swing.JCTextField();
        jLabel25 = new javax.swing.JLabel();
        s_course = new app.bolivia.swing.JCTextField();
        jLabel26 = new javax.swing.JLabel();
        s_section = new app.bolivia.swing.JCTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tb_studentd = new rojerusan.RSTableMetro();
        main_panel = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel27 = new javax.swing.JLabel();
        is_bookid = new app.bolivia.swing.JCTextField();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        is_bookauthor = new app.bolivia.swing.JCTextField();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        is_bookname = new app.bolivia.swing.JCTextField();
        is_bookqty = new app.bolivia.swing.JCTextField();
        jLabel33 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel32 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        is_scourse = new app.bolivia.swing.JCTextField();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        is_sname = new app.bolivia.swing.JCTextField();
        is_ssection = new app.bolivia.swing.JCTextField();
        is_sid = new app.bolivia.swing.JCTextField();
        jLabel38 = new javax.swing.JLabel();
        bid_invalid = new javax.swing.JLabel();
        is_studentbook = new app.bolivia.swing.JCTextField();
        is_studentid = new app.bolivia.swing.JCTextField();
        jLabel40 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        isbtn = new rojerusan.RSMaterialButtonCircle();
        jLabel43 = new javax.swing.JLabel();
        sid_invalid = new javax.swing.JLabel();
        i_returndate = new com.toedter.calendar.JDateChooser();
        i_issuedate = new com.toedter.calendar.JDateChooser();

        jPanel1.setBackground(new java.awt.Color(153, 0, 0));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 0, 36)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText(" Add Book");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 60, 180, 70));

        b_id.setBackground(new java.awt.Color(153, 0, 0));
        b_id.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 255, 255)));
        b_id.setForeground(new java.awt.Color(255, 255, 255));
        b_id.setToolTipText("");
        b_id.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        b_id.setPhColor(new java.awt.Color(255, 255, 255));
        b_id.setPlaceholder("Enter Book Id");
        b_id.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                b_idFocusLost(evt);
            }
        });
        b_id.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_idActionPerformed(evt);
            }
        });
        jPanel1.add(b_id, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 170, -1, -1));

        jLabel7.setFont(new java.awt.Font("Nirmala UI Semilight", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Book Id ");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 160, 100, 60));

        jLabel8.setFont(new java.awt.Font("Nirmala UI Semilight", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Book Name ");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 220, 140, 60));

        b_name.setBackground(new java.awt.Color(153, 0, 0));
        b_name.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 255, 255)));
        b_name.setForeground(new java.awt.Color(255, 255, 255));
        b_name.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        b_name.setPhColor(new java.awt.Color(255, 255, 255));
        b_name.setPlaceholder("Enter Book Name");
        b_name.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                b_nameFocusLost(evt);
            }
        });
        b_name.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_nameActionPerformed(evt);
            }
        });
        jPanel1.add(b_name, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 230, -1, -1));

        jLabel9.setFont(new java.awt.Font("Nirmala UI Semilight", 1, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Author Name ");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 280, 130, 60));

        b_aname.setBackground(new java.awt.Color(153, 0, 0));
        b_aname.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 255, 255)));
        b_aname.setForeground(new java.awt.Color(255, 255, 255));
        b_aname.setActionCommand("<Not Set>");
        b_aname.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        b_aname.setPhColor(new java.awt.Color(255, 255, 255));
        b_aname.setPlaceholder("Enter Author Name");
        b_aname.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                b_anameFocusLost(evt);
            }
        });
        b_aname.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_anameActionPerformed(evt);
            }
        });
        jPanel1.add(b_aname, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 290, -1, -1));

        jLabel10.setFont(new java.awt.Font("Nirmala UI Semilight", 1, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Quantity");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 340, 100, 60));

        b_qty.setBackground(new java.awt.Color(153, 0, 0));
        b_qty.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 255, 255)));
        b_qty.setForeground(new java.awt.Color(255, 255, 255));
        b_qty.setToolTipText("");
        b_qty.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        b_qty.setPhColor(new java.awt.Color(255, 255, 255));
        b_qty.setPlaceholder("Enter Quantity");
        b_qty.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                b_qtyFocusLost(evt);
            }
        });
        b_qty.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_qtyActionPerformed(evt);
            }
        });
        jPanel1.add(b_qty, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 350, -1, -1));

        rSMaterialButtonCircle4.setBackground(new java.awt.Color(255, 255, 255));
        rSMaterialButtonCircle4.setForeground(new java.awt.Color(0, 0, 0));
        rSMaterialButtonCircle4.setText("Delete");
        rSMaterialButtonCircle4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSMaterialButtonCircle4ActionPerformed(evt);
            }
        });
        jPanel1.add(rSMaterialButtonCircle4, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 580, 220, 60));

        rSMaterialButtonCircle5.setBackground(new java.awt.Color(255, 255, 255));
        rSMaterialButtonCircle5.setForeground(new java.awt.Color(0, 0, 0));
        rSMaterialButtonCircle5.setText("Update");
        rSMaterialButtonCircle5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSMaterialButtonCircle5ActionPerformed(evt);
            }
        });
        jPanel1.add(rSMaterialButtonCircle5, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 510, 220, 60));

        rSMaterialButtonCircle6.setBackground(new java.awt.Color(255, 255, 255));
        rSMaterialButtonCircle6.setForeground(new java.awt.Color(0, 0, 0));
        rSMaterialButtonCircle6.setText("Add");
        rSMaterialButtonCircle6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSMaterialButtonCircle6ActionPerformed(evt);
            }
        });
        jPanel1.add(rSMaterialButtonCircle6, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 440, 220, 60));

        jLabel5.setFont(new java.awt.Font("Tw Cen MT", 1, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("X");
        jLabel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel5MouseClicked(evt);
            }
        });
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 0, 20, 50));

        jPanel2.setBackground(new java.awt.Color(153, 0, 0));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel6.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 0, 36)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText(" Add Book");
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 60, 180, 70));

        b_id1.setBackground(new java.awt.Color(153, 0, 0));
        b_id1.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 255, 255)));
        b_id1.setForeground(new java.awt.Color(255, 255, 255));
        b_id1.setToolTipText("");
        b_id1.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        b_id1.setPhColor(new java.awt.Color(255, 255, 255));
        b_id1.setPlaceholder("Enter Book Id");
        b_id1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                b_id1FocusLost(evt);
            }
        });
        b_id1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_id1ActionPerformed(evt);
            }
        });
        jPanel2.add(b_id1, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 170, -1, -1));

        jLabel11.setFont(new java.awt.Font("Nirmala UI Semilight", 1, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Book Id ");
        jPanel2.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 160, 100, 60));

        jLabel12.setFont(new java.awt.Font("Nirmala UI Semilight", 1, 18)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Book Name ");
        jPanel2.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 220, 140, 60));

        b_name1.setBackground(new java.awt.Color(153, 0, 0));
        b_name1.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 255, 255)));
        b_name1.setForeground(new java.awt.Color(255, 255, 255));
        b_name1.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        b_name1.setPhColor(new java.awt.Color(255, 255, 255));
        b_name1.setPlaceholder("Enter Book Name");
        b_name1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                b_name1FocusLost(evt);
            }
        });
        b_name1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_name1ActionPerformed(evt);
            }
        });
        jPanel2.add(b_name1, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 230, -1, -1));

        jLabel13.setFont(new java.awt.Font("Nirmala UI Semilight", 1, 18)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Author Name ");
        jPanel2.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 280, 130, 60));

        b_aname1.setBackground(new java.awt.Color(153, 0, 0));
        b_aname1.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 255, 255)));
        b_aname1.setForeground(new java.awt.Color(255, 255, 255));
        b_aname1.setActionCommand("<Not Set>");
        b_aname1.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        b_aname1.setPhColor(new java.awt.Color(255, 255, 255));
        b_aname1.setPlaceholder("Enter Author Name");
        b_aname1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                b_aname1FocusLost(evt);
            }
        });
        b_aname1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_aname1ActionPerformed(evt);
            }
        });
        jPanel2.add(b_aname1, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 290, -1, -1));

        jLabel14.setFont(new java.awt.Font("Nirmala UI Semilight", 1, 18)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Quantity");
        jPanel2.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 340, 100, 60));

        b_qty1.setBackground(new java.awt.Color(153, 0, 0));
        b_qty1.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 255, 255)));
        b_qty1.setForeground(new java.awt.Color(255, 255, 255));
        b_qty1.setToolTipText("");
        b_qty1.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        b_qty1.setPhColor(new java.awt.Color(255, 255, 255));
        b_qty1.setPlaceholder("Enter Quantity");
        b_qty1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                b_qty1FocusLost(evt);
            }
        });
        b_qty1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_qty1ActionPerformed(evt);
            }
        });
        jPanel2.add(b_qty1, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 350, -1, -1));

        rSMaterialButtonCircle7.setBackground(new java.awt.Color(255, 255, 255));
        rSMaterialButtonCircle7.setForeground(new java.awt.Color(0, 0, 0));
        rSMaterialButtonCircle7.setText("Delete");
        rSMaterialButtonCircle7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSMaterialButtonCircle7ActionPerformed(evt);
            }
        });
        jPanel2.add(rSMaterialButtonCircle7, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 580, 220, 60));

        rSMaterialButtonCircle8.setBackground(new java.awt.Color(255, 255, 255));
        rSMaterialButtonCircle8.setForeground(new java.awt.Color(0, 0, 0));
        rSMaterialButtonCircle8.setText("Update");
        rSMaterialButtonCircle8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSMaterialButtonCircle8ActionPerformed(evt);
            }
        });
        jPanel2.add(rSMaterialButtonCircle8, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 510, 220, 60));

        rSMaterialButtonCircle9.setBackground(new java.awt.Color(255, 255, 255));
        rSMaterialButtonCircle9.setForeground(new java.awt.Color(0, 0, 0));
        rSMaterialButtonCircle9.setText("Add");
        rSMaterialButtonCircle9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSMaterialButtonCircle9ActionPerformed(evt);
            }
        });
        jPanel2.add(rSMaterialButtonCircle9, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 440, 220, 60));

        jLabel15.setFont(new java.awt.Font("Tw Cen MT", 1, 24)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("X");
        jLabel15.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel15MouseClicked(evt);
            }
        });
        jPanel2.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 0, 20, 50));

        jLabel22.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 0, 36)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(255, 255, 255));
        jLabel22.setText(" Add Students");

        jLabel23.setFont(new java.awt.Font("Nirmala UI Semilight", 1, 18)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(255, 255, 255));
        jLabel23.setText("Student Id ");

        s_id.setBackground(new java.awt.Color(153, 0, 0));
        s_id.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 255, 255)));
        s_id.setForeground(new java.awt.Color(255, 255, 255));
        s_id.setToolTipText("");
        s_id.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        s_id.setPhColor(new java.awt.Color(255, 255, 255));
        s_id.setPlaceholder("Enter Student Id");
        s_id.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                s_idFocusLost(evt);
            }
        });
        s_id.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                s_idActionPerformed(evt);
            }
        });

        jLabel24.setFont(new java.awt.Font("Nirmala UI Semilight", 1, 18)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(255, 255, 255));
        jLabel24.setText("Student Name ");

        s_name.setBackground(new java.awt.Color(153, 0, 0));
        s_name.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 255, 255)));
        s_name.setForeground(new java.awt.Color(255, 255, 255));
        s_name.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        s_name.setPhColor(new java.awt.Color(255, 255, 255));
        s_name.setPlaceholder("Enter Student Name");
        s_name.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                s_nameFocusLost(evt);
            }
        });
        s_name.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                s_nameActionPerformed(evt);
            }
        });

        jLabel25.setFont(new java.awt.Font("Nirmala UI Semilight", 1, 18)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(255, 255, 255));
        jLabel25.setText("Course");

        s_course.setBackground(new java.awt.Color(153, 0, 0));
        s_course.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 255, 255)));
        s_course.setForeground(new java.awt.Color(255, 255, 255));
        s_course.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        s_course.setPhColor(new java.awt.Color(255, 255, 255));
        s_course.setPlaceholder("Enter Student Name");
        s_course.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                s_courseFocusLost(evt);
            }
        });
        s_course.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                s_courseActionPerformed(evt);
            }
        });

        jLabel26.setFont(new java.awt.Font("Nirmala UI Semilight", 1, 18)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(255, 255, 255));
        jLabel26.setText("Section");

        s_section.setBackground(new java.awt.Color(153, 0, 0));
        s_section.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 255, 255)));
        s_section.setForeground(new java.awt.Color(255, 255, 255));
        s_section.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        s_section.setPhColor(new java.awt.Color(255, 255, 255));
        s_section.setPlaceholder("Enter Student Name");
        s_section.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                s_sectionFocusLost(evt);
            }
        });
        s_section.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                s_sectionActionPerformed(evt);
            }
        });

        tb_studentd.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Student Id", "Name", "Course", "Section"
            }
        ));
        tb_studentd.setColorBackgoundHead(new java.awt.Color(153, 0, 0));
        tb_studentd.setPreferredSize(new java.awt.Dimension(200, 600));
        tb_studentd.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tb_studentdMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tb_studentd);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        main_panel.setBackground(new java.awt.Color(255, 255, 255));
        main_panel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel4.setBackground(new java.awt.Color(153, 0, 0));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel27.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 0, 36)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(255, 255, 255));
        jLabel27.setText(" Book Detail");
        jPanel4.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 20, 190, 30));

        is_bookid.setBackground(new java.awt.Color(153, 0, 0));
        is_bookid.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 255, 255)));
        is_bookid.setForeground(new java.awt.Color(255, 255, 255));
        is_bookid.setToolTipText("");
        is_bookid.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        is_bookid.setPhColor(new java.awt.Color(255, 255, 255));
        is_bookid.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                is_bookidFocusLost(evt);
            }
        });
        is_bookid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                is_bookidActionPerformed(evt);
            }
        });
        jPanel4.add(is_bookid, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 70, -1, -1));

        jLabel28.setFont(new java.awt.Font("Nirmala UI Semilight", 1, 18)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(255, 255, 255));
        jLabel28.setText("Book Id ");
        jPanel4.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 70, 90, 30));

        jLabel29.setFont(new java.awt.Font("Nirmala UI Semilight", 1, 18)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(255, 255, 255));
        jLabel29.setText("Book Name ");
        jPanel4.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 110, 130, 40));

        is_bookauthor.setBackground(new java.awt.Color(153, 0, 0));
        is_bookauthor.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 255, 255)));
        is_bookauthor.setForeground(new java.awt.Color(255, 255, 255));
        is_bookauthor.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        is_bookauthor.setPhColor(new java.awt.Color(255, 255, 255));
        is_bookauthor.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                is_bookauthorFocusLost(evt);
            }
        });
        is_bookauthor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                is_bookauthorActionPerformed(evt);
            }
        });
        jPanel4.add(is_bookauthor, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 150, -1, -1));

        jLabel30.setFont(new java.awt.Font("Nirmala UI Semilight", 1, 18)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(255, 255, 255));
        jLabel30.setText("Author");
        jPanel4.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 150, 80, 50));

        jLabel31.setFont(new java.awt.Font("Nirmala UI Semilight", 1, 18)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(255, 255, 255));
        jLabel31.setText("Quantity");
        jPanel4.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 190, 80, 50));

        is_bookname.setBackground(new java.awt.Color(153, 0, 0));
        is_bookname.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 255, 255)));
        is_bookname.setForeground(new java.awt.Color(255, 255, 255));
        is_bookname.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        is_bookname.setPhColor(new java.awt.Color(255, 255, 255));
        is_bookname.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                is_booknameFocusLost(evt);
            }
        });
        is_bookname.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                is_booknameActionPerformed(evt);
            }
        });
        jPanel4.add(is_bookname, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 110, -1, -1));

        is_bookqty.setBackground(new java.awt.Color(153, 0, 0));
        is_bookqty.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 255, 255)));
        is_bookqty.setForeground(new java.awt.Color(255, 255, 255));
        is_bookqty.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        is_bookqty.setPhColor(new java.awt.Color(255, 255, 255));
        is_bookqty.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                is_bookqtyFocusLost(evt);
            }
        });
        is_bookqty.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                is_bookqtyActionPerformed(evt);
            }
        });
        jPanel4.add(is_bookqty, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 200, -1, -1));

        main_panel.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 420, 440, 260));

        jLabel33.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 0, 48)); // NOI18N
        jLabel33.setForeground(new java.awt.Color(204, 0, 0));
        jLabel33.setText("ISSUE BOOKS");
        main_panel.add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 20, 250, 90));

        jLabel21.setBackground(new java.awt.Color(153, 0, 0));
        jLabel21.setFont(new java.awt.Font("Tw Cen MT", 1, 24)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(153, 0, 0));
        jLabel21.setText("X");
        jLabel21.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel21MouseClicked(evt);
            }
        });
        main_panel.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(1010, 0, 20, 50));

        jLabel3.setBackground(new java.awt.Color(153, 0, 0));
        jLabel3.setFont(new java.awt.Font("Tw Cen MT", 1, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(153, 0, 0));
        jLabel3.setText("<");
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
        });
        main_panel.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 30, 50));

        jPanel5.setBackground(new java.awt.Color(153, 0, 0));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel32.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 0, 36)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(255, 255, 255));
        jLabel32.setText("Student Detail");
        jPanel5.add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 20, 190, 30));

        jLabel34.setFont(new java.awt.Font("Nirmala UI Semilight", 1, 18)); // NOI18N
        jLabel34.setForeground(new java.awt.Color(255, 255, 255));
        jLabel34.setText("Student Id ");
        jPanel5.add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 70, 90, 30));

        jLabel35.setFont(new java.awt.Font("Nirmala UI Semilight", 1, 18)); // NOI18N
        jLabel35.setForeground(new java.awt.Color(255, 255, 255));
        jLabel35.setText("Student Name ");
        jPanel5.add(jLabel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 110, 130, 40));

        is_scourse.setBackground(new java.awt.Color(153, 0, 0));
        is_scourse.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 255, 255)));
        is_scourse.setForeground(new java.awt.Color(255, 255, 255));
        is_scourse.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        is_scourse.setPhColor(new java.awt.Color(255, 255, 255));
        is_scourse.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                is_scourseFocusLost(evt);
            }
        });
        is_scourse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                is_scourseActionPerformed(evt);
            }
        });
        jPanel5.add(is_scourse, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 150, -1, -1));

        jLabel36.setFont(new java.awt.Font("Nirmala UI Semilight", 1, 18)); // NOI18N
        jLabel36.setForeground(new java.awt.Color(255, 255, 255));
        jLabel36.setText("Course");
        jPanel5.add(jLabel36, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 150, 80, 50));

        jLabel37.setFont(new java.awt.Font("Nirmala UI Semilight", 1, 18)); // NOI18N
        jLabel37.setForeground(new java.awt.Color(255, 255, 255));
        jLabel37.setText("Section");
        jPanel5.add(jLabel37, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 190, 80, 50));

        is_sname.setBackground(new java.awt.Color(153, 0, 0));
        is_sname.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 255, 255)));
        is_sname.setForeground(new java.awt.Color(255, 255, 255));
        is_sname.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        is_sname.setPhColor(new java.awt.Color(255, 255, 255));
        is_sname.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                is_snameFocusLost(evt);
            }
        });
        is_sname.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                is_snameActionPerformed(evt);
            }
        });
        jPanel5.add(is_sname, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 110, -1, -1));

        is_ssection.setBackground(new java.awt.Color(153, 0, 0));
        is_ssection.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 255, 255)));
        is_ssection.setForeground(new java.awt.Color(255, 255, 255));
        is_ssection.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        is_ssection.setPhColor(new java.awt.Color(255, 255, 255));
        is_ssection.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                is_ssectionFocusLost(evt);
            }
        });
        is_ssection.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                is_ssectionActionPerformed(evt);
            }
        });
        jPanel5.add(is_ssection, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 200, -1, -1));

        is_sid.setBackground(new java.awt.Color(153, 0, 0));
        is_sid.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 255, 255)));
        is_sid.setForeground(new java.awt.Color(255, 255, 255));
        is_sid.setToolTipText("");
        is_sid.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        is_sid.setPhColor(new java.awt.Color(255, 255, 255));
        is_sid.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                is_sidFocusLost(evt);
            }
        });
        is_sid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                is_sidActionPerformed(evt);
            }
        });
        jPanel5.add(is_sid, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 70, -1, -1));

        main_panel.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 130, 440, 260));

        jLabel38.setFont(new java.awt.Font("Nirmala UI Semilight", 1, 24)); // NOI18N
        jLabel38.setForeground(new java.awt.Color(153, 0, 0));
        jLabel38.setText("Issue Date ");
        main_panel.add(jLabel38, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 390, 140, 30));

        bid_invalid.setFont(new java.awt.Font("Nirmala UI", 0, 14)); // NOI18N
        bid_invalid.setForeground(new java.awt.Color(153, 0, 0));
        main_panel.add(bid_invalid, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 340, 140, 30));

        is_studentbook.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(102, 0, 0)));
        is_studentbook.setForeground(new java.awt.Color(153, 0, 0));
        is_studentbook.setToolTipText("");
        is_studentbook.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        is_studentbook.setPhColor(new java.awt.Color(255, 255, 255));
        is_studentbook.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                is_studentbookFocusLost(evt);
            }
        });
        is_studentbook.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                is_studentbookActionPerformed(evt);
            }
        });
        main_panel.add(is_studentbook, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 300, -1, -1));

        is_studentid.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(102, 0, 0)));
        is_studentid.setForeground(new java.awt.Color(153, 0, 0));
        is_studentid.setToolTipText("");
        is_studentid.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        is_studentid.setPhColor(new java.awt.Color(255, 255, 255));
        is_studentid.setPlaceholder("Enter student id");
        is_studentid.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                is_studentidFocusLost(evt);
            }
        });
        is_studentid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                is_studentidActionPerformed(evt);
            }
        });
        main_panel.add(is_studentid, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 220, -1, -1));

        jLabel40.setFont(new java.awt.Font("Nirmala UI Semilight", 1, 24)); // NOI18N
        jLabel40.setForeground(new java.awt.Color(153, 0, 0));
        jLabel40.setText("Book Id ");
        main_panel.add(jLabel40, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 300, 140, 30));

        jLabel41.setFont(new java.awt.Font("Nirmala UI Semilight", 1, 24)); // NOI18N
        jLabel41.setForeground(new java.awt.Color(153, 0, 0));
        jLabel41.setText("Return Date");
        main_panel.add(jLabel41, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 470, 140, 30));

        isbtn.setBackground(new java.awt.Color(153, 0, 0));
        isbtn.setText("Issue");
        isbtn.setFont(new java.awt.Font("Roboto Medium", 0, 18)); // NOI18N
        isbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                isbtnActionPerformed(evt);
            }
        });
        main_panel.add(isbtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 560, 190, 60));

        jLabel43.setFont(new java.awt.Font("Nirmala UI Semilight", 1, 24)); // NOI18N
        jLabel43.setForeground(new java.awt.Color(153, 0, 0));
        jLabel43.setText("Student Id ");
        main_panel.add(jLabel43, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 220, 140, 30));

        sid_invalid.setFont(new java.awt.Font("Nirmala UI", 0, 14)); // NOI18N
        sid_invalid.setForeground(new java.awt.Color(153, 0, 0));
        main_panel.add(sid_invalid, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 260, 140, 30));

        i_returndate.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 0, 0)));
        i_returndate.setForeground(new java.awt.Color(153, 0, 0));
        i_returndate.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                i_returndateComponentResized(evt);
            }
        });
        main_panel.add(i_returndate, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 460, 200, 40));

        i_issuedate.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 0, 0)));
        i_issuedate.setForeground(new java.awt.Color(153, 0, 0));
        i_issuedate.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                i_issuedateFocusLost(evt);
            }
        });
        i_issuedate.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                i_issuedateComponentResized(evt);
            }
        });
        main_panel.add(i_issuedate, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 380, 200, 40));

        getContentPane().add(main_panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1040, 720));

        setSize(new java.awt.Dimension(1042, 718));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void b_idFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_b_idFocusLost

        // TODO add your handling code here:
    }//GEN-LAST:event_b_idFocusLost

    private void b_idActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_idActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_b_idActionPerformed

    private void b_nameFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_b_nameFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_b_nameFocusLost

    private void b_nameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_nameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_b_nameActionPerformed

    private void b_anameFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_b_anameFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_b_anameFocusLost

    private void b_anameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_anameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_b_anameActionPerformed

    private void b_qtyFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_b_qtyFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_b_qtyFocusLost

    private void b_qtyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_qtyActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_b_qtyActionPerformed

    private void rSMaterialButtonCircle4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSMaterialButtonCircle4ActionPerformed
     
        // TODO add your handling code here:
    }//GEN-LAST:event_rSMaterialButtonCircle4ActionPerformed

    private void rSMaterialButtonCircle5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSMaterialButtonCircle5ActionPerformed
     
        // TODO add your handling code here:
    }//GEN-LAST:event_rSMaterialButtonCircle5ActionPerformed

    private void rSMaterialButtonCircle6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSMaterialButtonCircle6ActionPerformed
       
        // TODO add your handling code here:
    }//GEN-LAST:event_rSMaterialButtonCircle6ActionPerformed

    private void jLabel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseClicked
        System.exit(0);
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel5MouseClicked

    private void b_id1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_b_id1FocusLost

        // TODO add your handling code here:
    }//GEN-LAST:event_b_id1FocusLost

    private void b_id1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_id1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_b_id1ActionPerformed

    private void b_name1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_b_name1FocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_b_name1FocusLost

    private void b_name1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_name1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_b_name1ActionPerformed

    private void b_aname1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_b_aname1FocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_b_aname1FocusLost

    private void b_aname1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_aname1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_b_aname1ActionPerformed

    private void b_qty1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_b_qty1FocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_b_qty1FocusLost

    private void b_qty1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_qty1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_b_qty1ActionPerformed

    private void rSMaterialButtonCircle7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSMaterialButtonCircle7ActionPerformed
      
        // TODO add your handling code here:
    }//GEN-LAST:event_rSMaterialButtonCircle7ActionPerformed

    private void rSMaterialButtonCircle8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSMaterialButtonCircle8ActionPerformed
       
        // TODO add your handling code here:
    }//GEN-LAST:event_rSMaterialButtonCircle8ActionPerformed

    private void rSMaterialButtonCircle9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSMaterialButtonCircle9ActionPerformed
       
        // TODO add your handling code here:
    }//GEN-LAST:event_rSMaterialButtonCircle9ActionPerformed

    private void jLabel15MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel15MouseClicked
        System.exit(0);
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel15MouseClicked

    private void jLabel21MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel21MouseClicked
        System.exit(0);
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel21MouseClicked

    private void s_idFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_s_idFocusLost

        // TODO add your handling code here:
    }//GEN-LAST:event_s_idFocusLost

    private void s_idActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_s_idActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_s_idActionPerformed

    private void s_nameFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_s_nameFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_s_nameFocusLost

    private void s_nameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_s_nameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_s_nameActionPerformed

    private void s_courseFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_s_courseFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_s_courseFocusLost

    private void s_courseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_s_courseActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_s_courseActionPerformed

    private void s_sectionFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_s_sectionFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_s_sectionFocusLost

    private void s_sectionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_s_sectionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_s_sectionActionPerformed

    private void is_bookidFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_is_bookidFocusLost

        // TODO add your handling code here:
    }//GEN-LAST:event_is_bookidFocusLost

    private void is_bookidActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_is_bookidActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_is_bookidActionPerformed

    private void is_bookauthorFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_is_bookauthorFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_is_bookauthorFocusLost

    private void is_bookauthorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_is_bookauthorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_is_bookauthorActionPerformed

    private void is_booknameFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_is_booknameFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_is_booknameFocusLost

    private void is_booknameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_is_booknameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_is_booknameActionPerformed

    private void is_bookqtyFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_is_bookqtyFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_is_bookqtyFocusLost

    private void is_bookqtyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_is_bookqtyActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_is_bookqtyActionPerformed

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked
        Home hpage = new Home();
        hpage.setVisible(true);
        dispose();
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel3MouseClicked

    private void is_studentbookFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_is_studentbookFocusLost
   if(is_bookid.getText().equals("")){
        bookdetails();
     }else{
        bookdetails();
   }

        // TODO add your handling code here:
    }//GEN-LAST:event_is_studentbookFocusLost

    private void is_studentbookActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_is_studentbookActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_is_studentbookActionPerformed

    private void is_scourseFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_is_scourseFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_is_scourseFocusLost

    private void is_scourseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_is_scourseActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_is_scourseActionPerformed

    private void is_snameFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_is_snameFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_is_snameFocusLost

    private void is_snameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_is_snameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_is_snameActionPerformed

    private void is_ssectionFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_is_ssectionFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_is_ssectionFocusLost

    private void is_ssectionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_is_ssectionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_is_ssectionActionPerformed

    private void tb_studentdMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_studentdMouseClicked
               // TODO add your handling code here:
    }//GEN-LAST:event_tb_studentdMouseClicked

    private void is_sidFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_is_sidFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_is_sidFocusLost

    private void is_sidActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_is_sidActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_is_sidActionPerformed

    private void is_studentidFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_is_studentidFocusLost
 if(is_studentid.getText().equals("")){
        studentdetails();
     }else{
      studentdetails();
 }

        // TODO add your handling code here:
    }//GEN-LAST:event_is_studentidFocusLost

    
     
    private void is_studentidActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_is_studentidActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_is_studentidActionPerformed

    private void isbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_isbtnActionPerformed

        if(is_bookqty.getText().equals("0")){
            JOptionPane.showMessageDialog(this,"Book is not available ");
        }else{
         
         if(alreadyissued()==false){
              try {
            if(issuedetails()==true){
                JOptionPane.showMessageDialog(this,"Book Issued");
                bookcount();
            }else{
                JOptionPane.showMessageDialog(this,"Book NotIssued");
            }
            
            // TODO add your handling code here:
        } catch (ParseException ex) {
            Logger.getLogger(IssueBooks.class.getName()).log(Level.SEVERE, null, ex);
          
        }
         }else{
             JOptionPane.showMessageDialog(this, "Student already issued book");
         }   
        }
        
         
         
        // TODO add your handling code here:
    }//GEN-LAST:event_isbtnActionPerformed

    private void i_returndateComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_i_returndateComponentResized
        // TODO add your handling code here:
    }//GEN-LAST:event_i_returndateComponentResized

    private void i_issuedateComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_i_issuedateComponentResized
        // TODO add your handling code here:
    }//GEN-LAST:event_i_issuedateComponentResized

    private void i_issuedateFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_i_issuedateFocusLost

      
    }//GEN-LAST:event_i_issuedateFocusLost

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(IssueBooks.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(IssueBooks.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(IssueBooks.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(IssueBooks.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new IssueBooks().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private app.bolivia.swing.JCTextField b_aname;
    private app.bolivia.swing.JCTextField b_aname1;
    private app.bolivia.swing.JCTextField b_id;
    private app.bolivia.swing.JCTextField b_id1;
    private app.bolivia.swing.JCTextField b_name;
    private app.bolivia.swing.JCTextField b_name1;
    private app.bolivia.swing.JCTextField b_qty;
    private app.bolivia.swing.JCTextField b_qty1;
    private javax.swing.JLabel bid_invalid;
    private com.toedter.calendar.JDateChooser i_issuedate;
    private com.toedter.calendar.JDateChooser i_returndate;
    private app.bolivia.swing.JCTextField is_bookauthor;
    private app.bolivia.swing.JCTextField is_bookid;
    private app.bolivia.swing.JCTextField is_bookname;
    private app.bolivia.swing.JCTextField is_bookqty;
    private app.bolivia.swing.JCTextField is_scourse;
    private app.bolivia.swing.JCTextField is_sid;
    private app.bolivia.swing.JCTextField is_sname;
    private app.bolivia.swing.JCTextField is_ssection;
    private app.bolivia.swing.JCTextField is_studentbook;
    private app.bolivia.swing.JCTextField is_studentid;
    private rojerusan.RSMaterialButtonCircle isbtn;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel main_panel;
    private rojerusan.RSMaterialButtonCircle rSMaterialButtonCircle4;
    private rojerusan.RSMaterialButtonCircle rSMaterialButtonCircle5;
    private rojerusan.RSMaterialButtonCircle rSMaterialButtonCircle6;
    private rojerusan.RSMaterialButtonCircle rSMaterialButtonCircle7;
    private rojerusan.RSMaterialButtonCircle rSMaterialButtonCircle8;
    private rojerusan.RSMaterialButtonCircle rSMaterialButtonCircle9;
    private app.bolivia.swing.JCTextField s_course;
    private app.bolivia.swing.JCTextField s_id;
    private app.bolivia.swing.JCTextField s_name;
    private app.bolivia.swing.JCTextField s_section;
    private javax.swing.JLabel sid_invalid;
    private rojerusan.RSTableMetro tb_studentd;
    // End of variables declaration//GEN-END:variables
}
