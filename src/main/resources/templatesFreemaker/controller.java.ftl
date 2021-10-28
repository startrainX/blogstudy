package ${package.Controller};


import ${package.Service}.${table.serviceName};
import ${package.Entity}.${entity};
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
<#if restControllerStyle>
    import org.springframework.web.bind.annotation.RestController;
<#else>
    import org.springframework.stereotype.Controller;
</#if>
<#if superControllerClassPackage??>
    import ${superControllerClassPackage};
</#if>

/**
* <p>
    * ${table.comment!} 前端控制器
    * </p>
*
* @author ${author}
* @since ${date}
*/

@Slf4j
@Api(tags = "${table.comment!}")
<#if restControllerStyle>
    @RestController
<#else>
    @Controller
</#if>
@RequestMapping("<#if package.ModuleName??>${package.ModuleName}</#if>/<#if controllerMappingHyphenStyle??>${controllerMappingHyphen}<#else>${table.entityPath}</#if>")
<#if kotlin>
    class ${table.controllerName}<#if superControllerClass??> : ${superControllerClass}()</#if>
<#else>
    <#if superControllerClass??>
        public class ${table.controllerName} extends ${superControllerClass} {
    <#else>
        public class ${table.controllerName} {

        @Autowired
        public ${table.serviceName} ${table.entityPath}Service;

        @ApiOperation(value = "新增")
        @PostMapping("/save")
        public ResultEntity save(@RequestBody ${entity} ${table.entityPath}){
        ${table.entityPath}Service.save(${table.entityPath});
        return ResultEntity.success("新增成功");
        }

        @ApiOperation(value = "根据id删除")
        @DeleteMapping("/delete/{id}")
        public ResultEntity delete(@PathVariable("id") Long id){
        boolean flag = ${table.entityPath}Service.removeById(id);
        if(flag) {
        return ResultEntity.success("删除用户成功");
        } else {
        return ResultEntity.error("删除用户失败", ExceptionEnum.SYS_EXCEPTION);
        }
        }

        @ApiOperation(value = "条件查询")
        @PostMapping("/get")
        public ResultEntity list(@RequestBody ${entity} ${table.entityPath}){
        List<${entity}> ${table.entityPath}List = ${table.entityPath}Service.list(new QueryWrapper<>(${table.entityPath}));
        return ResultEntity.success("查询成功", ${table.entityPath}List);
        }

        @ApiOperation(value = "列表（分页）")
        @GetMapping("/list/{pageNum}/{pageSize}")
        public ResultEntity list(@PathVariable("pageNum")Long pageNum, @PathVariable("pageSize")Long pageSize){
        IPage<${entity}> page = ${table.entityPath}Service.page(
        new Page<>(pageNum, pageSize), null);
        if (page.getTotal() == 0) {
        page.setTotal(page.getRecords().size());
        }
        return ResultEntity.success("分页查询成功", page);
        }

        @ApiOperation(value = "详情")
        @GetMapping("/get/{id}")
        public ResultEntity<${entity}> get(@PathVariable("id") Long id){
        ${entity} ${table.entityPath} = ${table.entityPath}Service.getById(id);
        return ResultEntity.success("根据id查询用户成功", ${table.entityPath});
        }

        @ApiOperation(value = "根据id修改")
        @PostMapping("/update/{id}")
        public ResultEntity update(@PathVariable("id") Long id, @RequestBody ${entity} ${table.entityPath}){
        ${table.entityPath}.setId(id);
        ${table.entityPath}Service.updateById(${table.entityPath});
        return ResultEntity.success("更新成功");
        }

    </#if>

    }
</#if>
