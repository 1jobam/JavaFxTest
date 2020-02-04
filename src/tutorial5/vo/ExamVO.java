package tutorial5.vo;

public class ExamVO {

	private String name;
	private Integer guk;
	private Integer su;
	private Integer eng;
	
	public ExamVO(String name, Integer guk, Integer su, Integer eng) {
		super();
		this.name = name;
		this.guk = guk;
		this.su = su;
		this.eng = eng;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getGuk() {
		return guk;
	}

	public void setGuk(Integer guk) {
		this.guk = guk;
	}

	public Integer getSu() {
		return su;
	}

	public void setSu(Integer su) {
		this.su = su;
	}

	public Integer getEng() {
		return eng;
	}

	public void setEng(Integer eng) {
		this.eng = eng;
	}
	
}
