package com.example;

import java.sql.Timestamp;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ConversorDeFechas implements ConversorDeFechasSrv {

@Override
public String convertirFecha(String fecha, String formatoEntrada, String formatoSalida) {
    DateTimeFormatter formatterEntrada = DateTimeFormatter.ofPattern(formatoEntrada);
    DateTimeFormatter formatterSalida = DateTimeFormatter.ofPattern(formatoSalida);
    LocalDate fechaLocalDate = LocalDate.parse(fecha, formatterEntrada);
    return fechaLocalDate.format(formatterSalida);
}

@Override
public Timestamp convertirStringATimestamp(String fecha, String formato) {
    if ("0".equals(fecha) || fecha.isEmpty()){
        return Timestamp.valueOf(LocalDateTime.parse("01/01/0001 00:00:00", DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")));
    }
    return Timestamp.valueOf(LocalDateTime.parse(fecha, DateTimeFormatter.ofPattern(formato)));
}

@Override
public Date convertirStringADate(String fecha, String formato) {
    if ("0".equals(fecha) || fecha.isEmpty()){
        return Date.valueOf(LocalDateTime.parse("01/01/0001 00:00:00", DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")).toLocalDate());
    }
    return Date.valueOf(LocalDateTime.parse(fecha, DateTimeFormatter.ofPattern(formato)).toLocalDate());
}

@Override
public String convertirTimestampAString(Timestamp fecha, String formatoSalida) {
    if (fecha == null || fecha.equals(Timestamp.valueOf(LocalDateTime.parse("01/01/0001 00:00:00", DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"))))){
        return "";
    }
    return fecha.toLocalDateTime().format(DateTimeFormatter.ofPattern(formatoSalida));
}
@Override
public String convertirDateAString(Date fecha, String formatoSalida) {
    if (fecha == null || fecha.equals(Date.valueOf(LocalDateTime.parse("01/01/0001 00:00:00", DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")).toLocalDate()))){
        return "";
    }
    return fecha.toLocalDate().format(DateTimeFormatter.ofPattern(formatoSalida));
}

@Override
public Date convertirTimestampADate(Timestamp fecha) {
    if (fecha == null || fecha.equals(Timestamp.valueOf(LocalDateTime.parse("01/01/0001 00:00:00", DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"))))){
        return Date.valueOf(LocalDateTime.parse("01/01/0001 00:00:00", DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")).toLocalDate());
    }
    return new Date(Timestamp.valueOf(fecha.toLocalDateTime().toLocalDate().atStartOfDay()).getTime());
}
  
@Override
public Timestamp convertirDateATimestamp(Date fecha) {
    if (fecha == null || fecha.equals(Date.valueOf(LocalDateTime.parse("01/01/0001 00:00:00", DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")).toLocalDate()))){
        return Timestamp.valueOf(LocalDateTime.parse("01/01/0001 00:00:00", DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")));
    }
    return Timestamp.valueOf(fecha.toLocalDate().atStartOfDay());
}


}
