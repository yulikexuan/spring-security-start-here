//: ssia.domain.model.Document.java


package ssia.domain.model;


import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor(staticName = "of")
public class Document {

    private String owner;

}///:~