package com.jb.api.domain.entity;

import com.jb.api.domain.exception.ApplicationException;
import com.jb.api.domain.exception.InvalidCpfException;
import lombok.Data;

@Data
public class Cpf {

    private final String value;


    private String onlyDigits() {
        return value.replaceAll("[^0-9]", "");
    }

    private boolean isInvalidLength(String cpfDigits) {
        return cpfDigits.length() != 11;
    }

    private boolean allDigitsAreEqual(String cpfDigits) {
        char firstChar = cpfDigits.charAt(0);
        return cpfDigits.lastIndexOf(firstChar) != 0;
    }

    private int calculateDigits(String cpfDigits, int factor, int max) {
        int total = 0;
        for (Character c : cpfDigits.substring(0, max).toCharArray()) {
            // transforma o caractere '0' no inteiro 0
            // (48 eh a posicao de '0' na tabela ASCII)
            total += (c - 48) * factor--;
        }
        int rest = total % 11;
        return (rest < 2) ? 0 : 11 - rest;
    }

    private String extractCheckerDigit(String cpfDigits) {
        return cpfDigits.substring(9);
    }

    public void validate() throws ApplicationException {
        if (value == null || value.isEmpty()) {
            throw new InvalidCpfException("Cpf cannot be empty");
        }
        String cpfDigits = this.onlyDigits();
        if (this.isInvalidLength(cpfDigits)) {
            throw new InvalidCpfException("Invalid length");
        }
        if (this.allDigitsAreEqual(cpfDigits)) {
            throw new InvalidCpfException("All digits are equal");
        }
        int dg1 = this.calculateDigits(cpfDigits, 10, 9);
        int dg2 = this.calculateDigits(cpfDigits, 11, 10);
        String calculatedDigit = String.format("%d%d", dg1, dg2);
        String checkerDigit = this.extractCheckerDigit(cpfDigits);
        if (!calculatedDigit.equals(checkerDigit)) {
            throw new InvalidCpfException("Invalid check digits");
        }
    }

}
