package br.com.larissafalcao.fullstackweek.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.larissafalcao.fullstackweek.domain.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Long>{

}
