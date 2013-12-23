/**
 *
 */
package models

import java.net.MalformedURLException
import java.net.URL
import play.api.Logger
import play.api.templates.Html
import java.text.SimpleDateFormat
import java.util.Date
import java.lang.Long

/**
 * @author stefan.illgen
 */
class Item {

  private var _creator: Option[String] = null
  def creator(): Option[String] = _creator
  def getCreator(): String = _creator orNull
  def setCreator(creator: String) = _creator = Option(creator)

  private var _createdOn: Option[String] = null
  def createdOn(): Option[String] = _createdOn
  def getCreatedOn(): String = _createdOn orNull
  def setCreatedOn(createdOn: String) = _createdOn = 
    Option(new SimpleDateFormat("KK:mm:ss a '|' yyyy-MM-dd").
      format(new Date(Long.valueOf(createdOn))))

  private var _href: Option[URL] = null
  def href(): Option[URL] = _href
  def getHref(): URL = _href orNull
  def setHref(href: URL) = _href = Option(href)

  private var _img: Option[URL] = null
  def img(): Option[URL] = _img
  def getImg(): URL = _img orNull
  def setImg(img: URL) = _img = Option(img)

  private var _content: Option[Html] = null
  def content(): Option[Html] = _content
  def getContent(): Html = _content orNull
  def setContent(content: Html) = _content = Option(content)

  def createOptionURL(spec: String): URL =
    Option(spec) map { _.trim } filter { _.length != 0 } map { createURL(_) } orNull
  // or use also ..
  // Option(spec).map(_.trim).filter(_.length != 0).map(createURL(_)).get

  def createURL(url: String): URL = {
    try { return new URL(url) }
    catch {
      case e: MalformedURLException => {
        // log it
        Logger.error("Error when creating the URL [" + url + "]:", e)
        // and return null
        return null
      }
    }
  }

}