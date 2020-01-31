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
public class Varietas {
    
    private int id;
    private String nama;
    private int group_id;
    private Connection con = Koneksi.getkoneksi();
    private Statement stt;
    private ResultSet rss;
    
    //variabel kriteria dari varietas
    private float RerataJumlahTandan,RerataBeratTandan,PotensiTBS,Rendemen,PotensiCPO,Tinggi,PanjangPelepah,KerapatanTanam;

    public void setKriteriaVarietas(float RerataJumlahTandan,float RerataBeratTandan,float PotensiTBS,float Rendemen,float PotensiCPO,float Tinggi,float PanjangPelepah,float KerapatanTanam) {
        this.RerataJumlahTandan = RerataJumlahTandan;
        this.RerataBeratTandan = RerataBeratTandan;
        this.PotensiTBS = PotensiTBS;
        this.Rendemen = Rendemen;
        this.PotensiCPO = PotensiCPO;
        this.Tinggi = Tinggi;
        this.PanjangPelepah = PanjangPelepah;
        this.KerapatanTanam = KerapatanTanam;
    }
    
    public float getRerataJumlahTandan() {
        return RerataJumlahTandan;
    }

    public float getRerataBeratTandan() {
        return RerataBeratTandan;
    }

    public float getPotensiTBS() {
        return PotensiTBS;
    }

    public float getRendemen() {
        return Rendemen;
    }

    public float getPotensiCPO() {
        return PotensiCPO;
    }

    public float getTinggi() {
        return Tinggi;
    }

    public float getPanjangPelepah() {
        return PanjangPelepah;
    }

    public float getKerapatanTanam() {
        return KerapatanTanam;
    }
    
            
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
    
