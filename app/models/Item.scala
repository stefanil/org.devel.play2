/**
 *
 */
package models

import java.net.URL
import java.util.Date
import java._
import play.api.templates.Html
import utils.Utils4Devel
import java.util.Calendar

/**
 * @author stefan.illgen
 */
class Item {
  
  // defaults
  val HREF:URL = new URL("http://anonymous.org")
  val IMG:URL = new URL("http://anonymous.org")
  val CREATED_ON:Date = Utils4Devel.createDate(1982, 2, 20, 23, 30, 35)
  val CREATOR = "Anonymous"
  val CONTENT = Html("There was no markdown scripted yet.")
  
  // bean stuff
  private var _creator: Option[String] = Utils4Devel.toOption(CREATOR)
  def creator(): Option[String] = _creator
  def getCreator(): String = _creator getOrElse CREATOR
  def setCreator(creator: String) = _creator = Utils4Devel.toOption(creator)
  
  private var _createdOn: Option[Date] = Utils4Devel.toOption(CREATED_ON)
  def createdOn(): Option[Date] = _createdOn
  def getCreatedOn(): Date = _createdOn getOrElse CREATED_ON
  def setCreatedOn(createdOn: Date) = _createdOn = Utils4Devel.toOption(createdOn)
  
  private var _href: Option[URL] = Utils4Devel.toOption(HREF)
  def href(): Option[URL] = _href
  def getHref(): URL = _href getOrElse HREF
  def setHref(href: URL) = _href = Utils4Devel.toOption(href)

  private var _img: Option[URL] = Utils4Devel.toOption(IMG)
  def img(): Option[URL] = _img
  def getImg(): URL = _img  getOrElse IMG
  def setImg(img: URL) = _img = Utils4Devel.toOption(img)

  private var _content: Option[Html] = Utils4Devel.toOption(CONTENT)
  def content(): Option[Html] = _content
  def getContent(): Html = _content getOrElse CONTENT
  def setContent(content: Html) = _content = Utils4Devel.toOption(content)
  
}