/**
 * 
 */
package parsers;

import java.util.List;
import java.util.Map;

import models.Item;

import org.markdown4j.Plugin;

/**
 * @author stefan.illgen
 */
public class ItemMetaDataPlugin extends Plugin {
	
	private ItemParser itemParser;

	public ItemMetaDataPlugin() {
		super("markdown_metadata_plugin");
		itemParser = new ItemParser();
	}

	@Override
	public void emit(StringBuilder out, List<String> lines,
			Map<String, String> params) {		
		itemParser.parseAll(lines);
	}

	public Item getResult() {
		return itemParser.getResult();
	}

}
