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
   
    // Database girilerek baglantı gerçekleştiren metod
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
    
    //bağlantı ve statement kapatma metodu.
    public  void baglantiKapa()  {
        try {
            con.close();
            st.close();
            System.out.println("baglanti kapat�ld�");
        } catch (SQLException sQLException) {
            
        }
    }
    
    //tablo kısmına tablonun adını ve geri kalan where koşul kısmını yazarak sorgu elde edilir.
    //Dönuş değeri olan Resultset yardımıyla istenilen değerlere erişilir.
    public ResultSet select(String tablo)  {
        String sorgu= String.format("select * from %s;", tablo);
        try {
            rs=st.executeQuery(sorgu);
            System.out.println("sorgu ger�ekle�tirildi");
     
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "sorguda hata var");
        }
        return rs;
        
    }
    
     public ResultSet customSelect(String sorgu)  {
        //String sorgu= String.format("select * from %s;", tablo);
    	 
    	 try {
             rs=st.executeQuery(sorgu);
            System.out.println("sorgu ger�ekle�tirildi");
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "sorguda hata var");
        }
    	
       return rs;
        
    }
    
    //insert sorgumuzu insert metodunun içine yazarak insert işlemini gerçekleştirir
    public  int insert(String sorgu) {
    	int sonucc = 0;
       try {
            sonucc = st.executeUpdate(sorgu);
            System.out.println("ekleme yap�lm��t�r");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "ekleme yap�lamad� sorguda hata var: "+ex);
        }
        
       return sonucc;
    }
    
    // tablo kısmına tablonun adı ve gerikalan kısmını yazarak hangi tablonun hangi elemanı silineceğini belirleriz
    public int delete(String tablo) {
    	int durum=0;
        String sorgu= String.format("delete from %s", tablo);
         try {
             durum=st.executeUpdate(sorgu);
             
             System.out.println(" silme i�lemiger�ekle�tirmi�tir");
         } catch (SQLException ex) {
             JOptionPane.showMessageDialog(null, " silme ger�ekle�medi hata var "+ex);
         }
        return durum;
    }
    
    // UPDATE tablo_ismi SET `kullanici_adi` = 'kara' WHERE `yoneticiler`.`yonetici_id` =12;
    // örnekte verildiği şekilde tablo ismi kısmı ve SET'ten sonraki kısmı girilerek update sorgusu gerçekleştirilir.
    public int update(String tablo,String set) {
    	int sonuc=0;
        String sorgu= String.format(" update %s set %s ", tablo,set);
         try {
             sonuc = st.executeUpdate(sorgu);
             
             System.out.println("de�i�tirme i�lemi ger�ekle�tirildi ");
         } catch (SQLException ex) {
             JOptionPane.showMessageDialog(null, "update i�lemi ger�ekle�tirilemedi hata var "+ex);
         }
        
        return sonuc;
    }
}
 

