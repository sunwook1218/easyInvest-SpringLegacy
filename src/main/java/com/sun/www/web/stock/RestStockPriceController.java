package com.sun.www.web.stock;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sun.www.common.scrapper.IndexScrapper;

@RestController
@RequestMapping("/api/v1/price")
public class RestStockPriceController {
	
	public RestStockPriceController(IndexScrapper indexScrapper) {
		this.indexScrapper = indexScrapper;
	}
	
	private IndexScrapper indexScrapper;
	
	@PostMapping("/ci")
	public ResponseEntity<List<Integer>> compositeIndex(String option) throws Exception {
		
		if(option.equalsIgnoreCase(IndexScrapper.KOSPI)) {
			return ResponseEntity.ok(indexScrapper.scrap(IndexScrapper.KOSPI, 60));
		} else if (option.equalsIgnoreCase(IndexScrapper.KOSDAQ)) {
			return ResponseEntity.ok(indexScrapper.scrap(IndexScrapper.KOSDAQ, 60));
		} else {
			throw new Exception("invaild option : " + option); 
		}
		
	}
	
}
