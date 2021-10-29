package com.example.blogstudy.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.blogstudy.common.ExceptionEnum;
import com.example.blogstudy.common.ResultEntity;
import com.example.blogstudy.domain.entity.ZjUserFriends;
import com.example.blogstudy.service.ZjUserFriendsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 好友表 前端控制器
 * </p>
 *
 * @author liyh
 * @since 2021-08-11
 */

@Slf4j
@Api(tags = "好友表")
@RestController
@RequestMapping("/zjUserFriends")
public class ZjUserFriendsController {

    @Autowired
    public ZjUserFriendsService zjUserFriendsService;

    @ApiOperation(value = "新增")
    @PostMapping("/save")
    public ResultEntity save(@RequestBody ZjUserFriends zjUserFriends) {
        zjUserFriendsService.save(zjUserFriends);
        return ResultEntity.success("新增成功");
    }

    @ApiOperation(value = "根据id删除")
    @DeleteMapping("/delete/{id}")
    public ResultEntity delete(@PathVariable("id") Long id) {
        boolean flag = zjUserFriendsService.removeById(id);
        if (flag) {
            return ResultEntity.success("删除用户成功");
        } else {
            return ResultEntity.error("删除用户失败", ExceptionEnum.SYS_EXCEPTION);
        }
    }

    @ApiOperation(value = "条件查询")
    @PostMapping("/get")
    public ResultEntity list(@RequestBody ZjUserFriends zjUserFriends) {
        List<ZjUserFriends> zjUserFriendsList = zjUserFriendsService.list(new QueryWrapper<>(zjUserFriends));
        return ResultEntity.success("查询成功", zjUserFriendsList);
    }

    @ApiOperation(value = "列表（分页）")
    @GetMapping("/list/{pageNum}/{pageSize}")
    public ResultEntity list(@PathVariable("pageNum") Long pageNum, @PathVariable("pageSize") Long pageSize) {
        IPage<ZjUserFriends> page = zjUserFriendsService.page(
                new Page<>(pageNum, pageSize), null);
        if (page.getTotal() == 0) {
            page.setTotal(page.getRecords().size());
        }
        return ResultEntity.success("分页查询成功", page);
    }

    @ApiOperation(value = "详情")
    @GetMapping("/get/{id}")
    public ResultEntity<ZjUserFriends> get(@PathVariable("id") Long id) {
        ZjUserFriends zjUserFriends = zjUserFriendsService.getById(id);
        return ResultEntity.success("根据id查询用户成功", zjUserFriends);
    }

    @ApiOperation(value = "根据id修改")
    @PostMapping("/update/{id}")
    public ResultEntity update(@PathVariable("id") Long id, @RequestBody ZjUserFriends zjUserFriends) {
        zjUserFriends.setId(id);
        zjUserFriendsService.updateById(zjUserFriends);
        return ResultEntity.success("更新成功");
    }


}
