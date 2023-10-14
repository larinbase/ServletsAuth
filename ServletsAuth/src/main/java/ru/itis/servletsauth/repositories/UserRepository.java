package ru.itis.servletsauth.repositories;

import ru.itis.servletsauth.dto.User;
import ru.itis.servletsauth.utils.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface UserRepository extends CrudRepository<User>{
    default List<User> extract(RowMapper<User> rowMapper, ResultSet resultSet) throws SQLException {
        Boolean next = resultSet.next();
        List<User> entities = new ArrayList<>();
        int i = 0;
        while (next) {
            entities.add(rowMapper.from(resultSet, i));
            next = resultSet.next();
            i++;
        }
        return entities;
    }

}
