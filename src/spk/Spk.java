/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spk;

import java.sql.Connection;
import spk.data.Koneksi;
import spk.data.MetodeAhp;
import spk.data.MetodeMoora;
import spk.ui.Home;
import spk.ui.Login;
import spk.ui.Register;

/**
 *
 * @author anas
 */
public class Spk {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
//        Login login = new Login();
//        login.setVisible(true);
//            Register regis = new Register();
//            regis.setVisible(true);
//        MetodeAhp metod = new MetodeAhp();
//        metod.proses();
            MetodeMoora moora = new MetodeMoora();
            moora.proses();
    }
}
