package com.example.demo.service;

import com.example.demo.model.ProvinceResult;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class XoSoService {

    public List<ProvinceResult> getMienNam() throws Exception {

        Document doc = Jsoup.connect("https://xoso.com.vn/xsmn")
                .userAgent("Mozilla/5.0")
                .timeout(15000)
                .get();

        // 1️⃣ Lấy bảng kết quả miền Nam
        Element table = doc.selectFirst("table.table-result.table-xsmn");
        if (table == null) {
            throw new RuntimeException("Không tìm thấy bảng kết quả");
        }

        // 2️⃣ Lấy danh sách tỉnh (header)
        Elements headerCells = table.select("thead th");
        List<ProvinceResult> provinces = new ArrayList<>();

        // bỏ cột đầu (tên giải)
        for (int i = 1; i < headerCells.size(); i++) {
            ProvinceResult p = new ProvinceResult();
            p.province = headerCells.get(i).text();

            p.giaiNhi = new ArrayList<>();
            p.giaiBa  = new ArrayList<>();
            p.giaiTu  = new ArrayList<>();
            p.giaiSau = new ArrayList<>();

            provinces.add(p);
        }

        // 3️⃣ Duyệt từng hàng giải
        Elements rows = table.select("tbody tr");

        for (Element row : rows) {
            String giai = row.selectFirst("th").text().trim();
            Elements tds = row.select("td");

            for (int i = 0; i < tds.size() && i < provinces.size(); i++) {
                ProvinceResult p = provinces.get(i);
                String value = tds.get(i).text().replace("\n", " ").trim();

                switch (giai) {
                    case "ĐB" -> p.dacBiet = value;
                    case "1"  -> p.giaiNhat = value;
                    case "2"  -> p.giaiNhi.add(value);
                    case "3"  -> p.giaiBa.add(value);
                    case "4"  -> p.giaiTu.add(value);
                    case "5"  -> p.giaiNam = value;
                    case "6"  -> p.giaiSau.add(value);
                    case "7"  -> p.giaiBay = value;
                    case "8"  -> p.giaiTam = value;
                }
            }
        }

        return provinces;
    }
}
