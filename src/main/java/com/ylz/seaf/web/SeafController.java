package com.ylz.seaf.web;

import com.sun.org.apache.xml.internal.security.utils.Base64;
import com.ylz.seaf.core.*;
import org.springframework.web.bind.annotation.*;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by 86189 on 2019/5/17.
 */
@RequestMapping(value="/seaf")
@RestController
public class SeafController {

    public static int qrNum = 0;


    /**
     * 获取授权码
     * @param user 用户信息
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/auth", method = {RequestMethod.POST})
    ResultModel getToken(@RequestBody User user) {
        System.out.println(user);
        StringBuilder tokenBuilder = new StringBuilder();
        String userName = user.getUserName();
        String password = user.getPassword();
        String mac = user.getHardWareSerial();
        String imgbase64 = user.getImagData();
        BASE64Decoder base64Encoder = new BASE64Decoder();
        try {
            byte[] bytes = base64Encoder.decodeBuffer(imgbase64);

            File file = new File("d:/my.jpg");
            OutputStream os = new FileOutputStream(file);
            os.write(bytes, 0, bytes.length);
            os.flush();
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (userName != null && password != null && mac != null) {
            tokenBuilder.append(userName.hashCode())
                    .append(password.hashCode())
                    .append(mac.hashCode());
        } else {
            tokenBuilder.append(user.hashCode());
        }

        ResultModel mod = new ResultModel();
        mod.setFlag("1");
        mod.setCause("success");

        Map<String, String> data  = new HashMap<>();
        data.put("auth", tokenBuilder.toString());
        mod.setData(data);

        System.out.println("获取授权码成功。。。。");

        return mod;
    }

    @ResponseBody
    @RequestMapping(value = "/requireAuth", method = {RequestMethod.POST})
    ResultModel requireAuth(@RequestBody Param1 param1 ) {
        boolean forceAuth = true;
        if("Q04.05.01.01".equals(param1.getFunid())) {
            forceAuth = true;
        }
        ResultModel mod = new ResultModel();
        mod.setFlag("1");
        mod.setCause("success");

        Map<String, String> data  = new HashMap<>();
        data.put("forceAuth", forceAuth ? "1" : "0");
        mod.setData(data);

        System.out.println("获取是否需要认证信息成功...");
        return mod;
    }

    @ResponseBody
    @RequestMapping(value = "/getDoctorQrCode", method = {RequestMethod.POST})
    ResultModel getDoctorQrCode(@RequestBody Param2 param2) {
        ResultModel mod = new ResultModel();
        mod.setFlag("1");
        mod.setCause("success");

        InputStream in = null;
        byte[] imgData = null;
        try {
            in = new FileInputStream("D:\\qrCode.jpg");
            imgData = new byte[in.available()];
            in.read(imgData);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
            mod.setFlag("0");
            mod.setCause("读取二维码失败");;
        }

        String qrCode = new String();
        if("1".equals(mod.getFlag())) {
            BASE64Encoder encoder = new BASE64Encoder();
            qrCode = encoder.encode(imgData);
        }
        Map<String, String> data  = new HashMap<>();
        data.put("bkz543", qrCode);
        mod.setData(data);

        return mod;
    }

    @ResponseBody
    @RequestMapping(value = "/getDoctorScanResults", method = {RequestMethod.POST})
    ResultModel getDoctorScanResults(@RequestBody Param2 param2) {
        ResultModel mod = new ResultModel();
        mod.setFlag("1");
        mod.setCause("success");

        Map<String, String> data  = new HashMap<>();
        data.put("qrIdentifyCode",  "abc");
        mod.setData(data);

        return mod;
    }

    @ResponseBody
    @RequestMapping(value = "/getMedicalQrCode", method = {RequestMethod.POST})
    ResultModel getMedicalQrCode(@RequestBody Param2 param2) {
        ResultModel mod = new ResultModel();
        mod.setFlag("1");
        mod.setCause("success");

        InputStream in = null;
        byte[] imgData = null;
        try {
            in = new FileInputStream("D:\\qrCode.gif");
            imgData = new byte[in.available()];
            in.read(imgData);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
            mod.setFlag("0");
            mod.setCause("读取二维码失败");;
        }

        String qrCode = new String();
        if("1".equals(mod.getFlag())) {
            BASE64Encoder encoder = new BASE64Encoder();
            qrCode = encoder.encode(imgData);
        }
        Map<String, String> data  = new HashMap<>();
        data.put("qrCode", qrCode);
        mod.setData(data);

        System.out.println("获取二维码图片成功。。。。");
        return mod;
    }

    @ResponseBody
    @RequestMapping(value = "/getMedicalScanResults", method = {RequestMethod.POST})
    ResultModel getMedicalScanResults(@RequestBody Param2 param2) {
        ResultModel mod = new ResultModel();
        Map<String, String> data  = new HashMap<>();

        if(qrNum > 5) {
            qrNum = 0;
            mod.setFlag("1");
            mod.setCause("success");
            data.put("qrIdentifyCode",  "1");
            System.out.println("用户成功扫码。。。。");
        } else {
            qrNum++;
            mod.setFlag("1");
            mod.setCause("fail");
            data.put("qrIdentifyCode",  "0");
            System.out.println("获取二维码扫码结果(未扫码)。。。。");
        }



        mod.setData(data);

        return mod;
    }

    static boolean status = false;

    @ResponseBody
    @RequestMapping(value = "/in", method = {RequestMethod.POST})
    ResultModel in(@RequestBody BaseParam param) {
        ResultModel mod = new ResultModel();
        String funId = param.getFunid();

        if("Q00.00.00.00".equals(funId)) {
            // 登录
            mod.setFlag("1");
            Map<String, String> data = new HashMap<>();
            data.put("akb021", "厦门中医院");
            data.put("akb020", "org001");
            mod.setData(data);
            status = false;
        } else if("F04.13.01.14".equals(funId)) {
            //获取申报状态
            mod.setFlag(status ? "1" : "0");
            mod.setCause("");
        } else if("F04.13.01.15".equals(funId)) {
            // 进行申报
            mod.setFlag("1");
            status = true;
            System.out.println(param);
        }
        return mod;
    }
}
