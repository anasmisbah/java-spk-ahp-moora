/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spk.data;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList; 
import java.util.*;
/**
 *
 * @author anas
 */
public class MetodeAhp {
    
    DecimalFormat df = new DecimalFormat("#.####");
    public void proses(int idUser){
        //dummy array bobot
        double[] bobots = {5,1,4,2,2,2,2,3,2,5,8,6,4,7,2,2,2,5,2,2,4,2,1,2,7,2,9,2};
        
        
        int jumlahKriteria = 8;
        Kriteria kriteria = new Kriteria();
        ArrayList<Kriteria> kriteriaAll = kriteria.allKriteria();
        kriteria.checkPerbandinganKriteriaUser(idUser);
        double[][] matriksKriteria = new double[8][8];
        int urut = 0;
        //buat matrik kriteria erpasangan dulu
        for(int x=0;x<= (kriteriaAll.size()-2);x++){
            for(int y=x+1;y<= (kriteriaAll.size() -1);y++){
                double bobot = bobots[urut];
                matriksKriteria[x][y] = bobot;
                matriksKriteria[y][x] = 1/bobot;
                urut++;
                
                //TODO : simpan ke db
                kriteria.tambahPerbandinganAntarKriteria(kriteriaAll.get(x).getId(), kriteriaAll.get(y).getId(), matriksKriteria[x][y], idUser);
                
            }
        }
        
        //mengisi setiap pasangan kriteria yg sama dengan nilai 1
        for (int i = 0; i <= jumlahKriteria-1; i++) {
            matriksKriteria[i][i] = 1;
        }
        
        //buat dulu matriks untuk nyimpan jumlah setiap baris dan kolom
        double[] jmlpb = new double[8];
        double[] jmlpk = new double[8];
        
        //perulangan untuk ngitung jumlah setiap kolom dulu
        for(int x=0;x<= (jumlahKriteria-1);x++){
            for(int y=0;y<= (jumlahKriteria -1);y++){
                double nilai = matriksKriteria[x][y];
                jmlpk[y]+=nilai;
            }
        }
        
        //buat dulu variabel untuk matriks normalisasi
        double[][] matriksTernormalisasi = new double[8][8];
        double[] priorityVector = new double[8];
        kriteria.checkpvectorKriteriaUser(idUser);
        for(int x=0;x<= (jumlahKriteria-1);x++){
            for(int y=0;y<= (jumlahKriteria -1);y++){
                matriksTernormalisasi[x][y] = matriksKriteria[x][y]/jmlpk[y];
                double nilai = matriksTernormalisasi[x][y];
                jmlpb[x]+=nilai;
            }
            priorityVector[x] = jmlpb[x]/jumlahKriteria;
            
            //TODO : simpan ke db
            kriteria.tambahpvectorKriteria(kriteriaAll.get(x).getId(), idUser, priorityVector[x]);
        }
        
        //hitung principal eigen value
        double eigenValue = 0;
        for (int i = 0; i <= (jumlahKriteria - 1); i++) {
            eigenValue+=(jmlpk[i]*priorityVector[i]);
        }
        //hitung ci
        double consistencyIndex = (eigenValue - jumlahKriteria)/(jumlahKriteria - 1);
        double consistencyRatio = consistencyIndex / 1.41; //hitung cr
        
        System.out.println(Arrays.toString(priorityVector));
        System.out.println(eigenValue);
        System.out.println(consistencyIndex);
        System.out.println(consistencyRatio);
    }
    
}
