package com.where_next.entities;

import javax.persistence.*;

@Entity
@Table(name = "trip")
public class Trip {

    // this initialValue is to avoid IntegrityExceptions when clashing with the ids from the import.sql.
    // we can remove later if not needed - thought it would be handy to put some seeding data there for now
    @Id
    @SequenceGenerator(initialValue = 3, name = "trip_id_seq", sequenceName = "trip_id_seq")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "trip_id_seq")
    @Column(name = "id")
    private long id;

    @Column(name="name")
    private String name;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
