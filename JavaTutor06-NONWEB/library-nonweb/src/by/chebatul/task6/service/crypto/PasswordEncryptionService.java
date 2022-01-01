package by.chebatul.task6.service.crypto;

import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Base64;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PasswordEncryptionService {

	private static final PasswordEncryptionService INSTANCE = new PasswordEncryptionService();

	public boolean authenticate(String attemptedPassword, String encryptedPassword, byte[] salt) {
		return encryptedPassword.equals(getEncriptedPassword(attemptedPassword, salt));
	}

	public String getPassword(String password) {
		byte[] salt = generateSalt();
		String encryptedPassword = getEncriptedPassword(password, salt);
		return encryptedPassword + Base64.getEncoder().encodeToString(salt);
	}

	@SneakyThrows
	private String getEncriptedPassword(String password, byte[] salt) {
		MessageDigest md = MessageDigest.getInstance("SHA-1");
		md.update(salt);
		byte[] bytes = md.digest(password.getBytes());
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < bytes.length; i++) {
			sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
		}
		return sb.toString();

	}

	@SneakyThrows
	private byte[] generateSalt() {
		SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
		byte[] salt = new byte[8];
		random.nextBytes(salt);
		return salt;

	}

	public static PasswordEncryptionService getInstance() {
		return INSTANCE;
	}

}
