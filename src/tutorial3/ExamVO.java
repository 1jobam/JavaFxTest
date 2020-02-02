package tutorial3;

public class ExamVO {
	
	private String zipcode;
	private String sido;
	private String gugun;
	private String dong;
	private String bunji;
	
	public ExamVO(String zipcode, String sido, String gungu, String dong, String bunji) {
		super();
		this.zipcode = zipcode;
		this.sido = sido;
		this.gugun = gungu;
		this.dong = dong;
		this.bunji = bunji;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public String getSido() {
		return sido;
	}

	public void setSido(String sido) {
		this.sido = sido;
	}

	public String getGungu() {
		return gugun;
	}

	public void setGungu(String gungu) {
		this.gugun = gungu;
	}

	public String getDong() {
		return dong;
	}

	public void setDong(String dong) {
		this.dong = dong;
	}

	public String getBunji() {
		return bunji;
	}

	public void setBunji(String bunji) {
		this.bunji = bunji;
	}
	
}
