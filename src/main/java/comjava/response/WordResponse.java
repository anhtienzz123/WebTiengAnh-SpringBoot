package comjava.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WordResponse {

	private Integer id;
	private String key;
	private String value;
	private List<String> mixKey;
}
