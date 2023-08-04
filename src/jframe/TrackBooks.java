/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package jframe;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.*;
import javax.swing.table.*;

/**
 *
 * @author belie
 */
public class TrackBooks extends javax.swing.JFrame {

    /**
     * Creates new form TrackBooks
     */
    
    int bookId, quantity;
    String bookName, author;
    DefaultTableModel model;
    public TrackBooks() {
        initComponents();
        Insertbookdetails();
    }

    
    public void Insertbookdetails(){
        try{
           Class.forName("com.mysql.cj.jdbc.Driver");
           Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/library_mgnt","root","");
           Statement st=con.createStatement();
           ResultSet rs= st.executeQuery("Select * from book_details");
           
           while (rs.next()){
               String bookId=rs.getString("book_id");
               String bookName=rs.getString("book_name");
               String author=rs.getString("author_name");
               int quantity= rs.getInt("quantity");
               
               Object[] obj={bookId,bookName,author,quantity};
               model=(DefaultTableModel)tb_bookd.getModel();
               model.addRow(obj);
           }
           
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    //for adding
    public boolean add_book(){
        boolean isAdded = false;
        bookId= Integer.parseInt(b_id.getText());
        bookName = b_name.getText();
        author = b_name.getText();
        quantity= Integer.parseInt(b_qty.getText());
        
        try{
          Class.forName("com.mysql.cj.jdbc.Driver");
          Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/library_mgnt","root","");  
          String sql="insert into book_details values(?,?,?,?)";
          PreparedStatement pst =con.prepareStatement(sql);
          pst.setInt(1,bookId);
          pst.setString(2,bookName);
          pst.setString(3,author);
          pst.setInt(4,quantity);
          
          int rowCount = pst.executeUpdate();
          if(rowCount>0){
              isAdded=true;
          }else{
              isAdded=false;
          }
          
        }catch(Exception e){
            e.printStackTrace();
        }
    return isAdded;
    }
    //for clr
    public void clrTable(){
        DefaultTableModel model= (DefaultTableModel) tb_bookd.getModel();
        model.setRowCount(0);
    }
    
    //for update
    public boolean updatebook(){
        boolean isUpdated = false;
        bookId= Integer.parseInt(b_id.getText());
        bookName = b_name.getText();
        author = b_aname.getText();
        quantity= Integer.parseInt(b_qty.getText());
        
        try{
          Class.forName("com.mysql.cj.jdbc.Driver");
          Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/library_mgnt","root","");  
          String sql="update book_details set book_name=?, author_name = ?,quantity=? where book_id = ?";
          PreparedStatement pst =con.prepareStatement(sql);
          pst.setString(1,bookName);
          pst.setString(2,author);          
          pst.setInt(3,quantity);
          pst.setInt(4,bookId);

          
          int rowCount= pst.executeUpdate();
          if(rowCount>0){
              isUpdated=true;
          }else{
              isUpdated=false;
          }
        }
        catch(Exception e){
            e.printStackTrace();
            }
        return isUpdated;
    }
    
    //to delete
    public boolean deletebooks(){
        boolean isDeleted = false;
        bookId= Integer.parseInt(b_id.getText());
        
        try{
          Class.forName("com.mysql.cj.jdbc.Driver");
          Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/library_mgnt","root","");  
          String sql="delete from book_details where book_id = ?";
          PreparedStatement pst =con.prepareStatement(sql);
          pst.setInt(1,bookId);
          
          int rowCount= pst.executeUpdate();
          if(rowCount>0){
              isDeleted = true;
          }else{
              isDeleted=false;
          }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return isDeleted;
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
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tb_bookd = new rojerusan.RSTableMetro();
        jLabel15 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 0, 480, 690));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setForeground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setBackground(new java.awt.Color(153, 0, 0));
        jLabel3.setFont(new java.awt.Font("Tw Cen MT", 1, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(153, 0, 0));
        jLabel3.setText("<");
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
        });
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 30, 50));

        tb_bookd.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Book Id", "Name", "Author", "Quantity"
            }
        ));
        tb_bookd.setColorBackgoundHead(new java.awt.Color(153, 0, 0));
        tb_bookd.setPreferredSize(new java.awt.Dimension(200, 600));
        tb_bookd.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tb_bookdMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tb_bookd);

        jPanel2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 190, 400, 410));

        jLabel15.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 24)); // NOI18N
        jLabel15.setText("Book Details");
        jPanel2.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 120, 160, 50));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 480, 680));

        setSize(new java.awt.Dimension(958, 683));
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
        if(deletebooks()==true){
             JOptionPane.showMessageDialog(this,"Record Deleted");
             clrTable();
             Insertbookdetails();
         }else{
             JOptionPane.showMessageDialog(this,"Record Not Deleted");
         }
        // TODO add your handling code here:
    }//GEN-LAST:event_rSMaterialButtonCircle4ActionPerformed

    private void rSMaterialButtonCircle5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSMaterialButtonCircle5ActionPerformed
         if(updatebook()==true){
             JOptionPane.showMessageDialog(this,"Record Updated");
             clrTable(); 
             Insertbookdetails();
         }else{
             JOptionPane.showMessageDialog(this,"Record Not Updated");
         }
        // TODO add your handling code here:
    }//GEN-LAST:event_rSMaterialButtonCircle5ActionPerformed

    private void rSMaterialButtonCircle6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSMaterialButtonCircle6ActionPerformed
         if(add_book()==true){
             JOptionPane.showMessageDialog(this,"Record Added");
             clrTable();
             Insertbookdetails();
         }else{
             JOptionPane.showMessageDialog(this,"Record Not Added");
         }
        // TODO add your handling code here:
    }//GEN-LAST:event_rSMaterialButtonCircle6ActionPerformed

    private void jLabel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseClicked
        System.exit(0);
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel5MouseClicked

    private void tb_bookdMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_bookdMouseClicked
         int rowno=tb_bookd.getSelectedRow();
         TableModel model=tb_bookd.getModel();
         
         b_id.setText(model.getValueAt(rowno,0).toString());
         b_name.setText(model.getValueAt(rowno,1).toString());
         b_aname.setText(model.getValueAt(rowno,2).toString());
         b_qty.setText(model.getValueAt(rowno,3).toString());
   
        // TODO add your handling code here:
    }//GEN-LAST:event_tb_bookdMouseClicked

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked
        Home hpage = new Home();
        hpage.setVisible(true);
        dispose();
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel3MouseClicked

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
            java.util.logging.Logger.getLogger(TrackBooks.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TrackBooks.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TrackBooks.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TrackBooks.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TrackBooks().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private app.bolivia.swing.JCTextField b_aname;
    private app.bolivia.swing.JCTextField b_id;
    private app.bolivia.swing.JCTextField b_name;
    private app.bolivia.swing.JCTextField b_qty;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private rojerusan.RSMaterialButtonCircle rSMaterialButtonCircle4;
    private rojerusan.RSMaterialButtonCircle rSMaterialButtonCircle5;
    private rojerusan.RSMaterialButtonCircle rSMaterialButtonCircle6;
    private rojerusan.RSTableMetro tb_bookd;
    // End of variables declaration//GEN-END:variables
}
