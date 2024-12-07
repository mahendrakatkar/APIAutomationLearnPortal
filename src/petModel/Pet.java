package petModel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@JsonIgnoreProperties(value= {"photoUrls", "tags"})
public class Pet {

		public Pet() {
			
		}
	

private int id;
private String name;
private Category category;
private String status;
public String getStatus() {
	return status;
}


public void setStatus(String status) {
	this.status = status;
}


public Pet(int id, String name, Category category,String status) {
	super();
	this.id = id;
	this.name = name;
	this.category = category;
	this.status=status;
}


public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public Category getCategory() {
	return category;
}
public void setCategory(Category category) {
	this.category = category;
}

}
