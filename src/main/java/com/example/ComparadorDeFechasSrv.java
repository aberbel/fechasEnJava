package com.example;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;

public interface ComparadorDeFechasSrv {

    boolean esMayorQue(BigDecimal fechaInicio, String formatoInicio, BigDecimal fechaFin, String formatoFin);

    boolean esMenorQue(BigDecimal fechaInicio, String formatoInicio, BigDecimal fechaFin, String formatoFin);

    boolean esIgualQue(BigDecimal fechaInicio, String formatoInicio, BigDecimal fechaFin, String formatoFin);

    /**
     * Compara dos fechas representadas como cadenas de texto, utilizando el formato especificado para cada una.
     * Si se quiere convertir un Date a String para convertir fecha.toLocalDate().format(DateTimeFormatter.ofPattern(formato) to String 
     * Si se quiere convertir un Timestamp a String para convertir fecha.toLocalDateTime().format(DateTimeFormatter.ofPattern(formato)) to String 
    */
    boolean esMayorQue(String fechaInicio, String formatoInicio, String fechaFin, String formatoFin);

    /**
     * Compara dos fechas representadas como cadenas de texto, utilizando el formato especificado para cada una.
     * Si se quiere convertir un Date a String para convertir fecha.toLocalDate().format(DateTimeFormatter.ofPattern(formato) to String 
     * Si se quiere convertir un Timestamp a String para convertir fecha.toLocalDateTime().format(DateTimeFormatter.ofPattern(formato)) to String 
    */
    boolean esMayorOIgualQue(String fechaInicio, String formatoInicio, String fechaFin, String formatoFin);

    /**
     * Compara dos fechas representadas como cadenas de texto, utilizando el formato especificado para cada una.
     * Si se quiere convertir un Date a String para convertir fecha.toLocalDate().format(DateTimeFormatter.ofPattern(formato) to String 
     * Si se quiere convertir un Timestamp a String para convertir fecha.toLocalDateTime().format(DateTimeFormatter.ofPattern(formato)) to String 
    */
    boolean esMenorQue(String fechaInicio, String formatoInicio, String fechaFin, String formatoFin);

    /**
     * Compara dos fechas representadas como cadenas de texto, utilizando el formato especificado para cada una.
     * Si se quiere convertir un Date a String para convertir fecha.toLocalDate().format(DateTimeFormatter.ofPattern(formato) to String 
     * Si se quiere convertir un Timestamp a String para convertir fecha.toLocalDateTime().format(DateTimeFormatter.ofPattern(formato)) to String 
    */
    boolean esMenorOIgualQue(String fechaInicio, String formatoInicio, String fechaFin, String formatoFin);

    /**
     * Compara dos fechas representadas como cadenas de texto, utilizando el formato especificado para cada una.
     * Si se quiere convertir un Date a String para convertir fecha.toLocalDate().format(DateTimeFormatter.ofPattern(formato) to String 
     * Si se quiere convertir un Timestamp a String para convertir fecha.toLocalDateTime().format(DateTimeFormatter.ofPattern(formato)) to String 
    */
    boolean esIgualQue(String fechaInicio, String formatoInicio, String fechaFin, String formatoFin);

    boolean esMayorQue(Timestamp fechaInicio, Timestamp fechaFin);

    boolean esMenorQue(Timestamp fechaInicio, Timestamp fechaFin);

    boolean esIgualQue(Timestamp fechaInicio, Timestamp fechaFin);

    boolean esMayorQue(Date fechaInicio, Date fechaFin);

    boolean esMenorQue(Date fechaInicio, Date fechaFin);

    boolean esIgualQue(Date fechaInicio, Date fechaFin);

}