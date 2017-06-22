package test.com.hecj.common;
import com.hecj.common.util.encryp.Encrypt;

public class DesTest {

	public static void main(String[] args) {
		String data = "4201DE1EADCD91A5D3D64E2E05A75C07";
		String firstKey = "hechaojie";
		String str2 = new Encrypt().strDecode(data, firstKey, "", "");
		System.out.println(new Encrypt().strDecode(data, firstKey, "", ""));
	
	}

}
