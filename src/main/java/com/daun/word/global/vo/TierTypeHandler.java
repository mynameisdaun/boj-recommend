package com.daun.word.global.vo;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@MappedJdbcTypes(JdbcType.INTEGER)
public class TierTypeHandler implements TypeHandler<Tier> {

    @Override
    public void setParameter(PreparedStatement ps, int i, Tier parameter, JdbcType jdbcType) throws SQLException {
        ps.setInt(i, parameter.getLevel());
    }

    @Override
    public Tier getResult(ResultSet rs, String columnName) throws SQLException {
        return new Tier(rs.getInt(columnName));
    }

    @Override
    public Tier getResult(ResultSet rs, int columnIndex) throws SQLException {
        return new Tier(rs.getInt(columnIndex));
    }

    @Override
    public Tier getResult(CallableStatement cs, int columnIndex) throws SQLException {
        return new Tier(cs.getInt(columnIndex));
    }
}
