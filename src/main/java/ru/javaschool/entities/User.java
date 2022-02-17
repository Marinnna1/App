package ru.javaschool.entities;

import ru.javaschool.enums.Position;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Data
@Table(name = "users")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name", unique = true)
    private String name;

    @Column(name = "password")
    private String password;

    @Column(name = "position")
    @Enumerated(EnumType.STRING)
    private Position position;


    public User(String name, String password, Position position) {
        this.name = name;
        this.password = password;
        this.position = position;
    }

    public User() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPosition() {
        return position.toString();
    }

    public void setPosition(Position position) {
        this.position = position;
    }
}
