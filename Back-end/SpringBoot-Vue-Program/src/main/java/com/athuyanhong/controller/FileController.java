package com.athuyanhong.controller;

import com.athuyanhong.pojo.Result;
import com.athuyanhong.utils.AliOssUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

/**
 * @Author Wei_Rong
 * @Description
 * @Date 2023-11-09-0009 00:08
 * @Package_Name com.athuyanhong.controller
 * @Project_Name SpringBoot-Vue-Program
 */

@RestController
@RequestMapping("/upload")
public class FileController {

    @PostMapping
    public Result<String> uploadFile(MultipartFile file) throws IOException {
//        String endpoint = "https://oss-cn-guangzhou.aliyuncs.com";
//
//        String accessKeyId = "LTAI5t6u5kUu5iSoUoB3MmWP";
//
//        String accessKeySecret= "4oRhP10oe08icKaazYuznTMliXhiXK";
//




        final var originalFilename = file.getOriginalFilename();
        String fileName = UUID.randomUUID() + originalFilename.substring(originalFilename.lastIndexOf("."));


        final var url = AliOssUtil.uploadFile(fileName, file.getInputStream());


        return Result.success(url);


//        file.transferTo(
//                new File("D:\\IDEAprogram\\SpringBoot-Vue-Program\\src\\main\\resources\\com\\file\\"
//                        + fileName ));
//
//
//        //TODO
//        return Result.success("D:\\IDEAprogram\\SpringBoot-Vue-Program\\src\\main\\resources\\com\\file\\" + fileName);
    }
}
