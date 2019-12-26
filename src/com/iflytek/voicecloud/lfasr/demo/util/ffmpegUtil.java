package com.iflytek.voicecloud.lfasr.demo.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ffmpegUtil {

	// 在指定路径下执行ffmpeg命令
	public static void executeCMD(String cmd, String dirTodoCMD) throws IOException, InterruptedException {

		String[] cmdarray = new String[3];
		cmdarray[0] = "cmd";
		cmdarray[1] = "/c";
		cmdarray[2] = cmd;
		ProcessBuilder builder = new ProcessBuilder(cmdarray);
		if (dirTodoCMD != null)
			builder.directory(new File(dirTodoCMD));
		builder.redirectErrorStream(true);
		Process p = builder.start();

		String line = null;
		InputStream inputStream = p.getInputStream();
		BufferedReader inputStreamBr = new BufferedReader(new InputStreamReader(inputStream, "gbk"));
		while ((line = inputStreamBr.readLine()) != null) {
			System.out.println("Out > " + line);
		}

		p.waitFor();
	}
}
