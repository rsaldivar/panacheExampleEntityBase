package org.acme.hibernate.orm.panache.repository;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Cacheable
public class Cliente {

    @Id
    @GeneratedValue
    public Long id;

    @Column(length = 40, unique = true)
    public String name;

    public Cliente() {
    }

    public Cliente(String name) {
        this.name = name;
    }
}
