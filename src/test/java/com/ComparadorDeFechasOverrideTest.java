package com;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.ComparadorDeFechas;

class ComparadorDeFechasOverrideTest {

    private ComparadorDeFechas comparador;

    @BeforeEach
    void setUp() {
        comparador = new ComparadorDeFechas(new BigDecimal("20260207"));
    }

    @Test
    void noEsMayorQue_bigDecimal_ceroCero() {
        assertFalse(
                comparador.esMayorQue(BigDecimal.ZERO, "yyyyMMdd", BigDecimal.ZERO, "yyyyMMdd")
        );
    }

    @Test
    void noEsMayorQue_bigDecimal_ceroNoCero() {
        assertFalse(
                comparador.esMayorQue(BigDecimal.ZERO, "yyyyMMdd", new BigDecimal("20260207"), "yyyyMMdd")
        );
    }

    @Test
    void noEsMenorQue_bigDecimal_ceroCero() {
        assertFalse(
                comparador.esMenorQue(BigDecimal.ZERO, "yyyyMMdd", BigDecimal.ZERO, "yyyyMMdd")
        );
    }

    @Test
    void esMenorQue_bigDecimal_ceroNoCero() {
        assertTrue(
                comparador.esMenorQue(BigDecimal.ZERO, "yyyyMMdd", new BigDecimal("20260207"), "yyyyMMdd")
        );
    }

    @Test
    void esIgualQue_bigDecimal_ceroCero() {
        assertTrue(
                comparador.esIgualQue(BigDecimal.ZERO, "yyyyMMdd", BigDecimal.ZERO, "yyyyMMdd")
        );
    }

    @Test
    void noEsIgualQue_bigDecimal_ceroNoCero() {
        assertFalse(
                comparador.esIgualQue(BigDecimal.ZERO, "yyyyMMdd", new BigDecimal("20260207"), "yyyyMMdd")
        );
    }

    @Test
    void esMayorMenorIgual_bigDecimal_normales() {
        BigDecimal fechaMenor = new BigDecimal("20260206");
        BigDecimal fechaIgual = new BigDecimal("20260207");
        BigDecimal fechaMayor = new BigDecimal("20260208");

        assertTrue(comparador.esMayorQue(fechaMayor, "yyyyMMdd", fechaIgual, "yyyyMMdd"));
        assertFalse(comparador.esMayorQue(fechaMenor, "yyyyMMdd", fechaIgual, "yyyyMMdd"));

        assertTrue(comparador.esMenorQue(fechaMenor, "yyyyMMdd", fechaIgual, "yyyyMMdd"));
        assertFalse(comparador.esMenorQue(fechaMayor, "yyyyMMdd", fechaIgual, "yyyyMMdd"));

        assertTrue(comparador.esIgualQue(fechaIgual, "yyyyMMdd", fechaIgual, "yyyyMMdd"));
        assertFalse(comparador.esIgualQue(fechaMenor, "yyyyMMdd", fechaIgual, "yyyyMMdd"));
    }

    @Test
    void esMayorQue_string_nullOBlanco() {
        assertFalse(comparador.esMayorQue(null, "yyyyMMdd", "20260207", "yyyyMMdd"));
        assertFalse(comparador.esMayorQue(" ", "yyyyMMdd", "20260207", "yyyyMMdd"));
        assertFalse(comparador.esMayorQue("20260207", "yyyyMMdd", "", "yyyyMMdd"));
    }

    @Test
    void esMenorQue_string_nullOBlanco() {
        assertFalse(comparador.esMenorQue(null, "yyyyMMdd", "20260207", "yyyyMMdd"));
        assertFalse(comparador.esMenorQue(" ", "yyyyMMdd", "20260207", "yyyyMMdd"));
        assertFalse(comparador.esMenorQue("20260207", "yyyyMMdd", "", "yyyyMMdd"));
    }

    @Test
    void esIgualQue_string_nullOBlanco() {
        assertFalse(comparador.esIgualQue(null, "yyyyMMdd", "20260207", "yyyyMMdd"));
        assertFalse(comparador.esIgualQue(" ", "yyyyMMdd", "20260207", "yyyyMMdd"));
        assertFalse(comparador.esIgualQue("20260207", "yyyyMMdd", "", "yyyyMMdd"));
    }

    @Test
    void esMayorOIgualMenorOIgual_string() {
        assertTrue(comparador.esMayorOIgualQue("20260207", "yyyyMMdd", "20260207", "yyyyMMdd"));
        assertTrue(comparador.esMenorOIgualQue("20260207", "yyyyMMdd", "20260207", "yyyyMMdd"));

        assertTrue(comparador.esMayorOIgualQue("20260208", "yyyyMMdd", "20260207", "yyyyMMdd"));
        assertTrue(comparador.esMenorOIgualQue("20260206", "yyyyMMdd", "20260207", "yyyyMMdd"));

        assertFalse(comparador.esMayorOIgualQue("20260206", "yyyyMMdd", "20260207", "yyyyMMdd"));
        assertFalse(comparador.esMenorOIgualQue("20260208", "yyyyMMdd", "20260207", "yyyyMMdd"));
    }

    @Test
    void esMayorMenorIgual_timestamp_null() {
        assertFalse(comparador.esMayorQue((Timestamp) null, Timestamp.valueOf("2026-02-07 00:00:00")));
        assertFalse(comparador.esMenorQue((Timestamp) null, Timestamp.valueOf("2026-02-07 00:00:00")));
        assertFalse(comparador.esIgualQue((Timestamp) null, Timestamp.valueOf("2026-02-07 00:00:00")));
    }

    @Test
    void esMayorMenorIgual_timestamp_mismoDia() {
        Timestamp manana = Timestamp.valueOf(LocalDateTime.of(2026, 2, 7, 8, 0));
        Timestamp tarde = Timestamp.valueOf(LocalDateTime.of(2026, 2, 7, 20, 30));
        Timestamp otroDia = Timestamp.valueOf(LocalDateTime.of(2026, 2, 8, 0, 0));

        assertFalse(comparador.esMayorQue(manana, tarde));
        assertFalse(comparador.esMenorQue(manana, tarde));
        assertTrue(comparador.esIgualQue(manana, tarde));

        assertTrue(comparador.esMenorQue(manana, otroDia));
        assertTrue(comparador.esMayorQue(otroDia, manana));
    }

    @Test
    void esMayorMenorIgual_date_null() {
        assertFalse(comparador.esMayorQue((Date) null, Date.valueOf("2026-02-07")));
        assertFalse(comparador.esMenorQue((Date) null, Date.valueOf("2026-02-07")));
        assertFalse(comparador.esIgualQue((Date) null, Date.valueOf("2026-02-07")));
    }

    @Test
    void esMayorMenorIgual_date() {
        Date date1 = Date.valueOf(LocalDate.of(2026, 2, 7));
        Date date2 = Date.valueOf(LocalDate.of(2026, 2, 8));

        assertTrue(comparador.esMenorQue(date1, date2));
        assertTrue(comparador.esMayorQue(date2, date1));
        assertFalse(comparador.esIgualQue(date1, date2));
        assertTrue(comparador.esIgualQue(date1, date1));
    }
}
