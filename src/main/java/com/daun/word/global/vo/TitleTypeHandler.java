package com.daun.word.global.vo;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@MappedJdbcTypes(JdbcType.VARCHAR)
public class TitleTypeHandler implements TypeHandler<Title> {

    @Override
    public void setParameter(PreparedStatement ps, int i, Title parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, parameter.getTitle());
    }

    @Override
    public Title getResult(ResultSet rs, String columnName) throws SQLException {
        return new Title(rs.getString(columnName));
    }

    @Override
    public Title getResult(ResultSet rs, int columnIndex) throws SQLException {
        return new Title(rs.getString(columnIndex));
    }

    @Override
    public Title getResult(CallableStatement cs, int columnIndex) throws SQLException {
        return new Title(cs.getString(columnIndex));
    }
}
