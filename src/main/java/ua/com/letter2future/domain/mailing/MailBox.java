package ua.com.letter2future.domain.mailing;

import org.springframework.data.repository.CrudRepository;

public interface MailBox extends CrudRepository<Letter, Long> {
}
