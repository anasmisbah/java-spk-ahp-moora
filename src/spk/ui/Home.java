/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spk.ui;

import java.awt.Color;
import java.awt.Component;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Enumeration;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.JDialog;
import javax.swing.JPanel;
import spk.data.Koneksi;
import spk.data.Pengguna;
import spk.data.Auth;
import spk.data.Kriteria;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import spk.data.MetodeAhp;
import spk.data.MetodeMoora;
import spk.data.Varietas;

/**
 *
 * @author anas
 */
public class Home extends javax.swing.JFrame {

    /**
     * Creates new form Home
     */
    private Connection con;
    private Statement stt;
    private ResultSet rss;
    private Pengguna penggunaLogin;
    Pengguna pengguna = Auth.penggunaLogin();
    Kriteria kriteria = new Kriteria();
    private DefaultTableModel model;
    MetodeAhp ahp = new MetodeAhp();
    double CI = 0;
    double CR = 0;
    double[] pVector;
    private Varietas varietas = new Varietas();
    MetodeMoora moora = new MetodeMoora();

    public Home() {
        con = Koneksi.getkoneksi();
        initComponents();

        String nama = pengguna.getNama();
        welcomeText.setText("Hai, " + nama);
        ArrayList<Double> bobot = kriteria.getPerbandinganKriteriaUser(pengguna.
                getId());

        if (bobot.isEmpty()) {

        } else {
            this.radioKriteria(bobot);
        }

        setColor(menu_kriteria);
        kriteria_aktif.setOpaque(true);
        resetColor(new JPanel[]{menu_matriks}, new JPanel[]{matriks_aktif});
        resetColor(new JPanel[]{menu_hasil}, new JPanel[]{hasil_aktif});
        panel_kriteria.setVisible(true);
        panel_hasil.setVisible(false);
        panel_matriks.setVisible(false);

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
            double[] bobots = new double[bobot.size()];
            for (int i = 0; i < bobot.size(); i++) {
                bobots[i] = bobot.get(i);
            }
            ahp.proses(pengguna.getId(), bobots);
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

    private void InitTableVarietas() {
        model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("Varietas");
        model.addColumn("Grup");
        model.addColumn("id_grup");
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
        TableVarietas.removeColumn(TableVarietas.getColumnModel().getColumn(2));
        TableVarietas.setRowHeight(30);
        TableColumnModel columnModel = TableVarietas.getColumnModel();
        for (int i = 0; i < 10; i++) {
            columnModel.getColumn(i).setPreferredWidth(150);
        }
    }

    private void TampilDataVarietas() {
        ArrayList<Varietas> variety = varietas.allVarietas();
        try {
            for (int i = 0; i < variety.size(); i++) {
                Object[] record = new Object[12];
                record[0] = variety.get(i).getId();
                record[1] = variety.get(i).getNama();
                record[2] = variety.get(i).getNamaGrup();
                record[3] = variety.get(i).getGroup_id();
                record[4] = variety.get(i).getRerataJumlahTandan();
                record[5] = variety.get(i).getRerataBeratTandan();
                record[6] = variety.get(i).getPotensiTBS();
                record[7] = variety.get(i).getRendemen();
                record[8] = variety.get(i).getPotensiCPO();
                record[9] = variety.get(i).getTinggi();
                record[10] = variety.get(i).getPanjangPelepah();
                record[11] = variety.get(i).getKerapatanTanam();
                model.addRow(record);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void InitTableTernormalisasi() {
        model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("Alternatif");
        model.addColumn("Grup");
        model.addColumn("id_grup");
        model.addColumn("Rerata Jumlah Tandan");
        model.addColumn("Rerata Berat Tandan");
        model.addColumn("Potensi TBS");
        model.addColumn("Rendemen");
        model.addColumn("Potensi CPO");
        model.addColumn("Tinggi");
        model.addColumn("Panjang Pelepah");
        model.addColumn("Kerapatan Tanam");

        TableTernormalisasi.setModel(model);
        TableTernormalisasi.removeColumn(TableTernormalisasi.getColumnModel().
                getColumn(0));
        TableTernormalisasi.removeColumn(TableTernormalisasi.getColumnModel().
                getColumn(2));
        TableTernormalisasi.setRowHeight(30);
        TableColumnModel columnModel = TableTernormalisasi.getColumnModel();
        for (int i = 0; i < 10; i++) {
            columnModel.getColumn(i).setPreferredWidth(150);
        }
    }

    private void TampilDataTernormalisasi() {
        ArrayList<Varietas> variety = varietas.allVarietas();
        ArrayList<Kriteria> allKriteria = kriteria.allKriteria();
        moora.proses(pengguna.getId());
        double[][] ternormalisasi = moora.getMatriksTernormalisasi();

        try {
            for (int i = 0; i < variety.size(); i++) {
                Object[] record = new Object[12];
                record[0] = variety.get(i).getId();
                record[1] = variety.get(i).getNama();
                record[2] = variety.get(i).getNamaGrup();
                record[3] = variety.get(i).getGroup_id();
                for (int j = 0; j < allKriteria.size(); j++) {
                    record[j + 4] = ternormalisasi[i][j];
                }
                model.addRow(record);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void InitTableRanking() {
        model = new DefaultTableModel();
        model.addColumn("Alternatif");
        model.addColumn("Rerata Jumlah Tandan");
        model.addColumn("Rerata Berat Tandan");
        model.addColumn("Potensi TBS");
        model.addColumn("Rendemen");
        model.addColumn("Potensi CPO");
        model.addColumn("Tinggi");
        model.addColumn("Panjang Pelepah");
        model.addColumn("Kerapatan Tanam");
        model.addColumn("Hasil Optimasi");

        TableRanking.setModel(model);
        TableRanking.setRowHeight(30);
        TableColumnModel columnModel = TableRanking.getColumnModel();
        for (int i = 0; i < 10; i++) {
            columnModel.getColumn(i).setPreferredWidth(150);
        }
    }

    private void TampilDataRanking() {
        ArrayList<Varietas> variety = varietas.allVarietas();
        ArrayList<Kriteria> allKriteria = kriteria.allKriteria();
        moora.proses(pengguna.getId());
        double[][] terbobot = moora.getMatriksTernormalisasiTerbobot();
        double[] optimasi = moora.getHasilOptimasi();

        try {

            Object[] firstRow = new Object[9];
            for (int j = 1; j < 9; j++) {
                firstRow[j] = allKriteria.get(j - 1).getTipe();
            }
            model.addRow(firstRow);

            Object[] secondRow = new Object[9];
            for (int j = 1; j < 9; j++) {
                secondRow[j] = pVector[j - 1];
            }
            model.addRow(secondRow);

            for (int i = 0; i < variety.size(); i++) {
                Object[] record = new Object[12];
                record[0] = variety.get(i).getNama();
                for (int j = 0; j < allKriteria.size(); j++) {
                    record[j + 1] = terbobot[i][j];
                }
                record[9] = optimasi[i];
                model.addRow(record);
            }
            TableRanking.setDefaultRenderer(Object.class,
                    new DefaultTableCellRenderer() {
                public Component getTableCellRendererComponent(JTable table,
                        Object value, boolean isSelected, boolean hasFocus,
                        int row,
                        int col) {

                    super.
                            getTableCellRendererComponent(table, value,
                                    isSelected,
                                    hasFocus, row, col);

                    if (row == 0) {
                        setBackground(Color.YELLOW);
                        setForeground(Color.BLACK);
                    } else if (row == 1) {
                        setBackground(Color.GRAY);
                        setForeground(Color.BLACK);
                    } else {
                        setBackground(table.getBackground());
                        setForeground(table.getForeground());
                    }
                    return this;
                }
            });
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
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

    public void radioKriteria(ArrayList<Double> bobot) {

        setButtonGroup(bobot.get(0).intValue(), groupAB.getElements());
        setButtonGroup(bobot.get(1).intValue(), groupAC.getElements());
        setButtonGroup(bobot.get(2).intValue(), groupAD.getElements());
        setButtonGroup(bobot.get(3).intValue(), groupAE.getElements());
        setButtonGroup(bobot.get(4).intValue(), groupAF.getElements());
        setButtonGroup(bobot.get(5).intValue(), groupAG.getElements());
        setButtonGroup(bobot.get(6).intValue(), groupAH.getElements());
        setButtonGroup(bobot.get(7).intValue(), groupBC.getElements());
        setButtonGroup(bobot.get(8).intValue(), groupBD.getElements());
        setButtonGroup(bobot.get(9).intValue(), groupBE.getElements());
        setButtonGroup(bobot.get(10).intValue(), groupBF.getElements());
        setButtonGroup(bobot.get(11).intValue(), groupBG.getElements());
        setButtonGroup(bobot.get(12).intValue(), groupBH.getElements());
        setButtonGroup(bobot.get(13).intValue(), groupCD.getElements());
        setButtonGroup(bobot.get(14).intValue(), groupCE.getElements());
        setButtonGroup(bobot.get(15).intValue(), groupCF.getElements());
        setButtonGroup(bobot.get(16).intValue(), groupCG.getElements());
        setButtonGroup(bobot.get(17).intValue(), groupCH.getElements());
        setButtonGroup(bobot.get(18).intValue(), groupDE.getElements());
        setButtonGroup(bobot.get(19).intValue(), groupDF.getElements());
        setButtonGroup(bobot.get(20).intValue(), groupDG.getElements());
        setButtonGroup(bobot.get(21).intValue(), groupDH.getElements());
        setButtonGroup(bobot.get(22).intValue(), groupEF.getElements());
        setButtonGroup(bobot.get(23).intValue(), groupEG.getElements());
        setButtonGroup(bobot.get(24).intValue(), groupEH.getElements());
        setButtonGroup(bobot.get(25).intValue(), groupFG.getElements());
        setButtonGroup(bobot.get(26).intValue(), groupFH.getElements());
        setButtonGroup(bobot.get(27).intValue(), groupGH.getElements());
    }

    public void setButtonGroup(int rdValue, Enumeration elements) {
        while (elements.hasMoreElements()) {
            AbstractButton button = (AbstractButton) elements.nextElement();
            if (rdValue == Integer.parseInt(button.getText())) {
                button.setSelected(true);
            }
        }
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
        side_bar = new javax.swing.JPanel();
        menu_matriks = new javax.swing.JPanel();
        matriks_aktif = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        menu_kriteria = new javax.swing.JPanel();
        kriteria_aktif = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        kButton1 = new keeptoo.KButton();
        menu_hasil = new javax.swing.JPanel();
        hasil_aktif = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        welcomeText = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        ContainerPanel = new javax.swing.JPanel();
        panel_kriteria = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        ahpProcess = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        AB1 = new javax.swing.JRadioButton();
        AB2 = new javax.swing.JRadioButton();
        AB3 = new javax.swing.JRadioButton();
        AB4 = new javax.swing.JRadioButton();
        AB5 = new javax.swing.JRadioButton();
        AB6 = new javax.swing.JRadioButton();
        AB7 = new javax.swing.JRadioButton();
        AB8 = new javax.swing.JRadioButton();
        AB9 = new javax.swing.JRadioButton();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
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
        jLabel16 = new javax.swing.JLabel();
        AD1 = new javax.swing.JRadioButton();
        AD2 = new javax.swing.JRadioButton();
        AD3 = new javax.swing.JRadioButton();
        AD4 = new javax.swing.JRadioButton();
        AD5 = new javax.swing.JRadioButton();
        AD6 = new javax.swing.JRadioButton();
        AD7 = new javax.swing.JRadioButton();
        AD8 = new javax.swing.JRadioButton();
        AD9 = new javax.swing.JRadioButton();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
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
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        AF1 = new javax.swing.JRadioButton();
        AF2 = new javax.swing.JRadioButton();
        AF3 = new javax.swing.JRadioButton();
        AF4 = new javax.swing.JRadioButton();
        AF5 = new javax.swing.JRadioButton();
        AF6 = new javax.swing.JRadioButton();
        AF7 = new javax.swing.JRadioButton();
        AF8 = new javax.swing.JRadioButton();
        AF9 = new javax.swing.JRadioButton();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
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
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        AH1 = new javax.swing.JRadioButton();
        AH2 = new javax.swing.JRadioButton();
        AH3 = new javax.swing.JRadioButton();
        AH4 = new javax.swing.JRadioButton();
        AH5 = new javax.swing.JRadioButton();
        AH6 = new javax.swing.JRadioButton();
        AH7 = new javax.swing.JRadioButton();
        AH8 = new javax.swing.JRadioButton();
        AH9 = new javax.swing.JRadioButton();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
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
        jLabel33 = new javax.swing.JLabel();
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
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
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
        jLabel40 = new javax.swing.JLabel();
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
        jLabel42 = new javax.swing.JLabel();
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
        jLabel48 = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
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
        jLabel53 = new javax.swing.JLabel();
        jLabel54 = new javax.swing.JLabel();
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
        jLabel58 = new javax.swing.JLabel();
        jLabel59 = new javax.swing.JLabel();
        jLabel60 = new javax.swing.JLabel();
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
        jLabel63 = new javax.swing.JLabel();
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
        jLabel68 = new javax.swing.JLabel();
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
        jLabel70 = new javax.swing.JLabel();
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
        jLabel74 = new javax.swing.JLabel();
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
        jLabel77 = new javax.swing.JLabel();
        jLabel78 = new javax.swing.JLabel();
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
        jLabel84 = new javax.swing.JLabel();
        jLabel85 = new javax.swing.JLabel();
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
        jLabel87 = new javax.swing.JLabel();
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
        jLabel92 = new javax.swing.JLabel();
        jSeparator28 = new javax.swing.JSeparator();
        jLabel8 = new javax.swing.JLabel();
        helpButton = new javax.swing.JButton();
        simpan = new javax.swing.JButton();
        panel_matriks = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        TableMatriks = new javax.swing.JTable();
        KI = new javax.swing.JLabel();
        KR = new javax.swing.JLabel();
        EV = new javax.swing.JLabel();
        finish = new javax.swing.JButton();
        panel_hasil = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jSeparator29 = new javax.swing.JSeparator();
        jScrollPane6 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel93 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        TableVarietas = new javax.swing.JTable();
        jLabel94 = new javax.swing.JLabel();
        jSeparator30 = new javax.swing.JSeparator();
        jScrollPane4 = new javax.swing.JScrollPane();
        TableRanking = new javax.swing.JTable();
        jLabel95 = new javax.swing.JLabel();
        jSeparator31 = new javax.swing.JSeparator();
        jScrollPane5 = new javax.swing.JScrollPane();
        TableTernormalisasi = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setResizable(false);
        addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                formPropertyChange(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        side_bar.setBackground(new java.awt.Color(23, 35, 51));
        side_bar.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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
                .addGap(80, 80, 80)
                .addComponent(jLabel2)
                .addGap(0, 79, Short.MAX_VALUE))
        );
        menu_matriksLayout.setVerticalGroup(
            menu_matriksLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(matriks_aktif, javax.swing.GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)
            .addGroup(menu_matriksLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        side_bar.add(menu_matriks, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 130, 210, -1));

        menu_kriteria.setBackground(new java.awt.Color(41, 57, 80));
        menu_kriteria.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                menu_kriteriaMousePressed(evt);
            }
        });

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

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Kriteria");

        javax.swing.GroupLayout menu_kriteriaLayout = new javax.swing.GroupLayout(menu_kriteria);
        menu_kriteria.setLayout(menu_kriteriaLayout);
        menu_kriteriaLayout.setHorizontalGroup(
            menu_kriteriaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menu_kriteriaLayout.createSequentialGroup()
                .addComponent(kriteria_aktif, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(81, 81, 81)
                .addComponent(jLabel3)
                .addGap(0, 81, Short.MAX_VALUE))
        );
        menu_kriteriaLayout.setVerticalGroup(
            menu_kriteriaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(kriteria_aktif, javax.swing.GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, menu_kriteriaLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addContainerGap())
        );

        side_bar.add(menu_kriteria, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 87, 210, -1));

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

        menu_hasil.setBackground(new java.awt.Color(23, 35, 51));
        menu_hasil.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                menu_hasilMousePressed(evt);
            }
        });

        hasil_aktif.setOpaque(false);
        hasil_aktif.setPreferredSize(new java.awt.Dimension(5, 40));

        javax.swing.GroupLayout hasil_aktifLayout = new javax.swing.GroupLayout(hasil_aktif);
        hasil_aktif.setLayout(hasil_aktifLayout);
        hasil_aktifLayout.setHorizontalGroup(
            hasil_aktifLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 5, Short.MAX_VALUE)
        );
        hasil_aktifLayout.setVerticalGroup(
            hasil_aktifLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Hasil");

        javax.swing.GroupLayout menu_hasilLayout = new javax.swing.GroupLayout(menu_hasil);
        menu_hasil.setLayout(menu_hasilLayout);
        menu_hasilLayout.setHorizontalGroup(
            menu_hasilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menu_hasilLayout.createSequentialGroup()
                .addComponent(hasil_aktif, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(88, 88, 88)
                .addComponent(jLabel4)
                .addContainerGap(88, Short.MAX_VALUE))
        );
        menu_hasilLayout.setVerticalGroup(
            menu_hasilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(hasil_aktif, javax.swing.GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, menu_hasilLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addContainerGap())
        );

        side_bar.add(menu_hasil, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 170, 210, -1));

        welcomeText.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        welcomeText.setForeground(new java.awt.Color(255, 255, 255));
        welcomeText.setText("Hai, ");
        side_bar.add(welcomeText, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));

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
                .addContainerGap(24, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 0, 980, 50));

