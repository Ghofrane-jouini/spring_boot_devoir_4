package com.chanson.chansons.repos;

import com.chanson.chansons.model.Chanson;
import com.chanson.chansons.model.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(path = "rest")
public interface ChansonRepository extends JpaRepository<Chanson, Long> {

    List<Chanson> findByTitreChanson(String titre);

    List<Chanson> findByTitreChansonContains(String titre);

    List<Chanson> findByArtiste(String artiste);

    List<Chanson> findByArtisteContains(String artiste);

    @Query("select c from Chanson c where c.titreChanson like %?1 and c.dureeSecondes > ?2")
    List<Chanson> findByTitreDuree(String titre, Integer duree);

    @Query("select c from Chanson c where c.genre = ?1")
    List<Chanson> findByGenre(Genre genre);

    List<Chanson> findByGenreIdGenre(Long id);

    List<Chanson> findByOrderByTitreChansonAsc();

    @Query("select c from Chanson c order by c.titreChanson ASC, c.dureeSecondes DESC")
    List<Chanson> trierChansonsTitreDuree();
}
