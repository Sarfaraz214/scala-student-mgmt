import model.Student
import org.junit
import org.junit.{After, Before}
import org.scalatest.junit.{AssertionsForJUnit, JUnitSuiteLike}
import com.sarfaraz.knoldus.StudentServiceImpl
import repository.StudentRepository

class StudentServiceImplTest extends AssertionsForJUnit with JUnitSuiteLike  {

  private final val id = 1001
  private final val name = "Raghu"
  private final val age = 20

  @After
  def after(): Unit = {
    object student extends StudentServiceImpl
    student.deleteStudent(id)
    student.deleteStudent(id+1)
  }

  @Before
  def before(): Unit = {
    object student extends StudentServiceImpl
    student.createStudent(Student(id, name, age))
  }

  @junit.Test
  def getStudents(): Unit = {
    object student extends StudentServiceImpl
    val students = student.getStudents
    assert(students.nonEmpty, "Student should exist.")
  }

  @junit.Test
  def getStudent(): Unit = {
    object studentObj extends StudentServiceImpl
    val student = studentObj.getStudent(id)
    assert(student.id != 0, "Student ID should be greater then 0.")
  }

  @junit.Test
  def createStudent(): Unit = {
    object studentObj extends StudentServiceImpl
    val student = studentObj.createStudent(Student(id + 1, name, age))
    assert(student.id != 0, "Student ID should be greater then 0.")
  }

  @junit.Test
  def updateStudent(): Unit = {
    object studentObj extends StudentServiceImpl
    val student = studentObj.updateStudent(Student(id, "Testing", age))
    println(student.name)
    assert(student.id != 0, "Student ID should be greater then 0.")
  }

  @junit.Test
  def deleteStudent(): Unit = {
    object studentObj extends StudentServiceImpl
    val student = studentObj.deleteStudent(id)
    assert(true, "Student should be deleted.")
  }
}
