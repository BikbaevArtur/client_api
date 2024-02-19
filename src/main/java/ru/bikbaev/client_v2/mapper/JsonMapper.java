package ru.bikbaev.client_v2.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import ru.bikbaev.client_v2.dto.DTO;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public class JsonMapper {



    public void printJson(DTO dto, HttpServletResponse resp) throws IOException {
        resp.setStatus(200);
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(dto);
        PrintWriter out = resp.getWriter();
        out.println(json);
        out.close();
    }
    public void printAllJson(Iterable<DTO>dtos, HttpServletResponse resp) throws IOException {
        resp.setStatus(200);
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(dtos);
        PrintWriter out = resp.getWriter();
        out.println(json);
        out.close();
    }

    public static StringBuffer doPost (HttpServletRequest req ) throws IOException {
        StringBuffer jsonRequest = new StringBuffer();
        try(BufferedReader bufferedReader = req.getReader()){
            String line ;
            while ((line = bufferedReader.readLine()) != null){
                jsonRequest.append(line);
            }
        }
        return jsonRequest;
    }
}
