/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spk.data;

import java.util.Arrays;

/**
 *
 * @author anas
 */
public class MetodeMoora {
    
    public void proses(){
        
        //membuat dummy matriks alternatif
        double[][] matriksAlternatif = {
            {8,25,32,26,7.5,67.5,6.2,130},
            {12,16,30,26,7.8,75,6.08,130},
            {13,19.2,33,26.5,8.7,77.5,5.47,143},
            {14.1,15.4,33,29.9,9.6,72,5.47,143},
            {13,16,39,26,7.5,65,6.09,130},
            {9.3,22.8,28,25.17,8.11,75,5.47,143},
            {15.3,17.2,38,25.8,8.4,62.5,6.5,130},
            {12.5,19,31,26.3,8.3,65,5.31,143}
        };
        String[] kriteria = {"Rerata Jumlah Tandan","Rerata Berat Tandan","Potensi TBS","Rendemen","Potensi CPO","Tinggi","Panjang Pelepah","Kerapatan Tanam"};
        String[] varietas = {"Dy x P SP-1 (Dumpy)","D x P AVROS","D x P Simalungun","D x P PPKS 540","D x P Yangambi","D x P PPKS 718","D x P PPKS 239","D x P Langkat"};
        
        //pangkat dua matriks alternatif kemudian jumlah lalu akarkan
        double[] jmlpKolom = new double[kriteria.length];
        for (int x = 0; x < kriteria.length; x++) {
            for (int y = 0; y < varietas.length ; y++) {
                double nilaiPangkat = Math.pow(matriksAlternatif[x][y],2);
                jmlpKolom[y]+=nilaiPangkat;
            }
        }
        
        //menghitung akar setiap jumlah
        double[] akarpKolom = new double[8];
        for (int i = 0; i < kriteria.length; i++) {
            akarpKolom[i] = Math.sqrt(jmlpKolom[i]); 
        }
        
        System.out.println(Arrays.toString(jmlpKolom));
        
        //membuat matriks ternormalisasi
        double[][] matriksTernormalisasi = new double[kriteria.length][varietas.length];
        
        //kalikan akar dengan nilai pada tabel matriks alternatif
        for (int x = 0; x < kriteria.length; x++) {
            for (int y = 0; y < varietas.length ; y++) {
                matriksTernormalisasi[x][y] = matriksAlternatif[x][y]/akarpKolom[y];
            }
        }
        
        System.out.println(Arrays.deepToString(matriksTernormalisasi));
        
        //TODO : dapatkan bobot dari setiap kriteria
        
        //hitung dan dapatkan matriks dari perhitungan matriksternormalisasi dengan bobot
        
        //hitung penjumlah masing2 nilai
        
        //masukkan ke dalam database nilai hasil optimisasi
        
    }
    
}
