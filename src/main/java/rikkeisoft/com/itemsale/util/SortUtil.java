package rikkeisoft.com.itemsale.util;
import org.springframework.data.domain.Sort;
public class SortUtil {
    public static Sort getSort(String sort){
        String[] parts = sort.split(":");
        String sortBy = parts[0];
        String direction = parts[1];
        if(direction.equalsIgnoreCase("asc")){
            return  Sort.by(Sort.Direction.ASC,sortBy);
        }
        return Sort.by(Sort.Direction.DESC,sortBy);
    }

}
