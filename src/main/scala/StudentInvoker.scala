import model.Student
import com.sarfaraz.knoldus.StudentServiceImpl

object StudentInvoker {
  def main(args: Array[String]): Unit = {
    val s1 = Student(11, "Sarfaraz", 26)
    val s2 = Student(22, "Divyansh", 23)
    val s3 = Student(22, "Rahul", 24)

    val student = new StudentServiceImpl

    println("Adding students to DB...")     //TODO: Check for duplicate Primary key value
    println((student.createStudent(s1)).toString)
    println((student.createStudent(s2)).toString)
    println((student.createStudent(s3)).toString)

    println("\nRetrieving info of all students...")
    val allStudents = student.getStudents
    for(s <- allStudents) {
      println(s.toString)
    }

    val id_find = 11

    println(s"\nRetrieving student with ID: ${id_find}...")
    val foundStud: Student = student.getStudent(id_find)
    if(foundStud.id != 0) println(foundStud.toString) else println("No such student found!!!")

    val id_delete = 22

    println(s"\nDeleting student with ID: ${id_delete}...")
    val delStud = student.deleteStudent(id_delete)
    if(delStud) println("Deleted sucessfully!!!") else println("No such record found!!!")

    val stud = Student(11, "Sarfaraz Hussain", 26)
    println(s"\nUpdating student with ID: ${stud.id}...")
    println(student.updateStudent(stud).toString)
  }
}
