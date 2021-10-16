package com.example.blogstudy.controller;



import com.example.blogstudy.service.ZjSetArtitleLabelService;
import com.example.blogstudy.entity.ZjSetArtitleLabel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.blogstudy.common.ResultEntity;
import com.example.blogstudy.common.ExceptionEnum;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
    import org.springframework.web.bind.annotation.RestController;

/**
* <p>
    * 文章标签对应表 前端控制器
    * </p>
*
* @author liyh
* @since 2021-08-11
*/

@Slf4j
@Api(tags = "文章标签对应表")
@RestController
@RequestMapping("//zjSetArtitleLabel")
public class ZjSetArtitleLabelController {

    @Autowired
    public ZjSetArtitleLabelService zjSetArtitleLabelService;

    @ApiOperation(value = "新增")
    @PostMapping("/save")
    public ResultEntity save(@RequestBody ZjSetArtitleLabel zjSetArtitleLabel){
        zjSetArtitleLabelService.save(zjSetArtitleLabel);
        return ResultEntity.success("新增成功");
    }

    @ApiOperation(value = "根据id删除")
    @DeleteMapping("/delete/{id}")
    public ResultEntity delete(@PathVariable("id") Long id){
        boolean flag = zjSetArtitleLabelService.removeById(id);
        if(flag) {
            return ResultEntity.success("删除用户成功");
        } else {
            return ResultEntity.error("删除用户失败", ExceptionEnum.SYS_EXCEPTION);
        }
    }

    @ApiOperation(value = "条件查询")
    @PostMapping("/get")
    public ResultEntity list(@RequestBody ZjSetArtitleLabel zjSetArtitleLabel){
        List<ZjSetArtitleLabel> zjSetArtitleLabelList = zjSetArtitleLabelService.list(new QueryWrapper<>(zjSetArtitleLabel));
        return ResultEntity.success("查询成功", zjSetArtitleLabelList);
    }

    @ApiOperation(value = "列表（分页）")
    @GetMapping("/list/{pageNum}/{pageSize}")
    public ResultEntity list(@PathVariable("pageNum")Long pageNum, @PathVariable("pageSize")Long pageSize){
        IPage<ZjSetArtitleLabel> page = zjSetArtitleLabelService.page(
        new Page<>(pageNum, pageSize), null);
        if (page.getTotal() == 0) {
            page.setTotal(page.getRecords().size());
        }
        return ResultEntity.success("分页查询成功", page);
    }

    @ApiOperation(value = "详情")
    @GetMapping("/get/{id}")
    public ResultEntity<ZjSetArtitleLabel> get(@PathVariable("id") Long id){
        ZjSetArtitleLabel zjSetArtitleLabel = zjSetArtitleLabelService.getById(id);
        return ResultEntity.success("根据id查询用户成功", zjSetArtitleLabel);
    }

    @ApiOperation(value = "根据id修改")
    @PostMapping("/update/{id}")
    public ResultEntity update(@PathVariable("id") Long id, @RequestBody ZjSetArtitleLabel zjSetArtitleLabel){
        zjSetArtitleLabel.setId(id);
        zjSetArtitleLabelService.updateById(zjSetArtitleLabel);
        return ResultEntity.success("更新成功");
    }


}
