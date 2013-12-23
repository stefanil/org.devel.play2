/**
 * 
 */
package controllers;

import java.util.List;
import java.util.Map;

import models.Item;

import org.markdown4j.Plugin;

/**
 * @author stefan.illgen
 */
public class ItemMetaDataPlugin extends Plugin {

	private Item item;

	public ItemMetaDataPlugin() {
		super("markdown_metadata_plugin");
	}

	@Override
	public void emit(StringBuilder out, List<String> lines,
			Map<String, String> params) {
		
		item = new Item();

		for (String line : lines) {
			String key = getMetaKey(line);
			if (key != null) {
				// switch over the meta key
				switch (key) {
				case "creator":
					item.setCreator(getMetaValue(line));
					break;
				case "createdOn":
					item.setCreatedOn(getMetaValue(line));
					break;
				case "href":
					item.setHref(item.createOptionURL(getMetaValue(line)));
					break;
				case "img":
					item.setImg(item.createOptionURL(getMetaValue(line)));
					break;
				}
			}
		}
	}

	public Item getItem() {
		return item;
	}

	private String getMetaValue(String line) {
		try {
			String value = line.substring(line.indexOf("=") + 1).trim();
			return (value.length() > 0) ? value : null;
		} catch (IndexOutOfBoundsException e) {
			return null;
		}
	}

	private String getMetaKey(String line) {
		try {
			String key = line.substring(0, line.indexOf("=")).trim();
			return (key.length() > 0) ? key : null;
		} catch (IndexOutOfBoundsException e) {
			return null;
		}
	}

}
