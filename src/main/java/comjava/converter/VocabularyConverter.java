package comjava.converter;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import comjava.dto.TuVungDTO;
import comjava.entity.Means;
import comjava.entity.Vocabulary;
import comjava.ulits.VocabularyMappingProcess;
import customresponse.CacTuLienQuan;
import customresponse.ThongDungDTO;

@Component
public class VocabularyConverter {

	public static final String THONG_DUNG = "Thông dụng";
	public static final String CHUYEN_NGANH = "Chuyên ngành";
	public static final String CAC_TU_LIEN_QUAN = "Các từ liên quan";

	private VocabularyMappingProcess vocabularyMappingProcess;

	@Autowired
	public VocabularyConverter(VocabularyMappingProcess vocabularyMappingProcess) {
		this.vocabularyMappingProcess = vocabularyMappingProcess;
	}

	public TuVungDTO toDTO(Vocabulary vocabulary) {

		TuVungDTO tuVungDTO = null;

		if (vocabulary != null) {
			String tenTuVung = vocabulary.getName();
			String phatAm = vocabulary.getPronunciation();

			List<Means> meansesOfCommon = vocabularyMappingProcess.filterNghiaTheoDanhMuc(vocabulary,
					s -> s.getCategoryName().equals(THONG_DUNG));
			List<Means> meansesOfSpecialized = vocabularyMappingProcess.filterNghiaTheoDanhMuc(vocabulary,
					s -> s.getCategoryName().equals(CHUYEN_NGANH));

			List<Means> meansesOfRelatedWord = vocabularyMappingProcess.filterNghiaTheoDanhMuc(vocabulary,
					s -> s.getCategoryName().equals(CAC_TU_LIEN_QUAN));

			ThongDungDTO thongDungDTO = vocabularyMappingProcess.toThongDungDTO(meansesOfCommon);
			ThongDungDTO chuyenNganhDTO = vocabularyMappingProcess.toThongDungDTO(meansesOfSpecialized);
			CacTuLienQuan cacTuLienQuanDTO = vocabularyMappingProcess.toCacTuLienQuanDTO(meansesOfRelatedWord);
			tuVungDTO = new TuVungDTO(tenTuVung, phatAm, thongDungDTO, chuyenNganhDTO, cacTuLienQuanDTO);
		}

		return tuVungDTO;
	}

}
