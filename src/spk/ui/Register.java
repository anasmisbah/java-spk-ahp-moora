/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spk.ui;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import spk.data.Auth;
import spk.data.Koneksi;
import spk.data.Pengguna;

/**
 *
 * @author anas
 */
public class Register extends javax.swing.JFrame {

    /**
     * Creates new form Register
     */
    private Connection con;
    private Statement stt;
    private ResultSet rss;
    public Register() {
        initComponents();
        con = Koneksi.getkoneksi();
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
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        jSeparator4 = new javax.swing.JSeparator();
        et_password = new javax.swing.JPasswordField();
        et_asaldaerah = new javax.swing.JTextField();
        et_nama = new javax.swing.JTextField();
        et_username = new javax.swing.JTextField();
        btn_daftar = new keeptoo.KButton();
        btn_masuk = new javax.swing.JLabel();
        jSeparator5 = new javax.swing.JSeparator();
        label_error_nama = new javax.swing.JLabel();
        label_error_username = new javax.swing.JLabel();
        label_error_password = new javax.swing.JLabel();
        label_error_asaldaerah = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(36, 47, 65));
        jPanel1.setPreferredSize(new java.awt.Dimension(100, 728));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(97, 212, 195));
        jPanel2.setPreferredSize(new java.awt.Dimension(500, 730));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Sistem Pendukung Keputusan");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Bibit Kelapa Sawit");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(156, 156, 156)
                        .addComponent(jLabel7))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(89, 89, 89)
                        .addComponent(jLabel1)))
                .addContainerGap(91, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(81, 81, 81)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel7)
                .addContainerGap(575, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 500, 730));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Password");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 370, -1, -1));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Daftar");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 130, -1, -1));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Nama Lengkap");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 210, -1, -1));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Username");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 290, -1, -1));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Asal Daerah");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 450, -1, -1));
        jPanel1.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 580, 160, 10));
        jPanel1.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 270, 310, 10));
        jPanel1.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 350, 310, 10));
        jPanel1.add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 430, 310, 10));

        et_password.setBackground(new java.awt.Color(36, 47, 65));
        et_password.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        et_password.setForeground(new java.awt.Color(255, 255, 255));
        et_password.setBorder(null);
        et_password.setCaretColor(new java.awt.Color(255, 255, 255));
        jPanel1.add(et_password, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 400, 310, 30));

        et_asaldaerah.setBackground(new java.awt.Color(36, 47, 65));
        et_asaldaerah.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        et_asaldaerah.setForeground(new java.awt.Color(255, 255, 255));
        et_asaldaerah.setBorder(null);
        et_asaldaerah.setCaretColor(new java.awt.Color(255, 255, 255));
        jPanel1.add(et_asaldaerah, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 480, 310, 30));

        et_nama.setBackground(new java.awt.Color(36, 47, 65));
        et_nama.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        et_nama.setForeground(new java.awt.Color(255, 255, 255));
        et_nama.setBorder(null);
        et_nama.setCaretColor(new java.awt.Color(255, 255, 255));
        jPanel1.add(et_nama, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 240, 310, 30));

        et_username.setBackground(new java.awt.Color(36, 47, 65));
        et_username.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        et_username.setForeground(new java.awt.Color(255, 255, 255));
        et_username.setBorder(null);
        et_username.setCaretColor(new java.awt.Color(255, 255, 255));
        jPanel1.add(et_username, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 320, 310, 30));

        btn_daftar.setBorder(null);
        btn_daftar.setText("Daftar");
        btn_daftar.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btn_daftar.setkHoverEndColor(new java.awt.Color(102, 0, 255));
        btn_daftar.setkHoverForeGround(new java.awt.Color(255, 255, 255));
        btn_daftar.setkHoverStartColor(new java.awt.Color(153, 153, 255));
        btn_daftar.setkPressedColor(new java.awt.Color(97, 212, 195));
        btn_daftar.setkStartColor(new java.awt.Color(97, 212, 195));
        btn_daftar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btn_daftarMousePressed(evt);
            }
        });
        jPanel1.add(btn_daftar, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 550, 150, 40));

        btn_masuk.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        btn_masuk.setForeground(new java.awt.Color(255, 255, 255));
        btn_masuk.setText("Sudah Punya Akun? Klik Disini");
        btn_masuk.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btn_masukMousePressed(evt);
            }
        });
        jPanel1.add(btn_masuk, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 560, -1, -1));
        jPanel1.add(jSeparator5, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 510, 310, 10));

        label_error_nama.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        label_error_nama.setForeground(new java.awt.Color(255, 102, 102));
        jPanel1.add(label_error_nama, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 270, -1, -1));

        label_error_username.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        label_error_username.setForeground(new java.awt.Color(255, 102, 102));
        jPanel1.add(label_error_username, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 350, -1, -1));

        label_error_password.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        label_error_password.setForeground(new java.awt.Color(255, 102, 102));
        jPanel1.add(label_error_password, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 430, -1, -1));

        label_error_asaldaerah.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        label_error_asaldaerah.setForeground(new java.awt.Color(255, 102, 102));
        jPanel1.add(label_error_asaldaerah, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 510, -1, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1006, 730));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btn_daftarMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_daftarMousePressed
        // TODO add your handling code here:
        String nama = this.et_nama.getText();
        String username = this.et_username.getText();
        String password = new String(this.et_password.getPassword());
        String asalDaerah = this.et_asaldaerah.getText();
        
        removeErrorEmpty(new JLabel[]{this.label_error_nama,this.label_error_username,this.label_error_password,this.label_error_asaldaerah});
        
         if(nama.equals("")){
            setErrorEmpty(this.label_error_nama, "nama tidak boleh kosong");
        }else if(username.equals("")){
            setErrorEmpty(this.label_error_username, "username tidak boleh kosong");
        }else if(password.equals("")){
            setErrorEmpty(this.label_error_password, "Password tidak boleh kosong");
        }else if(asalDaerah.equals("")){
            setErrorEmpty(this.label_error_asaldaerah, "asal daerah tidak boleh kosong");
        }
        else{
            if(Auth.register(username,nama,password,asalDaerah)){
                Auth.getPenggunaRegistered();
                this.dispose();
                Home home = new Home();
                home.setVisible(true);
            }else{
                JOptionPane.showMessageDialog(rootPane,"Register Gagal username telah digunakan","Perhatian",JOptionPane.WARNING_MESSAGE);
            }
        }
    }//GEN-LAST:event_btn_daftarMousePressed

    private void btn_masukMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_masukMousePressed
        this.dispose();
        Login login = new Login();
        login.setVisible(true);
    }//GEN-LAST:event_btn_masukMousePressed

    
    private void setErrorEmpty(JLabel label,String pesanError){
        label.setText(pesanError);
    }
    
    private void removeErrorEmpty(JLabel[] jlabel){
        for (int i = 0; i < jlabel.length; i++) {
            jlabel[i].setText("");
        }
    }
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
            java.util.logging.Logger.getLogger(Register.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Register.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Register.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Register.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Register().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private keeptoo.KButton btn_daftar;
    private javax.swing.JLabel btn_masuk;
    private javax.swing.JTextField et_asaldaerah;
    private javax.swing.JTextField et_nama;
    private javax.swing.JPasswordField et_password;
    private javax.swing.JTextField et_username;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JLabel label_error_asaldaerah;
    private javax.swing.JLabel label_error_nama;
    private javax.swing.JLabel label_error_password;
    private javax.swing.JLabel label_error_username;
    // End of variables declaration//GEN-END:variables
}
