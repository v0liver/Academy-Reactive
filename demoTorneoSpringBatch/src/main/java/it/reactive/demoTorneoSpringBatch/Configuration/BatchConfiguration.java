package it.reactive.demoTorneoSpringBatch.Configuration;


import it.reactive.demoTorneoSpringBatch.Utility.Costanti;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;
import javax.lang.model.element.Name;
import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Configuration
public class BatchConfiguration {


    @Bean(Costanti.primoJob)
    public Job Job(JobRepository jobRepository,
                   @Qualifier(Costanti.primoStep) Step primoStep){

        return new JobBuilder(Costanti.primoJob,jobRepository)
                .start(primoStep)
                .build();


    }


    @Bean(Costanti.primoStep)
    public Step primoStep(JobRepository jobRepository, PlatformTransactionManager transactionManager,@Qualifier("tasklet_delete")Tasklet tasklet){
        return new StepBuilder(Costanti.primoStep,jobRepository)
                .tasklet(tasklet,transactionManager)
                .build();

    }

    @Bean(name = "tasklet_delete")
    public Tasklet tasklet(@Qualifier(Costanti.dataSourceTorneo) DataSource dataSource){
       return new Tasklet() {
           @Override
           public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
               System.out.println("Primo Step");
               try {
                   PreparedStatement ps = dataSource.getConnection().prepareStatement("delete from squadra cascade");
                   ps.executeUpdate();
                   ps = dataSource.getConnection().prepareStatement("delete from giocatore cascade");
                   ps.executeUpdate();
                   ps = dataSource.getConnection().prepareStatement("delete from tifoseria cascade");
                   ps.executeUpdate();
                   ps = dataSource.getConnection().prepareStatement("delete from squadra_torneo cascade");
                   ps.executeUpdate();
                   ps = dataSource.getConnection().prepareStatement("delete from torneo cascade");
                   ps.executeUpdate();
               } catch (SQLException e) {
                   throw new RuntimeException(e);
               }
               //GiocatoreModel giocatoreModel = giocatoreJpaRepository.findById(1).orElseThrow(()->new GiocatoreNonPresenteException());
//               giocatoreJpaRepository.delete(giocatoreModel);
               return RepeatStatus.FINISHED;
           }
       };
    }
}
