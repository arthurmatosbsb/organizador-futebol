package com.futebol.futebol.service;


import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.springframework.stereotype.Service;

@Service
public class EnviaEmail {

	public void enviarEmail(String nomeJogador, String EmailJogador) {
		try {
			Email email = new SimpleEmail();
			email.setHostName("smtp.googlemail.com");
			email.setSmtpPort(465);
			email.setAuthenticator(new DefaultAuthenticator("coloque um email@gmail.com", "senha"));
			email.setSSLOnConnect(true);

			email.setFrom("coloque um email");
			email.setSubject("Você foi convidado para o futebol");
			email.setMsg("Olá " + nomeJogador + ". Você acaba de ser convidado para o futebol.");
			email.addTo(EmailJogador);
			email.send();

		} catch (EmailException e) {
			e.printStackTrace();
		}
	}

}
