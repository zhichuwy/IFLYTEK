package com.iflytek.voicecloud.lfasr.demo.util;

import java.util.List;

import com.alibaba.fastjson.JSON;
import com.iflytek.voicecloud.lfasr.demo.dto.Data;

public class DataFormat {

	// Json 解析
	// 转写结果:
	// [{"bg":"50","ed":"9850","onebest":"随。","speaker":"0"},
	// {"bg":"9860","ed":"11870","onebest":"光而变心动所在！","speaker":"0"},
	// {"bg":"11870","ed":"14970","onebest":"啊17破OPPO拍照手机。","speaker":"0"}]

	public static String formatData2PlainText(String jsonStr) {

		List<Data> list = (List<Data>) JSON.parseArray(jsonStr, Data.class);
		StringBuilder result = new StringBuilder();
		// 提取"onebest" 拼接
		for (Data data : list) {
			result.append(data.getOnebest());
		}
		return result.toString();
	}

	public static int export2File(String jsonStr, String path) {

		return 0;
	}
}
