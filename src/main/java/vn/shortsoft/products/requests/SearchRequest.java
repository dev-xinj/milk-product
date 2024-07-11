package vn.shortsoft.products.requests;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SearchRequest {
    String columnSearch; //name, id, date
    String operator; // like, >, < 
    Object value;
}
