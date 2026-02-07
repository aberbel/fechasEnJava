package com;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import com.example.ConversorDeFechas;
import com.example.ConversorDeFechasSrv;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ConversorDeFechasTest {

    private ConversorDeFechasSrv conversor;

    private static final DateTimeFormatter FORMATO_DEFECTO =
            DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private static final LocalDate FECHA_MINIMA =
            LocalDate.parse("01/01/0001", FORMATO_DEFECTO);

    @BeforeEach
    void setUp() {
        conversor = new ConversorDeFechas();
    }

    @Test
    void convertirFecha_ok() {
        String resultado = ConversorDeFechas.convertirFecha(
                "15/08/2023",
                "dd/MM/yyyy",
                "yyyy-MM-dd"
        );

        assertEquals("2023-08-15", resultado);
    }

    @Test
    void convertirStringATimestamp_ok() {
        Timestamp ts = conversor.convertirStringATimestamp(
                "15/08/2023 10:30:00",
                "dd/MM/yyyy HH:mm:ss"
        );

        assertEquals(
                Timestamp.valueOf(LocalDateTime.of(2023, 8, 15, 10, 30)),
                ts
        );
    }

    @Test
    void convertirStringATimestamp_fechaCeroOVacia() {
        Timestamp tsCero = conversor.convertirStringATimestamp("0", "dd/MM/yyyy");
        Timestamp tsVacia = conversor.convertirStringATimestamp("", "dd/MM/yyyy");

        Timestamp esperado = Timestamp.valueOf(FECHA_MINIMA.atStartOfDay());

        assertEquals(esperado, tsCero);
        assertEquals(esperado, tsVacia);
    }

    @Test
    void convertirStringADate_ok() {
        Date date = conversor.convertirStringADate(
                "15/08/2023 00:00:00",
                "dd/MM/yyyy HH:mm:ss"
        );

        assertEquals(Date.valueOf(LocalDate.of(2023, 8, 15)), date);
    }

    @Test
    void convertirStringADate_fechaCeroOVacia() {
        Date dateCero = conversor.convertirStringADate("0", "dd/MM/yyyy");
        Date dateVacia = conversor.convertirStringADate("", "dd/MM/yyyy");

        Date esperado = Date.valueOf(FECHA_MINIMA);

        assertEquals(esperado, dateCero);
        assertEquals(esperado, dateVacia);
    }

    @Test
    void convertirTimestampAString_ok() {
        Timestamp ts = Timestamp.valueOf(LocalDateTime.of(2023, 8, 15, 12, 0));

        String resultado = conversor.convertirTimestampAString(ts, "dd/MM/yyyy");

        assertEquals("15/08/2023", resultado);
    }

    @Test
    void convertirTimestampAString_nullOFechaMinima() {
        Timestamp fechaMinima = Timestamp.valueOf(FECHA_MINIMA.atStartOfDay());

        assertEquals("", conversor.convertirTimestampAString(null, "dd/MM/yyyy"));
        assertEquals("", conversor.convertirTimestampAString(fechaMinima, "dd/MM/yyyy"));
    }

    @Test
    void convertirDateAString_ok() {
        Date date = Date.valueOf(LocalDate.of(2023, 8, 15));

        String resultado = conversor.convertirDateAString(date, "dd/MM/yyyy");

        assertEquals("15/08/2023", resultado);
    }

    @Test
    void convertirDateAString_nullOFechaMinima() {
        Date fechaMinima = Date.valueOf(FECHA_MINIMA);

        assertEquals("", conversor.convertirDateAString(null, "dd/MM/yyyy"));
        assertEquals("", conversor.convertirDateAString(fechaMinima, "dd/MM/yyyy"));
    }

    @Test
    void convertirTimestampADate_ok() {
        Timestamp ts = Timestamp.valueOf(LocalDateTime.of(2023, 8, 15, 18, 45));

        Date resultado = conversor.convertirTimestampADate(ts);

        assertEquals(Date.valueOf(LocalDate.of(2023, 8, 15)), resultado);
    }

    @Test
    void convertirDateATimestamp_ok() {
        Date date = Date.valueOf(LocalDate.of(2023, 8, 15));

        Timestamp resultado = conversor.convertirDateATimestamp(date);

        assertEquals(
                Timestamp.valueOf(LocalDate.of(2023, 8, 15).atStartOfDay()),
                resultado
        );
    }
}

