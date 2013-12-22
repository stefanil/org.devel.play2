package controllers

import play.api._
import play.api.mvc._
import play.core.Router

/**
 * @author stefan.illgen
 */
object Application extends Controller {

  def index = Action {
    Redirect("/me")   
  }
  
  def me = Action {
    Ok(views.html.me("Information about me .."))
  }
  
  def projects = Action {
    Ok(views.html.projects("Information about projects .."))
  }
  
  def music = Action {
    Ok(views.html.music("Information about music .."))
  }

}