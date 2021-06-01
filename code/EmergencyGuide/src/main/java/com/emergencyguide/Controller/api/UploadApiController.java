package com.emergencyguide.Controller.api;

import com.alibaba.fastjson.JSONObject;
import com.emergencyguide.Controller.Util.DateUtil;
import com.emergencyguide.Controller.Util.UUIDGenerateUtil;
import com.emergencyguide.Entity.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author DarckLH
 * @date 2021/5/25 20:44
 * @Description
 */
@Controller
@RequestMapping("file")
@Api(value = "文件上传接口", tags = "文件上传Api")
public class UploadApiController {

    //日志对象
    private Logger logger = LoggerFactory.getLogger(UploadApiController.class);

    //上传路径
    @Value("/Emeguide")
    private String uploadFilePath;
    //上传路径
    @Value("/Emeguide/DefaultImages")
    private String uploadDefaultFilePath;

    /**
     * 上传图片
     * @param file
     * @return
     */
    @ApiOperation(value="上传图片")
    @RequestMapping(value = "/upload/image", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    @ApiImplicitParam(name = "image", value = "接收图片文件", required = true, dataType = "string")
    public String uploadImage(@RequestParam("image") MultipartFile file) {
        JSONObject object = new JSONObject();
        System.out.println(file.getOriginalFilename());
        if (!file.isEmpty()) {

            String fileName = file.getOriginalFilename(); //获取上传的文件名
            String suffixName = fileName.substring(fileName.lastIndexOf("." )+ 1); //获取后缀名
            fileName = "Image_" + DateUtil.formatDateByFormat(new Date(),"yyyyMMddHHmmsssss") + "_" + UUIDGenerateUtil.generateShortUuid() + "." + suffixName;

            File dest = new File(new File(uploadFilePath).getAbsolutePath() +'/'+ fileName);
            if (!dest.getParentFile().exists()) {
                dest.getParentFile().mkdirs();
            }
            try {

                file.transferTo(dest);
                String realUrl = "/file/images/"+fileName;
                Result<String> result= new Result<String>();
                result = result.success("上传成功");
                List<String> data = new ArrayList<>();
                data.add(realUrl);
                result.setData(data);
                return result.toString();

            } catch (Exception e) {

                e.printStackTrace();
                object.put("success",2);
                object.put("result","程序错误，请重新上传");
                return object.toString();

            }
        } else {

            object.put("success",2);
            object.put("result","上传失败，因为文件是空的");
            return object.toString();

        }
    }
    /**
     * 上传系统设置默认图片
     * @param file
     * @return
     */
    @RequestMapping(value = "/upload/defaultImg", method = RequestMethod.POST, produces="application/json")
    @ResponseBody
    public String handleFileUpload(@RequestParam("image") MultipartFile file) {
        JSONObject object = new JSONObject();
        if (!file.isEmpty()) {

            String fileName = file.getOriginalFilename(); //获取上传的文件名
            String suffixName = fileName.substring(fileName.lastIndexOf("." )+ 1); //获取后缀名
            fileName = "Image_" + DateUtil.formatDateByFormat(new Date(),"yyyyMMddHHmmsssss") + "_" + UUIDGenerateUtil.generateShortUuid() + "." + suffixName;

            File dest = new File(new File(uploadDefaultFilePath).getAbsolutePath() +"/"+ fileName);
            if (!dest.getParentFile().exists()) {
                dest.getParentFile().mkdirs();
            }
            try {

                file.transferTo(dest);
                String realUrl = "/file/images/defaultImages/"+fileName;
                Result<String> result= new Result<String>();
                result = result.success("上传成功");
                List<String> data = new ArrayList<>();
                data.add(realUrl);
                result.setData(data);
                return result.toString();

            } catch (Exception e) {

                e.printStackTrace();
                object.put("success",2);
                object.put("result","程序错误，请重新上传");
                return object.toString();

            }
        } else {

            object.put("success",2);
            object.put("result","上传失败，因为文件是空的");
            return object.toString();

        }
    }

    /**
     * images文件下载
     * @param request
     * @param response
     * @return
     */
    @GetMapping("/images/**")
    @ResponseBody
    public String imagesDownLoad(HttpServletRequest request, HttpServletResponse response){
        String url = request.getRequestURI();
        JSONObject object=new JSONObject();
        String fileName = url.replace("/file/images/","");
        File file = new File(uploadFilePath +'/'+ fileName);
        if(!file.exists()){
            object.put("success",2);
            object.put("result","下载文件不存在");
            return object.toString();
        }
        response.reset();
        response.setContentType("application/octet-stream");
        response.setCharacterEncoding("utf-8");
        response.setContentLength((int) file.length());
        response.setHeader("Content-Disposition", "attachment;filename=" + fileName );
        try(BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));) {
            byte[] buff = new byte[1024];
            OutputStream os  = response.getOutputStream();
            int i = 0;
            while ((i = bis.read(buff)) != -1) {
                os.write(buff, 0, i);
                os.flush();
            }
        } catch (IOException e) {
            object.put("success",2);
            object.put("result","下载文件不存在");
            return object.toString();
        }
        object.put("success",0);
        object.put("result","下载文件成功");
        return object.toString();
    }

    /**
     * 默认文件下载
     * @param request
     * @param response
     * @return
     */
    @GetMapping("/images/defaultImages/**")
    @ResponseBody
    public String fileDownLoad(HttpServletRequest request,HttpServletResponse response){
        String url = request.getRequestURI();
        JSONObject object=new JSONObject();
        String fileName = url.replace("/file/images/defaultImages/","");
        File file = new File(uploadDefaultFilePath +"/"+ fileName);
        if(!file.exists()){
            object.put("success",2);
            object.put("result","下载文件不存在");
            return object.toString();
        }
        response.reset();
        response.setContentType("application/octet-stream");
        response.setCharacterEncoding("utf-8");
        response.setContentLength((int) file.length());
        response.setHeader("Content-Disposition", "attachment;filename=" + fileName );
        try(BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));) {
            byte[] buff = new byte[1024];
            OutputStream os  = response.getOutputStream();
            int i = 0;
            while ((i = bis.read(buff)) != -1) {
                os.write(buff, 0, i);
                os.flush();
            }
        } catch (IOException e) {
            object.put("success",2);
            object.put("result","下载文件失败");
            return object.toString();
        }
        object.put("success",0);
        object.put("result","下载文件成功");
        return object.toString();
    }

}

