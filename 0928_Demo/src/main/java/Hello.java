/**
 * 
 */

/**
 * @����  ����
 *
 * @ʱ�� :2019��9��28��
 */
public class Hello {
	private  int  oid;
	private  String oname;
	public int getOid() {
		return oid;
	}
	public void setOid(int oid) {
		this.oid = oid;
	}
	public String getOname() {
		return oname;
	}
	public void setOname(String oname) {
		this.oname = oname;
	}
	@Override
	public String toString() {
		return "Hello [oid=" + oid + ", oname=" + oname + "]";
	}
	public Hello(int oid, String oname) {
		super();
		this.oid = oid;
		this.oname = oname;
	}
	
	

}
