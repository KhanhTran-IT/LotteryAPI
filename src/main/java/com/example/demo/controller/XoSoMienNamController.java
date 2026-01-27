package com.example.demo.controller;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.Map;

@RestController
public class XoSoMienNamController {

    @GetMapping("/api/xoso/mien-nam")
    public Map<String, String> getXoSoMienNam() {

        Map<String, String> result = new LinkedHashMap<>();

        try {
            Document doc = Jsoup.connect("https://xoso.com.vn/xsmn")
                    .userAgent("Mozilla/5.0")
                    .timeout(10000)
                    .get();

            Elements rows = doc.select("table.result tr");

            for (Element row : rows) {
                Element giai = row.selectFirst("th");
                Element so = row.selectFirst("td");

                if (giai != null && so != null) {
                    result.put(giai.text(), so.text());
                }
            }

            result.put("status", "OK");

        } catch (Exception e) {
            result.put("status", "ERROR");
            result.put("message", e.getMessage());
        }

        return result;
    }
}
