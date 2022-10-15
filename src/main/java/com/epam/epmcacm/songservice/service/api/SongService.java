package com.epam.epmcacm.songservice.service.api;

import com.epam.epmcacm.songservice.repository.model.Song;

import java.util.List;

public interface SongService {

    Song saveSong(Song song);

    Song updateSong(Long songId, Song song);

    Song getSongById(Long id);

    List<Long> deleteSongByIds(List<Long> ids);
}
