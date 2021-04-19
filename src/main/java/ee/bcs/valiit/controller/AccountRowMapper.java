package ee.bcs.valiit.controller;

import ee.bcs.valiit.tasks.AccountDTO;

import java.sql.ResultSet;
import java.sql.SQLException;


public class AccountRowMapper implements org.springframework.jdbc.core.RowMapper<AccountDTO> {

    @Override
    public AccountDTO mapRow(ResultSet rs, int rowNum) throws SQLException{
        AccountDTO response = new AccountDTO();
        response.setAccountnumber(rs.getString("account_number"));
        response.setBalance(rs.getDouble("balance"));
        response.setAccount_id(rs.getInt("account_id"));
        response.setLocked(rs.getBoolean("locked"));
        return response;
    }
}