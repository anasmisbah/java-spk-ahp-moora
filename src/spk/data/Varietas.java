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
public class Varietas {
    
    private int id;
    private String nama;
    private int group_id;

    public Varietas(int id, String nama, int group_id) {
        this.id = id;
        this.nama = nama;
        this.group_id = group_id;
    }

    public Varietas() {
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

    public int getGroup_id() {
        return group_id;
    }

    public void setGroup_id(int group_id) {
        this.group_id = group_id;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    public void insertNilaiKriteriaVarietas(){
//        INSERT INTO `kriteria_varietas`(`id`, `varietas_id`, `kriteria_id`, `nilai`) VALUES (null,1,1,8)
    }
}
