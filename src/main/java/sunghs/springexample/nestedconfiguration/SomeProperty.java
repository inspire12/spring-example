package sunghs.springexample.nestedconfiguration;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

@Getter
@Setter
@ToString
public class SomeProperty {

    private String someName;

    private boolean isEnabled;

    @NestedConfigurationProperty
    private SomeNestedProperty someNestedProperty;
}
