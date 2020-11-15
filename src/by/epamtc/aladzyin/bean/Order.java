package by.epamtc.aladzyin.bean;

import java.io.Serializable;
import java.util.Date;

public class Order implements Serializable {

	private static final long serialVersionUID = 8013293204193809760L;
	
	private String order_id;
	private String status;
	private String sender_id;
	private String executor_id;
	private Date date_in;
	private String town;
	private String route_id;
	
	public Order() {}

	public Order(String sender_id) {
		super();
		this.sender_id = sender_id;
	}

	public Order(String sender_id, String town) {
		super();
		this.sender_id = sender_id;
		this.town = town;
	}

	public String getOrder_id() {
		return order_id;
	}

	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSender_id() {
		return sender_id;
	}

	public void setSender_id(String sender_id) {
		this.sender_id = sender_id;
	}

	public String getExecutor_id() {
		return executor_id;
	}

	public void setExecutor_id(String executor_id) {
		this.executor_id = executor_id;
	}

	public Date getDate_in() {
		return date_in;
	}

	public void setDate_in(Date date_in) {
		this.date_in = date_in;
	}

	public String getTown() {
		return town;
	}

	public void setTown(String town) {
		this.town = town;
	}

	public String getRoute_id() {
		return route_id;
	}

	public void setRoute_id(String route_id) {
		this.route_id = route_id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((date_in == null) ? 0 : date_in.hashCode());
		result = prime * result + ((executor_id == null) ? 0 : executor_id.hashCode());
		result = prime * result + ((order_id == null) ? 0 : order_id.hashCode());
		result = prime * result + ((route_id == null) ? 0 : route_id.hashCode());
		result = prime * result + ((sender_id == null) ? 0 : sender_id.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((town == null) ? 0 : town.hashCode());
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
		Order other = (Order) obj;
		if (date_in == null) {
			if (other.date_in != null)
				return false;
		} else if (!date_in.equals(other.date_in))
			return false;
		if (executor_id == null) {
			if (other.executor_id != null)
				return false;
		} else if (!executor_id.equals(other.executor_id))
			return false;
		if (order_id == null) {
			if (other.order_id != null)
				return false;
		} else if (!order_id.equals(other.order_id))
			return false;
		if (route_id == null) {
			if (other.route_id != null)
				return false;
		} else if (!route_id.equals(other.route_id))
			return false;
		if (sender_id == null) {
			if (other.sender_id != null)
				return false;
		} else if (!sender_id.equals(other.sender_id))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (town == null) {
			if (other.town != null)
				return false;
		} else if (!town.equals(other.town))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Order [order_id=" + order_id + ", status=" + status + ", sender_id=" + sender_id + ", executor_id="
				+ executor_id + ", date_in=" + date_in + ", town=" + town + ", route_id=" + route_id + "]";
	}

}
