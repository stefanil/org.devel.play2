/**
 *
 */
package models

import java.util.Date

/**
 * @author stefan.illgen
 *
 */
object ItemConstants {
  
  /**
   * Pattern used for formatting dates.
   */
  val DATE_FORMAT_SCHEMA = "KK:mm:ss a '|' yyyy-MM-dd"

  val CREATOR_KEY: String = "creator"
  val CREATED_ON_KEY: String = "createdOn"
  val HREF_KEY: String = "href"
  val IMG_KEY: String = "img"

  val ARBITRARY_KEY_REGEX: String = """\s*\w+\s*""";
  val KEY_VALUE_SEPARATOR_REGEX: String = """=""";
  val ARBITRARY_CHAR_VALUE_REGEX: String = """\s*\w+\s*"""

  val CREATOR_KEY_REGEX: String = """creator"""

  val CREATED_ON_KEY_REGEX: String = """createdOn"""
  val CREATED_ON_VALUE_REGEX: String = """(\d{2}:){3}\s[A|P]M\s|\s\d{4}-\d{2}-\d{2}"""
  val CREATED_ON_VALUE_MILLI_REGEX: String = """\d*"""
  val CREATED_ON_VALUE_EMPTY_REGEX: String = """\w{0}\d{0}"""

  val HREF_KEY_REGEX: String = """href"""
  val HREF_VALUE_REGEX: String = """http://\w*\d*"""
  val IMG_KEY_REGEX: String = """img"""
  val IMG_VALUE_REGEX: String = """http://\w*\d*"""
    
}