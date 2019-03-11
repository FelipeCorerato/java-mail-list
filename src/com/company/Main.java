package com.company;

import java.io.*;
import java.util.Scanner;
import com.company.*;

public class Main {

    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);

        String opcao;

        String email = teclado.next();
        String senha = teclado.next();

        IMAPHandler imapHandler = new IMAPHandler(email, senha);
        imapHandler.setProperties();
        try{
            imapHandler.createSession();

            System.out.println("Selecione a opcao desejada: ");
            System.out.println("1. Listar emails");
            System.out.println("2. Criar pasta");
            System.out.println("3. Fechar programa");

            opcao = teclado.next();

            if (opcao == "1"){
                imapHandler.listar();
            } else if (opcao == "2"){
                System.out.println("Digite o nome da pasta: ");

                imapHandler.criarPasta(teclado.nextLine());
            } else if (opcao == "3"){
                imapHandler.close();
            }

            else {
                System.out.println("Opcao invalida!");

            }

        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
