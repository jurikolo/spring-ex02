package org.talestats.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

@Component
public class HeroExtract {
	public int getCount(Document doc, int cnt) {
		Elements divs = doc.select("div.accordion-group");
		Element div = divs.get(cnt);
		String str = div.toString();
		Pattern p = Pattern.compile("/game/heroes");
		Matcher m = p.matcher(str);
		int result = 0;
	    while (m.find()){
	    	result++;
	    }
		return result;
	}

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
		str = str.substring(0, str.indexOf("\""));
		return Integer.parseInt(str);
	}

	public boolean isAlly(Document doc, int cnt, int heroCnt) {
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
		int ally = str.indexOf("</tr>");
		int enemy = str.indexOf("<td>");
		if ((enemy == -1) || (ally < enemy)) {
			return false;
		} else {
			return true;
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
		str = str.substring(str.indexOf("\">") + 2, str.indexOf("</a>"));
		return str;
	}
	
	public String getKeeper(Document doc, int cnt, int heroCnt) {
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
		str = str.substring(str.indexOf("/accounts/") + 10);
		str = str.substring(str.indexOf("\">") + 2, str.indexOf("</a>"));
		return str;
	}

	public int getGuildId(Document doc, int cnt, int heroCnt) {
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
}