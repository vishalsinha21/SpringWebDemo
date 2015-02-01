package org.vs.util;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.LocalDate;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.*;

public final class DaoUtils {

    private DaoUtils() {
        // hidden because all methods are static
    }

    public static DateTime sqlTimestampToJodaDateTime(Timestamp date) {
        return date == null ? null : new DateTime(date);
    }

    public static LocalDate sqlTimestampToJodaDateTime(Date date) {
        return date == null ? null : new LocalDate(date);
    }

    public static Date convertToDateOrNull(DateTime date) {
        return date == null ? null : new Date(date.getMillis());
    }

    public static Date convertToDateOrNull(LocalDate date) {
        return date == null ? null : new Date(date.toDateTimeAtCurrentTime().getMillis());
    }

    public static Timestamp convertToTimestampOrNull(DateTime date) {
        return date == null ? null : new Timestamp(date.getMillis());
    }

    protected static Integer getInteger(ResultSet resultSet, String columnName) throws SQLException {
        int value = resultSet.getInt(columnName);
        return resultSet.wasNull() ? null : value;
    }

    protected static void setInteger(PreparedStatement preparedStatement, int columnIndex, Integer value) throws SQLException {
        if (value == null) {
            preparedStatement.setNull(columnIndex, Types.BIT);
        } else {
            preparedStatement.setInt(columnIndex, value);
        }
    }

    public static BigDecimal getBigDecimalOrNull(ResultSet rs, String columnName) throws SQLException {
        // always create BigDecimal either by using String in constructor OR use .valueOf
        String result = rs.getString(columnName);
        if (!rs.wasNull()) {
            return new BigDecimal(result);
        }
        return null;
    }


    public static BigInteger getBigIntegerOrNull(ResultSet rs, String columnName) throws SQLException {
        String result = rs.getString(columnName);
        if (!rs.wasNull()) {
            return new BigInteger(result);
        }
        return null;
    }

    public static DateTime getDatetimeOrNullFromTimestamp(ResultSet rs, String columnName) throws SQLException {
        Timestamp time = rs.getTimestamp(columnName);

        if (rs.wasNull())
            return null;

        return new DateTime(time.getTime());
    }

    public static DateTime getDatetimeWithTzOrNullFromTimestamp(ResultSet rs, String columnName, String columnTimeZone) throws SQLException {
        Timestamp time = rs.getTimestamp(columnName);

        if (rs.wasNull())
            return null;

        DateTime dateTime = new DateTime(time.getTime());
        String olsonId = rs.getString(columnTimeZone);

        if (rs.wasNull())
            return dateTime; // timezone is then this server tz ... better than null/exception I guess

        return dateTime.withZone(DateTimeZone.forID(olsonId));
    }

    public static DateTime getDatetimeOrNullFromDate(ResultSet rs, String columnName) throws SQLException {
        Date date = rs.getDate(columnName);

        if (rs.wasNull())
            return null;

        return new DateTime(date.getTime());
    }

    public static BigInteger convertToBigInteger(Object candidate) {
        if (candidate instanceof BigInteger) {
            return (BigInteger) candidate;
        } else if (candidate instanceof BigDecimal) {
            BigDecimal decimal = (BigDecimal) candidate;
            return decimal.toBigInteger();
        } else if (candidate instanceof Number) {
            Number number = (Number) candidate;
            return BigInteger.valueOf(number.longValue());  // and pray...
        }
        String objectType = candidate != null ? candidate.getClass().toString() : "null";
        throw new ClassCastException("Could not convert " + objectType + ". Is not of type BigInteger, BigDecimal, Number, or a numeric primitive");
    }
}
