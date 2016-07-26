package com.hecj.file.server;

import java.util.Scanner;

import com.hecj.file.deal.DealFile1;
import com.hecj.file.deal.DealFile2;


public class Server {

	public static void main(String[] args) {
		
		System.out.println("============功能菜单=========== ");
		System.out.println("     1.筛选文件夹A中不在配置文件B中的文件集合 ");
		System.out.println("     2.筛选配置文件B中不在文件夹A中的文件集合 ");
		System.out.println("============功能菜单=========== ");
		Scanner scanner = new Scanner(System.in);
		while(true){
			System.out.print("请选择功能编号（否则输入Y退出）：");
			String num = scanner.next();
			if("1".equals(num)){
				System.out.print("请输入配置文件B路径 : ");
				String configfile = scanner.next();
				System.out.print("请输入文件夹A路径 : ");
				String dir = scanner.next();
				DealFile1 deal = new DealFile1();
				deal.deal(configfile, dir);
			}else if("2".equals(num)){
				System.out.print("请输入配置文件B路径 : ");
				String configfile = scanner.next();
				System.out.print("请输入文件夹A路径 : ");
				String dir = scanner.next();
				DealFile2 deal2 = new DealFile2();
				deal2.deal(configfile, dir);
			}else if("Y".equalsIgnoreCase(num)){
				System.out.println("【程序退出】");
				break;
			} else{
				System.out.println("【ERROR】编号错误");
			}
		}
	}
}
