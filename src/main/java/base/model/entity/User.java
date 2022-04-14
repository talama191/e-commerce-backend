package base.model.entity;

import java.nio.MappedByteBuffer;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "\"user\"")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private Integer id;
	
	@Column(name = "username")
	private String username;
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "fullname")
	private String fullname;
	
	@Column(name = "phone_number")
	private String phoneNumber;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "address")
	private String address;
	
	@Column(name = "gender")
	private Character gender;
	
	@Column(name = "dob")
	private Date dob;
	
	@OneToOne(mappedBy = "user", fetch = FetchType.LAZY)
	@JsonIgnore
	private Cart cart;
	
	@ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
	List<Order> orders = new ArrayList<>();


	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
	@JsonIgnore
	List<Comment> comment = new ArrayList<>();


}
