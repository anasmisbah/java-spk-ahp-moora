/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spk;

import java.sql.Connection;
import spk.data.Auth;
import spk.data.Grup;
import spk.data.Koneksi;
import spk.data.Kriteria;
import spk.data.MetodeAhp;
import spk.data.MetodeMoora;
import spk.data.Pengguna;
import spk.data.Varietas;
import spk.ui.Home;
import spk.ui.Login;
import spk.ui.Register;

/**
 *
 * @author anas
 */
public class Spk {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
// TODO code application logic here
//            LOGIC LOGIN
            Login login = new Login();
            login.setVisible(true);
//            Pengguna pengguna = Auth.penggunaLogin();
//            if(pengguna != null){
//              System.out.println(pengguna.getNama()); 
//            }

//            LOGIC REGIS
//            Register regis = new Register();
//            regis.setVisible(true);

//            PERHITUNGAN AHP
//            double[] bobots = {5,1,4,2,2,2,2,3,2,5,8,6,4,7,2,2,2,5,2,2,4,2,1,2,7,2,9,2}; contoh bobots isi yg dibawa lewat parameter dari hasil form
//            MetodeAhp metod = new MetodeAhp();
//            metod.proses(1,bobots);

//            PERHITUNGAN MOORA
//            MetodeMoora moora = new MetodeMoora();
//            moora.proses(1);
//            moora.getPreferensiAlternatifUser(1).forEach(item->{
//                System.out.println(item);
//            });

//            RUD KRITERIA
//            Kriteria kriteria = new Kriteria();
//            kriteria.updateKriteria(8, "Kerapatan Tanam", "benefit");
//            kriteria.allKriteria().forEach(kriter->{
//                System.out.println(kriter.getNama());
//            });
//            kriteria.showDetail(8);

//            CRUD VARIETAS
//            Varietas vari = new Varietas();
//            vari.tambah("baru", 1);
//            float[] nilaiKriteria = {1,2,3,4,5,6,7,8};
//            vari.tambahNilaiKriteriaVarietas(nilaiKriteria);
//            vari.ubah(14,"ubah", 1);
//            vari.ubahNilaiKriteriaVarietas(14,new float[]{8,7,6,5,4,3,2,1});
//            System.out.println(vari.detail(14));
//            vari.allVarietas().forEach(varietas->{
//                System.out.println(varietas.getNama()+"|"+varietas.getRerataJumlahTandan());
//            });
//            vari.hapusKriteriaVarietas(14);
//            vari.hapus(14);

//            CRUD PENGGUNA
//            Pengguna pengguna = new Pengguna();
//            boolean hasil = pengguna.tambah("indah", "123123", "indah ", "wahau", "pengguna");
//            boolean hasil = pengguna.ubah(7,"indah", "123123", "indah ubah", "wahau", "pengguna");
//            pengguna.detail(7).getNama()
//            pengguna.allPengguna().forEach(penggunai->{
//                System.out.println(penggunai.getNama());
//            });
//            boolean hasil = pengguna.hapus(7);
//            System.out.println(hasil);
//            boolean hasil =pengguna.ubahPassword(2, "123321");
//            System.out.println(hasil);
            
//            CRUD GRUP
//            Grup grup = new Grup();
//            grup.tambah("baru");
//            grup.ubah(5, "baru diubah");
//            grup.detail(5);
//            grup.allGrup();
//            grup.hapus(5);
            
    }
}
