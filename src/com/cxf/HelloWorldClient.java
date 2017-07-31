/**
 * @created time:2017年7月27日,下午3:25:51
 * @author:chixh
 * @file:HelloClient.java
 */
package com.cxf;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.demo.HelloWorld;
import com.demo.User;

public class HelloWorldClient {
	public static void main(String[] args) {
		// 在应用配置文件中解析client字段。
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

		HelloWorld client = (HelloWorld) context.getBean("client");
		// 客户端产生两个user数据
		User user1 = new User();
		user1.setName("Tony");
		user1.setDescription("test");

		User user2 = new User();
		user2.setName("freeman");
		user2.setDescription("test");

		List<User> userList = new ArrayList<User>();
		userList.add(user1);
		userList.add(user2);
		// 将数据通过bean的方式应用到服务端中去
		List<String> res = client.sayHiToUserList(userList);
		System.out.println(res.get(0));
		System.out.println(res.get(1));

		String fileName = "client.txt";
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(fileName);
			System.out.println(fis.available());
			byte[] bytes = new byte[fis.available()];
			fis.read(bytes);
			System.out.println(client.uploadFile(fileName, bytes));
			System.out.println(fileName);
		} catch (Exception e) {
			Logger.getRootLogger().error(e.getMessage());
		} finally {
			try {
				if (fis != null)
					fis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		System.out.println(new String(client.fieldToJson("json1")));

		FileOutputStream fos = null;
		fileName = "已上传.txt";
		byte[] bytes = null;
		try {
			bytes = client.downloadFile(fileName);
			fos = new FileOutputStream("已下载.txt");
			fos.write(bytes);
			System.out.println(new String(bytes));
		} catch (Exception e) {
			Logger.getRootLogger().info(e.getMessage());
			e.printStackTrace();
		} finally {
			if (fos != null)
				try {
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}

	}
}