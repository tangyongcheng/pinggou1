package cn.itcast.core.service;

import cn.itcast.core.dao.specification.SpecificationDao;
import cn.itcast.core.dao.specification.SpecificationOptionDao;
import cn.itcast.core.entity.PageResult;
import cn.itcast.core.pojo.specification.Specification;
import cn.itcast.core.pojo.specification.SpecificationOption;
import cn.itcast.core.pojo.specification.SpecificationOptionQuery;
import cn.itcast.core.pojo.specification.SpecificationQuery;
import cn.itcast.core.vo.SpecVo;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class SpecificationServiceImpl implements SpecificationService {
    //注入
    @Resource
    private SpecificationDao specificationDao;

    @Resource
    private SpecificationOptionDao specificationOptionDao;
    /**
     * 规格列表搜索查询
     * @param page
     * @param rows
     * @param specification
     * @return
     */
    @Override
    public PageResult search(Integer page, Integer rows, Specification specification) {
        //设置分页条件
        PageHelper.startPage(page,rows);
        //设置查询条件
        SpecificationQuery query = new SpecificationQuery();
        if (specification.getSpecName()!=null&&!"".equals(specification.getSpecName().trim())){
            query.createCriteria().andSpecNameLike("%"+specification.getSpecName().trim()+"%");
        }
        //查询按照id排序
        query.setOrderByClause("id desc");
        //按查询
        Page<Specification> page1 = (Page<Specification>) specificationDao.selectByExample(query);
        //封装结果
        PageResult pageResult = new PageResult(page1.getTotal(),page1.getResult());
        return pageResult;
    }

    /**
     * 新建保存规格
     * @param specVo
     */
    @Transactional
    @Override
    public void add(SpecVo specVo) {
        //保存规格插入数据后需要返回自增主键的id
        Specification specification = specVo.getSpecification();
        specificationDao.insertSelective(specification);//返回自增主键ID
        //保存规格选项外键spec_id
        List<SpecificationOption> specificationOptionsList = specVo.getSpecificationOptionsList();
        //设置条件
        if(specificationOptionsList!=null&&specificationOptionsList.size()>0){
          //循环遍历，specificationOptionsList是一个集合
            for (SpecificationOption specificationOption:specificationOptionsList){
                //设定Specification主键ID=SpecificationOption的外键specid
                specificationOption.setSpecId(specification.getId());
                specificationOptionDao.insertSelective(specificationOption);
            }
            // TODO: 2019/3/19 :插入报表数据导入数据库中，一条条插入内存会溢出，所以选择批量插入
            //批量插入
            specificationOptionDao.insertSelectives(specificationOptionsList);
        }
    }

    /**
     * 规格回显
     * @param id
     * @return
     */
    @Override
    public SpecVo findOne(Long id) {
        //获取规格
        Specification specification = specificationDao.selectByPrimaryKey(id);
        //通过规格获取规格选项
        SpecificationQuery specificationQuery = new SpecificationQuery();
        //封装条件
        specificationQuery.createCriteria().andIdEqualTo(id);
        List<SpecificationOption> specificationOptionList = specificationOptionDao.selectByExample(specificationQuery);
        //封装到vo中
        SpecVo specVo = new SpecVo();
        //set  specVo.setSpecification(specification);
        specVo.setSpecification(specification);
        specVo.setSpecificationOptionsList(specificationOptionList);
        return specVo;
    }

    /**
     * 更新规格
     * @param specVo
     */
    @Transactional
    @Override
    public void update(SpecVo specVo) {
        //获取规格->先查询规格
        Specification specification = specVo.getSpecification();
        specificationDao.updateByPrimaryKeySelective(specification);
        //更新规格选项
        //先删除，根据外键删除
        SpecificationOptionQuery specificationOptionQuery=new SpecificationOptionQuery();
        //封装条件
        specificationOptionQuery.createCriteria().andIdEqualTo(specification.getId());
        //调用deleteByExample方法
        specificationOptionDao.deleteByExample(specificationOptionQuery);
        //插入保存规格选项外键
        List<SpecificationOption> specificationOptionsList = specVo.getSpecificationOptionsList();
        //过滤条件
        if (specificationOptionsList!=null&&specificationOptionsList.size()>0){
            for (SpecificationOption specificationOption:specificationOptionsList){
                //设置外键spec_id
                specificationOption.setSpecId(specification.getId());
            }
        }
    }

    /**
     * 删除规格
     * @param ids
     */
    @Transactional
    @Override
    public void delete(Long[] ids) {
        if (ids!=null&&ids.length>0){
            for (Long id:ids){
                //删除规格选项
                SpecificationOptionQuery specificationOptionQuery = new SpecificationOptionQuery();
                specificationOptionQuery.createCriteria().andSpecIdEqualTo(id);
                specificationOptionDao.deleteByExample(specificationOptionQuery);
                //删除规格
                specificationDao.deleteByPrimaryKey(id);
            }
        }
    }

    /**
     * 新增模板：初始化规格下拉框列表数据
     * @return
     */
    @Override
    public List<Map<String, String>> selectOptionList() {
        return specificationDao.selectOptionList();
    }
}
