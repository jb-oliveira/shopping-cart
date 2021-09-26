package com.jb.api.domain.entity;

import com.jb.api.domain.exception.InvalidCpfException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CpfUnitTest {

    @DisplayName("Deve aceitar cpf validos.")
    @Test
    void validCpf() {
        assertDoesNotThrow(() -> {
            Cpf.parseCpf("864.161.670-50");
            Cpf.parseCpf("591.249.610-43");
            Cpf.parseCpf("35895349404");
            Cpf.parseCpf("19822251491");
        });
    }

    @DisplayName("Não permitir cpf invalidos")
    @Test
    void invalidCpf() {
        assertThrows(InvalidCpfException.class, () -> Cpf.parseCpf("591.249.610-33"));
        assertThrows(InvalidCpfException.class, () -> Cpf.parseCpf("111.111.111-11"));
        assertThrows(InvalidCpfException.class, () -> Cpf.parseCpf("111.111-11"));
        assertThrows(InvalidCpfException.class, () -> Cpf.parseCpf(null));
    }
}
