package e_commerce.utils;

import java.util.Random;

public class OtpUtil {

    public static String generateOtp() {
        int otpLength = 6;

        Random rand = new Random();

        StringBuilder otp = new StringBuilder(otpLength);

        for (int i = 0; i < otpLength; i++) {
            otp.append(rand.nextInt(10));
        }
        return otp.toString();
    }
}
