package cn.itcast.core.vo;


import cn.itcast.core.pojo.specification.Specification;
import cn.itcast.core.pojo.specification.SpecificationOption;

import java.io.Serializable;
import java.util.List;

/**
 * 包装类型的pojo，包装规格以及规格选项
 * vo:value object,封装页面的数据
 */
@SuppressWarnings("serial")//忽略序列化接口没有定义serialVersionUID警告
public class SpecVo implements Serializable{
    private Specification specification;  //规格
    private List<SpecificationOption> specificationOptionsList;//规格选项


    public Specification getSpecification() {
        return specification;
    }
    public void setSpecification(Specification specification) {
        this.specification = specification;
    }

    public List<SpecificationOption> getSpecificationOptionsList() {
        return specificationOptionsList;
    }

    public void setSpecificationOptionsList(List<SpecificationOption> specificationOptionsList) {
        this.specificationOptionsList = specificationOptionsList;
    }
}
