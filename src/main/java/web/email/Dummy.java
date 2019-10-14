package web.email;

public class Dummy {

	public static void main(String[] args) {
		String var= "94hojun@gmail.com";
		String code = new AES256E().encrypt(var, "hi");
		System.out.println(code);
		
		String decode = new AES256D().decrypt(code, "hi");
		System.out.println(decode);
	}

}
