<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.deneme.modal.kullanici"%>
<%@page import="java.sql.ResultSet"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ page session="false"%>
<%@page import="com.deneme.baglanti.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html style="font-size:100%">
<head>
<title>Home</title>

<link rel="stylesheet"href="//code.jquery.com/ui/1.10.4/themes/smoothness/jquery-ui.css">

<link rel="stylesheet" href="././resources/css/bootstrap.min.css">

<script src="//code.jquery.com/jquery-1.9.1.js"></script>
<script src="././resources/js/bootstrap.min.js"></script>

<script src="//code.jquery.com/ui/1.10.4/jquery-ui.js"></script>

</head>
<body>
<div class="container">
	<h1>Hello world!</h1>

	<P>The time on the server is ${serverTime}.</P>

	<style>
body {
	font-size: 62.5%;
}

label,input {
	display: block;
}

input.text {
	margin-bottom: 12px;
	width: 95%;
	padding: .4em;
}

fieldset {
	padding: 0;
	border: 0;
	margin-top: 25px;
}

h1 {
	font-size: 1.2em;
	margin: .6em 0;
}

div#users-contain {
	width: 350px;
	margin: 20px 0;
}

div#users-contain table {
	margin: 1em 0;
	border-collapse: collapse;
	width: 100%;
}

div#users-contain table td,div#users-contain table th {
	border: 1px solid #eee;
	padding: .6em 10px;
	text-align: left;
}

.ui-dialog .ui-state-error {
	padding: .3em;
}

