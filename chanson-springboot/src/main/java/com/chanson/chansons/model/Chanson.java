package com.chanson.chansons.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
public class Chanson {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idChanson;

    @NotBlank(message = "Le titre ne peut pas être vide")
    @Size(min = 2, max = 100, message = "Le titre doit contenir entre 2 et 100 caractères")
    private String titreChanson;

    @NotBlank(message = "L'artiste ne peut pas être vide")
    @Size(min = 2, max = 80, message = "L'artiste doit contenir entre 2 et 80 caractères")
    private String artiste;

    @NotNull(message = "La durée ne peut pas être vide")
    @Min(value = 1, message = "La durée doit être au moins 1 seconde")
    @Max(value = 3600, message = "La durée ne peut pas dépasser 3600 secondes")
    private Integer dureeSecondes;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @PastOrPresent(message = "La date de sortie doit être dans le passé ou aujourd'hui")
    private Date dateSortie;

    @ManyToOne
    private Genre genre;

    public Chanson() {
        super();
    }

    public Chanson(String titreChanson, String artiste, Integer dureeSecondes, Date dateSortie) {
        super();
        this.titreChanson = titreChanson;
        this.artiste = artiste;
        this.dureeSecondes = dureeSecondes;
        this.dateSortie = dateSortie;
    }

    public Long getIdChanson() { return idChanson; }
    public void setIdChanson(Long idChanson) { this.idChanson = idChanson; }

    public String getTitreChanson() { return titreChanson; }
    public void setTitreChanson(String titreChanson) { this.titreChanson = titreChanson; }

    public String getArtiste() { return artiste; }
    public void setArtiste(String artiste) { this.artiste = artiste; }

    public Integer getDureeSecondes() { return dureeSecondes; }
    public void setDureeSecondes(Integer dureeSecondes) { this.dureeSecondes = dureeSecondes; }

    public Date getDateSortie() { return dateSortie; }
    public void setDateSortie(Date dateSortie) { this.dateSortie = dateSortie; }

    public Genre getGenre() { return genre; }
    public void setGenre(Genre genre) { this.genre = genre; }

    @Override
    public String toString() {
        return "Chanson [idChanson=" + idChanson + ", titreChanson=" + titreChanson
                + ", artiste=" + artiste + ", dureeSecondes=" + dureeSecondes
                + ", dateSortie=" + dateSortie + "]";
    }
}
