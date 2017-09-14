package com.ran.randommusic.dao;

import com.ran.randommusic.model.Music;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by ran on 17/9/14.
 */
@Repository
public interface MusicDao {
    List<Music> getMusicList();
}
