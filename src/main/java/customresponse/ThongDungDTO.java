package customresponse;

import lombok.NoArgsConstructor;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ThongDungDTO {

	private List<LoaiTuDTO> loaiTus;
	
}
