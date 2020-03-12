/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spk.ui;

import java.awt.Color;
import java.awt.Image;
import java.awt.List;
import java.io.File;
import java.sql.Connection;
import javax.swing.JPanel;
import spk.data.Auth;
import spk.data.Koneksi;
import spk.data.Pengguna;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Vector;
import javax.swing.ComboBoxModel;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import spk.data.Kriteria;
import javax.swing.table.DefaultTableModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import spk.data.ComboItem;
import spk.data.Grup;

import spk.data.Varietas;

/**
 *
 * @author MOTHAFUCKAS
 */
public class Admin extends javax.swing.JFrame {

    private Connection con;
    private Statement stt;
    private ResultSet rss;
    private Pengguna User = new Pengguna();
    private Grup grup = new Grup();
    private Varietas varietas = new Varietas();
    private DefaultTableModel model;
    Pengguna pengguna = Auth.penggunaLogin();
    Kriteria kriteria = new Kriteria();
    String path = "";

    /**
     * Creates new form Admin
     */
    public Admin() {
        initComponents();
        String nama = pengguna.getNama();
        welcomeText.setText("Hai, " + nama);

        setColor(menu_pengguna);
        pengguna_aktif.setOpaque(true);

        resetColor(new JPanel[]{menu_varietas}, new JPanel[]{varietas_aktif});
        panel_pengguna.setVisible(true);
    
        panel_varietas.setVisible(false);
        JDialog.setDefaultLookAndFeelDecorated(true);

    }

    private void InitTablePengguna() {
        model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("Nama");
        model.addColumn("Asal Daerah");
        model.addColumn("Username");
        model.addColumn("Password");
        model.addColumn("Role");
        TablePengguna.setModel(model);
        TablePengguna.removeColumn(TablePengguna.getColumnModel().getColumn(0));
        TablePengguna.setRowHeight(30);
    }

