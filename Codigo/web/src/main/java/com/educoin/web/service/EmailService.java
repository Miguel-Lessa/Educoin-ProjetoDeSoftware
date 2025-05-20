package com.educoin.web.service;

import org.springframework.stereotype.Service;

@Service
public class EmailService {
    public void enviar(String destinatario, String assunto, String mensagem) {
        System.out.println("Enviando email para: " + destinatario);
        System.out.println("Assunto: " + assunto);
        System.out.println("Mensagem: " + mensagem);
    }
}