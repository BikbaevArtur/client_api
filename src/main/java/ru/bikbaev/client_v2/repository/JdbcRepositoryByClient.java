package ru.bikbaev.client_v2.repository;


import ru.bikbaev.client_v2.dto.DTO;

public interface JdbcRepositoryByClient {
    Iterable<DTO>findAll();
    DTO findById(int id);
    <S extends DTO> S save(S entity);
    void deleteById(int id);
}
