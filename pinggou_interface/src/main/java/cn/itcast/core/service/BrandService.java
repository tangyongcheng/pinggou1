package cn.itcast.core.service;

import cn.itcast.core.entity.PageResult;
import cn.itcast.core.entity.Result;
import cn.itcast.core.pojo.good.Brand;

import java.util.List;
import java.util.Map;

public interface BrandService {
    /**
     * 查询所有品牌
     * @return
     */
    List<Brand> findAll();

    /**
     * 分页查询
     * @param pageNum
     * @param pageSize
     * @return
     */

     PageResult findPage(Integer pageNum,Integer pageSize);

    /**
     * 条件查询
     * @param pageNum
     * @param pageSize
     * @param brand
     * @return
     */
    PageResult search(Integer pageNum,Integer pageSize,Brand brand);

    /**
     * 添加保存
     * @param brand
     */
   public void  add(Brand brand);

    /**
     * 按照id查询修改回显品牌
     * @param id
     * @return
     */
   Brand findOne(Long id);

    /**
     * 更新数据
     * @param brand
     */
   void update(Brand brand);

    /**
     * 删除规格
     * @param ids
     */
   void delete(Long[] ids);
    /**
     * 新增模块：初始化品牌下拉框列表数据
     * @return
     */
    public List<Map<String,String>> selectOptionList();

}
