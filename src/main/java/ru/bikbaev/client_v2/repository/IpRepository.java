package ru.bikbaev.client_v2.repository;

import ru.bikbaev.client_v2.dto.DTO;
import ru.bikbaev.client_v2.dto.DTOCompany;
import ru.bikbaev.client_v2.dto.DTOIp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class IpRepository  implements  JdbcRepositoryByClient{
    @Override
    public Iterable<DTO> findAll() {
        List<DTO> dtoList = new ArrayList<>();
        String sql = "SELECT * FROM ip";
        try(PreparedStatement preparedStatement = connect().prepareStatement(sql) ){
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                DTO dto = new DTOCompany(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("inn"));
                dtoList.add(dto);
            }

        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }

        return dtoList;
    }

    @Override
    public DTO findById(int id) {
        String sql = "SELECT * FROM ip WHERE  id = ?";
        try(PreparedStatement preparedStatement  = connect().prepareStatement(sql)){
            preparedStatement.setInt(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                return new DTOIp(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("inn"));
            }
            else {
                return null;
            }
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }

    }

    @Override
    public <S extends DTO> S save(S entity) {
        String sql = "INSERT INTO ip VALUES(DEFAULT,?,?,DEFAULT) ";
        try(PreparedStatement preparedStatement = connect().prepareStatement(sql)){
            preparedStatement.setString(1,entity.getName());
            preparedStatement.setString(2,entity.getInn());
            preparedStatement.executeUpdate();
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
        return entity;
    }

    @Override
    public void deleteById(int id) {
        String sql = "DELETE FROM ip WHERE id = ?";
        try(PreparedStatement preparedStatement = connect().prepareStatement(sql)){
            preparedStatement.setInt(1,id);
           int affectRow =  preparedStatement.executeUpdate();
           if(affectRow == 0){
               System.out.printf("Не было удалено %d, такого не существуте",id);
           }
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    private Connection connect() {
        try {
            return ConnectionDataBase.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }
}
