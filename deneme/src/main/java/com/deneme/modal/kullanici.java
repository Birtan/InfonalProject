package com.deneme.modal;

public class kullanici {
	
	private String ad;
	private String soyad;
	private String mail;
	private String telefon;
	
	
	public String getAd() {
		return ad;
	}
	public void setAd(String ad) {
		this.ad = ad;
	}
	public String getSoyad() {
		return soyad;
	}
	public void setSoyad(String soyad) {
		this.soyad = soyad;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getTelefon() {
		return telefon;
	}
	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}
	public kullanici(String ad, String soyad, String mail, String telefon) {
		super();
		this.ad = ad;
		this.soyad = soyad;
		this.mail = mail;
		this.telefon = telefon;
	}
	
	 
}
