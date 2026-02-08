package com.example;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.math.BigDecimal;
import java.sql.Date;

public class ComparadorDeFechas implements ComparadorDeFechasSrv {
     private Timestamp tFecha;
    private Date dFecha;
    private BigDecimal bfecha;

    public ComparadorDeFechas(Timestamp tFecha) {
        this.tFecha = tFecha;
    }

    public ComparadorDeFechas(Date dFecha) {
        this.dFecha = dFecha;
    }

    public ComparadorDeFechas(BigDecimal bfecha) {
        this.bfecha = bfecha;
    }

    public boolean esMayorQue(Timestamp fecha) {
        return getStartOfDay(this.tFecha).isAfter(getStartOfDay(fecha)); 
    }
 
    public boolean esMenorQue(Timestamp fecha) {
        return getStartOfDay(this.tFecha).isBefore(getStartOfDay(fecha)); 
    }

    public boolean esIgualA(Timestamp fecha) {
        return getStartOfDay(this.tFecha).isEqual(getStartOfDay(fecha)); 
    }
    public boolean esMayorQue(Date fecha) {
        return this.dFecha.after(fecha); 
    }
 
    public boolean esMenorQue(Date fecha) {
        return this.dFecha.before(fecha);
    }

    public boolean esIgualA(Date fecha) {
        return this.dFecha.equals(fecha);
    }

    public boolean esMayorQue(BigDecimal fecha, String formato) {
       return this.esMayorQue(this.bfecha, formato, fecha, formato);
    }

    public boolean esMenorQue(BigDecimal fecha, String formato) {
        return this.esMenorQue(this.bfecha, formato, fecha, formato);
    }


    public boolean esIgualQue(BigDecimal fecha, String formato) {
        return this.esIgualQue(this.bfecha, formato, fecha, formato);
    }

    public boolean esMayorQue(String fecha, String formato) {
        return this.esMayorQue(this.bfecha.toString(), formato, fecha, formato);
    }

    public boolean esMenorQue(String fecha, String formato) {
        return this.esMenorQue(this.bfecha.toString(), formato, fecha, formato);
    }

    public boolean esIgualQue(String fecha, String formato) {
        return this.esIgualQue(this.bfecha.toString(), formato, fecha, formato);
    }
        

    @Override
    public boolean esMayorQue(BigDecimal fechaInicio, String formatoInicio, BigDecimal fechaFin, String formatoFin) {
        return this.esMayorQue(fechaInicio.toString(), formatoInicio, fechaFin.toString(), formatoFin);
    }

    @Override
    public boolean esMenorQue(BigDecimal fechaInicio, String formatoInicio, BigDecimal fechaFin, String formatoFin) {
        return this.esMenorQue(fechaInicio.toString(), formatoInicio, fechaFin.toString(), formatoFin);
    }

    @Override
    public boolean esIgualQue(BigDecimal fechaInicio, String formatoInicio, BigDecimal fechaFin, String formatoFin) {
        return this.esIgualQue(fechaInicio.toString(), formatoInicio, fechaFin.toString(), formatoFin);
    }
    

    /**
     * Compara dos fechas representadas como cadenas de texto, utilizando el formato especificado para cada una.
     * Si se quiere convertir un Date a String para convertir fecha.toLocalDate().format(DateTimeFormatter.ofPattern(formato) to String 
     * Si se quiere convertir un Timestamp a String para convertir fecha.toLocalDateTime().format(DateTimeFormatter.ofPattern(formato)) to String 
    */
    @Override
    public boolean esMayorQue(String fechaInicio, String formatoInicio, String fechaFin, String formatoFin) {
        if (fechaInicio == null || fechaInicio.isBlank() || fechaFin == null || fechaFin.isBlank()) {
            return false;      
        }
        return getDate(new BigDecimal(fechaInicio), formatoInicio).isAfter(getDate(new BigDecimal(fechaFin), formatoFin));
    }

    /**
     * Compara dos fechas representadas como cadenas de texto, utilizando el formato especificado para cada una.
     * Si se quiere convertir un Date a String para convertir fecha.toLocalDate().format(DateTimeFormatter.ofPattern(formato) to String 
     * Si se quiere convertir un Timestamp a String para convertir fecha.toLocalDateTime().format(DateTimeFormatter.ofPattern(formato)) to String 
    */
    @Override
    public boolean esMayorOIgualQue(String fechaInicio, String formatoInicio, String fechaFin, String formatoFin) {
        if (fechaInicio == null || fechaInicio.isBlank() || fechaFin == null || fechaFin.isBlank()) {
            return false;      
        }
        return this.esMayorQue(fechaInicio, formatoInicio, fechaFin, formatoFin) || 
               this.esIgualQue(fechaInicio, formatoInicio, fechaFin, formatoFin);
    }

