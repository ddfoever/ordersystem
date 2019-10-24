package com.ddfoever.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Menu implements Serializable {

    private long id;

    private String name;

    private String flavor;

    private double price;

    private Type type;

    @Override
    public String toString() {
        return "Menu{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", flavor='" + flavor + '\'' +
                ", price=" + price +
                ", type=" + type +
                '}';
    }
}
