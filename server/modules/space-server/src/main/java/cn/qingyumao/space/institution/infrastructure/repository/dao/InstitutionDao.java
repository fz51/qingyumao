package cn.qingyumao.space.institution.infrastructure.repository.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InstitutionDao extends JpaRepository<InstitutionDO, Long> {

    Optional<InstitutionDO> findByCodeEquals(String code);

    Page<InstitutionDO> findAll(Specification<InstitutionDO> specification, Pageable pageable);

}
