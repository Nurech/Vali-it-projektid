package ee.bcs.valiit.controller;

import ee.bcs.valiit.dto.TransactionDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;


public class TransactionRowMapper implements org.springframework.jdbc.core.RowMapper<TransactionDTO> {

    @Override
    public TransactionDTO mapRow(ResultSet rs, int rowNum) throws SQLException{
        TransactionDTO response = new TransactionDTO();
        response.setFrom_account(rs.getString("from_account"));
        response.setTo_account(rs.getString("to_account"));
        response.setDate_time(rs.getObject("date_time", LocalDateTime.class));
        //rs.getObject("date_time", LocalDateTime.class);
        response.setTransfer(rs.getDouble("transfer"));
        response.setDeduction(rs.getDouble("deduction"));
        return response;
    }
}

