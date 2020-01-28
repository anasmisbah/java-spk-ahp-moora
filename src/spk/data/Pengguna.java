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
public class Pengguna {
    private int id;
    private String username;
    private String password;
    private String nama;
    private String asalDaerah;
    private String role;

    //db things
    private final Connection con = Koneksi.getkoneksi();
    private Statement stt;
    private ResultSet rss;
    
    public Pengguna(int id, String username, String password, String nama, String asalDaerah, String role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.nama = nama;
        this.asalDaerah = asalDaerah;
        this.role = role;
    }

    public Pengguna() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getAsalDaerah() {
        return asalDaerah;
    }

    public void setAsalDaerah(String asalDaerah) {
        this.asalDaerah = asalDaerah;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
    
    public boolean tambah(String username,String password,String nama,String asalDaerah,String role){
        try {
            String sql = "INSERT INTO pengguna VALUES (NULL,'" + nama + "','" + username + "','" + password + "','" + asalDaerah + "','" + role + "',0);";
            stt = con.createStatement();
            stt.executeUpdate(sql);
            return true; 
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
    
    public boolean ubah(int id,String username,String nama,String asalDaerah,String role){
        try {
            String sql = "UPDATE pengguna SET username='" + username + "',nama='"+ nama +"',asal_daerah='"+ asalDaerah +"',role='"+ role +"' WHERE id=" + id + ";";
            stt = con.createStatement();
            stt.executeUpdate(sql);
            return true;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
    
    public Pengguna detail(int id){
        Pengguna pengguna = null;
        try{
            stt = con.createStatement();
            String sql="Select * from pengguna WHERE id=" + id + ";";
            rss=stt.executeQuery(sql);
            while(rss.next()){
                pengguna= new Pengguna(rss.getInt("id"),rss.getString("username"),rss.getString("password"),rss.getString("nama"),rss.getString("asal_daerah"),rss.getString("role"));
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return pengguna;
    }
    
    public ArrayList<Pengguna> allPengguna(){
        ArrayList<Pengguna> allPengguna = new ArrayList<>();
        try{
            stt = con.createStatement();
            String sql="Select * from pengguna;";
            rss=stt.executeQuery(sql);
            while(rss.next()){
                allPengguna.add(new Pengguna(rss.getInt("id"),rss.getString("username"),rss.getString("password"),rss.getString("nama"),rss.getString("asal_daerah"),rss.getString("role")));
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return allPengguna;
    }
    
    public boolean hapus(int id){
        try {
            String sql = "DELETE FROM pengguna WHERE id=" + id + ";";
            stt = con.createStatement();
            stt.executeUpdate(sql);
            return true;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }
    
    public boolean ubahPassword(int id,String passbaru){
        try {
            String sql = "UPDATE pengguna SET password='" + passbaru + "' WHERE id=" + id + ";";
            stt = con.createStatement();
            stt.executeUpdate(sql);
            return true;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
    
    
    
    
}
