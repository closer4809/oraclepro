package com.javaex.phone;

public class PersonVo {
	private int phoneId;
	private String name;
	private String hp;
	private String company;
	
	public PersonVo() {
		super();
	}

	public PersonVo(String name, String hp, String company) {
		super();
		this.name = name;
		this.hp = hp;
		this.company = company;
	}

	public PersonVo(String name, String hp, String company, int phoneId) {
		super();
		this.phoneId = phoneId;
		this.name = name;
		this.hp = hp;
		this.company = company;
	}
	
	public PersonVo(int phoneId, String name, String hp, String company) {
		super();
		this.phoneId = phoneId;
		this.name = name;
		this.hp = hp;
		this.company = company;
	}

	public int getPhoneId() {
		return phoneId;
	}

	public void setPhoneId(int phoneId) {
		this.phoneId = phoneId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getHp() {
		return hp;
	}

	public void setHp(String hp) {
		this.hp = hp;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	@Override
	public String toString() {
		return "PhoneVo [phoneId=" + phoneId + ", name=" + name + ", hp=" + hp + ", company=" + company + "]";
	}
	
	

}