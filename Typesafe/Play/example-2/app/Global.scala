import play.api._

import models._
import anorm._

object Global extends GlobalSettings {

  override def onStart(app: Application) {
  InitialData.insert()
  }
  
}

/**
 * Initial set of data to be imported 
 * in the sample application.
 */
object InitialData {
  
  def date(str: String) = new java.text.SimpleDateFormat("yyyy-MM-dd").parse(str)
  
  def insert() = {
    
    if(User.findAll.isEmpty) {
      
      Seq(
        User("one@example.com", "User One",   "one"),
        User("two@example.com", "User Two",   "two"),
        User("three@example.com", "User Three", "three"),
        User("four@example.com", "User Four",  "four")
      ).foreach(User.create)
      
      Seq(
        Project(Id(1), "Play framework", "Play 2.0") -> Seq("one@example.com", "two@example.com", "three@example.com", "four@example.com"),
        Project(Id(2), "Play framework", "Play 1.2.4") -> Seq("one@example.com", "four@example.com"),
        Project(Id(3), "Play framework", "Website") -> Seq("one@example.com", "two@example.com"),
        Project(Id(4), "Zenexity", "Secret project") -> Seq("one@example.com", "two@example.com", "three@example.com", "four@example.com"),
        Project(Id(5), "Zenexity", "Playmate") -> Seq("two@example.com"),
        Project(Id(6), "Personal", "Things to do") -> Seq("one@example.com"),
        Project(Id(7), "Zenexity", "Play samples") -> Seq("one@example.com", "two@example.com"),
        Project(Id(8), "Personal", "Private") -> Seq("two@example.com"),
        Project(Id(9), "Personal", "Private") -> Seq("one@example.com"),
        Project(Id(10), "Personal", "Private") -> Seq("four@example.com"),
        Project(Id(11), "Personal", "Private") -> Seq("three@example.com")
      ).foreach {
        case (project,members) => Project.create(project, members)
    }
      
      Seq(
        Task(NotAssigned, "Todo", 1, "Fix the documentation", false, None, Some("one@example.com")),
        Task(NotAssigned, "Urgent", 1, "Prepare the beta release", false, Some(date("2011-11-15")), None),
        Task(NotAssigned, "Todo", 9, "Buy some milk", false, None, None),
        Task(NotAssigned, "Todo", 2, "Check 1.2.4-RC2", false, Some(date("2011-11-18")), Some("one@example.com")),
        Task(NotAssigned, "Todo", 7, "Finish zentask integration", true, Some(date("2011-11-15")), Some("two@example.com")),
        Task(NotAssigned, "Todo", 4, "Release the secret project", false, Some(date("2012-01-01")), Some("three@example.com"))
      ).foreach(Task.create)
      
    }
    
  }
  
}
