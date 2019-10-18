import org.scalatest.{Matchers, WordSpecLike}
import org.scalatest.mockito.MockitoSugar
import org.mockito.Mockito.when
import repository.StudentRepository
import com.sarfaraz.knoldus.StudentServiceImpl
import model.Student

class StudentServiceImplTest extends MockitoSugar with WordSpecLike with Matchers {
  private val studentRepo = mock[StudentRepository]
  private val student = new StudentServiceImpl{
    override val studRepo = studentRepo
  }
  private val id = 1001
  private val name = "Raghu"
  private val age = 20

  "StudentServiceImpl" should {
    "should return student" in {
      when(studentRepo.getStudents) thenReturn(List(Student(id, name, age)))
      val students = student.getStudents
      assert(students.nonEmpty, "Student should exist.")
      assert(students.size == 1, "One Student record should exist.")
    }
  }
}
