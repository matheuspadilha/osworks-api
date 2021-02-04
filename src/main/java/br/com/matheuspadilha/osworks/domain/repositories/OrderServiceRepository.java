package br.com.matheuspadilha.osworks.domain.repositories;

import br.com.matheuspadilha.osworks.domain.models.OrderService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderServiceRepository extends JpaRepository<OrderService, Long> {
}
