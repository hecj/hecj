package test.com.hecj.common;

import com.hecj.common.util.encryp.Encrypt;

public class DesTest {

	public static void main(String[] args) {
		Encrypt encrypt = new Encrypt();
		String data = "275070";
		String firstKey = "hechaojie";
		String str = encrypt.strEncode(data, firstKey, "", "");
		System.out.println(str);
		
		String data2 = "199578";
		String str2 = encrypt.strEncode(data2, firstKey, "", "");
		System.out.println(str2);
		
		System.out.println(encrypt.strDecode("831B078621FBCA25F1C2E73270094696", firstKey, "", ""));
	}

}
