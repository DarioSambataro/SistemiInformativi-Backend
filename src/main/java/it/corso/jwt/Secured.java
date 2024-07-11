package it.corso.jwt;

import java.lang.annotation.*;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.annotation.ElementType;
import jakarta.ws.rs.NameBinding;

@NameBinding
//Questa annotation specifica che l'annotation personalizzata secured dev'essere convertita in fase di runtime
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
public @interface Secured {
	String role() default "all";
}
