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
        String resultado = conversor.convertirFecha(
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

    @Test
    void convertirFecha_multipleFormatos() {
        String resultado1 = conversor.convertirFecha(
                "15/08/2023",
                "dd/MM/yyyy",
                "yyyy-MM-dd"
        );
        assertEquals("2023-08-15", resultado1);

        String resultado2 = conversor.convertirFecha(
                "2023-08-15",
                "yyyy-MM-dd",
                "dd/MM/yyyy"
        );
        assertEquals("15/08/2023", resultado2);

        String resultado3 = conversor.convertirFecha(
                "08-15-2023",
                "MM-dd-yyyy",
                "dd/MM/yyyy"
        );
        assertEquals("15/08/2023", resultado3);
    }

    @Test
    void convertirTimestampADate_nullOFechaMinima() {
        Timestamp fechaMinima = Timestamp.valueOf(FECHA_MINIMA.atStartOfDay());
        Date esperado = Date.valueOf(FECHA_MINIMA);

        assertEquals(esperado, conversor.convertirTimestampADate(null));
        assertEquals(esperado, conversor.convertirTimestampADate(fechaMinima));
    }

    @Test
    void convertirDateATimestamp_nullOFechaMinima() {
        Date fechaMinima = Date.valueOf(FECHA_MINIMA);
        Timestamp esperado = Timestamp.valueOf(FECHA_MINIMA.atStartOfDay());

        assertEquals(esperado, conversor.convertirDateATimestamp(null));
        assertEquals(esperado, conversor.convertirDateATimestamp(fechaMinima));
    }

    @Test
    void convertirStringATimestamp_conFormatoCompleto() {
        Timestamp ts = conversor.convertirStringATimestamp(
                "2023-08-15 14:30:45",
                "yyyy-MM-dd HH:mm:ss"
        );

        assertEquals(
                Timestamp.valueOf(LocalDateTime.of(2023, 8, 15, 14, 30, 45)),
                ts
        );
    }

    @Test
    void convertirStringADate_MultiplesFormatos() {
        Date date1 = conversor.convertirStringADate(
                "15/08/2023",
                "dd/MM/yyyy"
        );
        assertEquals(Date.valueOf(LocalDate.of(2023, 8, 15)), date1);

        Date date2 = conversor.convertirStringADate(
                "2023-08-15",
                "yyyy-MM-dd"
        );
        assertEquals(Date.valueOf(LocalDate.of(2023, 8, 15)), date2);
    }

    @Test
    void convertirTimestampAString_conFormatosVariados() {
        Timestamp ts = Timestamp.valueOf(LocalDateTime.of(2023, 8, 15, 14, 30, 45));

        String resultado1 = conversor.convertirTimestampAString(ts, "dd/MM/yyyy");
        assertEquals("15/08/2023", resultado1);

        String resultado2 = conversor.convertirTimestampAString(ts, "yyyy-MM-dd HH:mm:ss");
        assertEquals("2023-08-15 14:30:45", resultado2);

        String resultado3 = conversor.convertirTimestampAString(ts, "dd/MM/yyyy HH:mm");
        assertEquals("15/08/2023 14:30", resultado3);
    }

    @Test
    void convertirDateAString_conFormatosVariados() {
        Date date = Date.valueOf(LocalDate.of(2023, 8, 15));

        String resultado1 = conversor.convertirDateAString(date, "dd/MM/yyyy");
        assertEquals("15/08/2023", resultado1);

        String resultado2 = conversor.convertirDateAString(date, "yyyy-MM-dd");
        assertEquals("2023-08-15", resultado2);

        String resultado3 = conversor.convertirDateAString(date, "EEEE, dd 'de' MMMM 'de' yyyy");
        assertNotNull(resultado3);
    }

    @Test
    void convertirTimestampADate_conservaSoloLaFecha() {
        Timestamp ts1 = Timestamp.valueOf(LocalDateTime.of(2023, 8, 15, 8, 00));
        Timestamp ts2 = Timestamp.valueOf(LocalDateTime.of(2023, 8, 15, 20, 30));

        Date resultado1 = conversor.convertirTimestampADate(ts1);
        Date resultado2 = conversor.convertirTimestampADate(ts2);

        //Ambos deben convertir a la misma fecha sin importar la hora
        assertEquals(resultado1, resultado2);
        assertEquals(Date.valueOf(LocalDate.of(2023, 8, 15)), resultado1);
    }

    @Test
    void convertirDateATimestamp_estableceHoraEnCeros() {
        Date date = Date.valueOf(LocalDate.of(2023, 8, 15));

        Timestamp resultado = conversor.convertirDateATimestamp(date);

        LocalDateTime ldt = resultado.toLocalDateTime();
        assertEquals(0, ldt.getHour());
        assertEquals(0, ldt.getMinute());
        assertEquals(0, ldt.getSecond());
    }
}

