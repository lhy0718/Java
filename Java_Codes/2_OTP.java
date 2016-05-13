import javax.swing.JOptionPane;

public class OTP {

	public static void main(String[] args) {
		int otp = 0, count = 0, temp;
		String zeros = "";
		double d = Math.random();
		otp = (int) (d * 1000000);
		temp = otp;
		while(temp > 0){
			temp /= 10;
			count++;
		}
		for(int i = 0; i < 6 - count; i++)
			zeros += "0";
		JOptionPane.showMessageDialog(null, "OTP = " + zeros + otp);
	}
}
