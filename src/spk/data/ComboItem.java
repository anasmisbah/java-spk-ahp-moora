/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spk.data;

/**
 *
 * @author MOTHAFUCKAS
 */
public class ComboItem {
    private String nama;
    private int id;

    public ComboItem(String nama, int id)
    {
        this.nama = nama;
        this.id = id;
    }

    @Override
    public String toString()
    {
        return nama;
    }

    public String getNama()
    {
        return nama;
    }

    public int getId()
    {
        return id;
    }
}
