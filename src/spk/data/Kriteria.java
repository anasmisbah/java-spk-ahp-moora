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
    
    public boolean checkPerbandinganKriteriaUser(int userId){
        try{
            stt = con.createStatement();
            String sql="Select * from perbandingankriteria WHERE pengguna_id=" + userId + ";";
            rss=stt.executeQuery(sql);
            if(rss.next()){
                stt = con.createStatement();
                String sqlDelete="DELETE FROM perbandingankriteria WHERE perbandingankriteria.pengguna_id=" + userId + ";";
                stt.executeUpdate(sqlDelete);
            }
            return true;
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        
        return false;
    }
    
    public boolean tambahPerbandinganAntarKriteria(int kriteriaId1,int kriteriaId2,double nilai,int userId){
        try {
            String sql = "INSERT INTO perbandingankriteria VALUES (NULL," + kriteriaId1 + "," + kriteriaId2 + "," + nilai + "," + userId + ");";
            stt = con.createStatement();
            stt.executeUpdate(sql);
            return true; 
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
    
    public boolean tambahpvectorKriteria(int kriteriaId,int penggunaId,double nilaipvector){
        try {
            String sql = "INSERT INTO pvector_kriteria VALUES (NULL," + kriteriaId + "," + penggunaId + "," + nilaipvector +");";
            stt = con.createStatement();
            stt.executeUpdate(sql);
            return true; 
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
    public boolean checkpvectorKriteriaUser(int penggunaId){
        try{
            stt = con.createStatement();
            String sql="Select * from pvector_kriteria WHERE pengguna_id=" + penggunaId + ";";
            rss=stt.executeQuery(sql);
            if(rss.next()){
                stt = con.createStatement();
                String sqlDelete="DELETE FROM pvector_kriteria WHERE pvector_kriteria.pengguna_id=" + penggunaId + ";";
                stt.executeUpdate(sqlDelete);
            }
            return true;
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return false;
    }
    
}
