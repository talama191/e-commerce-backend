package base.model.entity;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "product")
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name="price")
	private Long price;
	
	@Column(name = "img1")
	private String img1;
	
	@Column(name = "img2")
	private String img2;
	
	@Column(name = "img3")
	private String img3;
	
	@Column(name = "img4")
	private String img4;
	
	
	@Column(name = "short_description")
	private String shortDescription;
	
	@Column(name = "long_description")
	private String longDescription;
	
	@ManyToOne
	@JoinColumn(name = "category_id")
	private Category category;
	
	@OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
	@JsonIgnore
	List<CartLine> cartLines = new ArrayList<>();

	@OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
	@JsonIgnore
	List<Comment> comment = new ArrayList<>();

}
