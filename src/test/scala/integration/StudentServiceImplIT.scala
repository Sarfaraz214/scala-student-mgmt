import com.sarfaraz.knoldus.StudentServiceImpl
import model.Student
import org.junit
import org.junit.{After, Before}
import org.scalatest.Tag
import org.scalatest.junit.{AssertionsForJUnit, JUnitSuiteLike}

class StudentServiceImplIT extends Tag("Integration-Test") with AssertionsForJUnit with JUnitSuiteLike  {

  private val id = 1001
  private val sName = "Raghu"
  private val age = 20
  object student extends StudentServiceImpl

  @After
  def after(): Unit = {
    student.deleteStudent(id)
    student.deleteStudent(id+1)
  }

  @Before
  def before(): Unit = {
    student.createStudent(Student(id, sName, age))
  }

  @junit.Test
  def getStudents(): Unit = {
    val students = student.getStudents
    assert(students.nonEmpty, "Student should exist.")
  }

  @junit.Test
  def getStudent(): Unit = {
    val stud = student.getStudent(id)
    assert(stud.id != 0, "Student ID should be greater then 0.")
  }

  @junit.Test
  def createStudent(): Unit = {
    val stud = student.createStudent(Student(id + 1, sName, age))
    assert(stud.id != 0, "Student ID should be greater then 0.")
  }

  @junit.Test
  def updateStudent(): Unit = {
    val stud = student.updateStudent(Student(id, "Testing1", age))
    println(stud.name)
    assert(stud.id != 0, "Student ID should be greater then 0.")
  }

  @junit.Test
  def deleteStudent(): Unit = {
    val stud = student.deleteStudent(id)
    assert(stud == true, "Student should be deleted.")
  }
}
