package com.minzu.minzustore.controller.admin;

import com.minzu.minzustore.entity.HomeType;
import com.minzu.minzustore.entity.Msg;
import com.minzu.minzustore.entity.PageBean;
import com.minzu.minzustore.entity.Type;
import com.minzu.minzustore.service.HomeTypeService;
import com.minzu.minzustore.service.TypeService;
import com.minzu.minzustore.util.DateUtil;
import com.minzu.minzustore.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**后台管理-商品大类Controller控制器
 */
@RestController
@RequestMapping("/admin/bigType")
public class AdminBigTypeController {

    @Autowired
    private HomeTypeService bigTypeService;

    @Autowired
    private TypeService smallTypeService;

    @Value("${homeTypeImagesFilePath}")
    private String bigTypeImagesFilePath;

    /**
     * 根据条件分页查询商品大类信息
     * @param pageBean
     * @return
     */
    @RequestMapping("/list")
    public Msg list(@RequestBody PageBean pageBean){
        System.out.println(pageBean);
        String query=pageBean.getQuery().trim();
        Page<HomeType> page=new Page<>(pageBean.getPageNum(),pageBean.getPageSize());
        Page<HomeType> pageResult = bigTypeService.page(page, new QueryWrapper<HomeType>().like(StringUtil.isNotEmpty(query), "name", query));
        Map<String,Object> map=new HashMap<>();
        map.put("bigTypeList",pageResult.getRecords());
        map.put("total",pageResult.getTotal());
        return Msg.ok(map);
    }

    /**
     * 添加或者修改
     * @param bigType
     * @return
     */
    @RequestMapping("/save")
    public Msg save(@RequestBody HomeType bigType){
        if(bigType.getId()==null || bigType.getId()==-1){
            int maxId = bigTypeService.maxId();
            bigType.setId(maxId + 1);
            bigTypeService.save(bigType);
        }else{
            bigTypeService.saveOrUpdate(bigType);
        }
        return Msg.ok();
    }

    /**
     * 根据id查询
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Msg findById(@PathVariable(value = "id")Integer id){
        HomeType bigType = bigTypeService.getById(id);
        Map<String,Object> map=new HashMap<>();
        map.put("bigType",bigType);
        return Msg.ok(map);
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @GetMapping("/delete/{id}")
    public Msg delete(@PathVariable(value = "id")Integer id){
        // 加个判断 大类下面如果有小类 返回报错提示
        if(smallTypeService.count(new QueryWrapper<Type>().eq("homeTypeId",id))>0){
            return Msg.error(500,"首页类别下有小分类信息，不能删除");
        }else{
            bigTypeService.removeById(id);
            return Msg.ok();
        }
    }

    /**
     * 上传商品大类图片
     * @param file
     * @return
     * @throws Exception
     */
    @RequestMapping("/uploadImage")
    public Map<String,Object> uploadImage(MultipartFile file)throws Exception{
        Map<String,Object> resultMap=new HashMap<>();
        if(!file.isEmpty()){
            // 获取文件名
            String originalFilename = file.getOriginalFilename();
            String suffixName=originalFilename.substring(originalFilename.lastIndexOf("."));
            String newFileName= DateUtil.getCurrentDateStr()+suffixName;
            FileUtils.copyInputStreamToFile(file.getInputStream(),new File(bigTypeImagesFilePath+newFileName));
            resultMap.put("code",0);
            resultMap.put("msg","上传成功");
            Map<String,Object> dataMap=new HashMap<>();
            dataMap.put("title",newFileName);
            dataMap.put("src","/image/homeType/"+newFileName);
            resultMap.put("data",dataMap);
        }
        return resultMap;
    }

    /**
     * 查询所有数据，下拉框用到
     * @return
     */
    @RequestMapping("/listAll")
    public Msg listAll(){
        Map<String,Object> map=new HashMap<>();
        map.put("bigTypeList",bigTypeService.list());
        return Msg.ok(map);
    }

}
