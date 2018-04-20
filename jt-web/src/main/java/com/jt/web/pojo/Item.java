package com.jt.web.pojo;


import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.jt.common.po.BasePojo;
@Table(name="tb_item")
public class Item extends BasePojo {

	private static final long serialVersionUID = -1044674607080460156L;
	//标识主键信息,表示主键自增
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;			//商品ID，也是商品编号
	 private String title;		//商品标题
	 private String sellPoint;	//商品卖点
	 private Long price;		//商品价格
	 private Integer num;		//商品数量
	 private String barcode;	//商品二维码信息
	 private String image;		//商品图片 ，最多5张图片
	 private Long cid;			//商品分类信息
	 private Integer status;		//商品状态信息 ，默认值为1，可选值：1正常，2下架，3删除
	
	 public String[] getImages(){
		 return image.split(",");
	 }
	 
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getSellPoint() {
		return sellPoint;
	}
	public void setSellPoint(String sellPoint) {
		this.sellPoint = sellPoint;
	}
	public Long getPrice() {
		return price;
	}
	public void setPrice(Long price) {
		this.price = price;
	}
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
	}
	public String getBarcode() {
		return barcode;
	}
	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public Long getCid() {
		return cid;
	}
	public void setCid(Long cid) {
		this.cid = cid;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "Item [id=" + id + ", title=" + title + ", sellPoint=" + sellPoint + ", price=" + price + ", num=" + num
				+ ", barcode=" + barcode + ", image=" + image + ", cid=" + cid + ", status=" + status + "]";
	}	 
	 
	 
}
