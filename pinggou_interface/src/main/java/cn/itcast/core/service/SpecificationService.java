package cn.itcast.core.service;

import cn.itcast.core.entity.PageResult;
import cn.itcast.core.pojo.specification.Specification;
import cn.itcast.core.vo.SpecVo;

import java.util.List;
import java.util.Map;

public interface SpecificationService {
    /**
     * 规格列表查询
     * @param page
     * @param rows
     * @param specification
     * @return
     */
    public PageResult search(Integer page,Integer rows,Specification specification);

    /**
     * 新建保存规格
     * @param specVo
     */
    void add(SpecVo specVo);

    /**
     * 规格回显
     * @param id
     * @return
     */
    public SpecVo findOne(Long id);

    /**
     * 更新规格
     * @param specVo
     */
    void update(SpecVo specVo);

    /**
     * 删除规格
     * @param ids
     */
    void  delete(Long[] ids);

    /**
     * 新增模块：初始化规格下拉框列表数据
     * @return
     */
    public List<Map<String,String>> selectOptionList();
}
