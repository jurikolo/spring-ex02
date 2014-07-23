package org.talestats.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

@Component
public class GuildExtract {

	public int getId(Document doc, int cnt, int heroCnt) {
		Elements divs = doc.select("div.accordion-group");
		Element div = divs.get(cnt);
		String str = div.toString();
		Pattern p = Pattern.compile("/game/heroes");
		Matcher m = p.matcher(str);
		int TmpCnt = 0;
		while (m.find() && heroCnt != TmpCnt){
	    	TmpCnt++;
	    	str = str.substring(str.indexOf("/game/heroes") + 10);
	    }
		str = str.substring(str.indexOf("/game/heroes") + 13);
		int clan = str.indexOf("clans");
		int noClan = str.indexOf("</td>");
		if ((clan == -1) || (noClan < clan)) {
			return 0;
		} else {
			str = str.substring(str.indexOf("clans") + 6);
			str = str.substring(0, str.indexOf("\""));
			return Integer.parseInt(str);
		}
	}
	
	public String getName(Document doc, int cnt, int heroCnt) {
		Elements divs = doc.select("div.accordion-group");
		Element div = divs.get(cnt);
		String str = div.toString();
		Pattern p = Pattern.compile("/game/heroes");
		Matcher m = p.matcher(str);
		int TmpCnt = 0;
		while (m.find() && heroCnt != TmpCnt){
	    	TmpCnt++;
	    	str = str.substring(str.indexOf("/game/heroes") + 10);
	    }
		str = str.substring(str.indexOf("/game/heroes") + 13);
		int clan = str.indexOf("clans");
		int noClan = str.indexOf("</td>");
		if ((clan == -1) || (noClan < clan)) {
			return "";
		} else {
			str = str.substring(str.indexOf("clans") + 6);
			str = str.substring(str.indexOf(">") + 1, str.indexOf("<"));
			return str.trim();
		}
	}
}