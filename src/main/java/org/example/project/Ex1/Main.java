package org.example.project.Ex1;


import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ExchangeRate exchangeRate = new ExchangeRate();
        CurrencyConverter converter = new CurrencyConverter(exchangeRate);
        Scanner scanner = new Scanner(System.in);

        System.out.println("Entrez le montant en MAD à convertir en EUR : ");
        double mad = scanner.nextDouble();
        try {
            double eur = converter.convertMadToEur(mad);
            System.out.println(mad + " MAD = " + eur + " EUR");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("Entrez le montant en EUR à convertir en MAD : ");
        double eurInput = scanner.nextDouble();
        try {
            double madOutput = converter.convertEurToMad(eurInput);
            System.out.println(eurInput + " EUR = " + madOutput + " MAD");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        scanner.close();
    }
}