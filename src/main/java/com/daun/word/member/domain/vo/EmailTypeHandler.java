package com.daun.word.member.domain.vo;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@MappedJdbcTypes(JdbcType.VARCHAR)
public class EmailTypeHandler implements TypeHandler<Email> {

    @Override
    public void setParameter(PreparedStatement ps, int i, Email parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, parameter.getValue());
    }

    @Override
    public Email getResult(ResultSet rs, String columnName) throws SQLException {
        return new Email(rs.getString(columnName));
    }

    @Override
    public Email getResult(ResultSet rs, int columnIndex) throws SQLException {
        return new Email(rs.getString(columnIndex));
    }

    @Override
    public Email getResult(CallableStatement cs, int columnIndex) throws SQLException {
        return new Email(cs.getString(columnIndex));
    }
}
