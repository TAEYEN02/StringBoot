package com.korea.dependency.dependency;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("Apple")
public class Apple extends Computer {

}
