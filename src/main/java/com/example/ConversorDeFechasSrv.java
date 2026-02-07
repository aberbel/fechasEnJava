package com.example;

import java.sql.Date;
import java.sql.Timestamp;

public interface ConversorDeFechasSrv {

    Timestamp convertirStringATimestamp(String fecha, String formato);

    Date convertirStringADate(String fecha, String formato);

    String convertirTimestampAString(Timestamp fecha, String formatoSalida);

    Date convertirTimestampADate(Timestamp fecha);

    String convertirDateAString(Date fecha, String formatoSalida);

    Timestamp convertirDateATimestamp(Date fecha);

}