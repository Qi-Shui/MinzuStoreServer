package com.minzu.minzustore.controller.admin;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.minzu.minzustore.entity.Msg;
import com.minzu.minzustore.entity.ProductSwiperImage;
import com.minzu.minzustore.service.ProductSwiperImageService;
import com.minzu.minzustore.util.DateUtil;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**后台管理-产品轮播图Controller控制器
 */
@RestController
@RequestMapping("/admin/productSwiperImage")
public class AdminProductSwiperImageController {

    @Autowired
    private ProductSwiperImageService productSwiperImageService;

    @Value("${productImagesFilePath}")
    private String productSwiperImagesFilePath;

    /**
     * 查询所有
     * @param id
     * @return
     */
    @GetMapping("/list/{id}")
    public Msg list(@PathVariable(value = "id")Integer id){
        List<ProductSwiperImage> list=productSwiperImageService.list(new QueryWrapper<ProductSwiperImage>().eq("productId",id));
        Map<String,Object> resultMap=new HashMap<>();
        resultMap.put("list",list);
        return Msg.ok(resultMap);
    }

    /**
     * 添加
     * @param productSwiperImage
     * @return
     */
    @RequestMapping("/add")
    public Msg add(@RequestBody ProductSwiperImage productSwiperImage){
        int maxId = productSwiperImageService.maxId();
        productSwiperImage.setId(maxId + 1);
        int productSwiperImageCount = Math.toIntExact(productSwiperImageService.count(new QueryWrapper<ProductSwiperImage>().eq("productId",productSwiperImage.getProductId())));
        productSwiperImage.setSort(productSwiperImageCount + 1);
        productSwiperImageService.saveOrUpdate(productSwiperImage);
        return Msg.ok();
    }


    /**
     * 删除
     * @param id
     * @return
     */
    @GetMapping("/delete/{id}")
    public Msg delete(@PathVariable(value = "id")Integer id){
        productSwiperImageService.removeById(id);
        return Msg.ok();
    }

    /**
     * 上传商品轮播图片
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
            FileUtils.copyInputStreamToFile(file.getInputStream(),new File(productSwiperImagesFilePath+newFileName));
            resultMap.put("code",0);
            resultMap.put("msg","上传成功");
            Map<String,Object> dataMap=new HashMap<>();
            dataMap.put("title",newFileName);
            dataMap.put("src","/image/product/"+newFileName);
            resultMap.put("data",dataMap);
        }
        return resultMap;
    }


}
