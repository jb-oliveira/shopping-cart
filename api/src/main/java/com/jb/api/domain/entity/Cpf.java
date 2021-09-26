package com.jb.api.domain.entity;

import com.jb.api.domain.exception.InvalidCpfException;
import lombok.Getter;

@Getter
public class Cpf {

    private final String value;

    private Cpf(String value) {
        this.value = value;
    }


    public static Cpf parseCpf(String cpf) throws InvalidCpfException {
        if (cpf == null || cpf.isEmpty()) {
            throw new InvalidCpfException("Cpf cannot be empty");
        }
        String cpfDigits = cpf.replaceAll("[^0-9]", "");
        if (cpfDigits.length() != 11) {
            throw new InvalidCpfException("Invalid length");
        }
        if (allDigitsAreEqual(cpfDigits)) {
            throw new InvalidCpfException("All digits are equal");
        }
        int dg1 = calculateDigits(cpfDigits, 10, 9);
        int dg2 = calculateDigits(cpfDigits, 11, 10);
        String calculatedDigit = String.format("%d%d", dg1, dg2);
        String checkerDigit = extractCheckerDigit(cpfDigits);
        if (!calculatedDigit.equals(checkerDigit)) {
            throw new InvalidCpfException("Invalid check digits");
        }
        return new Cpf(cpfDigits);
    }


    private static boolean allDigitsAreEqual(String cpfDigits) {
        char firstChar = cpfDigits.charAt(0);
        for (Character c : cpfDigits.toCharArray()) {
            if (!c.equals(firstChar)) {
                return false;
            }
        }
        return true;
    }

    private static int calculateDigits(String cpfDigits, int factor, int max) {
        int total = 0;
        for (Character c : cpfDigits.substring(0, max).toCharArray()) {
            // transforma o caractere '0' no inteiro 0
            // (48 eh a posicao de '0' na tabela ASCII)
            total += (c - 48) * factor--;
        }
        int rest = total % 11;
        return (rest < 2) ? 0 : 11 - rest;
    }

    private static String extractCheckerDigit(String cpfDigits) {
        return cpfDigits.substring(9);
    }


}
