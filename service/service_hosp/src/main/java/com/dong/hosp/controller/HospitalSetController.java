package com.dong.hosp.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dong.common.result.Result;
import com.dong.hosp.model.hosp.HospitalSet;
import com.dong.hosp.service.HospitalSetService;
import com.dong.hosp.vo.hosp.HospitalSetQueryVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@Api(tags = "医院设置管理")
@RequestMapping("/admin/hosp/hospitalSet")
public class HospitalSetController {

    //注入service
    @Autowired
    private HospitalSetService hospitalSetService;

    //1查询医院设置表所有信息
    @ApiOperation(value = "获取所有医院设置")
    @GetMapping("findAll")
    public Result findAllHospitalSet(){
        //调用service的方法
        List<HospitalSet> list = hospitalSetService.list();
        return Result.ok(list);
    }
    //2逻辑删除删除医院设置
    @ApiOperation(value = "逻辑删除医院设置")
    @DeleteMapping("{id}")
    public Result removeHospSet(@PathVariable Long id){
        boolean flag = hospitalSetService.removeById(id);
        //使用三目运算符
        return flag==true?Result.ok():Result.fail();
    }
    //3条件查询带分页
    //创建vo类,封装条件值HospitalSetQueryVo
    @ApiOperation(value = "条件查询带分页")
    @GetMapping("findPage/{current}/{limit}")
    public Result findPageHospSet(@PathVariable long current,
                                  @PathVariable long limit,
                                  HospitalSetQueryVo hospitalSetQueryVo){
        //创建page对象,传递当前页,每页记录数
        Page<HospitalSet> page = new Page(current,limit);

        //构建条件
        QueryWrapper<HospitalSet> wrapper = new QueryWrapper<>();
        String hoscode = hospitalSetQueryVo.getHoscode();//医院编号
        String hosname = hospitalSetQueryVo.getHosname();//医院名称

        if (!StringUtils.isEmpty(hosname)){
            wrapper.like("hosname",hospitalSetQueryVo.getHosname());
        }
        if (!StringUtils.isEmpty(hoscode)){
            wrapper.eq("hoscode",hospitalSetQueryVo.getHoscode());
        }
        /**
         * 为什么不用以下2个方法而是用判断,因为(医院名称),(医院编号)两个条件值可能有也可能都没有,所以需要判断
         * wrapper.like("hosname",hospitalSetQueryVo.getHosname());
         * wrapper.eq("hoscode",hospitalSetQueryVo.getHoscode());
         */

        //调用方法实现分页查询
        Page<HospitalSet> pageHospitalSet = hospitalSetService.page(page, wrapper);

        return Result.ok(pageHospitalSet);
    }



    //4添加医院设置


    //5根据ID获取医院设置


    //6修改医院设置


    //7批量删除医院设置

}
