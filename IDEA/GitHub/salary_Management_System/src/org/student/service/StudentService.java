package org.student.service;

import org.student.dao.StudentDao;

//�߼��Ե���ɾ�Ĳ�
//������+��  ��Dao����е���װ
public class StudentService {
	StudentDao studentDao = new StudentDao();

	public boolean deleteBysno(String sno) {
		return studentDao.deleteStudent(sno);
	}
	
	public boolean deleteStudentrecord(String sno) {
		return studentDao.deleteStudentrecord(sno);
	}

	public boolean deleteStudentnumber(String sno) {
		return studentDao.deleteStudentnumber(sno);
	}
	
	public boolean DeleteStudentmessage(String sno) {
		return studentDao.DeleteStudentmessage(sno);
	}
	
}
