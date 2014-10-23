package org.talestats.utils;

import org.jsoup.nodes.Element;
import org.springframework.stereotype.Component;

@Component
public class VoteExtract {
	public Integer getCount(Element element)
	{
		//TODO there might be less cities to be looked
		Integer size = element.select("tr").size();
		if (size > 12)
		{
			int result = 11;
			Element ten = element.select("tr").get(11);
			Element last = element.select("tr").get(12);
			while ((result < size - 2) && (ten.select("td").last().toString().compareTo(last.select("td").last().toString()) == 0)) {
				result++;
				ten = element.select("tr").get(result);
				last = element.select("tr").get(result+1);
			}
			return result;
		}
		else
		{
			return size - 1;
		}
	}

	public String getCityName(Element element, int cnt) {
		return element.select("tr").get(cnt).select("td").get(1).toString().replaceAll("<(?!\\/?span)[^>]+>", "");
	}
}