.validateTips {
	border: 1px solid transparent;
	padding: 0.3em;
}
</style>



	<script>
		$(function() {
			var name = $("#name"), email = $("#email"), password = $("#password"), allFields = $(
					[]).add(name).add(email).add(password), tips = $(".validateTips");

			function updateTips(t) {
				tips.text(t).addClass("ui-state-highlight");
				setTimeout(function() {
					tips.removeClass("ui-state-highlight", 1500);
				}, 500);
			}
			function checkLength(o, n, min, max) {
				if (o.val().length > max || o.val().length < min) {
					o.addClass("ui-state-error");
					updateTips("Length of " + n + " must be between " + min
							+ " and " + max + ".");
					return false;
				} else {
					return true;
				}
			}
			function checkRegexp(o, regexp, n) {
				if (!(regexp.test(o.val()))) {
					o.addClass("ui-state-error");
					updateTips(n);
					return false;
				} else {
					return true;
				}
			}
			$("#dialog-form")
					.dialog(
							{
								autoOpen : false,
								height : 300,
								width : 350,
								modal : true,
								buttons : {
									"Create an account" : function() {
										//L
										function ajax() {

											var name = $("#name").val();
											var email = $("#email").val();
											var telefon = $("#password").val();
											
											$
													.ajax({
														url : "home/ekle", // bu adres ajax yöntemi ile veri gönderilecek dosyayı belirler
														data : {
															"name" : name,
															"email" : email,
															"telefon" : telefon
														},// gönderilecek bilgiler bu alanda belirtilir
														type : "GET",
														async : false,
														contentType : "application/json",
														success : function() { // geri dönen sonuç
															$("#gif").show("slow");
														},
														complete: function(){
															$("#gif").hide("slow");
															window.location.href = "/asd";
															
														}
														
													});

										}

										var bValid = true;

										allFields.removeClass("ui-state-error");
										bValid = bValid
												&& checkLength(name,
														"username", 3, 16);
										bValid = bValid
												&& checkLength(email, "email",
														6, 80);
										bValid = bValid
												&& checkLength(password,
														"password", 5, 16);
										bValid = bValid
												&& checkRegexp(name,
														/^[a-z]([0-9a-z_])+$/i,
														"Username may consist of a-z, 0-9, underscores, begin with a letter.");
										// From jquery.validate.js (by joern), contributed by Scott Gonzalez: http://projects.scottsplayground.com/email_address_validation/
										bValid = bValid
												&& checkRegexp(
														email,
														/^((([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+(\.([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+)*)|((\x22)((((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(([\x01-\x08\x0b\x0c\x0e-\x1f\x7f]|\x21|[\x23-\x5b]|[\x5d-\x7e]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(\\([\x01-\x09\x0b\x0c\x0d-\x7f]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]))))*(((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(\x22)))@((([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.)+(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.?$/i,
														"eg. ui@jquery.com");
										bValid = bValid
												&& checkRegexp(password,
														/^([0-9a-zA-Z])+$/,
														"Password field only allow : a-z 0-9");
										if (bValid) {
											ajax();
											$("#users tbody")
													.append(
															"<tr>"
																	+ "<td>"
																	+ name
																			.val()
																	+ "</td>"
																	+ "<td>"
																	+ email
																			.val()
																	+ "</td>"
																	+ "<td>"
																	+ password
																			.val()
																	+ "</td>"
																	+ "</tr>");
											$(this).dialog("close");
										}

									},
									Cancel : function() {
										$(this).dialog("close");
									}
								},
								close : function() {
									allFields.val("").removeClass(
											"ui-state-error");
								}
							});
			$("#create-user").button().click(function() {
				$("#dialog-form").dialog("open");
			});
		});
	</script>
	
	<script type="text/javascript">
	//silme islemini yapan ajax fonksiyonu
	var guncelleId;
	
	function sil(id) {
		
		
		$
		.ajax({
			url : "home/sil", // bu adres ajax yöntemi ile veri gönderilecek dosyayı belirler
			data : {
				"id" : id,
			},// gönderilecek bilgiler bu alanda belirtilir
			type : "GET",
			async : false,
			contentType : "application/json",
			success : function() { // geri dönen sonuç
				$("#gif").show("slow");
			},
			complete: function(){
				$("#gif").hide("slow");
				window.location.href = "/asd";
			}
			
		});
	};
	//güncelleme işlemini yapan ajax fonksiyonu
	function guncelle2(id) {
		guncelleId=id;
	};
	
	function guncelle() {
		
		var guncelleAd=$("#guncelleAd").val();
		var guncelleMail=$("#guncelleMail").val();
		var guncelleTelefon=$("#guncelleTelefon").val();
		
		
		$.ajax({
			url : "home/guncelle", // bu adres ajax yöntemi ile veri gönderilecek dosyayı belirler
			data : {
				"id" : guncelleId,
				"name":guncelleAd,
				"mail":guncelleMail,
				"telefon":guncelleTelefon
				
			},// gönderilecek bilgiler bu alanda belirtilir
			type : "GET",
			async : false,
			contentType : "application/json",
			success : function() { // geri dönen sonuç
				$("#gif").show("slow");
			},
			complete: function(){
				$("#gif").hide("slow");
				$(".bs-example-modal-sm").modal('hide');
				window.location.href = "/asd";
				
			}
			
		});
	}
	</script>
</head>
<body>
	<div id="dialog-form" title="Create new user">
		<p class="validateTips">All form fields are required.</p>
		<form>
			<fieldset>
				<label for="name">Name</label> <input type="text" name="ad"
					id="name" class="text ui-widget-content ui-corner-all"> <label
					for="email">Email</label> <input type="text" name="mail" id="email"
					value="" class="text ui-widget-content ui-corner-all"> <label
					for="password">Phone</label> <input type="password" name="telefon"
					id="password" value="" class="text ui-widget-content ui-corner-all">
			</fieldset>
		</form>
	</div>
	<div id="users-contain" class="ui-widget">
		<h1>Existing Users:</h1>
		<table id="users" class="ui-widget ui-widget-content">
			<thead>
				<tr class="ui-widget-header ">
					<th>Name</th>
					<th>Email</th>
					<th>Phone</th>
					<th>İşlem</th>
				</tr>
			</thead>
			<tbody>


				<c:forEach items="${list}" var="kullanicilar">
					<tr>
						<td>ad:${kullanicilar.ad}</td>
						<td>mail:${kullanicilar.mail}</td>
						<td>telefon:${kullanicilar.telefon}</td>
						<td>
						<div class="btn-group">
							<button type="button" class="btn btn-primary dropdown-toggle"
								data-toggle="dropdown">
								İşlemler <span class="caret"></span>
							</button>
							<ul class="dropdown-menu" role="menu">
								<li><a id="guncelle" data-toggle="modal" data-target=".bs-example-modal-sm" onclick="guncelle2('${kullanicilar.id}')">Güncelle</a></li>
								<li><a id="sil"  onclick="sil('${kullanicilar.id}')">sil</a></li>
								
							</ul>
						</div>
						</td>
					</tr>
				</c:forEach>

				<!--<td>${kullanicilar.kullanici.ad}</td>-->

			</tbody>
		</table>
		<div class="modal fade bs-example-modal-sm" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-sm">
    <div class="modal-content">
      
        <h2 class="form-signin-heading">güncellenecek alanları seçiniz</h2>
        <form>
        <input type="text" name="ad" id="guncelleAd" autofocus="" required="" placeholder="name" class="form-control">
        <input type="email" name="mail" id="guncelleMail" autofocus="" required="" placeholder="Email address" class="form-control">
        <input type="password" name="telefon" id="guncelleTelefon" required="" placeholder="phone" class="form-control">
        </form>
        <button type="submit" class="btn btn-lg btn-primary btn-block" onclick="guncelle()">Güncelle</button>
      
    </div>
  </div>
</div>
	</div>
	<button id="create-user">Create new user</button>
	<img src="././resources/img/loading-page.gif" id="gif" width="100"
		height="100" style="display: none">
		
		</div>
</body>


</html>
