package com.epam.epmcacm.resourceservice;

import com.epam.epmcacm.resourceservice.model.Song;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SongRepository extends CrudRepository<Song, Long> {
}
