package ru.bikbaev.client_v2.dto;






public class DTORetail implements DTO{
    private int id;
    private final String firstName;
    private final String lastName;

    public DTORetail(int id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public DTORetail(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public int getId() {
        return this.id;
    }

    @Override
    public String getFirsName() {
        return this.firstName;
    }

    @Override
    public String getLastName() {
        return this.lastName;
    }

    @Override
    public String toString() {
        return "DTORetail{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
