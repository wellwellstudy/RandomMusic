package com.ran.randommusic.controller;

import com.ran.randommusic.model.Music;
import com.ran.randommusic.service.MusicService;
import com.ran.randommusic.util.JsonUtil;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by ran on 17/9/14.
 */
@Controller
@RequestMapping("/music")
public class MusicController {
    private static Logger logger = Logger.getLogger(MusicController.class);
    @Resource
    private MusicService musicService;
    @RequestMapping("/musicList")
    @ResponseBody
    public List<Music> getMusicList(HttpServletRequest request, Model model){
        List<Music> musicList = musicService.getMusicList();
        return musicList;
    }
}
