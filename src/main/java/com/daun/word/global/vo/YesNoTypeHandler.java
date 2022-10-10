package com.daun.word.global.vo;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@MappedJdbcTypes(JdbcType.CHAR)
public class YesNoTypeHandler implements TypeHandler<YesNo> {

    @Override
    public void setParameter(PreparedStatement ps, int i, YesNo parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, parameter.name());
    }

    @Override
    public YesNo getResult(ResultSet rs, String columnName) throws SQLException {
        return YesNo.valueOf(rs.getString(columnName));
    }

    @Override
    public YesNo getResult(ResultSet rs, int columnIndex) throws SQLException {
        return YesNo.valueOf(rs.getString(columnIndex));
    }

    @Override
    public YesNo getResult(CallableStatement cs, int columnIndex) throws SQLException {
        return YesNo.valueOf(cs.getString(columnIndex));
    }
}
