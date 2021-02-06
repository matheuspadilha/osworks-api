package br.com.matheuspadilha.osworks.domain.repositories;

import br.com.matheuspadilha.osworks.domain.models.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRespository extends JpaRepository<Comment, Long> {

}
