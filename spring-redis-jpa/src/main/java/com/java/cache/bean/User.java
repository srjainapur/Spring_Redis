package com.java.cache.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="REDIS_USER")
public class User implements Serializable {

	/** serialVersionUID **/
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ID")
	private Integer id;
	
	@Column(name="NAME")
	private String name;
	
	@Column(name="FOLLOWER")
	private Long follower;

	public User() {
	
	}

	public User(Integer id, String name, Long follower) {
		this.id = id;
		this.name = name;
		this.follower = follower;
	}

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

	public Long getFollower() {
		return follower;
	}

	public void setFollower(Long follower) {
		this.follower = follower;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", follower=" + follower + "]";
	}
}
