/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spk.data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author anas
 */
public class Kriteria {
    private int id;
    private String nama;
    private String tipe;
    private final Connection con = Koneksi.getkoneksi();
    private Statement stt;
    private ResultSet rss;
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
    
    public Boolean updateKriteria(int id,String nama,String tipe){
        try {
            String sql = "UPDATE kriteria SET nama='"+nama+"',tipe='"+tipe+"' WHERE id=" + id + ";";
            stt = con.createStatement();
            stt.executeUpdate(sql);
            return true; 
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
    
    public ArrayList<Kriteria> allKriteria(){
        ArrayList<Kriteria> kriteriaAll = new ArrayList<>();
        try{
            stt = con.createStatement();
            String sql="Select * from kriteria;";
            rss=stt.executeQuery(sql);
            while(rss.next()){
                kriteriaAll.add(new Kriteria(rss.getInt("id"),rss.getString("nama"),rss.getString("tipe")));
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        
        return kriteriaAll;
    }
    
    public Kriteria showDetail(int id){
        Kriteria kriteria = null;
        try{
            stt = con.createStatement();
            String sql="Select * from kriteria WHERE id=" + id + ";";
            rss=stt.executeQuery(sql);
            while(rss.next()){
                kriteria= new Kriteria(rss.getInt("id"),rss.getString("nama"),rss.getString("tipe"));
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return kriteria;
    }
    
}
