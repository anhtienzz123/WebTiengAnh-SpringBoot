package comjava.ulits;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import comjava.entity.Means;
import comjava.entity.Vocabulary;
import customresponse.CacTuLienQuan;
import customresponse.LoaiTuDTO;
import customresponse.NghiaDTO;
import customresponse.ThongDungDTO;

@Component
public class VocabularyMappingProcess {

	public static final String TU_DONG_NGHIA = "Từ đồng nghĩa";
	public static final String TU_TRAI_NGHIA = "Từ trái nghĩa";

	// có được 1 list nghĩa thuộc loại THÔNG DỤNG
	public ThongDungDTO toThongDungDTO(List<Means> nghias) {

		List<LoaiTuDTO> loaiTuDTOs = new ArrayList<LoaiTuDTO>();

		List<List<Means>> nghiaOfLoaiTus = filterNghiaOfLoaiTus(nghias);

		for (List<Means> list : nghiaOfLoaiTus) {
			LoaiTuDTO loaiTuDTO = getLoaiTuTheoNghias(list);
			loaiTuDTOs.add(loaiTuDTO);
		}
		ThongDungDTO thongDungDTO = new ThongDungDTO(loaiTuDTOs);
		return thongDungDTO;
	}

	// có được 1 list nghĩa thuộc loại CÁC TỪ LIÊN QUAN
	public CacTuLienQuan toCacTuLienQuanDTO(List<Means> nghias) {

		Map<String, String> tuDongNghias = new LinkedHashMap<String, String>();
		Map<String, String> tuTraiNghias = new LinkedHashMap<String, String>();

		for (Means nghia : nghias) {

			if (nghia.getTypeOfVocabulary().equals(TU_DONG_NGHIA)) {
				tuDongNghias.put(nghia.getName(), nghia.getExamples().get(0).getKeyName());

			}

			if (nghia.getTypeOfVocabulary().equals(TU_TRAI_NGHIA)) {
				tuTraiNghias.put(nghia.getName(), nghia.getExamples().get(0).getKeyName());

			}

		}

		CacTuLienQuan cacTuLienQuanDTO = new CacTuLienQuan(tuDongNghias, tuTraiNghias);

		return cacTuLienQuanDTO;
	}

	public List<List<Means>> filterNghiaOfLoaiTus(List<Means> nghias) {

		List<List<Means>> nghiaOfLoaiTus = new ArrayList<List<Means>>();

		List<String> tempt = new ArrayList<String>();

		for (Means nghia : nghias) {

			String tenLoaiTu = nghia.getTypeOfVocabulary();

			// nếu như đã có loai từ này rồi
			if (tempt.contains(tenLoaiTu)) {

				// lọc ra từng loại từ kiểm tra và thêm vào
				for (List<Means> s : nghiaOfLoaiTus) {

					if (s.get(0).getTypeOfVocabulary().equals(tenLoaiTu))
						s.add(nghia);
				}

			} else {
				tempt.add(nghia.getTypeOfVocabulary());
				List<Means> loaiTus = new ArrayList<Means>();
				loaiTus.add(nghia);

				nghiaOfLoaiTus.add(loaiTus);

			}
		}

		return nghiaOfLoaiTus;
	}

	public LoaiTuDTO getLoaiTuTheoNghias(List<Means> nghias) {

		String tenLoaiTu = nghias.get(0).getTypeOfVocabulary();

		List<NghiaDTO> nghiaDTOs = new ArrayList<NghiaDTO>();

		for (Means nghia : nghias) {

			String tenNghia = nghia.getName();

			Map<String, String> viDus = new LinkedHashMap<String, String>();

			// lập từng thằng nghĩa để lấy ví dụ
			nghia.getExamples().forEach(viDu -> {
				viDus.put(viDu.getKeyName(), viDu.getValueName());
			});

			NghiaDTO nghiaDTO = new NghiaDTO(tenNghia, viDus);

			nghiaDTOs.add(nghiaDTO);

		}

		LoaiTuDTO loaiTuDTO = new LoaiTuDTO(tenLoaiTu, nghiaDTOs);

		return loaiTuDTO;
	}

	public List<Means> filterNghiaTheoDanhMuc(Vocabulary tuVung, MeansFilter filterNghia) {

		List<Means> nghias = new ArrayList<Means>();

		for (Means nghia : tuVung.getMeanses()) {

			if (filterNghia.check(nghia))
				nghias.add(nghia);
		}

		return nghias;

	}

}
