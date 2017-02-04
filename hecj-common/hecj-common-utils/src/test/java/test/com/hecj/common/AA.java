package test.com.hecj.common;

import com.hecj.common.util.encryp.Encrypt;

public class AA {

		public static void main(String[] args) {
			String s = new Encrypt().strDecode("BC69439F12278F4C", "hechaojie","","");
			System.out.println(s);
		}
}
