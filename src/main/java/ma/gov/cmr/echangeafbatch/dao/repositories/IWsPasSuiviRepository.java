package ma.gov.cmr.echangeafbatch.dao.repositories;

import ma.gov.cmr.echangeafbatch.dao.models.WsPasSuiviEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IWsPasSuiviRepository extends JpaRepository<WsPasSuiviEntity, Long> {

    /*List<WsPasSuiviEntity> findAll(Sort sort);*/
}

/*public interface IWsPasSuiviRepository extends PagingAndSortingRepository<WsPasSuiviEntity, Long> {
}*/
