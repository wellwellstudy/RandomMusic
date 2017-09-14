package com.ran.randommusic.service;

import com.ran.randommusic.model.Music;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by ran on 17/9/14.
 */
@Service
public interface MusicService {
    public List<Music> getMusicList();
}
