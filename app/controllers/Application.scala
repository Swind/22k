package controllers

import play.api._
import play.api.mvc._
import play.api.data._
import play.api.data.Forms._
import models._

object Application extends Controller {
  
  def index = Action {
    Redirect(routes.Application.jobs(1))
  }
  
  def jobs(pageNum:Int = 1) = Action {
    val dataList = OpenDataAPI.listData(9,pageNum).toList
    if(dataList.size < 9)
      Ok(views.html.index(dataList, 1))
    else
      Ok(views.html.index(dataList, pageNum+1))
  }
  
}