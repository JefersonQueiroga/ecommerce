package br.ifrn.edu.jeferson.ecommerce.repository;

import br.ifrn.edu.jeferson.ecommerce.domain.Endereco;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
}
