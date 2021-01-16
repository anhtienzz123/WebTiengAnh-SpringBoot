package comjava.ulits;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import comjava.entity.Means;
import comjava.entity.Vocabulary;
import comjava.entity.Example;

@Component
public class CrawlVocabulary {

	private final String URL = "http://tratu.soha.vn/dict/en_vn/";
	private final String TU_LIEN_QUAN = "Các từ liên quan";

	
	public Vocabulary getVocabulary(String vocabularyName) throws IOException {

		Document document = Jsoup.connect(URL + vocabularyName).get();
		Element bodyContentElement = document.selectFirst("#bodyContent");

		// Lấy phát âm
		String pronunciation = bodyContentElement.selectFirst("#content-5").text();

		// lấy tất cả danh mục từ
		List<Means> meanses = new ArrayList<Means>();

		Elements danhMucTuElements = bodyContentElement.select(".section-h2");
		for (Element danhMucTuElement : danhMucTuElements) {
			List<Means> nghiasTheoDanhMucTu = getNghiasTheoDanhMucTu(danhMucTuElement);

			meanses.addAll(nghiasTheoDanhMucTu);
		}
		Vocabulary vocabulary = new Vocabulary(vocabularyName, pronunciation, meanses);

		vocabulary.getMeanses().forEach(s -> {

			s.setVocabulary(vocabulary);

			s.getExamples().forEach(s1 -> {
				s1.setMeans(s);
			});
		});

		return vocabulary;
	}

	public List<Means> getNghiasTheoDanhMucTu(Element danhMucTuElement) {

		List<Means> nghias = new ArrayList<Means>();

		// lấy tên danh mục từ
		String tenDanhMucTu = danhMucTuElement.selectFirst("h2").text();

		// lấy ra nhiều loại từ
		Elements loaiTuElements = danhMucTuElement.select(".section-h3");

		for (Element loaiTuElement : loaiTuElements) {

			List<Means> nghiasTemp = getNghiasTheoLoaiTu(loaiTuElement, tenDanhMucTu);
			nghias.addAll(nghiasTemp);
		}

		return nghias;

	}

	public List<Means> getNghiasTheoLoaiTu(Element loaiTuElement, String tenDanhMucTu) {

		List<Means> nghias = new ArrayList<Means>();

		// lay ten loai tu
		String tenLoaiTu = loaiTuElement.selectFirst("h3").text();

		// Lay nghia
		Elements nghiaElements = loaiTuElement.select(".section-h5");

		nghiaElements.forEach(nghiaElement -> {

			// lấy tên nghĩa
			String tenNghia = nghiaElement.selectFirst("h5").text();

			Elements viDuElements = tenDanhMucTu.equals(TU_LIEN_QUAN) ? nghiaElement.select("dl dd")
					: nghiaElement.select("dl dd dl dd");
			List<Example> viDus = new ArrayList<Example>();

			int size = viDuElements.size();
			for (int i = 0; i < size; i = i + 2) {
				String textSau = "";

				if (i + 1 < size) {
					textSau = viDuElements.get(i + 1).text();
				}

				Example viDu = new Example(viDuElements.get(i).text(), textSau);

				viDus.add(viDu);
			}

			Means nghia = new Means(tenDanhMucTu, tenLoaiTu, tenNghia, viDus);
			nghias.add(nghia);
		});

		return nghias;
	}

}
