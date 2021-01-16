package customresponse;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoaiTuDTO {

	private String tenLoaiTu;
	private List<NghiaDTO> nghias;
}
