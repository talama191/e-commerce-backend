package base.model.entity.form;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter @Setter
@NoArgsConstructor
public class CommentForm {
  private int userId;
  private int productId;
  private String comment;
  private int rate;
}
