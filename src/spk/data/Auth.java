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
import javax.swing.JOptionPane;
import spk.ui.Home;

/**
 *
 * @author anas
 */
public class Auth {
    //db things
    private final static Connection con = Koneksi.getkoneksi();
    private static Statement stt;
    private static ResultSet rss;
    
    public static Pengguna login(String username,String password){
        Pengguna penggunaLogin = null;
        try{
            stt = con.createStatement();
            String sql="Select * from pengguna where username = '" + username+"' and password = '" + password+"'";
            rss=stt.executeQuery(sql);
            if(rss.next()){
                penggunaLogin = new Pengguna(rss.getInt("id"),rss.getString("username"),rss.getString("password"),rss.getString("nama"),rss.getString("asal_daerah"),rss.getString("role"));
                
            }
            rss.close();
            stt.close();
            }catch(SQLException e){
                System.out.println(e.getMessage());
            }
        return penggunaLogin;
    }
    public static Boolean ubahStatusLogin(int id) {
        try {
            String sql = "UPDATE pengguna SET status_login=1 WHERE id=" + id + ";";
            stt = con.createStatement();
            stt.executeUpdate(sql);
            return true; 
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
    
    public static Boolean register(String username,String nama,String password,String asalDaerah){
        try {
            String sql = "INSERT INTO pengguna VALUES (NULL,'" + nama + "','" + username + "','" + password +"','" + asalDaerah + "','pengguna',1);";
            stt = con.createStatement();
            stt.executeUpdate(sql);
            return true; 
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
    
    public static Pengguna getPenggunaRegistered(){
        Pengguna penggunaRegistered = null;
        try{
            String selectPenggunaRegistered = "select * from pengguna order by id desc limit 1";
            stt = con.createStatement();
            rss=stt.executeQuery(selectPenggunaRegistered);
            if(rss.next()){
                penggunaRegistered = new Pengguna(rss.getInt("id"),rss.getString("username"),rss.getString("password"),rss.getString("nama"),rss.getString("asal_daerah"),rss.getString("role"));
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
            return null;
        }
        return penggunaRegistered;  
    }
    
    public static boolean logout(int id){
        try {
            String sql = "UPDATE pengguna SET status_login=0 WHERE id=" + id + ";";
            stt = con.createStatement();
            stt.executeUpdate(sql);
            return true; 
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
    
    public static Pengguna penggunaLogin(){
         Pengguna penggunaLogin = null;
        try{
            stt = con.createStatement();
            String sql="Select * from pengguna where status_login =1 limit 1;";
            rss=stt.executeQuery(sql);
            if(rss.next()){
                penggunaLogin = new Pengguna(rss.getInt("id"),rss.getString("username"),rss.getString("password"),rss.getString("nama"),rss.getString("asal_daerah"),rss.getString("role"));
                
            }
            rss.close();
            stt.close();
            }catch(SQLException e){
                System.out.println(e.getMessage());
            }
        return penggunaLogin;
    }
}
