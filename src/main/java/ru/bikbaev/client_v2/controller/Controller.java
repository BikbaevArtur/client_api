package ru.bikbaev.client_v2.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.bikbaev.client_v2.dto.DTO;
import ru.bikbaev.client_v2.exceptions.InvalidIdException;
import ru.bikbaev.client_v2.mapper.JsonMapper;
import ru.bikbaev.client_v2.repository.JdbcRepositoryByClient;

import java.io.IOException;
import java.io.PrintWriter;

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
            try {
                int id = Integer.parseInt(idParam);
                if (id < 0) {
                    throw new InvalidIdException("Invalid id value " + id);
                } else {
                    DTO dto = jdbcRepositoryByClient.findById(id);
                    jsonMapper.printJson(dto, resp);
                }
            } catch (NumberFormatException e) {
                resp.setStatus(500);
                PrintWriter out = resp.getWriter();
                out.println("<h1> Invalid id format </h1>");

            } catch (InvalidIdException e) {
                resp.setStatus(500);
                PrintWriter out = resp.getWriter();
                out.println("<h1>" + e.getMessage() + "</h1>");
            }


        }
    }

    public void doDelete(HttpServletRequest req,HttpServletResponse resp, JdbcRepositoryByClient jdbcRepositoryByClient) throws IOException {
        String idParam = req.getParameter("id");


        try {
            int id = Integer.parseInt(idParam);
            if (id < 0) {
                throw new InvalidIdException("Invalid id value " + id);
            } else {
                jdbcRepositoryByClient.deleteById(id);
            }
        } catch (NumberFormatException e) {
            resp.setStatus(500);
            PrintWriter out = resp.getWriter();
            out.println("<h1> Invalid id format </h1>");

        } catch (InvalidIdException e) {
            resp.setStatus(500);
            PrintWriter out = resp.getWriter();
            out.println("<h1>" + e.getMessage() + "</h1>");
        }
    }
}
