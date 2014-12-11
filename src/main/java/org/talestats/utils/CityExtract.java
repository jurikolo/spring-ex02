package org.talestats.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.talestats.scheduled.Scheduler;

import static java.util.Optional.*;

@Component
public class CityExtract {
	
	static final Logger logger = LoggerFactory.getLogger(Scheduler.class);
	
	public int getSize(String str) {
		str = str.substring(str.indexOf("Размер города"));
		Pattern cityPattern = Pattern.compile("([\\D\\s]+)([0-9]+)");
		Matcher m = cityPattern.matcher(str);
		if (m.matches()) {
			return Integer.parseInt(m.group(2));
		} else {
			return 0;
		}
	}

	public String getName(Document doc) {
		String description = doc.select("meta[name=description]").get(0).attr("content");
		return ofNullable(
				description.substring(
						description.indexOf("«") + 1,
						description.indexOf("»")))
				.orElse("-").trim();
	}
}