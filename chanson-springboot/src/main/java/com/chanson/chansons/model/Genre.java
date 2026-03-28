package com.chanson.chansons.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Genre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idGenre;

    private String nomGenre;
    private String descriptionGenre;

    @JsonIgnore
    @OneToMany(mappedBy = "genre")
    private List<Chanson> chansons;
}
