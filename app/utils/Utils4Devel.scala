/**
 *
 */
package utils

import java.net.MalformedURLException
import java.net.URL
import play.api.Logger
import java.util.Calendar
import java.util.Date
import java.text.SimpleDateFormat
import parsers.ItemParser
import models.ItemConstants

/**
 * @author stefan.illgen
 *
 */
object Utils4Devel {

  def toOption[T](value: T): Option[T] = if (value == null) None else Some(value)
  
  def trimString2Option(data: String): Option[String] =
    Utils4Devel.toOption(data) map { _.trim } filter { _.length != 0 }
    
  def createDate( year:Int, month:Int, date:Int, hourOfDay:Int, minute:Int, second:Int):Date = {
    val cal:Calendar = Calendar.getInstance();
    cal.set(year, month, date, hourOfDay, minute, second);
    cal.getTime()
  }
  
  def dateInMillis(millis: String): Date = new Date(java.lang.Long.parseLong(millis))

  def formatDate(date: Date): String =
    (new SimpleDateFormat(ItemConstants.DATE_FORMAT_SCHEMA)).format(date)

  def parseDate(date: String): Date =
    (new SimpleDateFormat(ItemConstants.DATE_FORMAT_SCHEMA)).parse(date)
    
  @Deprecated
  def createOptionURL(spec: String): URL =
    Option(spec) map { _.trim } filter { _.length != 0 } map { createURL(_) } orNull
  // or use also ..
  // Option(spec).map(_.trim).filter(_.length != 0).map(createURL(_)).get

  @Deprecated
  def createURL(url: String): URL =
    try return new URL(url)
    catch {
      case e: MalformedURLException => {
        // log it
        Logger.error("Error when creating the URL [" + url + "]:", e)
        // and return null
        return null
      }
    }
}