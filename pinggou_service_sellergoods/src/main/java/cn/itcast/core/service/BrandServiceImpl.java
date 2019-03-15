package cn.itcast.core.service;

import cn.itcast.core.dao.good.BrandDao;
import cn.itcast.core.pojo.good.Brand;
import com.alibaba.dubbo.config.annotation.Service;

import javax.annotation.Resource;
import java.util.List;
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
}
