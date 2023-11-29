package ma.gov.cmr.echangeafbatch;

import com.fasterxml.jackson.databind.util.JSONPObject;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import ma.gov.cmr.echangeafbatch.batchElements.EchangeafItemProcessor;
import ma.gov.cmr.echangeafbatch.batchElements.EchangeafItemWriter;
import ma.gov.cmr.echangeafbatch.dao.models.WsPasSuiviEntity;
import ma.gov.cmr.echangeafbatch.dao.repositories.IWsPasSuiviRepository;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.data.RepositoryItemReader;
import org.springframework.batch.item.data.builder.RepositoryItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Sort;

import java.util.Collections;

@Configuration
@EnableBatchProcessing
@RequiredArgsConstructor
@AllArgsConstructor
public class EchangeafBatchConfiguration {

    private JobBuilderFactory jobBuilderFactory;
    private StepBuilderFactory stepBuilderFactory;
    private IWsPasSuiviRepository wsPasSuiviRepository;

    @Bean
    public RepositoryItemReader<WsPasSuiviEntity> jpaItemReader(IWsPasSuiviRepository wsPasSuiviRepository) {
        return new RepositoryItemReaderBuilder<WsPasSuiviEntity>()
                .name("jpaItemReader")
                .repository(wsPasSuiviRepository)
                .methodName("findAll")
                .sorts(Collections.singletonMap("id", Sort.Direction.ASC))
                .pageSize(10)
                .build();
    }

    @Bean
    public EchangeafItemProcessor processor() {
        return new EchangeafItemProcessor();
    }

    @Bean
    public EchangeafItemWriter writer() {
        return new EchangeafItemWriter();
    }

    @Bean
    @JobScope
    public Step step1(RepositoryItemReader<WsPasSuiviEntity> jpaItemReader,
                      ItemProcessor<WsPasSuiviEntity, JSONPObject> processor,
                      ItemWriter<JSONPObject> writer) {
        return stepBuilderFactory.get("step1")
                .<WsPasSuiviEntity, JSONPObject>chunk(1000)
                .reader(jpaItemReader)
                .processor(processor)
                .writer(writer)
                .build();
    }

    @Bean
    public Job exportDataJob(Step step1) {
        return jobBuilderFactory.get("exportDataJob")
                .incrementer(new RunIdIncrementer())
                .flow(step1)
                .end()
                .build();
    }
}
