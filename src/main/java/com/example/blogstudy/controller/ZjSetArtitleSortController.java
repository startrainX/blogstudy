package com.example.blogstudy.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.blogstudy.common.ExceptionEnum;
import com.example.blogstudy.common.ResultEntity;
import com.example.blogstudy.entity.ZjSetArtitleSort;
import com.example.blogstudy.service.ZjSetArtitleSortService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 文章类别表 前端控制器
 * </p>
 *
 * @author liyh
 * @since 2021-08-11
 */

@Slf4j
@Api(tags = "文章类别表")
@RestController
@RequestMapping("/zjSetArtitleSort")
public class ZjSetArtitleSortController {

    @Autowired
    public ZjSetArtitleSortService zjSetArtitleSortService;

    @ApiOperation(value = "新增")
    @PostMapping("/save")
    public ResultEntity save(@RequestBody ZjSetArtitleSort zjSetArtitleSort) {
        zjSetArtitleSortService.save(zjSetArtitleSort);
        return ResultEntity.success("新增成功");
    }

    @ApiOperation(value = "根据id删除")
    @DeleteMapping("/delete/{id}")
    public ResultEntity delete(@PathVariable("id") Long id) {
        boolean flag = zjSetArtitleSortService.removeById(id);
        if (flag) {
            return ResultEntity.success("删除用户成功");
        } else {
            return ResultEntity.error("删除用户失败", ExceptionEnum.SYS_EXCEPTION);
        }
    }

    @ApiOperation(value = "条件查询")
    @PostMapping("/get")
    public ResultEntity list(@RequestBody ZjSetArtitleSort zjSetArtitleSort) {
        List<ZjSetArtitleSort> zjSetArtitleSortList = zjSetArtitleSortService.list(new QueryWrapper<>(zjSetArtitleSort));
        return ResultEntity.success("查询成功", zjSetArtitleSortList);
    }

    @ApiOperation(value = "列表（分页）")
    @GetMapping("/list/{pageNum}/{pageSize}")
    public ResultEntity list(@PathVariable("pageNum") Long pageNum, @PathVariable("pageSize") Long pageSize) {
        IPage<ZjSetArtitleSort> page = zjSetArtitleSortService.page(
                new Page<>(pageNum, pageSize), null);
        if (page.getTotal() == 0) {
            page.setTotal(page.getRecords().size());
        }
        return ResultEntity.success("分页查询成功", page);
    }

    @ApiOperation(value = "详情")
    @GetMapping("/get/{id}")
    public ResultEntity<ZjSetArtitleSort> get(@PathVariable("id") Long id) {
        ZjSetArtitleSort zjSetArtitleSort = zjSetArtitleSortService.getById(id);
        return ResultEntity.success("根据id查询用户成功", zjSetArtitleSort);
    }

    @ApiOperation(value = "根据id修改")
    @PostMapping("/update/{id}")
    public ResultEntity update(@PathVariable("id") Long id, @RequestBody ZjSetArtitleSort zjSetArtitleSort) {
        zjSetArtitleSort.setId(id);
        zjSetArtitleSortService.updateById(zjSetArtitleSort);
        return ResultEntity.success("更新成功");
    }


}
