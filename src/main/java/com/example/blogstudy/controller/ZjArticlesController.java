package com.example.blogstudy.controller;



import com.example.blogstudy.service.ZjArticlesService;
import com.example.blogstudy.entity.ZjArticles;
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
    * 文章表 前端控制器
    * </p>
*
* @author liyh
* @since 2021-10-17
*/

@Slf4j
@Api(tags = "文章表")
@RestController
@RequestMapping("//zjArticles")
public class ZjArticlesController {

    @Autowired
    public ZjArticlesService zjArticlesService;

    @ApiOperation(value = "新增")
    @PostMapping("/save")
    public ResultEntity save(@RequestBody ZjArticles zjArticles){
        zjArticlesService.save(zjArticles);
        return ResultEntity.success("新增成功");
    }

    @ApiOperation(value = "根据id删除")
    @DeleteMapping("/delete/{id}")
    public ResultEntity delete(@PathVariable("id") Long id){
        boolean flag = zjArticlesService.removeById(id);
        if(flag) {
            return ResultEntity.success("删除用户成功");
        } else {
            return ResultEntity.error("删除用户失败", ExceptionEnum.SYS_EXCEPTION);
        }
    }

    @ApiOperation(value = "条件查询")
    @PostMapping("/get")
    public ResultEntity list(@RequestBody ZjArticles zjArticles){
        List<ZjArticles> zjArticlesList = zjArticlesService.list(new QueryWrapper<>(zjArticles));
        return ResultEntity.success("查询成功", zjArticlesList);
    }

    @ApiOperation(value = "列表（分页）")
    @GetMapping("/list/{pageNum}/{pageSize}")
    public ResultEntity list(@PathVariable("pageNum")Long pageNum, @PathVariable("pageSize")Long pageSize){
        IPage<ZjArticles> page = zjArticlesService.page(
        new Page<>(pageNum, pageSize), null);
        if (page.getTotal() == 0) {
            page.setTotal(page.getRecords().size());
        }
        return ResultEntity.success("分页查询成功", page);
    }

    @ApiOperation(value = "详情")
    @GetMapping("/get/{id}")
    public ResultEntity<ZjArticles> get(@PathVariable("id") Long id){
        ZjArticles zjArticles = zjArticlesService.getById(id);
        return ResultEntity.success("根据id查询用户成功", zjArticles);
    }

    @ApiOperation(value = "根据id修改")
    @PostMapping("/update/{id}")
    public ResultEntity update(@PathVariable("id") Long id, @RequestBody ZjArticles zjArticles){
        zjArticles.setId(id);
        zjArticlesService.updateById(zjArticles);
        return ResultEntity.success("更新成功");
    }


}
