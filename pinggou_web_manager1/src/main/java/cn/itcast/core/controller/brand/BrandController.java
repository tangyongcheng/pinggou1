package cn.itcast.core.controller.brand;

import cn.itcast.core.entity.PageResult;
import cn.itcast.core.entity.Result;
import cn.itcast.core.pojo.good.Brand;
import cn.itcast.core.service.BrandService;
import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/brand")
public class BrandController {
    @Reference
    private BrandService brandService;

    /**
     * 查询所有品牌
     * @return
     */
    @RequestMapping("/findAll.do")
    public List<Brand> findAll(){
        return brandService.findAll();
    }

    /**
     * 分页查询
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping("/findPage.do")
    public PageResult findPage(Integer pageNum,Integer pageSize){
        return  brandService.findPage(pageNum,pageSize);
    }

    /**
     * 搜索查询
     * @param pageNum
     * @param pageSize
     * @param brand
     * @return
     */
    @RequestMapping("/search.do")
    public PageResult search(Integer pageNum,Integer pageSize ,@RequestBody Brand brand){
        return  brandService.search(pageNum,pageSize,brand);
    }

    /**
     * 添加品牌
     * @param brand
     * @return
     */
    @RequestMapping("/add.do")
    public Result add(@RequestBody Brand brand){
        try{
            brandService.add(brand);
            return new Result(true,"保存成功");
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false,"保存失败");
        }
    }

    /**
     * 回显数据
     * @param id
     * @return
     */
    @RequestMapping("/findOne.do")
    public Brand findOne(Long id){
      return   brandService.findOne(id);
    }

    /**
     * 修改品牌数据
     * @param brand
     * @return
     */
    @RequestMapping("/update.do")
    public Result update(@RequestBody Brand brand){
        try{
            brandService.update(brand);
            return new Result(true,"更新成功");
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false,"更新失败");
        }
    }
  @RequestMapping("/delete.do")
    public Result delete(Long[] ids){
       try {
           brandService.delete(ids);
           return new Result(true,"删除成功");
       }catch (Exception e){
           e.printStackTrace();
           return new Result(false,"删除失败");
       }
       }

    /**
     * 新增：初始化品牌下拉列表
     * @return
     */
    @RequestMapping("/selectOptionList.do")
       public List<Map<String, String>> selectOptionList() {
           return brandService.selectOptionList();
       }
}
