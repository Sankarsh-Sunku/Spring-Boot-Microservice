package com.bajaj.apiGateway.dto;

import java.util.List;
import java.util.Objects;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;



public class ApiKey {
	
	private String gatewayKey;
	private List<String> services;
	public String getGatewayKey() {
		return gatewayKey;
	}
	public void setGatewayKey(String gatewayKey) {
		this.gatewayKey = gatewayKey;
	}
	public List<String> getServices() {
		return services;
	}
	public void setServices(List<String> services) {
		this.services = services;
	}
	@Override
	public String toString() {
		return "ApiKey [gatewayKey=" + gatewayKey + ", services=" + services + "]";
	}
	@Override
	public int hashCode() {
		return Objects.hash(gatewayKey, services);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ApiKey other = (ApiKey) obj;
		return Objects.equals(gatewayKey, other.gatewayKey) && Objects.equals(services, other.services);
	}
	public ApiKey(String gatewayKey, List<String> services) {
		super();
		this.gatewayKey = gatewayKey;
		this.services = services;
	}
	public ApiKey() {
		super();
	}
	
	
	
}
