package ru.itis.servletsauth.repositories;

import ru.itis.servletsauth.dto.User;
import ru.itis.servletsauth.utils.RowMapper;
import ru.itis.servletsauth.utils.UserRowMapper;

import java.sql.*;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class UserRepositoryJDBC implements UserRepository {

    //language=sql
    private static final String SQL_SAVE = "insert into account(name, password) values(?, ?)";
    //language=sql
    private static final String SQL_SELECT_ALL = "select * from account";
    //language=sql
    private static final String SQL_GET_BY_ID = "select * from account where id = ?";
    //language=sql
    private static final String SQL_GET_BY_NAME = "select * from account where name = ?";
    //language=sql
    private static final String SQL_DELETE_BY_ID = "delete from account where id = ?";
    final String HOST = "jdbc:postgresql://localhost:5432/AuthBase";
    final String USER = "postgres";
    final String PASS = "1234";


    @Override
    public void save(User model) {
        try (Connection connection = DriverManager.getConnection(HOST, USER, PASS);
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_SAVE, PreparedStatement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setString(1, model.getName());
            preparedStatement.setString(2, model.getPassword());


            int affect = preparedStatement.executeUpdate();

            if (affect != 1) {
                throw new SQLException("Cannot insert account");
            }

            try (ResultSet generatedIds = preparedStatement.getGeneratedKeys()) {
                if (generatedIds.next()) {
                    model.setId((UUID) generatedIds.getObject("id"));
                } else {
                    throw new SQLException("Cannot retrieve id");
                }
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public List<User> getAll() {
        List<User> users;

        try (Connection connection = DriverManager.getConnection(HOST, USER, PASS);
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_ALL)) {

            try (ResultSet resultSet = preparedStatement.executeQuery();) {
                RowMapper<User> rowMapper = new UserRowMapper();
                users = extract(rowMapper, resultSet);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return users;

    }

    @Override
    public void delete() {

    }

    @Override
    public Optional<User> findById(UUID id) {
        Optional<User> optionalUser;

        try (Connection connection = DriverManager.getConnection(HOST, USER, PASS);
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_GET_BY_ID)) {

            preparedStatement.setObject(1, id);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                RowMapper<User> rowMapper = new UserRowMapper();
                optionalUser = extract(rowMapper, resultSet).stream().findAny();
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return optionalUser;

    }

    @Override
    public Optional<User> findByName(String name) {
        Optional<User> optionalUser;

        try (Connection connection = DriverManager.getConnection(HOST, USER, PASS);
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_GET_BY_NAME)) {

            preparedStatement.setString(1, name);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                RowMapper<User> rowMapper = new UserRowMapper();
                optionalUser = extract(rowMapper, resultSet).stream().findAny();
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return optionalUser;

    }
}
