package com.parking.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.parking.dto.priceTicket.PriceTicketResponse;


@Repository
public class SpRepository {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

    public List<PriceTicketResponse> getCurrentPriceTicket(Long parkingLotId) {
        String storedProcedure = "SP_Get_Price_Ticket_Of_ParkingLot";
        return jdbcTemplate.query(
            "{call " + storedProcedure + "(?)}",
            new Object[]{parkingLotId},
            new PriceTicketMapper()
        );
    }

    private static final class PriceTicketMapper implements RowMapper<PriceTicketResponse> {
        @Override
        public PriceTicketResponse mapRow(ResultSet rs, int rowNum) throws SQLException {
        	PriceTicketResponse priceTicket = new PriceTicketResponse();
            priceTicket.setTypeName(rs.getString("typeName"));
            priceTicket.setPrice(rs.getLong("price"));
            return priceTicket;
        }
    }
}
