package com.deneme.asd;

import java.sql.ResultSet;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.deneme.baglanti.Baglanti;
import com.deneme.modal.kullanici;
import com.deneme.sevice.*;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		Baglanti kullaniciBaglanti =new Baglanti("kullanicilar");
      	ResultSet rs=kullaniciBaglanti.select("kullanici"); 
      	kullaniciManeger maneger=new kullaniciManeger();
      	try {
			while(rs.next()){
				System.out.println(rs.getString(2));
			maneger.addKullanici(new kullanici(rs.getString(2), rs.getString(3),rs.getString(4) , rs.getString(5)));
			System.out.println(rs.getString(1));
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		model.addAttribute("list",maneger.getKullaniciList());
		return "home";
	}
	
	@RequestMapping(value="home/ekle",method=RequestMethod.GET)
	 public @ResponseBody String ekle(@RequestParam(value = "name") String name,
			 						@RequestParam(value = "email") String email,
			 						@RequestParam(value = "telefon") String telefon) {
		Baglanti bgl=new Baglanti("kullanicilar");
		int sonuc=bgl.insert("insert into kullanici values(NULL,'"+name+"','"+""+"','"+email+"','"+telefon+"')");
		logger.info("sonuc {}.", sonuc);
		return "home";
	}
	
}
