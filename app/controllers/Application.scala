package controllers

import java.io.File
import org.markdown4j.Markdown4jProcessor
import play.api._
import play.api.mvc._
import play.api.templates.Html
import java.net.URI
import java.net.URL
import java.io.FileReader
import models.Item
import java.util.ArrayList
import scala.collection.mutable.MutableList

/**
 * @author stefan.illgen
 */
object Application extends Controller {

  def index = Action {
    Redirect("/me")
  }

  def me = Action { implicit request =>
    Ok(views.html.me(Markdown2ItemProcessor.processItems("/me")))
  }

  def projects = Action {
    Ok(views.html.projects(Markdown2ItemProcessor.processItems("/projects")))
  }

  def music = Action {
    Ok(views.html.music(Markdown2ItemProcessor.processItems("/music")))
  }

}