package com.daun.word.domain.member.domain.vo;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@MappedJdbcTypes(JdbcType.VARCHAR)
public class SocialTypeHandler implements TypeHandler<SocialType> {

    @Override
    public void setParameter(PreparedStatement ps, int i, SocialType parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, parameter.name());
    }

    @Override
    public SocialType getResult(ResultSet rs, String columnName) throws SQLException {
        return SocialType.valueOf(rs.getString(columnName));
    }

    @Override
    public SocialType getResult(ResultSet rs, int columnIndex) throws SQLException {
        return SocialType.valueOf(rs.getString(columnIndex));
    }

    @Override
    public SocialType getResult(CallableStatement cs, int columnIndex) throws SQLException {
        return SocialType.valueOf(cs.getString(columnIndex));
    }
}
