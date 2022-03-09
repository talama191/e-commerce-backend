package base.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder.Default;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import org.hibernate.annotations.ColumnDefault;

import base.model.composite.CommentId;

@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Comment {
	@EmbeddedId
	private CommentId id;
	@Column(name="content")
    private String content;
	@ManyToOne()
	@JoinColumn(name = "user_id",insertable=false, updatable=false)
	private User user;
	
	@ManyToOne()
	@JoinColumn(name = "product_id",insertable=false, updatable=false)
	private Product product;
	@Column(name="rate")
	private int rate;
}
