package comjava.ulits;

import java.time.LocalDate;

import org.springframework.stereotype.Component;

@Component
public class DateProcessor {

	
	public String formatDate(LocalDate date) {
		
		//SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		
		String ngay = date.getDayOfMonth() + "";
		String thang = date.getMonthValue() + "";
		String nam = date.getYear()+"";
		
		String result = ngay + "-" + thang + "-"+nam;
		
		return result;
	}
}
