package ru.bikbaev.client_v2.dto;


public interface DTO {
    default int getId() {
        return 0;
    }
    default String  getName(){
        return null;
    }
    default String getInn(){
        return null;
    }
    default String getFirsName(){
        return null;
    }
    default String getLastName(){
        return null;
    }
}
