package com.batch.example.service;

import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.batch.core.StepExecution;

public class BatchLogWriter {
    public void LogWriter (StepExecution stepExecution) {

		// 현 시스템일자를 구한다. 
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String sysDate = LocalDateTime.now().format(formatter);

		String logFile = "C:/Temp/" + stepExecution.getJobExecution().getJobInstance().getJobName()  + ".log";

		// 로그를 출력한다. 
		try {
		    FileOutputStream fos = new FileOutputStream(logFile, true);
		    PrintWriter pw = new PrintWriter(fos);

		    pw.printf("[%s] Read   건수[%05d]\n", sysDate, stepExecution.getReadCount());
		    pw.printf("[%s] Write  건수[%05d]\n", sysDate, stepExecution.getWriteCount());
		    pw.printf("[%s] Commit 건수[%05d]\n", sysDate, stepExecution.getCommitCount());
		    pw.printf("[%s] RSkip  건수[%05d]\n", sysDate, stepExecution.getReadSkipCount());
		    pw.printf("[%s] WSkip  건수[%05d]\n", sysDate, stepExecution.getWriteSkipCount());
		    pw.flush();	
		    pw.close();
			fos.close();
		} catch (Exception e) {
			e.getStackTrace();
		}
    }
}