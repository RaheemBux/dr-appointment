package com.uni.drappointment.searchFilters;

import com.uni.drappointment.entity.UserEntity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class UserFilter implements Specification<UserEntity> {
    private String userName;
    private String rollName;
    private String email;

    @Override
    public Predicate toPredicate(Root<UserEntity> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        List<Predicate> predicates = new ArrayList<>();
        if (userName != null && !userName.equals("") && !userName.equals("undefined")){
            predicates.add(builder.like(builder.upper(root.get("userName")), "%" + userName.toUpperCase() + "%"));
        }
        if (email != null && !email.equals("") && !email.equals("undefined")) {
            predicates.add(builder.like(builder.upper(root.get("email")), "%" + email.toUpperCase() + "%"));
        }
        return builder.and(predicates.toArray(new Predicate[0]));
    }
}
