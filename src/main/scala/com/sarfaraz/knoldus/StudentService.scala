package com.sarfaraz.knoldus

import model.Student

trait StudentService {
  def getStudents: List[Student]

  def getStudent(id: Int): Student

  def createStudent(s: Student): Student

  def updateStudent(s: Student): Student

  def deleteStudent(id: Int): Boolean
}
