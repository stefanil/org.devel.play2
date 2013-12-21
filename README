org.devel.play2
===============

This is my online presentation.

#Features

##Technical Features

###Generate Content from Markdown
I've created the below InputFormat.scala file:

<code>
abstract trait InputFormat { def format(source: String): String }
</code>

<code>
object LineBreakFormatter extends InputFormat {
  def format(source: String) = source.replaceAll("\n", "\<br/\>")
}
</code>

<code>
object HtmlEscapeFormatter extends InputFormat {
  def format(source: String) = play.api.templates.HtmlFormat.escape(source).body
}
</code>

<code>
object InputFormats {
  private val formaters = HtmlEscapeFormatter :: LineBreakFormatter :: Nil
  def format(source: String) = formaters.foldLeft(source)((result, formatter) => formatter.format(result))
}
</code>

Then in the template I do:

<code>
@Html(InputFormats.format(content))
</code>

This pattern lets me create additional pre-processing rules such as parsing bbcode, escaping emails to prevent spam etc just by implementing a new InputFormat trait and adding it to the list. My usercase is simple so InputFormats is a simple helper object, if I was to extend this I'd probably make InputFormats a class InputFormats(Seq[InputFormat]) so I can have different pre-processing rules on user content depending where it is on the site.
