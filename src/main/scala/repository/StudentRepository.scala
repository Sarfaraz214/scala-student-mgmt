package repository

import java.sql.{Connection, DriverManager}

import model.Student

import scala.collection.mutable

class StudentRepository {
  private final val url = "jdbc:mysql://localhost:3306/demodb?useSSL=false"
  private final val driver = "com.mysql.jdbc.Driver"
  private final val username = "sarfu"
  private final val password = "P@ssword1"
  private final val conn: Connection = {
    var con: Connection = null
    try {
      Class.forName(driver)
      con = DriverManager.getConnection(url, username, password)
      con.setAutoCommit(false)
    } catch {
      case e: Exception => e.printStackTrace()
    }
    con
  }

  def getStudents: List[Student] = {
    val students: mutable.ListBuffer[Student] = mutable.ListBuffer[Student]()
    val query = "select * from student"
    try {
      val stmt = conn.createStatement
      val rs = stmt.executeQuery(query)
      while (rs.next) {
        val student = Student(rs.getInt(1), rs.getString(2), rs.getInt(3))
        students += student
      }
    } catch {
      case e: Exception => e.printStackTrace()
    }
    students.toList
  }

  def getStudent(id: Int): Student = {
    val query = "select * from student where id = " + id
    var student = Student(0,"",0)
    try {
      val stmt = conn.createStatement
      val rs = stmt.executeQuery(query)
      if (rs.next) {
         student = Student(rs.getInt(1), rs.getString(2), rs.getInt(3))
      }
    } catch {
      case e: Exception => e.printStackTrace()
    }
    student
  }

  def create(s: Student): Unit = {
    val query = "insert into student values(?,?,?)"
    try {
      val stmt = conn.prepareStatement(query)
      stmt.setInt(1, s.id)
      stmt.setString(2, s.name)
      stmt.setInt(3, s.age)
      stmt.executeUpdate
      conn.commit()
    } catch {
      case e: Exception => e.printStackTrace()
    }
  }

  def update(s: Student): Unit = {
    val query = "update student set name = ?, age = ? where id = ?"
    try {
      val stmt = conn.prepareStatement(query)
      stmt.setString(1, s.name)
      stmt.setInt(2, s.age)
      stmt.setInt(3, s.id)
      stmt.executeUpdate
      conn.commit()
    } catch {
      case e: Exception => e.printStackTrace()
    }
  }

  def delete(id: Int): Unit = {
    val query = "delete from student where id = ?"
    try {
      val stmt = conn.prepareStatement(query)
      stmt.setInt(1, id)
      stmt.executeUpdate
      conn.commit()
    } catch {
      case e: Exception => e.printStackTrace()
    }
  }

}
