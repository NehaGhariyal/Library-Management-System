/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package jframe;
import java.sql.*;  
import java.sql.Connection;
import javax.swing.JOptionPane;
/**
 *
 * @author belie
 */
public class SignUp extends javax.swing.JFrame {

    /**
     * Creates new form Signup
     */
    public SignUp() {
        initComponents();
    }
    public void signupDetails(){
        String name= s_username.getText();
        String password= s_password.getText();
        String email= s_email.getText();
        String phone= s_phoneno.getText();
        
        try{
           Connection con=DBconnection.getConnection();
           String sql="insert into users(name,password,email,contact) values(?,?,?,?)";
           PreparedStatement pst=con.prepareStatement(sql);
           pst.setString(1,name);
           pst.setString(2,password);
           pst.setString(3,email);
           pst.setString(4,phone);
           
           int updateRowCount= pst.executeUpdate();
           if(updateRowCount >0){
               JOptionPane.showMessageDialog(this,"SignUp Successfully"); 
               ChangePassword lpage= new ChangePassword();
               lpage.setVisible(true);
               dispose();
           }else{
               JOptionPane.showMessageDialog(this,"SignUp Failed"); 
        }
            
           
        }catch(Exception e){
         e.printStackTrace();   
        }
    }  
    
    //Validation
    
    public boolean ValidateSignUp(){
        String name= s_username.getText();
        String password= s_password.getText();
        String email= s_email.getText();
        String phone= s_phoneno.getText();
        
        if(name.equals("")){
            JOptionPane.showMessageDialog(this,"Please enter username");
            return false; 
        } 
        if(password.equals("")){
            JOptionPane.showMessageDialog(this,"Please enter password");
            return false; 
        } 
        if(email.equals("")|| !email.matches("^(.+)@(\\S+)$")){
            JOptionPane.showMessageDialog(this,"Please enter valid email");
            return false; 
        } 
        if(phone.equals("")){
            JOptionPane.showMessageDialog(this,"Please enter contact number");
            return false; 
        } 
        return true;
    }
    
