/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spk.ui;

import java.awt.Color;
import java.awt.Component;
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
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Vector;
import javax.swing.AbstractButton;
import javax.swing.ComboBoxModel;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import spk.data.Kriteria;
import javax.swing.table.DefaultTableModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JTable;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumnModel;
import spk.data.ComboItem;
import spk.data.Grup;
import spk.data.MetodeAhp;

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
    double CI = 0;
    double CR = 0;
    double[] pVector;
    MetodeAhp ahp = new MetodeAhp();

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
        InitTablePengguna();
        TampilDataPengguna();
        panel_varietas.setVisible(false);
        JDialog.setDefaultLookAndFeelDecorated(true);
        jScrollPane4.getVerticalScrollBar().setUnitIncrement(16);

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

        groupAB = new javax.swing.ButtonGroup();
        groupAC = new javax.swing.ButtonGroup();
        groupAD = new javax.swing.ButtonGroup();
        groupAE = new javax.swing.ButtonGroup();
        groupAF = new javax.swing.ButtonGroup();
        groupAG = new javax.swing.ButtonGroup();
        groupAH = new javax.swing.ButtonGroup();
        groupBC = new javax.swing.ButtonGroup();
        groupBD = new javax.swing.ButtonGroup();
        groupBE = new javax.swing.ButtonGroup();
        groupBF = new javax.swing.ButtonGroup();
        groupBG = new javax.swing.ButtonGroup();
        groupBH = new javax.swing.ButtonGroup();
        groupCD = new javax.swing.ButtonGroup();
        groupCE = new javax.swing.ButtonGroup();
        groupCF = new javax.swing.ButtonGroup();
        groupCG = new javax.swing.ButtonGroup();
        groupCH = new javax.swing.ButtonGroup();
        groupDE = new javax.swing.ButtonGroup();
        groupDF = new javax.swing.ButtonGroup();
        groupDG = new javax.swing.ButtonGroup();
        groupDH = new javax.swing.ButtonGroup();
        groupEF = new javax.swing.ButtonGroup();
        groupEG = new javax.swing.ButtonGroup();
        groupEH = new javax.swing.ButtonGroup();
        groupFG = new javax.swing.ButtonGroup();
        groupFH = new javax.swing.ButtonGroup();
        groupGH = new javax.swing.ButtonGroup();
        ab = new javax.swing.ButtonGroup();
        ac = new javax.swing.ButtonGroup();
        ad = new javax.swing.ButtonGroup();
        ae = new javax.swing.ButtonGroup();
        af = new javax.swing.ButtonGroup();
        ag = new javax.swing.ButtonGroup();
        ah = new javax.swing.ButtonGroup();
        bc = new javax.swing.ButtonGroup();
        bd = new javax.swing.ButtonGroup();
        be = new javax.swing.ButtonGroup();
        bf = new javax.swing.ButtonGroup();
        bg = new javax.swing.ButtonGroup();
        bh = new javax.swing.ButtonGroup();
        cd = new javax.swing.ButtonGroup();
        ce = new javax.swing.ButtonGroup();
        cf = new javax.swing.ButtonGroup();
        cg = new javax.swing.ButtonGroup();
        ch = new javax.swing.ButtonGroup();
        de = new javax.swing.ButtonGroup();
        df = new javax.swing.ButtonGroup();
        dg = new javax.swing.ButtonGroup();
        dh = new javax.swing.ButtonGroup();
        ef = new javax.swing.ButtonGroup();
        eg = new javax.swing.ButtonGroup();
        eh = new javax.swing.ButtonGroup();
        fg = new javax.swing.ButtonGroup();
        fh = new javax.swing.ButtonGroup();
        gh = new javax.swing.ButtonGroup();
        side_bar = new javax.swing.JPanel();
        menu_pengguna = new javax.swing.JPanel();
        pengguna_aktif = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        menu_varietas = new javax.swing.JPanel();
        varietas_aktif = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        welcomeText = new javax.swing.JLabel();
        kButton1 = new keeptoo.KButton();
        menu_kriteria = new javax.swing.JPanel();
        kriteria_aktif = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        menu_matriks = new javax.swing.JPanel();
        matriks_aktif = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
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
        panel_matriks = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        TableMatriks = new javax.swing.JTable();
        EV = new javax.swing.JLabel();
        KI = new javax.swing.JLabel();
        KR = new javax.swing.JLabel();
        finish = new javax.swing.JButton();
        jLabel35 = new javax.swing.JLabel();
        panel_kriteria = new javax.swing.JPanel();
        jLabel87 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        ahpProcess = new javax.swing.JPanel();
        jLabel92 = new javax.swing.JLabel();
        jLabel93 = new javax.swing.JLabel();
        ABa = new javax.swing.JRadioButton();
        ABb = new javax.swing.JRadioButton();
        AB1 = new javax.swing.JRadioButton();
        AB2 = new javax.swing.JRadioButton();
        AB3 = new javax.swing.JRadioButton();
        AB4 = new javax.swing.JRadioButton();
        AB5 = new javax.swing.JRadioButton();
        AB6 = new javax.swing.JRadioButton();
        AB7 = new javax.swing.JRadioButton();
        AB8 = new javax.swing.JRadioButton();
        AB9 = new javax.swing.JRadioButton();
        jLabel94 = new javax.swing.JLabel();
        jLabel95 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        AC1 = new javax.swing.JRadioButton();
        AC2 = new javax.swing.JRadioButton();
        AC3 = new javax.swing.JRadioButton();
        AC4 = new javax.swing.JRadioButton();
        AC5 = new javax.swing.JRadioButton();
        AC6 = new javax.swing.JRadioButton();
        AC7 = new javax.swing.JRadioButton();
        AC8 = new javax.swing.JRadioButton();
        AC9 = new javax.swing.JRadioButton();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel15 = new javax.swing.JLabel();
        jLabel96 = new javax.swing.JLabel();
        AD1 = new javax.swing.JRadioButton();
        AD2 = new javax.swing.JRadioButton();
        AD3 = new javax.swing.JRadioButton();
        AD4 = new javax.swing.JRadioButton();
        AD5 = new javax.swing.JRadioButton();
        AD6 = new javax.swing.JRadioButton();
        AD7 = new javax.swing.JRadioButton();
        AD8 = new javax.swing.JRadioButton();
        AD9 = new javax.swing.JRadioButton();
        jLabel97 = new javax.swing.JLabel();
        jLabel98 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        AE1 = new javax.swing.JRadioButton();
        AE2 = new javax.swing.JRadioButton();
        AE3 = new javax.swing.JRadioButton();
        AE4 = new javax.swing.JRadioButton();
        AE5 = new javax.swing.JRadioButton();
        AE6 = new javax.swing.JRadioButton();
        AE7 = new javax.swing.JRadioButton();
        AE8 = new javax.swing.JRadioButton();
        AE9 = new javax.swing.JRadioButton();
        jSeparator4 = new javax.swing.JSeparator();
        jLabel99 = new javax.swing.JLabel();
        jLabel100 = new javax.swing.JLabel();
        AF1 = new javax.swing.JRadioButton();
        AF2 = new javax.swing.JRadioButton();
        AF3 = new javax.swing.JRadioButton();
        AF4 = new javax.swing.JRadioButton();
        AF5 = new javax.swing.JRadioButton();
        AF6 = new javax.swing.JRadioButton();
        AF7 = new javax.swing.JRadioButton();
        AF8 = new javax.swing.JRadioButton();
        AF9 = new javax.swing.JRadioButton();
        jLabel101 = new javax.swing.JLabel();
        jLabel102 = new javax.swing.JLabel();
        jSeparator5 = new javax.swing.JSeparator();
        AG1 = new javax.swing.JRadioButton();
        AG2 = new javax.swing.JRadioButton();
        AG3 = new javax.swing.JRadioButton();
        AG4 = new javax.swing.JRadioButton();
        AG5 = new javax.swing.JRadioButton();
        AG6 = new javax.swing.JRadioButton();
        AG7 = new javax.swing.JRadioButton();
        AG8 = new javax.swing.JRadioButton();
        AG9 = new javax.swing.JRadioButton();
        jSeparator6 = new javax.swing.JSeparator();
        jLabel103 = new javax.swing.JLabel();
        jLabel104 = new javax.swing.JLabel();
        AH1 = new javax.swing.JRadioButton();
        AH2 = new javax.swing.JRadioButton();
        AH3 = new javax.swing.JRadioButton();
        AH4 = new javax.swing.JRadioButton();
        AH5 = new javax.swing.JRadioButton();
        AH6 = new javax.swing.JRadioButton();
        AH7 = new javax.swing.JRadioButton();
        AH8 = new javax.swing.JRadioButton();
        AH9 = new javax.swing.JRadioButton();
        jLabel105 = new javax.swing.JLabel();
        jLabel106 = new javax.swing.JLabel();
        jSeparator7 = new javax.swing.JSeparator();
        BC1 = new javax.swing.JRadioButton();
        BC2 = new javax.swing.JRadioButton();
        BC3 = new javax.swing.JRadioButton();
        BC4 = new javax.swing.JRadioButton();
        BC5 = new javax.swing.JRadioButton();
        BC6 = new javax.swing.JRadioButton();
        BC7 = new javax.swing.JRadioButton();
        BC8 = new javax.swing.JRadioButton();
        BC9 = new javax.swing.JRadioButton();
        jSeparator8 = new javax.swing.JSeparator();
        jLabel107 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        BD1 = new javax.swing.JRadioButton();
        BD2 = new javax.swing.JRadioButton();
        BD3 = new javax.swing.JRadioButton();
        BD4 = new javax.swing.JRadioButton();
        BD5 = new javax.swing.JRadioButton();
        BD6 = new javax.swing.JRadioButton();
        BD7 = new javax.swing.JRadioButton();
        BD8 = new javax.swing.JRadioButton();
        BD9 = new javax.swing.JRadioButton();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jSeparator9 = new javax.swing.JSeparator();
        BE1 = new javax.swing.JRadioButton();
        BE2 = new javax.swing.JRadioButton();
        BE3 = new javax.swing.JRadioButton();
        BE4 = new javax.swing.JRadioButton();
        BE5 = new javax.swing.JRadioButton();
        BE6 = new javax.swing.JRadioButton();
        BE7 = new javax.swing.JRadioButton();
        BE8 = new javax.swing.JRadioButton();
        BE9 = new javax.swing.JRadioButton();
        jSeparator10 = new javax.swing.JSeparator();
        jLabel39 = new javax.swing.JLabel();
        jSeparator11 = new javax.swing.JSeparator();
        BF1 = new javax.swing.JRadioButton();
        BF2 = new javax.swing.JRadioButton();
        BF3 = new javax.swing.JRadioButton();
        BF4 = new javax.swing.JRadioButton();
        BF5 = new javax.swing.JRadioButton();
        BF6 = new javax.swing.JRadioButton();
        BF7 = new javax.swing.JRadioButton();
        BF8 = new javax.swing.JRadioButton();
        BF9 = new javax.swing.JRadioButton();
        jSeparator12 = new javax.swing.JSeparator();
        jLabel41 = new javax.swing.JLabel();
        BG1 = new javax.swing.JRadioButton();
        BG2 = new javax.swing.JRadioButton();
        BG3 = new javax.swing.JRadioButton();
        BG4 = new javax.swing.JRadioButton();
        BG5 = new javax.swing.JRadioButton();
        BG6 = new javax.swing.JRadioButton();
        BG7 = new javax.swing.JRadioButton();
        BG8 = new javax.swing.JRadioButton();
        BG9 = new javax.swing.JRadioButton();
        jSeparator13 = new javax.swing.JSeparator();
        jLabel43 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        BH1 = new javax.swing.JRadioButton();
        BH2 = new javax.swing.JRadioButton();
        BH3 = new javax.swing.JRadioButton();
        BH4 = new javax.swing.JRadioButton();
        BH5 = new javax.swing.JRadioButton();
        BH6 = new javax.swing.JRadioButton();
        BH7 = new javax.swing.JRadioButton();
        BH8 = new javax.swing.JRadioButton();
        BH9 = new javax.swing.JRadioButton();
        jLabel45 = new javax.swing.JLabel();
        CD1 = new javax.swing.JRadioButton();
        CD2 = new javax.swing.JRadioButton();
        CD3 = new javax.swing.JRadioButton();
        CD4 = new javax.swing.JRadioButton();
        CD5 = new javax.swing.JRadioButton();
        CD6 = new javax.swing.JRadioButton();
        CD7 = new javax.swing.JRadioButton();
        CD8 = new javax.swing.JRadioButton();
        CD9 = new javax.swing.JRadioButton();
        jLabel46 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        jSeparator14 = new javax.swing.JSeparator();
        jLabel50 = new javax.swing.JLabel();
        CE1 = new javax.swing.JRadioButton();
        CE2 = new javax.swing.JRadioButton();
        CE3 = new javax.swing.JRadioButton();
        CE4 = new javax.swing.JRadioButton();
        CE5 = new javax.swing.JRadioButton();
        CE6 = new javax.swing.JRadioButton();
        CE7 = new javax.swing.JRadioButton();
        CE8 = new javax.swing.JRadioButton();
        CE9 = new javax.swing.JRadioButton();
        jSeparator15 = new javax.swing.JSeparator();
        CF1 = new javax.swing.JRadioButton();
        CF2 = new javax.swing.JRadioButton();
        CF3 = new javax.swing.JRadioButton();
        CF4 = new javax.swing.JRadioButton();
        CF5 = new javax.swing.JRadioButton();
        CF6 = new javax.swing.JRadioButton();
        CF7 = new javax.swing.JRadioButton();
        CF8 = new javax.swing.JRadioButton();
        CF9 = new javax.swing.JRadioButton();
        jLabel51 = new javax.swing.JLabel();
        jLabel52 = new javax.swing.JLabel();
        jSeparator16 = new javax.swing.JSeparator();
        CG1 = new javax.swing.JRadioButton();
        CG2 = new javax.swing.JRadioButton();
        CG3 = new javax.swing.JRadioButton();
        CG4 = new javax.swing.JRadioButton();
        CG5 = new javax.swing.JRadioButton();
        CG6 = new javax.swing.JRadioButton();
        CG7 = new javax.swing.JRadioButton();
        CG8 = new javax.swing.JRadioButton();
        CG9 = new javax.swing.JRadioButton();
        jLabel55 = new javax.swing.JLabel();
        jLabel56 = new javax.swing.JLabel();
        jSeparator17 = new javax.swing.JSeparator();
        CH1 = new javax.swing.JRadioButton();
        CH2 = new javax.swing.JRadioButton();
        CH3 = new javax.swing.JRadioButton();
        CH4 = new javax.swing.JRadioButton();
        CH5 = new javax.swing.JRadioButton();
        CH6 = new javax.swing.JRadioButton();
        CH7 = new javax.swing.JRadioButton();
        CH8 = new javax.swing.JRadioButton();
        CH9 = new javax.swing.JRadioButton();
        jLabel57 = new javax.swing.JLabel();
        jLabel59 = new javax.swing.JLabel();
        jSeparator18 = new javax.swing.JSeparator();
        DE1 = new javax.swing.JRadioButton();
        DE2 = new javax.swing.JRadioButton();
        DE3 = new javax.swing.JRadioButton();
        DE4 = new javax.swing.JRadioButton();
        DE5 = new javax.swing.JRadioButton();
        DE6 = new javax.swing.JRadioButton();
        DE7 = new javax.swing.JRadioButton();
        DE8 = new javax.swing.JRadioButton();
        DE9 = new javax.swing.JRadioButton();
        jLabel61 = new javax.swing.JLabel();
        jLabel62 = new javax.swing.JLabel();
        DF1 = new javax.swing.JRadioButton();
        DF2 = new javax.swing.JRadioButton();
        DF3 = new javax.swing.JRadioButton();
        DF4 = new javax.swing.JRadioButton();
        DF5 = new javax.swing.JRadioButton();
        DF6 = new javax.swing.JRadioButton();
        DF7 = new javax.swing.JRadioButton();
        DF8 = new javax.swing.JRadioButton();
        DF9 = new javax.swing.JRadioButton();
        jSeparator19 = new javax.swing.JSeparator();
        jLabel64 = new javax.swing.JLabel();
        jLabel65 = new javax.swing.JLabel();
        jSeparator20 = new javax.swing.JSeparator();
        DG1 = new javax.swing.JRadioButton();
        DG2 = new javax.swing.JRadioButton();
        DG3 = new javax.swing.JRadioButton();
        DG4 = new javax.swing.JRadioButton();
        DG5 = new javax.swing.JRadioButton();
        DG6 = new javax.swing.JRadioButton();
        DG7 = new javax.swing.JRadioButton();
        DG8 = new javax.swing.JRadioButton();
        DG9 = new javax.swing.JRadioButton();
        jLabel66 = new javax.swing.JLabel();
        jLabel67 = new javax.swing.JLabel();
        DH1 = new javax.swing.JRadioButton();
        DH2 = new javax.swing.JRadioButton();
        DH3 = new javax.swing.JRadioButton();
        DH4 = new javax.swing.JRadioButton();
        DH5 = new javax.swing.JRadioButton();
        DH6 = new javax.swing.JRadioButton();
        DH7 = new javax.swing.JRadioButton();
        DH8 = new javax.swing.JRadioButton();
        DH9 = new javax.swing.JRadioButton();
        jLabel69 = new javax.swing.JLabel();
        jLabel71 = new javax.swing.JLabel();
        jLabel72 = new javax.swing.JLabel();
        jSeparator21 = new javax.swing.JSeparator();
        EF1 = new javax.swing.JRadioButton();
        EF2 = new javax.swing.JRadioButton();
        EF3 = new javax.swing.JRadioButton();
        EF4 = new javax.swing.JRadioButton();
        EF5 = new javax.swing.JRadioButton();
        EF6 = new javax.swing.JRadioButton();
        EF7 = new javax.swing.JRadioButton();
        EF8 = new javax.swing.JRadioButton();
        EF9 = new javax.swing.JRadioButton();
        jLabel73 = new javax.swing.JLabel();
        jSeparator22 = new javax.swing.JSeparator();
        EG1 = new javax.swing.JRadioButton();
        EG2 = new javax.swing.JRadioButton();
        EG3 = new javax.swing.JRadioButton();
        EG4 = new javax.swing.JRadioButton();
        EG5 = new javax.swing.JRadioButton();
        EG6 = new javax.swing.JRadioButton();
        EG7 = new javax.swing.JRadioButton();
        EG8 = new javax.swing.JRadioButton();
        EG9 = new javax.swing.JRadioButton();
        jLabel75 = new javax.swing.JLabel();
        jLabel76 = new javax.swing.JLabel();
        jSeparator23 = new javax.swing.JSeparator();
        jLabel79 = new javax.swing.JLabel();
        jSeparator24 = new javax.swing.JSeparator();
        EH1 = new javax.swing.JRadioButton();
        EH2 = new javax.swing.JRadioButton();
        EH3 = new javax.swing.JRadioButton();
        EH4 = new javax.swing.JRadioButton();
        EH5 = new javax.swing.JRadioButton();
        EH6 = new javax.swing.JRadioButton();
        EH7 = new javax.swing.JRadioButton();
        EH8 = new javax.swing.JRadioButton();
        EH9 = new javax.swing.JRadioButton();
        jLabel80 = new javax.swing.JLabel();
        jLabel81 = new javax.swing.JLabel();
        jLabel82 = new javax.swing.JLabel();
        jLabel83 = new javax.swing.JLabel();
        jLabel86 = new javax.swing.JLabel();
        jSeparator25 = new javax.swing.JSeparator();
        FG1 = new javax.swing.JRadioButton();
        FG2 = new javax.swing.JRadioButton();
        FG3 = new javax.swing.JRadioButton();
        FG4 = new javax.swing.JRadioButton();
        FG5 = new javax.swing.JRadioButton();
        FG6 = new javax.swing.JRadioButton();
        FG7 = new javax.swing.JRadioButton();
        FG8 = new javax.swing.JRadioButton();
        FG9 = new javax.swing.JRadioButton();
        jSeparator26 = new javax.swing.JSeparator();
        FH1 = new javax.swing.JRadioButton();
        FH2 = new javax.swing.JRadioButton();
        FH3 = new javax.swing.JRadioButton();
        FH4 = new javax.swing.JRadioButton();
        FH5 = new javax.swing.JRadioButton();
        FH6 = new javax.swing.JRadioButton();
        FH7 = new javax.swing.JRadioButton();
        FH8 = new javax.swing.JRadioButton();
        FH9 = new javax.swing.JRadioButton();
        GH1 = new javax.swing.JRadioButton();
        GH2 = new javax.swing.JRadioButton();
        GH3 = new javax.swing.JRadioButton();
        GH4 = new javax.swing.JRadioButton();
        GH5 = new javax.swing.JRadioButton();
        GH6 = new javax.swing.JRadioButton();
        GH7 = new javax.swing.JRadioButton();
        GH8 = new javax.swing.JRadioButton();
        GH9 = new javax.swing.JRadioButton();
        jLabel88 = new javax.swing.JLabel();
        jLabel89 = new javax.swing.JLabel();
        jSeparator27 = new javax.swing.JSeparator();
        jLabel90 = new javax.swing.JLabel();
        jLabel91 = new javax.swing.JLabel();
        jSeparator28 = new javax.swing.JSeparator();
        ACa = new javax.swing.JRadioButton();
        ACc = new javax.swing.JRadioButton();
        ADa = new javax.swing.JRadioButton();
        ADd = new javax.swing.JRadioButton();
        AEa = new javax.swing.JRadioButton();
        AEe = new javax.swing.JRadioButton();
        AFa = new javax.swing.JRadioButton();
        AFf = new javax.swing.JRadioButton();
        AGa = new javax.swing.JRadioButton();
        AGg = new javax.swing.JRadioButton();
        AHa = new javax.swing.JRadioButton();
        AHh = new javax.swing.JRadioButton();
        BCb = new javax.swing.JRadioButton();
        BCc = new javax.swing.JRadioButton();
        BDb = new javax.swing.JRadioButton();
        BDd = new javax.swing.JRadioButton();
        BEb = new javax.swing.JRadioButton();
        BEe = new javax.swing.JRadioButton();
        BFb = new javax.swing.JRadioButton();
        BFf = new javax.swing.JRadioButton();
        BGb = new javax.swing.JRadioButton();
        BGg = new javax.swing.JRadioButton();
        BHb = new javax.swing.JRadioButton();
        BHh = new javax.swing.JRadioButton();
        CDc = new javax.swing.JRadioButton();
        CDd = new javax.swing.JRadioButton();
        ABb14 = new javax.swing.JRadioButton();
        ABa14 = new javax.swing.JRadioButton();
        ABb15 = new javax.swing.JRadioButton();
        ABa15 = new javax.swing.JRadioButton();
        ABb16 = new javax.swing.JRadioButton();
        ABa16 = new javax.swing.JRadioButton();
        ABb17 = new javax.swing.JRadioButton();
        ABa17 = new javax.swing.JRadioButton();
        ABb18 = new javax.swing.JRadioButton();
        ABa18 = new javax.swing.JRadioButton();
        ABb19 = new javax.swing.JRadioButton();
        ABa19 = new javax.swing.JRadioButton();
        ABb20 = new javax.swing.JRadioButton();
        ABa20 = new javax.swing.JRadioButton();
        ABb21 = new javax.swing.JRadioButton();
        ABa21 = new javax.swing.JRadioButton();
        ABb22 = new javax.swing.JRadioButton();
        ABa22 = new javax.swing.JRadioButton();
        ABb23 = new javax.swing.JRadioButton();
        ABa23 = new javax.swing.JRadioButton();
        ABb24 = new javax.swing.JRadioButton();
        ABa24 = new javax.swing.JRadioButton();
        ABb25 = new javax.swing.JRadioButton();
        ABa25 = new javax.swing.JRadioButton();
        ABb26 = new javax.swing.JRadioButton();
        ABa26 = new javax.swing.JRadioButton();
        ABb27 = new javax.swing.JRadioButton();
        ABa27 = new javax.swing.JRadioButton();
        jLabel108 = new javax.swing.JLabel();
        helpButton = new javax.swing.JButton();
        simpan = new javax.swing.JButton();

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

        menu_kriteria.setBackground(new java.awt.Color(23, 35, 51));
        menu_kriteria.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                menu_kriteriaMousePressed(evt);
            }
        });

        kriteria_aktif.setOpaque(false);
        kriteria_aktif.setPreferredSize(new java.awt.Dimension(5, 40));

        javax.swing.GroupLayout kriteria_aktifLayout = new javax.swing.GroupLayout(kriteria_aktif);
        kriteria_aktif.setLayout(kriteria_aktifLayout);
        kriteria_aktifLayout.setHorizontalGroup(
            kriteria_aktifLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 5, Short.MAX_VALUE)
        );
        kriteria_aktifLayout.setVerticalGroup(
            kriteria_aktifLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jLabel14.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Kriteria");

        javax.swing.GroupLayout menu_kriteriaLayout = new javax.swing.GroupLayout(menu_kriteria);
        menu_kriteria.setLayout(menu_kriteriaLayout);
        menu_kriteriaLayout.setHorizontalGroup(
            menu_kriteriaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menu_kriteriaLayout.createSequentialGroup()
                .addComponent(kriteria_aktif, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(78, 78, 78)
                .addComponent(jLabel14)
                .addGap(0, 84, Short.MAX_VALUE))
        );
        menu_kriteriaLayout.setVerticalGroup(
            menu_kriteriaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(kriteria_aktif, javax.swing.GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)
            .addGroup(menu_kriteriaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel14)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        side_bar.add(menu_kriteria, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 170, -1, 40));

        menu_matriks.setBackground(new java.awt.Color(23, 35, 51));
        menu_matriks.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                menu_matriksMousePressed(evt);
            }
        });

        matriks_aktif.setOpaque(false);
        matriks_aktif.setPreferredSize(new java.awt.Dimension(5, 40));

        javax.swing.GroupLayout matriks_aktifLayout = new javax.swing.GroupLayout(matriks_aktif);
        matriks_aktif.setLayout(matriks_aktifLayout);
        matriks_aktifLayout.setHorizontalGroup(
            matriks_aktifLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 5, Short.MAX_VALUE)
        );
        matriks_aktifLayout.setVerticalGroup(
            matriks_aktifLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Matriks");

        javax.swing.GroupLayout menu_matriksLayout = new javax.swing.GroupLayout(menu_matriks);
        menu_matriks.setLayout(menu_matriksLayout);
        menu_matriksLayout.setHorizontalGroup(
            menu_matriksLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menu_matriksLayout.createSequentialGroup()
                .addComponent(matriks_aktif, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(77, 77, 77)
                .addComponent(jLabel2)
                .addGap(0, 82, Short.MAX_VALUE))
        );
        menu_matriksLayout.setVerticalGroup(
            menu_matriksLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(matriks_aktif, javax.swing.GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)
            .addGroup(menu_matriksLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        side_bar.add(menu_matriks, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 210, -1, 40));

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

        panel_matriks.setBackground(new java.awt.Color(204, 204, 204));
        panel_matriks.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                panel_matriksComponentShown(evt);
            }
        });

        TableMatriks.setModel(new javax.swing.table.DefaultTableModel(
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
        TableMatriks.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        TableMatriks.getTableHeader().setResizingAllowed(false);
        TableMatriks.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(TableMatriks);

        EV.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        EV.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        EV.setText("Eigen Value =");

        KI.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        KI.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        KI.setText("Konsistensi Index = ");

        KR.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        KR.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        KR.setText("Konsistensi Ratio = ");

        finish.setText("jButton1");
        finish.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                finishActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(EV, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(KI, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(KR, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(357, 357, 357)
                .addComponent(finish, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(366, Short.MAX_VALUE))
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 860, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(351, Short.MAX_VALUE)
                .addComponent(EV)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(KI)
                .addGap(18, 18, 18)
                .addComponent(KR)
                .addGap(18, 18, 18)
                .addComponent(finish, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34))
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(241, Short.MAX_VALUE)))
        );

        jLabel35.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel35.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel35.setText("MATRIKS");
        jLabel35.setFocusable(false);

        javax.swing.GroupLayout panel_matriksLayout = new javax.swing.GroupLayout(panel_matriks);
        panel_matriks.setLayout(panel_matriksLayout);
        panel_matriksLayout.setHorizontalGroup(
            panel_matriksLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_matriksLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel_matriksLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel35)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panel_matriksLayout.setVerticalGroup(
            panel_matriksLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_matriksLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel35)
                .addGap(18, 18, 18)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        ContainerPanel.add(panel_matriks, "card2");

        panel_kriteria.setBackground(new java.awt.Color(204, 204, 204));

        jLabel87.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel87.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel87.setText("KRITERIA");
        jLabel87.setFocusable(false);

        ahpProcess.setBackground(new java.awt.Color(204, 204, 204));
        ahpProcess.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "AHP Process", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP));
        ahpProcess.setForeground(new java.awt.Color(204, 204, 204));
        ahpProcess.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel92.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel92.setText("Rerata Jumlah Tandan");
        ahpProcess.add(jLabel92, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, -1, 39));

        jLabel93.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel93.setText("Rerata Berat Tandan");
        ahpProcess.add(jLabel93, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 50, -1, 39));

        ABa.setBackground(new java.awt.Color(204, 204, 204));
        ab.add(ABa);
        ABa.setForeground(new java.awt.Color(204, 204, 204));
        ABa.setText("1");
        ABa.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        ABa.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ABa.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        ahpProcess.add(ABa, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 30, -1, -1));

        ABb.setBackground(new java.awt.Color(204, 204, 204));
        ab.add(ABb);
        ABb.setForeground(new java.awt.Color(204, 204, 204));
        ABb.setText("0");
        ABb.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        ABb.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ABb.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        ahpProcess.add(ABb, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 30, -1, -1));

        AB1.setBackground(new java.awt.Color(204, 204, 204));
        groupAB.add(AB1);
        AB1.setText("1");
        AB1.setToolTipText("Sama Pentingnya");
        AB1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        AB1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        AB1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AB1ActionPerformed(evt);
            }
        });
        ahpProcess.add(AB1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, -1, -1));

        AB2.setBackground(new java.awt.Color(204, 204, 204));
        groupAB.add(AB2);
        AB2.setText("2");
        AB2.setToolTipText("Nilai tengah diantara 2 nilai keputusan yang berdekatan");
        AB2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        AB2.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        AB2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AB2ActionPerformed(evt);
            }
        });
        ahpProcess.add(AB2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 90, -1, -1));

        AB3.setBackground(new java.awt.Color(204, 204, 204));
        groupAB.add(AB3);
        AB3.setText("3");
        AB3.setToolTipText("Agak Lebih Penting");
        AB3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        AB3.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        AB3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AB3ActionPerformed(evt);
            }
        });
        ahpProcess.add(AB3, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 90, -1, -1));

        AB4.setBackground(new java.awt.Color(204, 204, 204));
        groupAB.add(AB4);
        AB4.setText("4");
        AB4.setToolTipText("Nilai tengah diantara 2 nilai keputusan yang berdekatan");
        AB4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        AB4.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        AB4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AB4ActionPerformed(evt);
            }
        });
        ahpProcess.add(AB4, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 90, -1, -1));

        AB5.setBackground(new java.awt.Color(204, 204, 204));
        groupAB.add(AB5);
        AB5.setText("5");
        AB5.setToolTipText("Cukup Lebih Penting");
        AB5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        AB5.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        AB5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AB5ActionPerformed(evt);
            }
        });
        ahpProcess.add(AB5, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 90, -1, -1));

        AB6.setBackground(new java.awt.Color(204, 204, 204));
        groupAB.add(AB6);
        AB6.setText("6");
        AB6.setToolTipText("Nilai tengah diantara 2 nilai keputusan yang berdekatan");
        AB6.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        AB6.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        AB6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AB6ActionPerformed(evt);
            }
        });
        ahpProcess.add(AB6, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 90, -1, -1));

        AB7.setBackground(new java.awt.Color(204, 204, 204));
        groupAB.add(AB7);
        AB7.setText("7");
        AB7.setToolTipText("Sangat Lebih Penting");
        AB7.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        AB7.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        AB7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AB7ActionPerformed(evt);
            }
        });
        ahpProcess.add(AB7, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 90, -1, -1));

        AB8.setBackground(new java.awt.Color(204, 204, 204));
        groupAB.add(AB8);
        AB8.setText("8");
        AB8.setToolTipText("Nilai tengah diantara 2 nilai keputusan yang berdekatan");
        AB8.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        AB8.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        AB8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AB8ActionPerformed(evt);
            }
        });
        ahpProcess.add(AB8, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 90, -1, -1));

        AB9.setBackground(new java.awt.Color(204, 204, 204));
        groupAB.add(AB9);
        AB9.setText("9");
        AB9.setToolTipText("Lebih Penting Ekstrim");
        AB9.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        AB9.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        AB9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AB9ActionPerformed(evt);
            }
        });
        ahpProcess.add(AB9, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 90, -1, -1));

        jLabel94.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel94.setText("Rerata Jumlah Tandan");
        ahpProcess.add(jLabel94, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 50, -1, 39));

        jLabel95.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel95.setText("Potensi TBS");
        ahpProcess.add(jLabel95, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 50, -1, 39));

        jSeparator1.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);
        ahpProcess.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 20, 10, 120));

        AC1.setBackground(new java.awt.Color(204, 204, 204));
        groupAC.add(AC1);
        AC1.setText("1");
        AC1.setToolTipText("Sama Pentingnya");
        AC1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        AC1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        AC1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AC1ActionPerformed(evt);
            }
        });
        ahpProcess.add(AC1, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 90, -1, -1));

        AC2.setBackground(new java.awt.Color(204, 204, 204));
        groupAC.add(AC2);
        AC2.setText("2");
        AC2.setToolTipText("Nilai tengah diantara 2 nilai keputusan yang berdekatan");
        AC2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        AC2.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        AC2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AC2ActionPerformed(evt);
            }
        });
        ahpProcess.add(AC2, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 90, -1, -1));

        AC3.setBackground(new java.awt.Color(204, 204, 204));
        groupAC.add(AC3);
        AC3.setText("3");
        AC3.setToolTipText("Agak Lebih Penting");
        AC3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        AC3.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        AC3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AC3ActionPerformed(evt);
            }
        });
        ahpProcess.add(AC3, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 90, -1, -1));

        AC4.setBackground(new java.awt.Color(204, 204, 204));
        groupAC.add(AC4);
        AC4.setText("4");
        AC4.setToolTipText("Nilai tengah diantara 2 nilai keputusan yang berdekatan");
        AC4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        AC4.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        AC4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AC4ActionPerformed(evt);
            }
        });
        ahpProcess.add(AC4, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 90, -1, -1));

        AC5.setBackground(new java.awt.Color(204, 204, 204));
        groupAC.add(AC5);
        AC5.setText("5");
        AC5.setToolTipText("Cukup Lebih Penting");
        AC5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        AC5.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        AC5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AC5ActionPerformed(evt);
            }
        });
        ahpProcess.add(AC5, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 90, -1, -1));

        AC6.setBackground(new java.awt.Color(204, 204, 204));
        groupAC.add(AC6);
        AC6.setText("6");
        AC6.setToolTipText("Nilai tengah diantara 2 nilai keputusan yang berdekatan");
        AC6.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        AC6.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        AC6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AC6ActionPerformed(evt);
            }
        });
        ahpProcess.add(AC6, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 90, -1, -1));

        AC7.setBackground(new java.awt.Color(204, 204, 204));
        groupAC.add(AC7);
        AC7.setText("7");
        AC7.setToolTipText("Sangat Lebih Penting");
        AC7.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        AC7.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        AC7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AC7ActionPerformed(evt);
            }
        });
        ahpProcess.add(AC7, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 90, -1, -1));

        AC8.setBackground(new java.awt.Color(204, 204, 204));
        groupAC.add(AC8);
        AC8.setText("8");
        AC8.setToolTipText("Nilai tengah diantara 2 nilai keputusan yang berdekatan");
        AC8.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        AC8.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        AC8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AC8ActionPerformed(evt);
            }
        });
        ahpProcess.add(AC8, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 90, -1, -1));

        AC9.setBackground(new java.awt.Color(204, 204, 204));
        groupAC.add(AC9);
        AC9.setText("9");
        AC9.setToolTipText("Lebih Penting Ekstrim");
        AC9.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        AC9.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        AC9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AC9ActionPerformed(evt);
            }
        });
        ahpProcess.add(AC9, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 90, -1, -1));

        jSeparator2.setBackground(new java.awt.Color(0, 0, 0));
        ahpProcess.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 142, 880, 10));

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel15.setText("Rerata Jumlah Tandan");
        ahpProcess.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 180, -1, 39));

        jLabel96.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel96.setText("Rendemen");
        ahpProcess.add(jLabel96, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 180, -1, 39));

        AD1.setBackground(new java.awt.Color(204, 204, 204));
        groupAD.add(AD1);
        AD1.setText("1");
        AD1.setToolTipText("Sama Pentingnya");
        AD1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        AD1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        AD1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AD1ActionPerformed(evt);
            }
        });
        ahpProcess.add(AD1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 220, -1, -1));

        AD2.setBackground(new java.awt.Color(204, 204, 204));
        groupAD.add(AD2);
        AD2.setText("2");
        AD2.setToolTipText("Nilai tengah diantara 2 nilai keputusan yang berdekatan");
        AD2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        AD2.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        AD2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AD2ActionPerformed(evt);
            }
        });
        ahpProcess.add(AD2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 220, -1, -1));

        AD3.setBackground(new java.awt.Color(204, 204, 204));
        groupAD.add(AD3);
        AD3.setText("3");
        AD3.setToolTipText("Agak Lebih Penting");
        AD3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        AD3.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        AD3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AD3ActionPerformed(evt);
            }
        });
        ahpProcess.add(AD3, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 220, -1, -1));

        AD4.setBackground(new java.awt.Color(204, 204, 204));
        groupAD.add(AD4);
        AD4.setText("4");
        AD4.setToolTipText("Nilai tengah diantara 2 nilai keputusan yang berdekatan");
        AD4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        AD4.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        AD4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AD4ActionPerformed(evt);
            }
        });
        ahpProcess.add(AD4, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 220, -1, -1));

        AD5.setBackground(new java.awt.Color(204, 204, 204));
        groupAD.add(AD5);
        AD5.setText("5");
        AD5.setToolTipText("Cukup Lebih Penting");
        AD5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        AD5.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        AD5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AD5ActionPerformed(evt);
            }
        });
        ahpProcess.add(AD5, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 220, -1, -1));

        AD6.setBackground(new java.awt.Color(204, 204, 204));
        groupAD.add(AD6);
        AD6.setText("6");
        AD6.setToolTipText("Nilai tengah diantara 2 nilai keputusan yang berdekatan");
        AD6.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        AD6.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        AD6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AD6ActionPerformed(evt);
            }
        });
        ahpProcess.add(AD6, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 220, -1, -1));

        AD7.setBackground(new java.awt.Color(204, 204, 204));
        groupAD.add(AD7);
        AD7.setText("7");
        AD7.setToolTipText("Sangat Lebih Penting");
        AD7.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        AD7.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        AD7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AD7ActionPerformed(evt);
            }
        });
        ahpProcess.add(AD7, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 220, -1, -1));

        AD8.setBackground(new java.awt.Color(204, 204, 204));
        groupAD.add(AD8);
        AD8.setText("8");
        AD8.setToolTipText("Nilai tengah diantara 2 nilai keputusan yang berdekatan");
        AD8.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        AD8.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        AD8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AD8ActionPerformed(evt);
            }
        });
        ahpProcess.add(AD8, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 220, -1, -1));

        AD9.setBackground(new java.awt.Color(204, 204, 204));
        groupAD.add(AD9);
        AD9.setText("9");
        AD9.setToolTipText("Lebih Penting Ekstrim");
        AD9.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        AD9.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        AD9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AD9ActionPerformed(evt);
            }
        });
        ahpProcess.add(AD9, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 220, -1, -1));

        jLabel97.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel97.setText("Rerata Jumlah Tandan");
        ahpProcess.add(jLabel97, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 180, -1, 39));

        jLabel98.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel98.setText("Potensi CPO");
        ahpProcess.add(jLabel98, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 180, -1, 39));

        jSeparator3.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator3.setOrientation(javax.swing.SwingConstants.VERTICAL);
        ahpProcess.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 150, 10, 120));

        AE1.setBackground(new java.awt.Color(204, 204, 204));
        groupAE.add(AE1);
        AE1.setText("1");
        AE1.setToolTipText("Sama Pentingnya");
        AE1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        AE1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        AE1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AE1ActionPerformed(evt);
            }
        });
        ahpProcess.add(AE1, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 220, -1, -1));

        AE2.setBackground(new java.awt.Color(204, 204, 204));
        groupAE.add(AE2);
        AE2.setText("2");
        AE2.setToolTipText("Nilai tengah diantara 2 nilai keputusan yang berdekatan");
        AE2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        AE2.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        AE2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AE2ActionPerformed(evt);
            }
        });
        ahpProcess.add(AE2, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 220, -1, -1));

        AE3.setBackground(new java.awt.Color(204, 204, 204));
        groupAE.add(AE3);
        AE3.setText("3");
        AE3.setToolTipText("Agak Lebih Penting");
        AE3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        AE3.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        AE3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AE3ActionPerformed(evt);
            }
        });
        ahpProcess.add(AE3, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 220, -1, -1));

        AE4.setBackground(new java.awt.Color(204, 204, 204));
        groupAE.add(AE4);
        AE4.setText("4");
        AE4.setToolTipText("Nilai tengah diantara 2 nilai keputusan yang berdekatan");
        AE4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        AE4.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        AE4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AE4ActionPerformed(evt);
            }
        });
        ahpProcess.add(AE4, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 220, -1, -1));

        AE5.setBackground(new java.awt.Color(204, 204, 204));
        groupAE.add(AE5);
        AE5.setText("5");
        AE5.setToolTipText("Cukup Lebih Penting");
        AE5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        AE5.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        AE5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AE5ActionPerformed(evt);
            }
        });
        ahpProcess.add(AE5, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 220, -1, -1));

        AE6.setBackground(new java.awt.Color(204, 204, 204));
        groupAE.add(AE6);
        AE6.setText("6");
        AE6.setToolTipText("Nilai tengah diantara 2 nilai keputusan yang berdekatan");
        AE6.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        AE6.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        AE6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AE6ActionPerformed(evt);
            }
        });
        ahpProcess.add(AE6, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 220, -1, -1));

        AE7.setBackground(new java.awt.Color(204, 204, 204));
        groupAE.add(AE7);
        AE7.setText("7");
        AE7.setToolTipText("Sangat Lebih Penting");
        AE7.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        AE7.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        AE7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AE7ActionPerformed(evt);
            }
        });
        ahpProcess.add(AE7, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 220, -1, -1));

        AE8.setBackground(new java.awt.Color(204, 204, 204));
        groupAE.add(AE8);
        AE8.setText("8");
        AE8.setToolTipText("Nilai tengah diantara 2 nilai keputusan yang berdekatan");
        AE8.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        AE8.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        AE8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AE8ActionPerformed(evt);
            }
        });
        ahpProcess.add(AE8, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 220, -1, -1));

        AE9.setBackground(new java.awt.Color(204, 204, 204));
        groupAE.add(AE9);
        AE9.setText("9");
        AE9.setToolTipText("Lebih Penting Ekstrim");
        AE9.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        AE9.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        AE9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AE9ActionPerformed(evt);
            }
        });
        ahpProcess.add(AE9, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 220, -1, -1));

        jSeparator4.setBackground(new java.awt.Color(0, 0, 0));
        ahpProcess.add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 270, 880, 10));

        jLabel99.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel99.setText("Rerata Jumlah Tandan");
        ahpProcess.add(jLabel99, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 310, -1, 39));

        jLabel100.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel100.setText("Tinggi");
        ahpProcess.add(jLabel100, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 310, -1, 39));

        AF1.setBackground(new java.awt.Color(204, 204, 204));
        groupAF.add(AF1);
        AF1.setText("1");
        AF1.setToolTipText("Sama Pentingnya");
        AF1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        AF1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        AF1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AF1ActionPerformed(evt);
            }
        });
        ahpProcess.add(AF1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 350, -1, -1));

        AF2.setBackground(new java.awt.Color(204, 204, 204));
        groupAF.add(AF2);
        AF2.setText("2");
        AF2.setToolTipText("Nilai tengah diantara 2 nilai keputusan yang berdekatan");
        AF2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        AF2.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        AF2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AF2ActionPerformed(evt);
            }
        });
        ahpProcess.add(AF2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 350, -1, -1));

        AF3.setBackground(new java.awt.Color(204, 204, 204));
        groupAF.add(AF3);
        AF3.setText("3");
        AF3.setToolTipText("Agak Lebih Penting");
        AF3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        AF3.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        AF3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AF3ActionPerformed(evt);
            }
        });
        ahpProcess.add(AF3, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 350, -1, -1));

        AF4.setBackground(new java.awt.Color(204, 204, 204));
        groupAF.add(AF4);
        AF4.setText("4");
        AF4.setToolTipText("Nilai tengah diantara 2 nilai keputusan yang berdekatan");
        AF4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        AF4.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        AF4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AF4ActionPerformed(evt);
            }
        });
        ahpProcess.add(AF4, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 350, -1, -1));

        AF5.setBackground(new java.awt.Color(204, 204, 204));
        groupAF.add(AF5);
        AF5.setText("5");
        AF5.setToolTipText("Cukup Lebih Penting");
        AF5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        AF5.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        AF5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AF5ActionPerformed(evt);
            }
        });
        ahpProcess.add(AF5, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 350, -1, -1));

        AF6.setBackground(new java.awt.Color(204, 204, 204));
        groupAF.add(AF6);
        AF6.setText("6");
        AF6.setToolTipText("Nilai tengah diantara 2 nilai keputusan yang berdekatan");
        AF6.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        AF6.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        AF6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AF6ActionPerformed(evt);
            }
        });
        ahpProcess.add(AF6, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 350, -1, -1));

        AF7.setBackground(new java.awt.Color(204, 204, 204));
        groupAF.add(AF7);
        AF7.setText("7");
        AF7.setToolTipText("Sangat Lebih Penting");
        AF7.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        AF7.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        AF7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AF7ActionPerformed(evt);
            }
        });
        ahpProcess.add(AF7, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 350, -1, -1));

        AF8.setBackground(new java.awt.Color(204, 204, 204));
        groupAF.add(AF8);
        AF8.setText("8");
        AF8.setToolTipText("Nilai tengah diantara 2 nilai keputusan yang berdekatan");
        AF8.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        AF8.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        AF8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AF8ActionPerformed(evt);
            }
        });
        ahpProcess.add(AF8, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 350, -1, -1));

        AF9.setBackground(new java.awt.Color(204, 204, 204));
        groupAF.add(AF9);
        AF9.setText("9");
        AF9.setToolTipText("Lebih Penting Ekstrim");
        AF9.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        AF9.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        AF9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AF9ActionPerformed(evt);
            }
        });
        ahpProcess.add(AF9, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 350, -1, -1));

        jLabel101.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel101.setText("Rerata Jumlah Tandan");
        ahpProcess.add(jLabel101, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 310, -1, 39));

        jLabel102.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel102.setText("Panjang Pelepah");
        ahpProcess.add(jLabel102, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 310, -1, 39));

        jSeparator5.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator5.setOrientation(javax.swing.SwingConstants.VERTICAL);
        ahpProcess.add(jSeparator5, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 280, 10, 120));

        AG1.setBackground(new java.awt.Color(204, 204, 204));
        groupAG.add(AG1);
        AG1.setText("1");
        AG1.setToolTipText("Sama Pentingnya");
        AG1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        AG1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        AG1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AG1ActionPerformed(evt);
            }
        });
        ahpProcess.add(AG1, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 350, -1, -1));

        AG2.setBackground(new java.awt.Color(204, 204, 204));
        groupAG.add(AG2);
        AG2.setText("2");
        AG2.setToolTipText("Nilai tengah diantara 2 nilai keputusan yang berdekatan");
        AG2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        AG2.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        AG2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AG2ActionPerformed(evt);
            }
        });
        ahpProcess.add(AG2, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 350, -1, -1));

        AG3.setBackground(new java.awt.Color(204, 204, 204));
        groupAG.add(AG3);
        AG3.setText("3");
        AG3.setToolTipText("Agak Lebih Penting");
        AG3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        AG3.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        AG3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AG3ActionPerformed(evt);
            }
        });
        ahpProcess.add(AG3, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 350, -1, -1));

        AG4.setBackground(new java.awt.Color(204, 204, 204));
        groupAG.add(AG4);
        AG4.setText("4");
        AG4.setToolTipText("Nilai tengah diantara 2 nilai keputusan yang berdekatan");
        AG4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        AG4.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        AG4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AG4ActionPerformed(evt);
            }
        });
        ahpProcess.add(AG4, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 350, -1, -1));

        AG5.setBackground(new java.awt.Color(204, 204, 204));
        groupAG.add(AG5);
        AG5.setText("5");
        AG5.setToolTipText("Cukup Lebih Penting");
        AG5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        AG5.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        AG5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AG5ActionPerformed(evt);
            }
        });
        ahpProcess.add(AG5, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 350, -1, -1));

        AG6.setBackground(new java.awt.Color(204, 204, 204));
        groupAG.add(AG6);
        AG6.setText("6");
        AG6.setToolTipText("Nilai tengah diantara 2 nilai keputusan yang berdekatan");
        AG6.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        AG6.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        AG6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AG6ActionPerformed(evt);
            }
        });
        ahpProcess.add(AG6, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 350, -1, -1));

        AG7.setBackground(new java.awt.Color(204, 204, 204));
        groupAG.add(AG7);
        AG7.setText("7");
        AG7.setToolTipText("Sangat Lebih Penting");
        AG7.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        AG7.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        AG7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AG7ActionPerformed(evt);
            }
        });
        ahpProcess.add(AG7, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 350, -1, -1));

        AG8.setBackground(new java.awt.Color(204, 204, 204));
        groupAG.add(AG8);
        AG8.setText("8");
        AG8.setToolTipText("Nilai tengah diantara 2 nilai keputusan yang berdekatan");
        AG8.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        AG8.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        AG8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AG8ActionPerformed(evt);
            }
        });
        ahpProcess.add(AG8, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 350, -1, -1));

        AG9.setBackground(new java.awt.Color(204, 204, 204));
        groupAG.add(AG9);
        AG9.setText("9");
        AG9.setToolTipText("Lebih Penting Ekstrim");
        AG9.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        AG9.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        AG9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AG9ActionPerformed(evt);
            }
        });
        ahpProcess.add(AG9, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 350, -1, -1));

        jSeparator6.setBackground(new java.awt.Color(0, 0, 0));
        ahpProcess.add(jSeparator6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 400, 880, 10));

        jLabel103.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel103.setText("Rerata Jumlah Tandan");
        ahpProcess.add(jLabel103, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 440, -1, 39));

        jLabel104.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel104.setText("Kerapatan Tanam");
        ahpProcess.add(jLabel104, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 440, -1, 39));

        AH1.setBackground(new java.awt.Color(204, 204, 204));
        groupAH.add(AH1);
        AH1.setText("1");
        AH1.setToolTipText("Sama Pentingnya");
        AH1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        AH1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        AH1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AH1ActionPerformed(evt);
            }
        });
        ahpProcess.add(AH1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 480, -1, -1));

        AH2.setBackground(new java.awt.Color(204, 204, 204));
        groupAH.add(AH2);
        AH2.setText("2");
        AH2.setToolTipText("Nilai tengah diantara 2 nilai keputusan yang berdekatan");
        AH2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        AH2.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        AH2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AH2ActionPerformed(evt);
            }
        });
        ahpProcess.add(AH2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 480, -1, -1));

        AH3.setBackground(new java.awt.Color(204, 204, 204));
        groupAH.add(AH3);
        AH3.setText("3");
        AH3.setToolTipText("Agak Lebih Penting");
        AH3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        AH3.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        AH3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AH3ActionPerformed(evt);
            }
        });
        ahpProcess.add(AH3, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 480, -1, -1));

        AH4.setBackground(new java.awt.Color(204, 204, 204));
        groupAH.add(AH4);
        AH4.setText("4");
        AH4.setToolTipText("Nilai tengah diantara 2 nilai keputusan yang berdekatan");
        AH4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        AH4.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        AH4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AH4ActionPerformed(evt);
            }
        });
        ahpProcess.add(AH4, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 480, -1, -1));

        AH5.setBackground(new java.awt.Color(204, 204, 204));
        groupAH.add(AH5);
        AH5.setText("5");
        AH5.setToolTipText("Cukup Lebih Penting");
        AH5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        AH5.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        AH5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AH5ActionPerformed(evt);
            }
        });
        ahpProcess.add(AH5, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 480, -1, -1));

        AH6.setBackground(new java.awt.Color(204, 204, 204));
        groupAH.add(AH6);
        AH6.setText("6");
        AH6.setToolTipText("Nilai tengah diantara 2 nilai keputusan yang berdekatan");
        AH6.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        AH6.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        AH6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AH6ActionPerformed(evt);
            }
        });
        ahpProcess.add(AH6, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 480, -1, -1));

        AH7.setBackground(new java.awt.Color(204, 204, 204));
        groupAH.add(AH7);
        AH7.setText("7");
        AH7.setToolTipText("Sangat Lebih Penting");
        AH7.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        AH7.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        AH7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AH7ActionPerformed(evt);
            }
        });
        ahpProcess.add(AH7, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 480, -1, -1));

        AH8.setBackground(new java.awt.Color(204, 204, 204));
        groupAH.add(AH8);
        AH8.setText("8");
        AH8.setToolTipText("Nilai tengah diantara 2 nilai keputusan yang berdekatan");
        AH8.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        AH8.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        AH8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AH8ActionPerformed(evt);
            }
        });
        ahpProcess.add(AH8, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 480, -1, -1));

        AH9.setBackground(new java.awt.Color(204, 204, 204));
        groupAH.add(AH9);
        AH9.setText("9");
        AH9.setToolTipText("Lebih Penting Ekstrim");
        AH9.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        AH9.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        AH9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AH9ActionPerformed(evt);
            }
        });
        ahpProcess.add(AH9, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 480, -1, -1));

        jLabel105.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel105.setText("Rerata Berat Tandan");
        ahpProcess.add(jLabel105, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 440, -1, 39));

        jLabel106.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel106.setText("Potensi TBS");
        ahpProcess.add(jLabel106, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 440, -1, 39));

        jSeparator7.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator7.setOrientation(javax.swing.SwingConstants.VERTICAL);
        ahpProcess.add(jSeparator7, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 410, 10, 120));

        BC1.setBackground(new java.awt.Color(204, 204, 204));
        groupBC.add(BC1);
        BC1.setText("1");
        BC1.setToolTipText("Sama Pentingnya");
        BC1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        BC1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        BC1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BC1ActionPerformed(evt);
            }
        });
        ahpProcess.add(BC1, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 480, -1, -1));

        BC2.setBackground(new java.awt.Color(204, 204, 204));
        groupBC.add(BC2);
        BC2.setText("2");
        BC2.setToolTipText("Nilai tengah diantara 2 nilai keputusan yang berdekatan");
        BC2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        BC2.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        BC2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BC2ActionPerformed(evt);
            }
        });
        ahpProcess.add(BC2, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 480, -1, -1));

        BC3.setBackground(new java.awt.Color(204, 204, 204));
        groupBC.add(BC3);
        BC3.setText("3");
        BC3.setToolTipText("Agak Lebih Penting");
        BC3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        BC3.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        BC3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BC3ActionPerformed(evt);
            }
        });
        ahpProcess.add(BC3, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 480, -1, -1));

        BC4.setBackground(new java.awt.Color(204, 204, 204));
        groupBC.add(BC4);
        BC4.setText("4");
        BC4.setToolTipText("Nilai tengah diantara 2 nilai keputusan yang berdekatan");
        BC4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        BC4.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        BC4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BC4ActionPerformed(evt);
            }
        });
        ahpProcess.add(BC4, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 480, -1, -1));

        BC5.setBackground(new java.awt.Color(204, 204, 204));
        groupBC.add(BC5);
        BC5.setText("5");
        BC5.setToolTipText("Cukup Lebih Penting");
        BC5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        BC5.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        BC5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BC5ActionPerformed(evt);
            }
        });
        ahpProcess.add(BC5, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 480, -1, -1));

        BC6.setBackground(new java.awt.Color(204, 204, 204));
        groupBC.add(BC6);
        BC6.setText("6");
        BC6.setToolTipText("Nilai tengah diantara 2 nilai keputusan yang berdekatan");
        BC6.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        BC6.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        BC6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BC6ActionPerformed(evt);
            }
        });
        ahpProcess.add(BC6, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 480, -1, -1));

        BC7.setBackground(new java.awt.Color(204, 204, 204));
        groupBC.add(BC7);
        BC7.setText("7");
        BC7.setToolTipText("Sangat Lebih Penting");
        BC7.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        BC7.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        BC7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BC7ActionPerformed(evt);
            }
        });
        ahpProcess.add(BC7, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 480, -1, -1));

        BC8.setBackground(new java.awt.Color(204, 204, 204));
        groupBC.add(BC8);
        BC8.setText("8");
        BC8.setToolTipText("Nilai tengah diantara 2 nilai keputusan yang berdekatan");
        BC8.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        BC8.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        BC8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BC8ActionPerformed(evt);
            }
        });
        ahpProcess.add(BC8, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 480, -1, -1));

        BC9.setBackground(new java.awt.Color(204, 204, 204));
        groupBC.add(BC9);
        BC9.setText("9");
        BC9.setToolTipText("Lebih Penting Ekstrim");
        BC9.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        BC9.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        BC9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BC9ActionPerformed(evt);
            }
        });
        ahpProcess.add(BC9, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 480, -1, -1));

        jSeparator8.setBackground(new java.awt.Color(0, 0, 0));
        ahpProcess.add(jSeparator8, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 530, 880, 10));

        jLabel107.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel107.setText("Rerata Berat Tandan");
        ahpProcess.add(jLabel107, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 570, -1, 39));

        jLabel34.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel34.setText("Rendemen");
        ahpProcess.add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 570, -1, 39));

        BD1.setBackground(new java.awt.Color(204, 204, 204));
        groupBD.add(BD1);
        BD1.setText("1");
        BD1.setToolTipText("Sama Pentingnya");
        BD1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        BD1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        BD1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BD1ActionPerformed(evt);
            }
        });
        ahpProcess.add(BD1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 610, -1, -1));

        BD2.setBackground(new java.awt.Color(204, 204, 204));
        groupBD.add(BD2);
        BD2.setText("2");
        BD2.setToolTipText("Nilai tengah diantara 2 nilai keputusan yang berdekatan");
        BD2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        BD2.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        BD2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BD2ActionPerformed(evt);
            }
        });
        ahpProcess.add(BD2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 610, -1, -1));

        BD3.setBackground(new java.awt.Color(204, 204, 204));
        groupBD.add(BD3);
        BD3.setText("3");
        BD3.setToolTipText("Agak Lebih Penting");
        BD3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        BD3.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        BD3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BD3ActionPerformed(evt);
            }
        });
        ahpProcess.add(BD3, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 610, -1, -1));

        BD4.setBackground(new java.awt.Color(204, 204, 204));
        groupBD.add(BD4);
        BD4.setText("4");
        BD4.setToolTipText("Nilai tengah diantara 2 nilai keputusan yang berdekatan");
        BD4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        BD4.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        BD4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BD4ActionPerformed(evt);
            }
        });
        ahpProcess.add(BD4, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 610, -1, -1));

        BD5.setBackground(new java.awt.Color(204, 204, 204));
        groupBD.add(BD5);
        BD5.setText("5");
        BD5.setToolTipText("Cukup Lebih Penting");
        BD5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        BD5.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        BD5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BD5ActionPerformed(evt);
            }
        });
        ahpProcess.add(BD5, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 610, -1, -1));

        BD6.setBackground(new java.awt.Color(204, 204, 204));
        groupBD.add(BD6);
        BD6.setText("6");
        BD6.setToolTipText("Nilai tengah diantara 2 nilai keputusan yang berdekatan");
        BD6.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        BD6.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        BD6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BD6ActionPerformed(evt);
            }
        });
        ahpProcess.add(BD6, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 610, -1, -1));

        BD7.setBackground(new java.awt.Color(204, 204, 204));
        groupBD.add(BD7);
        BD7.setText("7");
        BD7.setToolTipText("Sangat Lebih Penting");
        BD7.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        BD7.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        BD7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BD7ActionPerformed(evt);
            }
        });
        ahpProcess.add(BD7, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 610, -1, -1));

        BD8.setBackground(new java.awt.Color(204, 204, 204));
        groupBD.add(BD8);
        BD8.setText("8");
        BD8.setToolTipText("Nilai tengah diantara 2 nilai keputusan yang berdekatan");
        BD8.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        BD8.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        BD8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BD8ActionPerformed(evt);
            }
        });
        ahpProcess.add(BD8, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 610, -1, -1));

        BD9.setBackground(new java.awt.Color(204, 204, 204));
        groupBD.add(BD9);
        BD9.setText("9");
        BD9.setToolTipText("Lebih Penting Ekstrim");
        BD9.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        BD9.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        BD9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BD9ActionPerformed(evt);
            }
        });
        ahpProcess.add(BD9, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 610, -1, -1));

        jLabel36.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel36.setText("Rerata Berat Tandan");
        ahpProcess.add(jLabel36, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 570, -1, 39));

        jLabel37.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel37.setText("Potensi CPO");
        ahpProcess.add(jLabel37, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 570, -1, 39));

        jSeparator9.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator9.setOrientation(javax.swing.SwingConstants.VERTICAL);
        ahpProcess.add(jSeparator9, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 540, 10, 120));

        BE1.setBackground(new java.awt.Color(204, 204, 204));
        groupBE.add(BE1);
        BE1.setText("1");
        BE1.setToolTipText("Sama Pentingnya");
        BE1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        BE1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        BE1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BE1ActionPerformed(evt);
            }
        });
        ahpProcess.add(BE1, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 610, -1, -1));

        BE2.setBackground(new java.awt.Color(204, 204, 204));
        groupBE.add(BE2);
        BE2.setText("2");
        BE2.setToolTipText("Nilai tengah diantara 2 nilai keputusan yang berdekatan");
        BE2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        BE2.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        BE2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BE2ActionPerformed(evt);
            }
        });
        ahpProcess.add(BE2, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 610, -1, -1));

        BE3.setBackground(new java.awt.Color(204, 204, 204));
        groupBE.add(BE3);
        BE3.setText("3");
        BE3.setToolTipText("Agak Lebih Penting");
        BE3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        BE3.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        BE3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BE3ActionPerformed(evt);
            }
        });
        ahpProcess.add(BE3, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 610, -1, -1));

        BE4.setBackground(new java.awt.Color(204, 204, 204));
        groupBE.add(BE4);
        BE4.setText("4");
        BE4.setToolTipText("Nilai tengah diantara 2 nilai keputusan yang berdekatan");
        BE4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        BE4.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        BE4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BE4ActionPerformed(evt);
            }
        });
        ahpProcess.add(BE4, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 610, -1, -1));

        BE5.setBackground(new java.awt.Color(204, 204, 204));
        groupBE.add(BE5);
        BE5.setText("5");
        BE5.setToolTipText("Cukup Lebih Penting");
        BE5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        BE5.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        BE5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BE5ActionPerformed(evt);
            }
        });
        ahpProcess.add(BE5, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 610, -1, -1));

        BE6.setBackground(new java.awt.Color(204, 204, 204));
        groupBE.add(BE6);
        BE6.setText("6");
        BE6.setToolTipText("Nilai tengah diantara 2 nilai keputusan yang berdekatan");
        BE6.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        BE6.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        BE6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BE6ActionPerformed(evt);
            }
        });
        ahpProcess.add(BE6, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 610, -1, -1));

        BE7.setBackground(new java.awt.Color(204, 204, 204));
        groupBE.add(BE7);
        BE7.setText("7");
        BE7.setToolTipText("Sangat Lebih Penting");
        BE7.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        BE7.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        BE7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BE7ActionPerformed(evt);
            }
        });
        ahpProcess.add(BE7, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 610, -1, -1));

        BE8.setBackground(new java.awt.Color(204, 204, 204));
        groupBE.add(BE8);
        BE8.setText("8");
        BE8.setToolTipText("Nilai tengah diantara 2 nilai keputusan yang berdekatan");
        BE8.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        BE8.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        BE8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BE8ActionPerformed(evt);
            }
        });
        ahpProcess.add(BE8, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 610, -1, -1));

        BE9.setBackground(new java.awt.Color(204, 204, 204));
        groupBE.add(BE9);
        BE9.setText("9");
        BE9.setToolTipText("Lebih Penting Ekstrim");
        BE9.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        BE9.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        BE9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BE9ActionPerformed(evt);
            }
        });
        ahpProcess.add(BE9, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 610, -1, -1));

        jSeparator10.setBackground(new java.awt.Color(0, 0, 0));
        ahpProcess.add(jSeparator10, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 660, 880, 10));

        jLabel39.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel39.setText("Tinggi");
        ahpProcess.add(jLabel39, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 700, -1, 39));

        jSeparator11.setBackground(new java.awt.Color(0, 0, 0));
        ahpProcess.add(jSeparator11, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 790, 880, 10));

        BF1.setBackground(new java.awt.Color(204, 204, 204));
        groupBF.add(BF1);
        BF1.setText("1");
        BF1.setToolTipText("Sama Pentingnya");
        BF1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        BF1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        BF1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BF1ActionPerformed(evt);
            }
        });
        ahpProcess.add(BF1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 740, -1, -1));

        BF2.setBackground(new java.awt.Color(204, 204, 204));
        groupBF.add(BF2);
        BF2.setText("2");
        BF2.setToolTipText("Nilai tengah diantara 2 nilai keputusan yang berdekatan");
        BF2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        BF2.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        BF2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BF2ActionPerformed(evt);
            }
        });
        ahpProcess.add(BF2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 740, -1, -1));

        BF3.setBackground(new java.awt.Color(204, 204, 204));
        groupBF.add(BF3);
        BF3.setText("3");
        BF3.setToolTipText("Agak Lebih Penting");
        BF3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        BF3.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        BF3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BF3ActionPerformed(evt);
            }
        });
        ahpProcess.add(BF3, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 740, -1, -1));

        BF4.setBackground(new java.awt.Color(204, 204, 204));
        groupBF.add(BF4);
        BF4.setText("4");
        BF4.setToolTipText("Nilai tengah diantara 2 nilai keputusan yang berdekatan");
        BF4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        BF4.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        BF4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BF4ActionPerformed(evt);
            }
        });
        ahpProcess.add(BF4, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 740, -1, -1));

        BF5.setBackground(new java.awt.Color(204, 204, 204));
        groupBF.add(BF5);
        BF5.setText("5");
        BF5.setToolTipText("Cukup Lebih Penting");
        BF5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        BF5.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        BF5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BF5ActionPerformed(evt);
            }
        });
        ahpProcess.add(BF5, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 740, -1, -1));

        BF6.setBackground(new java.awt.Color(204, 204, 204));
        groupBF.add(BF6);
        BF6.setText("6");
        BF6.setToolTipText("Nilai tengah diantara 2 nilai keputusan yang berdekatan");
        BF6.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        BF6.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        BF6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BF6ActionPerformed(evt);
            }
        });
        ahpProcess.add(BF6, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 740, -1, -1));

        BF7.setBackground(new java.awt.Color(204, 204, 204));
        groupBF.add(BF7);
        BF7.setText("7");
        BF7.setToolTipText("Sangat Lebih Penting");
        BF7.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        BF7.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        BF7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BF7ActionPerformed(evt);
            }
        });
        ahpProcess.add(BF7, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 740, -1, -1));

        BF8.setBackground(new java.awt.Color(204, 204, 204));
        groupBF.add(BF8);
        BF8.setText("8");
        BF8.setToolTipText("Nilai tengah diantara 2 nilai keputusan yang berdekatan");
        BF8.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        BF8.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        BF8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BF8ActionPerformed(evt);
            }
        });
        ahpProcess.add(BF8, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 740, -1, -1));

        BF9.setBackground(new java.awt.Color(204, 204, 204));
        groupBF.add(BF9);
        BF9.setText("9");
        BF9.setToolTipText("Lebih Penting Ekstrim");
        BF9.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        BF9.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        BF9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BF9ActionPerformed(evt);
            }
        });
        ahpProcess.add(BF9, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 740, -1, -1));

        jSeparator12.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator12.setOrientation(javax.swing.SwingConstants.VERTICAL);
        ahpProcess.add(jSeparator12, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 670, 10, 120));

        jLabel41.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel41.setText("Rerata Berat Tandan");
        ahpProcess.add(jLabel41, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 700, -1, 39));

        BG1.setBackground(new java.awt.Color(204, 204, 204));
        groupBG.add(BG1);
        BG1.setText("1");
        BG1.setToolTipText("Sama Pentingnya");
        BG1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        BG1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        BG1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BG1ActionPerformed(evt);
            }
        });
        ahpProcess.add(BG1, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 740, -1, -1));

        BG2.setBackground(new java.awt.Color(204, 204, 204));
        groupBG.add(BG2);
        BG2.setText("2");
        BG2.setToolTipText("Nilai tengah diantara 2 nilai keputusan yang berdekatan");
        BG2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        BG2.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        BG2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BG2ActionPerformed(evt);
            }
        });
        ahpProcess.add(BG2, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 740, -1, -1));

        BG3.setBackground(new java.awt.Color(204, 204, 204));
        groupBG.add(BG3);
        BG3.setText("3");
        BG3.setToolTipText("Agak Lebih Penting");
        BG3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        BG3.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        BG3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BG3ActionPerformed(evt);
            }
        });
        ahpProcess.add(BG3, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 740, -1, -1));

        BG4.setBackground(new java.awt.Color(204, 204, 204));
        groupBG.add(BG4);
        BG4.setText("4");
        BG4.setToolTipText("Nilai tengah diantara 2 nilai keputusan yang berdekatan");
        BG4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        BG4.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        BG4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BG4ActionPerformed(evt);
            }
        });
        ahpProcess.add(BG4, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 740, -1, -1));

        BG5.setBackground(new java.awt.Color(204, 204, 204));
        groupBG.add(BG5);
        BG5.setText("5");
        BG5.setToolTipText("Cukup Lebih Penting");
        BG5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        BG5.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        BG5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BG5ActionPerformed(evt);
            }
        });
        ahpProcess.add(BG5, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 740, -1, -1));

        BG6.setBackground(new java.awt.Color(204, 204, 204));
        groupBG.add(BG6);
        BG6.setText("6");
        BG6.setToolTipText("Nilai tengah diantara 2 nilai keputusan yang berdekatan");
        BG6.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        BG6.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        BG6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BG6ActionPerformed(evt);
            }
        });
        ahpProcess.add(BG6, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 740, -1, -1));

        BG7.setBackground(new java.awt.Color(204, 204, 204));
        groupBG.add(BG7);
        BG7.setText("7");
        BG7.setToolTipText("Sangat Lebih Penting");
        BG7.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        BG7.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        BG7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BG7ActionPerformed(evt);
            }
        });
        ahpProcess.add(BG7, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 740, -1, -1));

        BG8.setBackground(new java.awt.Color(204, 204, 204));
        groupBG.add(BG8);
        BG8.setText("8");
        BG8.setToolTipText("Nilai tengah diantara 2 nilai keputusan yang berdekatan");
        BG8.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        BG8.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        BG8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BG8ActionPerformed(evt);
            }
        });
        ahpProcess.add(BG8, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 740, -1, -1));

        BG9.setBackground(new java.awt.Color(204, 204, 204));
        groupBG.add(BG9);
        BG9.setText("9");
        BG9.setToolTipText("Lebih Penting Ekstrim");
        BG9.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        BG9.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        BG9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BG9ActionPerformed(evt);
            }
        });
        ahpProcess.add(BG9, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 740, -1, -1));

        jSeparator13.setBackground(new java.awt.Color(0, 0, 0));
        ahpProcess.add(jSeparator13, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 920, 880, 10));

        jLabel43.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel43.setText("Rerata Berat Tandan");
        ahpProcess.add(jLabel43, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 700, -1, 39));

        jLabel44.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel44.setText("Panjang Pelepah");
        ahpProcess.add(jLabel44, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 700, -1, 39));

        BH1.setBackground(new java.awt.Color(204, 204, 204));
        groupBH.add(BH1);
        BH1.setText("1");
        BH1.setToolTipText("Sama Pentingnya");
        BH1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        BH1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        BH1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BH1ActionPerformed(evt);
            }
        });
        ahpProcess.add(BH1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 870, -1, -1));

        BH2.setBackground(new java.awt.Color(204, 204, 204));
        groupBH.add(BH2);
        BH2.setText("2");
        BH2.setToolTipText("Nilai tengah diantara 2 nilai keputusan yang berdekatan");
        BH2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        BH2.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        BH2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BH2ActionPerformed(evt);
            }
        });
        ahpProcess.add(BH2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 870, -1, -1));

        BH3.setBackground(new java.awt.Color(204, 204, 204));
        groupBH.add(BH3);
        BH3.setText("3");
        BH3.setToolTipText("Agak Lebih Penting");
        BH3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        BH3.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        BH3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BH3ActionPerformed(evt);
            }
        });
        ahpProcess.add(BH3, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 870, -1, -1));

        BH4.setBackground(new java.awt.Color(204, 204, 204));
        groupBH.add(BH4);
        BH4.setText("4");
        BH4.setToolTipText("Nilai tengah diantara 2 nilai keputusan yang berdekatan");
        BH4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        BH4.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        BH4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BH4ActionPerformed(evt);
            }
        });
        ahpProcess.add(BH4, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 870, -1, -1));

        BH5.setBackground(new java.awt.Color(204, 204, 204));
        groupBH.add(BH5);
        BH5.setText("5");
        BH5.setToolTipText("Cukup Lebih Penting");
        BH5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        BH5.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        BH5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BH5ActionPerformed(evt);
            }
        });
        ahpProcess.add(BH5, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 870, -1, -1));

        BH6.setBackground(new java.awt.Color(204, 204, 204));
        groupBH.add(BH6);
        BH6.setText("6");
        BH6.setToolTipText("Nilai tengah diantara 2 nilai keputusan yang berdekatan");
        BH6.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        BH6.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        BH6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BH6ActionPerformed(evt);
            }
        });
        ahpProcess.add(BH6, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 870, -1, -1));

        BH7.setBackground(new java.awt.Color(204, 204, 204));
        groupBH.add(BH7);
        BH7.setText("7");
        BH7.setToolTipText("Sangat Lebih Penting");
        BH7.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        BH7.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        BH7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BH7ActionPerformed(evt);
            }
        });
        ahpProcess.add(BH7, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 870, -1, -1));

        BH8.setBackground(new java.awt.Color(204, 204, 204));
        groupBH.add(BH8);
        BH8.setText("8");
        BH8.setToolTipText("Nilai tengah diantara 2 nilai keputusan yang berdekatan");
        BH8.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        BH8.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        BH8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BH8ActionPerformed(evt);
            }
        });
        ahpProcess.add(BH8, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 870, -1, -1));

        BH9.setBackground(new java.awt.Color(204, 204, 204));
        groupBH.add(BH9);
        BH9.setText("9");
        BH9.setToolTipText("Lebih Penting Ekstrim");
        BH9.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        BH9.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        BH9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BH9ActionPerformed(evt);
            }
        });
        ahpProcess.add(BH9, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 870, -1, -1));

        jLabel45.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel45.setText("Kerapatan Tanam");
        ahpProcess.add(jLabel45, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 830, -1, 39));

        CD1.setBackground(new java.awt.Color(204, 204, 204));
        groupCD.add(CD1);
        CD1.setText("1");
        CD1.setToolTipText("Sama Pentingnya");
        CD1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        CD1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        CD1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CD1ActionPerformed(evt);
            }
        });
        ahpProcess.add(CD1, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 870, -1, -1));

        CD2.setBackground(new java.awt.Color(204, 204, 204));
        groupCD.add(CD2);
        CD2.setText("2");
        CD2.setToolTipText("Nilai tengah diantara 2 nilai keputusan yang berdekatan");
        CD2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        CD2.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        CD2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CD2ActionPerformed(evt);
            }
        });
        ahpProcess.add(CD2, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 870, -1, -1));

        CD3.setBackground(new java.awt.Color(204, 204, 204));
        groupCD.add(CD3);
        CD3.setText("3");
        CD3.setToolTipText("Agak Lebih Penting");
        CD3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        CD3.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        CD3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CD3ActionPerformed(evt);
            }
        });
        ahpProcess.add(CD3, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 870, -1, -1));

        CD4.setBackground(new java.awt.Color(204, 204, 204));
        groupCD.add(CD4);
        CD4.setText("4");
        CD4.setToolTipText("Nilai tengah diantara 2 nilai keputusan yang berdekatan");
        CD4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        CD4.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        CD4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CD4ActionPerformed(evt);
            }
        });
        ahpProcess.add(CD4, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 870, -1, -1));

        CD5.setBackground(new java.awt.Color(204, 204, 204));
        groupCD.add(CD5);
        CD5.setText("5");
        CD5.setToolTipText("Cukup Lebih Penting");
        CD5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        CD5.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        CD5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CD5ActionPerformed(evt);
            }
        });
        ahpProcess.add(CD5, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 870, -1, -1));

        CD6.setBackground(new java.awt.Color(204, 204, 204));
        groupCD.add(CD6);
        CD6.setText("6");
        CD6.setToolTipText("Nilai tengah diantara 2 nilai keputusan yang berdekatan");
        CD6.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        CD6.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        CD6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CD6ActionPerformed(evt);
            }
        });
        ahpProcess.add(CD6, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 870, -1, -1));

        CD7.setBackground(new java.awt.Color(204, 204, 204));
        groupCD.add(CD7);
        CD7.setText("7");
        CD7.setToolTipText("Sangat Lebih Penting");
        CD7.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        CD7.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        CD7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CD7ActionPerformed(evt);
            }
        });
        ahpProcess.add(CD7, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 870, -1, -1));

        CD8.setBackground(new java.awt.Color(204, 204, 204));
        groupCD.add(CD8);
        CD8.setText("8");
        CD8.setToolTipText("Nilai tengah diantara 2 nilai keputusan yang berdekatan");
        CD8.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        CD8.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        CD8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CD8ActionPerformed(evt);
            }
        });
        ahpProcess.add(CD8, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 870, -1, -1));

        CD9.setBackground(new java.awt.Color(204, 204, 204));
        groupCD.add(CD9);
        CD9.setText("9");
        CD9.setToolTipText("Lebih Penting Ekstrim");
        CD9.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        CD9.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        CD9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CD9ActionPerformed(evt);
            }
        });
        ahpProcess.add(CD9, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 870, -1, -1));

        jLabel46.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel46.setText("Rerata Berat Tandan");
        ahpProcess.add(jLabel46, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 830, -1, 39));

        jLabel47.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel47.setText("Potensi TBS");
        ahpProcess.add(jLabel47, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 830, -1, 39));

        jSeparator14.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator14.setOrientation(javax.swing.SwingConstants.VERTICAL);
        ahpProcess.add(jSeparator14, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 800, 10, 120));

        jLabel50.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel50.setText("Rendemen");
        ahpProcess.add(jLabel50, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 830, -1, 39));

        CE1.setBackground(new java.awt.Color(204, 204, 204));
        groupCE.add(CE1);
        CE1.setText("1");
        CE1.setToolTipText("Sama Pentingnya");
        CE1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        CE1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        CE1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CE1ActionPerformed(evt);
            }
        });
        ahpProcess.add(CE1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 1000, -1, -1));

        CE2.setBackground(new java.awt.Color(204, 204, 204));
        groupCE.add(CE2);
        CE2.setText("2");
        CE2.setToolTipText("Nilai tengah diantara 2 nilai keputusan yang berdekatan");
        CE2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        CE2.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        CE2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CE2ActionPerformed(evt);
            }
        });
        ahpProcess.add(CE2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 1000, -1, -1));

        CE3.setBackground(new java.awt.Color(204, 204, 204));
        groupCE.add(CE3);
        CE3.setText("3");
        CE3.setToolTipText("Agak Lebih Penting");
        CE3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        CE3.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        CE3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CE3ActionPerformed(evt);
            }
        });
        ahpProcess.add(CE3, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 1000, -1, -1));

        CE4.setBackground(new java.awt.Color(204, 204, 204));
        groupCE.add(CE4);
        CE4.setText("4");
        CE4.setToolTipText("Nilai tengah diantara 2 nilai keputusan yang berdekatan");
        CE4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        CE4.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        CE4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CE4ActionPerformed(evt);
            }
        });
        ahpProcess.add(CE4, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 1000, -1, -1));

        CE5.setBackground(new java.awt.Color(204, 204, 204));
        groupCE.add(CE5);
        CE5.setText("5");
        CE5.setToolTipText("Cukup Lebih Penting");
        CE5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        CE5.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        CE5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CE5ActionPerformed(evt);
            }
        });
        ahpProcess.add(CE5, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 1000, -1, -1));

        CE6.setBackground(new java.awt.Color(204, 204, 204));
        groupCE.add(CE6);
        CE6.setText("6");
        CE6.setToolTipText("Nilai tengah diantara 2 nilai keputusan yang berdekatan");
        CE6.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        CE6.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        CE6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CE6ActionPerformed(evt);
            }
        });
        ahpProcess.add(CE6, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 1000, -1, -1));

        CE7.setBackground(new java.awt.Color(204, 204, 204));
        groupCE.add(CE7);
        CE7.setText("7");
        CE7.setToolTipText("Sangat Lebih Penting");
        CE7.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        CE7.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        CE7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CE7ActionPerformed(evt);
            }
        });
        ahpProcess.add(CE7, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 1000, -1, -1));

        CE8.setBackground(new java.awt.Color(204, 204, 204));
        groupCE.add(CE8);
        CE8.setText("8");
        CE8.setToolTipText("Nilai tengah diantara 2 nilai keputusan yang berdekatan");
        CE8.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        CE8.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        CE8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CE8ActionPerformed(evt);
            }
        });
        ahpProcess.add(CE8, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 1000, -1, -1));

        CE9.setBackground(new java.awt.Color(204, 204, 204));
        groupCE.add(CE9);
        CE9.setText("9");
        CE9.setToolTipText("Lebih Penting Ekstrim");
        CE9.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        CE9.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        CE9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CE9ActionPerformed(evt);
            }
        });
        ahpProcess.add(CE9, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 1000, -1, -1));

        jSeparator15.setBackground(new java.awt.Color(0, 0, 0));
        ahpProcess.add(jSeparator15, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 1050, 880, 10));

        CF1.setBackground(new java.awt.Color(204, 204, 204));
        groupCF.add(CF1);
        CF1.setText("1");
        CF1.setToolTipText("Sama Pentingnya");
        CF1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        CF1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        CF1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CF1ActionPerformed(evt);
            }
        });
        ahpProcess.add(CF1, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 1000, -1, -1));

        CF2.setBackground(new java.awt.Color(204, 204, 204));
        groupCF.add(CF2);
        CF2.setText("2");
        CF2.setToolTipText("Nilai tengah diantara 2 nilai keputusan yang berdekatan");
        CF2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        CF2.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        CF2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CF2ActionPerformed(evt);
            }
        });
        ahpProcess.add(CF2, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 1000, -1, -1));

        CF3.setBackground(new java.awt.Color(204, 204, 204));
        groupCF.add(CF3);
        CF3.setText("3");
        CF3.setToolTipText("Agak Lebih Penting");
        CF3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        CF3.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        CF3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CF3ActionPerformed(evt);
            }
        });
        ahpProcess.add(CF3, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 1000, -1, -1));

        CF4.setBackground(new java.awt.Color(204, 204, 204));
        groupCF.add(CF4);
        CF4.setText("4");
        CF4.setToolTipText("Nilai tengah diantara 2 nilai keputusan yang berdekatan");
        CF4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        CF4.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        CF4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CF4ActionPerformed(evt);
            }
        });
        ahpProcess.add(CF4, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 1000, -1, -1));

        CF5.setBackground(new java.awt.Color(204, 204, 204));
        groupCF.add(CF5);
        CF5.setText("5");
        CF5.setToolTipText("Cukup Lebih Penting");
        CF5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        CF5.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        CF5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CF5ActionPerformed(evt);
            }
        });
        ahpProcess.add(CF5, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 1000, -1, -1));

        CF6.setBackground(new java.awt.Color(204, 204, 204));
        groupCF.add(CF6);
        CF6.setText("6");
        CF6.setToolTipText("Nilai tengah diantara 2 nilai keputusan yang berdekatan");
        CF6.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        CF6.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        CF6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CF6ActionPerformed(evt);
            }
        });
        ahpProcess.add(CF6, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 1000, -1, -1));

        CF7.setBackground(new java.awt.Color(204, 204, 204));
        groupCF.add(CF7);
        CF7.setText("7");
        CF7.setToolTipText("Sangat Lebih Penting");
        CF7.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        CF7.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        CF7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CF7ActionPerformed(evt);
            }
        });
        ahpProcess.add(CF7, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 1000, -1, -1));

        CF8.setBackground(new java.awt.Color(204, 204, 204));
        groupCF.add(CF8);
        CF8.setText("8");
        CF8.setToolTipText("Nilai tengah diantara 2 nilai keputusan yang berdekatan");
        CF8.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        CF8.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        CF8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CF8ActionPerformed(evt);
            }
        });
        ahpProcess.add(CF8, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 1000, -1, -1));

        CF9.setBackground(new java.awt.Color(204, 204, 204));
        groupCF.add(CF9);
        CF9.setText("9");
        CF9.setToolTipText("Lebih Penting Ekstrim");
        CF9.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        CF9.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        CF9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CF9ActionPerformed(evt);
            }
        });
        ahpProcess.add(CF9, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 1000, -1, -1));

        jLabel51.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel51.setText("Potensi CPO");
        ahpProcess.add(jLabel51, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 960, -1, 39));

        jLabel52.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel52.setText("Potensi TBS");
        ahpProcess.add(jLabel52, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 960, -1, 39));

        jSeparator16.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator16.setOrientation(javax.swing.SwingConstants.VERTICAL);
        ahpProcess.add(jSeparator16, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 930, 10, 120));

        CG1.setBackground(new java.awt.Color(204, 204, 204));
        groupCG.add(CG1);
        CG1.setText("1");
        CG1.setToolTipText("Sama Pentingnya");
        CG1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        CG1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        CG1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CG1ActionPerformed(evt);
            }
        });
        ahpProcess.add(CG1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 1130, -1, -1));

        CG2.setBackground(new java.awt.Color(204, 204, 204));
        groupCG.add(CG2);
        CG2.setText("2");
        CG2.setToolTipText("Nilai tengah diantara 2 nilai keputusan yang berdekatan");
        CG2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        CG2.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        CG2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CG2ActionPerformed(evt);
            }
        });
        ahpProcess.add(CG2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 1130, -1, -1));

        CG3.setBackground(new java.awt.Color(204, 204, 204));
        groupCG.add(CG3);
        CG3.setText("3");
        CG3.setToolTipText("Agak Lebih Penting");
        CG3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        CG3.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        CG3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CG3ActionPerformed(evt);
            }
        });
        ahpProcess.add(CG3, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 1130, -1, -1));

        CG4.setBackground(new java.awt.Color(204, 204, 204));
        groupCG.add(CG4);
        CG4.setText("4");
        CG4.setToolTipText("Nilai tengah diantara 2 nilai keputusan yang berdekatan");
        CG4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        CG4.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        CG4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CG4ActionPerformed(evt);
            }
        });
        ahpProcess.add(CG4, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 1130, -1, -1));

        CG5.setBackground(new java.awt.Color(204, 204, 204));
        groupCG.add(CG5);
        CG5.setText("5");
        CG5.setToolTipText("Cukup Lebih Penting");
        CG5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        CG5.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        CG5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CG5ActionPerformed(evt);
            }
        });
        ahpProcess.add(CG5, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 1130, -1, -1));

        CG6.setBackground(new java.awt.Color(204, 204, 204));
        groupCG.add(CG6);
        CG6.setText("6");
        CG6.setToolTipText("Nilai tengah diantara 2 nilai keputusan yang berdekatan");
        CG6.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        CG6.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        CG6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CG6ActionPerformed(evt);
            }
        });
        ahpProcess.add(CG6, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 1130, -1, -1));

        CG7.setBackground(new java.awt.Color(204, 204, 204));
        groupCG.add(CG7);
        CG7.setText("7");
        CG7.setToolTipText("Sangat Lebih Penting");
        CG7.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        CG7.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        CG7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CG7ActionPerformed(evt);
            }
        });
        ahpProcess.add(CG7, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 1130, -1, -1));

        CG8.setBackground(new java.awt.Color(204, 204, 204));
        groupCG.add(CG8);
        CG8.setText("8");
        CG8.setToolTipText("Nilai tengah diantara 2 nilai keputusan yang berdekatan");
        CG8.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        CG8.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        CG8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CG8ActionPerformed(evt);
            }
        });
        ahpProcess.add(CG8, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 1130, -1, -1));

        CG9.setBackground(new java.awt.Color(204, 204, 204));
        groupCG.add(CG9);
        CG9.setText("9");
        CG9.setToolTipText("Lebih Penting Ekstrim");
        CG9.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        CG9.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        CG9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CG9ActionPerformed(evt);
            }
        });
        ahpProcess.add(CG9, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 1130, -1, -1));

        jLabel55.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel55.setText("Potensi TBS");
        ahpProcess.add(jLabel55, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 960, -1, 39));

        jLabel56.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel56.setText("Tinggi");
        ahpProcess.add(jLabel56, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 960, -1, 39));

        jSeparator17.setBackground(new java.awt.Color(0, 0, 0));
        ahpProcess.add(jSeparator17, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 1180, 880, 10));

        CH1.setBackground(new java.awt.Color(204, 204, 204));
        groupCH.add(CH1);
        CH1.setText("1");
        CH1.setToolTipText("Sama Pentingnya");
        CH1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        CH1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        CH1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CH1ActionPerformed(evt);
            }
        });
        ahpProcess.add(CH1, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 1130, -1, -1));

        CH2.setBackground(new java.awt.Color(204, 204, 204));
        groupCH.add(CH2);
        CH2.setText("2");
        CH2.setToolTipText("Nilai tengah diantara 2 nilai keputusan yang berdekatan");
        CH2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        CH2.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        CH2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CH2ActionPerformed(evt);
            }
        });
        ahpProcess.add(CH2, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 1130, -1, -1));

        CH3.setBackground(new java.awt.Color(204, 204, 204));
        groupCH.add(CH3);
        CH3.setText("3");
        CH3.setToolTipText("Agak Lebih Penting");
        CH3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        CH3.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        CH3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CH3ActionPerformed(evt);
            }
        });
        ahpProcess.add(CH3, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 1130, -1, -1));

        CH4.setBackground(new java.awt.Color(204, 204, 204));
        groupCH.add(CH4);
        CH4.setText("4");
        CH4.setToolTipText("Nilai tengah diantara 2 nilai keputusan yang berdekatan");
        CH4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        CH4.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        CH4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CH4ActionPerformed(evt);
            }
        });
        ahpProcess.add(CH4, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 1130, -1, -1));

        CH5.setBackground(new java.awt.Color(204, 204, 204));
        groupCH.add(CH5);
        CH5.setText("5");
        CH5.setToolTipText("Cukup Lebih Penting");
        CH5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        CH5.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        CH5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CH5ActionPerformed(evt);
            }
        });
        ahpProcess.add(CH5, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 1130, -1, -1));

        CH6.setBackground(new java.awt.Color(204, 204, 204));
        groupCH.add(CH6);
        CH6.setText("6");
        CH6.setToolTipText("Nilai tengah diantara 2 nilai keputusan yang berdekatan");
        CH6.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        CH6.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        CH6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CH6ActionPerformed(evt);
            }
        });
        ahpProcess.add(CH6, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 1130, -1, -1));

        CH7.setBackground(new java.awt.Color(204, 204, 204));
        groupCH.add(CH7);
        CH7.setText("7");
        CH7.setToolTipText("Sangat Lebih Penting");
        CH7.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        CH7.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        CH7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CH7ActionPerformed(evt);
            }
        });
        ahpProcess.add(CH7, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 1130, -1, -1));

        CH8.setBackground(new java.awt.Color(204, 204, 204));
        groupCH.add(CH8);
        CH8.setText("8");
        CH8.setToolTipText("Nilai tengah diantara 2 nilai keputusan yang berdekatan");
        CH8.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        CH8.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        CH8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CH8ActionPerformed(evt);
            }
        });
        ahpProcess.add(CH8, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 1130, -1, -1));

        CH9.setBackground(new java.awt.Color(204, 204, 204));
        groupCH.add(CH9);
        CH9.setText("9");
        CH9.setToolTipText("Lebih Penting Ekstrim");
        CH9.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        CH9.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        CH9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CH9ActionPerformed(evt);
            }
        });
        ahpProcess.add(CH9, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 1130, -1, -1));

        jLabel57.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel57.setText("Panjang Pelepah");
        ahpProcess.add(jLabel57, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 1090, -1, 39));

        jLabel59.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel59.setText("Potensi TBS");
        ahpProcess.add(jLabel59, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 1090, -1, 39));

        jSeparator18.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator18.setOrientation(javax.swing.SwingConstants.VERTICAL);
        ahpProcess.add(jSeparator18, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 1060, 10, 120));

        DE1.setBackground(new java.awt.Color(204, 204, 204));
        groupDE.add(DE1);
        DE1.setText("1");
        DE1.setToolTipText("Sama Pentingnya");
        DE1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        DE1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        DE1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DE1ActionPerformed(evt);
            }
        });
        ahpProcess.add(DE1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 1260, -1, -1));

        DE2.setBackground(new java.awt.Color(204, 204, 204));
        groupDE.add(DE2);
        DE2.setText("2");
        DE2.setToolTipText("Nilai tengah diantara 2 nilai keputusan yang berdekatan");
        DE2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        DE2.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        DE2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DE2ActionPerformed(evt);
            }
        });
        ahpProcess.add(DE2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 1260, -1, -1));

        DE3.setBackground(new java.awt.Color(204, 204, 204));
        groupDE.add(DE3);
        DE3.setText("3");
        DE3.setToolTipText("Agak Lebih Penting");
        DE3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        DE3.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        DE3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DE3ActionPerformed(evt);
            }
        });
        ahpProcess.add(DE3, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 1260, -1, -1));

        DE4.setBackground(new java.awt.Color(204, 204, 204));
        groupDE.add(DE4);
        DE4.setText("4");
        DE4.setToolTipText("Nilai tengah diantara 2 nilai keputusan yang berdekatan");
        DE4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        DE4.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        DE4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DE4ActionPerformed(evt);
            }
        });
        ahpProcess.add(DE4, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 1260, -1, -1));

        DE5.setBackground(new java.awt.Color(204, 204, 204));
        groupDE.add(DE5);
        DE5.setText("5");
        DE5.setToolTipText("Cukup Lebih Penting");
        DE5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        DE5.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        DE5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DE5ActionPerformed(evt);
            }
        });
        ahpProcess.add(DE5, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 1260, -1, -1));

        DE6.setBackground(new java.awt.Color(204, 204, 204));
        groupDE.add(DE6);
        DE6.setText("6");
        DE6.setToolTipText("Nilai tengah diantara 2 nilai keputusan yang berdekatan");
        DE6.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        DE6.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        DE6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DE6ActionPerformed(evt);
            }
        });
        ahpProcess.add(DE6, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 1260, -1, -1));

        DE7.setBackground(new java.awt.Color(204, 204, 204));
        groupDE.add(DE7);
        DE7.setText("7");
        DE7.setToolTipText("Sangat Lebih Penting");
        DE7.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        DE7.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        DE7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DE7ActionPerformed(evt);
            }
        });
        ahpProcess.add(DE7, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 1260, -1, -1));

        DE8.setBackground(new java.awt.Color(204, 204, 204));
        groupDE.add(DE8);
        DE8.setText("8");
        DE8.setToolTipText("Nilai tengah diantara 2 nilai keputusan yang berdekatan");
        DE8.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        DE8.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        DE8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DE8ActionPerformed(evt);
            }
        });
        ahpProcess.add(DE8, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 1260, -1, -1));

        DE9.setBackground(new java.awt.Color(204, 204, 204));
        groupDE.add(DE9);
        DE9.setText("9");
        DE9.setToolTipText("Lebih Penting Ekstrim");
        DE9.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        DE9.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        DE9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DE9ActionPerformed(evt);
            }
        });
        ahpProcess.add(DE9, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 1260, -1, -1));

        jLabel61.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel61.setText("Potensi TBS");
        ahpProcess.add(jLabel61, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 1090, -1, 39));

        jLabel62.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel62.setText("Kerapatan Tanam");
        ahpProcess.add(jLabel62, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 1090, -1, 39));

        DF1.setBackground(new java.awt.Color(204, 204, 204));
        groupDF.add(DF1);
        DF1.setText("1");
        DF1.setToolTipText("Sama Pentingnya");
        DF1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        DF1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        DF1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DF1ActionPerformed(evt);
            }
        });
        ahpProcess.add(DF1, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 1260, -1, -1));

        DF2.setBackground(new java.awt.Color(204, 204, 204));
        groupDF.add(DF2);
        DF2.setText("2");
        DF2.setToolTipText("Nilai tengah diantara 2 nilai keputusan yang berdekatan");
        DF2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        DF2.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        DF2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DF2ActionPerformed(evt);
            }
        });
        ahpProcess.add(DF2, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 1260, -1, -1));

        DF3.setBackground(new java.awt.Color(204, 204, 204));
        groupDF.add(DF3);
        DF3.setText("3");
        DF3.setToolTipText("Agak Lebih Penting");
        DF3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        DF3.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        DF3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DF3ActionPerformed(evt);
            }
        });
        ahpProcess.add(DF3, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 1260, -1, -1));

        DF4.setBackground(new java.awt.Color(204, 204, 204));
        groupDF.add(DF4);
        DF4.setText("4");
        DF4.setToolTipText("Nilai tengah diantara 2 nilai keputusan yang berdekatan");
        DF4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        DF4.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        DF4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DF4ActionPerformed(evt);
            }
        });
        ahpProcess.add(DF4, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 1260, -1, -1));

        DF5.setBackground(new java.awt.Color(204, 204, 204));
        groupDF.add(DF5);
        DF5.setText("5");
        DF5.setToolTipText("Cukup Lebih Penting");
        DF5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        DF5.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        DF5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DF5ActionPerformed(evt);
            }
        });
        ahpProcess.add(DF5, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 1260, -1, -1));

        DF6.setBackground(new java.awt.Color(204, 204, 204));
        groupDF.add(DF6);
        DF6.setText("6");
        DF6.setToolTipText("Nilai tengah diantara 2 nilai keputusan yang berdekatan");
        DF6.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        DF6.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        DF6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DF6ActionPerformed(evt);
            }
        });
        ahpProcess.add(DF6, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 1260, -1, -1));

        DF7.setBackground(new java.awt.Color(204, 204, 204));
        groupDF.add(DF7);
        DF7.setText("7");
        DF7.setToolTipText("Sangat Lebih Penting");
        DF7.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        DF7.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        DF7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DF7ActionPerformed(evt);
            }
        });
        ahpProcess.add(DF7, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 1260, -1, -1));

        DF8.setBackground(new java.awt.Color(204, 204, 204));
        groupDF.add(DF8);
        DF8.setText("8");
        DF8.setToolTipText("Nilai tengah diantara 2 nilai keputusan yang berdekatan");
        DF8.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        DF8.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        DF8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DF8ActionPerformed(evt);
            }
        });
        ahpProcess.add(DF8, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 1260, -1, -1));

        DF9.setBackground(new java.awt.Color(204, 204, 204));
        groupDF.add(DF9);
        DF9.setText("9");
        DF9.setToolTipText("Lebih Penting Ekstrim");
        DF9.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        DF9.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        DF9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DF9ActionPerformed(evt);
            }
        });
        ahpProcess.add(DF9, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 1260, -1, -1));

        jSeparator19.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator19.setOrientation(javax.swing.SwingConstants.VERTICAL);
        ahpProcess.add(jSeparator19, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 1190, 10, 120));

        jLabel64.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel64.setText("Potensi CPO");
        ahpProcess.add(jLabel64, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 1220, -1, 39));

        jLabel65.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel65.setText("Tinggi");
        ahpProcess.add(jLabel65, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 1220, -1, 39));

        jSeparator20.setBackground(new java.awt.Color(0, 0, 0));
        ahpProcess.add(jSeparator20, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 1310, 880, 10));

        DG1.setBackground(new java.awt.Color(204, 204, 204));
        groupDG.add(DG1);
        DG1.setText("1");
        DG1.setToolTipText("Sama Pentingnya");
        DG1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        DG1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        DG1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DG1ActionPerformed(evt);
            }
        });
        ahpProcess.add(DG1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 1390, -1, -1));

        DG2.setBackground(new java.awt.Color(204, 204, 204));
        groupDG.add(DG2);
        DG2.setText("2");
        DG2.setToolTipText("Nilai tengah diantara 2 nilai keputusan yang berdekatan");
        DG2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        DG2.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        DG2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DG2ActionPerformed(evt);
            }
        });
        ahpProcess.add(DG2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 1390, -1, -1));

        DG3.setBackground(new java.awt.Color(204, 204, 204));
        groupDG.add(DG3);
        DG3.setText("3");
        DG3.setToolTipText("Agak Lebih Penting");
        DG3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        DG3.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        DG3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DG3ActionPerformed(evt);
            }
        });
        ahpProcess.add(DG3, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 1390, -1, -1));

        DG4.setBackground(new java.awt.Color(204, 204, 204));
        groupDG.add(DG4);
        DG4.setText("4");
        DG4.setToolTipText("Nilai tengah diantara 2 nilai keputusan yang berdekatan");
        DG4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        DG4.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        DG4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DG4ActionPerformed(evt);
            }
        });
        ahpProcess.add(DG4, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 1390, -1, -1));

        DG5.setBackground(new java.awt.Color(204, 204, 204));
        groupDG.add(DG5);
        DG5.setText("5");
        DG5.setToolTipText("Cukup Lebih Penting");
        DG5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        DG5.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        DG5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DG5ActionPerformed(evt);
            }
        });
        ahpProcess.add(DG5, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 1390, -1, -1));

        DG6.setBackground(new java.awt.Color(204, 204, 204));
        groupDG.add(DG6);
        DG6.setText("6");
        DG6.setToolTipText("Nilai tengah diantara 2 nilai keputusan yang berdekatan");
        DG6.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        DG6.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        DG6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DG6ActionPerformed(evt);
            }
        });
        ahpProcess.add(DG6, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 1390, -1, -1));

        DG7.setBackground(new java.awt.Color(204, 204, 204));
        groupDG.add(DG7);
        DG7.setText("7");
        DG7.setToolTipText("Sangat Lebih Penting");
        DG7.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        DG7.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        DG7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DG7ActionPerformed(evt);
            }
        });
        ahpProcess.add(DG7, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 1390, -1, -1));

        DG8.setBackground(new java.awt.Color(204, 204, 204));
        groupDG.add(DG8);
        DG8.setText("8");
        DG8.setToolTipText("Nilai tengah diantara 2 nilai keputusan yang berdekatan");
        DG8.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        DG8.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        DG8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DG8ActionPerformed(evt);
            }
        });
        ahpProcess.add(DG8, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 1390, -1, -1));

        DG9.setBackground(new java.awt.Color(204, 204, 204));
        groupDG.add(DG9);
        DG9.setText("9");
        DG9.setToolTipText("Lebih Penting Ekstrim");
        DG9.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        DG9.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        DG9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DG9ActionPerformed(evt);
            }
        });
        ahpProcess.add(DG9, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 1390, -1, -1));

        jLabel66.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel66.setText("Rendemen");
        ahpProcess.add(jLabel66, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 1220, -1, 39));

        jLabel67.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel67.setText("Rendemen");
        ahpProcess.add(jLabel67, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 1220, -1, 39));

        DH1.setBackground(new java.awt.Color(204, 204, 204));
        groupDH.add(DH1);
        DH1.setText("1");
        DH1.setToolTipText("Sama Pentingnya");
        DH1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        DH1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        DH1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DH1ActionPerformed(evt);
            }
        });
        ahpProcess.add(DH1, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 1390, -1, -1));

        DH2.setBackground(new java.awt.Color(204, 204, 204));
        groupDH.add(DH2);
        DH2.setText("2");
        DH2.setToolTipText("Nilai tengah diantara 2 nilai keputusan yang berdekatan");
        DH2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        DH2.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        DH2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DH2ActionPerformed(evt);
            }
        });
        ahpProcess.add(DH2, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 1390, -1, -1));

        DH3.setBackground(new java.awt.Color(204, 204, 204));
        groupDH.add(DH3);
        DH3.setText("3");
        DH3.setToolTipText("Agak Lebih Penting");
        DH3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        DH3.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        DH3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DH3ActionPerformed(evt);
            }
        });
        ahpProcess.add(DH3, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 1390, -1, -1));

        DH4.setBackground(new java.awt.Color(204, 204, 204));
        groupDH.add(DH4);
        DH4.setText("4");
        DH4.setToolTipText("Nilai tengah diantara 2 nilai keputusan yang berdekatan");
        DH4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        DH4.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        DH4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DH4ActionPerformed(evt);
            }
        });
        ahpProcess.add(DH4, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 1390, -1, -1));

        DH5.setBackground(new java.awt.Color(204, 204, 204));
        groupDH.add(DH5);
        DH5.setText("5");
        DH5.setToolTipText("Cukup Lebih Penting");
        DH5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        DH5.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        DH5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DH5ActionPerformed(evt);
            }
        });
        ahpProcess.add(DH5, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 1390, -1, -1));

        DH6.setBackground(new java.awt.Color(204, 204, 204));
        groupDH.add(DH6);
        DH6.setText("6");
        DH6.setToolTipText("Nilai tengah diantara 2 nilai keputusan yang berdekatan");
        DH6.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        DH6.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        DH6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DH6ActionPerformed(evt);
            }
        });
        ahpProcess.add(DH6, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 1390, -1, -1));

        DH7.setBackground(new java.awt.Color(204, 204, 204));
        groupDH.add(DH7);
        DH7.setText("7");
        DH7.setToolTipText("Sangat Lebih Penting");
        DH7.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        DH7.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        DH7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DH7ActionPerformed(evt);
            }
        });
        ahpProcess.add(DH7, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 1390, -1, -1));

        DH8.setBackground(new java.awt.Color(204, 204, 204));
        groupDH.add(DH8);
        DH8.setText("8");
        DH8.setToolTipText("Nilai tengah diantara 2 nilai keputusan yang berdekatan");
        DH8.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        DH8.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        DH8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DH8ActionPerformed(evt);
            }
        });
        ahpProcess.add(DH8, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 1390, -1, -1));

        DH9.setBackground(new java.awt.Color(204, 204, 204));
        groupDH.add(DH9);
        DH9.setText("9");
        DH9.setToolTipText("Lebih Penting Ekstrim");
        DH9.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        DH9.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        DH9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DH9ActionPerformed(evt);
            }
        });
        ahpProcess.add(DH9, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 1390, -1, -1));

        jLabel69.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel69.setText("Rendemen");
        ahpProcess.add(jLabel69, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 1350, -1, 39));

        jLabel71.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel71.setText("Rendemen");
        ahpProcess.add(jLabel71, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 1350, -1, 39));

        jLabel72.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel72.setText("Kerapatan Tanam");
        ahpProcess.add(jLabel72, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 1350, -1, 39));

        jSeparator21.setBackground(new java.awt.Color(0, 0, 0));
        ahpProcess.add(jSeparator21, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 1440, 880, 10));

        EF1.setBackground(new java.awt.Color(204, 204, 204));
        groupEF.add(EF1);
        EF1.setText("1");
        EF1.setToolTipText("Sama Pentingnya");
        EF1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        EF1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        EF1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EF1ActionPerformed(evt);
            }
        });
        ahpProcess.add(EF1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 1520, -1, -1));

        EF2.setBackground(new java.awt.Color(204, 204, 204));
        groupEF.add(EF2);
        EF2.setText("2");
        EF2.setToolTipText("Nilai tengah diantara 2 nilai keputusan yang berdekatan");
        EF2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        EF2.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        EF2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EF2ActionPerformed(evt);
            }
        });
        ahpProcess.add(EF2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 1520, -1, -1));

        EF3.setBackground(new java.awt.Color(204, 204, 204));
        groupEF.add(EF3);
        EF3.setText("3");
        EF3.setToolTipText("Agak Lebih Penting");
        EF3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        EF3.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        EF3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EF3ActionPerformed(evt);
            }
        });
        ahpProcess.add(EF3, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 1520, -1, -1));

        EF4.setBackground(new java.awt.Color(204, 204, 204));
        groupEF.add(EF4);
        EF4.setText("4");
        EF4.setToolTipText("Nilai tengah diantara 2 nilai keputusan yang berdekatan");
        EF4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        EF4.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        EF4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EF4ActionPerformed(evt);
            }
        });
        ahpProcess.add(EF4, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 1520, -1, -1));

        EF5.setBackground(new java.awt.Color(204, 204, 204));
        groupEF.add(EF5);
        EF5.setText("5");
        EF5.setToolTipText("Cukup Lebih Penting");
        EF5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        EF5.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        EF5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EF5ActionPerformed(evt);
            }
        });
        ahpProcess.add(EF5, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 1520, -1, -1));

        EF6.setBackground(new java.awt.Color(204, 204, 204));
        groupEF.add(EF6);
        EF6.setText("6");
        EF6.setToolTipText("Nilai tengah diantara 2 nilai keputusan yang berdekatan");
        EF6.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        EF6.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        EF6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EF6ActionPerformed(evt);
            }
        });
        ahpProcess.add(EF6, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 1520, -1, -1));

        EF7.setBackground(new java.awt.Color(204, 204, 204));
        groupEF.add(EF7);
        EF7.setText("7");
        EF7.setToolTipText("Sangat Lebih Penting");
        EF7.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        EF7.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        EF7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EF7ActionPerformed(evt);
            }
        });
        ahpProcess.add(EF7, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 1520, -1, -1));

        EF8.setBackground(new java.awt.Color(204, 204, 204));
        groupEF.add(EF8);
        EF8.setText("8");
        EF8.setToolTipText("Nilai tengah diantara 2 nilai keputusan yang berdekatan");
        EF8.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        EF8.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        EF8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EF8ActionPerformed(evt);
            }
        });
        ahpProcess.add(EF8, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 1520, -1, -1));

        EF9.setBackground(new java.awt.Color(204, 204, 204));
        groupEF.add(EF9);
        EF9.setText("9");
        EF9.setToolTipText("Lebih Penting Ekstrim");
        EF9.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        EF9.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        EF9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EF9ActionPerformed(evt);
            }
        });
        ahpProcess.add(EF9, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 1520, -1, -1));

        jLabel73.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel73.setText("Panjang Pelepah");
        ahpProcess.add(jLabel73, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 1350, -1, 39));

        jSeparator22.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator22.setOrientation(javax.swing.SwingConstants.VERTICAL);
        ahpProcess.add(jSeparator22, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 1320, 10, 120));

        EG1.setBackground(new java.awt.Color(204, 204, 204));
        groupEG.add(EG1);
        EG1.setText("1");
        EG1.setToolTipText("Sama Pentingnya");
        EG1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        EG1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        EG1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EG1ActionPerformed(evt);
            }
        });
        ahpProcess.add(EG1, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 1520, -1, -1));

        EG2.setBackground(new java.awt.Color(204, 204, 204));
        groupEG.add(EG2);
        EG2.setText("2");
        EG2.setToolTipText("Nilai tengah diantara 2 nilai keputusan yang berdekatan");
        EG2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        EG2.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        EG2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EG2ActionPerformed(evt);
            }
        });
        ahpProcess.add(EG2, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 1520, -1, -1));

        EG3.setBackground(new java.awt.Color(204, 204, 204));
        groupEG.add(EG3);
        EG3.setText("3");
        EG3.setToolTipText("Agak Lebih Penting");
        EG3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        EG3.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        EG3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EG3ActionPerformed(evt);
            }
        });
        ahpProcess.add(EG3, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 1520, -1, -1));

        EG4.setBackground(new java.awt.Color(204, 204, 204));
        groupEG.add(EG4);
        EG4.setText("4");
        EG4.setToolTipText("Nilai tengah diantara 2 nilai keputusan yang berdekatan");
        EG4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        EG4.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        EG4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EG4ActionPerformed(evt);
            }
        });
        ahpProcess.add(EG4, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 1520, -1, -1));

        EG5.setBackground(new java.awt.Color(204, 204, 204));
        groupEG.add(EG5);
        EG5.setText("5");
        EG5.setToolTipText("Cukup Lebih Penting");
        EG5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        EG5.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        EG5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EG5ActionPerformed(evt);
            }
        });
        ahpProcess.add(EG5, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 1520, -1, -1));

        EG6.setBackground(new java.awt.Color(204, 204, 204));
        groupEG.add(EG6);
        EG6.setText("6");
        EG6.setToolTipText("Nilai tengah diantara 2 nilai keputusan yang berdekatan");
        EG6.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        EG6.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        EG6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EG6ActionPerformed(evt);
            }
        });
        ahpProcess.add(EG6, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 1520, -1, -1));

        EG7.setBackground(new java.awt.Color(204, 204, 204));
        groupEG.add(EG7);
        EG7.setText("7");
        EG7.setToolTipText("Sangat Lebih Penting");
        EG7.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        EG7.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        EG7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EG7ActionPerformed(evt);
            }
        });
        ahpProcess.add(EG7, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 1520, -1, -1));

        EG8.setBackground(new java.awt.Color(204, 204, 204));
        groupEG.add(EG8);
        EG8.setText("8");
        EG8.setToolTipText("Nilai tengah diantara 2 nilai keputusan yang berdekatan");
        EG8.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        EG8.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        EG8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EG8ActionPerformed(evt);
            }
        });
        ahpProcess.add(EG8, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 1520, -1, -1));

        EG9.setBackground(new java.awt.Color(204, 204, 204));
        groupEG.add(EG9);
        EG9.setText("9");
        EG9.setToolTipText("Lebih Penting Ekstrim");
        EG9.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        EG9.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        EG9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EG9ActionPerformed(evt);
            }
        });
        ahpProcess.add(EG9, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 1520, -1, -1));

        jLabel75.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel75.setText("Potensi CPO");
        ahpProcess.add(jLabel75, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 1480, -1, 39));

        jLabel76.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel76.setText("Tinggi");
        ahpProcess.add(jLabel76, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 1480, -1, 39));

        jSeparator23.setBackground(new java.awt.Color(0, 0, 0));
        ahpProcess.add(jSeparator23, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 1570, 880, 10));

        jLabel79.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel79.setText("Potensi CPO");
        ahpProcess.add(jLabel79, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 1480, -1, 39));

        jSeparator24.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator24.setOrientation(javax.swing.SwingConstants.VERTICAL);
        ahpProcess.add(jSeparator24, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 1450, 10, 120));

        EH1.setBackground(new java.awt.Color(204, 204, 204));
        groupEH.add(EH1);
        EH1.setText("1");
        EH1.setToolTipText("Sama Pentingnya");
        EH1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        EH1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        EH1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EH1ActionPerformed(evt);
            }
        });
        ahpProcess.add(EH1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 1650, -1, -1));

        EH2.setBackground(new java.awt.Color(204, 204, 204));
        groupEH.add(EH2);
        EH2.setText("2");
        EH2.setToolTipText("Nilai tengah diantara 2 nilai keputusan yang berdekatan");
        EH2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        EH2.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        EH2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EH2ActionPerformed(evt);
            }
        });
        ahpProcess.add(EH2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 1650, -1, -1));

        EH3.setBackground(new java.awt.Color(204, 204, 204));
        groupEH.add(EH3);
        EH3.setText("3");
        EH3.setToolTipText("Agak Lebih Penting");
        EH3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        EH3.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        EH3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EH3ActionPerformed(evt);
            }
        });
        ahpProcess.add(EH3, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 1650, -1, -1));

        EH4.setBackground(new java.awt.Color(204, 204, 204));
        groupEH.add(EH4);
        EH4.setText("4");
        EH4.setToolTipText("Nilai tengah diantara 2 nilai keputusan yang berdekatan");
        EH4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        EH4.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        EH4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EH4ActionPerformed(evt);
            }
        });
        ahpProcess.add(EH4, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 1650, -1, -1));

        EH5.setBackground(new java.awt.Color(204, 204, 204));
        groupEH.add(EH5);
        EH5.setText("5");
        EH5.setToolTipText("Cukup Lebih Penting");
        EH5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        EH5.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        EH5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EH5ActionPerformed(evt);
            }
        });
        ahpProcess.add(EH5, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 1650, -1, -1));

        EH6.setBackground(new java.awt.Color(204, 204, 204));
        groupEH.add(EH6);
        EH6.setText("6");
        EH6.setToolTipText("Nilai tengah diantara 2 nilai keputusan yang berdekatan");
        EH6.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        EH6.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        EH6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EH6ActionPerformed(evt);
            }
        });
        ahpProcess.add(EH6, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 1650, -1, -1));

        EH7.setBackground(new java.awt.Color(204, 204, 204));
        groupEH.add(EH7);
        EH7.setText("7");
        EH7.setToolTipText("Sangat Lebih Penting");
        EH7.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        EH7.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        EH7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EH7ActionPerformed(evt);
            }
        });
        ahpProcess.add(EH7, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 1650, -1, -1));

        EH8.setBackground(new java.awt.Color(204, 204, 204));
        groupEH.add(EH8);
        EH8.setText("8");
        EH8.setToolTipText("Nilai tengah diantara 2 nilai keputusan yang berdekatan");
        EH8.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        EH8.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        EH8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EH8ActionPerformed(evt);
            }
        });
        ahpProcess.add(EH8, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 1650, -1, -1));

        EH9.setBackground(new java.awt.Color(204, 204, 204));
        groupEH.add(EH9);
        EH9.setText("9");
        EH9.setToolTipText("Lebih Penting Ekstrim");
        EH9.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        EH9.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        EH9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EH9ActionPerformed(evt);
            }
        });
        ahpProcess.add(EH9, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 1650, -1, -1));

        jLabel80.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel80.setText("Panjang Pelepah");
        ahpProcess.add(jLabel80, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 1480, -1, 39));

        jLabel81.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel81.setText("Panjang Pelepah");
        ahpProcess.add(jLabel81, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 1610, -1, 39));

        jLabel82.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel82.setText("Kerapatan Tanam");
        ahpProcess.add(jLabel82, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 1610, -1, 39));

        jLabel83.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel83.setText("Tinggi");
        ahpProcess.add(jLabel83, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 1610, -1, 39));

        jLabel86.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel86.setText("Potensi CPO");
        ahpProcess.add(jLabel86, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 1610, -1, 39));

        jSeparator25.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator25.setOrientation(javax.swing.SwingConstants.VERTICAL);
        ahpProcess.add(jSeparator25, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 1580, 10, 120));

        FG1.setBackground(new java.awt.Color(204, 204, 204));
        groupFG.add(FG1);
        FG1.setText("1");
        FG1.setToolTipText("Sama Pentingnya");
        FG1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        FG1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        FG1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FG1ActionPerformed(evt);
            }
        });
        ahpProcess.add(FG1, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 1650, -1, -1));

        FG2.setBackground(new java.awt.Color(204, 204, 204));
        groupFG.add(FG2);
        FG2.setText("2");
        FG2.setToolTipText("Nilai tengah diantara 2 nilai keputusan yang berdekatan");
        FG2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        FG2.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        FG2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FG2ActionPerformed(evt);
            }
        });
        ahpProcess.add(FG2, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 1650, -1, -1));

        FG3.setBackground(new java.awt.Color(204, 204, 204));
        groupFG.add(FG3);
        FG3.setText("3");
        FG3.setToolTipText("Agak Lebih Penting");
        FG3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        FG3.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        FG3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FG3ActionPerformed(evt);
            }
        });
        ahpProcess.add(FG3, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 1650, -1, -1));

        FG4.setBackground(new java.awt.Color(204, 204, 204));
        groupFG.add(FG4);
        FG4.setText("4");
        FG4.setToolTipText("Nilai tengah diantara 2 nilai keputusan yang berdekatan");
        FG4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        FG4.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        FG4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FG4ActionPerformed(evt);
            }
        });
        ahpProcess.add(FG4, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 1650, -1, -1));

        FG5.setBackground(new java.awt.Color(204, 204, 204));
        groupFG.add(FG5);
        FG5.setText("5");
        FG5.setToolTipText("Cukup Lebih Penting");
        FG5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        FG5.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        FG5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FG5ActionPerformed(evt);
            }
        });
        ahpProcess.add(FG5, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 1650, -1, -1));

        FG6.setBackground(new java.awt.Color(204, 204, 204));
        groupFG.add(FG6);
        FG6.setText("6");
        FG6.setToolTipText("Nilai tengah diantara 2 nilai keputusan yang berdekatan");
        FG6.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        FG6.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        FG6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FG6ActionPerformed(evt);
            }
        });
        ahpProcess.add(FG6, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 1650, -1, -1));

        FG7.setBackground(new java.awt.Color(204, 204, 204));
        groupFG.add(FG7);
        FG7.setText("7");
        FG7.setToolTipText("Sangat Lebih Penting");
        FG7.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        FG7.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        FG7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FG7ActionPerformed(evt);
            }
        });
        ahpProcess.add(FG7, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 1650, -1, -1));

        FG8.setBackground(new java.awt.Color(204, 204, 204));
        groupFG.add(FG8);
        FG8.setText("8");
        FG8.setToolTipText("Nilai tengah diantara 2 nilai keputusan yang berdekatan");
        FG8.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        FG8.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        FG8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FG8ActionPerformed(evt);
            }
        });
        ahpProcess.add(FG8, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 1650, -1, -1));

        FG9.setBackground(new java.awt.Color(204, 204, 204));
        groupFG.add(FG9);
        FG9.setText("9");
        FG9.setToolTipText("Lebih Penting Ekstrim");
        FG9.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        FG9.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        FG9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FG9ActionPerformed(evt);
            }
        });
        ahpProcess.add(FG9, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 1650, -1, -1));

        jSeparator26.setBackground(new java.awt.Color(0, 0, 0));
        ahpProcess.add(jSeparator26, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 1700, 880, 10));

        FH1.setBackground(new java.awt.Color(204, 204, 204));
        groupFH.add(FH1);
        FH1.setText("1");
        FH1.setToolTipText("Sama Pentingnya");
        FH1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        FH1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        FH1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FH1ActionPerformed(evt);
            }
        });
        ahpProcess.add(FH1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 1780, -1, -1));

        FH2.setBackground(new java.awt.Color(204, 204, 204));
        groupFH.add(FH2);
        FH2.setText("2");
        FH2.setToolTipText("Nilai tengah diantara 2 nilai keputusan yang berdekatan");
        FH2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        FH2.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        FH2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FH2ActionPerformed(evt);
            }
        });
        ahpProcess.add(FH2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 1780, -1, -1));

        FH3.setBackground(new java.awt.Color(204, 204, 204));
        groupFH.add(FH3);
        FH3.setText("3");
        FH3.setToolTipText("Agak Lebih Penting");
        FH3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        FH3.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        FH3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FH3ActionPerformed(evt);
            }
        });
        ahpProcess.add(FH3, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 1780, -1, -1));

        FH4.setBackground(new java.awt.Color(204, 204, 204));
        groupFH.add(FH4);
        FH4.setText("4");
        FH4.setToolTipText("Nilai tengah diantara 2 nilai keputusan yang berdekatan");
        FH4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        FH4.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        FH4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FH4ActionPerformed(evt);
            }
        });
        ahpProcess.add(FH4, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 1780, -1, -1));

        FH5.setBackground(new java.awt.Color(204, 204, 204));
        groupFH.add(FH5);
        FH5.setText("5");
        FH5.setToolTipText("Cukup Lebih Penting");
        FH5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        FH5.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        FH5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FH5ActionPerformed(evt);
            }
        });
        ahpProcess.add(FH5, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 1780, -1, -1));

        FH6.setBackground(new java.awt.Color(204, 204, 204));
        groupFH.add(FH6);
        FH6.setText("6");
        FH6.setToolTipText("Nilai tengah diantara 2 nilai keputusan yang berdekatan");
        FH6.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        FH6.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        FH6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FH6ActionPerformed(evt);
            }
        });
        ahpProcess.add(FH6, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 1780, -1, -1));

        FH7.setBackground(new java.awt.Color(204, 204, 204));
        groupFH.add(FH7);
        FH7.setText("7");
        FH7.setToolTipText("Sangat Lebih Penting");
        FH7.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        FH7.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        FH7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FH7ActionPerformed(evt);
            }
        });
        ahpProcess.add(FH7, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 1780, -1, -1));

        FH8.setBackground(new java.awt.Color(204, 204, 204));
        groupFH.add(FH8);
        FH8.setText("8");
        FH8.setToolTipText("Nilai tengah diantara 2 nilai keputusan yang berdekatan");
        FH8.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        FH8.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        FH8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FH8ActionPerformed(evt);
            }
        });
        ahpProcess.add(FH8, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 1780, -1, -1));

        FH9.setBackground(new java.awt.Color(204, 204, 204));
        groupFH.add(FH9);
        FH9.setText("9");
        FH9.setToolTipText("Lebih Penting Ekstrim");
        FH9.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        FH9.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        FH9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FH9ActionPerformed(evt);
            }
        });
        ahpProcess.add(FH9, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 1780, -1, -1));

        GH1.setBackground(new java.awt.Color(204, 204, 204));
        groupGH.add(GH1);
        GH1.setText("1");
        GH1.setToolTipText("Sama Pentingnya");
        GH1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        GH1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        GH1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GH1ActionPerformed(evt);
            }
        });
        ahpProcess.add(GH1, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 1780, -1, -1));

        GH2.setBackground(new java.awt.Color(204, 204, 204));
        groupGH.add(GH2);
        GH2.setText("2");
        GH2.setToolTipText("Nilai tengah diantara 2 nilai keputusan yang berdekatan");
        GH2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        GH2.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        GH2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GH2ActionPerformed(evt);
            }
        });
        ahpProcess.add(GH2, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 1780, -1, -1));

        GH3.setBackground(new java.awt.Color(204, 204, 204));
        groupGH.add(GH3);
        GH3.setText("3");
        GH3.setToolTipText("Agak Lebih Penting");
        GH3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        GH3.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        GH3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GH3ActionPerformed(evt);
            }
        });
        ahpProcess.add(GH3, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 1780, -1, -1));

        GH4.setBackground(new java.awt.Color(204, 204, 204));
        groupGH.add(GH4);
        GH4.setText("4");
        GH4.setToolTipText("Nilai tengah diantara 2 nilai keputusan yang berdekatan");
        GH4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        GH4.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        GH4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GH4ActionPerformed(evt);
            }
        });
        ahpProcess.add(GH4, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 1780, -1, -1));

        GH5.setBackground(new java.awt.Color(204, 204, 204));
        groupGH.add(GH5);
        GH5.setText("5");
        GH5.setToolTipText("Cukup Lebih Penting");
        GH5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        GH5.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        GH5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GH5ActionPerformed(evt);
            }
        });
        ahpProcess.add(GH5, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 1780, -1, -1));

        GH6.setBackground(new java.awt.Color(204, 204, 204));
        groupGH.add(GH6);
        GH6.setText("6");
        GH6.setToolTipText("Nilai tengah diantara 2 nilai keputusan yang berdekatan");
        GH6.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        GH6.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        GH6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GH6ActionPerformed(evt);
            }
        });
        ahpProcess.add(GH6, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 1780, -1, -1));

        GH7.setBackground(new java.awt.Color(204, 204, 204));
        groupGH.add(GH7);
        GH7.setText("7");
        GH7.setToolTipText("Sangat Lebih Penting");
        GH7.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        GH7.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        GH7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GH7ActionPerformed(evt);
            }
        });
        ahpProcess.add(GH7, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 1780, -1, -1));

        GH8.setBackground(new java.awt.Color(204, 204, 204));
        groupGH.add(GH8);
        GH8.setText("8");
        GH8.setToolTipText("Nilai tengah diantara 2 nilai keputusan yang berdekatan");
        GH8.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        GH8.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        GH8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GH8ActionPerformed(evt);
            }
        });
        ahpProcess.add(GH8, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 1780, -1, -1));

        GH9.setBackground(new java.awt.Color(204, 204, 204));
        groupGH.add(GH9);
        GH9.setText("9");
        GH9.setToolTipText("Lebih Penting Ekstrim");
        GH9.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        GH9.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        GH9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GH9ActionPerformed(evt);
            }
        });
        ahpProcess.add(GH9, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 1780, -1, -1));

        jLabel88.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel88.setText("Panjang Pelepah");
        ahpProcess.add(jLabel88, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 1740, -1, 39));

        jLabel89.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel89.setText("Tinggi");
        ahpProcess.add(jLabel89, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 1740, -1, 39));

        jSeparator27.setBackground(new java.awt.Color(0, 0, 0));
        ahpProcess.add(jSeparator27, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 1830, 880, 10));

        jLabel90.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel90.setText("Kerapatan Tanam");
        ahpProcess.add(jLabel90, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 1740, -1, 39));

        jLabel91.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel91.setText("Kerapatan Tanam");
        ahpProcess.add(jLabel91, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 1740, -1, 39));

        jSeparator28.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator28.setOrientation(javax.swing.SwingConstants.VERTICAL);
        ahpProcess.add(jSeparator28, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 1710, 10, 120));

        ACa.setBackground(new java.awt.Color(204, 204, 204));
        ac.add(ACa);
        ACa.setForeground(new java.awt.Color(204, 204, 204));
        ACa.setText("1");
        ACa.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        ACa.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ACa.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        ahpProcess.add(ACa, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 30, -1, -1));

        ACc.setBackground(new java.awt.Color(204, 204, 204));
        ac.add(ACc);
        ACc.setForeground(new java.awt.Color(204, 204, 204));
        ACc.setText("0");
        ACc.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        ACc.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ACc.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        ahpProcess.add(ACc, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 30, -1, -1));

        ADa.setBackground(new java.awt.Color(204, 204, 204));
        ad.add(ADa);
        ADa.setForeground(new java.awt.Color(204, 204, 204));
        ADa.setText("1");
        ADa.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        ADa.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ADa.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        ahpProcess.add(ADa, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 160, -1, -1));

        ADd.setBackground(new java.awt.Color(204, 204, 204));
        ad.add(ADd);
        ADd.setForeground(new java.awt.Color(204, 204, 204));
        ADd.setText("0");
        ADd.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        ADd.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ADd.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        ahpProcess.add(ADd, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 160, -1, -1));

        AEa.setBackground(new java.awt.Color(204, 204, 204));
        ae.add(AEa);
        AEa.setForeground(new java.awt.Color(204, 204, 204));
        AEa.setText("1");
        AEa.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        AEa.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        AEa.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        ahpProcess.add(AEa, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 160, -1, -1));

        AEe.setBackground(new java.awt.Color(204, 204, 204));
        ae.add(AEe);
        AEe.setForeground(new java.awt.Color(204, 204, 204));
        AEe.setText("0");
        AEe.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        AEe.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        AEe.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        ahpProcess.add(AEe, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 160, -1, -1));

        AFa.setBackground(new java.awt.Color(204, 204, 204));
        af.add(AFa);
        AFa.setForeground(new java.awt.Color(204, 204, 204));
        AFa.setText("1");
        AFa.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        AFa.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        AFa.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        ahpProcess.add(AFa, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 290, -1, -1));

        AFf.setBackground(new java.awt.Color(204, 204, 204));
        af.add(AFf);
        AFf.setForeground(new java.awt.Color(204, 204, 204));
        AFf.setText("0");
        AFf.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        AFf.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        AFf.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        ahpProcess.add(AFf, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 290, -1, -1));

        AGa.setBackground(new java.awt.Color(204, 204, 204));
        ag.add(AGa);
        AGa.setForeground(new java.awt.Color(204, 204, 204));
        AGa.setText("1");
        AGa.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        AGa.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        AGa.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        ahpProcess.add(AGa, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 290, -1, -1));

        AGg.setBackground(new java.awt.Color(204, 204, 204));
        ag.add(AGg);
        AGg.setForeground(new java.awt.Color(204, 204, 204));
        AGg.setText("0");
        AGg.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        AGg.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        AGg.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        ahpProcess.add(AGg, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 290, -1, -1));

        AHa.setBackground(new java.awt.Color(204, 204, 204));
        ah.add(AHa);
        AHa.setForeground(new java.awt.Color(204, 204, 204));
        AHa.setText("1");
        AHa.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        AHa.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        AHa.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        ahpProcess.add(AHa, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 420, -1, -1));

        AHh.setBackground(new java.awt.Color(204, 204, 204));
        ah.add(AHh);
        AHh.setForeground(new java.awt.Color(204, 204, 204));
        AHh.setText("0");
        AHh.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        AHh.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        AHh.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        ahpProcess.add(AHh, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 420, -1, -1));

        BCb.setBackground(new java.awt.Color(204, 204, 204));
        bc.add(BCb);
        BCb.setForeground(new java.awt.Color(204, 204, 204));
        BCb.setText("1");
        BCb.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        BCb.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        BCb.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        ahpProcess.add(BCb, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 420, -1, -1));

        BCc.setBackground(new java.awt.Color(204, 204, 204));
        bc.add(BCc);
        BCc.setForeground(new java.awt.Color(204, 204, 204));
        BCc.setText("0");
        BCc.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        BCc.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        BCc.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        ahpProcess.add(BCc, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 420, -1, -1));

        BDb.setBackground(new java.awt.Color(204, 204, 204));
        bd.add(BDb);
        BDb.setForeground(new java.awt.Color(204, 204, 204));
        BDb.setText("1");
        BDb.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        BDb.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        BDb.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        ahpProcess.add(BDb, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 550, -1, -1));

        BDd.setBackground(new java.awt.Color(204, 204, 204));
        bd.add(BDd);
        BDd.setForeground(new java.awt.Color(204, 204, 204));
        BDd.setText("0");
        BDd.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        BDd.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        BDd.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        ahpProcess.add(BDd, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 550, -1, -1));

        BEb.setBackground(new java.awt.Color(204, 204, 204));
        be.add(BEb);
        BEb.setForeground(new java.awt.Color(204, 204, 204));
        BEb.setText("1");
        BEb.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        BEb.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        BEb.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        ahpProcess.add(BEb, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 550, -1, -1));

        BEe.setBackground(new java.awt.Color(204, 204, 204));
        be.add(BEe);
        BEe.setForeground(new java.awt.Color(204, 204, 204));
        BEe.setText("0");
        BEe.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        BEe.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        BEe.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        ahpProcess.add(BEe, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 550, -1, -1));

        BFb.setBackground(new java.awt.Color(204, 204, 204));
        bf.add(BFb);
        BFb.setForeground(new java.awt.Color(204, 204, 204));
        BFb.setText("1");
        BFb.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        BFb.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        BFb.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        ahpProcess.add(BFb, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 680, -1, -1));

        BFf.setBackground(new java.awt.Color(204, 204, 204));
        bg.add(BFf);
        BFf.setForeground(new java.awt.Color(204, 204, 204));
        BFf.setText("1");
        BFf.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        BFf.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        BFf.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        ahpProcess.add(BFf, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 680, -1, -1));

        BGb.setBackground(new java.awt.Color(204, 204, 204));
        bf.add(BGb);
        BGb.setForeground(new java.awt.Color(204, 204, 204));
        BGb.setText("0");
        BGb.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        BGb.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        BGb.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        ahpProcess.add(BGb, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 680, -1, -1));

        BGg.setBackground(new java.awt.Color(204, 204, 204));
        bg.add(BGg);
        BGg.setForeground(new java.awt.Color(204, 204, 204));
        BGg.setText("0");
        BGg.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        BGg.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        BGg.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        ahpProcess.add(BGg, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 680, -1, -1));

        BHb.setBackground(new java.awt.Color(204, 204, 204));
        cd.add(BHb);
        BHb.setForeground(new java.awt.Color(204, 204, 204));
        BHb.setText("1");
        BHb.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        BHb.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        BHb.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        ahpProcess.add(BHb, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 810, -1, -1));

        BHh.setBackground(new java.awt.Color(204, 204, 204));
        bh.add(BHh);
        BHh.setForeground(new java.awt.Color(204, 204, 204));
        BHh.setText("0");
        BHh.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        BHh.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        BHh.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        ahpProcess.add(BHh, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 810, -1, -1));

        CDc.setBackground(new java.awt.Color(204, 204, 204));
        bh.add(CDc);
        CDc.setForeground(new java.awt.Color(204, 204, 204));
        CDc.setText("1");
        CDc.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        CDc.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        CDc.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        ahpProcess.add(CDc, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 810, -1, -1));

        CDd.setBackground(new java.awt.Color(204, 204, 204));
        cd.add(CDd);
        CDd.setForeground(new java.awt.Color(204, 204, 204));
        CDd.setText("0");
        CDd.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        CDd.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        CDd.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        ahpProcess.add(CDd, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 810, -1, -1));

        ABb14.setBackground(new java.awt.Color(204, 204, 204));
        cf.add(ABb14);
        ABb14.setForeground(new java.awt.Color(204, 204, 204));
        ABb14.setText("1");
        ABb14.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        ABb14.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ABb14.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        ahpProcess.add(ABb14, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 940, -1, -1));

        ABa14.setBackground(new java.awt.Color(204, 204, 204));
        ce.add(ABa14);
        ABa14.setForeground(new java.awt.Color(204, 204, 204));
        ABa14.setText("0");
        ABa14.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        ABa14.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ABa14.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        ahpProcess.add(ABa14, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 940, -1, -1));

        ABb15.setBackground(new java.awt.Color(204, 204, 204));
        ce.add(ABb15);
        ABb15.setForeground(new java.awt.Color(204, 204, 204));
        ABb15.setText("1");
        ABb15.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        ABb15.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ABb15.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        ahpProcess.add(ABb15, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 940, -1, -1));

        ABa15.setBackground(new java.awt.Color(204, 204, 204));
        cf.add(ABa15);
        ABa15.setForeground(new java.awt.Color(204, 204, 204));
        ABa15.setText("0");
        ABa15.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        ABa15.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ABa15.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        ahpProcess.add(ABa15, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 940, -1, -1));

        ABb16.setBackground(new java.awt.Color(204, 204, 204));
        ch.add(ABb16);
        ABb16.setForeground(new java.awt.Color(204, 204, 204));
        ABb16.setText("1");
        ABb16.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        ABb16.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ABb16.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        ahpProcess.add(ABb16, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 1070, -1, -1));

        ABa16.setBackground(new java.awt.Color(204, 204, 204));
        cg.add(ABa16);
        ABa16.setForeground(new java.awt.Color(204, 204, 204));
        ABa16.setText("0");
        ABa16.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        ABa16.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ABa16.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        ahpProcess.add(ABa16, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 1070, -1, -1));

        ABb17.setBackground(new java.awt.Color(204, 204, 204));
        cg.add(ABb17);
        ABb17.setForeground(new java.awt.Color(204, 204, 204));
        ABb17.setText("1");
        ABb17.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        ABb17.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ABb17.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        ahpProcess.add(ABb17, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 1070, -1, -1));

        ABa17.setBackground(new java.awt.Color(204, 204, 204));
        ch.add(ABa17);
        ABa17.setForeground(new java.awt.Color(204, 204, 204));
        ABa17.setText("0");
        ABa17.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        ABa17.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ABa17.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        ahpProcess.add(ABa17, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 1070, -1, -1));

        ABb18.setBackground(new java.awt.Color(204, 204, 204));
        df.add(ABb18);
        ABb18.setForeground(new java.awt.Color(204, 204, 204));
        ABb18.setText("1");
        ABb18.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        ABb18.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ABb18.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        ahpProcess.add(ABb18, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 1200, -1, -1));

        ABa18.setBackground(new java.awt.Color(204, 204, 204));
        de.add(ABa18);
        ABa18.setForeground(new java.awt.Color(204, 204, 204));
        ABa18.setText("0");
        ABa18.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        ABa18.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ABa18.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        ahpProcess.add(ABa18, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 1200, -1, -1));

        ABb19.setBackground(new java.awt.Color(204, 204, 204));
        de.add(ABb19);
        ABb19.setForeground(new java.awt.Color(204, 204, 204));
        ABb19.setText("1");
        ABb19.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        ABb19.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ABb19.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        ahpProcess.add(ABb19, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 1200, -1, -1));

        ABa19.setBackground(new java.awt.Color(204, 204, 204));
        df.add(ABa19);
        ABa19.setForeground(new java.awt.Color(204, 204, 204));
        ABa19.setText("0");
        ABa19.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        ABa19.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ABa19.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        ahpProcess.add(ABa19, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 1200, -1, -1));

        ABb20.setBackground(new java.awt.Color(204, 204, 204));
        dh.add(ABb20);
        ABb20.setForeground(new java.awt.Color(204, 204, 204));
        ABb20.setText("1");
        ABb20.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        ABb20.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ABb20.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        ahpProcess.add(ABb20, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 1330, -1, -1));

        ABa20.setBackground(new java.awt.Color(204, 204, 204));
        dg.add(ABa20);
        ABa20.setForeground(new java.awt.Color(204, 204, 204));
        ABa20.setText("0");
        ABa20.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        ABa20.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ABa20.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        ahpProcess.add(ABa20, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 1330, -1, -1));

        ABb21.setBackground(new java.awt.Color(204, 204, 204));
        dg.add(ABb21);
        ABb21.setForeground(new java.awt.Color(204, 204, 204));
        ABb21.setText("1");
        ABb21.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        ABb21.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ABb21.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        ahpProcess.add(ABb21, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 1330, -1, -1));

        ABa21.setBackground(new java.awt.Color(204, 204, 204));
        dh.add(ABa21);
        ABa21.setForeground(new java.awt.Color(204, 204, 204));
        ABa21.setText("0");
        ABa21.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        ABa21.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ABa21.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        ahpProcess.add(ABa21, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 1330, -1, -1));

        ABb22.setBackground(new java.awt.Color(204, 204, 204));
        eg.add(ABb22);
        ABb22.setForeground(new java.awt.Color(204, 204, 204));
        ABb22.setText("1");
        ABb22.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        ABb22.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ABb22.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        ahpProcess.add(ABb22, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 1460, -1, -1));

        ABa22.setBackground(new java.awt.Color(204, 204, 204));
        ef.add(ABa22);
        ABa22.setForeground(new java.awt.Color(204, 204, 204));
        ABa22.setText("0");
        ABa22.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        ABa22.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ABa22.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        ahpProcess.add(ABa22, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 1460, -1, -1));

        ABb23.setBackground(new java.awt.Color(204, 204, 204));
        ef.add(ABb23);
        ABb23.setForeground(new java.awt.Color(204, 204, 204));
        ABb23.setText("1");
        ABb23.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        ABb23.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ABb23.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        ahpProcess.add(ABb23, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 1460, -1, -1));

        ABa23.setBackground(new java.awt.Color(204, 204, 204));
        eg.add(ABa23);
        ABa23.setForeground(new java.awt.Color(204, 204, 204));
        ABa23.setText("0");
        ABa23.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        ABa23.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ABa23.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        ahpProcess.add(ABa23, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 1460, -1, -1));

        ABb24.setBackground(new java.awt.Color(204, 204, 204));
        fg.add(ABb24);
        ABb24.setForeground(new java.awt.Color(204, 204, 204));
        ABb24.setText("1");
        ABb24.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        ABb24.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ABb24.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        ahpProcess.add(ABb24, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 1590, -1, -1));

        ABa24.setBackground(new java.awt.Color(204, 204, 204));
        eh.add(ABa24);
        ABa24.setForeground(new java.awt.Color(204, 204, 204));
        ABa24.setText("0");
        ABa24.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        ABa24.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ABa24.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        ahpProcess.add(ABa24, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 1590, -1, -1));

        ABb25.setBackground(new java.awt.Color(204, 204, 204));
        eh.add(ABb25);
        ABb25.setForeground(new java.awt.Color(204, 204, 204));
        ABb25.setText("1");
        ABb25.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        ABb25.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ABb25.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        ahpProcess.add(ABb25, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 1590, -1, -1));

        ABa25.setBackground(new java.awt.Color(204, 204, 204));
        fg.add(ABa25);
        ABa25.setForeground(new java.awt.Color(204, 204, 204));
        ABa25.setText("0");
        ABa25.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        ABa25.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ABa25.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        ahpProcess.add(ABa25, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 1590, -1, -1));

        ABb26.setBackground(new java.awt.Color(204, 204, 204));
        gh.add(ABb26);
        ABb26.setForeground(new java.awt.Color(204, 204, 204));
        ABb26.setText("1");
        ABb26.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        ABb26.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ABb26.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        ahpProcess.add(ABb26, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 1720, -1, -1));

        ABa26.setBackground(new java.awt.Color(204, 204, 204));
        fh.add(ABa26);
        ABa26.setForeground(new java.awt.Color(204, 204, 204));
        ABa26.setText("0");
        ABa26.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        ABa26.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ABa26.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        ahpProcess.add(ABa26, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 1720, -1, -1));

        ABb27.setBackground(new java.awt.Color(204, 204, 204));
        fh.add(ABb27);
        ABb27.setForeground(new java.awt.Color(204, 204, 204));
        ABb27.setText("1");
        ABb27.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        ABb27.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ABb27.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        ahpProcess.add(ABb27, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 1720, -1, -1));

        ABa27.setBackground(new java.awt.Color(204, 204, 204));
        gh.add(ABa27);
        ABa27.setForeground(new java.awt.Color(204, 204, 204));
        ABa27.setText("0");
        ABa27.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        ABa27.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ABa27.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        ahpProcess.add(ABa27, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 1720, -1, -1));

        jScrollPane4.setViewportView(ahpProcess);

        jLabel108.setText("Deskripsi");

        helpButton.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        helpButton.setText("?");
        helpButton.setToolTipText("Bantuan Nilai SKala");
        helpButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                helpButtonActionPerformed(evt);
            }
        });

        simpan.setBackground(new java.awt.Color(51, 255, 0));
        simpan.setText("Simpan");
        simpan.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.black, java.awt.Color.green, null, null));
        simpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                simpanActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panel_kriteriaLayout = new javax.swing.GroupLayout(panel_kriteria);
        panel_kriteria.setLayout(panel_kriteriaLayout);
        panel_kriteriaLayout.setHorizontalGroup(
            panel_kriteriaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 900, Short.MAX_VALUE)
            .addGroup(panel_kriteriaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel_kriteriaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel87)
                    .addComponent(jLabel108))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_kriteriaLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(panel_kriteriaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(helpButton, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_kriteriaLayout.createSequentialGroup()
                        .addComponent(simpan, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        panel_kriteriaLayout.setVerticalGroup(
            panel_kriteriaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_kriteriaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel87)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel108)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 45, Short.MAX_VALUE)
                .addComponent(helpButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 391, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(simpan, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25))
        );

        ContainerPanel.add(panel_kriteria, "card2");

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
        int response = JOptionPane.showConfirmDialog(null,
                "Anda yakin ingin menyimpan Pengguna ?", "Simpan Pengguna",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (response == JOptionPane.NO_OPTION) {
            JOptionPane.showMessageDialog(null, "Batal Menyimpan Pengguna");
        } else if (response == JOptionPane.YES_OPTION) {
            if (User.tambah(username, password, nama, asal, role)) {
                JOptionPane.showMessageDialog(null,
                        "Berhasil Menyimpan Pengguna");
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
        rolePengguna.getModel().setSelectedItem(
                TablePengguna.getValueAt(row, 4).toString());
    }//GEN-LAST:event_TablePenggunaMouseClicked

    private void UpdatePenggunaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UpdatePenggunaActionPerformed
        String nama = namaPengguna.getText();
        String asal = asalDaerahPengguna.getText();
        String username = usernamePengguna.getText();
        String password = passwordPengguna.getText();
        String role = rolePengguna.getSelectedItem().toString();

        int row = TablePengguna.getSelectedRow();
        int id = Integer.parseInt(TablePengguna.getModel().getValueAt(row, 0).
                toString());

        int response = JOptionPane.showConfirmDialog(null,
                "Anda yakin ingin mengubah Pengguna ?", "Ubah Pengguna",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (response == JOptionPane.NO_OPTION) {
            JOptionPane.showMessageDialog(null, "Batal Mengubah Pengguna");
        } else if (response == JOptionPane.YES_OPTION) {
            if (User.ubah(id, username, nama, asal, role)) {
                JOptionPane.
                        showMessageDialog(null, "Berhasil Mengubah Pengguna");
                InitTablePengguna();
                TampilDataPengguna();
            } else {
                JOptionPane.showMessageDialog(null, "Gagal");
            }
        }
    }//GEN-LAST:event_UpdatePenggunaActionPerformed

    private void HapusPenggunaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_HapusPenggunaActionPerformed
        int row = TablePengguna.getSelectedRow();
        int id = Integer.parseInt(TablePengguna.getModel().getValueAt(row, 0).
                toString());

        int response = JOptionPane.showConfirmDialog(null,
                "Anda yakin ingin menghapus Pengguna ?", "hapus Pengguna",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (response == JOptionPane.NO_OPTION) {
            JOptionPane.showMessageDialog(null, "Batal Menghapus Pengguna");
        } else if (response == JOptionPane.YES_OPTION) {
            if (User.hapus(id)) {
                JOptionPane.showMessageDialog(null,
                        "Berhasil Menghapus Pengguna");
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

        int response = JOptionPane.showConfirmDialog(null,
                "Anda yakin ingin menyimpan Varietas ?", "Simpan Varietas",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (response == JOptionPane.NO_OPTION) {
            JOptionPane.showMessageDialog(null, "Batal Menyimpan Varietas");
        } else if (response == JOptionPane.YES_OPTION) {
            if (varietas.tambah(nama, grup, path) && varietas.
                    tambahNilaiKriteriaVarietas(nilai)) {
                JOptionPane.showMessageDialog(null,
                        "Berhasil Menyimpan Varietas");
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
        int id = Integer.parseInt(TableVarietas.getModel().getValueAt(row, 0).
                toString());

        float[] nilai = new float[8];
        nilai[0] = (float) RJT.getValue();
        nilai[1] = (float) RBT.getValue();
        nilai[2] = (float) TBS.getValue();
        nilai[3] = (float) Rendemen.getValue();
        nilai[4] = (float) CPO.getValue();
        nilai[5] = (float) Tinggi.getValue();
        nilai[6] = (float) PP.getValue();
        nilai[7] = (float) KT.getValue();

        int response = JOptionPane.showConfirmDialog(null,
                "Anda yakin ingin mengubah Varietas?", "Ubah Varietas",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (response == JOptionPane.NO_OPTION) {
            JOptionPane.showMessageDialog(null, "Batal Mengubah Varietas");
        } else if (response == JOptionPane.YES_OPTION) {
            if (varietas.ubah(id, nama, grup, path) && varietas.
                    ubahNilaiKriteriaVarietas(id, nilai)) {
                JOptionPane.
                        showMessageDialog(null, "Berhasil Mengubah Varietas");
                InitTableVarietas();
                TampilDataVarietas();
            } else {
                JOptionPane.showMessageDialog(null, "Gagal");
            }
        }
    }//GEN-LAST:event_UpdateVarietasActionPerformed

    private void HapusVarietasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_HapusVarietasActionPerformed
        int row = TableVarietas.getSelectedRow();
        int id = Integer.parseInt(TableVarietas.getModel().getValueAt(row, 0).
                toString());

        int response = JOptionPane.showConfirmDialog(null,
                "Anda yakin ingin menghapus Varietas ?", "hapus Varietas",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (response == JOptionPane.NO_OPTION) {
            JOptionPane.showMessageDialog(null, "Batal Menghapus Varietas");
        } else if (response == JOptionPane.YES_OPTION) {
            if (varietas.hapus(id)) {
                JOptionPane.showMessageDialog(null,
                        "Berhasil Menghapus Varietas");
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

        int id = Integer.parseInt(TableVarietas.getModel().getValueAt(row, 0).
                toString());
        fotoField.setIcon(varietas.getImage(id, fotoField.getWidth(), fotoField.
                getHeight()));


    }//GEN-LAST:event_TableVarietasMouseClicked

    private void panel_varietasComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_panel_varietasComponentShown
        InitTableVarietas();
        TampilDataVarietas();

    }//GEN-LAST:event_panel_varietasComponentShown

    private void chooseFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chooseFileActionPerformed
        JFileChooser jFile = new JFileChooser();
        jFile.setCurrentDirectory(new File(System.getProperty("user.home")));

        FileNameExtensionFilter filter = new FileNameExtensionFilter("*.image",
                "jpg", "png", "jpeg");
        jFile.addChoosableFileFilter(filter);

        int result = jFile.showSaveDialog(null);

        File selectedFile = jFile.getSelectedFile();
        String filename = selectedFile.getName();

        if (filename.endsWith(".jpg") || filename.endsWith(".JPG") || filename.
                endsWith(".png") || filename.endsWith(".PNG") || filename.
                endsWith(".jpeg") || filename.endsWith(".JPEG")) {
            if (result == JFileChooser.APPROVE_OPTION) {
                path = selectedFile.getAbsolutePath();
                ImageIcon myImage = new ImageIcon(path);

                Image img = myImage.getImage();
                Image newImage = img.getScaledInstance(fotoField.getWidth(),
                        fotoField.getHeight(), Image.SCALE_SMOOTH);

                ImageIcon image = new ImageIcon(newImage);
                fotoField.setIcon(image);
            }
        } else {
            JOptionPane.showMessageDialog(rootPane,
                    "Silahkan pilih file bertipe gambar", "COba Lagi", 1);
        }
    }//GEN-LAST:event_chooseFileActionPerformed

    public void radioPreferensi(ArrayList<Double> bobot) {
        int rounds;
        if (bobot.get(0).doubleValue() >= 1) {
            setPreferensiGroup(bobot.get(0).intValue(), ab.getElements());
            setButtonGroup(bobot.get(0).intValue(), groupAB.getElements());
        } else {
            rounds = (int) Math.ceil(1 / bobot.get(0).doubleValue());
            setPreferensiGroup(bobot.get(0).intValue(), ab.getElements());
            setButtonGroup(rounds, groupAB.getElements());
        }

        if (bobot.get(1).doubleValue() >= 1) {
            setPreferensiGroup(bobot.get(1).intValue(), ac.getElements());
            setButtonGroup(bobot.get(1).intValue(), groupAC.getElements());
        } else {
            rounds = (int) Math.ceil(1 / bobot.get(1).doubleValue());
            setPreferensiGroup(bobot.get(1).intValue(), ac.getElements());
            setButtonGroup(rounds, groupAC.getElements());
        }

        if (bobot.get(2).doubleValue() >= 1) {
            setPreferensiGroup(bobot.get(2).intValue(), ad.getElements());
            setButtonGroup(bobot.get(2).intValue(), groupAD.getElements());
        } else {
            rounds = (int) Math.ceil(1 / bobot.get(2).doubleValue());
            setPreferensiGroup(bobot.get(2).intValue(), ad.getElements());
            setButtonGroup(rounds, groupAD.getElements());
        }

        if (bobot.get(3).doubleValue() >= 1) {
            setPreferensiGroup(bobot.get(3).intValue(), ae.getElements());
            setButtonGroup(bobot.get(3).intValue(), groupAE.getElements());
        } else {
            rounds = (int) Math.ceil(1 / bobot.get(3).doubleValue());
            setPreferensiGroup(bobot.get(3).intValue(), ae.getElements());
            setButtonGroup(rounds, groupAE.getElements());
        }

        if (bobot.get(4).doubleValue() >= 1) {
            setPreferensiGroup(bobot.get(4).intValue(), af.getElements());
            setButtonGroup(bobot.get(4).intValue(), groupAF.getElements());
        } else {
            rounds = (int) Math.ceil(1 / bobot.get(4).doubleValue());
            setPreferensiGroup(bobot.get(4).intValue(), af.getElements());
            setButtonGroup(rounds, groupAF.getElements());
        }

        if (bobot.get(5).doubleValue() >= 1) {
            setPreferensiGroup(bobot.get(5).intValue(), ag.getElements());
            setButtonGroup(bobot.get(5).intValue(), groupAG.getElements());
        } else {
            rounds = (int) Math.ceil(1 / bobot.get(5).doubleValue());
            setPreferensiGroup(bobot.get(5).intValue(), ag.getElements());
            setButtonGroup(rounds, groupAG.getElements());
        }

        if (bobot.get(6).doubleValue() >= 1) {
            setPreferensiGroup(bobot.get(6).intValue(), ah.getElements());
            setButtonGroup(bobot.get(6).intValue(), groupAH.getElements());
        } else {
            rounds = (int) Math.ceil(1 / bobot.get(6).doubleValue());
            setPreferensiGroup(bobot.get(6).intValue(), ah.getElements());
            setButtonGroup(rounds, groupAH.getElements());
        }

        if (bobot.get(7).doubleValue() >= 1) {
            setPreferensiGroup(bobot.get(7).intValue(), bc.getElements());
            setButtonGroup(bobot.get(7).intValue(), groupBC.getElements());
        } else {
            rounds = (int) Math.ceil(1 / bobot.get(7).doubleValue());
            setPreferensiGroup(bobot.get(7).intValue(), bc.getElements());
            setButtonGroup(rounds, groupBC.getElements());
        }

        if (bobot.get(8).doubleValue() >= 1) {
            setPreferensiGroup(bobot.get(8).intValue(), bd.getElements());
            setButtonGroup(bobot.get(8).intValue(), groupBD.getElements());
        } else {
            rounds = (int) Math.ceil(1 / bobot.get(8).doubleValue());
            setPreferensiGroup(bobot.get(8).intValue(), bd.getElements());
            setButtonGroup(rounds, groupBD.getElements());
        }

        if (bobot.get(9).doubleValue() >= 1) {
            setPreferensiGroup(bobot.get(9).intValue(), be.getElements());
            setButtonGroup(bobot.get(9).intValue(), groupBE.getElements());
        } else {
            rounds = (int) Math.ceil(1 / bobot.get(9).doubleValue());
            setPreferensiGroup(bobot.get(9).intValue(), be.getElements());
            setButtonGroup(rounds, groupBE.getElements());
        }

        if (bobot.get(10).doubleValue() >= 1) {
            setPreferensiGroup(bobot.get(10).intValue(), bf.getElements());
            setButtonGroup(bobot.get(10).intValue(), groupBF.getElements());
        } else {
            rounds = (int) Math.ceil(1 / bobot.get(10).doubleValue());
            setPreferensiGroup(bobot.get(10).intValue(), bf.getElements());
            setButtonGroup(rounds, groupBF.getElements());
        }

        if (bobot.get(11).doubleValue() >= 1) {
            setPreferensiGroup(bobot.get(11).intValue(), bg.getElements());
            setButtonGroup(bobot.get(11).intValue(), groupBG.getElements());
        } else {
            rounds = (int) Math.ceil(1 / bobot.get(11).doubleValue());
            setPreferensiGroup(bobot.get(11).intValue(), bg.getElements());
            setButtonGroup(rounds, groupBG.getElements());
        }

        if (bobot.get(12).doubleValue() >= 1) {
            setPreferensiGroup(bobot.get(12).intValue(), bh.getElements());
            setButtonGroup(bobot.get(12).intValue(), groupBH.getElements());
        } else {
            rounds = (int) Math.ceil(1 / bobot.get(12).doubleValue());
            setPreferensiGroup(bobot.get(12).intValue(), bh.getElements());
            setButtonGroup(rounds, groupBH.getElements());
        }

        if (bobot.get(13).doubleValue() >= 1) {
            setPreferensiGroup(bobot.get(13).intValue(), cd.getElements());
            setButtonGroup(bobot.get(13).intValue(), groupCD.getElements());
        } else {
            rounds = (int) Math.ceil(1 / bobot.get(13).doubleValue());
            setPreferensiGroup(bobot.get(13).intValue(), cd.getElements());
            setButtonGroup(rounds, groupCD.getElements());
        }

        if (bobot.get(14).doubleValue() >= 1) {
            setPreferensiGroup(bobot.get(14).intValue(), ce.getElements());
            setButtonGroup(bobot.get(14).intValue(), groupCE.getElements());
        } else {
            rounds = (int) Math.ceil(1 / bobot.get(14).doubleValue());
            setPreferensiGroup(bobot.get(14).intValue(), ce.getElements());
            setButtonGroup(rounds, groupCE.getElements());
        }

        if (bobot.get(15).doubleValue() >= 1) {
            setPreferensiGroup(bobot.get(15).intValue(), cf.getElements());
            setButtonGroup(bobot.get(15).intValue(), groupCF.getElements());
        } else {
            rounds = (int) Math.ceil(1 / bobot.get(15).doubleValue());
            setPreferensiGroup(bobot.get(15).intValue(), cf.getElements());
            setButtonGroup(rounds, groupCF.getElements());
        }

        if (bobot.get(16).doubleValue() >= 1) {
            setPreferensiGroup(bobot.get(16).intValue(), cg.getElements());
            setButtonGroup(bobot.get(16).intValue(), groupCG.getElements());
        } else {
            rounds = (int) Math.ceil(1 / bobot.get(16).doubleValue());
            setPreferensiGroup(bobot.get(16).intValue(), cg.getElements());
            setButtonGroup(rounds, groupCG.getElements());
        }

        if (bobot.get(17).doubleValue() >= 1) {
            setPreferensiGroup(bobot.get(17).intValue(), ch.getElements());
            setButtonGroup(bobot.get(17).intValue(), groupCH.getElements());
        } else {
            rounds = (int) Math.ceil(1 / bobot.get(17).doubleValue());
            setPreferensiGroup(bobot.get(17).intValue(), ch.getElements());
            setButtonGroup(rounds, groupCH.getElements());
        }

        if (bobot.get(18).doubleValue() >= 1) {
            setPreferensiGroup(bobot.get(18).intValue(), de.getElements());
            setButtonGroup(bobot.get(18).intValue(), groupDE.getElements());
        } else {
            rounds = (int) Math.ceil(1 / bobot.get(18).doubleValue());
            setPreferensiGroup(bobot.get(18).intValue(), de.getElements());
            setButtonGroup(rounds, groupDE.getElements());
        }

        if (bobot.get(19).doubleValue() >= 1) {
            setPreferensiGroup(bobot.get(19).intValue(), df.getElements());
            setButtonGroup(bobot.get(19).intValue(), groupDF.getElements());
        } else {
            rounds = (int) Math.ceil(1 / bobot.get(19).doubleValue());
            setPreferensiGroup(bobot.get(19).intValue(), df.getElements());
            setButtonGroup(rounds, groupDF.getElements());
        }

        if (bobot.get(20).doubleValue() >= 1) {
            setPreferensiGroup(bobot.get(20).intValue(), dg.getElements());
            setButtonGroup(bobot.get(20).intValue(), groupDG.getElements());
        } else {
            rounds = (int) Math.ceil(1 / bobot.get(20).doubleValue());
            setPreferensiGroup(bobot.get(20).intValue(), dg.getElements());
            setButtonGroup(rounds, groupDG.getElements());
        }

        if (bobot.get(21).doubleValue() >= 1) {
            setPreferensiGroup(bobot.get(21).intValue(), dh.getElements());
            setButtonGroup(bobot.get(21).intValue(), groupDH.getElements());
        } else {
            rounds = (int) Math.ceil(1 / bobot.get(21).doubleValue());
            setPreferensiGroup(bobot.get(21).intValue(), dh.getElements());
            setButtonGroup(rounds, groupDH.getElements());
        }

        if (bobot.get(22).doubleValue() >= 1) {
            setPreferensiGroup(bobot.get(22).intValue(), ef.getElements());
            setButtonGroup(bobot.get(22).intValue(), groupEF.getElements());
        } else {
            rounds = (int) Math.ceil(1 / bobot.get(22).doubleValue());
            setPreferensiGroup(bobot.get(22).intValue(), ef.getElements());
            setButtonGroup(rounds, groupEF.getElements());
        }

        if (bobot.get(23).doubleValue() >= 1) {
            setPreferensiGroup(bobot.get(23).intValue(), eg.getElements());
            setButtonGroup(bobot.get(23).intValue(), groupEG.getElements());
        } else {
            rounds = (int) Math.ceil(1 / bobot.get(23).doubleValue());
            setPreferensiGroup(bobot.get(23).intValue(), eg.getElements());
            setButtonGroup(rounds, groupEG.getElements());
        }

        if (bobot.get(24).doubleValue() >= 1) {
            setPreferensiGroup(bobot.get(24).intValue(), eh.getElements());
            setButtonGroup(bobot.get(24).intValue(), groupEH.getElements());
        } else {
            rounds = (int) Math.ceil(1 / bobot.get(24).doubleValue());
            setPreferensiGroup(bobot.get(24).intValue(), eh.getElements());
            setButtonGroup(rounds, groupEH.getElements());
        }

        if (bobot.get(25).doubleValue() >= 1) {
            setPreferensiGroup(bobot.get(25).intValue(), fg.getElements());
            setButtonGroup(bobot.get(25).intValue(), groupFG.getElements());
        } else {
            rounds = (int) Math.ceil(1 / bobot.get(25).doubleValue());
            setPreferensiGroup(bobot.get(25).intValue(), fg.getElements());
            setButtonGroup(rounds, groupFG.getElements());
        }

        if (bobot.get(26).doubleValue() >= 1) {
            setPreferensiGroup(bobot.get(26).intValue(), fh.getElements());
            setButtonGroup(bobot.get(26).intValue(), groupFH.getElements());
        } else {
            rounds = (int) Math.ceil(1 / bobot.get(26).doubleValue());
            setPreferensiGroup(bobot.get(26).intValue(), fh.getElements());
            setButtonGroup(rounds, groupFH.getElements());
        }

        if (bobot.get(27).doubleValue() >= 1) {
            setPreferensiGroup(bobot.get(27).intValue(), gh.getElements());
            setButtonGroup(bobot.get(27).intValue(), groupGH.getElements());
        } else {
            rounds = (int) Math.ceil(1 / bobot.get(27).doubleValue());
            setPreferensiGroup(bobot.get(27).intValue(), gh.getElements());
            setButtonGroup(rounds, groupGH.getElements());
        }

    }

    public void setPreferensiGroup(int rdValue, Enumeration elements) {
        while (elements.hasMoreElements()) {
            AbstractButton button = (AbstractButton) elements.nextElement();
            if (rdValue >= 1 && Integer.parseInt(button.getText()) == 1) {
                button.setSelected(true);
            } else if (rdValue < 1 && Integer.parseInt(button.getText()) == 0) {
                button.setSelected(true);
            }
        }
    }

    public void setButtonGroup(int rdValue, Enumeration elements) {
        while (elements.hasMoreElements()) {
            AbstractButton button = (AbstractButton) elements.nextElement();
            if (rdValue == Integer.parseInt(button.getText())) {
                button.setSelected(true);
            }
        }
    }

    public double getNilaiButton(Enumeration bobot, Enumeration pilihan) {
        double nilai = 0;
        if (getButtonGroup(pilihan) == 1) {
            nilai = getButtonGroup(bobot);
        } else {
            nilai = 1 / getButtonGroup(bobot);
        }
        return nilai;
    }

    public double getButtonGroup(Enumeration elements) {
        while (elements.hasMoreElements()) {
            AbstractButton button = (AbstractButton) elements.nextElement();
            if (button.isSelected()) {
                return Double.parseDouble(button.getText());
            }
        }
        return 0;
    }

    boolean cekNilai(ArrayList<Double> nilai) {
        for (int i = 0; i < nilai.size(); i++) {
            if (nilai.get(i) == 0) {
                return false;
            }
        }
        return true;
    }

    private void InitTableMatriks() {
        model = new DefaultTableModel();
        model.addColumn("Kriteria");
        model.addColumn("Rerata Jumlah Tandan");
        model.addColumn("Rerata Berat Tandan");
        model.addColumn("Potensi TBS");
        model.addColumn("Rendemen");
        model.addColumn("Potensi CPO");
        model.addColumn("Tinggi");
        model.addColumn("Panjang Pelepah");
        model.addColumn("Kerapatan Tanam");
//        model.addColumn("Eigen Value");
        model.addColumn("Priority Vector");
        TableMatriks.setModel(model);
        TableMatriks.setRowHeight(30);
        TableColumnModel columnModel = TableMatriks.getColumnModel();
        for (int i = 0; i < 10; i++) {
            columnModel.getColumn(i).setPreferredWidth(150);
        }
//        TableMatriks.removeColumn(TablePengguna.getColumnModel().getColumn(0));
    }
    
    private void TampilDataMatriks() {
        ArrayList<Double> bobot = kriteria.getPerbandinganKriteriaUser(pengguna.
                getId());
        try {
            double[] bobots =  new double[28];
            int [] pilihan=  new int[28];
            
            bobots[0] = getButtonGroup(groupAB.getElements());
            bobots[1] = getButtonGroup(groupAC.getElements());
            bobots[2] = getButtonGroup(groupAD.getElements());
            bobots[3] = getButtonGroup(groupAE.getElements());
            bobots[4] = getButtonGroup(groupAF.getElements());
            bobots[5] = getButtonGroup(groupAG.getElements());
            bobots[6] = getButtonGroup(groupAH.getElements());
            bobots[7] = getButtonGroup(groupBC.getElements());
            bobots[8] = getButtonGroup(groupBD.getElements());
            bobots[9] = getButtonGroup(groupBE.getElements());
            bobots[10] = getButtonGroup(groupBF.getElements());
            bobots[11] = getButtonGroup(groupBG.getElements());
            bobots[12] = getButtonGroup(groupBH.getElements());
            bobots[13] = getButtonGroup(groupCD.getElements());
            bobots[14] = getButtonGroup(groupCE.getElements());
            bobots[15] = getButtonGroup(groupCF.getElements());
            bobots[16] = getButtonGroup(groupCG.getElements());
            bobots[17] = getButtonGroup(groupCH.getElements());
            bobots[18] = getButtonGroup(groupDE.getElements());
            bobots[19] = getButtonGroup(groupDF.getElements());
            bobots[20] = getButtonGroup(groupDG.getElements());
            bobots[21] = getButtonGroup(groupDH.getElements());
            bobots[22] = getButtonGroup(groupEF.getElements());
            bobots[23] = getButtonGroup(groupEG.getElements());
            bobots[24] = getButtonGroup(groupEH.getElements());
            bobots[25] = getButtonGroup(groupFG.getElements());
            bobots[26] = getButtonGroup(groupFH.getElements());
            bobots[27] = getButtonGroup(groupGH.getElements());
            
            pilihan[0] = (int)getButtonGroup(ab.getElements());
            pilihan[1] = (int)getButtonGroup(ac.getElements());
            pilihan[2] = (int)getButtonGroup(ad.getElements());
            pilihan[3] = (int)getButtonGroup(ae.getElements());
            pilihan[4] = (int)getButtonGroup(af.getElements());
            pilihan[5] = (int)getButtonGroup(ag.getElements());
            pilihan[6] = (int)getButtonGroup(ah.getElements());
            pilihan[7] = (int)getButtonGroup(bc.getElements());
            pilihan[8] = (int)getButtonGroup(bd.getElements());
            pilihan[9] = (int)getButtonGroup(be.getElements());
            pilihan[10] = (int)getButtonGroup(bf.getElements());
            pilihan[11] = (int)getButtonGroup(bg.getElements());
            pilihan[12] = (int)getButtonGroup(bh.getElements());
            pilihan[13] = (int)getButtonGroup(cd.getElements());
            pilihan[14] = (int)getButtonGroup(ce.getElements());
            pilihan[15] = (int)getButtonGroup(cf.getElements());
            pilihan[16] = (int)getButtonGroup(cg.getElements());
            pilihan[17] = (int)getButtonGroup(ch.getElements());
            pilihan[18] = (int)getButtonGroup(de.getElements());
            pilihan[19] = (int)getButtonGroup(df.getElements());
            pilihan[20] = (int)getButtonGroup(dg.getElements());
            pilihan[21] = (int)getButtonGroup(dh.getElements());
            pilihan[22] = (int)getButtonGroup(ef.getElements());
            pilihan[23] = (int)getButtonGroup(eg.getElements());
            pilihan[24] = (int)getButtonGroup(eh.getElements());
            pilihan[25] = (int)getButtonGroup(fg.getElements());
            pilihan[26] = (int)getButtonGroup(fh.getElements());
            pilihan[27] = (int)getButtonGroup(gh.getElements());
           
            
            
            ahp.proses(pengguna.getId(), bobots,pilihan);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        double[][] matriks = ahp.getMatriksKriteria();
        pVector = ahp.getPriorityVector();
        double eValue = ahp.getEigenValue();
        CI = ahp.getConsistencyIndex();
        CR = ahp.getConsistencyRatio();
        double eigen = ahp.getEigenValue();
        try {
            for (int i = 0; i < 8; i++) {
                Object[] record = new Object[10];
                record[0] = kriteria.allKriteria().get(i).getNama();
                for (int j = 0; j < 8; j++) {
                    record[j + 1] = matriks[i][j];
                }
                record[9] = pVector[i];
                model.addRow(record);
            }
            EV.setText(EV.getText() + "" + eigen);
            KI.setText(KI.getText() + "" + CI);
            KR.setText(KR.getText() + "" + CR);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        try {
            Object[] row = new Object[10];
            row[0] = "Jumlah Total";
            for (int i = 1; i < 10; i++) {
                row[i] = hitungNilai(i);
            }
            model.addRow(row);
        } catch (Exception e) {

        }

        TableMatriks.setDefaultRenderer(Object.class,
                new DefaultTableCellRenderer() {
            public Component getTableCellRendererComponent(JTable table,
                    Object value, boolean isSelected, boolean hasFocus, int row,
                    int col) {

                super.getTableCellRendererComponent(table, value, isSelected,
                        hasFocus, row, col);

                if (row == 0 && col == 1 || row == 1 && col == 2 || row == 2
                        && col == 3 || row == 3 && col == 4 || row == 4 && col
                        == 5 || row == 5 && col == 6 || row == 6 && col == 7
                        || row == 7 && col == 8 || row == 8 && col == 9 || row
                        == 8 && col == 0) {
                    setBackground(Color.YELLOW);
                    setForeground(Color.BLACK);
                } else {
                    setBackground(table.getBackground());
                    setForeground(table.getForeground());
                }
                return this;
            }
        });
    }
    
    public double hitungNilai(int column) {
        double nilai = 0;
        ArrayList list = new ArrayList();
        for (int i = 0; i < TableMatriks.getModel().getRowCount(); i++) {
            list.add(TableMatriks.getModel().getValueAt(i, column)); //get the all row values at column index 0
        }
        for (int i = 0; i < list.size(); i++) {
            nilai = nilai + (Double) list.get(i);
        }
        return nilai;
    }
    
    private void menu_kriteriaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menu_kriteriaMousePressed
        ArrayList<Double> bobot = kriteria.getPerbandinganKriteriaUser(pengguna.
                getId());
        if (bobot.isEmpty()) {

        } else {
            this.radioPreferensi(bobot);
        }
        setColor(menu_kriteria);
        kriteria_aktif.setOpaque(true);
        resetColor(new JPanel[]{menu_matriks}, new JPanel[]{matriks_aktif});
        resetColor(new JPanel[]{menu_pengguna}, new JPanel[]{pengguna_aktif});
        resetColor(new JPanel[]{menu_varietas}, new JPanel[]{varietas_aktif});

        panel_matriks.setVisible(false);
        panel_pengguna.setVisible(false);
        panel_varietas.setVisible(false);

        panel_kriteria.setVisible(true);
    }//GEN-LAST:event_menu_kriteriaMousePressed

    private void menu_matriksMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menu_matriksMousePressed
        // TODO add your handling code here:
        setColor(menu_matriks);
        matriks_aktif.setOpaque(true);
        resetColor(new JPanel[]{menu_kriteria}, new JPanel[]{kriteria_aktif});
        resetColor(new JPanel[]{menu_pengguna}, new JPanel[]{pengguna_aktif});
        resetColor(new JPanel[]{menu_varietas}, new JPanel[]{varietas_aktif});

        panel_kriteria.setVisible(false);
        panel_pengguna.setVisible(false);
        panel_varietas.setVisible(false);
        panel_matriks.setVisible(true);
    }//GEN-LAST:event_menu_matriksMousePressed

    private void AB1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AB1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_AB1ActionPerformed

    private void AB2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AB2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_AB2ActionPerformed

    private void AB3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AB3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_AB3ActionPerformed

    private void AB4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AB4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_AB4ActionPerformed

    private void AB5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AB5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_AB5ActionPerformed

    private void AB6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AB6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_AB6ActionPerformed

    private void AB7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AB7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_AB7ActionPerformed

    private void AB8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AB8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_AB8ActionPerformed

    private void AB9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AB9ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_AB9ActionPerformed

    private void AC1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AC1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_AC1ActionPerformed

    private void AC2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AC2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_AC2ActionPerformed

    private void AC3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AC3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_AC3ActionPerformed

    private void AC4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AC4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_AC4ActionPerformed

    private void AC5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AC5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_AC5ActionPerformed

    private void AC6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AC6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_AC6ActionPerformed

    private void AC7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AC7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_AC7ActionPerformed

    private void AC8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AC8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_AC8ActionPerformed

    private void AC9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AC9ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_AC9ActionPerformed

    private void AD1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AD1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_AD1ActionPerformed

    private void AD2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AD2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_AD2ActionPerformed

    private void AD3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AD3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_AD3ActionPerformed

    private void AD4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AD4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_AD4ActionPerformed

    private void AD5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AD5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_AD5ActionPerformed

    private void AD6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AD6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_AD6ActionPerformed

    private void AD7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AD7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_AD7ActionPerformed

    private void AD8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AD8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_AD8ActionPerformed

    private void AD9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AD9ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_AD9ActionPerformed

    private void AE1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AE1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_AE1ActionPerformed

    private void AE2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AE2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_AE2ActionPerformed

    private void AE3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AE3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_AE3ActionPerformed

    private void AE4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AE4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_AE4ActionPerformed

    private void AE5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AE5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_AE5ActionPerformed

    private void AE6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AE6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_AE6ActionPerformed

    private void AE7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AE7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_AE7ActionPerformed

    private void AE8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AE8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_AE8ActionPerformed

    private void AE9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AE9ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_AE9ActionPerformed

    private void AF1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AF1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_AF1ActionPerformed

    private void AF2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AF2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_AF2ActionPerformed

    private void AF3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AF3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_AF3ActionPerformed

    private void AF4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AF4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_AF4ActionPerformed

    private void AF5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AF5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_AF5ActionPerformed

    private void AF6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AF6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_AF6ActionPerformed

    private void AF7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AF7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_AF7ActionPerformed

    private void AF8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AF8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_AF8ActionPerformed

    private void AF9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AF9ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_AF9ActionPerformed

    private void AG1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AG1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_AG1ActionPerformed

    private void AG2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AG2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_AG2ActionPerformed

    private void AG3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AG3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_AG3ActionPerformed

    private void AG4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AG4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_AG4ActionPerformed

    private void AG5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AG5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_AG5ActionPerformed

    private void AG6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AG6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_AG6ActionPerformed

    private void AG7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AG7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_AG7ActionPerformed

    private void AG8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AG8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_AG8ActionPerformed

    private void AG9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AG9ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_AG9ActionPerformed

    private void AH1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AH1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_AH1ActionPerformed

    private void AH2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AH2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_AH2ActionPerformed

    private void AH3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AH3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_AH3ActionPerformed

    private void AH4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AH4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_AH4ActionPerformed

    private void AH5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AH5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_AH5ActionPerformed

    private void AH6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AH6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_AH6ActionPerformed

    private void AH7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AH7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_AH7ActionPerformed

    private void AH8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AH8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_AH8ActionPerformed

    private void AH9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AH9ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_AH9ActionPerformed

    private void BC1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BC1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BC1ActionPerformed

    private void BC2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BC2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BC2ActionPerformed

    private void BC3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BC3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BC3ActionPerformed

    private void BC4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BC4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BC4ActionPerformed

    private void BC5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BC5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BC5ActionPerformed

    private void BC6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BC6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BC6ActionPerformed

    private void BC7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BC7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BC7ActionPerformed

    private void BC8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BC8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BC8ActionPerformed

    private void BC9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BC9ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BC9ActionPerformed

    private void BD1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BD1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BD1ActionPerformed

    private void BD2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BD2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BD2ActionPerformed

    private void BD3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BD3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BD3ActionPerformed

    private void BD4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BD4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BD4ActionPerformed

    private void BD5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BD5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BD5ActionPerformed

    private void BD6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BD6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BD6ActionPerformed

    private void BD7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BD7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BD7ActionPerformed

    private void BD8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BD8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BD8ActionPerformed

    private void BD9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BD9ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BD9ActionPerformed

    private void BE1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BE1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BE1ActionPerformed

    private void BE2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BE2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BE2ActionPerformed

    private void BE3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BE3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BE3ActionPerformed

    private void BE4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BE4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BE4ActionPerformed

    private void BE5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BE5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BE5ActionPerformed

    private void BE6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BE6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BE6ActionPerformed

    private void BE7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BE7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BE7ActionPerformed

    private void BE8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BE8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BE8ActionPerformed

    private void BE9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BE9ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BE9ActionPerformed

    private void BF1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BF1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BF1ActionPerformed

    private void BF2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BF2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BF2ActionPerformed

    private void BF3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BF3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BF3ActionPerformed

    private void BF4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BF4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BF4ActionPerformed

    private void BF5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BF5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BF5ActionPerformed

    private void BF6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BF6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BF6ActionPerformed

    private void BF7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BF7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BF7ActionPerformed

    private void BF8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BF8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BF8ActionPerformed

    private void BF9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BF9ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BF9ActionPerformed

    private void BG1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BG1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BG1ActionPerformed

    private void BG2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BG2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BG2ActionPerformed

    private void BG3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BG3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BG3ActionPerformed

    private void BG4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BG4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BG4ActionPerformed

    private void BG5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BG5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BG5ActionPerformed

    private void BG6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BG6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BG6ActionPerformed

    private void BG7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BG7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BG7ActionPerformed

    private void BG8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BG8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BG8ActionPerformed

    private void BG9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BG9ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BG9ActionPerformed

    private void BH1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BH1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BH1ActionPerformed

    private void BH2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BH2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BH2ActionPerformed

    private void BH3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BH3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BH3ActionPerformed

    private void BH4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BH4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BH4ActionPerformed

    private void BH5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BH5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BH5ActionPerformed

    private void BH6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BH6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BH6ActionPerformed

    private void BH7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BH7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BH7ActionPerformed

    private void BH8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BH8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BH8ActionPerformed

    private void BH9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BH9ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BH9ActionPerformed

    private void CD1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CD1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CD1ActionPerformed

    private void CD2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CD2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CD2ActionPerformed

    private void CD3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CD3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CD3ActionPerformed

    private void CD4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CD4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CD4ActionPerformed

    private void CD5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CD5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CD5ActionPerformed

    private void CD6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CD6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CD6ActionPerformed

    private void CD7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CD7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CD7ActionPerformed

    private void CD8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CD8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CD8ActionPerformed

    private void CD9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CD9ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CD9ActionPerformed

    private void CE1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CE1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CE1ActionPerformed

    private void CE2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CE2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CE2ActionPerformed

    private void CE3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CE3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CE3ActionPerformed

    private void CE4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CE4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CE4ActionPerformed

    private void CE5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CE5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CE5ActionPerformed

    private void CE6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CE6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CE6ActionPerformed

    private void CE7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CE7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CE7ActionPerformed

    private void CE8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CE8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CE8ActionPerformed

    private void CE9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CE9ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CE9ActionPerformed

    private void CF1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CF1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CF1ActionPerformed

    private void CF2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CF2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CF2ActionPerformed

    private void CF3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CF3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CF3ActionPerformed

    private void CF4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CF4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CF4ActionPerformed

    private void CF5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CF5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CF5ActionPerformed

    private void CF6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CF6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CF6ActionPerformed

    private void CF7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CF7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CF7ActionPerformed

    private void CF8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CF8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CF8ActionPerformed

    private void CF9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CF9ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CF9ActionPerformed

    private void CG1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CG1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CG1ActionPerformed

    private void CG2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CG2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CG2ActionPerformed

    private void CG3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CG3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CG3ActionPerformed

    private void CG4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CG4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CG4ActionPerformed

    private void CG5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CG5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CG5ActionPerformed

    private void CG6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CG6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CG6ActionPerformed

    private void CG7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CG7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CG7ActionPerformed

    private void CG8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CG8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CG8ActionPerformed

    private void CG9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CG9ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CG9ActionPerformed

    private void CH1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CH1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CH1ActionPerformed

    private void CH2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CH2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CH2ActionPerformed

    private void CH3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CH3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CH3ActionPerformed

    private void CH4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CH4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CH4ActionPerformed

    private void CH5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CH5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CH5ActionPerformed

    private void CH6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CH6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CH6ActionPerformed

    private void CH7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CH7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CH7ActionPerformed

    private void CH8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CH8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CH8ActionPerformed

    private void CH9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CH9ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CH9ActionPerformed

    private void DE1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DE1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_DE1ActionPerformed

    private void DE2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DE2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_DE2ActionPerformed

    private void DE3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DE3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_DE3ActionPerformed

    private void DE4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DE4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_DE4ActionPerformed

    private void DE5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DE5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_DE5ActionPerformed

    private void DE6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DE6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_DE6ActionPerformed

    private void DE7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DE7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_DE7ActionPerformed

    private void DE8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DE8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_DE8ActionPerformed

    private void DE9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DE9ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_DE9ActionPerformed

    private void DF1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DF1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_DF1ActionPerformed

    private void DF2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DF2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_DF2ActionPerformed

    private void DF3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DF3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_DF3ActionPerformed

    private void DF4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DF4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_DF4ActionPerformed

    private void DF5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DF5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_DF5ActionPerformed

    private void DF6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DF6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_DF6ActionPerformed

    private void DF7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DF7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_DF7ActionPerformed

    private void DF8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DF8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_DF8ActionPerformed

    private void DF9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DF9ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_DF9ActionPerformed

    private void DG1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DG1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_DG1ActionPerformed

    private void DG2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DG2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_DG2ActionPerformed

    private void DG3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DG3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_DG3ActionPerformed

    private void DG4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DG4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_DG4ActionPerformed

    private void DG5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DG5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_DG5ActionPerformed

    private void DG6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DG6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_DG6ActionPerformed

    private void DG7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DG7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_DG7ActionPerformed

    private void DG8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DG8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_DG8ActionPerformed

    private void DG9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DG9ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_DG9ActionPerformed

    private void DH1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DH1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_DH1ActionPerformed

    private void DH2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DH2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_DH2ActionPerformed

    private void DH3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DH3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_DH3ActionPerformed

    private void DH4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DH4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_DH4ActionPerformed

    private void DH5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DH5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_DH5ActionPerformed

    private void DH6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DH6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_DH6ActionPerformed

    private void DH7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DH7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_DH7ActionPerformed

    private void DH8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DH8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_DH8ActionPerformed

    private void DH9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DH9ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_DH9ActionPerformed

    private void EF1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EF1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_EF1ActionPerformed

    private void EF2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EF2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_EF2ActionPerformed

    private void EF3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EF3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_EF3ActionPerformed

    private void EF4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EF4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_EF4ActionPerformed

    private void EF5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EF5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_EF5ActionPerformed

    private void EF6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EF6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_EF6ActionPerformed

    private void EF7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EF7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_EF7ActionPerformed

    private void EF8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EF8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_EF8ActionPerformed

    private void EF9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EF9ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_EF9ActionPerformed

    private void EG1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EG1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_EG1ActionPerformed

    private void EG2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EG2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_EG2ActionPerformed

    private void EG3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EG3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_EG3ActionPerformed

    private void EG4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EG4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_EG4ActionPerformed

    private void EG5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EG5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_EG5ActionPerformed

    private void EG6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EG6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_EG6ActionPerformed

    private void EG7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EG7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_EG7ActionPerformed

    private void EG8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EG8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_EG8ActionPerformed

    private void EG9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EG9ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_EG9ActionPerformed

    private void EH1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EH1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_EH1ActionPerformed

    private void EH2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EH2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_EH2ActionPerformed

    private void EH3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EH3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_EH3ActionPerformed

    private void EH4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EH4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_EH4ActionPerformed

    private void EH5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EH5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_EH5ActionPerformed

    private void EH6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EH6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_EH6ActionPerformed

    private void EH7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EH7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_EH7ActionPerformed

    private void EH8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EH8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_EH8ActionPerformed

    private void EH9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EH9ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_EH9ActionPerformed

    private void FG1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FG1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_FG1ActionPerformed

    private void FG2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FG2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_FG2ActionPerformed

    private void FG3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FG3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_FG3ActionPerformed

    private void FG4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FG4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_FG4ActionPerformed

    private void FG5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FG5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_FG5ActionPerformed

    private void FG6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FG6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_FG6ActionPerformed

    private void FG7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FG7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_FG7ActionPerformed

    private void FG8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FG8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_FG8ActionPerformed

    private void FG9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FG9ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_FG9ActionPerformed

    private void FH1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FH1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_FH1ActionPerformed

    private void FH2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FH2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_FH2ActionPerformed

    private void FH3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FH3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_FH3ActionPerformed

    private void FH4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FH4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_FH4ActionPerformed

    private void FH5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FH5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_FH5ActionPerformed

    private void FH6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FH6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_FH6ActionPerformed

    private void FH7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FH7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_FH7ActionPerformed

    private void FH8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FH8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_FH8ActionPerformed

    private void FH9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FH9ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_FH9ActionPerformed

    private void GH1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GH1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_GH1ActionPerformed

    private void GH2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GH2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_GH2ActionPerformed

    private void GH3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GH3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_GH3ActionPerformed

    private void GH4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GH4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_GH4ActionPerformed

    private void GH5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GH5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_GH5ActionPerformed

    private void GH6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GH6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_GH6ActionPerformed

    private void GH7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GH7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_GH7ActionPerformed

    private void GH8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GH8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_GH8ActionPerformed

    private void GH9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GH9ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_GH9ActionPerformed

    private void helpButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_helpButtonActionPerformed
        Help bantuan = new Help();
        bantuan.setVisible(true);
    }//GEN-LAST:event_helpButtonActionPerformed

    private void simpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_simpanActionPerformed
        JDialog.setDefaultLookAndFeelDecorated(true);
        int response = JOptionPane.showConfirmDialog(null,
                "Anda yakin ingin menyimpan Bobot Kriteria ?",
                "Simpan Bobot Kriteria",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (response == JOptionPane.NO_OPTION) {
            JOptionPane.
                    showMessageDialog(null, "Batal Menyimpan Bobot Kriteria");
        } else if (response == JOptionPane.YES_OPTION) {

            System.out.println((int) getButtonGroup(ab.getElements()));
            ArrayList<Double> nilai = new ArrayList<>();
            nilai.add(getNilaiButton(groupAB.getElements(), ab.getElements()));
            nilai.add(getNilaiButton(groupAC.getElements(), ac.getElements()));
            nilai.add(getNilaiButton(groupAD.getElements(), ad.getElements()));
            nilai.add(getNilaiButton(groupAE.getElements(), ae.getElements()));
            nilai.add(getNilaiButton(groupAF.getElements(), af.getElements()));
            nilai.add(getNilaiButton(groupAG.getElements(), ag.getElements()));
            nilai.add(getNilaiButton(groupAH.getElements(), ah.getElements()));
            nilai.add(getNilaiButton(groupBC.getElements(), bc.getElements()));
            nilai.add(getNilaiButton(groupBD.getElements(), bd.getElements()));
            nilai.add(getNilaiButton(groupBE.getElements(), be.getElements()));
            nilai.add(getNilaiButton(groupBF.getElements(), bf.getElements()));
            nilai.add(getNilaiButton(groupBG.getElements(), bg.getElements()));
            nilai.add(getNilaiButton(groupBH.getElements(), bh.getElements()));
            nilai.add(getNilaiButton(groupCD.getElements(), cd.getElements()));
            nilai.add(getNilaiButton(groupCE.getElements(), ce.getElements()));
            nilai.add(getNilaiButton(groupCF.getElements(), cf.getElements()));
            nilai.add(getNilaiButton(groupCG.getElements(), cg.getElements()));
            nilai.add(getNilaiButton(groupCH.getElements(), ch.getElements()));
            nilai.add(getNilaiButton(groupDE.getElements(), de.getElements()));
            nilai.add(getNilaiButton(groupDF.getElements(), df.getElements()));
            nilai.add(getNilaiButton(groupDG.getElements(), dg.getElements()));
            nilai.add(getNilaiButton(groupDH.getElements(), dh.getElements()));
            nilai.add(getNilaiButton(groupEF.getElements(), ef.getElements()));
            nilai.add(getNilaiButton(groupEG.getElements(), eg.getElements()));
            nilai.add(getNilaiButton(groupEH.getElements(), eh.getElements()));
            nilai.add(getNilaiButton(groupFG.getElements(), fg.getElements()));
            nilai.add(getNilaiButton(groupFH.getElements(), fh.getElements()));
            nilai.add(getNilaiButton(groupGH.getElements(), gh.getElements()));

            if (cekNilai(nilai)) {
                int indexNilai = 0;
                if (kriteria.checkPerbandinganKriteriaUser(pengguna.getId())) {
                    for (int i = 1; i < 8; i++) {
                        for (int j = 1; j < 9; j++) {
                            if (j <= i) {
                                continue;
                            } else {
                                kriteria.tambahPerbandinganAntarKriteria(i, j,
                                        nilai.get(indexNilai), pengguna.getId());
                                indexNilai++;
                            }
                        }
                    }
                    indexNilai = 0;
                }
                JOptionPane.showMessageDialog(null, "Berhasil Menyimpan Bobot");
                setColor(menu_matriks);
                matriks_aktif.setOpaque(true);
                resetColor(new JPanel[]{menu_kriteria}, new JPanel[]{
                    kriteria_aktif});
                resetColor(new JPanel[]{menu_pengguna}, new JPanel[]{
                    pengguna_aktif});
                resetColor(new JPanel[]{menu_varietas}, new JPanel[]{
                    varietas_aktif});
                panel_kriteria.setVisible(false);
                panel_pengguna.setVisible(false);
                panel_varietas.setVisible(false);
                panel_matriks.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null,
                        "Silahkan Isi Bobot Yang Masih Kosong");
            }
        }
    }//GEN-LAST:event_simpanActionPerformed

    private void finishActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_finishActionPerformed
        if (finish.getText().equalsIgnoreCase("Kembali Ke Kriteria")) {
            ArrayList<Double> bobot = kriteria.getPerbandinganKriteriaUser(
                pengguna.
                getId());
            if (bobot.isEmpty()) {

            } else {
                this.radioPreferensi(bobot);
            }
            setColor(menu_kriteria);
            kriteria_aktif.setOpaque(true);
            resetColor(new JPanel[]{menu_matriks}, new JPanel[]{matriks_aktif});
            resetColor(new JPanel[]{menu_pengguna}, new JPanel[]{pengguna_aktif});
            resetColor(new JPanel[]{menu_varietas}, new JPanel[]{varietas_aktif});
            panel_matriks.setVisible(false);
            panel_pengguna.setVisible(false);
            panel_varietas.setVisible(false);
            panel_kriteria.setVisible(true);
            
        } else if (finish.getText().equalsIgnoreCase("Lanjut Ke Hasil")) {
            setColor(menu_hasil);
            hasil_aktif.setOpaque(true);
            resetColor(new JPanel[]{menu_kriteria}, new JPanel[]{kriteria_aktif});
            resetColor(new JPanel[]{menu_matriks}, new JPanel[]{matriks_aktif});
            panel_kriteria.setVisible(false);
            panel_hasil.setVisible(true);
            panel_matriks.setVisible(false);
        }
    }//GEN-LAST:event_finishActionPerformed

    private void panel_matriksComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_panel_matriksComponentShown
        EV.setText("Eigen Value\t\t= ");
        KI.setText("Konsistensi Index\t= ");
        KR.setText("Konsistensi Rasio\t= ");
        InitTableMatriks();
        TampilDataMatriks();
        if (CR > 0.1) {
            finish.setText("Kembali Ke Kriteria");
            finish.setBackground(Color.red);
            JOptionPane.showMessageDialog(this,
                "Nilai Konsistensi Rasio Anda Tidak Memenuhi Syarat Untuk Melanjutkan Perhitungan. Silahkan Ulangi Pengisian Kriteria Anda");

        } else {
            finish.setText("Lanjut Ke Hasil");
            finish.setBackground(Color.green);
        }
        if (CR == 0) {
            finish.setVisible(false);
        }
    }//GEN-LAST:event_panel_matriksComponentShown

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
            for (javax.swing.UIManager.LookAndFeelInfo info
                    : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Admin.class.getName()).log(
                    java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Admin.class.getName()).log(
                    java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Admin.class.getName()).log(
                    java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Admin.class.getName()).log(
                    java.util.logging.Level.SEVERE, null, ex);
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
    private javax.swing.JRadioButton AB1;
    private javax.swing.JRadioButton AB2;
    private javax.swing.JRadioButton AB3;
    private javax.swing.JRadioButton AB4;
    private javax.swing.JRadioButton AB5;
    private javax.swing.JRadioButton AB6;
    private javax.swing.JRadioButton AB7;
    private javax.swing.JRadioButton AB8;
    private javax.swing.JRadioButton AB9;
    private javax.swing.JRadioButton ABa;
    private javax.swing.JRadioButton ABa14;
    private javax.swing.JRadioButton ABa15;
    private javax.swing.JRadioButton ABa16;
    private javax.swing.JRadioButton ABa17;
    private javax.swing.JRadioButton ABa18;
    private javax.swing.JRadioButton ABa19;
    private javax.swing.JRadioButton ABa20;
    private javax.swing.JRadioButton ABa21;
    private javax.swing.JRadioButton ABa22;
    private javax.swing.JRadioButton ABa23;
    private javax.swing.JRadioButton ABa24;
    private javax.swing.JRadioButton ABa25;
    private javax.swing.JRadioButton ABa26;
    private javax.swing.JRadioButton ABa27;
    private javax.swing.JRadioButton ABb;
    private javax.swing.JRadioButton ABb14;
    private javax.swing.JRadioButton ABb15;
    private javax.swing.JRadioButton ABb16;
    private javax.swing.JRadioButton ABb17;
    private javax.swing.JRadioButton ABb18;
    private javax.swing.JRadioButton ABb19;
    private javax.swing.JRadioButton ABb20;
    private javax.swing.JRadioButton ABb21;
    private javax.swing.JRadioButton ABb22;
    private javax.swing.JRadioButton ABb23;
    private javax.swing.JRadioButton ABb24;
    private javax.swing.JRadioButton ABb25;
    private javax.swing.JRadioButton ABb26;
    private javax.swing.JRadioButton ABb27;
    private javax.swing.JRadioButton AC1;
    private javax.swing.JRadioButton AC2;
    private javax.swing.JRadioButton AC3;
    private javax.swing.JRadioButton AC4;
    private javax.swing.JRadioButton AC5;
    private javax.swing.JRadioButton AC6;
    private javax.swing.JRadioButton AC7;
    private javax.swing.JRadioButton AC8;
    private javax.swing.JRadioButton AC9;
    private javax.swing.JRadioButton ACa;
    private javax.swing.JRadioButton ACc;
    private javax.swing.JRadioButton AD1;
    private javax.swing.JRadioButton AD2;
    private javax.swing.JRadioButton AD3;
    private javax.swing.JRadioButton AD4;
    private javax.swing.JRadioButton AD5;
    private javax.swing.JRadioButton AD6;
    private javax.swing.JRadioButton AD7;
    private javax.swing.JRadioButton AD8;
    private javax.swing.JRadioButton AD9;
    private javax.swing.JRadioButton ADa;
    private javax.swing.JRadioButton ADd;
    private javax.swing.JRadioButton AE1;
    private javax.swing.JRadioButton AE2;
    private javax.swing.JRadioButton AE3;
    private javax.swing.JRadioButton AE4;
    private javax.swing.JRadioButton AE5;
    private javax.swing.JRadioButton AE6;
    private javax.swing.JRadioButton AE7;
    private javax.swing.JRadioButton AE8;
    private javax.swing.JRadioButton AE9;
    private javax.swing.JRadioButton AEa;
    private javax.swing.JRadioButton AEe;
    private javax.swing.JRadioButton AF1;
    private javax.swing.JRadioButton AF2;
    private javax.swing.JRadioButton AF3;
    private javax.swing.JRadioButton AF4;
    private javax.swing.JRadioButton AF5;
    private javax.swing.JRadioButton AF6;
    private javax.swing.JRadioButton AF7;
    private javax.swing.JRadioButton AF8;
    private javax.swing.JRadioButton AF9;
    private javax.swing.JRadioButton AFa;
    private javax.swing.JRadioButton AFf;
    private javax.swing.JRadioButton AG1;
    private javax.swing.JRadioButton AG2;
    private javax.swing.JRadioButton AG3;
    private javax.swing.JRadioButton AG4;
    private javax.swing.JRadioButton AG5;
    private javax.swing.JRadioButton AG6;
    private javax.swing.JRadioButton AG7;
    private javax.swing.JRadioButton AG8;
    private javax.swing.JRadioButton AG9;
    private javax.swing.JRadioButton AGa;
    private javax.swing.JRadioButton AGg;
    private javax.swing.JRadioButton AH1;
    private javax.swing.JRadioButton AH2;
    private javax.swing.JRadioButton AH3;
    private javax.swing.JRadioButton AH4;
    private javax.swing.JRadioButton AH5;
    private javax.swing.JRadioButton AH6;
    private javax.swing.JRadioButton AH7;
    private javax.swing.JRadioButton AH8;
    private javax.swing.JRadioButton AH9;
    private javax.swing.JRadioButton AHa;
    private javax.swing.JRadioButton AHh;
    private javax.swing.JRadioButton BC1;
    private javax.swing.JRadioButton BC2;
    private javax.swing.JRadioButton BC3;
    private javax.swing.JRadioButton BC4;
    private javax.swing.JRadioButton BC5;
    private javax.swing.JRadioButton BC6;
    private javax.swing.JRadioButton BC7;
    private javax.swing.JRadioButton BC8;
    private javax.swing.JRadioButton BC9;
    private javax.swing.JRadioButton BCb;
    private javax.swing.JRadioButton BCc;
    private javax.swing.JRadioButton BD1;
    private javax.swing.JRadioButton BD2;
    private javax.swing.JRadioButton BD3;
    private javax.swing.JRadioButton BD4;
    private javax.swing.JRadioButton BD5;
    private javax.swing.JRadioButton BD6;
    private javax.swing.JRadioButton BD7;
    private javax.swing.JRadioButton BD8;
    private javax.swing.JRadioButton BD9;
    private javax.swing.JRadioButton BDb;
    private javax.swing.JRadioButton BDd;
    private javax.swing.JRadioButton BE1;
    private javax.swing.JRadioButton BE2;
    private javax.swing.JRadioButton BE3;
    private javax.swing.JRadioButton BE4;
    private javax.swing.JRadioButton BE5;
    private javax.swing.JRadioButton BE6;
    private javax.swing.JRadioButton BE7;
    private javax.swing.JRadioButton BE8;
    private javax.swing.JRadioButton BE9;
    private javax.swing.JRadioButton BEb;
    private javax.swing.JRadioButton BEe;
    private javax.swing.JRadioButton BF1;
    private javax.swing.JRadioButton BF2;
    private javax.swing.JRadioButton BF3;
    private javax.swing.JRadioButton BF4;
    private javax.swing.JRadioButton BF5;
    private javax.swing.JRadioButton BF6;
    private javax.swing.JRadioButton BF7;
    private javax.swing.JRadioButton BF8;
    private javax.swing.JRadioButton BF9;
    private javax.swing.JRadioButton BFb;
    private javax.swing.JRadioButton BFf;
    private javax.swing.JRadioButton BG1;
    private javax.swing.JRadioButton BG2;
    private javax.swing.JRadioButton BG3;
    private javax.swing.JRadioButton BG4;
    private javax.swing.JRadioButton BG5;
    private javax.swing.JRadioButton BG6;
    private javax.swing.JRadioButton BG7;
    private javax.swing.JRadioButton BG8;
    private javax.swing.JRadioButton BG9;
    private javax.swing.JRadioButton BGb;
    private javax.swing.JRadioButton BGg;
    private javax.swing.JRadioButton BH1;
    private javax.swing.JRadioButton BH2;
    private javax.swing.JRadioButton BH3;
    private javax.swing.JRadioButton BH4;
    private javax.swing.JRadioButton BH5;
    private javax.swing.JRadioButton BH6;
    private javax.swing.JRadioButton BH7;
    private javax.swing.JRadioButton BH8;
    private javax.swing.JRadioButton BH9;
    private javax.swing.JRadioButton BHb;
    private javax.swing.JRadioButton BHh;
    private javax.swing.JRadioButton CD1;
    private javax.swing.JRadioButton CD2;
    private javax.swing.JRadioButton CD3;
    private javax.swing.JRadioButton CD4;
    private javax.swing.JRadioButton CD5;
    private javax.swing.JRadioButton CD6;
    private javax.swing.JRadioButton CD7;
    private javax.swing.JRadioButton CD8;
    private javax.swing.JRadioButton CD9;
    private javax.swing.JRadioButton CDc;
    private javax.swing.JRadioButton CDd;
    private javax.swing.JRadioButton CE1;
    private javax.swing.JRadioButton CE2;
    private javax.swing.JRadioButton CE3;
    private javax.swing.JRadioButton CE4;
    private javax.swing.JRadioButton CE5;
    private javax.swing.JRadioButton CE6;
    private javax.swing.JRadioButton CE7;
    private javax.swing.JRadioButton CE8;
    private javax.swing.JRadioButton CE9;
    private javax.swing.JRadioButton CF1;
    private javax.swing.JRadioButton CF2;
    private javax.swing.JRadioButton CF3;
    private javax.swing.JRadioButton CF4;
    private javax.swing.JRadioButton CF5;
    private javax.swing.JRadioButton CF6;
    private javax.swing.JRadioButton CF7;
    private javax.swing.JRadioButton CF8;
    private javax.swing.JRadioButton CF9;
    private javax.swing.JRadioButton CG1;
    private javax.swing.JRadioButton CG2;
    private javax.swing.JRadioButton CG3;
    private javax.swing.JRadioButton CG4;
    private javax.swing.JRadioButton CG5;
    private javax.swing.JRadioButton CG6;
    private javax.swing.JRadioButton CG7;
    private javax.swing.JRadioButton CG8;
    private javax.swing.JRadioButton CG9;
    private javax.swing.JRadioButton CH1;
    private javax.swing.JRadioButton CH2;
    private javax.swing.JRadioButton CH3;
    private javax.swing.JRadioButton CH4;
    private javax.swing.JRadioButton CH5;
    private javax.swing.JRadioButton CH6;
    private javax.swing.JRadioButton CH7;
    private javax.swing.JRadioButton CH8;
    private javax.swing.JRadioButton CH9;
    private javax.swing.JSpinner CPO;
    private javax.swing.JPanel ContainerPanel;
    private javax.swing.JRadioButton DE1;
    private javax.swing.JRadioButton DE2;
    private javax.swing.JRadioButton DE3;
    private javax.swing.JRadioButton DE4;
    private javax.swing.JRadioButton DE5;
    private javax.swing.JRadioButton DE6;
    private javax.swing.JRadioButton DE7;
    private javax.swing.JRadioButton DE8;
    private javax.swing.JRadioButton DE9;
    private javax.swing.JRadioButton DF1;
    private javax.swing.JRadioButton DF2;
    private javax.swing.JRadioButton DF3;
    private javax.swing.JRadioButton DF4;
    private javax.swing.JRadioButton DF5;
    private javax.swing.JRadioButton DF6;
    private javax.swing.JRadioButton DF7;
    private javax.swing.JRadioButton DF8;
    private javax.swing.JRadioButton DF9;
    private javax.swing.JRadioButton DG1;
    private javax.swing.JRadioButton DG2;
    private javax.swing.JRadioButton DG3;
    private javax.swing.JRadioButton DG4;
    private javax.swing.JRadioButton DG5;
    private javax.swing.JRadioButton DG6;
    private javax.swing.JRadioButton DG7;
    private javax.swing.JRadioButton DG8;
    private javax.swing.JRadioButton DG9;
    private javax.swing.JRadioButton DH1;
    private javax.swing.JRadioButton DH2;
    private javax.swing.JRadioButton DH3;
    private javax.swing.JRadioButton DH4;
    private javax.swing.JRadioButton DH5;
    private javax.swing.JRadioButton DH6;
    private javax.swing.JRadioButton DH7;
    private javax.swing.JRadioButton DH8;
    private javax.swing.JRadioButton DH9;
    private javax.swing.JRadioButton EF1;
    private javax.swing.JRadioButton EF2;
    private javax.swing.JRadioButton EF3;
    private javax.swing.JRadioButton EF4;
    private javax.swing.JRadioButton EF5;
    private javax.swing.JRadioButton EF6;
    private javax.swing.JRadioButton EF7;
    private javax.swing.JRadioButton EF8;
    private javax.swing.JRadioButton EF9;
    private javax.swing.JRadioButton EG1;
    private javax.swing.JRadioButton EG2;
    private javax.swing.JRadioButton EG3;
    private javax.swing.JRadioButton EG4;
    private javax.swing.JRadioButton EG5;
    private javax.swing.JRadioButton EG6;
    private javax.swing.JRadioButton EG7;
    private javax.swing.JRadioButton EG8;
    private javax.swing.JRadioButton EG9;
    private javax.swing.JRadioButton EH1;
    private javax.swing.JRadioButton EH2;
    private javax.swing.JRadioButton EH3;
    private javax.swing.JRadioButton EH4;
    private javax.swing.JRadioButton EH5;
    private javax.swing.JRadioButton EH6;
    private javax.swing.JRadioButton EH7;
    private javax.swing.JRadioButton EH8;
    private javax.swing.JRadioButton EH9;
    private javax.swing.JLabel EV;
    private javax.swing.JRadioButton FG1;
    private javax.swing.JRadioButton FG2;
    private javax.swing.JRadioButton FG3;
    private javax.swing.JRadioButton FG4;
    private javax.swing.JRadioButton FG5;
    private javax.swing.JRadioButton FG6;
    private javax.swing.JRadioButton FG7;
    private javax.swing.JRadioButton FG8;
    private javax.swing.JRadioButton FG9;
    private javax.swing.JRadioButton FH1;
    private javax.swing.JRadioButton FH2;
    private javax.swing.JRadioButton FH3;
    private javax.swing.JRadioButton FH4;
    private javax.swing.JRadioButton FH5;
    private javax.swing.JRadioButton FH6;
    private javax.swing.JRadioButton FH7;
    private javax.swing.JRadioButton FH8;
    private javax.swing.JRadioButton FH9;
    private javax.swing.JRadioButton GH1;
    private javax.swing.JRadioButton GH2;
    private javax.swing.JRadioButton GH3;
    private javax.swing.JRadioButton GH4;
    private javax.swing.JRadioButton GH5;
    private javax.swing.JRadioButton GH6;
    private javax.swing.JRadioButton GH7;
    private javax.swing.JRadioButton GH8;
    private javax.swing.JRadioButton GH9;
    private javax.swing.JButton HapusPengguna;
    private javax.swing.JButton HapusVarietas;
    private javax.swing.JLabel KI;
    private javax.swing.JLabel KR;
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
    private javax.swing.JTable TableMatriks;
    private javax.swing.JTable TablePengguna;
    private javax.swing.JTable TableVarietas;
    private javax.swing.JSpinner Tinggi;
    private javax.swing.JButton UpdatePengguna;
    private javax.swing.JButton UpdateVarietas;
    private javax.swing.ButtonGroup ab;
    private javax.swing.ButtonGroup ac;
    private javax.swing.ButtonGroup ad;
    private javax.swing.ButtonGroup ae;
    private javax.swing.ButtonGroup af;
    private javax.swing.ButtonGroup ag;
    private javax.swing.ButtonGroup ah;
    private javax.swing.JPanel ahpProcess;
    private javax.swing.JTextField asalDaerahPengguna;
    private javax.swing.ButtonGroup bc;
    private javax.swing.ButtonGroup bd;
    private javax.swing.ButtonGroup be;
    private javax.swing.ButtonGroup bf;
    private javax.swing.ButtonGroup bg;
    private javax.swing.ButtonGroup bh;
    private javax.swing.ButtonGroup cd;
    private javax.swing.ButtonGroup ce;
    private javax.swing.ButtonGroup cf;
    private javax.swing.ButtonGroup cg;
    private javax.swing.ButtonGroup ch;
    private javax.swing.JButton chooseFile;
    private javax.swing.ButtonGroup de;
    private javax.swing.ButtonGroup df;
    private javax.swing.ButtonGroup dg;
    private javax.swing.ButtonGroup dh;
    private javax.swing.ButtonGroup ef;
    private javax.swing.ButtonGroup eg;
    private javax.swing.ButtonGroup eh;
    private javax.swing.ButtonGroup fg;
    private javax.swing.ButtonGroup fh;
    private javax.swing.JButton finish;
    private javax.swing.JLabel fotoField;
    private javax.swing.ButtonGroup gh;
    private javax.swing.ButtonGroup groupAB;
    private javax.swing.ButtonGroup groupAC;
    private javax.swing.ButtonGroup groupAD;
    private javax.swing.ButtonGroup groupAE;
    private javax.swing.ButtonGroup groupAF;
    private javax.swing.ButtonGroup groupAG;
    private javax.swing.ButtonGroup groupAH;
    private javax.swing.ButtonGroup groupBC;
    private javax.swing.ButtonGroup groupBD;
    private javax.swing.ButtonGroup groupBE;
    private javax.swing.ButtonGroup groupBF;
    private javax.swing.ButtonGroup groupBG;
    private javax.swing.ButtonGroup groupBH;
    private javax.swing.ButtonGroup groupCD;
    private javax.swing.ButtonGroup groupCE;
    private javax.swing.ButtonGroup groupCF;
    private javax.swing.ButtonGroup groupCG;
    private javax.swing.ButtonGroup groupCH;
    private javax.swing.ButtonGroup groupDE;
    private javax.swing.ButtonGroup groupDF;
    private javax.swing.ButtonGroup groupDG;
    private javax.swing.ButtonGroup groupDH;
    private javax.swing.ButtonGroup groupEF;
    private javax.swing.ButtonGroup groupEG;
    private javax.swing.ButtonGroup groupEH;
    private javax.swing.ButtonGroup groupFG;
    private javax.swing.ButtonGroup groupFH;
    private javax.swing.ButtonGroup groupGH;
    private javax.swing.JTextField grupField;
    private javax.swing.JButton helpButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel100;
    private javax.swing.JLabel jLabel101;
    private javax.swing.JLabel jLabel102;
    private javax.swing.JLabel jLabel103;
    private javax.swing.JLabel jLabel104;
    private javax.swing.JLabel jLabel105;
    private javax.swing.JLabel jLabel106;
    private javax.swing.JLabel jLabel107;
    private javax.swing.JLabel jLabel108;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
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
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JLabel jLabel72;
    private javax.swing.JLabel jLabel73;
    private javax.swing.JLabel jLabel75;
    private javax.swing.JLabel jLabel76;
    private javax.swing.JLabel jLabel79;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel80;
    private javax.swing.JLabel jLabel81;
    private javax.swing.JLabel jLabel82;
    private javax.swing.JLabel jLabel83;
    private javax.swing.JLabel jLabel86;
    private javax.swing.JLabel jLabel87;
    private javax.swing.JLabel jLabel88;
    private javax.swing.JLabel jLabel89;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabel90;
    private javax.swing.JLabel jLabel91;
    private javax.swing.JLabel jLabel92;
    private javax.swing.JLabel jLabel93;
    private javax.swing.JLabel jLabel94;
    private javax.swing.JLabel jLabel95;
    private javax.swing.JLabel jLabel96;
    private javax.swing.JLabel jLabel97;
    private javax.swing.JLabel jLabel98;
    private javax.swing.JLabel jLabel99;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
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
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator20;
    private javax.swing.JSeparator jSeparator21;
    private javax.swing.JSeparator jSeparator22;
    private javax.swing.JSeparator jSeparator23;
    private javax.swing.JSeparator jSeparator24;
    private javax.swing.JSeparator jSeparator25;
    private javax.swing.JSeparator jSeparator26;
    private javax.swing.JSeparator jSeparator27;
    private javax.swing.JSeparator jSeparator28;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JSeparator jSeparator9;
    private keeptoo.KButton kButton1;
    private javax.swing.JPanel kriteria_aktif;
    private javax.swing.JPanel matriks_aktif;
    private javax.swing.JPanel menu_kriteria;
    private javax.swing.JPanel menu_matriks;
    private javax.swing.JPanel menu_pengguna;
    private javax.swing.JPanel menu_varietas;
    private javax.swing.JTextField namaPengguna;
    private javax.swing.JTextField namaVarietas;
    private javax.swing.JPanel panel_kriteria;
    private javax.swing.JPanel panel_matriks;
    private javax.swing.JPanel panel_pengguna;
    private javax.swing.JPanel panel_varietas;
    private javax.swing.JTextField passwordPengguna;
    private javax.swing.JPanel pengguna_aktif;
    private javax.swing.JComboBox<String> rolePengguna;
    private javax.swing.JPanel side_bar;
    private javax.swing.JButton simpan;
    private javax.swing.JTextField usernamePengguna;
    private javax.swing.JPanel varietas_aktif;
    private javax.swing.JLabel welcomeText;
    // End of variables declaration//GEN-END:variables
}
