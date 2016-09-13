package pt.gois.dtServices.util;

public class SearchPageCtrlRelField {

    String field;
    String value;
    String operator;

    public SearchPageCtrlRelField(String field, String value) {
	this( field, value, " = ");
    }
    
    public SearchPageCtrlRelField(String field, String value, String operator) {
	this.field = field;
	this.value = value;
	this.operator = operator;
    }

    public String getField() {
        return field;
    }
    
    public String getFieldEncoded(){
	return ":" + field.replaceAll(".", "_");
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }
    
    public String toString(){
	return getField() + getOperator() + getFieldEncoded(); 
    }
}
