package com.sarfaraz.knoldus
import model.Student
import repository.StudentRepository

class StudentServiceImpl extends StudentService {
  val studRepo = new StudentRepository

  override def getStudents: List[Student] = studRepo.getStudents

  override def getStudent(id: Int): Student = studRepo.getStudent(id)

  override def createStudent(s: Student): Student = {
    studRepo.create(s)
    s
  }

  override def updateStudent(s: Student): Student = {
    if((studRepo.getStudent(s.id)).id == 0) {
      println(s"${s.id} doesn't exist..so creating it...")
      studRepo.create(s)
    }
    else
      studRepo.update(s)
    s
  }

  override def deleteStudent(sId: Int): Boolean = {
    if((studRepo.getStudent(sId)).id != 0) {
      studRepo.delete(sId)
      true
    }
    else
      false
  }
}
