package com.example.blogstudy.service.impl;

import com.example.blogstudy.domain.entity.ZjComments;
import com.example.blogstudy.dao.ZjCommentsMapper;
import com.example.blogstudy.service.ZjCommentsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 评论表 服务实现类
 * </p>
 *
 * @author liyh
 * @since 2021-08-11
 */
@Service
public class ZjCommentsServiceImpl extends ServiceImpl<ZjCommentsMapper, ZjComments> implements ZjCommentsService {

}
