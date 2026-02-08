package com;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.ComparadorDeFechas;
public class FechaTest {

    private ComparadorDeFechas fecha1;
    private ComparadorDeFechas fecha2;
    private ComparadorDeFechas fecha3;


    private Timestamp ts1; // 2026-02-07 08:30:45
    private Timestamp ts2; // 2026-02-07 14:15:30
    private Timestamp ts3; // 2026-02-07 23:59:59
    private Timestamp ts4; // 2026-02-06 10:00:00
    private Timestamp ts5; // 2026-02-08 05:30:00
    
    private Date d1; // 2026-02-07
    private Date d2; // 2026-02-06
    private Date d3; // 2026-02-08

    @BeforeEach
    public void setUp() {
        // Timestamp con diferentes horas del mismo día
        ts1 = Timestamp.valueOf(LocalDateTime.of(2026, 2, 7, 8, 30, 45));
        ts2 = Timestamp.valueOf(LocalDateTime.of(2026, 2, 7, 14, 15, 30));
        ts3 = Timestamp.valueOf(LocalDateTime.of(2026, 2, 7, 23, 59, 59));
        
        // Timestamp con diferentes días
        ts4 = Timestamp.valueOf(LocalDateTime.of(2026, 2, 6, 10, 0, 0));
        ts5 = Timestamp.valueOf(LocalDateTime.of(2026, 2, 8, 5, 30, 0));
        
        // Dates
        d1 = Date.valueOf("2026-02-07");
        d2 = Date.valueOf("2026-02-06");
        d3 = Date.valueOf("2026-02-08");
        
        // Inicializar objetos Fecha con Timestamp
        fecha1 = new ComparadorDeFechas(ts1); // 2026-02-07 08:30:45
        fecha2 = new ComparadorDeFechas(ts2); // 2026-02-07 14:15:30
        fecha3 = new ComparadorDeFechas(ts5); // 2026-02-08 05:30:00
    }

    // ====== TESTS CON TIMESTAMP ======
    
    @Test
    public void testEsMayorQueTimestampMismoDia() {
        // ts1 y ts2 son del mismo día, diferentes horas
        // esMayorQue compara solo fechas, no horas
        assertFalse(fecha1.esMayorQue(ts2), 
                    "ts1 (08:30) no es mayor que ts2 (14:15) - mismo día");
        assertFalse(fecha2.esMayorQue(ts1), 
                    "ts2 (14:15) no es mayor que ts1 (08:30) - mismo día");

        assertTrue(fecha3.esMayorQue(ts1), 
                    "ts3 (08/02) es mayor que ts1 (07/02)");
    }

    @Test
    public void testEsMayorQueTimestampDiaAnterior() {
        // ts1 (2026-02-07) es mayor que ts4 (2026-02-06)
        assertTrue(fecha1.esMayorQue(ts4), 
                   "ts1 (07/02) es mayor que ts4 (06/02)");
    }

    @Test
    public void testEsMayorQueTimestampDiaPosterior() {
        // ts1 (2026-02-07) no es mayor que ts5 (2026-02-08)
        assertFalse(fecha1.esMayorQue(ts5), 
                    "ts1 (07/02) no es mayor que ts5 (08/02)");
    }

    @Test
    public void testEsMenorQueTimestampMismoDia() {
        // ts1 y ts2 son del mismo día
        assertFalse(fecha1.esMenorQue(ts2), 
                    "ts1 no es menor que ts2 - mismo día");
    }

    @Test
    public void testEsMenorQueTimestampDiaAnterior() {
        // ts1 (2026-02-07) no es menor que ts4 (2026-02-06)
        assertFalse(fecha1.esMenorQue(ts4), 
                    "ts1 (07/02) no es menor que ts4 (06/02)");
    }

    @Test
    public void testEsMenorQueTimestampDiaPosterior() {
        // ts1 (2026-02-07) es menor que ts5 (2026-02-08)
        assertTrue(fecha1.esMenorQue(ts5), 
                   "ts1 (07/02) es menor que ts5 (08/02)");
    }

    @Test
    public void testEsIgualATimestampMismoDia() {
        // ts1 y ts2 son del mismo día, por eso son iguales
        assertTrue(fecha1.esIgualA(ts2), 
                   "ts1 es igual a ts2 - mismo día");
    }

    @Test
    public void testEsIgualATimestampDiferente() {
        // ts1 (07/02) no es igual a ts5 (08/02)
        assertFalse(fecha1.esIgualA(ts5), 
                    "ts1 (07/02) no es igual a ts5 (08/02)");
    }

    // ====== TESTS CON DATE ======
    
    @Test
    public void testEsMayorQueDate() {
        ComparadorDeFechas fechaDate = new ComparadorDeFechas(d1); // 2026-02-07
        assertTrue(fechaDate.esMayorQue(d2), 
                   "d1 (07/02) es mayor que d2 (06/02)");
        assertFalse(fechaDate.esMayorQue(d3), 
                    "d1 (07/02) no es mayor que d3 (08/02)");
    }

    @Test
    public void testEsMenorQueDate() {
        ComparadorDeFechas fechaDate = new ComparadorDeFechas(d1); // 2026-02-07
        assertFalse(fechaDate.esMenorQue(d2), 
                    "d1 (07/02) no es menor que d2 (06/02)");
        assertTrue(fechaDate.esMenorQue(d3), 
                   "d1 (07/02) es menor que d3 (08/02)");
    }

    @Test
    public void testEsIgualADate() {
        ComparadorDeFechas fechaDate = new ComparadorDeFechas(d1); // 2026-02-07
        assertTrue(fechaDate.esIgualA(d1), 
                   "d1 es igual a sí mismo");
        assertFalse(fechaDate.esIgualA(d2), 
                    "d1 (07/02) no es igual a d2 (06/02)");
    }

