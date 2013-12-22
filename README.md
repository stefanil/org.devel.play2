org.devel.skills.play2
===============

This is an online presentation for presenting my skills.

#Features

##Technical Features

###Built on Play Framework v2.2.1
* dependency and build management based on SBT
* built using Scala programming language in frontend and backend

###Generate Content from Markdown

####Approach 0: Reuse an existing Markdown Processor
* Java
  * <a href="https://code.google.com/p/markdown4j/">Markdown4J</a>
* Scala
  * <a href="https://github.com/planet42">Laika</a>
  * <a href="https://github.com/chirino/scalamd">ScalaMD</a>
* see also <a href="http://stackoverflow.com/questions/19784525/recommended-java-scala-library-for-converting-markdown-to-html-and-pdf">this link</a>

####Approach x: Create your own parser / processor
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

#Basic Layout

<img src="https://raw.github.com/stefanil/org.devel.skills.play2/master/layout/layout00.png" />
