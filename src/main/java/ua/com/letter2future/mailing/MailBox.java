package ua.com.letter2future.mailing;

import org.springframework.data.repository.CrudRepository;

public interface MailBox extends CrudRepository<Letter, Long> {
}
