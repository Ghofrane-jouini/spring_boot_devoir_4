package com.chanson.chansons.service;

import com.chanson.chansons.dto.ChansonDTO;
import com.chanson.chansons.model.Chanson;
import com.chanson.chansons.model.Genre;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ChansonService {

    ChansonDTO saveChanson(ChansonDTO c);
    ChansonDTO updateChanson(ChansonDTO c);
    void deleteChanson(Long id);
    void deleteChansonById(Long id);
    ChansonDTO getChanson(Long id);
    List<ChansonDTO> getAllChansons();

    Page<ChansonDTO> getAllChansonsParPage(int page, int size);
    List<ChansonDTO> findByTitreChanson(String titre);
    List<ChansonDTO> findByTitreChansonContains(String titre);
    List<ChansonDTO> findByArtiste(String artiste);
    List<ChansonDTO> findByArtisteContains(String artiste);
    List<ChansonDTO> findByTitreDuree(String titre, Integer duree);
    List<ChansonDTO> findByGenre(Genre genre);
    List<ChansonDTO> findByGenreIdGenre(Long id);
    List<ChansonDTO> findByOrderByTitreChansonAsc();
    List<ChansonDTO> trierChansonsTitreDuree();
    List<Genre> getAllGenres();

    ChansonDTO convertEntityToDto(Chanson chanson);
    Chanson convertDtoToEntity(ChansonDTO chansonDto);
}
