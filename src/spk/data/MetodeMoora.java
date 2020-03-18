/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spk.data;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author anas
 */
public class MetodeMoora {
    
    DecimalFormat df = new DecimalFormat("#.####");
    
    private double[][] matriksTernormalisasi;
    private double[][] matriksTernormalisasiTerbobot;
    private double[] hasilOptimasi;

    public double[][] getMatriksTernormalisasiTerbobot() {
        return matriksTernormalisasiTerbobot;
    }

    public double[] getHasilOptimasi() {
        return hasilOptimasi;
    }
    public double[][] getMatriksTernormalisasi() {
        return matriksTernormalisasi;
    }
    public void proses(int penggunaId){
        
        //membuat dummy matriks alternatif
        Varietas varietas = new Varietas();
        Kriteria kriteria = new Kriteria();
        double[][] matriksAlternatif = varietas.getMatriksAlternatif();
        ArrayList<Kriteria> kriteriaAll = kriteria.allKriteria();
        ArrayList<Varietas> varietasAll = varietas.allVarietas();
        
        //pangkat dua matriks alternatif kemudian jumlah lalu akarkan
        double[] jmlpKolom = new double[kriteriaAll.size()];
        for (int x = 0; x < kriteriaAll.size(); x++) {
            for (int y = 0; y < varietasAll.size() ; y++) {
                double nilaiPangkat = Math.pow(matriksAlternatif[x][y],2);
                jmlpKolom[y]+=nilaiPangkat;
            }
        }
        
        //menghitung akar setiap jumlah
        double[] akarpKolom = new double[8];
        for (int i = 0; i < kriteriaAll.size(); i++) {
            akarpKolom[i] = Math.sqrt(jmlpKolom[i]); 
        }
        
        
        //membuat matriks ternormalisasi
        double[][] matriksTernormalisasi = new double[kriteriaAll.size()][varietasAll.size()];
        
        //kalikan akar dengan nilai pada tabel matriks alternatif
        for (int x = 0; x < kriteriaAll.size(); x++) {
            for (int y = 0; y < varietasAll.size() ; y++) {
                matriksTernormalisasi[x][y] = Double.valueOf(df.format(matriksAlternatif[x][y]/akarpKolom[y]));
            }
        }
        
        this.matriksTernormalisasi = matriksTernormalisasi;
        //TODO : dapatkan bobot dari setiap kriteria
        ArrayList<Double> bobot = kriteria.getpvectorKriteriaUser(penggunaId);
        
        //hitung dan dapatkan matriks dari perhitungan matriksternormalisasi dengan bobot
        double[][] matriksTernormalisasiTerbobot = new double[kriteriaAll.size()][varietasAll.size()];
        String tipe = "benefit";
        double[] hasilOptimasi = new double[kriteriaAll.size()];
        for (int x = 0; x < kriteriaAll.size(); x++) {
            for (int y = 0; y < varietasAll.size() ; y++) {
                if(tipe.equalsIgnoreCase(kriteriaAll.get(y).getTipe())){
                    matriksTernormalisasiTerbobot[x][y] = Double.valueOf(df.format(matriksTernormalisasi[x][y]*bobot.get(y)));;
                }else{
                    matriksTernormalisasiTerbobot[x][y] = Double.valueOf(df.format(matriksTernormalisasi[x][y]*bobot.get(y)*-1));
                }
                 //hitung penjumlah masing2 nilai perbarisan
                hasilOptimasi[x] = Double.valueOf(df.format(hasilOptimasi[x]+matriksTernormalisasiTerbobot[x][y]));
            }
        }
        this.matriksTernormalisasiTerbobot = matriksTernormalisasiTerbobot;
        this.hasilOptimasi = hasilOptimasi;
//        System.out.println(Arrays.toString(hasilOptimasi));
        
        //cek alternatif sebelumnya
        varietas.checkPreferensiAlternatifUser(penggunaId);
        //masukkan ke dalam database nilai hasil optimisasi
        for (int i = 0; i < varietasAll.size(); i++) {
            varietas.tambahPreferensiAlternatifUser(penggunaId, hasilOptimasi[i], varietasAll.get(i).getId());
        }
        
        
        
    }

    
    public ArrayList<Double> getPreferensiAlternatifUser(int penggunaId){
        return new Varietas().getPreferensiAlternatifUser(penggunaId);
    }
    
    public ArrayList<String> getNamaPreferensiAlternatifUser(int penggunaId){
        return new Varietas().getNamaPreferensiAlternatifUser(penggunaId);
    }
    
    
}
