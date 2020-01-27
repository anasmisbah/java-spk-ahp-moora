/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spk.data;

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
    
    
    
    
}
