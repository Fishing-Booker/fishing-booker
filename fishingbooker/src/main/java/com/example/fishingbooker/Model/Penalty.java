package com.example.fishingbooker.Model;

import javax.persistence.*;

@Entity
@Table(name = "penalty")
@Inheritance(strategy = InheritanceType.JOINED)
public class Penalty {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(targetEntity = User.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    //@JoinColumn(name = "client_id", referencedColumnName = "user_id")
    private User client;

    @Column(name = "penalties")
    private Integer penalties;

    public Penalty() {
    }

    public Penalty(Integer id, User client, Integer penalties) {
        this.id = id;
        this.client = client;
        this.penalties = penalties;
    }

    public Penalty(User client, Integer penalties) {
        this.client = client;
        this.penalties = penalties;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getClient() {
        return client;
    }

    public void setClient(User client) {
        this.client = client;
    }

    public Integer getPenalties() {
        return penalties;
    }

    public void setPenalties(Integer penalties) {
        this.penalties = penalties;
    }
}
