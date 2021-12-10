package sunghs.springexample.springcache;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class CacheData {

    private String value;

    private LocalDateTime expirationDate;
}