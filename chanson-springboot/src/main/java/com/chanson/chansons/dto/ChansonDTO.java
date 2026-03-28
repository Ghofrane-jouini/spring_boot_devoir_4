package com.chanson.chansons.dto;

import com.chanson.chansons.model.Genre;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChansonDTO {

    private Long idChanson;
    private String titreChanson;
    private String artiste;
    private Integer dureeSecondes;
    private Date dateSortie;
    private Genre genre;
    private String nomGenre;
}
