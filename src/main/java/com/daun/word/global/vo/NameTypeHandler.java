package com.daun.word.global.vo;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@MappedJdbcTypes(JdbcType.VARCHAR)
public class NameTypeHandler implements TypeHandler<Name> {

    @Override
    public void setParameter(PreparedStatement ps, int i, Name parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, parameter.getValue());
    }

    @Override
    public Name getResult(ResultSet rs, String columnName) throws SQLException {
        return new Name(rs.getString(columnName));
    }

    @Override
    public Name getResult(ResultSet rs, int columnIndex) throws SQLException {
        return new Name(rs.getString(columnIndex));
    }

    @Override
    public Name getResult(CallableStatement cs, int columnIndex) throws SQLException {
        return new Name(cs.getString(columnIndex));
    }
}
