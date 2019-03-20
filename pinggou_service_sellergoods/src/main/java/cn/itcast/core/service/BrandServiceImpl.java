package cn.itcast.core.service;

import cn.itcast.core.dao.good.BrandDao;
import cn.itcast.core.entity.PageResult;
import cn.itcast.core.pojo.good.Brand;
import cn.itcast.core.pojo.good.BrandQuery;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class BrandServiceImpl implements BrandService {
    //注入dao接口
    @Resource
    private BrandDao brandDao;
    @Override
    public List<Brand> findAll() {
        //根据条件查询
        List<Brand> brands = brandDao.selectByExample(null);
        return brands;
    }

    /**
     * 分页查询
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public PageResult findPage(Integer pageNum, Integer pageSize) {
        //设置分页条件参数
        PageHelper.startPage(pageNum,pageSize);
        //查询结果集
        Page<Brand> page = (Page<Brand>) brandDao.selectByExample(null);
        //将结果返回pageResult结果集中 page封装的数据太多，传输效率低
        //封装结果集
        PageResult pageResult=new PageResult(page.getTotal(),page.getResult());
        return pageResult;
    }

    /**
     * 模糊分页查询
     * @param pageNum
     * @param pageSize
     * @param brand
     * @return
     */
    @Override
    public PageResult search(Integer pageNum, Integer pageSize, Brand brand) {
        //设置分页条件参数
        PageHelper.startPage(pageNum,pageSize);
        //设置查询条件 封装查询条件对象XXXQUERY
        BrandQuery brandQuery=new BrandQuery();
        //封装具体的查询条件对象
        BrandQuery.Criteria criteria = brandQuery.createCriteria();
        //拼接sql语句 判断getname getfristchar  trim()去掉两边多余的空格
        if (brand.getName()!=null&&!"".equals(brand.getName().trim())){
            criteria.andNameLike("%"+brand.getName().trim()+"%");
        }
        if (brand.getFirstChar()!=null&&!"".equals(brand.getFirstChar().trim())){
            criteria.andFirstCharEqualTo(brand.getFirstChar().trim());
        }
        brandQuery.setOrderByClause("id desc");
        //进行分页查询
        Page<Brand> page = (Page<Brand>) brandDao.selectByExample(brandQuery);
        PageResult pageResult=new PageResult(page.getTotal(),page.getResult());
        return pageResult;
    }

    /**
     * 添加保存品牌
     * @param brand
     */
    @Transactional
    @Override
    public void add(Brand brand) {
        brandDao.insertSelective(brand);
    }

    /**
     * 修改回显品牌
     * @param id
     * @return
     */
    @Override
    public Brand findOne(Long id) {
        return brandDao.selectByPrimaryKey(id);
    }

    /**
     * 更新品牌数据
     * @param brand
     */
    @Transactional
   @Override
    public void update(Brand brand) {
        brandDao.updateByPrimaryKey(brand);
    }
    //删除事务注释
    @Transactional
    @Override
    public void delete(Long[] ids) {
        //判断是否为空
        if (ids!=null&&ids.length>0){
            //遍历ids
            for (Long id:ids){
                brandDao.deleteByPrimaryKey(id);
            }

        }
    }

    /**
     * 新增模板：初始化品牌的下拉框列表数据
     * @return
     */
    @Override
    public List<Map<String, String>> selectOptionList() {
        return brandDao.selectOptionList();
    }
}
