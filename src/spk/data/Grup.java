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
public class Grup {
    private int id;
    private String nama;

    //db things
    private final Connection con = Koneksi.getkoneksi();
    private Statement stt;
    private ResultSet rss;
    
    public Grup() {
    }

    public Grup(int id, String nama) {
        this.id = id;
        this.nama = nama;
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
    
    public boolean tambah(String nama){
        try {
            String sql = "INSERT INTO grup VALUES (NULL,'" + nama + "');";
            stt = con.createStatement();
            stt.executeUpdate(sql);
            return true; 
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
    
    public boolean ubah(int id,String nama){
        try {
            String sql = "UPDATE grup SET nama='" + nama + "' WHERE id="+id+";";
            stt = con.createStatement();
            stt.executeUpdate(sql);
            return true;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
    
    public Grup detail(int id){
        Grup grup = null;
        try{
            stt = con.createStatement();
            String sql="Select * from grup WHERE id=" + id + ";";
            rss=stt.executeQuery(sql);
            while(rss.next()){
                grup= new Grup(rss.getInt("id"),rss.getString("nama"));
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return grup;
    }
    
    public ArrayList<Grup> allGrup(){
        ArrayList<Grup> grups = new ArrayList<>();
        try{
            stt = con.createStatement();
            String sql="Select * from grup;";
            rss=stt.executeQuery(sql);
            while(rss.next()){
                grups.add(new Grup(rss.getInt("id"),rss.getString("nama")));
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return grups;
    }
    
    public boolean hapus(int id){
        try {
            String sql = "DELETE FROM grup WHERE id=" + id + ";";
            stt = con.createStatement();
            stt.executeUpdate(sql);
            return true;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }
    
    
    
}
