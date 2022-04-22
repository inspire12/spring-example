package sunghs.springexample.nestedconfiguration;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class SomeNestedProperty {

    private String someNestedName;

    private List<String> someNestedList;
}
