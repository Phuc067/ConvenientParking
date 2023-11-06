package com.parking.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.parking.dto.merchant.ReportResponse;
import com.parking.dto.priceTicket.PriceTicketResponse;
import com.parking.dto.ticket.HistoryTicket;
import com.parking.dto.ticket.TicketResponse;
import com.parking.utils.TimeUtils;


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
        	priceTicket.setId(rs.getLong("id"));
            priceTicket.setTypeName(rs.getString("typeName"));
            priceTicket.setPrice(rs.getLong("price"));
            return priceTicket;
        }
    }
    
    public List<TicketResponse> getUnpaidTicket(Long userId)
    {
    	String proc = "SP_GET_TICKET_OF_USER_UN_PAID";
    	return jdbcTemplate.query("{call " + proc  + "(?)}", 
    			new Object[] {userId},
    			new TicketMapper()
    	);
    }
    
    public static final class TicketMapper implements RowMapper<TicketResponse>
    {

		@Override
		public TicketResponse mapRow(ResultSet rs, int rowNum) throws SQLException {
			TicketResponse ticket = new TicketResponse();
			ticket.setId(rs.getLong(1));
			ticket.setCheckInTime(TimeUtils.getGMT_7(rs.getTimestamp(2)));
			ticket.setLicensePlate(rs.getString(3));
			ticket.setVehicleTypeName(rs.getString(4));
			ticket.setParkingLotName(rs.getString(5));
			ticket.setVehicleTypeId(rs.getLong(6));
			ticket.setParkingLotId(rs.getLong(7));
			return ticket;
		}
    	
    }
    
    public List<ReportResponse> getReportInYear(Long parkingLotId, Long year)
    {
    	String proc = "[SP_revenue_of_the_year]";
    	return jdbcTemplate.query("{call " + proc  + "(?, ?)}", 
    			new Object[] {parkingLotId, year},
    			new ReportMapper()
    	);
    }
    
    public static final class ReportMapper implements RowMapper<ReportResponse>
    {

		@Override
		public ReportResponse mapRow(ResultSet rs, int rowNum) throws SQLException {
			ReportResponse reportResponse = new ReportResponse();
			reportResponse.setMonth(rs.getLong(1));
			reportResponse.setTotal(rs.getDouble(2));
			return reportResponse;
		}
    	
    }
    
    public List<HistoryTicket> getHistoryTicket(Long userId)
    {
    	String proc = "[SP_GET_HISTORY_TICKET_OF_USER]";
    	return jdbcTemplate.query("{call " + proc  + "(?)}", 
    			new Object[] {userId},
    			new HistoryTicketMapper()
    	);
    }
    
    public static final class HistoryTicketMapper implements RowMapper<HistoryTicket>
    {

		@Override
		public HistoryTicket mapRow(ResultSet rs, int rowNum) throws SQLException {
			HistoryTicket historyTicket = new HistoryTicket();
			historyTicket.setId(rs.getLong(1));
			historyTicket.setCheckInTime(TimeUtils.getGMT_7(rs.getTimestamp(2)));
			historyTicket.setCheckOutTime(TimeUtils.getGMT_7(rs.getTimestamp(3)));
			historyTicket.setLicensePlate(rs.getString(4));
			historyTicket.setParkingLotName(rs.getString(5));
			return historyTicket;
		}
    	
    }
}
