package ru.parhomych.spring01.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.parhomych.spring01.model.Transaction;

import java.util.List;

@Repository
public class KnockToBDRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    // thanks Java 8, look the custom RowMapper
    public List<Transaction> findAllTransactions() {
        String SQLquery = "select i.id_of_transaction, \n" +
                "c.name || ' ' || c.surname as client_name, \n" +
                "i.date_of_income, \n" +
                "i.payment, \n" +
                "a.name_of_activity || ' (' || a.company || ')' as activity_name\n" +
                "from income i, activities a, clients c\n" +
                "where i.source_of_income = c.idclient\n" +
                "and i.activity = a.id_of_activity";

        List<Transaction> result = jdbcTemplate.query(
                SQLquery,
                (rs, rowNum) -> new Transaction(
                        rs.getInt("id_of_transaction"),
                        rs.getString("client_name"),
                        rs.getDate("date_of_income"),
                        rs.getInt("payment"),
                        rs.getString("activity_name")
                )
        );
        return result;
    }

    public List<Transaction> findTransactionsByID() {
        String SQLquery = "select i.id_of_transaction, \n" +
                "c.name || ' ' || c.surname as client_name, \n" +
                "i.date_of_income, \n" +
                "i.payment, \n" +
                "a.name_of_activity || ' (' || a.company || ')' as activity_name\n" +
                "from income i, activities a, clients c\n" +
                "where i.source_of_income = c.idclient\n" +
                "and i.activity = a.id_of_activity";

        List<Transaction> result = jdbcTemplate.query(
                SQLquery,
                (rs, rowNum) -> new Transaction(
                        rs.getInt("id_of_transaction"),
                        rs.getString("client_name"),
                        rs.getDate("date_of_income"),
                        rs.getInt("payment"),
                        rs.getString("activity_name")
                )
        );
        return result;
    }

}
