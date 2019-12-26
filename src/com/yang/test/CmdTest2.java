package com.yang.test;

import java.io.*;

public class CmdTest2 {
	
	public static void main(String args[]) throws IOException, InterruptedException {
		f2();
	}

	public static void f2() throws IOException, InterruptedException {
		int rs = 0;
		String[] cmds = new String[3];
		cmds[0] = "cmd";
		cmds[1] = "/C";
		cmds[2] = "java -version";
		
		ProcessBuilder builder = new ProcessBuilder(cmds);
		builder.redirectErrorStream(true);
		Process process = builder.start();

		BufferedReader br;
		br = new BufferedReader(new InputStreamReader(process.getInputStream(), "gbk"));

		PrintWriter pw = null;
		FileOutputStream os = null;
		os = new FileOutputStream("D:\\a.txt");
		if (os != null)
			pw = new PrintWriter(os);

		String line = null;
		while (null != (line = br.readLine())) {
			pw.println(line);
			System.out.println(line);
		}
		
		if (pw != null)
			pw.flush();
		rs = process.waitFor();
		
		System.out.println(rs);
		pw.close();
	}

	public static void f() throws IOException, InterruptedException {
		FileOutputStream fos = new FileOutputStream("D:/a.txt");
		Runtime rt = Runtime.getRuntime();
		String[] cmds = new String[3];
		cmds[0] = "cmd";
		cmds[1] = "/C";
		//cmds[2] = "java -version";
		cmds[2] = "hexo s";
		
		Process proc = rt.exec(cmds);

		StreamGobbler outputGobbler = new StreamGobbler(proc.getInputStream(), "INFO", fos);
		StreamGobbler errorGobbler = new StreamGobbler(proc.getErrorStream(), "Error");

		outputGobbler.start();
		errorGobbler.start();

		
//		outputGobbler.join();
//		errorGobbler.join();
		int exitVal = proc.waitFor();
		System.out.println("ExitValue: " + exitVal);
		fos.flush();
		fos.close();
	}
}

class StreamGobbler extends Thread {
	InputStream is;
	String type;
	OutputStream os;

	StreamGobbler(InputStream is, String type) {
		this(is, type, null);
	}

	StreamGobbler(InputStream is, String type, OutputStream redirect) {
		this.is = is;
		this.type = type;
		this.os = redirect;
	}

	public void run() {
		try {
			PrintWriter pw = null;
			if (os != null)
				pw = new PrintWriter(os);
			InputStreamReader isr = new InputStreamReader(is, "gbk");
			BufferedReader br = new BufferedReader(isr);
			String line = null;
			while ((line = br.readLine()) != null) {
				if (pw != null)
					pw.println(line);
				System.out.println(type + ">" + line);
			}
			if (pw != null)
				pw.flush();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}
}