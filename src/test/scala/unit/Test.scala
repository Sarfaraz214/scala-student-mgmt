import model.Student
import org.junit
import org.junit.{After, Before}
import org.scalatest.junit.{AssertionsForJUnit, JUnitSuiteLike}
import repository.StudentRepository

class Test extends AssertionsForJUnit with JUnitSuiteLike {
  private final val id = 1001
  private final val name = "Raghu"
  private final val age = 20

  @After
  def after(): Unit = {
    object studentRepo extends StudentRepository
    studentRepo.delete(id)
  }

  @Before
  def before(): Unit = {
    object studentRepo extends StudentRepository
    studentRepo.create(Student(id, name, age))
  }

  @junit.Test
  def getStudent(): Unit = {
    object studentRepo extends StudentRepository
    val student = studentRepo.getStudent(id)
    assert(student.id != 0, "Student ID should be greater then 0.")
  }

  @junit.Test
  def getStudents(): Unit = {
    object studentRepo extends StudentRepository
    val students = studentRepo.getStudents
    assert(students.nonEmpty, "Student should exist.")
  }
}
