package com.deneme.asd;

import java.sql.ResultSet;
import java.text.DateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.bson.types.ObjectId;
import org.junit.runner.Request;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
		//mysql kullanarak yaptýlan kodlar
		/*Baglanti kullaniciBaglanti =new Baglanti("kullanicilar");
      	ResultSet rs=kullaniciBaglanti.select("kullanici"); 
      	kullaniciManeger maneger=new kullaniciManeger();
      	try {
			while(rs.next()){
			maneger.addKullanici(new kullanici(rs.getString(1),rs.getString(2), rs.getString(3),rs.getString(4) , rs.getString(5)));
			
			}
		} catch (Exception e) {
			// TODO: handle exception
		}*/
		ApplicationContext ctx= new ClassPathXmlApplicationContext("application-context.xml");
		MongoOperations mopt=ctx.getBean("mongoTemplate",MongoOperations.class);
		List<kullanici> kullanici=mopt.find(new Query(), kullanici.class);
		kullaniciManeger maneger=new kullaniciManeger();
		for (kullanici k : kullanici) {
			maneger.addKullanici(k);
		}
		
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		model.addAttribute("list",maneger.getKullaniciList());
		
		return "home";
	}
	//aldýðý parametrelerle mongodb'ye ekleme iþlemini yapan metod
	@RequestMapping(value="home/ekle",method=RequestMethod.GET)
	 public @ResponseBody String ekle(@RequestParam(value = "name") String name,
			 						@RequestParam(value = "email") String email,
			 						@RequestParam(value = "telefon") String telefon,Model m) {
		/*Baglanti bgl=new Baglanti("kullanicilar");
		int sonuc=bgl.insert("insert into kullanici values(NULL,'"+name+"','"+""+"','"+email+"','"+telefon+"')");
		logger.info("sonuc {}.", sonuc);*/
		ApplicationContext ctx= new ClassPathXmlApplicationContext("application-context.xml");
		MongoOperations mopt=ctx.getBean("mongoTemplate",MongoOperations.class);
		kullanici kullanici=new kullanici(name, "", email, telefon);
		mopt.insert(kullanici);
		
		
		return "home";
	}
	
	//aldýðý parametreyle mongodb'den silme iþlemini yapan metod
	@RequestMapping(value="home/sil",method=RequestMethod.GET)
	 public @ResponseBody void sil(@RequestParam(value = "id") String id) {
		/*Baglanti bgl=new Baglanti("kullanicilar");
		int sonuc=bgl.delete("kullanici where id="+id);
		logger.info("sonuc {}.", sonuc);*/
		//logger.info("sonuc {}.", ad);
		ApplicationContext ctx= new ClassPathXmlApplicationContext("application-context.xml");
		MongoOperations mopt=ctx.getBean("mongoTemplate",MongoOperations.class);
		kullanici kulBul=mopt.findOne(new Query(Criteria.where("id").is(id)),kullanici.class);
		mopt.remove(kulBul);
		
	}
	//view dan aldýðý parametrelerle güncelleme iþlemini yapan fonksiyon
	@RequestMapping(value="home/guncelle",method=RequestMethod.GET)
	 public @ResponseBody void guncelle(@RequestParam(value = "id") ObjectId id,@RequestParam(value = "name") String name,
			 						@RequestParam(value = "mail") String mail,
			 						@RequestParam(value = "telefon") String telefon) {
		/*Baglanti bgl=new Baglanti("kullanicilar");
		int sonuc=bgl.update("kullanici", String.format("ad='%s',mail='%s',telefon='%s' where id='%s' ",name,mail,telefon,id ));
		logger.info("sonuc {}.", sonuc);*/
		System.out.println("id miz"+id);
		ApplicationContext ctx= new ClassPathXmlApplicationContext("application-context.xml");
		MongoOperations mopt=ctx.getBean("mongoTemplate",MongoOperations.class);
		kullanici kulBul=mopt.findOne(new Query(Criteria.where("id").is(id)), kullanici.class);
		Update update =new Update();
		update.set("ad", name);
		update.set("mail", mail);
		update.set("telefon", telefon);
		mopt.updateFirst(new Query(Criteria.where("id").is(id)), update, kullanici.class);
		
	}
	
}
