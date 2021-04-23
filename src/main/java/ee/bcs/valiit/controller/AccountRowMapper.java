package ee.bcs.valiit.controller;

import ee.bcs.valiit.dto.AccountDTOold;

import java.sql.ResultSet;
import java.sql.SQLException;


public class AccountRowMapper implements org.springframework.jdbc.core.RowMapper<AccountDTOold> {

    @Override
    public AccountDTOold mapRow(ResultSet rs, int rowNum) throws SQLException{
        AccountDTOold response = new AccountDTOold();
        response.setAccountnumber(rs.getString("account_number"));
        response.setBalance(rs.getDouble("balance"));
        response.setLocked(rs.getBoolean("locked"));
        //TODO add all rows
        return response;
    }
}