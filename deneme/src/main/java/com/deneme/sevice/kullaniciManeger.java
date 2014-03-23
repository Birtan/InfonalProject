package com.deneme.sevice;
import java.util.LinkedList;
import java.util.List;

import com.deneme.modal.*;
public class kullaniciManeger {
	
	private kullanici kullanici;
	private List<kullanici> kullaniciList;
	
	public kullaniciManeger() {
		 kullaniciList=new LinkedList<kullanici>();
	}

	
	public List<kullanici> getKullaniciList() {
		return kullaniciList;
	}

	public void addKullanici(kullanici kullanici) {
		this.kullanici=kullanici;
		this.kullaniciList.add(kullanici);
	}

	
	public kullanici getKullanici() {
		return kullanici;
	}

	public void setKullanici(kullanici kullanici) {
		this.kullanici = kullanici;
	}


}
