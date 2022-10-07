package com.daun.word.domain.member.domain.vo;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@MappedJdbcTypes(JdbcType.VARCHAR)
public class NicknameTypeHandler implements TypeHandler<Nickname> {

    @Override
    public void setParameter(PreparedStatement ps, int i, Nickname parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, parameter.getValue());
    }

    @Override
    public Nickname getResult(ResultSet rs, String columnName) throws SQLException {
        return new Nickname(rs.getString(columnName));
    }

    @Override
    public Nickname getResult(ResultSet rs, int columnIndex) throws SQLException {
        return new Nickname(rs.getString(columnIndex));
    }

    @Override
    public Nickname getResult(CallableStatement cs, int columnIndex) throws SQLException {
        return new Nickname(cs.getString(columnIndex));
    }
}
