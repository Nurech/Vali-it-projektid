package ee.bcs.valiit.controller;

import ee.bcs.valiit.hibernate.TransactionDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;


public class TransactionRowMapper implements org.springframework.jdbc.core.RowMapper<TransactionDAO> {

    @Override
    public TransactionDAO mapRow(ResultSet rs, int rowNum) throws SQLException{
        TransactionDAO response = new TransactionDAO();
        response.setFrom_account(rs.getString("from_account"));
        response.setTo_account(rs.getString("to_account"));
        response.setDate_time(rs.getObject("date_time", LocalDateTime.class));
        response.setTransfer(rs.getDouble("transfer"));
        response.setDeduction(rs.getDouble("deduction"));
        return response;
    }
}

