package com.sarfaraz.knoldus
import model.Student
import repository.StudentRepository

class StudentServiceImpl extends StudentService {


  override def getStudents: List[Student] = StudentRepository.getStudents

  override def getStudent(id: Int): Student = StudentRepository.getStudent(id)

  override def createStudent(s: Student): Student = {
    StudentRepository.create(s)
    s
  }

  override def updateStudent(s: Student): Student = {
    if((StudentRepository.getStudent(s.id)).id == 0) {
      println(s"${s.id} doesn't exist..so creating it...")
      StudentRepository.create(s)
    }
    else
      StudentRepository.update(s)
    s
  }

  override def deleteStudent(sId: Int): Boolean = {
    if((StudentRepository.getStudent(sId)).id != 0) {
      StudentRepository.delete(sId)
      true
    }
    else
      false
  }
}
