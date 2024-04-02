package kringlooptilburg.nl.productimageservice.mappers;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

public interface Mapper<A,B> {
    B mapTo(A a);
    A mapFrom(B b);
}