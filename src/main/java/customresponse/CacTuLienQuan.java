package customresponse;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CacTuLienQuan {

	private Map<String, String> tuDongNghias;
	private Map<String, String> tuTraiNghias;
	
}
