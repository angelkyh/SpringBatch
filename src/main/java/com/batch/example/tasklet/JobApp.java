package com.batch.example.tasklet;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class JobApp {
	Logger logger = LogManager.getLogger("org.springframework"); 

	public static void main(String[] args) {

		JobApp obj = new JobApp();
		obj.run();
	}

	private void run() {
	
		String springConfig =  "jobs/file_write_job.xml";

		ApplicationContext context = new ClassPathXmlApplicationContext(springConfig);
		JobLauncher jobLauncher = (JobLauncher) context.getBean("jobLauncher");
		Job job = (Job) context.getBean("EmpInfoFileCreateJob");

		try {
			JobParameters param = new JobParametersBuilder().addLong("time",System.currentTimeMillis()).toJobParameters();
			JobExecution execution = jobLauncher.run(job, param);
			logger.info("Exit Status : " + execution.getStatus());
		} catch (Exception e) {
			e.printStackTrace();
		}

		logger.info("프로그램이 실행성공!!!");
	}
}
