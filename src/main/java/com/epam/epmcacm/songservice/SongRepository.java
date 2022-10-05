package com.epam.epmcacm.songservice;

import com.epam.epmcacm.songservice.model.Song;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SongRepository extends CrudRepository<Song, Long> {
}
