package ru.itis.servletsauth.utils;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface RowMapper<E> {
    E from(ResultSet var1, int var2) throws SQLException;
}

