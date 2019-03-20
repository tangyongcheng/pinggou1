package cn.itcast.core.controller.brand;

import cn.itcast.core.entity.PageResult;
import cn.itcast.core.entity.Result;
import cn.itcast.core.pojo.specification.Specification;
import cn.itcast.core.service.SpecificationService;
import cn.itcast.core.vo.SpecVo;
import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;


@RestController
     @RequestMapping("/specification")
     public class SpecificationController {

        @Reference//跟Resource的区别
        private SpecificationService specificationService;

        /**
         * 规格列表查询
         * @param page
         * @param rows
         * @param specification
         * @return
         */
         @RequestMapping("/search.do")
         public PageResult search(Integer page, Integer rows,@RequestBody Specification specification){
             PageResult search = specificationService.search(page, rows, specification);
             return search;
         }

        /**
         * 暴君规格
         * @param specVo
         * @return
         */
         @RequestMapping("/add.do")
        public Result add(@RequestBody SpecVo specVo){
             try {
                 specificationService.add(specVo);
                 return new Result(true,"保存成功");
             }catch (Exception e){
                 e.printStackTrace();
                 return new Result(true,"保存失败");
             }
         }

        /**
         * 回显规格
         * @param id
         * @return
         */
         @RequestMapping("/findOne.do")
        public SpecVo findOne(Long id){
             return specificationService.findOne(id);
         }

        /**
         * 规格更新
         * @param specVo
         * @return
         */
        @RequestMapping("/update.do")
        public Result update(@RequestBody SpecVo specVo){
             try {
                 specificationService.update(specVo);
                 return new Result(true,"更新成功");
             }catch (Exception e){
                 e.printStackTrace();
                 return new Result(true,"更新失败");
             }
        }

        /**
         * 规格删除
         * @param ids
         * @return
         */
    @RequestMapping("/delete.do")
        public Result delete(Long[] ids){
           try{
               specificationService.delete(ids);
               return new Result(true,"删除成功");
           }catch (Exception e){
               e.printStackTrace();
               return new Result(true,"删除失败");
           }
    }

    @RequestMapping("/selectOptionList.do")
    public List<Map<String, String>> selectOptionList() {
        return specificationService.selectOptionList();
    }
}
