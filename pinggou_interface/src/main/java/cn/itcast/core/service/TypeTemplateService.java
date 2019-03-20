package cn.itcast.core.service;

import cn.itcast.core.entity.PageResult;
import cn.itcast.core.pojo.template.TypeTemplate;

import java.util.List;
import java.util.Map;

public interface TypeTemplateService {
    /**
     * 添加模块管理页面查询方法
     * @param page
     * @param rows
     * @param typeTemplate
     * @return
     */
    public PageResult search(Integer page,Integer rows,TypeTemplate typeTemplate);

    /**
     * 新建模板
     * @param typeTemplate
     */
   void add(TypeTemplate typeTemplate);

    /**
     * 回显模板
     * @param id
     * @return
     */
   TypeTemplate findOne(Long id);

    /**
     * 修改模板
     * @param typeTemplate
     */
   void  update(TypeTemplate typeTemplate);

   void delete(Long[] id);
}
