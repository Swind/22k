package models

import scala.xml.XML
import java.net.URL
import scala.xml.Node

object OpenDataAPI {
  val lastestURL = "http://www.22kopendata.org/api/lastest"
  val listDataURL = "http://www.22kopendata.org/api/list_data"

  def listData(size:Int, pageNum:Int) = {
	val url = new URL(listDataURL+"/"+size+"/"+pageNum)
    val connection = url.openConnection()
    val feedXML = XML.load(connection.getInputStream)
    parseFeedXML(feedXML)
  }

  def parseFeedXML(feedXML: Node) = (feedXML \ "job").map(parseJob)

  def findFirstElement(node: Node)(tagName: String) = (node \ tagName).first.text

  def parseJob(jobNode: Node) = {
    val nodeParser = findFirstElement(jobNode)_
    Job(
      nodeParser("count"),
      nodeParser("company_name"),
      nodeParser("company_location"),
      nodeParser("job_name"),
      nodeParser("salary"),
      nodeParser("note1"),
      nodeParser("note2"),
      nodeParser("job_url"),
      nodeParser("job_url_screenshot"),
      nodeParser("job_salary_pic"))
  }
}

case class Job(count: String, company_name: String, company_location: String, job_name: String, salary: String, note1: String, note2: String, job_url: String, job_url_screenshort: String, job_salary_pic: String)