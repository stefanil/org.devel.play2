/**
 *
 */
package parsers

import java.io.File

import scala.collection.mutable.MutableList

import org.markdown4j.Markdown4jProcessor

import controllers.Application
import models.Item
import play.api.templates.Html

/**
 * @author stefan.illgen
 *
 */
object Markdown2ItemProcessor extends Markdown4jProcessor {

  val plugin = new ItemMetaDataPlugin
  registerPlugins(plugin)

  def processItems(path2Markdown: String): List[Item] = {
    val result = new MutableList[Item]
    // val mdDir = new URL(routes.Assets.at("me/markdown/me.md").absoluteURL(false)).toURI()
    val mdDir = new File(Application.getClass().getResource("/public" + path2Markdown + "/markdown").toURI())
    if (mdDir.exists() && mdDir.canRead() && mdDir.isDirectory()) {
      for (file <- mdDir.listFiles()) {
        val content = Html(process(file))
        val item:Item = plugin.getResult()
        item.setContent(content)
        result += (item)
      }
    }
    result.toList
  }

}