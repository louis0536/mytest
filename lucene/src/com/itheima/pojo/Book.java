package com.itheima.pojo;

public class Book {
	// ͼ��ID
	private Integer id;
	// ͼ������
	private String name;
	// ͼ��۸�
	private Float price;
	// ͼ��ͼƬ
	private String pic;
	// ͼ������
	private String desc;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Float getPrice() {
		return price;
	}
	public void setPrice(Float price) {
		this.price = price;
	}
	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	@Override
	public String toString() {
		return "Book [id=" + id + ", name=" + name + ", price=" + price + ", pic=" + pic + ", desc=" + desc + "]";
	}
	
	
}
