package com.hecj.file.deal;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import com.hecj.file.util.FileUtil;

/**
 * 筛选文件夹A中不在配置文件B中的文件集合
 */
public class DealFile1 {
	
	public DealFile1(){
		
	}

	public boolean deal(String configfile,String dir){
		try{
			String content =FileUtils.readFileToString(new File(configfile),"gbk");
			if(content.length() == 0){
				return false;
			}
			File[] fileList = new File(dir).listFiles();
			if(fileList.length == 0){
				System.out.println(dir+" 目录文件为空");
				return false;
			}
			System.out.println("===========筛选文件如下==============");
			StringBuffer sb = new StringBuffer();
			for(File f : fileList){
				String filename = f.getName();
				String ext = FileUtil.getExt(filename);
				if("shp".equals(ext.toLowerCase())){
					String filenametemp = filename.replace(ext, "");
					if(!content.contains(filenametemp)){
						System.out.println(filename);
						sb.append(filename+"\r\n");
					}
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
