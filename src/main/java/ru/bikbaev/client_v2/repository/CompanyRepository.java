package ru.bikbaev.client_v2.repository;


import ru.bikbaev.client_v2.dto.DTO;
import ru.bikbaev.client_v2.dto.DTOCompany;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CompanyRepository implements JdbcRepositoryByClient {

    @Override
    public Iterable<DTO> findAll() {
        List<DTO> dtoList = new ArrayList<>();
        String sql = "SELECT * FROM company";

        try (PreparedStatement preparedStatement = ConnectionDataBase
                .getConnection()
                .prepareStatement(sql)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                DTOCompany dtoCompany = new DTOCompany(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("inn"));
                dtoList.add(dtoCompany);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return dtoList;
    }

    @Override
    public DTO findById(int id) {
        String sql = "SELECT * FROM company WHERE id = ?";
        try (PreparedStatement preparedStatement = ConnectionDataBase
                .getConnection()
                .prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return new DTOCompany(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("inn"));
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public <S extends DTO> S save(S entity) {
        String sql = "INSERT INTO company VALUES (DEFAULT,?,?,DEFAULT)";
        try (PreparedStatement preparedStatement = ConnectionDataBase
                .getConnection()
                .prepareStatement(sql)) {
            preparedStatement.setString(1, entity.getName());
            preparedStatement.setString(2, entity.getInn());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return entity;
    }

    @Override
    public void deleteById(int id) {
        String sql = "DELETE FROM company WHERE id = ?";
        try (
                PreparedStatement preparedStatement = ConnectionDataBase
                        .getConnection()
                        .prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            int affectedRow = preparedStatement.executeUpdate();
            // нужно сделать нормальную обработку
            if (affectedRow == 0) {
                // throw new RuntimeException("No rows where deleted for id: " + id);
                System.out.printf("Не было удалено id %d , ее не существует \n", id);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

//    private Connection connect() {
//        try {
//            return ConnectionDataBase.getConnection();
//        } catch (SQLException e) {
//            throw new RuntimeException();
//        }
//    }
}