    public boolean Duplicateuser(){
        String name=s_username.getText();
        boolean isExist=false;
        try{
            Connection con=DBconnection.getConnection();
            PreparedStatement  pst = con.prepareStatement("Select * from users where name = ?"); 
            pst.setString(1,name);
            ResultSet rs=pst.executeQuery();
            if(rs.next()){
                isExist=true;
            }else{
                isExist=false;
            }
        } catch(Exception e){
            e.printStackTrace();
        }
        return isExist;
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        s_username = new app.bolivia.swing.JCTextField();
        jLabel7 = new javax.swing.JLabel();
        s_password = new app.bolivia.swing.JCTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        s_phoneno = new app.bolivia.swing.JCTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        s_email = new app.bolivia.swing.JCTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        rSMaterialButtonCircle2 = new rojerusan.RSMaterialButtonCircle();
        rSMaterialButtonCircle3 = new rojerusan.RSMaterialButtonCircle();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Tw Cen MT Condensed", 1, 24)); // NOI18N
        jLabel2.setText("Welcome to ");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 60, 110, 50));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/icons/main_logo.jpg"))); // NOI18N
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 200, 490, 460));

        jLabel6.setFont(new java.awt.Font("Nirmala UI Semilight", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(153, 51, 0));
        jLabel6.setText("From shelves to screen....");
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 180, 200, 20));

        jLabel1.setFont(new java.awt.Font("Tw Cen MT", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(204, 0, 0));
        jLabel1.setText("X");
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 0, 20, 40));

        jLabel15.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 0, 48)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(204, 0, 0));
        jLabel15.setText("DigiLibrary");
        jPanel2.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 100, 220, 90));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 0, 570, 690));

        jPanel1.setBackground(new java.awt.Color(153, 0, 0));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 0, 36)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Signup");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 50, 100, 70));

        jLabel5.setFont(new java.awt.Font("Nirmala UI Semilight", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/icons/icons8_Account_50px.png"))); // NOI18N
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 220, 60, 50));

        s_username.setBackground(new java.awt.Color(153, 0, 0));
        s_username.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 255, 255)));
        s_username.setForeground(new java.awt.Color(255, 255, 255));
        s_username.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        s_username.setPhColor(new java.awt.Color(255, 255, 255));
        s_username.setPlaceholder("Enter username.....");
        s_username.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                s_usernameFocusLost(evt);
            }
        });
        s_username.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                s_usernameActionPerformed(evt);
            }
        });
        jPanel1.add(s_username, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 230, -1, -1));

        jLabel7.setFont(new java.awt.Font("Nirmala UI Semilight", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Username ");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 220, 100, 60));

        s_password.setBackground(new java.awt.Color(153, 0, 0));
        s_password.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 255, 255)));
        s_password.setForeground(new java.awt.Color(255, 255, 255));
        s_password.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        s_password.setPhColor(new java.awt.Color(255, 255, 255));
        s_password.setPlaceholder("Enter password.....");
        s_password.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                s_passwordActionPerformed(evt);
            }
        });
        jPanel1.add(s_password, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 300, -1, -1));

        jLabel8.setFont(new java.awt.Font("Nirmala UI Semilight", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Password");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 290, 100, 60));

        jLabel9.setFont(new java.awt.Font("Nirmala UI Semilight", 1, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/icons/icons8_Forgot_Password_50px_4.png"))); // NOI18N
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 290, 60, 50));

        s_phoneno.setBackground(new java.awt.Color(153, 0, 0));
        s_phoneno.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 255, 255)));
        s_phoneno.setForeground(new java.awt.Color(255, 255, 255));
        s_phoneno.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        s_phoneno.setPhColor(new java.awt.Color(255, 255, 255));
        s_phoneno.setPlaceholder("Enter phone number.....");
        s_phoneno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                s_phonenoActionPerformed(evt);
            }
        });
        jPanel1.add(s_phoneno, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 440, -1, -1));

        jLabel10.setFont(new java.awt.Font("Nirmala UI Semilight", 1, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Phone No.");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 430, 100, 60));

        jLabel11.setFont(new java.awt.Font("Nirmala UI Semilight", 1, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/icons/icons8_Google_Mobile_50px.png"))); // NOI18N
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 430, 60, 50));

        s_email.setBackground(new java.awt.Color(153, 0, 0));
        s_email.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 255, 255)));
        s_email.setForeground(new java.awt.Color(255, 255, 255));
        s_email.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        s_email.setPhColor(new java.awt.Color(255, 255, 255));
        s_email.setPlaceholder("Enter email.....");
        s_email.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                s_emailActionPerformed(evt);
            }
        });
        jPanel1.add(s_email, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 370, -1, -1));

        jLabel12.setFont(new java.awt.Font("Nirmala UI Semilight", 1, 18)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Email");
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 360, 100, 60));

        jLabel13.setFont(new java.awt.Font("Nirmala UI Semilight", 1, 18)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/icons/icons8_Secured_Letter_50px.png"))); // NOI18N
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 360, 60, 50));

        jLabel14.setFont(new java.awt.Font("Nirmala UI Semilight", 2, 18)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Create a new account");
        jPanel1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 100, 180, 60));

        rSMaterialButtonCircle2.setBackground(new java.awt.Color(255, 255, 255));
        rSMaterialButtonCircle2.setForeground(new java.awt.Color(0, 0, 0));
        rSMaterialButtonCircle2.setText("Login");
        rSMaterialButtonCircle2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSMaterialButtonCircle2ActionPerformed(evt);
            }
        });
        jPanel1.add(rSMaterialButtonCircle2, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 550, 140, 50));

        rSMaterialButtonCircle3.setBackground(new java.awt.Color(255, 255, 255));
        rSMaterialButtonCircle3.setForeground(new java.awt.Color(0, 0, 0));
        rSMaterialButtonCircle3.setText("Sign Up");
        rSMaterialButtonCircle3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSMaterialButtonCircle3ActionPerformed(evt);
            }
        });
        jPanel1.add(rSMaterialButtonCircle3, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 550, 140, 50));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 480, 690));

        setSize(new java.awt.Dimension(1050, 685));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void s_usernameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_s_usernameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_s_usernameActionPerformed

    private void s_passwordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_s_passwordActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_s_passwordActionPerformed

    private void s_phonenoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_s_phonenoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_s_phonenoActionPerformed

    private void s_emailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_s_emailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_s_emailActionPerformed

    private void rSMaterialButtonCircle2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSMaterialButtonCircle2ActionPerformed
         Login lpage=new Login();
         lpage.setVisible(true);
         dispose();
        // TODO add your handling code here:
    }//GEN-LAST:event_rSMaterialButtonCircle2ActionPerformed

    private void rSMaterialButtonCircle3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSMaterialButtonCircle3ActionPerformed
       if(ValidateSignUp()==true){
            if(Duplicateuser()==false){
            signupDetails();
        }}else{
              // JOptionPane.showMessageDialog(this,"User already exist");
        }
     
        // TODO add your handling code here:
    }//GEN-LAST:event_rSMaterialButtonCircle3ActionPerformed

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        System.exit(0);
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel1MouseClicked

    private void s_usernameFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_s_usernameFocusLost
         if(Duplicateuser()==true){
            JOptionPane.showMessageDialog(this,"User already exist");
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_s_usernameFocusLost

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
            java.util.logging.Logger.getLogger(SignUp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SignUp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SignUp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SignUp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SignUp().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private rojerusan.RSMaterialButtonCircle rSMaterialButtonCircle2;
    private rojerusan.RSMaterialButtonCircle rSMaterialButtonCircle3;
    private app.bolivia.swing.JCTextField s_email;
    private app.bolivia.swing.JCTextField s_password;
    private app.bolivia.swing.JCTextField s_phoneno;
    private app.bolivia.swing.JCTextField s_username;
    // End of variables declaration//GEN-END:variables
}