    /**
     * Compara dos fechas representadas como cadenas de texto, utilizando el formato especificado para cada una.
     * Si se quiere convertir un Date a String para convertir fecha.toLocalDate().format(DateTimeFormatter.ofPattern(formato) to String 
     * Si se quiere convertir un Timestamp a String para convertir fecha.toLocalDateTime().format(DateTimeFormatter.ofPattern(formato)) to String 
    */
    @Override
    public boolean esMenorQue(String fechaInicio, String formatoInicio, String fechaFin, String formatoFin) {
         if (fechaInicio == null || fechaInicio.isBlank() || fechaFin == null || fechaFin.isBlank()) {
            return false;      
        }
        return getDate(new BigDecimal(fechaInicio), formatoInicio).isBefore(getDate(new BigDecimal(fechaFin), formatoFin));
    }

    /**
     * Compara dos fechas representadas como cadenas de texto, utilizando el formato especificado para cada una.
     * Si se quiere convertir un Date a String para convertir fecha.toLocalDate().format(DateTimeFormatter.ofPattern(formato) to String 
     * Si se quiere convertir un Timestamp a String para convertir fecha.toLocalDateTime().format(DateTimeFormatter.ofPattern(formato)) to String 
    */
    @Override
    public boolean esMenorOIgualQue(String fechaInicio, String formatoInicio, String fechaFin, String formatoFin) {
         if (fechaInicio == null || fechaInicio.isBlank() || fechaFin == null || fechaFin.isBlank()) {
            return false;      
        }
        return this.esMenorQue(fechaInicio, formatoInicio, fechaFin, formatoFin) || 
               this.esIgualQue(fechaInicio, formatoInicio, fechaFin, formatoFin);
    }

    /**
     * Compara dos fechas representadas como cadenas de texto, utilizando el formato especificado para cada una.
     * Si se quiere convertir un Date a String para convertir fecha.toLocalDate().format(DateTimeFormatter.ofPattern(formato) to String 
     * Si se quiere convertir un Timestamp a String para convertir fecha.toLocalDateTime().format(DateTimeFormatter.ofPattern(formato)) to String 
    */
    @Override
    public boolean esIgualQue(String fechaInicio, String formatoInicio, String fechaFin, String formatoFin) {
         if (fechaInicio == null || fechaInicio.isBlank() || fechaFin == null || fechaFin.isBlank()) {
            return false;      
        }
        return getDate(new BigDecimal(fechaInicio), formatoInicio).equals(getDate(new BigDecimal(fechaFin), formatoFin));
    }

    @Override
    public boolean esMayorQue(Timestamp fechaInicio, Timestamp fechaFin) {
        if (fechaInicio == null ||  fechaFin == null)  {
            return false;              }
        return getStartOfDay(fechaInicio)
        .isAfter(getStartOfDay(fechaFin));
    }

    @Override
    public boolean esMenorQue(Timestamp fechaInicio, Timestamp fechaFin) {
         if (fechaInicio == null ||  fechaFin == null)  {
            return false;      
        }
        return getStartOfDay(fechaInicio).isBefore(getStartOfDay(fechaFin));
    }

    @Override
    public boolean esIgualQue(Timestamp fechaInicio, Timestamp fechaFin) {
         if (fechaInicio == null ||  fechaFin == null)  {
            return false;      
        }
        return getStartOfDay(fechaInicio).equals(getStartOfDay(fechaFin));
    }

    @Override
    public boolean esMayorQue(Date fechaInicio, Date fechaFin) {
        if (fechaInicio == null ||  fechaFin == null)  {
            return false;              }
        return getStartOfDay(fechaInicio)
        .isAfter(getStartOfDay(fechaFin));
    }

    @Override
    public boolean esMenorQue(Date fechaInicio, Date fechaFin) {
         if (fechaInicio == null ||  fechaFin == null)  {
            return false;      
        }
       return getStartOfDay(fechaInicio)
        .isBefore(getStartOfDay(fechaFin));
    }

    @Override
    public boolean esIgualQue(Date fechaInicio, Date fechaFin) {
         if (fechaInicio == null ||  fechaFin == null)  {
            return false;      
        }
         return getStartOfDay(fechaInicio)
        .equals(getStartOfDay(fechaFin));
    }
    
    
    private LocalDate getStartOfDay(Timestamp fecha) {
        return fecha.toLocalDateTime().toLocalDate().atStartOfDay(ZoneId.systemDefault()).toLocalDate();
    }

     private LocalDate getStartOfDay(Date fecha) {
        return fecha.toLocalDate().atStartOfDay(ZoneId.systemDefault()).toLocalDate();
    }

    private LocalDate getDate(BigDecimal fecha, String formato) {
        if (BigDecimal.ZERO.compareTo(fecha) == 0 ) {
            return LocalDate.parse("01/01/0001", DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        } 
        return LocalDate.parse(fecha.toPlainString(), DateTimeFormatter.ofPattern(formato));
    }
 }
