package ru.bikbaev.client_v2.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.bikbaev.client_v2.dto.DTO;
import ru.bikbaev.client_v2.mapper.JsonMapper;
import ru.bikbaev.client_v2.repository.JdbcRepositoryByClient;

import java.io.IOException;

public class Controller {
    private static final JsonMapper jsonMapper = new JsonMapper();

    public void doGetController(HttpServletRequest req, HttpServletResponse resp, JdbcRepositoryByClient jdbcRepositoryByClient)
            throws IOException {
        resp.setContentType("application/json");
        String idParam = req.getParameter("id");
        if (idParam.equals("all")) {
            Iterable<DTO> dtos = jdbcRepositoryByClient.findAll();
            jsonMapper.printAllJson(dtos, resp);
        } else {
            int id = Integer.parseInt(idParam);
            DTO dto = jdbcRepositoryByClient.findById(id);
            jsonMapper.printJson(dto, resp);
        }
    }

    public void doDelete(HttpServletRequest req,JdbcRepositoryByClient jdbcRepositoryByClient){
        String idParam = req.getParameter("id");
        int id = Integer.parseInt(idParam);
        jdbcRepositoryByClient.deleteById(id);
    }
}
