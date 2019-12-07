package com.batch.example.dto;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.apache.ibatis.session.SqlSession;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class EmpInfoDao {
	
	 Logger logger = LogManager.getLogger("com.batch.example");
	// private static final Logger logger = Logger.getLogger(EmpInfoDao.class); 

	 //@Resource(name="sqlSession")
     private SqlSession sqlSession;

	 public int selectEmpList() {

		String conf = "config/context.xml";
		String fileName = "C:/Temp/FilePrint.txt";
		PrintWriter pw = null;

		ApplicationContext context = new ClassPathXmlApplicationContext(conf);
		sqlSession = (SqlSession) context.getBean("sqlSession");

		try {
			pw = new PrintWriter(new FileWriter(fileName));
		
			List<EmpInfoDto> empList = sqlSession.selectList("EmpInfo.selectEmpList", "0003");
			for (EmpInfoDto emp : empList) {
//				pw.printf(  "%5s", getRPad(emp.getEmpNo(),5));
//				pw.printf("%-20s", getRPad(emp.getEmpNm(),20));
//				pw.printf(  "%4s", getRPad(emp.getDeptCd(),4));
//				pw.printf("%-20s", getRPad(emp.getDeptNm(), 20));
//				pw.printf(  "%1s", getRPad(emp.getGender(), 1));
//				pw.printf( "%-8s", getRPad(emp.getRetireDt(), 8));
//				pw.printf("%-20s", getRPad(emp.getRegiUserId(), 20));
//				pw.printf("%-16s", getRPad(emp.getRegiDttm(), 16));
//				pw.printf("%-20s", getRPad(emp.getLastModUserId(), 20));
//				pw.printf("%-16s", getRPad(emp.getLastModDttm(), 16));

				pw.printf("%s", getRPad(emp.getEmpNo(),5));
				pw.printf("%s", getRPad(emp.getEmpNm(),20));
				pw.printf("%s", getRPad(emp.getDeptCd(),4));
				pw.printf("%s", getRPad(emp.getDeptNm(), 20));
				pw.printf("%s", getRPad(emp.getGender(), 1));
				pw.printf("%s", getRPad(emp.getRetireDt(), 8));
				pw.printf("%s", getRPad(emp.getRegiUserId(), 20));
				pw.printf("%s", getRPad(emp.getRegiDttm(), 16));
				pw.printf("%s", getRPad(emp.getLastModUserId(), 20));
				pw.printf("%s", getRPad(emp.getLastModDttm(), 16));
				pw.println();
			}

		} catch(Throwable e) {
			e.printStackTrace();
			return -1;
		} finally {
			pw.close();
		}

		return 0;
	}
	 
	 /**
	     * 오른쪽으로 자리수만큼 문자 채우기
	     *
	     * @param   str         원본 문자열
	     * @param   size        총 문자열 사이즈(리턴받을 결과의 문자열 크기)
	     * @param   strFillText 원본 문자열 외에 남는 사이즈만큼을 채울 문자
	     * @return 
	     */
	public String getRPad(String str, int size)  {

		String str2 = null;
		
		try {
		    str2 = new String(str.getBytes("EUC-KR"),"EUC-KR");
		} catch(Throwable e) {
			e.printStackTrace();
			return null;
		} 

		logger.info("result Bef =[" + str2.getBytes().length + "][" + str2 + "]");

        for(int i = (str2.getBytes()).length; i < size; i++) {
	    	str2 += ' ';
	    }

	    return str2;
	}
}