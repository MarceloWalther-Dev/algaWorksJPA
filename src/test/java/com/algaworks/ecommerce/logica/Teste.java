package com.algaworks.ecommerce.logica;

import java.util.Arrays;
import java.util.Locale;
import java.util.Scanner;

public class Teste {
    public static void main(String[] args) {
        Scanner scanf = new Scanner(System.in);

        System.out.print("Informe uma palavra: ");
        String palavras = scanf.next();
        char stringArray[] = palavras.toCharArray();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < palavras.length(); i++) {
            if (i % 2 != 0) {
               stringBuilder.append(Character.toUpperCase(stringArray[i]));
            }else{
                stringBuilder.append(Character.toLowerCase(stringArray[i]));
            }
        }
        System.out.println(stringBuilder);
    }
}