/**
 *
 */
package parsers

import java.io.StringReader
import java.net.MalformedURLException
import java.net.URL
import java.text.ParseException
import java.util.Date

import scala.annotation.migration
import scala.collection.JavaConverters.asScalaBufferConverter
import scala.collection.immutable.StringLike
import scala.util.parsing.combinator.RegexParsers

import models.Item
import models.ItemConstants
import play.api.Logger
import utils.Utils4Devel

/**
 * @author stefan.illgen
 */
class ItemParser extends RegexParsers {

  private var result: Item = null;
  /* parser */
  private var fn: List[String => Boolean] = List(creator, createdOn , href, img )

  /**
   * Returns the resulting item.
   */
  def getResult: Item = result

  /**
   *
   */
  def key: Parser[String] = ItemConstants.ARBITRARY_KEY_REGEX.r ^^ {
    Utils4Devel.trimString2Option(_).get
  }

  /**
   *
   */
  def separator: Parser[String] = ItemConstants.KEY_VALUE_SEPARATOR_REGEX.r ^^ {
    Utils4Devel.trimString2Option(_).get
  }

  /**
   *
   */
  def value: Parser[String] = ItemConstants.ARBITRARY_CHAR_VALUE_REGEX.r ^^ {
    Utils4Devel.trimString2Option(_).get
  }

  /**
   *
   */
  def >^^(r: StringLike[_]): Parser[String] = r.r ^^ {
    Utils4Devel.trimString2Option(_: String) get
  }

  /**
   *
   */
  def ^^>(r: StringLike[_]): Parser[String] = r.r ^^ {
    Utils4Devel.trimString2Option(_:String) /*.map(splitValue(_).get)*/ get
  }

  /**
   * This function sets the appropriate value for the item. It runs a bunch of
   * parsers with the following semantics:
   *
   * `p ~! q` succeeds if `p` succeeds and `q` succeeds on the input left over by `p`.
   * Please consider, I'm not talking about left and right hand side of a function but
   * of the rest, which is left by each parser function.
   */
  def creator(line: String): Boolean = {

    parseAll(
      >^^(ItemConstants.CREATOR_KEY_REGEX) ~! separator ~!
        ^^>(ItemConstants.ARBITRARY_CHAR_VALUE_REGEX),
      new StringReader(line)) match {
        case Success(result: ItemParser.this.~[ItemParser.this.~[String, String], String], next: Input) => {
          this.result.setCreator(result._2);
          return true
        }
        case _: NoSuccess => return false
      }
  }

  /**
   * 
   */
  @Deprecated
  def creator2: Parser[String] = (ItemConstants.CREATOR_KEY_REGEX + ItemConstants.ARBITRARY_CHAR_VALUE_REGEX).r ^^ {
    Utils4Devel.trimString2Option(_).fold("Anonymous") { splitValue(_: String).get }
  }

  /**
   *
   */
  def createdOn(line: String): Boolean = {

    parseAll(>^^(ItemConstants.CREATED_ON_KEY_REGEX) ~! separator ~! createdOnValue, new StringReader(line)) match {
      case Success(result: ItemParser.this.~[ItemParser.this.~[String, String], Date], next: Input) => {
        this.result.setCreatedOn(result._2);
        return true
      }
      case _: NoSuccess => return false
    }
  }

  /**
   * 
   */
  def createdOnValue: Parser[Date] = customDate | milliDate

  /**
   * 
   */
  def customDate: Parser[Date] = ItemConstants.CREATED_ON_VALUE_REGEX.r ^^ {
    try {
      Utils4Devel.trimString2Option(_: String) map { Utils4Devel.parseDate(_: String) } get
    } catch {
      case e: ParseException => {
        Logger.info("Time stamp could not be resolved as long value.")
        null
      }
    }
  }

  /**
   * 
   */
  def milliDate: Parser[Date] = ItemConstants.CREATED_ON_VALUE_MILLI_REGEX.r ^^ {
    try {
      Utils4Devel.trimString2Option(_: String) map { Utils4Devel.dateInMillis(_: String) } get
    } catch {
      case e: NumberFormatException => {
        Logger.info("Time stamp could not be resolved as long value.")
        null
      }
    }
  }

  /**
   * 
   */
  def href(line: String): Boolean = {
    parseAll(>^^(ItemConstants.HREF_KEY_REGEX) ~! separator ~! hrefValue, new StringReader(line)) match {
      case Success(result:ItemParser.this.~[ItemParser.this.~[String,String],URL], next: Input) => {
        this.result.setHref(result._2);
        return true
      }
      case _: NoSuccess => return false
    }
  }

  /**
   * 
   */
  def hrefValue: Parser[URL] = ItemConstants.HREF_VALUE_REGEX.r ^^ {
    Utils4Devel.toOption(new URL(_:String)) get
  }

  /**
   * 
   */
  def img(line: String): Boolean = {
    parseAll(>^^(ItemConstants.IMG_KEY_REGEX) ~! separator ~! imgValue, new StringReader(line)) match {
      case Success(result:ItemParser.this.~[ItemParser.this.~[String,String],URL], next: Input) => {
        this.result.setImg(result._2);
        return true
      }
      case _: NoSuccess => return false
    }
  }
  
  /**
   * 
   */
  def imgValue: Parser[URL] = ItemConstants.IMG_VALUE_REGEX.r ^^ {
    Utils4Devel.toOption(new URL(_:String)) get
  }

  /**
   * 
   */ 
  private def splitKey(data: String) =
    Utils4Devel.trimString2Option(data.splitAt(data.indexOf("="))._1)

  /**
   * 
   */
  private def splitValue(data: String) =
    Utils4Devel.trimString2Option(data.splitAt(data.indexOf("="))._2)    

  /**
   *
   */
  def parseAll(lines: java.util.List[java.lang.String]): Item = {
    result = new Item()
    for (line <- lines.asScala.toList) parseLine(line)
    return result
  }

  /**
   * Simple OR concatenation.
   * One line is covered by one parser for now.
   * So the first one, which succeeds will win.
   */
  def parseLine(line: String) { for (f <- fn) { f(line); return } }

}