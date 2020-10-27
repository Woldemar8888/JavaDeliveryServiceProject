package by.epamtc.aladzyin.bean;

import java.io.Serializable;
import java.util.Date;

public class Route implements Serializable {

	private static final long serialVersionUID = 2036529263710895436L;
	
	private String status;
	private Date dateStart;
	private Date dateEnd;
	private int carID;
	
	public Route() {
	}
	
	public Route(String status, Date dateStart, Date dateEnd, int carID) {
		super();
		this.status = status;
		this.dateStart = dateStart;
		this.dateEnd = dateEnd;
		this.carID = carID;
	}
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getDateStart() {
		return dateStart;
	}

	public void setDateStart(Date dateStart) {
		this.dateStart = dateStart;
	}

	public Date getDateEnd() {
		return dateEnd;
	}



	public void setDateEnd(Date dateEnd) {
		this.dateEnd = dateEnd;
	}

	public int getCarID() {
		return carID;
	}

	public void setCarID(int carID) {
		this.carID = carID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + carID;
		result = prime * result + ((dateEnd == null) ? 0 : dateEnd.hashCode());
		result = prime * result + ((dateStart == null) ? 0 : dateStart.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Route other = (Route) obj;
		if (carID != other.carID)
			return false;
		if (dateEnd == null) {
			if (other.dateEnd != null)
				return false;
		} else if (!dateEnd.equals(other.dateEnd))
			return false;
		if (dateStart == null) {
			if (other.dateStart != null)
				return false;
		} else if (!dateStart.equals(other.dateStart))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Route [status=" + status + ", dateStart=" + dateStart + ", dateEnd=" + dateEnd + ", carID=" + carID
				+ "]";
	}
	
}
