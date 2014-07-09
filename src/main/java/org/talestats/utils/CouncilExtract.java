package org.talestats.utils;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

@Component
public class CouncilExtract {

	public int getCount(Document doc) {
		Elements cnt = doc.select("div.accordion-toggle");
		return cnt.size();
	}

	public int getId(Document doc, int cnt) {
		Elements divs = doc.select("div.accordion-toggle");
		Element div = divs.get(cnt);
		String str = div.toString();
		str = str.substring(str.indexOf("pgf-person") + 11, str.indexOf("\">"));
		return Integer.parseInt(str);
	}

	public String getName(Document doc, int cnt) {
		Elements divs = doc.select("div.accordion-toggle");
		Element div = divs.get(cnt);
		String str = div.toString();
		str = str.substring(str.indexOf("<a href=\"#\">") + 12,
				str.indexOf("<small>"));
		return str;
	}

	public String getRace(Document doc, int cnt) {
		Elements divs = doc.select("div.accordion-toggle");
		Element div = divs.get(cnt);
		String str = div.toString();
		str = str.substring(str.indexOf("<small>") + 7, str.indexOf("</small"));
		str = str.substring(0, str.indexOf("-"));
		return str;
	}

	public String getJob(Document doc, int cnt) {
		
		Elements divs = doc.select("div.accordion-toggle");
		Element div = divs.get(cnt);
		String str = div.toString();
		str = str.substring(str.indexOf("<small>") + 7, str.indexOf("</small"));
		str = str.substring(str.indexOf("-")+1, str.indexOf(","));
		return str;
	}
}