    public Boolean tambah(String nama,int grup_id){
        try {
            String sql = "INSERT INTO varietas VALUES (NULL,'" + nama + "','" + grup_id + "');";
            stt = con.createStatement();
            stt.executeUpdate(sql);
            return true; 
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
    
    public Boolean ubah(int id,String nama,int grup_id){
        try {
            String sql = "UPDATE varietas SET nama='" + nama + "',grup_id="+ grup_id +" WHERE id=" + id + ";";
            stt = con.createStatement();
            stt.executeUpdate(sql);
            return true;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
    
    public Varietas detail(int id){
        Varietas varietas = null;
        try{
            stt = con.createStatement();
            String sql="Select * from varietas JOIN kriteria_varietas ON varietas.id=kriteria_varietas.varietas_id WHERE varietas.id="+id+";";
            rss=stt.executeQuery(sql);
            if(rss.next()){
                varietas=new Varietas(rss.getInt("id"),rss.getString("nama"),rss.getInt("grup_id"));
                float[] nilai = new float[8];
                nilai[0] = rss.getFloat("nilai");
                int i=0;
                while(rss.next()){
                    i++;
                    nilai[i]=rss.getFloat("nilai");
                }
                varietas.setKriteriaVarietas(nilai[0], nilai[1], nilai[2], nilai[3], nilai[4], nilai[5], nilai[6], nilai[7]);
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return varietas;
    }
    
    public ArrayList<Varietas> allVarietas(){
        ArrayList<Varietas> varietasAll =  new ArrayList<>();
        try{
            stt = con.createStatement();
            String sql="Select * from varietas JOIN kriteria_varietas ON varietas.id=kriteria_varietas.varietas_id;";
            rss=stt.executeQuery(sql);
            int i=0;
            float[] nilai = new float[8];
            while(rss.next()){
                nilai[i]=rss.getFloat("nilai");
                if(i==7){
                    Varietas varietasSementara = new Varietas(rss.getInt("id"),rss.getString("nama"),rss.getInt("grup_id"));
                    varietasSementara.setKriteriaVarietas(nilai[0], nilai[1], nilai[2], nilai[3], nilai[4], nilai[5], nilai[6], nilai[7]);
                    varietasAll.add(varietasSementara);
                    i=0;
                }else{
                    i++;
                }
                
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return varietasAll;
    }
    
    public Boolean hapus(int id){
        try {
            String sql = "DELETE FROM varietas WHERE id=" + id + ";";
            stt = con.createStatement();
            stt.executeUpdate(sql);
            return true;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }
    
    public Boolean hapusKriteriaVarietas(int id){
        try {
            String sql = "DELETE FROM kriteria_varietas WHERE varietas_id=" + id + ";";
            stt = con.createStatement();
            stt.executeUpdate(sql);
            return true;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }
    
 
    
    public Boolean tambahNilaiKriteriaVarietas(float[] nilai){
//        INSERT INTO `kriteria_varietas`(`id`, `varietas_id`, `kriteria_id`, `nilai`) VALUES (null,1,1,8)
        ArrayList<Kriteria> kriteriaAll = new Kriteria().allKriteria();
        Varietas varietasBaru = getVarietasTerbaru();
        try {
            for (int i = 0; i < kriteriaAll.size(); i++) {
                String sql = "INSERT INTO `kriteria_varietas`(`id`,`varietas_id`, `kriteria_id`, `nilai` ) VALUES (NULL,"+varietasBaru.getId()+","+kriteriaAll.get(i).getId()+","+nilai[i]+")";
                stt = con.createStatement();
                stt.executeUpdate(sql);
            }
            return true; 
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
    
    public Boolean ubahNilaiKriteriaVarietas(int id,float[] nilai){
//        INSERT INTO `kriteria_varietas`(`id`, `varietas_id`, `kriteria_id`, `nilai`) VALUES (null,1,1,8)
        ArrayList<Kriteria> kriteriaAll = new Kriteria().allKriteria();
        try {
            for (int i = 0; i < kriteriaAll.size(); i++) {
                String sql = "UPDATE kriteria_varietas SET nilai='" + nilai[i] + "' WHERE varietas_id=" + id + " AND kriteria_id="+kriteriaAll.get(i).getId()+";";
                stt = con.createStatement();
                stt.executeUpdate(sql);
            }
            return true; 
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
    
    private Varietas getVarietasTerbaru(){
        
        Varietas varietasBaru = null;
        try{
            String selectPenggunaRegistered = "select * from varietas order by id desc limit 1";
            stt = con.createStatement();
            rss=stt.executeQuery(selectPenggunaRegistered);
            if(rss.next()){
                varietasBaru = new Varietas(rss.getInt("id"),rss.getString("nama"),rss.getInt("grup_id"));
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
            return null;
        }
        return varietasBaru;  
    
    }
    
    public double[][] getMatriksAlternatif(){
        
        ArrayList<Varietas> varietas = allVarietas();
        ArrayList<Kriteria> kriteriaAll = new Kriteria().allKriteria();
        double[][] matriksAlternatif = new double[varietas.size()][kriteriaAll.size()];
        
        for (int i = 0; i < varietas.size(); i++) {
            matriksAlternatif[i] = new double[]{varietas.get(i).getRerataJumlahTandan(),varietas.get(i).getRerataBeratTandan(),varietas.get(i).getPotensiTBS(),varietas.get(i).getRendemen(),
                                                varietas.get(i).getPotensiCPO(),varietas.get(i).getTinggi(),varietas.get(i).getPanjangPelepah(),varietas.get(i).getKerapatanTanam()};
        }
        return matriksAlternatif;
    }
    
    public boolean checkPreferensiAlternatifUser(int penggunaId){
        try{
            stt = con.createStatement();
            String sql="Select * from preferensialternatif WHERE pengguna_id=" + penggunaId + ";";
            rss=stt.executeQuery(sql);
            if(rss.next()){
                stt = con.createStatement();
                String sqlDelete="DELETE FROM preferensialternatif WHERE preferensialternatif.pengguna_id=" + penggunaId + ";";
                stt.executeUpdate(sqlDelete);
            }
            return true;
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return false;
    }
    
    public boolean tambahPreferensiAlternatifUser(int penggunaId,double hasilOptimasi,int varietasId){
        try {
            String sql = "INSERT INTO preferensialternatif VALUES (NULL," + penggunaId + "," + varietasId + "," + hasilOptimasi + ");";
            stt = con.createStatement();
            stt.executeUpdate(sql);
            return true; 
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
    
    public ArrayList<Double> getPreferensiAlternatifUser(int penggunaId){
        ArrayList<Double> nilai =  new ArrayList<>();
        try{
            stt = con.createStatement();
            String sql="Select * from preferensialternatif JOIN varietas ON varietas.id=preferensialternatif.varietas_id WHERE preferensialternatif.pengguna_id="+penggunaId+" order by preferensi desc;";
            rss=stt.executeQuery(sql);
            int i=0;
            while(rss.next()){
                nilai.add(rss.getDouble("preferensi"));
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return nilai;
    }
}