    private void TampilDataPengguna() {
        ArrayList<Pengguna> user = User.allPengguna();
        try {
            for (int i = 0; i < user.size(); i++) {
                Object[] record = new Object[6];
                record[0] = user.get(i).getId();
                record[1] = user.get(i).getNama();
                record[2] = user.get(i).getAsalDaerah();
                record[3] = user.get(i).getUsername();
                record[4] = user.get(i).getPassword();
                record[5] = user.get(i).getRole();

                model.addRow(record);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }




    private void InitTableVarietas() {
        model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("Nama");
        model.addColumn("Grup");
        model.addColumn("Rerata Jumlah Tandan");
        model.addColumn("Rerata Berat Tandan");
        model.addColumn("Potensi TBS");
        model.addColumn("Rendemen");
        model.addColumn("Potensi CPO");
        model.addColumn("Tinggi");
        model.addColumn("Panjang Pelepah");
        model.addColumn("Kerapatan Tanam");

        TableVarietas.setModel(model);
        TableVarietas.removeColumn(TableVarietas.getColumnModel().getColumn(0));
        TableVarietas.setRowHeight(30);
    }

    private void TampilDataVarietas() {
        ArrayList<Varietas> variety = varietas.allVarietas();

        try {
            for (int i = 0; i < variety.size(); i++) {
                Object[] record = new Object[11];
                record[0] = variety.get(i).getId();
                record[1] = variety.get(i).getNama();
                record[2] = variety.get(i).getGroup();
                record[3] = variety.get(i).getRerataJumlahTandan();
                record[4] = variety.get(i).getRerataBeratTandan();
                record[5] = variety.get(i).getPotensiTBS();
                record[6] = variety.get(i).getRendemen();
                record[7] = variety.get(i).getPotensiCPO();
                record[8] = variety.get(i).getTinggi();
                record[9] = variety.get(i).getPanjangPelepah();
                record[10] = variety.get(i).getKerapatanTanam();
                model.addRow(record);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void setColor(JPanel panel) {
        panel.setBackground(new Color(41, 57, 60));
    }

    private void resetColor(JPanel[] panel, JPanel[] indicators) {
        for (int i = 0; i < panel.length; i++) {
            panel[i].setBackground(new Color(23, 35, 51));
        }
        for (int i = 0; i < indicators.length; i++) {
            indicators[i].setOpaque(false);
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

        side_bar = new javax.swing.JPanel();
        menu_pengguna = new javax.swing.JPanel();
        pengguna_aktif = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        menu_varietas = new javax.swing.JPanel();
        varietas_aktif = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        welcomeText = new javax.swing.JLabel();
        kButton1 = new keeptoo.KButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        ContainerPanel = new javax.swing.JPanel();
        panel_pengguna = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        namaPengguna = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        asalDaerahPengguna = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        usernamePengguna = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        passwordPengguna = new javax.swing.JTextField();
        rolePengguna = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        TablePengguna = new javax.swing.JTable();
        SimpanPengguna = new javax.swing.JButton();
        UpdatePengguna = new javax.swing.JButton();
        HapusPengguna = new javax.swing.JButton();
        ResetPengguna = new javax.swing.JButton();
        panel_varietas = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        namaVarietas = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        SimpanVarietas = new javax.swing.JButton();
        UpdateVarietas = new javax.swing.JButton();
        HapusVarietas = new javax.swing.JButton();
        ResetVarietas = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        TableVarietas = new javax.swing.JTable();
        KT = new javax.swing.JSpinner();
        jLabel18 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        RJT = new javax.swing.JSpinner();
        RBT = new javax.swing.JSpinner();
        TBS = new javax.swing.JSpinner();
        Rendemen = new javax.swing.JSpinner();
        CPO = new javax.swing.JSpinner();
        Tinggi = new javax.swing.JSpinner();
        PP = new javax.swing.JSpinner();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        grupField = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        fotoField = new javax.swing.JLabel();
        chooseFile = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        side_bar.setBackground(new java.awt.Color(23, 35, 51));
        side_bar.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        menu_pengguna.setBackground(new java.awt.Color(41, 57, 80));
        menu_pengguna.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                menu_penggunaMousePressed(evt);
            }
        });

        pengguna_aktif.setPreferredSize(new java.awt.Dimension(5, 40));

        javax.swing.GroupLayout pengguna_aktifLayout = new javax.swing.GroupLayout(pengguna_aktif);
        pengguna_aktif.setLayout(pengguna_aktifLayout);
        pengguna_aktifLayout.setHorizontalGroup(
            pengguna_aktifLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 5, Short.MAX_VALUE)
        );
        pengguna_aktifLayout.setVerticalGroup(
            pengguna_aktifLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Pengguna");

        javax.swing.GroupLayout menu_penggunaLayout = new javax.swing.GroupLayout(menu_pengguna);
        menu_pengguna.setLayout(menu_penggunaLayout);
        menu_penggunaLayout.setHorizontalGroup(
            menu_penggunaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menu_penggunaLayout.createSequentialGroup()
                .addComponent(pengguna_aktif, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(69, 69, 69)
                .addComponent(jLabel3)
                .addGap(0, 74, Short.MAX_VALUE))
        );
        menu_penggunaLayout.setVerticalGroup(
            menu_penggunaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pengguna_aktif, javax.swing.GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)
            .addGroup(menu_penggunaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        side_bar.add(menu_pengguna, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 87, 210, -1));

        menu_varietas.setBackground(new java.awt.Color(23, 35, 51));
        menu_varietas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                menu_varietasMousePressed(evt);
            }
        });

        varietas_aktif.setOpaque(false);
        varietas_aktif.setPreferredSize(new java.awt.Dimension(5, 40));

        javax.swing.GroupLayout varietas_aktifLayout = new javax.swing.GroupLayout(varietas_aktif);
        varietas_aktif.setLayout(varietas_aktifLayout);
        varietas_aktifLayout.setHorizontalGroup(
            varietas_aktifLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 5, Short.MAX_VALUE)
        );
        varietas_aktifLayout.setVerticalGroup(
            varietas_aktifLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Varietas");

        javax.swing.GroupLayout menu_varietasLayout = new javax.swing.GroupLayout(menu_varietas);
        menu_varietas.setLayout(menu_varietasLayout);
        menu_varietasLayout.setHorizontalGroup(
            menu_varietasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menu_varietasLayout.createSequentialGroup()
                .addComponent(varietas_aktif, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(74, 74, 74)
                .addComponent(jLabel4)
                .addContainerGap(82, Short.MAX_VALUE))
        );
        menu_varietasLayout.setVerticalGroup(
            menu_varietasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(varietas_aktif, javax.swing.GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)
            .addGroup(menu_varietasLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        side_bar.add(menu_varietas, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 130, 210, -1));

        welcomeText.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        welcomeText.setForeground(new java.awt.Color(255, 255, 255));
        welcomeText.setText("Hai, ");
        side_bar.add(welcomeText, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));

        kButton1.setBorder(null);
        kButton1.setText("LOGOUT");
        kButton1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        kButton1.setkEndColor(new java.awt.Color(102, 204, 255));
        kButton1.setkHoverEndColor(new java.awt.Color(204, 0, 0));
        kButton1.setkHoverForeGround(new java.awt.Color(255, 255, 255));
        kButton1.setkHoverStartColor(new java.awt.Color(255, 102, 102));
        kButton1.setkStartColor(new java.awt.Color(0, 51, 255));
        kButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kButton1ActionPerformed(evt);
            }
        });
        side_bar.add(kButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 610, 150, 40));

        getContentPane().add(side_bar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 210, 670));

        jPanel2.setBackground(new java.awt.Color(71, 120, 197));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resource/cancel.png"))); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(944, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(20, 20, 20))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(23, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 0, 980, 50));

        ContainerPanel.setBackground(new java.awt.Color(255, 255, 255));
        ContainerPanel.setLayout(new java.awt.CardLayout());

        panel_pengguna.setBackground(new java.awt.Color(204, 204, 204));
        panel_pengguna.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                panel_penggunaComponentShown(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Pengguna");
        jLabel5.setFocusable(false);

        jLabel9.setText("Nama");

        jLabel10.setText("Asal Daerah");

        jLabel11.setText("Username");

        usernamePengguna.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                usernamePenggunaActionPerformed(evt);
            }
        });

        jLabel12.setText("Password");

        jLabel13.setText("Role");

        rolePengguna.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Admin", "Pengguna" }));
        rolePengguna.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rolePenggunaActionPerformed(evt);
            }
        });

        TablePengguna.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        TablePengguna.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        TablePengguna.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TablePenggunaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(TablePengguna);

        SimpanPengguna.setText("Simpan");
        SimpanPengguna.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SimpanPenggunaActionPerformed(evt);
            }
        });

        UpdatePengguna.setText("Update");
        UpdatePengguna.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UpdatePenggunaActionPerformed(evt);
            }
        });

        HapusPengguna.setText("Hapus");
        HapusPengguna.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                HapusPenggunaActionPerformed(evt);
            }
        });

        ResetPengguna.setText("Reset");
        ResetPengguna.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ResetPenggunaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panel_penggunaLayout = new javax.swing.GroupLayout(panel_pengguna);
        panel_pengguna.setLayout(panel_penggunaLayout);
        panel_penggunaLayout.setHorizontalGroup(
            panel_penggunaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_penggunaLayout.createSequentialGroup()
                .addGap(59, 59, 59)
                .addGroup(panel_penggunaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 766, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panel_penggunaLayout.createSequentialGroup()
                        .addGroup(panel_penggunaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addComponent(jLabel9)
                            .addComponent(jLabel13))
                        .addGap(18, 18, 18)
                        .addGroup(panel_penggunaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(namaPengguna, javax.swing.GroupLayout.DEFAULT_SIZE, 254, Short.MAX_VALUE)
                            .addComponent(asalDaerahPengguna)
                            .addComponent(rolePengguna, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(116, 116, 116)
                        .addGroup(panel_penggunaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(panel_penggunaLayout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addGap(18, 18, 18)
                                .addComponent(usernamePengguna, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panel_penggunaLayout.createSequentialGroup()
                                .addComponent(jLabel12)
                                .addGap(18, 18, 18)
                                .addComponent(passwordPengguna))))
                    .addGroup(panel_penggunaLayout.createSequentialGroup()
                        .addComponent(SimpanPengguna)
                        .addGap(40, 40, 40)
                        .addComponent(UpdatePengguna)
                        .addGap(65, 65, 65)
                        .addComponent(HapusPengguna)
                        .addGap(31, 31, 31)
                        .addComponent(ResetPengguna))
                    .addComponent(jLabel5))
                .addContainerGap(75, Short.MAX_VALUE))
        );
        panel_penggunaLayout.setVerticalGroup(
            panel_penggunaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_penggunaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addGap(65, 65, 65)
                .addGroup(panel_penggunaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(namaPengguna, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11)
                    .addComponent(usernamePengguna, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panel_penggunaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(asalDaerahPengguna, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12)
                    .addComponent(passwordPengguna, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panel_penggunaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13)
                    .addComponent(rolePengguna, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 81, Short.MAX_VALUE)
                .addGroup(panel_penggunaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(SimpanPengguna)
                    .addComponent(UpdatePengguna)
                    .addComponent(HapusPengguna)
                    .addComponent(ResetPengguna))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43))
        );

        ContainerPanel.add(panel_pengguna, "card2");

        panel_varietas.setBackground(new java.awt.Color(204, 204, 204));
        panel_varietas.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                panel_varietasComponentShown(evt);
            }
        });
        panel_varietas.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel7.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Varietas");
        jLabel7.setFocusable(false);
        panel_varietas.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, -1, -1));
        panel_varietas.add(namaVarietas, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 60, 160, -1));

        jLabel16.setText("Nama");
        panel_varietas.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 60, -1, -1));

        jLabel17.setText("Grup");
        panel_varietas.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 90, -1, -1));

        SimpanVarietas.setText("Simpan");
        SimpanVarietas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SimpanVarietasActionPerformed(evt);
            }
        });
        panel_varietas.add(SimpanVarietas, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 420, -1, -1));

        UpdateVarietas.setText("Update");
        UpdateVarietas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UpdateVarietasActionPerformed(evt);
            }
        });
        panel_varietas.add(UpdateVarietas, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 420, -1, -1));

        HapusVarietas.setText("Hapus");
        HapusVarietas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                HapusVarietasActionPerformed(evt);
            }
        });
        panel_varietas.add(HapusVarietas, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 420, -1, -1));

        ResetVarietas.setText("Reset");
        ResetVarietas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ResetVarietasActionPerformed(evt);
            }
        });
        panel_varietas.add(ResetVarietas, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 420, -1, -1));

        TableVarietas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        TableVarietas.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        TableVarietas.getTableHeader().setResizingAllowed(false);
        TableVarietas.getTableHeader().setReorderingAllowed(false);
        TableVarietas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TableVarietasMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(TableVarietas);

        panel_varietas.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 460, 780, 140));

        KT.setModel(new javax.swing.SpinnerNumberModel(0.0f, 0.0f, null, 0.001f));
        panel_varietas.add(KT, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 380, 140, -1));

        jLabel18.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel18.setText("Nilai Varietas");
        panel_varietas.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 250, -1, -1));

        jLabel8.setText("Rerata Jumlah Tandan");
        panel_varietas.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 290, -1, -1));

        jLabel19.setText("Rerata Berat Tandan");
        panel_varietas.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 320, -1, -1));

        jLabel20.setText("Potensi TBS");
        panel_varietas.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 350, -1, -1));

        jLabel21.setText("Rendemen");
        panel_varietas.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 380, -1, -1));

        jLabel22.setText("Potensi CPO");
        panel_varietas.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 290, -1, -1));

        jLabel23.setText("Tinggi");
        panel_varietas.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 320, -1, -1));

        jLabel24.setText("Panjang Pelepah");
        panel_varietas.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 350, -1, -1));

        jLabel25.setText("Kerapatan Tanam");
        panel_varietas.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 380, -1, -1));

        RJT.setModel(new javax.swing.SpinnerNumberModel(0.0f, 0.0f, null, 0.001f));
        panel_varietas.add(RJT, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 290, 140, -1));

        RBT.setModel(new javax.swing.SpinnerNumberModel(0.0f, 0.0f, null, 0.001f));
        panel_varietas.add(RBT, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 320, 140, -1));

        TBS.setModel(new javax.swing.SpinnerNumberModel(0.0f, 0.0f, null, 0.001f));
        panel_varietas.add(TBS, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 350, 140, -1));

        Rendemen.setModel(new javax.swing.SpinnerNumberModel(0.0f, 0.0f, null, 0.001f));
        panel_varietas.add(Rendemen, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 380, 140, -1));

        CPO.setModel(new javax.swing.SpinnerNumberModel(0.0f, 0.0f, null, 0.001f));
        panel_varietas.add(CPO, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 290, 140, -1));

        Tinggi.setModel(new javax.swing.SpinnerNumberModel(0.0f, 0.0f, null, 0.001f));
        panel_varietas.add(Tinggi, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 320, 140, -1));

        PP.setModel(new javax.swing.SpinnerNumberModel(0.0f, 0.0f, null, 0.001f));
        panel_varietas.add(PP, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 350, 140, -1));

        jLabel26.setText("tandan/phn/thn");
        panel_varietas.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 290, -1, -1));

        jLabel27.setText("kg/tandan");
        panel_varietas.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 320, -1, -1));

        jLabel28.setText("ton/ha/thn");
        panel_varietas.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 290, -1, -1));

        jLabel29.setText("%");
        panel_varietas.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 380, -1, -1));

        jLabel30.setText("ton/ha/thn");
        panel_varietas.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 350, -1, -1));

        jLabel31.setText("cm/thn");
        panel_varietas.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 320, -1, -1));

        jLabel32.setText("m");
        panel_varietas.add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 350, -1, -1));

        jLabel33.setText("phn/ha");
        panel_varietas.add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 380, -1, -1));
        panel_varietas.add(grupField, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 90, 160, -1));

        jLabel6.setText("Foto");
        panel_varietas.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 70, -1, 10));
        panel_varietas.add(fotoField, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 120, 310, 140));

        chooseFile.setText("Pilih File");
        chooseFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chooseFileActionPerformed(evt);
            }
        });
        panel_varietas.add(chooseFile, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 90, -1, 20));

        ContainerPanel.add(panel_varietas, "card2");

        getContentPane().add(ContainerPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 50, 900, 620));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void menu_penggunaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menu_penggunaMousePressed
        setColor(menu_pengguna);
        pengguna_aktif.setOpaque(true);
        
        resetColor(new JPanel[]{menu_varietas}, new JPanel[]{varietas_aktif});
        panel_pengguna.setVisible(true);
        
        panel_varietas.setVisible(false);
    }//GEN-LAST:event_menu_penggunaMousePressed

    private void menu_varietasMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menu_varietasMousePressed
        setColor(menu_varietas);
        varietas_aktif.setOpaque(true);
        
        resetColor(new JPanel[]{menu_pengguna}, new JPanel[]{pengguna_aktif});
        panel_pengguna.setVisible(false);
        
        panel_varietas.setVisible(true);
    }//GEN-LAST:event_menu_varietasMousePressed

    private void kButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kButton1ActionPerformed
        Auth.logout(pengguna.getId());
        this.dispose();
        Login login = new Login();
        login.setVisible(true);
    }//GEN-LAST:event_kButton1ActionPerformed

    private void usernamePenggunaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_usernamePenggunaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_usernamePenggunaActionPerformed

    private void panel_penggunaComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_panel_penggunaComponentShown
        InitTablePengguna();
        TampilDataPengguna();
    }//GEN-LAST:event_panel_penggunaComponentShown

    private void rolePenggunaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rolePenggunaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rolePenggunaActionPerformed

    private void SimpanPenggunaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SimpanPenggunaActionPerformed
        String nama = namaPengguna.getText();
        String asal = asalDaerahPengguna.getText();
        String username = usernamePengguna.getText();
        String password = passwordPengguna.getText();
        String role = rolePengguna.getSelectedItem().toString();
        int response = JOptionPane.showConfirmDialog(null, "Anda yakin ingin menyimpan Pengguna ?", "Simpan Pengguna",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (response == JOptionPane.NO_OPTION) {
            JOptionPane.showMessageDialog(null, "Batal Menyimpan Pengguna");
        } else if (response == JOptionPane.YES_OPTION) {
            if (User.tambah(username, password, nama, asal, role)) {
                JOptionPane.showMessageDialog(null, "Berhasil Menyimpan Pengguna");
                InitTablePengguna();
                TampilDataPengguna();
            } else {
                JOptionPane.showMessageDialog(null, "Gagal");
            }
        }

    }//GEN-LAST:event_SimpanPenggunaActionPerformed

    private void TablePenggunaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TablePenggunaMouseClicked
        int row = TablePengguna.getSelectedRow();
        namaPengguna.setText(TablePengguna.getValueAt(row, 0).toString());
        asalDaerahPengguna.setText(TablePengguna.getValueAt(row, 1).toString());
        usernamePengguna.setText(TablePengguna.getValueAt(row, 2).toString());
        passwordPengguna.setText(TablePengguna.getValueAt(row, 3).toString());
        rolePengguna.getModel().setSelectedItem(TablePengguna.getValueAt(row, 4).toString());
    }//GEN-LAST:event_TablePenggunaMouseClicked

    private void UpdatePenggunaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UpdatePenggunaActionPerformed
        String nama = namaPengguna.getText();
        String asal = asalDaerahPengguna.getText();
        String username = usernamePengguna.getText();
        String password = passwordPengguna.getText();
        String role = rolePengguna.getSelectedItem().toString();

        int row = TablePengguna.getSelectedRow();
        int id = Integer.parseInt(TablePengguna.getModel().getValueAt(row, 0).toString());

        int response = JOptionPane.showConfirmDialog(null, "Anda yakin ingin mengubah Pengguna ?", "Ubah Pengguna",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (response == JOptionPane.NO_OPTION) {
            JOptionPane.showMessageDialog(null, "Batal Mengubah Pengguna");
        } else if (response == JOptionPane.YES_OPTION) {
            if (User.ubah(id, username, nama, asal, role)) {
                JOptionPane.showMessageDialog(null, "Berhasil Mengubah Pengguna");
                InitTablePengguna();
                TampilDataPengguna();
            } else {
                JOptionPane.showMessageDialog(null, "Gagal");
            }
        }
    }//GEN-LAST:event_UpdatePenggunaActionPerformed

    private void HapusPenggunaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_HapusPenggunaActionPerformed
        int row = TablePengguna.getSelectedRow();
        int id = Integer.parseInt(TablePengguna.getModel().getValueAt(row, 0).toString());

        int response = JOptionPane.showConfirmDialog(null, "Anda yakin ingin menghapus Pengguna ?", "hapus Pengguna",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (response == JOptionPane.NO_OPTION) {
            JOptionPane.showMessageDialog(null, "Batal Menghapus Pengguna");
        } else if (response == JOptionPane.YES_OPTION) {
            if (User.hapus(id)) {
                JOptionPane.showMessageDialog(null, "Berhasil Menghapus Pengguna");
                InitTablePengguna();
                TampilDataPengguna();
            } else {
                JOptionPane.showMessageDialog(null, "Gagal");
            }
        }

    }//GEN-LAST:event_HapusPenggunaActionPerformed

    private void ResetPenggunaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ResetPenggunaActionPerformed
        namaPengguna.setText("");
        asalDaerahPengguna.setText("");
        usernamePengguna.setText("");
        passwordPengguna.setText("");
        rolePengguna.getModel().setSelectedItem("");
    }//GEN-LAST:event_ResetPenggunaActionPerformed

    private void SimpanVarietasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SimpanVarietasActionPerformed
        String grup = grupField.getText();
        String nama = namaVarietas.getText();

        float[] nilai = new float[8];
        nilai[0] = (float) RJT.getValue();
        nilai[1] = (float) RBT.getValue();
        nilai[2] = (float) TBS.getValue();
        nilai[3] = (float) Rendemen.getValue();
        nilai[4] = (float) CPO.getValue();
        nilai[5] = (float) Tinggi.getValue();
        nilai[6] = (float) PP.getValue();
        nilai[7] = (float) KT.getValue();

        int response = JOptionPane.showConfirmDialog(null, "Anda yakin ingin menyimpan Varietas ?", "Simpan Varietas",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (response == JOptionPane.NO_OPTION) {
            JOptionPane.showMessageDialog(null, "Batal Menyimpan Varietas");
        } else if (response == JOptionPane.YES_OPTION) {
            if (varietas.tambah(nama, grup,path) && varietas.tambahNilaiKriteriaVarietas(nilai)) {
                JOptionPane.showMessageDialog(null, "Berhasil Menyimpan Varietas");
                InitTableVarietas();
                TampilDataVarietas();
            } else {
                JOptionPane.showMessageDialog(null, "Gagal");
            }
        }
    }//GEN-LAST:event_SimpanVarietasActionPerformed

    private void UpdateVarietasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UpdateVarietasActionPerformed
        String grup = grupField.getText();
        String nama = namaVarietas.getText();
        int row = TableVarietas.getSelectedRow();
        int id = Integer.parseInt(TableVarietas.getModel().getValueAt(row, 0).toString());

        float[] nilai = new float[8];
        nilai[0] = (float) RJT.getValue();
        nilai[1] = (float) RBT.getValue();
        nilai[2] = (float) TBS.getValue();
        nilai[3] = (float) Rendemen.getValue();
        nilai[4] = (float) CPO.getValue();
        nilai[5] = (float) Tinggi.getValue();
        nilai[6] = (float) PP.getValue();
        nilai[7] = (float) KT.getValue();
        
        
        int response = JOptionPane.showConfirmDialog(null, "Anda yakin ingin mengubah Varietas?", "Ubah Varietas",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (response == JOptionPane.NO_OPTION) {
            JOptionPane.showMessageDialog(null, "Batal Mengubah Varietas");
        } else if (response == JOptionPane.YES_OPTION) {
            if (varietas.ubah(id, nama, grup, path) && varietas.ubahNilaiKriteriaVarietas(id, nilai)) {
                JOptionPane.showMessageDialog(null, "Berhasil Mengubah Varietas");
                InitTableVarietas();
                TampilDataVarietas();
            } else {
                JOptionPane.showMessageDialog(null, "Gagal");
            }
        }
    }//GEN-LAST:event_UpdateVarietasActionPerformed

    private void HapusVarietasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_HapusVarietasActionPerformed
        int row = TableVarietas.getSelectedRow();
        int id = Integer.parseInt(TableVarietas.getModel().getValueAt(row, 0).toString());

        int response = JOptionPane.showConfirmDialog(null, "Anda yakin ingin menghapus Varietas ?", "hapus Varietas",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (response == JOptionPane.NO_OPTION) {
            JOptionPane.showMessageDialog(null, "Batal Menghapus Varietas");
        } else if (response == JOptionPane.YES_OPTION) {
            if (varietas.hapus(id)) {
                JOptionPane.showMessageDialog(null, "Berhasil Menghapus Varietas");
                InitTableVarietas();
                TampilDataVarietas();
            } else {
                JOptionPane.showMessageDialog(null, "Gagal");
            }
        }
    }//GEN-LAST:event_HapusVarietasActionPerformed

    private void ResetVarietasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ResetVarietasActionPerformed
        namaVarietas.setText("");
        grupField.setText("");
        RJT.getModel().setValue(0);
        RBT.getModel().setValue(0);
        TBS.getModel().setValue(0);
        Rendemen.getModel().setValue(0);
        CPO.getModel().setValue(0);
        Tinggi.getModel().setValue(0);
        PP.getModel().setValue(0);
        KT.getModel().setValue(0);
        fotoField.setIcon(null);
    }//GEN-LAST:event_ResetVarietasActionPerformed

    private void TableVarietasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TableVarietasMouseClicked
        path = "";
        int row = TableVarietas.getSelectedRow();
        namaVarietas.setText(TableVarietas.getValueAt(row, 0).toString()); 
        grupField.setText(TableVarietas.getValueAt(row, 1).toString());
        RJT.getModel().setValue(TableVarietas.getValueAt(row, 2));
        RBT.getModel().setValue(TableVarietas.getValueAt(row, 3));
        TBS.getModel().setValue(TableVarietas.getValueAt(row, 4));
        Rendemen.getModel().setValue(TableVarietas.getValueAt(row, 5));
        CPO.getModel().setValue(TableVarietas.getValueAt(row, 6));
        Tinggi.getModel().setValue(TableVarietas.getValueAt(row, 7));
        PP.getModel().setValue(TableVarietas.getValueAt(row, 8));
        KT.getModel().setValue(TableVarietas.getValueAt(row, 9));
        
        
        int id = Integer.parseInt(TableVarietas.getModel().getValueAt(row, 0).toString());
        fotoField.setIcon(varietas.getImage(id, fotoField.getWidth(), fotoField.getHeight()));
        
        
        
        
    }//GEN-LAST:event_TableVarietasMouseClicked

    private void panel_varietasComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_panel_varietasComponentShown
        InitTableVarietas();
        TampilDataVarietas();

    }//GEN-LAST:event_panel_varietasComponentShown

    private void chooseFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chooseFileActionPerformed
        JFileChooser jFile = new JFileChooser();
        jFile.setCurrentDirectory(new File(System.getProperty("user.home")));
        
        FileNameExtensionFilter filter = new FileNameExtensionFilter("*.image","jpg","png","jpeg");
        jFile.addChoosableFileFilter(filter);
        
        int result = jFile.showSaveDialog(null);
        
        File selectedFile = jFile.getSelectedFile();
        String filename = selectedFile.getName();
        
        if (filename.endsWith(".jpg") || filename.endsWith(".JPG") || filename.endsWith(".png") || filename.endsWith(".PNG") || filename.endsWith(".jpeg") || filename.endsWith(".JPEG")) {
            if (result == JFileChooser.APPROVE_OPTION) {
                path = selectedFile.getAbsolutePath();
                ImageIcon myImage = new ImageIcon(path);
                
                Image img = myImage.getImage();
                Image newImage = img.getScaledInstance(fotoField.getWidth(), fotoField.getHeight(), Image.SCALE_SMOOTH);
                
                ImageIcon image = new ImageIcon(newImage);
                fotoField.setIcon(image);
            }
        }
        else {
            JOptionPane.showMessageDialog(rootPane, "Silahkan pilih file bertipe gambar","COba Lagi",1);
        }
    }//GEN-LAST:event_chooseFileActionPerformed

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
            java.util.logging.Logger.getLogger(Admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Admin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JSpinner CPO;
    private javax.swing.JPanel ContainerPanel;
    private javax.swing.JButton HapusPengguna;
    private javax.swing.JButton HapusVarietas;
    private javax.swing.JSpinner KT;
    private javax.swing.JSpinner PP;
    private javax.swing.JSpinner RBT;
    private javax.swing.JSpinner RJT;
    private javax.swing.JSpinner Rendemen;
    private javax.swing.JButton ResetPengguna;
    private javax.swing.JButton ResetVarietas;
    private javax.swing.JButton SimpanPengguna;
    private javax.swing.JButton SimpanVarietas;
    private javax.swing.JSpinner TBS;
    private javax.swing.JTable TablePengguna;
    private javax.swing.JTable TableVarietas;
    private javax.swing.JSpinner Tinggi;
    private javax.swing.JButton UpdatePengguna;
    private javax.swing.JButton UpdateVarietas;
    private javax.swing.JTextField asalDaerahPengguna;
    private javax.swing.JButton chooseFile;
    private javax.swing.JLabel fotoField;
    private javax.swing.JTextField grupField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
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
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private keeptoo.KButton kButton1;
    private javax.swing.JPanel menu_pengguna;
    private javax.swing.JPanel menu_varietas;
    private javax.swing.JTextField namaPengguna;
    private javax.swing.JTextField namaVarietas;
    private javax.swing.JPanel panel_pengguna;
    private javax.swing.JPanel panel_varietas;
    private javax.swing.JTextField passwordPengguna;
    private javax.swing.JPanel pengguna_aktif;
    private javax.swing.JComboBox<String> rolePengguna;
    private javax.swing.JPanel side_bar;
    private javax.swing.JTextField usernamePengguna;
    private javax.swing.JPanel varietas_aktif;
    private javax.swing.JLabel welcomeText;
    // End of variables declaration//GEN-END:variables
}
