package com.chanson.chansons.service;

import com.chanson.chansons.dto.ChansonDTO;
import com.chanson.chansons.model.Chanson;
import com.chanson.chansons.model.Genre;
import com.chanson.chansons.repos.ChansonRepository;
import com.chanson.chansons.repos.GenreRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChansonServiceImpl implements ChansonService {

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    ChansonRepository chansonRepository;

    @Autowired
    GenreRepository genreRepository;

    @Override
    public ChansonDTO saveChanson(ChansonDTO c) {
        return convertEntityToDto(chansonRepository.save(convertDtoToEntity(c)));
    }

    @Override
    public ChansonDTO updateChanson(ChansonDTO c) {
        return convertEntityToDto(chansonRepository.save(convertDtoToEntity(c)));
    }

    @Override
    public void deleteChanson(Long id) {
        chansonRepository.deleteById(id);
    }

    @Override
    public void deleteChansonById(Long id) {
        chansonRepository.deleteById(id);
    }

    @Override
    public ChansonDTO getChanson(Long id) {
        return convertEntityToDto(chansonRepository.findById(id).get());
    }

    @Override
    public List<ChansonDTO> getAllChansons() {
        return chansonRepository.findAll().stream()
                .map(this::convertEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public Page<ChansonDTO> getAllChansonsParPage(int page, int size) {
        return chansonRepository.findAll(PageRequest.of(page, size))
                .map(this::convertEntityToDto);
    }

    @Override
    public List<ChansonDTO> findByTitreChanson(String titre) {
        return chansonRepository.findByTitreChanson(titre).stream()
                .map(this::convertEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ChansonDTO> findByTitreChansonContains(String titre) {
        return chansonRepository.findByTitreChansonContains(titre).stream()
                .map(this::convertEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ChansonDTO> findByArtiste(String artiste) {
        return chansonRepository.findByArtiste(artiste).stream()
                .map(this::convertEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ChansonDTO> findByArtisteContains(String artiste) {
        return chansonRepository.findByArtisteContains(artiste).stream()
                .map(this::convertEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ChansonDTO> findByTitreDuree(String titre, Integer duree) {
        return chansonRepository.findByTitreDuree(titre, duree).stream()
                .map(this::convertEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ChansonDTO> findByGenre(Genre genre) {
        return chansonRepository.findByGenre(genre).stream()
                .map(this::convertEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ChansonDTO> findByGenreIdGenre(Long id) {
        return chansonRepository.findByGenreIdGenre(id).stream()
                .map(this::convertEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ChansonDTO> findByOrderByTitreChansonAsc() {
        return chansonRepository.findByOrderByTitreChansonAsc().stream()
                .map(this::convertEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ChansonDTO> trierChansonsTitreDuree() {
        return chansonRepository.trierChansonsTitreDuree().stream()
                .map(this::convertEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<Genre> getAllGenres() {
        return genreRepository.findAll();
    }

    @Override
    public ChansonDTO convertEntityToDto(Chanson chanson) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        return modelMapper.map(chanson, ChansonDTO.class);
    }

    @Override
    public Chanson convertDtoToEntity(ChansonDTO chansonDTO) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        Chanson chanson = modelMapper.map(chansonDTO, Chanson.class);
        chanson.setIdChanson(chansonDTO.getIdChanson());
        chanson.setTitreChanson(chansonDTO.getTitreChanson());
        chanson.setArtiste(chansonDTO.getArtiste());
        chanson.setDureeSecondes(chansonDTO.getDureeSecondes());
        chanson.setDateSortie(chansonDTO.getDateSortie());
        if (chansonDTO.getGenre() != null && chansonDTO.getGenre().getIdGenre() != null) {
            Genre genre = genreRepository.findById(chansonDTO.getGenre().getIdGenre()).orElse(null);
            chanson.setGenre(genre);
        }
        return chanson;
    }
}
