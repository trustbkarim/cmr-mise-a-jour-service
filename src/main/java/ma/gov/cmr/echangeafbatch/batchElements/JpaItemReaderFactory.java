package ma.gov.cmr.echangeafbatch.batchElements;

import ma.gov.cmr.echangeafbatch.dao.models.WsPasSuiviEntity;
import ma.gov.cmr.echangeafbatch.dao.repositories.IWsPasSuiviRepository;
import org.springframework.batch.item.data.RepositoryItemReader;
import org.springframework.batch.item.data.builder.RepositoryItemReaderBuilder;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class JpaItemReaderFactory {

    private final IWsPasSuiviRepository wsPasSuiviRepository;

    public JpaItemReaderFactory(IWsPasSuiviRepository wsPasSuiviRepository) {
        this.wsPasSuiviRepository = wsPasSuiviRepository;
    }

    public RepositoryItemReader<WsPasSuiviEntity> createReader() {
        return new RepositoryItemReaderBuilder<WsPasSuiviEntity>()
                .name("jpaItemReader")
                .pageSize(100)
                .methodName("findAll")
                .sorts(Collections.singletonMap("id", Sort.Direction.ASC))
                .repository(wsPasSuiviRepository)
                .build();
    }
}
