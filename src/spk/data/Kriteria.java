/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spk.data;

import java.sql.Connection;
import java.util.ArrayList;

/**
 *
 * @author anas
 */
public class Kriteria {
    private int id;
    private String nama;
    private String tipe;

    public Kriteria(int id, String nama, String tipe) {
        this.id = id;
        this.nama = nama;
        this.tipe = tipe;
    }

    public Kriteria() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getTipe() {
        return tipe;
    }

    public void setTipe(String tipe) {
        this.tipe = tipe;
    }
    
    public Boolean updateKriteria(String nama,String tipe){
        return true;
    }
    
    public ArrayList<Kriteria> allKriteria(){
        ArrayList<Kriteria> kriteriaAll = new ArrayList<>();
        
        return kriteriaAll;
    }
    
    public Kriteria showDetail(int id){
        Kriteria kriteria = null;
        
        
        return kriteria;
       
    }
    
}
