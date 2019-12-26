package com.yang.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

//===================并发处理===============================
class StreamManage extends Thread {
	InputStream is;
	String type;

	public StreamManage(InputStream is, String type) {
		this.is = is;
		this.type = type;
	}

	public void run() {
		InputStreamReader isr = null;
		try {
			isr = new InputStreamReader(is, "gbk");
		} catch (UnsupportedEncodingException uee) {
			uee.printStackTrace();
		}
		BufferedReader br = new BufferedReader(isr);
		String line = null;
		try {
			while ((line = br.readLine()) != null) {
				if (type.equals("Error")) {
					System.out.println("Error > " + line);
				} else {
					System.out.println("INFO > " + line);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}
}

public class CmdTest {

	public static void main(String[] args) throws IOException {

//		String path = "mspaint";
//		Runtime run = Runtime.getRuntime();
//		try {
//			Process process = run.exec("cmd.exe /k start " + path);
//			InputStream in = process.getInputStream();
//			while (in.read() != -1) {
//				System.out.println(in.read());
//			}
//			in.close();
//			process.waitFor();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}

		// String cmd = "gcc -v";
		// String[] cmdarray = { "cmd", "/C", cmd };
		// executeCMDfile(cmdarray, "D:/hexo/Blog", "D:/a.txt");

		try {
			startMailServer();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	// 执行 .bat 文件
	private static void startMailServer() throws InterruptedException {
		Runtime rt = Runtime.getRuntime(); // 运行时系统获取
		try {
			File filePath = new File("resource\\runStart.bat");
			Process proc = rt.exec(new String[] { "cmd", "/K", filePath.toString() });
			StreamManage errorStream = new StreamManage(proc.getErrorStream(), "Error");
			StreamManage outputStream = new StreamManage(proc.getInputStream(), "INFO");
			errorStream.start();
			outputStream.start();
			proc.waitFor();
			
			proc.destroy();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 在指定路径下执行命令，并将执行结果信息重定向到某一文件中
	public static String executeCMDfile(String[] cmds, String dirTodoCMD, String logToFile) throws IOException {
		try {
			ProcessBuilder builder = new ProcessBuilder(cmds);
			if (dirTodoCMD != null)
				builder.directory(new File(dirTodoCMD));
			builder.redirectErrorStream(true);
			builder.redirectOutput(new File(logToFile));
			Process p = builder.start();
			p.waitFor();

			InputStream is = p.getInputStream();
			BufferedReader buffer = new BufferedReader(new InputStreamReader(is));
			String line = null;
			StringBuffer sb = new StringBuffer();
			while ((line = buffer.readLine()) != null) {
				sb.append(line);
			}

			is.close();
			return sb.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
