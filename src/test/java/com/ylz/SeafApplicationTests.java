package com.ylz;

import com.ylz.seaf.core.ResultModel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import sun.misc.BASE64Encoder;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SeafApplicationTests {

	@Test
	public void contextLoads() {
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
			int ii = qrCode.length();
			ii++;
		}
		Map<String, String> data  = new HashMap<>();
		data.put("bkz543", qrCode);
		mod.setData(data);
	}

}
