package it.reactive.demoTorneoSpringBatch;

import it.reactive.demoTorneoSpringBatch.Utility.Costanti;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class RunnerJob implements CommandLineRunner {
    @Autowired
    JobLauncher jobLauncher;

    @Autowired
    BeanFactory beanFactory;

    @Override
    public void run(String... args) throws Exception {
        String nomeJob= Costanti.primoJob;
        System.out.println("nomeJob = " + nomeJob);
//        List<String> listaNomi=new ArrayList<>();
//        for (int i = 1; i < args.length; i++) {
//            listaNomi.add(args[i]);
//        }
        JobParameters jobParameters = new JobParametersBuilder()
                .addString("UUID", UUID.randomUUID().toString(),true)
                //.addJobParameter("nomi", listaNomi, List.class)
                .addString("nome", /*listaNomi.get(0)*/"NO_Name")

                .toJobParameters();
        Job job = (Job) beanFactory.getBean(nomeJob);
        jobLauncher.run(job, jobParameters);
    }
}
