package org.example.project.Ex1;

public class ExchangeRate {
    private double madToEurRate = 0.09;
    private double eurToMadRate = 11.11;

    public double getMadToEurRate() {
        return madToEurRate;
    }

    public double getEurToMadRate() {
        return eurToMadRate;
    }

    public void setMadToEurRate(double rate) {
        if (rate <= 0) {
            throw new IllegalArgumentException("Le taux doit être positif");
        }
        madToEurRate = rate;
        eurToMadRate = 1 / rate;
    }

    public void setEurToMadRate(double rate) {
        if (rate <= 0) {
            throw new IllegalArgumentException("Le taux doit être positif");
        }
        eurToMadRate = rate;
        madToEurRate = 1 / rate;
    }
}
