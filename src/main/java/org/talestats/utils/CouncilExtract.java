package org.talestats.utils;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

@Component
public class CouncilExtract {

	//Returns result + 1 as first div shows heroes who set current city as home city.
	public int getCount(Document doc) {
		return doc.select("div.accordion-toggle").size();
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
		return str.trim();
	}

	public String getRace(Document doc, int cnt) {
		Elements divs = doc.select("div.accordion-toggle");
		Element div = divs.get(cnt);
		String str = div.toString();
		str = str.substring(str.indexOf("<small>") + 7, str.indexOf("</small"));
		str = str.substring(0, str.indexOf("-"));
		return str.trim();
	}

	public String getJob(Document doc, int cnt) {
		
		Elements divs = doc.select("div.accordion-toggle");
		Element div = divs.get(cnt);
		String str = div.toString();
		str = str.substring(str.indexOf("<small>") + 7, str.indexOf("</small"));
		str = str.substring(str.indexOf("-") + 1, str.indexOf(","));
		return str.trim();
	}

	public String getSkill(Document doc, int cnt) {
		Elements divs = doc.select("div.accordion-toggle");
		Element div = divs.get(cnt);
		String str = div.toString();
		str = str.substring(str.indexOf("<small>") + 7, str.indexOf("</small") + 7);
		str = str.substring(str.indexOf(",") + 1, str.indexOf("</small"));
		str = str.substring(0, str.indexOf(","));
		return str.trim();
	}

	public int getAllies(Document doc, int cnt) {
		Elements divs = doc.select("div.accordion-toggle");
		Element div = divs.get(cnt);
		String str = div.toString();
		str = str.substring(str.indexOf("<small>") + 7, str.indexOf("</small") + 7);
		str = str.substring(str.indexOf("противников") + 13, str.indexOf("</small"));
		str = str.substring(0, str.indexOf("/"));
		return Integer.parseInt(str);
	}

	public int getEnemies(Document doc, int cnt) {
		Elements divs = doc.select("div.accordion-toggle");
		Element div = divs.get(cnt);
		String str = div.toString();
		String result = "";
		str = str.substring(str.indexOf("<small>") + 7, str.indexOf("</small") + 7);
		str = str.substring(str.indexOf("противников"), str.indexOf("</small"));
		str = str.substring(str.indexOf("/") + 1);
		for (int i = 0; i < str.length(); i++)
		{
			if (str.substring(i, i+1).matches("[0-9]"))
				result += str.substring(i, i+1);
		}
		return Integer.parseInt(result);
	}

	public int getInfluence(Document doc, int cnt) {
		Elements divs = doc.select("div.accordion-toggle");
		Element div = divs.get(cnt);
		String str = div.toString();
		str = str.substring(str.indexOf("<small>") + 7, str.indexOf("</small") + 7);
		str = str.substring(str.indexOf("влиятельность") + 15, str.indexOf("</small"));
		str = str.substring(0, str.indexOf("%"));
		return Integer.parseInt(str);
	}
}
