package com.epam.epmcacm.songservice.exceptions;

public class SongNotFoundException extends  Exception{

    public SongNotFoundException(String message)
    {
        super(message);
    }
}
