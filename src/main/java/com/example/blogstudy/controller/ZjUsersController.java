package com.example.blogstudy.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.blogstudy.common.ExceptionEnum;
import com.example.blogstudy.common.ResultEntity;
import com.example.blogstudy.domain.entity.ZjUsers;
import com.example.blogstudy.service.ZjUsersService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author liyh
 * @since 2021-08-11
 */

@Slf4j
@Api(tags = "用户表")
@RestController
@RequestMapping("/zjUsers")
public class ZjUsersController {

    @Autowired
    public ZjUsersService zjUsersService;

    @ApiOperation(value = "新增")
    @PostMapping("/save")
    public ResultEntity save(@RequestBody ZjUsers zjUsers) {
        zjUsersService.save(zjUsers);
        return ResultEntity.success("新增成功");
    }

    @ApiOperation(value = "根据id删除")
    @DeleteMapping("/delete/{id}")
    public ResultEntity delete(@PathVariable("id") Long id) {
        boolean flag = zjUsersService.removeById(id);
        if (flag) {
            return ResultEntity.success("删除用户成功");
        } else {
            return ResultEntity.error("删除用户失败", ExceptionEnum.SYS_EXCEPTION);
        }
    }

    @ApiOperation(value = "条件查询")
    @PostMapping("/get")
    public ResultEntity list(@RequestBody ZjUsers zjUsers) {
        List<ZjUsers> zjUsersList = zjUsersService.list(new QueryWrapper<>(zjUsers));
        return ResultEntity.success("查询成功", zjUsersList);
    }

    @ApiOperation(value = "列表（分页）")
    @GetMapping("/list/{pageNum}/{pageSize}")
    public ResultEntity list(@PathVariable("pageNum") Long pageNum, @PathVariable("pageSize") Long pageSize) {
        IPage<ZjUsers> page = zjUsersService.page(
                new Page<>(pageNum, pageSize), null);
        if (page.getTotal() == 0) {
            page.setTotal(page.getRecords().size());
        }
        return ResultEntity.success("分页查询成功", page);
    }

    @ApiOperation(value = "详情")
    @GetMapping("/get/{id}")
    public ResultEntity<ZjUsers> get(@PathVariable("id") Long id) {
        ZjUsers zjUsers = zjUsersService.getById(id);
        return ResultEntity.success("根据id查询用户成功", zjUsers);
    }

    @ApiOperation(value = "根据id修改")
    @PostMapping("/update/{id}")
    public ResultEntity update(@PathVariable("id") Long id, @RequestBody ZjUsers zjUsers) {
        zjUsers.setId(id);
        zjUsersService.updateById(zjUsers);
        return ResultEntity.success("更新成功");
    }


}
