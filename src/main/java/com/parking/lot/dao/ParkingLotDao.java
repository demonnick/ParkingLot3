package com.parking.lot.dao;

import com.parking.lot.Ticket;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.List;

/**
 * A DAO representing the interaction with the PARKING table.
 */
public class ParkingLotDao {

    public static final String SELECT_ALL_TICKETS = "SELECT  ID, TIME_IN TIME, TIME_OUT TIME, DATE DATE, IS_LOST BOOLEAN, WINNER FROM TICKET";
    public static final String SELECT_SINGLE_TICKET = "SELECT  ID, TIME_IN TIME, TIME_OUT TIME, DATE DATE, IS_LOST BOOLEAN, WINNER FROM TICKET WHERE ID = ?";

    private JdbcTemplate jdbcTemplate;
    private final RowMapper<Ticket> ticketRowMapper = new RowMapper<Ticket>() {
        @Override
        public Ticket mapRow(ResultSet resultSet, int i) throws SQLException {
            Ticket result = new Ticket(
                    resultSet.getInt("ID"),
                    resultSet.getTime("TIME_IN"),
                    resultSet.getTime("TIME_OUT"),
                    resultSet.getDate("DATE"),
                    resultSet.getBoolean("IS_LOST"));


            return result;
        }
    };


    public ParkingLotDao(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }




    /**
     * Retrieve all the tickets from the database.
     *
     * @return a {@link List} of all {@link Ticket}
     */
    public List<Ticket> getPoolMatches() {
        return jdbcTemplate.query(SELECT_ALL_TICKETS, ticketRowMapper);
    }

    /**
     * Return a ticket for a given id.
     *
     * @param id the id of the ticket
     * @return the {@link Ticket} for the id.
     */
    public Ticket getTicket(int id) {
        return jdbcTemplate.queryForObject(SELECT_SINGLE_TICKET,
                new Object[]{id},
                ticketRowMapper);

    }
}