package com.sharkbaitextraordinaire.location.core;

import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OwntracksUpdateMapper implements ResultSetMapper<OwntracksUpdate> {
    @Override
    public OwntracksUpdate map(int index, ResultSet resultSet, StatementContext statementContext) throws SQLException {
        return new OwntracksUpdate(resultSet.getString("_type"), resultSet.getString("lat"), resultSet.getString("lon"), resultSet.getString("acc"), resultSet.getString("batt"), resultSet.getLong("tst"));
    }
}
