package ee.bcs.valiit.controller;

import ee.bcs.valiit.tasks.AccountDTO;

import java.sql.ResultSet;
import java.sql.SQLException;


public class ObjectRowMapper implements org.springframework.jdbc.core.RowMapper<AccountDTO> {

    @Override
    public AccountDTO mapRow(ResultSet rs, int rowNum) throws SQLException{
        AccountDTO account = new AccountDTO();
        account.setAccountnumber(rs.getString("account_number"));
        account.setBalance(rs.getDouble("balance"));
        account.setAccount_id(rs.getInt("account_id"));
        account.setAccountnumber2(rs.getString("account_number2"));
        account.setLocked(rs.getBoolean("locked"));
        return account;
    }
}
