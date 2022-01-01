package by.chebatul.task6.service.mail;

import java.util.List;
import java.util.Properties;
import javax.mail.Address;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;
import by.chebatul.task6.dto.BookDto;
import by.chebatul.task6.exception.ServiceException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MailService {

	private static final MailService INSTANCE = new MailService();
	private static final String MAIL_PROPERTIES_NAME = "mail.properties";
	private static final String EMAIL_NAME = "mail.user";
	private static final String PASSWORD_NAME = "mail.password";
	private static final String SUBJECT = "Новая книга в нашей библиотеке!";
	private static final String TEXT = """
			Доброго дня.

			Представляем вашему вниманию список новых книг в нашей библиотеке:

			%s
			""";

	public void sendEmail(List<String> emailTo, List<BookDto> books) throws ServiceException {
		try {
			Properties properties = new Properties();
			properties.load(MailService.class.getClassLoader().getResourceAsStream(MAIL_PROPERTIES_NAME));
			Address[] addresses = emailTo.stream().map(MailService::getAddress).toArray(Address[]::new);
			StringBuffer sb = new StringBuffer();
			books.stream()
					.forEach(book -> sb.append(book.getAuthor() + " - " + book.getName() + System.lineSeparator()));
			String textMessage = TEXT.formatted(sb.toString());

			Session session = Session.getDefaultInstance(properties);
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(properties.getProperty(EMAIL_NAME)));
			message.setRecipients(RecipientType.TO, addresses);
			message.setSubject(SUBJECT);
			message.setText(textMessage);

			Transport te = session.getTransport();
			te.connect("javamailtester@mail.ru", properties.getProperty(PASSWORD_NAME));
			te.sendMessage(message, message.getAllRecipients());
			te.close();
		} catch (Exception e) {
			throw new ServiceException(e);
		}

	}

	@SneakyThrows
	public static InternetAddress getAddress(String email) {
		return new InternetAddress(email);
	}

	public static MailService getInstance() {
		return INSTANCE;
	}

}
