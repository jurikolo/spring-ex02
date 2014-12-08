package org.talestats.utils;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Component;
import org.talestats.config.Constants;

@Component
public class JSoup {

	public static Document getDoc(String url) {
		Document doc = null;
		try {
			doc = Jsoup.parse(Jsoup.connect(url).timeout(Constants.TIMEOUT).get().toString(), "UTF-8");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return doc;
	}
}