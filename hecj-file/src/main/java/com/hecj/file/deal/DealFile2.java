package com.hecj.file.deal;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;

import com.hecj.file.util.FileUtil;

/**
 * 筛选配置文件B中不在文件夹A中的文件集合
 */
public class DealFile2 {
	
	public DealFile2(){
		
	}
	
	public static boolean deal(String configfile,String dir){
		try{
			String content =FileUtils.readFileToString(new File(configfile),"gbk");
			if(content.length() == 0){
				System.out.println("【ERROR】配置文件内容为空");
				return false;
			}
			
			List<String> configFileList = new ArrayList<String>();
			// 正则出配置文件中的.shp文件集合 <FileName>县界.shp</FileName>
			Pattern pattern = Pattern.compile("<FileName>.+[\\.s|Sh|Hp|P]</FileName>");
			Matcher matcher = pattern.matcher(content);
			while(matcher.find()){
				String filename = matcher.group();
				filename = filename.replace("<FileName>", "").replace("</FileName>", "");
				configFileList.add(filename);
			}
			
			File[] fileList = new File(dir).listFiles();
			if(fileList.length == 0){
				System.out.println(dir+" 目录文件为空");
				return false;
			}
			System.out.println("===========筛选文件如下==============");
			String fileStringContent = "";
			for(File f : fileList){
				String filename = f.getName();
				String ext = FileUtil.getExt(filename);
				if("shp".equals(ext.toLowerCase())){
					fileStringContent += filename+"  ";
				}
			}
			
			// 匹配没有的文件
			List<String> resultFile = new ArrayList<String>();
			StringBuffer sb = new StringBuffer();
			for(String sf : configFileList){
				String sfstart = sf.split("\\.")[0];
				if(!fileStringContent.contains(sfstart+".")){
					System.out.println(sf);
					resultFile.add(sf);
					sb.append(sf+"\r\n");
				}
			}
			
			System.out.println("==============================");
			String fname = UUID.randomUUID().toString().substring(0, 13)+".txt";
			System.out.println("已写入文件:"+fname);
			File ft = new File(configfile);
			FileUtils.writeStringToFile(new File(ft.getParent()+"/"+fname), sb.toString(), "gbk");
			System.out.println();
			return true;
		} catch(FileNotFoundException ex){
			System.out.println();
			System.out.println("文件格式错误");
		} catch(Exception ex){
			ex.printStackTrace();
		}
		return false;
	}
}
