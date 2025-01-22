package it.reactive.demoTorneoSpringBatch.tasklet;

import it.reactive.demoTorneoSpringBatch.Utility.Costanti;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Configuration
public class Tasklet {

    @Bean(Costanti.taskletDelete)
    public org.springframework.batch.core.step.tasklet.Tasklet tasklet(@Qualifier(Costanti.dataSourceTorneo) DataSource dataSource) {
        return new org.springframework.batch.core.step.tasklet.Tasklet() {
            @Override
            public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
                System.out.println("Primo Step");
                Connection con = dataSource.getConnection();
                try {
                    PreparedStatement ps = dataSource.getConnection().prepareStatement("truncate table squadra cascade");
                    ps.executeUpdate();
                    ps = con.prepareStatement("truncate table giocatore cascade");
                    ps.executeUpdate();
                    ps = con.prepareStatement("truncate table tifoseria cascade");
                    ps.executeUpdate();
                    ps = con.prepareStatement("truncate table squadra_torneo cascade");
                    ps.executeUpdate();
                    ps = con.prepareStatement("truncate table torneo cascade");
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
