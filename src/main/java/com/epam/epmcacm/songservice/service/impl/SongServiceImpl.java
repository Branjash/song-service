package com.epam.epmcacm.songservice.service.impl;

import com.epam.epmcacm.songservice.repository.SongRepository;
import com.epam.epmcacm.songservice.repository.model.Song;
import com.epam.epmcacm.songservice.service.api.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SongServiceImpl implements SongService {
    private SongRepository songRepository;

    public SongServiceImpl(SongRepository songRepository) {
        this.songRepository = songRepository;
    }

    @Override
    public Song saveSong(Song song) {
        return songRepository.save(song);
    }

    @Override
    public Song updateSong(Long songId, Song updatedSong) {
        Song currentSongDB = songRepository.findById(songId).orElseThrow(() -> new ResourceNotFoundException());
        updateSongIfChanged(updatedSong,currentSongDB);
        return songRepository.save(currentSongDB);
    }

    @Override
    public Song getSongById(Long id) {
        return songRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException());
    }

    @Override
    public List<Long> deleteSongByIds(List<Long> ids) {
        songRepository.deleteAllById(ids);
        return ids;
    }

    private void updateSongIfChanged(Song newSong, Song oldSong) {
        if(isSongAlbumForUpdate(newSong)) oldSong.setAlbum(newSong.getAlbum());
        if(isSongArtistForUpdate(newSong)) oldSong.setArtist(newSong.getArtist());
        if(isSongLengthForUpdate(newSong)) oldSong.setLength(newSong.getLength());
        if(isSongResourceIdForUpdate(newSong)) oldSong.setResourceId(newSong.getResourceId());
        if(isSongNameForUpdate(newSong)) oldSong.setName(newSong.getName());
        if(isSongYearForUpdate(newSong)) oldSong.setYear(newSong.getYear());
    }

    private boolean isSongAlbumForUpdate(Song song) {
        return song.getAlbum() != null &&
                !song.getAlbum().isBlank();
    }

    private boolean isSongArtistForUpdate(Song song) {
        return song.getArtist() != null &&
                !song.getArtist().isBlank();
    }

    private boolean isSongLengthForUpdate(Song song) {
        return song.getLength() != null &&
                !song.getLength().isBlank();
    }

    private boolean isSongNameForUpdate(Song song) {
        return song.getName() != null &&
                !song.getName().isBlank();
    }

    private boolean isSongResourceIdForUpdate(Song song) {
        return song.getResourceId() != null;
    }

    private boolean isSongYearForUpdate(Song song) {
        return song.getYear() != null;
    }

}