        ContainerPanel.setBackground(new java.awt.Color(255, 255, 255));
        ContainerPanel.setLayout(new java.awt.CardLayout());

        panel_kriteria.setBackground(new java.awt.Color(204, 204, 204));

        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setLabelFor(panel_kriteria);
        jLabel5.setText("KRITERIA");
        jLabel5.setFocusable(false);

        ahpProcess.setBackground(new java.awt.Color(204, 204, 204));
        ahpProcess.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "AHP Process", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP));
        ahpProcess.setForeground(new java.awt.Color(204, 204, 204));
        ahpProcess.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel10.setText("Rerata Jumlah Tandan");
        ahpProcess.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 11, -1, 39));

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel9.setText("Rerata Berat Tandan");
        ahpProcess.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 10, -1, 39));

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
        ahpProcess.add(AB1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, -1, -1));

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
        ahpProcess.add(AB2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 40, -1, -1));

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
        ahpProcess.add(AB3, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 40, -1, -1));

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
        ahpProcess.add(AB4, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 40, -1, -1));

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
        ahpProcess.add(AB5, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 40, -1, -1));

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
        ahpProcess.add(AB6, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 40, -1, -1));

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
        ahpProcess.add(AB7, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 40, -1, -1));

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
        ahpProcess.add(AB8, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 40, -1, -1));

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
        ahpProcess.add(AB9, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 40, -1, -1));

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel11.setText("=>");
        ahpProcess.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 20, -1, -1));

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel12.setText("Rerata Jumlah Tandan");
        ahpProcess.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 10, -1, 39));

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel13.setText("Potensi TBS");
        ahpProcess.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 10, -1, 39));

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel14.setText("=>");
        ahpProcess.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 20, -1, -1));

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
        ahpProcess.add(AC1, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 40, -1, -1));

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
        ahpProcess.add(AC2, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 40, -1, -1));

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
        ahpProcess.add(AC3, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 40, -1, -1));

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
        ahpProcess.add(AC4, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 40, -1, -1));

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
        ahpProcess.add(AC5, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 40, -1, -1));

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
        ahpProcess.add(AC6, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 40, -1, -1));

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
        ahpProcess.add(AC7, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 40, -1, -1));

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
        ahpProcess.add(AC8, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 40, -1, -1));

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
        ahpProcess.add(AC9, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 40, -1, -1));

        jSeparator2.setBackground(new java.awt.Color(0, 0, 0));
        ahpProcess.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 142, 880, 10));

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel15.setText("Rerata Jumlah Tandan");
        ahpProcess.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 140, -1, 39));

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel16.setText("Rendemen");
        ahpProcess.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 140, -1, 39));

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
        ahpProcess.add(AD1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 170, -1, -1));

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
        ahpProcess.add(AD2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 170, -1, -1));

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
        ahpProcess.add(AD3, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 170, -1, -1));

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
        ahpProcess.add(AD4, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 170, -1, -1));

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
        ahpProcess.add(AD5, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 170, -1, -1));

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
        ahpProcess.add(AD6, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 170, -1, -1));

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
        ahpProcess.add(AD7, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 170, -1, -1));

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
        ahpProcess.add(AD8, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 170, -1, -1));

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
        ahpProcess.add(AD9, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 170, -1, -1));

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel17.setText("=>");
        ahpProcess.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 150, -1, -1));

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel18.setText("Rerata Jumlah Tandan");
        ahpProcess.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 140, -1, 39));

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel19.setText("Potensi CPO");
        ahpProcess.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 140, -1, 39));

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel20.setText("=>");
        ahpProcess.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 150, -1, -1));

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
        ahpProcess.add(AE1, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 170, -1, -1));

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
        ahpProcess.add(AE2, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 170, -1, -1));

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
        ahpProcess.add(AE3, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 170, -1, -1));

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
        ahpProcess.add(AE4, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 170, -1, -1));

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
        ahpProcess.add(AE5, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 170, -1, -1));

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
        ahpProcess.add(AE6, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 170, -1, -1));

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
        ahpProcess.add(AE7, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 170, -1, -1));

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
        ahpProcess.add(AE8, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 170, -1, -1));

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
        ahpProcess.add(AE9, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 170, -1, -1));

        jSeparator4.setBackground(new java.awt.Color(0, 0, 0));
        ahpProcess.add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 270, 880, 10));

        jLabel21.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel21.setText("Rerata Jumlah Tandan");
        ahpProcess.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 270, -1, 39));

        jLabel22.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel22.setText("Tinggi");
        ahpProcess.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 270, -1, 39));

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
        ahpProcess.add(AF1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 300, -1, -1));

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
        ahpProcess.add(AF2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 300, -1, -1));

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
        ahpProcess.add(AF3, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 300, -1, -1));

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
        ahpProcess.add(AF4, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 300, -1, -1));

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
        ahpProcess.add(AF5, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 300, -1, -1));

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
        ahpProcess.add(AF6, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 300, -1, -1));

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
        ahpProcess.add(AF7, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 300, -1, -1));

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
        ahpProcess.add(AF8, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 300, -1, -1));

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
        ahpProcess.add(AF9, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 300, -1, -1));

        jLabel23.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel23.setText("=>");
        ahpProcess.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 280, -1, -1));

        jLabel24.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel24.setText("Rerata Jumlah Tandan");
        ahpProcess.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 270, -1, 39));

        jLabel25.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel25.setText("Panjang Pelepah");
        ahpProcess.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 270, -1, 39));

        jLabel26.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel26.setText("=>");
        ahpProcess.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 280, -1, -1));

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
        ahpProcess.add(AG1, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 300, -1, -1));

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
        ahpProcess.add(AG2, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 300, -1, -1));

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
        ahpProcess.add(AG3, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 300, -1, -1));

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
        ahpProcess.add(AG4, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 300, -1, -1));

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
        ahpProcess.add(AG5, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 300, -1, -1));

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
        ahpProcess.add(AG6, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 300, -1, -1));

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
        ahpProcess.add(AG7, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 300, -1, -1));

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
        ahpProcess.add(AG8, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 300, -1, -1));

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
        ahpProcess.add(AG9, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 300, -1, -1));

        jSeparator6.setBackground(new java.awt.Color(0, 0, 0));
        ahpProcess.add(jSeparator6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 400, 880, 10));

        jLabel27.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel27.setText("Rerata Jumlah Tandan");
        ahpProcess.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 400, -1, 39));

        jLabel28.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel28.setText("Kerapatan Tanam");
        ahpProcess.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 400, -1, 39));

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
        ahpProcess.add(AH1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 430, -1, -1));

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
        ahpProcess.add(AH2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 430, -1, -1));

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
        ahpProcess.add(AH3, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 430, -1, -1));

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
        ahpProcess.add(AH4, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 430, -1, -1));

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
        ahpProcess.add(AH5, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 430, -1, -1));

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
        ahpProcess.add(AH6, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 430, -1, -1));

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
        ahpProcess.add(AH7, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 430, -1, -1));

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
        ahpProcess.add(AH8, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 430, -1, -1));

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
        ahpProcess.add(AH9, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 430, -1, -1));

        jLabel29.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel29.setText("=>");
        ahpProcess.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 410, -1, -1));

        jLabel30.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel30.setText("Rerata Berat Tandan");
        ahpProcess.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 400, -1, 39));

        jLabel31.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel31.setText("Potensi TBS");
        ahpProcess.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 400, -1, 39));

        jLabel32.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel32.setText("=>");
        ahpProcess.add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 410, -1, -1));

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
        ahpProcess.add(BC1, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 430, -1, -1));

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
        ahpProcess.add(BC2, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 430, -1, -1));

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
        ahpProcess.add(BC3, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 430, -1, -1));

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
        ahpProcess.add(BC4, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 430, -1, -1));

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
        ahpProcess.add(BC5, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 430, -1, -1));

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
        ahpProcess.add(BC6, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 430, -1, -1));

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
        ahpProcess.add(BC7, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 430, -1, -1));

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
        ahpProcess.add(BC8, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 430, -1, -1));

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
        ahpProcess.add(BC9, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 430, -1, -1));

        jSeparator8.setBackground(new java.awt.Color(0, 0, 0));
        ahpProcess.add(jSeparator8, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 530, 880, 10));

        jLabel33.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel33.setText("Rerata Berat Tandan");
        ahpProcess.add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 530, -1, 39));

        jLabel34.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel34.setText("Rendemen");
        ahpProcess.add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 530, -1, 39));

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
        ahpProcess.add(BD1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 560, -1, -1));

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
        ahpProcess.add(BD2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 560, -1, -1));

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
        ahpProcess.add(BD3, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 560, -1, -1));

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
        ahpProcess.add(BD4, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 560, -1, -1));

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
        ahpProcess.add(BD5, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 560, -1, -1));

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
        ahpProcess.add(BD6, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 560, -1, -1));

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
        ahpProcess.add(BD7, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 560, -1, -1));

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
        ahpProcess.add(BD8, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 560, -1, -1));

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
        ahpProcess.add(BD9, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 560, -1, -1));

        jLabel35.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel35.setText("=>");
        ahpProcess.add(jLabel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 540, -1, -1));

        jLabel36.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel36.setText("Rerata Berat Tandan");
        ahpProcess.add(jLabel36, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 530, -1, 39));

        jLabel37.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel37.setText("Potensi CPO");
        ahpProcess.add(jLabel37, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 530, -1, 39));

        jLabel38.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel38.setText("=>");
        ahpProcess.add(jLabel38, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 540, -1, -1));

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
        ahpProcess.add(BE1, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 560, -1, -1));

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
        ahpProcess.add(BE2, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 560, -1, -1));

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
        ahpProcess.add(BE3, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 560, -1, -1));

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
        ahpProcess.add(BE4, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 560, -1, -1));

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
        ahpProcess.add(BE5, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 560, -1, -1));

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
        ahpProcess.add(BE6, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 560, -1, -1));

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
        ahpProcess.add(BE7, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 560, -1, -1));

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
        ahpProcess.add(BE8, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 560, -1, -1));

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
        ahpProcess.add(BE9, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 560, -1, -1));

        jSeparator10.setBackground(new java.awt.Color(0, 0, 0));
        ahpProcess.add(jSeparator10, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 660, 880, 10));

        jLabel39.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel39.setText("Tinggi");
        ahpProcess.add(jLabel39, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 660, -1, 39));

        jLabel40.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel40.setText("=>");
        ahpProcess.add(jLabel40, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 670, -1, -1));

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
        ahpProcess.add(BF1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 690, -1, -1));

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
        ahpProcess.add(BF2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 690, -1, -1));

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
        ahpProcess.add(BF3, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 690, -1, -1));

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
        ahpProcess.add(BF4, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 690, -1, -1));

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
        ahpProcess.add(BF5, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 690, -1, -1));

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
        ahpProcess.add(BF6, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 690, -1, -1));

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
        ahpProcess.add(BF7, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 690, -1, -1));

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
        ahpProcess.add(BF8, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 690, -1, -1));

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
        ahpProcess.add(BF9, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 690, -1, -1));

        jSeparator12.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator12.setOrientation(javax.swing.SwingConstants.VERTICAL);
        ahpProcess.add(jSeparator12, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 670, 10, 120));

        jLabel41.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel41.setText("Rerata Berat Tandan");
        ahpProcess.add(jLabel41, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 660, -1, 39));

        jLabel42.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel42.setText("=>");
        ahpProcess.add(jLabel42, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 670, -1, -1));

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
        ahpProcess.add(BG1, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 690, -1, -1));

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
        ahpProcess.add(BG2, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 690, -1, -1));

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
        ahpProcess.add(BG3, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 690, -1, -1));

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
        ahpProcess.add(BG4, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 690, -1, -1));

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
        ahpProcess.add(BG5, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 690, -1, -1));

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
        ahpProcess.add(BG6, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 690, -1, -1));

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
        ahpProcess.add(BG7, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 690, -1, -1));

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
        ahpProcess.add(BG8, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 690, -1, -1));

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
        ahpProcess.add(BG9, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 690, -1, -1));

        jSeparator13.setBackground(new java.awt.Color(0, 0, 0));
        ahpProcess.add(jSeparator13, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 920, 880, 10));

        jLabel43.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel43.setText("Rerata Berat Tandan");
        ahpProcess.add(jLabel43, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 660, -1, 39));

        jLabel44.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel44.setText("Panjang Pelepah");
        ahpProcess.add(jLabel44, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 660, -1, 39));

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
        ahpProcess.add(BH1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 820, -1, -1));

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
        ahpProcess.add(BH2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 820, -1, -1));

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
        ahpProcess.add(BH3, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 820, -1, -1));

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
        ahpProcess.add(BH4, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 820, -1, -1));

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
        ahpProcess.add(BH5, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 820, -1, -1));

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
        ahpProcess.add(BH6, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 820, -1, -1));

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
        ahpProcess.add(BH7, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 820, -1, -1));

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
        ahpProcess.add(BH8, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 820, -1, -1));

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
        ahpProcess.add(BH9, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 820, -1, -1));

        jLabel45.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel45.setText("Kerapatan Tanam");
        ahpProcess.add(jLabel45, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 790, -1, 39));

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
        ahpProcess.add(CD1, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 820, -1, -1));

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
        ahpProcess.add(CD2, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 820, -1, -1));

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
        ahpProcess.add(CD3, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 820, -1, -1));

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
        ahpProcess.add(CD4, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 820, -1, -1));

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
        ahpProcess.add(CD5, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 820, -1, -1));

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
        ahpProcess.add(CD6, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 820, -1, -1));

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
        ahpProcess.add(CD7, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 820, -1, -1));

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
        ahpProcess.add(CD8, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 820, -1, -1));

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
        ahpProcess.add(CD9, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 820, -1, -1));

        jLabel46.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel46.setText("Rerata Berat Tandan");
        ahpProcess.add(jLabel46, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 790, -1, 39));

        jLabel47.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel47.setText("Potensi TBS");
        ahpProcess.add(jLabel47, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 790, -1, 39));

        jSeparator14.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator14.setOrientation(javax.swing.SwingConstants.VERTICAL);
        ahpProcess.add(jSeparator14, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 800, 10, 120));

        jLabel48.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel48.setText("=>");
        ahpProcess.add(jLabel48, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 800, -1, -1));

        jLabel49.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel49.setText("=>");
        ahpProcess.add(jLabel49, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 800, -1, -1));

        jLabel50.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel50.setText("Rendemen");
        ahpProcess.add(jLabel50, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 790, -1, 39));

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
        ahpProcess.add(CE1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 950, -1, -1));

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
        ahpProcess.add(CE2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 950, -1, -1));

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
        ahpProcess.add(CE3, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 950, -1, -1));

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
        ahpProcess.add(CE4, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 950, -1, -1));

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
        ahpProcess.add(CE5, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 950, -1, -1));

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
        ahpProcess.add(CE6, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 950, -1, -1));

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
        ahpProcess.add(CE7, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 950, -1, -1));

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
        ahpProcess.add(CE8, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 950, -1, -1));

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
        ahpProcess.add(CE9, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 950, -1, -1));

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
        ahpProcess.add(CF1, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 950, -1, -1));

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
        ahpProcess.add(CF2, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 950, -1, -1));

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
        ahpProcess.add(CF3, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 950, -1, -1));

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
        ahpProcess.add(CF4, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 950, -1, -1));

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
        ahpProcess.add(CF5, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 950, -1, -1));

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
        ahpProcess.add(CF6, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 950, -1, -1));

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
        ahpProcess.add(CF7, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 950, -1, -1));

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
        ahpProcess.add(CF8, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 950, -1, -1));

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
        ahpProcess.add(CF9, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 950, -1, -1));

        jLabel51.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel51.setText("Potensi CPO");
        ahpProcess.add(jLabel51, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 920, -1, 39));

        jLabel52.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel52.setText("Potensi TBS");
        ahpProcess.add(jLabel52, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 920, -1, 39));

        jLabel53.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel53.setText("=>");
        ahpProcess.add(jLabel53, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 930, -1, -1));

        jLabel54.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel54.setText("=>");
        ahpProcess.add(jLabel54, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 930, -1, -1));

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
        ahpProcess.add(CG1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 1080, -1, -1));

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
        ahpProcess.add(CG2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 1080, -1, -1));

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
        ahpProcess.add(CG3, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 1080, -1, -1));

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
        ahpProcess.add(CG4, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 1080, -1, -1));

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
        ahpProcess.add(CG5, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 1080, -1, -1));

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
        ahpProcess.add(CG6, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 1080, -1, -1));

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
        ahpProcess.add(CG7, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 1080, -1, -1));

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
        ahpProcess.add(CG8, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 1080, -1, -1));

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
        ahpProcess.add(CG9, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 1080, -1, -1));

        jLabel55.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel55.setText("Potensi TBS");
        ahpProcess.add(jLabel55, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 920, -1, 39));

        jLabel56.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel56.setText("Tinggi");
        ahpProcess.add(jLabel56, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 920, -1, 39));

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
        ahpProcess.add(CH1, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 1080, -1, -1));

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
        ahpProcess.add(CH2, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 1080, -1, -1));

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
        ahpProcess.add(CH3, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 1080, -1, -1));

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
        ahpProcess.add(CH4, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 1080, -1, -1));

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
        ahpProcess.add(CH5, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 1080, -1, -1));

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
        ahpProcess.add(CH6, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 1080, -1, -1));

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
        ahpProcess.add(CH7, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 1080, -1, -1));

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
        ahpProcess.add(CH8, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 1080, -1, -1));

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
        ahpProcess.add(CH9, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 1080, -1, -1));

        jLabel57.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel57.setText("Panjang Pelepah");
        ahpProcess.add(jLabel57, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 1050, -1, 39));

        jLabel58.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel58.setText("=>");
        ahpProcess.add(jLabel58, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 1060, -1, -1));

        jLabel59.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel59.setText("Potensi TBS");
        ahpProcess.add(jLabel59, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 1050, -1, 39));

        jLabel60.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel60.setText("=>");
        ahpProcess.add(jLabel60, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 1060, -1, -1));

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
        ahpProcess.add(DE1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 1210, -1, -1));

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
        ahpProcess.add(DE2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 1210, -1, -1));

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
        ahpProcess.add(DE3, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 1210, -1, -1));

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
        ahpProcess.add(DE4, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 1210, -1, -1));

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
        ahpProcess.add(DE5, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 1210, -1, -1));

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
        ahpProcess.add(DE6, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 1210, -1, -1));

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
        ahpProcess.add(DE7, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 1210, -1, -1));

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
        ahpProcess.add(DE8, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 1210, -1, -1));

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
        ahpProcess.add(DE9, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 1210, -1, -1));

        jLabel61.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel61.setText("Potensi TBS");
        ahpProcess.add(jLabel61, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 1050, -1, 39));

        jLabel62.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel62.setText("Kerapatan Tanam");
        ahpProcess.add(jLabel62, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 1050, -1, 39));

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
        ahpProcess.add(DF1, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 1210, -1, -1));

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
        ahpProcess.add(DF2, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 1210, -1, -1));

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
        ahpProcess.add(DF3, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 1210, -1, -1));

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
        ahpProcess.add(DF4, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 1210, -1, -1));

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
        ahpProcess.add(DF5, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 1210, -1, -1));

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
        ahpProcess.add(DF6, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 1210, -1, -1));

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
        ahpProcess.add(DF7, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 1210, -1, -1));

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
        ahpProcess.add(DF8, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 1210, -1, -1));

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
        ahpProcess.add(DF9, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 1210, -1, -1));

        jLabel63.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel63.setText("=>");
        ahpProcess.add(jLabel63, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 1190, -1, -1));

        jSeparator19.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator19.setOrientation(javax.swing.SwingConstants.VERTICAL);
        ahpProcess.add(jSeparator19, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 1190, 10, 120));

        jLabel64.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel64.setText("Potensi CPO");
        ahpProcess.add(jLabel64, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 1180, -1, 39));

        jLabel65.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel65.setText("Tinggi");
        ahpProcess.add(jLabel65, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 1180, -1, 39));

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
        ahpProcess.add(DG1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 1340, -1, -1));

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
        ahpProcess.add(DG2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 1340, -1, -1));

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
        ahpProcess.add(DG3, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 1340, -1, -1));

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
        ahpProcess.add(DG4, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 1340, -1, -1));

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
        ahpProcess.add(DG5, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 1340, -1, -1));

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
        ahpProcess.add(DG6, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 1340, -1, -1));

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
        ahpProcess.add(DG7, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 1340, -1, -1));

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
        ahpProcess.add(DG8, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 1340, -1, -1));

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
        ahpProcess.add(DG9, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 1340, -1, -1));

        jLabel66.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel66.setText("Rendemen");
        ahpProcess.add(jLabel66, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 1180, -1, 39));

        jLabel67.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel67.setText("Rendemen");
        ahpProcess.add(jLabel67, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 1180, -1, 39));

        jLabel68.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel68.setText("=>");
        ahpProcess.add(jLabel68, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 1190, -1, -1));

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
        ahpProcess.add(DH1, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 1340, -1, -1));

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
        ahpProcess.add(DH2, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 1340, -1, -1));

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
        ahpProcess.add(DH3, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 1340, -1, -1));

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
        ahpProcess.add(DH4, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 1340, -1, -1));

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
        ahpProcess.add(DH5, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 1340, -1, -1));

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
        ahpProcess.add(DH6, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 1340, -1, -1));

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
        ahpProcess.add(DH7, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 1340, -1, -1));

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
        ahpProcess.add(DH8, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 1340, -1, -1));

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
        ahpProcess.add(DH9, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 1340, -1, -1));

        jLabel69.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel69.setText("Rendemen");
        ahpProcess.add(jLabel69, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 1310, -1, 39));

        jLabel70.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel70.setText("=>");
        ahpProcess.add(jLabel70, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 1320, -1, -1));

        jLabel71.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel71.setText("Rendemen");
        ahpProcess.add(jLabel71, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 1310, -1, 39));

        jLabel72.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel72.setText("Kerapatan Tanam");
        ahpProcess.add(jLabel72, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 1310, -1, 39));

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
        ahpProcess.add(EF1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 1470, -1, -1));

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
        ahpProcess.add(EF2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 1470, -1, -1));

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
        ahpProcess.add(EF3, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 1470, -1, -1));

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
        ahpProcess.add(EF4, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 1470, -1, -1));

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
        ahpProcess.add(EF5, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 1470, -1, -1));

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
        ahpProcess.add(EF6, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 1470, -1, -1));

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
        ahpProcess.add(EF7, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 1470, -1, -1));

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
        ahpProcess.add(EF8, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 1470, -1, -1));

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
        ahpProcess.add(EF9, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 1470, -1, -1));

        jLabel73.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel73.setText("Panjang Pelepah");
        ahpProcess.add(jLabel73, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 1310, -1, 39));

        jLabel74.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel74.setText("=>");
        ahpProcess.add(jLabel74, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 1320, -1, -1));

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
        ahpProcess.add(EG1, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 1470, -1, -1));

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
        ahpProcess.add(EG2, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 1470, -1, -1));

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
        ahpProcess.add(EG3, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 1470, -1, -1));

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
        ahpProcess.add(EG4, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 1470, -1, -1));

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
        ahpProcess.add(EG5, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 1470, -1, -1));

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
        ahpProcess.add(EG6, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 1470, -1, -1));

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
        ahpProcess.add(EG7, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 1470, -1, -1));

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
        ahpProcess.add(EG8, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 1470, -1, -1));

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
        ahpProcess.add(EG9, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 1470, -1, -1));

        jLabel75.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel75.setText("Potensi CPO");
        ahpProcess.add(jLabel75, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 1440, -1, 39));

        jLabel76.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel76.setText("Tinggi");
        ahpProcess.add(jLabel76, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 1440, -1, 39));

        jSeparator23.setBackground(new java.awt.Color(0, 0, 0));
        ahpProcess.add(jSeparator23, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 1570, 880, 10));

        jLabel77.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel77.setText("=>");
        ahpProcess.add(jLabel77, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 1450, -1, -1));

        jLabel78.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel78.setText("=>");
        ahpProcess.add(jLabel78, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 1450, -1, -1));

        jLabel79.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel79.setText("Potensi CPO");
        ahpProcess.add(jLabel79, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 1440, -1, 39));

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
        ahpProcess.add(EH1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 1600, -1, -1));

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
        ahpProcess.add(EH2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 1600, -1, -1));

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
        ahpProcess.add(EH3, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 1600, -1, -1));

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
        ahpProcess.add(EH4, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 1600, -1, -1));

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
        ahpProcess.add(EH5, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 1600, -1, -1));

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
        ahpProcess.add(EH6, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 1600, -1, -1));

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
        ahpProcess.add(EH7, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 1600, -1, -1));

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
        ahpProcess.add(EH8, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 1600, -1, -1));

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
        ahpProcess.add(EH9, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 1600, -1, -1));

        jLabel80.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel80.setText("Panjang Pelepah");
        ahpProcess.add(jLabel80, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 1440, -1, 39));

        jLabel81.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel81.setText("Panjang Pelepah");
        ahpProcess.add(jLabel81, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 1570, -1, 39));

        jLabel82.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel82.setText("Kerapatan Tanam");
        ahpProcess.add(jLabel82, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 1570, -1, 39));

        jLabel83.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel83.setText("Tinggi");
        ahpProcess.add(jLabel83, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 1570, -1, 39));

        jLabel84.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel84.setText("=>");
        ahpProcess.add(jLabel84, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 1580, -1, -1));

        jLabel85.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel85.setText("=>");
        ahpProcess.add(jLabel85, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 1580, -1, -1));

        jLabel86.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel86.setText("Potensi CPO");
        ahpProcess.add(jLabel86, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 1570, -1, 39));

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
        ahpProcess.add(FG1, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 1600, -1, -1));

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
        ahpProcess.add(FG2, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 1600, -1, -1));

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
        ahpProcess.add(FG3, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 1600, -1, -1));

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
        ahpProcess.add(FG4, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 1600, -1, -1));

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
        ahpProcess.add(FG5, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 1600, -1, -1));

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
        ahpProcess.add(FG6, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 1600, -1, -1));

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
        ahpProcess.add(FG7, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 1600, -1, -1));

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
        ahpProcess.add(FG8, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 1600, -1, -1));

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
        ahpProcess.add(FG9, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 1600, -1, -1));

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
        ahpProcess.add(FH1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 1730, -1, -1));

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
        ahpProcess.add(FH2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 1730, -1, -1));

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
        ahpProcess.add(FH3, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 1730, -1, -1));

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
        ahpProcess.add(FH4, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 1730, -1, -1));

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
        ahpProcess.add(FH5, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 1730, -1, -1));

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
        ahpProcess.add(FH6, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 1730, -1, -1));

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
        ahpProcess.add(FH7, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 1730, -1, -1));

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
        ahpProcess.add(FH8, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 1730, -1, -1));

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
        ahpProcess.add(FH9, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 1730, -1, -1));

        jLabel87.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel87.setText("=>");
        ahpProcess.add(jLabel87, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 1710, -1, -1));

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
        ahpProcess.add(GH1, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 1730, -1, -1));

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
        ahpProcess.add(GH2, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 1730, -1, -1));

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
        ahpProcess.add(GH3, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 1730, -1, -1));

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
        ahpProcess.add(GH4, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 1730, -1, -1));

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
        ahpProcess.add(GH5, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 1730, -1, -1));

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
        ahpProcess.add(GH6, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 1730, -1, -1));

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
        ahpProcess.add(GH7, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 1730, -1, -1));

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
        ahpProcess.add(GH8, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 1730, -1, -1));

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
        ahpProcess.add(GH9, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 1730, -1, -1));

        jLabel88.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel88.setText("Panjang Pelepah");
        ahpProcess.add(jLabel88, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 1700, -1, 39));

        jLabel89.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel89.setText("Tinggi");
        ahpProcess.add(jLabel89, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 1700, -1, 39));

        jSeparator27.setBackground(new java.awt.Color(0, 0, 0));
        ahpProcess.add(jSeparator27, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 1830, 880, 10));

        jLabel90.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel90.setText("Kerapatan Tanam");
        ahpProcess.add(jLabel90, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 1700, -1, 39));

        jLabel91.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel91.setText("Kerapatan Tanam");
        ahpProcess.add(jLabel91, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 1700, -1, 39));

        jLabel92.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel92.setText("=>");
        ahpProcess.add(jLabel92, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 1710, -1, -1));

        jSeparator28.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator28.setOrientation(javax.swing.SwingConstants.VERTICAL);
        ahpProcess.add(jSeparator28, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 1710, 10, 120));

        jScrollPane1.setViewportView(ahpProcess);

        jLabel8.setText("Deskripsi");

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
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 900, Short.MAX_VALUE)
            .addGroup(panel_kriteriaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel_kriteriaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(jLabel8))
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
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 45, Short.MAX_VALUE)
                .addComponent(helpButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 391, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(simpan, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25))
        );

        ContainerPanel.add(panel_kriteria, "card2");

        panel_matriks.setBackground(new java.awt.Color(204, 204, 204));
        panel_matriks.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                panel_matriksComponentShown(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setLabelFor(panel_matriks);
        jLabel6.setText("MATRIKS");
        jLabel6.setFocusable(false);

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

        KI.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        KI.setText("Konsistensi Index = ");

        KR.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        KR.setText("Konsistensi Ratio = ");

        EV.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        EV.setText("Eigen Value =");

        finish.setText("jButton1");
        finish.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                finishActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panel_matriksLayout = new javax.swing.GroupLayout(panel_matriks);
        panel_matriks.setLayout(panel_matriksLayout);
        panel_matriksLayout.setHorizontalGroup(
            panel_matriksLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_matriksLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel_matriksLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 880, Short.MAX_VALUE)
                    .addGroup(panel_matriksLayout.createSequentialGroup()
                        .addGroup(panel_matriksLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(finish, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6)
                            .addComponent(KR)
                            .addComponent(KI)
                            .addComponent(EV))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        panel_matriksLayout.setVerticalGroup(
            panel_matriksLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_matriksLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 65, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(EV)
                .addGap(18, 18, 18)
                .addComponent(KI)
                .addGap(18, 18, 18)
                .addComponent(KR)
                .addGap(18, 18, 18)
                .addComponent(finish, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24))
        );

        ContainerPanel.add(panel_matriks, "card2");

        panel_hasil.setBackground(new java.awt.Color(204, 204, 204));
        panel_hasil.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                panel_hasilComponentShown(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setLabelFor(panel_hasil);
        jLabel7.setText("HASIL");
        jLabel7.setFocusable(false);

        jSeparator29.setForeground(new java.awt.Color(0, 0, 0));

        jLabel93.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel93.setText("Varietas");

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
        TableVarietas.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        TableVarietas.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        TableVarietas.getTableHeader().setResizingAllowed(false);
        TableVarietas.getTableHeader().setReorderingAllowed(false);
        jScrollPane3.setViewportView(TableVarietas);

        jLabel94.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel94.setText("Normalisasi");

        jSeparator30.setForeground(new java.awt.Color(0, 0, 0));

        TableRanking.setModel(new javax.swing.table.DefaultTableModel(
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
        TableRanking.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        TableRanking.getTableHeader().setResizingAllowed(false);
        TableRanking.getTableHeader().setReorderingAllowed(false);
        jScrollPane4.setViewportView(TableRanking);

        jLabel95.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel95.setText("Ranking");

        jSeparator31.setForeground(new java.awt.Color(0, 0, 0));

        TableTernormalisasi.setModel(new javax.swing.table.DefaultTableModel(
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
        TableTernormalisasi.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        TableTernormalisasi.getTableHeader().setResizingAllowed(false);
        TableTernormalisasi.getTableHeader().setReorderingAllowed(false);
        jScrollPane5.setViewportView(TableTernormalisasi);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator30, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jSeparator31, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 845, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel93)
                            .addComponent(jLabel94)
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 841, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel95)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 841, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(jLabel93, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator30, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel94, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator31, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel95, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jScrollPane6.setViewportView(jPanel1);

        javax.swing.GroupLayout panel_hasilLayout = new javax.swing.GroupLayout(panel_hasil);
        panel_hasil.setLayout(panel_hasilLayout);
        panel_hasilLayout.setHorizontalGroup(
            panel_hasilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_hasilLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel_hasilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator29)
                    .addGroup(panel_hasilLayout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 880, Short.MAX_VALUE))
                .addContainerGap())
        );
        panel_hasilLayout.setVerticalGroup(
            panel_hasilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_hasilLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addGap(18, 18, 18)
                .addComponent(jSeparator29, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 539, Short.MAX_VALUE)
                .addContainerGap())
        );

        ContainerPanel.add(panel_hasil, "card2");

        getContentPane().add(ContainerPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 50, 900, 620));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void menu_kriteriaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menu_kriteriaMousePressed
        ArrayList<Double> bobot = kriteria.getPerbandinganKriteriaUser(pengguna.
                getId());
        if (bobot.isEmpty()) {

        } else {
            this.radioKriteria(bobot);
        }
        setColor(menu_kriteria);
        kriteria_aktif.setOpaque(true);
        resetColor(new JPanel[]{menu_matriks}, new JPanel[]{matriks_aktif});
        resetColor(new JPanel[]{menu_hasil}, new JPanel[]{hasil_aktif});
        panel_kriteria.setVisible(true);
        panel_hasil.setVisible(false);
        panel_matriks.setVisible(false);

    }//GEN-LAST:event_menu_kriteriaMousePressed

    private void menu_matriksMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menu_matriksMousePressed
        // TODO add your handling code here:
        setColor(menu_matriks);
        matriks_aktif.setOpaque(true);
        resetColor(new JPanel[]{menu_kriteria}, new JPanel[]{kriteria_aktif});
        resetColor(new JPanel[]{menu_hasil}, new JPanel[]{hasil_aktif});
        panel_kriteria.setVisible(false);
        panel_hasil.setVisible(false);
        panel_matriks.setVisible(true);
    }//GEN-LAST:event_menu_matriksMousePressed

    private void menu_hasilMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menu_hasilMousePressed
        if (CR > 0.1) {
            JOptionPane.showMessageDialog(this,
                    "Nilai Konsistensi Rasio Anda Tidak Memenuhi Syarat Untuk Melanjutkan Perhitungan. Silahkan Ulangi Pengisian Kriteria Anda");
        } else if (CR == 0) {
            JOptionPane.showMessageDialog(this,
                    "Silahkan Lakukan Perhitungan AHP terlebih Dahulu Melalui Menu Matriks");
            setColor(menu_matriks);
            matriks_aktif.setOpaque(true);
            resetColor(new JPanel[]{menu_kriteria}, new JPanel[]{kriteria_aktif});
            resetColor(new JPanel[]{menu_hasil}, new JPanel[]{hasil_aktif});
            panel_kriteria.setVisible(false);
            panel_hasil.setVisible(false);
            panel_matriks.setVisible(true);
        } else {
            setColor(menu_hasil);
            hasil_aktif.setOpaque(true);
            resetColor(new JPanel[]{menu_kriteria}, new JPanel[]{kriteria_aktif});
            resetColor(new JPanel[]{menu_matriks}, new JPanel[]{matriks_aktif});
            panel_kriteria.setVisible(false);
            panel_hasil.setVisible(true);
            panel_matriks.setVisible(false);
        }

    }//GEN-LAST:event_menu_hasilMousePressed

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

    private void helpButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_helpButtonActionPerformed
        Help bantuan = new Help();
        bantuan.setVisible(true);
    }//GEN-LAST:event_helpButtonActionPerformed

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

    private void BG3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BG3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BG3ActionPerformed

    private void BG8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BG8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BG8ActionPerformed

    private void BG4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BG4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BG4ActionPerformed

    private void BF3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BF3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BF3ActionPerformed

    private void BG9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BG9ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BG9ActionPerformed

    private void BF4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BF4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BF4ActionPerformed

    private void BG5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BG5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BG5ActionPerformed

    private void BF6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BF6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BF6ActionPerformed

    private void BG1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BG1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BG1ActionPerformed

    private void BG6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BG6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BG6ActionPerformed

    private void BG7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BG7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BG7ActionPerformed

    private void BF8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BF8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BF8ActionPerformed

    private void BF1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BF1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BF1ActionPerformed

    private void BG2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BG2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BG2ActionPerformed

    private void BF7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BF7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BF7ActionPerformed

    private void BF2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BF2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BF2ActionPerformed

    private void BF9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BF9ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BF9ActionPerformed

    private void BF5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BF5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BF5ActionPerformed

    private void CD4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CD4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CD4ActionPerformed

    private void BH6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BH6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BH6ActionPerformed

    private void CD1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CD1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CD1ActionPerformed

    private void BH2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BH2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BH2ActionPerformed

    private void CD6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CD6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CD6ActionPerformed

    private void BH3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BH3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BH3ActionPerformed

    private void BH9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BH9ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BH9ActionPerformed

    private void BH8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BH8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BH8ActionPerformed

    private void CD5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CD5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CD5ActionPerformed

    private void CD2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CD2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CD2ActionPerformed

    private void CD7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CD7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CD7ActionPerformed

    private void BH4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BH4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BH4ActionPerformed

    private void CD8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CD8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CD8ActionPerformed

    private void CD9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CD9ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CD9ActionPerformed

    private void CD3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CD3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CD3ActionPerformed

    private void BH1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BH1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BH1ActionPerformed

    private void BH5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BH5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BH5ActionPerformed

    private void BH7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BH7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BH7ActionPerformed

    private void CF6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CF6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CF6ActionPerformed

    private void CF3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CF3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CF3ActionPerformed

    private void CE5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CE5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CE5ActionPerformed

    private void CE9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CE9ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CE9ActionPerformed

    private void CE7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CE7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CE7ActionPerformed

    private void CE2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CE2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CE2ActionPerformed

    private void CE3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CE3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CE3ActionPerformed

    private void CE8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CE8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CE8ActionPerformed

    private void CF5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CF5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CF5ActionPerformed

    private void CE6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CE6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CE6ActionPerformed

    private void CF2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CF2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CF2ActionPerformed

    private void CE4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CE4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CE4ActionPerformed

    private void CF9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CF9ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CF9ActionPerformed

    private void CF8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CF8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CF8ActionPerformed

    private void CF1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CF1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CF1ActionPerformed

    private void CF4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CF4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CF4ActionPerformed

    private void CE1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CE1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CE1ActionPerformed

    private void CF7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CF7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CF7ActionPerformed

    private void CG2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CG2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CG2ActionPerformed

    private void CH5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CH5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CH5ActionPerformed

    private void CH2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CH2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CH2ActionPerformed

    private void CH3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CH3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CH3ActionPerformed

    private void CH6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CH6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CH6ActionPerformed

    private void CH8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CH8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CH8ActionPerformed

    private void CG5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CG5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CG5ActionPerformed

    private void CG9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CG9ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CG9ActionPerformed

    private void CG1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CG1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CG1ActionPerformed

    private void CH1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CH1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CH1ActionPerformed

    private void CG6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CG6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CG6ActionPerformed

    private void CG7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CG7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CG7ActionPerformed

    private void CG3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CG3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CG3ActionPerformed

    private void CH9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CH9ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CH9ActionPerformed

    private void CG8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CG8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CG8ActionPerformed

    private void CH4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CH4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CH4ActionPerformed

    private void CG4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CG4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CG4ActionPerformed

    private void CH7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CH7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CH7ActionPerformed

    private void DE6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DE6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_DE6ActionPerformed

    private void DF5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DF5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_DF5ActionPerformed

    private void DE3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DE3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_DE3ActionPerformed

    private void DF2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DF2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_DF2ActionPerformed

    private void DE1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DE1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_DE1ActionPerformed

    private void DF6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DF6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_DF6ActionPerformed

    private void DE5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DE5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_DE5ActionPerformed

    private void DF7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DF7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_DF7ActionPerformed

    private void DF9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DF9ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_DF9ActionPerformed

    private void DF3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DF3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_DF3ActionPerformed

    private void DE8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DE8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_DE8ActionPerformed

    private void DF1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DF1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_DF1ActionPerformed

    private void DF4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DF4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_DF4ActionPerformed

    private void DE7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DE7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_DE7ActionPerformed

    private void DE4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DE4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_DE4ActionPerformed

    private void DE2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DE2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_DE2ActionPerformed

    private void DE9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DE9ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_DE9ActionPerformed

    private void DF8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DF8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_DF8ActionPerformed

    private void DH9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DH9ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_DH9ActionPerformed

    private void DH2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DH2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_DH2ActionPerformed

    private void DH3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DH3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_DH3ActionPerformed

    private void DH7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DH7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_DH7ActionPerformed

    private void DG4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DG4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_DG4ActionPerformed

    private void DG2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DG2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_DG2ActionPerformed

    private void DH1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DH1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_DH1ActionPerformed

    private void DH8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DH8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_DH8ActionPerformed

    private void DG8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DG8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_DG8ActionPerformed

    private void DG5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DG5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_DG5ActionPerformed

    private void DH5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DH5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_DH5ActionPerformed

    private void DH4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DH4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_DH4ActionPerformed

    private void DG7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DG7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_DG7ActionPerformed

    private void DG1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DG1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_DG1ActionPerformed

    private void DG9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DG9ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_DG9ActionPerformed

    private void DG6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DG6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_DG6ActionPerformed

    private void DH6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DH6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_DH6ActionPerformed

    private void DG3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DG3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_DG3ActionPerformed

    private void EF3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EF3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_EF3ActionPerformed

    private void EG7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EG7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_EG7ActionPerformed

    private void EG9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EG9ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_EG9ActionPerformed

    private void EF9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EF9ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_EF9ActionPerformed

    private void EF2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EF2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_EF2ActionPerformed

    private void EF5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EF5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_EF5ActionPerformed

    private void EF7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EF7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_EF7ActionPerformed

    private void EG6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EG6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_EG6ActionPerformed

    private void EG2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EG2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_EG2ActionPerformed

    private void EG8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EG8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_EG8ActionPerformed

    private void EF8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EF8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_EF8ActionPerformed

    private void EG1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EG1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_EG1ActionPerformed

    private void EF1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EF1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_EF1ActionPerformed

    private void EG5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EG5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_EG5ActionPerformed

    private void EG3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EG3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_EG3ActionPerformed

    private void EG4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EG4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_EG4ActionPerformed

    private void EF6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EF6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_EF6ActionPerformed

    private void EF4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EF4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_EF4ActionPerformed

    private void FG6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FG6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_FG6ActionPerformed

    private void EH1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EH1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_EH1ActionPerformed

    private void EH2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EH2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_EH2ActionPerformed

    private void EH9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EH9ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_EH9ActionPerformed

    private void EH8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EH8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_EH8ActionPerformed

    private void FG7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FG7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_FG7ActionPerformed

    private void FG9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FG9ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_FG9ActionPerformed

    private void EH5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EH5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_EH5ActionPerformed

    private void FG1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FG1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_FG1ActionPerformed

    private void FG5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FG5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_FG5ActionPerformed

    private void FG3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FG3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_FG3ActionPerformed

    private void FG2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FG2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_FG2ActionPerformed

    private void FG8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FG8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_FG8ActionPerformed

    private void EH7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EH7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_EH7ActionPerformed

    private void EH4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EH4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_EH4ActionPerformed

    private void FG4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FG4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_FG4ActionPerformed

    private void EH6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EH6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_EH6ActionPerformed

    private void EH3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EH3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_EH3ActionPerformed

    private void FH7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FH7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_FH7ActionPerformed

    private void GH9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GH9ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_GH9ActionPerformed

    private void GH8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GH8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_GH8ActionPerformed

    private void FH5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FH5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_FH5ActionPerformed

    private void GH3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GH3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_GH3ActionPerformed

    private void GH2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GH2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_GH2ActionPerformed

    private void GH5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GH5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_GH5ActionPerformed

    private void GH6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GH6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_GH6ActionPerformed

    private void FH9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FH9ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_FH9ActionPerformed

    private void FH1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FH1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_FH1ActionPerformed

    private void GH4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GH4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_GH4ActionPerformed

    private void GH1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GH1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_GH1ActionPerformed

    private void FH4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FH4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_FH4ActionPerformed

    private void FH8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FH8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_FH8ActionPerformed

    private void GH7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GH7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_GH7ActionPerformed

    private void FH2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FH2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_FH2ActionPerformed

    private void FH6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FH6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_FH6ActionPerformed

    private void FH3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FH3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_FH3ActionPerformed

    private void formPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_formPropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_formPropertyChange

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

            ArrayList<Double> nilai = new ArrayList<>();
            nilai.add(getButtonGroup(groupAB.getElements()));
            nilai.add(getButtonGroup(groupAC.getElements()));
            nilai.add(getButtonGroup(groupAD.getElements()));
            nilai.add(getButtonGroup(groupAE.getElements()));
            nilai.add(getButtonGroup(groupAF.getElements()));
            nilai.add(getButtonGroup(groupAG.getElements()));
            nilai.add(getButtonGroup(groupAH.getElements()));
            nilai.add(getButtonGroup(groupBC.getElements()));
            nilai.add(getButtonGroup(groupBD.getElements()));
            nilai.add(getButtonGroup(groupBE.getElements()));
            nilai.add(getButtonGroup(groupBF.getElements()));
            nilai.add(getButtonGroup(groupBG.getElements()));
            nilai.add(getButtonGroup(groupBH.getElements()));
            nilai.add(getButtonGroup(groupCD.getElements()));
            nilai.add(getButtonGroup(groupCE.getElements()));
            nilai.add(getButtonGroup(groupCF.getElements()));
            nilai.add(getButtonGroup(groupCG.getElements()));
            nilai.add(getButtonGroup(groupCH.getElements()));
            nilai.add(getButtonGroup(groupDE.getElements()));
            nilai.add(getButtonGroup(groupDF.getElements()));
            nilai.add(getButtonGroup(groupDG.getElements()));
            nilai.add(getButtonGroup(groupDH.getElements()));
            nilai.add(getButtonGroup(groupEF.getElements()));
            nilai.add(getButtonGroup(groupEG.getElements()));
            nilai.add(getButtonGroup(groupEH.getElements()));
            nilai.add(getButtonGroup(groupFG.getElements()));
            nilai.add(getButtonGroup(groupFH.getElements()));
            nilai.add(getButtonGroup(groupGH.getElements()));

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
                resetColor(new JPanel[]{menu_hasil}, new JPanel[]{hasil_aktif});
                panel_kriteria.setVisible(false);
                panel_hasil.setVisible(false);
                panel_matriks.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null,
                        "Silahkan Isi Bobot Yang Masih Kosong");
            }
        }
    }//GEN-LAST:event_simpanActionPerformed

    private void kButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kButton1ActionPerformed
        Auth.logout(pengguna.getId());
        this.dispose();
        Login login = new Login();
        login.setVisible(true);
    }//GEN-LAST:event_kButton1ActionPerformed

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

    private void finishActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_finishActionPerformed
        if (finish.getText().equalsIgnoreCase("Kembali Ke Kriteria")) {
            ArrayList<Double> bobot = kriteria.getPerbandinganKriteriaUser(
                    pengguna.
                            getId());
            if (bobot.isEmpty()) {

            } else {
                this.radioKriteria(bobot);
            }
            setColor(menu_kriteria);
            kriteria_aktif.setOpaque(true);
            resetColor(new JPanel[]{menu_matriks}, new JPanel[]{matriks_aktif});
            resetColor(new JPanel[]{menu_hasil}, new JPanel[]{hasil_aktif});
            panel_kriteria.setVisible(true);
            panel_hasil.setVisible(false);
            panel_matriks.setVisible(false);
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

    private void panel_hasilComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_panel_hasilComponentShown
        InitTableVarietas();
        TampilDataVarietas();
        InitTableTernormalisasi();
        TampilDataTernormalisasi();
        InitTableRanking();
        TampilDataRanking();
    }//GEN-LAST:event_panel_hasilComponentShown

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
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(
                    java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(
                    java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(
                    java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(
                    java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Home().setVisible(true);

            }
        });
    }

    private void setColor(JPanel panel) {
        panel.setBackground(new Color(41, 57, 60));
    }

    boolean cekNilai(ArrayList<Double> nilai) {
        for (int i = 0; i < nilai.size(); i++) {
            if (nilai.get(i) == 0) {
                return false;
            }
        }
        return true;
    }

    String getSelectedButtonText(ButtonGroup buttonGroup) {
        for (Enumeration buttons = buttonGroup.getElements(); buttons.
                hasMoreElements();) {
            AbstractButton button = (AbstractButton) buttons.nextElement();

            if (button.isSelected()) {
                return button.getText();
            }
        }

        return null;
    }

    private void resetColor(JPanel[] panel, JPanel[] indicators) {
        for (int i = 0; i < panel.length; i++) {
            panel[i].setBackground(new Color(23, 35, 51));
        }
        for (int i = 0; i < indicators.length; i++) {
            indicators[i].setOpaque(false);
        }

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
    private javax.swing.JRadioButton AC1;
    private javax.swing.JRadioButton AC2;
    private javax.swing.JRadioButton AC3;
    private javax.swing.JRadioButton AC4;
    private javax.swing.JRadioButton AC5;
    private javax.swing.JRadioButton AC6;
    private javax.swing.JRadioButton AC7;
    private javax.swing.JRadioButton AC8;
    private javax.swing.JRadioButton AC9;
    private javax.swing.JRadioButton AD1;
    private javax.swing.JRadioButton AD2;
    private javax.swing.JRadioButton AD3;
    private javax.swing.JRadioButton AD4;
    private javax.swing.JRadioButton AD5;
    private javax.swing.JRadioButton AD6;
    private javax.swing.JRadioButton AD7;
    private javax.swing.JRadioButton AD8;
    private javax.swing.JRadioButton AD9;
    private javax.swing.JRadioButton AE1;
    private javax.swing.JRadioButton AE2;
    private javax.swing.JRadioButton AE3;
    private javax.swing.JRadioButton AE4;
    private javax.swing.JRadioButton AE5;
    private javax.swing.JRadioButton AE6;
    private javax.swing.JRadioButton AE7;
    private javax.swing.JRadioButton AE8;
    private javax.swing.JRadioButton AE9;
    private javax.swing.JRadioButton AF1;
    private javax.swing.JRadioButton AF2;
    private javax.swing.JRadioButton AF3;
    private javax.swing.JRadioButton AF4;
    private javax.swing.JRadioButton AF5;
    private javax.swing.JRadioButton AF6;
    private javax.swing.JRadioButton AF7;
    private javax.swing.JRadioButton AF8;
    private javax.swing.JRadioButton AF9;
    private javax.swing.JRadioButton AG1;
    private javax.swing.JRadioButton AG2;
    private javax.swing.JRadioButton AG3;
    private javax.swing.JRadioButton AG4;
    private javax.swing.JRadioButton AG5;
    private javax.swing.JRadioButton AG6;
    private javax.swing.JRadioButton AG7;
    private javax.swing.JRadioButton AG8;
    private javax.swing.JRadioButton AG9;
    private javax.swing.JRadioButton AH1;
    private javax.swing.JRadioButton AH2;
    private javax.swing.JRadioButton AH3;
    private javax.swing.JRadioButton AH4;
    private javax.swing.JRadioButton AH5;
    private javax.swing.JRadioButton AH6;
    private javax.swing.JRadioButton AH7;
    private javax.swing.JRadioButton AH8;
    private javax.swing.JRadioButton AH9;
    private javax.swing.JRadioButton BC1;
    private javax.swing.JRadioButton BC2;
    private javax.swing.JRadioButton BC3;
    private javax.swing.JRadioButton BC4;
    private javax.swing.JRadioButton BC5;
    private javax.swing.JRadioButton BC6;
    private javax.swing.JRadioButton BC7;
    private javax.swing.JRadioButton BC8;
    private javax.swing.JRadioButton BC9;
    private javax.swing.JRadioButton BD1;
    private javax.swing.JRadioButton BD2;
    private javax.swing.JRadioButton BD3;
    private javax.swing.JRadioButton BD4;
    private javax.swing.JRadioButton BD5;
    private javax.swing.JRadioButton BD6;
    private javax.swing.JRadioButton BD7;
    private javax.swing.JRadioButton BD8;
    private javax.swing.JRadioButton BD9;
    private javax.swing.JRadioButton BE1;
    private javax.swing.JRadioButton BE2;
    private javax.swing.JRadioButton BE3;
    private javax.swing.JRadioButton BE4;
    private javax.swing.JRadioButton BE5;
    private javax.swing.JRadioButton BE6;
    private javax.swing.JRadioButton BE7;
    private javax.swing.JRadioButton BE8;
    private javax.swing.JRadioButton BE9;
    private javax.swing.JRadioButton BF1;
    private javax.swing.JRadioButton BF2;
    private javax.swing.JRadioButton BF3;
    private javax.swing.JRadioButton BF4;
    private javax.swing.JRadioButton BF5;
    private javax.swing.JRadioButton BF6;
    private javax.swing.JRadioButton BF7;
    private javax.swing.JRadioButton BF8;
    private javax.swing.JRadioButton BF9;
    private javax.swing.JRadioButton BG1;
    private javax.swing.JRadioButton BG2;
    private javax.swing.JRadioButton BG3;
    private javax.swing.JRadioButton BG4;
    private javax.swing.JRadioButton BG5;
    private javax.swing.JRadioButton BG6;
    private javax.swing.JRadioButton BG7;
    private javax.swing.JRadioButton BG8;
    private javax.swing.JRadioButton BG9;
    private javax.swing.JRadioButton BH1;
    private javax.swing.JRadioButton BH2;
    private javax.swing.JRadioButton BH3;
    private javax.swing.JRadioButton BH4;
    private javax.swing.JRadioButton BH5;
    private javax.swing.JRadioButton BH6;
    private javax.swing.JRadioButton BH7;
    private javax.swing.JRadioButton BH8;
    private javax.swing.JRadioButton BH9;
    private javax.swing.JRadioButton CD1;
    private javax.swing.JRadioButton CD2;
    private javax.swing.JRadioButton CD3;
    private javax.swing.JRadioButton CD4;
    private javax.swing.JRadioButton CD5;
    private javax.swing.JRadioButton CD6;
    private javax.swing.JRadioButton CD7;
    private javax.swing.JRadioButton CD8;
    private javax.swing.JRadioButton CD9;
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
    private javax.swing.JLabel KI;
    private javax.swing.JLabel KR;
    private javax.swing.JTable TableMatriks;
    private javax.swing.JTable TableRanking;
    private javax.swing.JTable TableTernormalisasi;
    private javax.swing.JTable TableVarietas;
    private javax.swing.JPanel ahpProcess;
    private javax.swing.JButton finish;
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
    private javax.swing.JPanel hasil_aktif;
    private javax.swing.JButton helpButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
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
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel70;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JLabel jLabel72;
    private javax.swing.JLabel jLabel73;
    private javax.swing.JLabel jLabel74;
    private javax.swing.JLabel jLabel75;
    private javax.swing.JLabel jLabel76;
    private javax.swing.JLabel jLabel77;
    private javax.swing.JLabel jLabel78;
    private javax.swing.JLabel jLabel79;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel80;
    private javax.swing.JLabel jLabel81;
    private javax.swing.JLabel jLabel82;
    private javax.swing.JLabel jLabel83;
    private javax.swing.JLabel jLabel84;
    private javax.swing.JLabel jLabel85;
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
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
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
    private javax.swing.JSeparator jSeparator29;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator30;
    private javax.swing.JSeparator jSeparator31;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JSeparator jSeparator9;
    private keeptoo.KButton kButton1;
    private javax.swing.JPanel kriteria_aktif;
    private javax.swing.JPanel matriks_aktif;
    private javax.swing.JPanel menu_hasil;
    private javax.swing.JPanel menu_kriteria;
    private javax.swing.JPanel menu_matriks;
    private javax.swing.JPanel panel_hasil;
    private javax.swing.JPanel panel_kriteria;
    private javax.swing.JPanel panel_matriks;
    private javax.swing.JPanel side_bar;
    private javax.swing.JButton simpan;
    private javax.swing.JLabel welcomeText;
    // End of variables declaration//GEN-END:variables
}
