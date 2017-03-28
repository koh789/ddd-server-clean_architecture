package jp.ddd.server.adapter.gateway.dynamodb.table.converter;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverter;
import jp.ddd.server.other.utils.Dates;

import java.util.Date;

/**
 * Created by noguchi_kohei 
 */
public class DateDynamoDbConverter implements DynamoDBTypeConverter<String, Date> {

    @Override
    public String convert(Date date) {
        return Dates.toString(date, Dates.DEFAULT_DATE_FORMAT);
    }

    @Override
    public Date unconvert(String dateStr) {
        return Dates.toDate(Dates.DEFAULT_DATE_FORMAT, dateStr);
    }
}
