package com.ran.randommusic.service.impl;

import com.ran.randommusic.dao.MusicDao;
import com.ran.randommusic.model.Music;
import com.ran.randommusic.service.MusicService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by ran on 17/9/14.
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class MusicServiceImpl implements MusicService {
    @Resource
    private MusicDao musicDao;
    public List<Music> getMusicList(){
        return musicDao.getMusicList();
    }
}
