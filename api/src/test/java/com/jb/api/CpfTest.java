package com.jb.api;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CpfTest {

    @Test
    void validCpf(){
        assertDoesNotThrow(()->{
            Cpf cpf = new Cpf("864.161.670-50");
            cpf.validate();
            cpf = new Cpf("591.249.610-43");
            cpf.validate();
        });
    }

    @Test
    void invalidCpf(){
        assertThrows(InvalidCpfException.class, ()->{
            Cpf cpf = new Cpf("591.249.610-33");
            cpf.validate();
        });
        assertThrows(InvalidCpfException.class, ()->{
            Cpf cpf = new Cpf("111.111.111-11");
            cpf.validate();
        });
        assertThrows(InvalidCpfException.class, ()->{
            Cpf cpf = new Cpf("111.111-11");
            cpf.validate();
        });
        assertThrows(InvalidCpfException.class, ()->{
            Cpf cpf = new Cpf(null);
            cpf.validate();
        });
    }
}