    // ====== TESTS CON BIGDECIMAL (yyyymmdd) ======
    
    @Test
    public void testEsMayorQueValorBigDecimal() {
        ComparadorDeFechas fechaBD = new ComparadorDeFechas(new BigDecimal("20260207"));
        assertTrue(fechaBD.esMayorQue(new BigDecimal("20260206"), "yyyyMMdd"), 
                   "20260207 es mayor que 20260206");
        assertFalse(fechaBD.esMayorQue(new BigDecimal("20260208"), "yyyyMMdd"), 
                    "20260207 no es mayor que 20260208");
    }

    @Test
    public void testEsMenorQueValorBigDecimal() {
        ComparadorDeFechas fechaBD = new ComparadorDeFechas(new BigDecimal("20260207"));
        assertFalse(fechaBD.esMenorQue(new BigDecimal("20260206"), "yyyyMMdd"), 
                    "20260207 no es menor que 20260206");
        assertTrue(fechaBD.esMenorQue(new BigDecimal("20260208"), "yyyyMMdd"), 
                   "20260207 es menor que 20260208");
    }

    @Test
    public void testEsIgualQueValorBigDecimal() {
        ComparadorDeFechas fechaBD = new ComparadorDeFechas(new BigDecimal("20260207"));
        assertTrue(fechaBD.esIgualQue(new BigDecimal("20260207"), "yyyyMMdd"), 
                   "20260207 es igual a 20260207");
        assertFalse(fechaBD.esIgualQue(new BigDecimal("20260206"), "yyyyMMdd"), 
                    "20260207 no es igual a 20260206");
    }

    // ====== TESTS CON STRING (yyyymmdd) ======
    
    @Test
    public void testEsMayorQueValorString() {
        ComparadorDeFechas fechaBD = new ComparadorDeFechas(new BigDecimal("20260207"));
        assertTrue(fechaBD.esMayorQue("20260206", "yyyyMMdd"), 
                   "20260207 es mayor que 20260206");
        assertFalse(fechaBD.esMayorQue("20260208", "yyyyMMdd"), 
                    "20260207 no es mayor que 20260208");
    }

    @Test
    public void testEsMenorQueValorString() {
        ComparadorDeFechas fechaBD = new ComparadorDeFechas(new BigDecimal("20260207"));
        assertFalse(fechaBD.esMenorQue("20260206", "yyyyMMdd"), 
                    "20260207 no es menor que 20260206");
        assertTrue(fechaBD.esMenorQue("20260208", "yyyyMMdd"), 
                   "20260207 es menor que 20260208");
    }

    @Test
    public void testEsIgualQueValorString() {
        ComparadorDeFechas fechaBD = new ComparadorDeFechas(new BigDecimal("20260207"));
        assertTrue(fechaBD.esIgualQue("20260207", "yyyyMMdd"), 
                   "20260207 es igual a 20260207");
        assertFalse(fechaBD.esIgualQue("20260206", "yyyyMMdd"), 
                    "20260207 no es igual a 20260206");
    }

    // ====== TESTS CON DIFERENTES HORAS ======
    
    @Test
    public void testTimestampsConDiferentesHorasIgnoranLaHora() {
        // Todos estos timestamps son del 07/02/2026 pero con diferentes horas
        Timestamp am = Timestamp.valueOf(LocalDateTime.of(2026, 2, 7, 6, 0, 0));
        Timestamp noon = Timestamp.valueOf(LocalDateTime.of(2026, 2, 7, 12, 0, 0));
        Timestamp pm = Timestamp.valueOf(LocalDateTime.of(2026, 2, 7, 18, 0, 0));
        Timestamp late = Timestamp.valueOf(LocalDateTime.of(2026, 2, 7, 23, 59, 59));
        
        ComparadorDeFechas f = new ComparadorDeFechas(am);
        
        // Todas las comparaciones deben ignorar la hora (comparar solo fechas)
        assertTrue(f.esIgualA(noon), "06:00 es igual a 12:00 (mismo día)");
        assertTrue(f.esIgualA(pm), "06:00 es igual a 18:00 (mismo día)");
        assertTrue(f.esIgualA(late), "06:00 es igual a 23:59:59 (mismo día)");
    }

    @Test
    public void testComparacionesComplejasConVariasHoras() {
        // Crear timestamps del mismo día pero diferentes horas
        Timestamp early = Timestamp.valueOf(LocalDateTime.of(2026, 2, 7, 1, 0, 0));
        Timestamp late = Timestamp.valueOf(LocalDateTime.of(2026, 2, 7, 23, 59, 59));
        Timestamp nextDay = Timestamp.valueOf(LocalDateTime.of(2026, 2, 8, 0, 0, 1));
        
        ComparadorDeFechas earlyFecha = new ComparadorDeFechas(early);
        ComparadorDeFechas lateFecha = new ComparadorDeFechas(late);
        ComparadorDeFechas nextDayFecha = new ComparadorDeFechas(nextDay);
        
        // Mismo día, diferentes horas - deben ser iguales
        assertTrue(earlyFecha.esIgualA(late), 
                   "01:00 es igual a 23:59:59 (mismo día)");
        
        // Días diferentes - deben compararse correctamente
        assertTrue(earlyFecha.esMenorQue(nextDay), 
                   "07/02 es menor que 08/02");
        assertTrue(lateFecha.esMenorQue(nextDay), 
                   "07/02 es menor que 08/02");
        assertTrue(nextDayFecha.esMayorQue(early), 
                   "08/02 es mayor que 07/02");
    }
}
