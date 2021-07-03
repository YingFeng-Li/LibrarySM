package cn.edu.guet.controller;

import cn.edu.guet.dao.ITreeDao;
import cn.edu.guet.dao.impl.TreeDaoImpl;
import cn.edu.guet.mvc.annotation.Controller;
import cn.edu.guet.bean.*;
import cn.edu.guet.mvc.annotation.RequestMapping;

import java.util.List;

@Controller
public class TreeController {
    ITreeDao treeDao = new TreeDaoImpl();
    @RequestMapping("tree/getAllTree.do")
    public List<Tree> getAllTree(){
        return treeDao.getAllTree();
    }
}
