/**
 * @created time:2017年7月27日,下午4:11:22
 * @author:chixh
 * @file:MSDLHandler.java
 */
package com.cxf.wsdl;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.ExecuteException;
import org.apache.commons.exec.ExecuteStreamHandler;
import org.apache.commons.exec.ExecuteWatchdog;
import org.apache.commons.exec.Executor;
import org.apache.commons.exec.PumpStreamHandler;
import org.apache.log4j.Logger;

public class WSDLHandler {
	/**
	 * 根据url地址里的WSDL,生成java源代码和class文件
	 * 
	 * @param url
	 *            = http://localhost:8080/helloCXF/webservice/HelloWorld?wsdl
	 * @param interfaceName
	 */
	public static boolean exec(String bat, String interfaceName) {
		if (new File(bat).exists()) {
			CommandLine cmd = CommandLine.parse(bat);
			Executor executor = new DefaultExecutor();
			executor.setExitValue(1);
			ByteArrayOutputStream outStream = new ByteArrayOutputStream();
			ByteArrayOutputStream errStream = new ByteArrayOutputStream();
			ExecuteStreamHandler stream = new PumpStreamHandler(outStream, errStream);
			executor.setStreamHandler(stream);
			executor.setWatchdog(new ExecuteWatchdog(10));

			try {
				int ex = executor.execute(cmd);

				// 拿到执行程序中输出内容
				String out = outStream.toString("gbk");
				outStream.close();
				// 拿到执行程序中执行异常内容
				String err = errStream.toString("gbk");
				errStream.close();
				Logger.getRootLogger()
						.info(String.format("Method[exec]执行命令行文件名称：%s，执行输出：%s，执行异常信息：%s，执行结果：%s", bat, out, err, ex));

			} catch (ExecuteException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return true;
		} else {
			return false;
		}
		// System.out.println("Success! The java code is in directory
		// D:\\"+interfaceName);
	}

	public static void main(String[] args) {
		String url = "D:\\spring\\workspace\\ClientHelloCXF\\WSDL生成jar的批处理.bat";
		String interfaceName = "helloInterface";
		exec(url, interfaceName);
	}
}
