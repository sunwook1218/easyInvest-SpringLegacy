package com.sun.www.common.scrapper;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

@Service
public class IndexScrapper {
	
	public static final String KOSPI = "KOSPI";
	public static final String KOSDAQ = "KOSDAQ";
	
	public List<Integer> scrap(String option, int length) throws Exception {
		
		if(!(option.equals(KOSPI) || option.equals(KOSDAQ))) {
			throw new Exception("invalid option : " + option);
		}
		
		List<Integer> result = new ArrayList<>();
		
		for(int page = 1; page <= 10; page++) {
			
			int idx = 0;
			String url = "https://finance.naver.com/sise/sise_index_day.nhn?code="+option+"&page=";
			url = url + page;
			
			Document doc = Jsoup.connect(url).get();
			
			Elements elements = doc.select("tbody tr");
			
			for(Element element : elements) {
				if(idx == 2 || idx == 3 || idx == 4 || idx == 9 || idx == 10 || idx == 11) {
					String price = "";
					if(option.equals(KOSPI)) {
						price = element.getElementsByClass("number_1").text().substring(0, 5);	
					} else if(option.equals(KOSDAQ)) {
						price = element.getElementsByClass("number_1").text().substring(0, 3);
					}
					price = price.replaceAll(",", "");
					result.add(Integer.valueOf(price));
				}
				idx++;
			}
		}
		
		return result;
	}

}
