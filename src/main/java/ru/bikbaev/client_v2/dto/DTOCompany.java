package ru.bikbaev.client_v2.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class DTOCompany implements DTO {
   @Setter(AccessLevel.NONE)
   private int id;
   private String name;
   private String inn;

   public DTOCompany(String name, String inn) {
      this.name = name;
      this.inn = inn;
   }

   public DTOCompany(int id, String name, String inn) {
      this.id = id;
      this.name = name;
      this.inn = inn;
   }

   @Override
   public String toString() {
      return "DTOCompany{" +
              "id=" + id +
              ", name='" + name + '\'' +
              ", inn='" + inn + '\'' +
              '}';
   }
}
