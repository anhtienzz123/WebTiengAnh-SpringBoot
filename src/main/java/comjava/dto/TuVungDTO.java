package comjava.dto;

import customresponse.CacTuLienQuan;
import customresponse.ThongDungDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TuVungDTO {

	private String tenTuVung;
	private String phatAm;
	private ThongDungDTO thongDung;
	private ThongDungDTO chuyenNganh;
	private CacTuLienQuan cacTuLienQuan;
	
}
