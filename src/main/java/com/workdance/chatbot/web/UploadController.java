package com.workdance.chatbot.web;

import com.workdance.chatbot.utils.FileUtil;
import com.workdance.chatbot.utils.NetUtil;
import com.workdance.chatbot.utils.TimeUtil;
import com.workdance.chatbot.web.helper.Result;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.java.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Timer;

@Controller
@RequestMapping("api/v1")
public class UploadController {
    private final static Logger logger = LoggerFactory.getLogger(UploadController.class);

    @PostMapping("/upload")
    @ResponseBody
    public Result<String> upload(@PathVariable MultipartFile file, HttpServletRequest request) {
        String addr = NetUtil.getIpAddr(request).replaceAll("\\.", "/").replaceAll(":", "/");

        Result<String> result = new Result<>();
        if (file.isEmpty()) {
            result.setErrorCode("406");
            return result;
        }

        // 是否是图片格式
        String fileName = file.getOriginalFilename();
        String suffixName = FileUtil.getSuffixName(fileName);
        logger.info("Uploading file {} to {}", fileName, addr);

        if (FileUtil.isPicFile(suffixName)) {
            String time = TimeUtil.getDirByTime();
            File dest = FileUtil.generatePicFile(suffixName, time, addr);
            result.setData(fileName);
            fileName = dest.getName();
            logger.info("save to " + dest.getAbsolutePath());
            if (!dest.getParentFile().exists()) {
                dest.getParentFile().mkdirs();
            }
            try {
                file.transferTo(dest);
                String url = "/uploadImages/" + addr + "/" + time + fileName;
                result.setErrorCode("200");
                result.setData(url);
                return result;
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            result.setErrorCode("500");
            result.setErrorMessage("不是jpg/jpeg/png/svg/gif图片！");
            return result;
        }
        result.setErrorCode("500");
        result.setErrorMessage("未知错误");
        return result;
    }
}
