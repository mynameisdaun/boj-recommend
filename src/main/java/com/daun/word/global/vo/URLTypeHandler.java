package com.daun.word.global.vo;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@MappedJdbcTypes(JdbcType.VARCHAR)
public class URLTypeHandler implements TypeHandler<URL> {

    @Override
    public void setParameter(PreparedStatement ps, int i, URL parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, parameter.getUrl());
    }

    @Override
    public URL getResult(ResultSet rs, String columnName) throws SQLException {
        return new URL(rs.getInt(columnName));
    }

    @Override
    public URL getResult(ResultSet rs, int columnIndex) throws SQLException {
        return new URL(rs.getInt(columnIndex));
    }

    @Override
    public URL getResult(CallableStatement cs, int columnIndex) throws SQLException {
        return new URL(cs.getInt(columnIndex));
    }
}
