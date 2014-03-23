package com.deneme.baglanti;
import com.mysql.jdbc.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ubuntu/birtan
 */
public class Baglanti {
     String db="jdbc:mysql://localhost:3306/";
     Connection con;
    private  String kullaniciAdi="root";
    private  String sifre="root";
     Statement st;
     public ResultSet rs;
     
     public String getKullaniciAdi() {
        return kullaniciAdi;
    }

    public void setKullaniciAdi(String kullaniciAdi) {
        this.kullaniciAdi = kullaniciAdi;
    }

    public String getSifre() {
        return sifre;
    }

    public void setSifre(String sifre) {
        this.sifre = sifre;
    }
    
    public void kullaniciAdiSifreDegistir(String ad,String sifre) {
        setKullaniciAdi(ad);
        setSifre(sifre);
    }
    
    
    public  Baglanti() {
        try {
            this.con = DriverManager.getConnection(db,this.kullaniciAdi,this.sifre);
            st=(Statement) con.createStatement();
            System.out.println("baglanti basarili");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "baglanti basarisiz hata: "+ex);
        }
    }
   
    // Database girilerek baglantÄ± gerÃ§ekleÅŸtiren metod
    public Baglanti(String db) {
        this.db=this.db+db;
        try {
        	Class.forName("com.mysql.jdbc.Driver");
            this.con = DriverManager.getConnection(this.db,this.kullaniciAdi,this.sifre);
            st=(Statement) con.createStatement();
            System.out.println("baglanti basarili");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "baglanti basarisiz hata: "+ex);
        }
        
    }
    
    //baÄŸlantÄ± ve statement kapatma metodu.
    public  void baglantiKapa()  {
        try {
            con.close();
            st.close();
            System.out.println("baglanti kapatýldý");
        } catch (SQLException sQLException) {
            
        }
    }
    
    //tablo kÄ±smÄ±na tablonun adÄ±nÄ± ve geri kalan where koÅŸul kÄ±smÄ±nÄ± yazarak sorgu elde edilir.
    //DÃ¶nuÅŸ deÄŸeri olan Resultset yardÄ±mÄ±yla istenilen deÄŸerlere eriÅŸilir.
    public ResultSet select(String tablo)  {
        String sorgu= String.format("select * from %s;", tablo);
        try {
            rs=st.executeQuery(sorgu);
            System.out.println("sorgu gerçekleþtirildi");
     
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "sorguda hata var");
        }
        return rs;
        
    }
    
     public ResultSet customSelect(String sorgu)  {
        //String sorgu= String.format("select * from %s;", tablo);
    	 
    	 try {
             rs=st.executeQuery(sorgu);
            System.out.println("sorgu gerçekleþtirildi");
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "sorguda hata var");
        }
    	
       return rs;
        
    }
    
    //insert sorgumuzu insert metodunun iÃ§ine yazarak insert iÅŸlemini gerÃ§ekleÅŸtirir
    public  int insert(String sorgu) {
    	int sonucc = 0;
       try {
            sonucc = st.executeUpdate(sorgu);
            System.out.println("ekleme yapýlmýþtýr");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "ekleme yapýlamadý sorguda hata var: "+ex);
        }
        
       return sonucc;
    }
    
    // tablo kÄ±smÄ±na tablonun adÄ± ve gerikalan kÄ±smÄ±nÄ± yazarak hangi tablonun hangi elemanÄ± silineceÄŸini belirleriz
    public int delete(String tablo) {
    	int durum=0;
        String sorgu= String.format("delete from %s", tablo);
         try {
             durum=st.executeUpdate(sorgu);
             
             System.out.println(" silme iþlemigerçekleþtirmiþtir");
         } catch (SQLException ex) {
             JOptionPane.showMessageDialog(null, " silme gerçekleþmedi hata var "+ex);
         }
        return durum;
    }
    
    // UPDATE tablo_ismi SET `kullanici_adi` = 'kara' WHERE `yoneticiler`.`yonetici_id` =12;
    // Ã¶rnekte verildiÄŸi ÅŸekilde tablo ismi kÄ±smÄ± ve SET'ten sonraki kÄ±smÄ± girilerek update sorgusu gerÃ§ekleÅŸtirilir.
    public int update(String tablo,String set) {
    	int sonuc=0;
        String sorgu= String.format(" update %s set %s ", tablo,set);
         try {
             sonuc = st.executeUpdate(sorgu);
             
             System.out.println("deðiþtirme iþlemi gerçekleþtirildi ");
         } catch (SQLException ex) {
             JOptionPane.showMessageDialog(null, "update iþlemi gerçekleþtirilemedi hata var "+ex);
         }
        
        return sonuc;
    }
}
 

