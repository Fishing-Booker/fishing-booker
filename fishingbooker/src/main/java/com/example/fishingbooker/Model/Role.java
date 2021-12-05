package com.example.fishingbooker.Model;

import com.example.fishingbooker.Enum.ERole;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="roles")
public class Role implements Serializable {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(name = "name")
    private ERole name;

    public Role() {
    }

    public Role(Integer id, ERole name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ERole getName() {
        return name;
    }

    public void setName(ERole name) {
        this.name = name;
    }
}