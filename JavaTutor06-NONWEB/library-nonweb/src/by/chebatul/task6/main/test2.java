package by.chebatul.task6.main;

import java.util.List;

import by.chebatul.task6.dto.BookDto;
import by.chebatul.task6.entity.BookType;
import by.chebatul.task6.exception.ServiceException;
import by.chebatul.task6.service.mail.MailService;

public class test2 {

	public static void main(String[] args) {

		MailService service = MailService.getInstance();

		try {
			service.sendEmail(List.of("javamailtester@mail.ru"),
					List.of(BookDto.builder().name("test").author("testtt").type(BookType.ELECTRONIC).build()));
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
