package pt.gois.dtServices.util;

public class SearchPageCtrlFullField {

    String field;
    String value;

    public SearchPageCtrlFullField(String field, String value) {
	this.field = field;
	this.value = value;
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

    public String toString(){
	return "fulltextSearch( '" + getField() + "','" +  getFieldEncoded() + ")"; 
    }
}
